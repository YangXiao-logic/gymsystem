import click

from gymsystem.extension import bootstrap, db, login_manager, csrf, ckeditor, moment, migrate
from gymsystem.setting import config
from gymsystem.blueprint.home import home_bp
from gymsystem.blueprint.admin import admin_bp
from gymsystem.blueprint.auth import auth_bp
from gymsystem.blueprint.gym import gym_bp
import os
import logging
from logging.handlers import RotatingFileHandler
from flask import Flask, request

basedir = os.path.abspath(os.path.dirname(os.path.dirname(__file__)))


def create_app(config_name=None):
    if config_name is None:
        config_name = os.getenv('FLASK_CONFIG', 'development')

    app = Flask('gymsystem')
    app.config.from_object(config[config_name])

    register_logging(app)
    register_extensions(app)
    register_blueprints(app)
    register_command(app)
    register_shell_context(app)
    register_template_context(app)
    return app


def register_extensions(app):
    bootstrap.init_app(app)
    db.init_app(app)
    login_manager.init_app(app)
    csrf.init_app(app)
    ckeditor.init_app(app)
    # mail.init_app(app)
    moment.init_app(app)
    # toolbar.init_app(app)
    migrate.init_app(app, db)


def register_logging(app):
    app.logger.setLevel(logging.INFO)

    class RequestFormatter(logging.Formatter):
        def format(self, record):
            record.url = request.url
            record.remote_addr = request.remote_addr
            return super(RequestFormatter, self).format(record)

    formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

    file_handler = RotatingFileHandler(os.path.join(basedir, 'logs/gym.log'),
                                       maxBytes=10 * 1024 * 1024, backupCount=10)
    file_handler.setFormatter(formatter)
    file_handler.setLevel(logging.INFO)

    if not app.debug:
        app.logger.addHandler(file_handler)


def register_blueprints(app):
    app.register_blueprint(home_bp)
    app.register_blueprint(auth_bp, url_prefix='/auth')
    app.register_blueprint(gym_bp, url_prefix='/gym')
    app.register_blueprint(admin_bp, url_prefix='/admin')
    return app


def register_shell_context(app):
    return app


def register_template_context(app):
    return app


def register_command(app):
    @app.cli.command()
    @click.option('--drop', is_flag=True, help='Create after drop.')
    def initdb(drop):
        """Initialize the database."""
        if drop:
            click.confirm('This operation will delete the database, do you want to continue?', abort=True)
            db.drop_all()
            click.echo('Drop tables.')
        db.create_all()
        click.echo('Initialized database.')

    @app.cli.command()
    @click.option('--user', default=15, help='Quantity of users, default is 5.')
    @click.option('--question', default=100, help='Quantity of questions, default is 30.')
    @click.option('--answer', default=300, help='Quantity of answers, default is 100.')
    def forge(user, question, answer):
        from gymsystem.fakes import fake_facility, fake_manager, fake_customer, fake_activity, fake_personal_activity, \
            fake_employee, fake_product, fake_shopping_cart
        """Generate fake data."""
        db.drop_all()
        db.create_all()

        # click.echo('Generating %d users...' % user)
        fake_employee(3)
        fake_customer(100)
        fake_manager(30)
        fake_facility()
        fake_activity()
        fake_personal_activity(200)

        fake_shopping_cart(200)
        fake_product(300)

