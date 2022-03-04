
from flask import render_template, flash, redirect, url_for, Blueprint, current_app
from flask_login import login_user, logout_user, current_user
from askanswer.extension import db
from askanswer.models import User
from askanswer.forms import RegisterForm, LoginForm

auth_bp = Blueprint('auth', __name__)


@auth_bp.route('/register', methods=['GET', 'POST'])
def register():
    form = RegisterForm()
    if form.validate_on_submit():
        username = form.username.data
        password = form.password.data
        new_user = User(username=username)
        new_user.set_password(password)
        db.session.add(new_user)
        db.session.commit()
        flash('New user registered.', 'Success')
        current_app.logger.info('New user registered.')
        return redirect(url_for('auth.login'))
    return render_template('auth/register.html', form=form)


# login function
@auth_bp.route('/login', methods=['GET', 'POST'])
def login():
    if current_user.is_authenticated:
        return redirect(url_for('home.index'))
    form = LoginForm()
    if form.validate_on_submit():
        username = form.username.data
        password = form.password.data
        remember = form.remember.data
        user = User.query.filter_by(username=username).first()
        # Judge if the user is none
        if user:
            # validate user
            if user.validate_password(password):
                login_user(user, remember)
                flash('Login success.', 'info')
                current_app.logger.info('Login success.')
                return redirect(url_for('home.index'))
            flash('Invalid username or password.', 'warning')
            current_app.logger.warning('Invalid username or password.')
        else:
            flash('No account.', 'warning')
            current_app.logger.warning('No account.')
    return render_template('auth/login.html', form=form)


# log out current user.
@auth_bp.route('/logout')
def logout():
    logout_user()
    flash('Logout success.', 'info')
    current_app.logger.info('Logout success.')
    return redirect(url_for('home.index'))