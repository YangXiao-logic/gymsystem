from datetime import datetime

from flask_login import UserMixin
from werkzeug.security import generate_password_hash, check_password_hash
from gymsystem.extension import db


class Employee(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    employee_name = db.Column(db.String(30))
    email = db.Column(db.String(256), unique=True, index=True)
    password_hash = db.Column(db.String(128))

    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def validate_password(self, password):
        return check_password_hash(self.password_hash, password)


class Customer(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    customer_name = db.Column(db.String(30), unique=True)
    gender = db.Column(db.String(10))
    age = db.Column(db.Integer)
    portrait = db.Column(db.String(256))

    email = db.Column(db.String(256), unique=True, index=True)
    credit_card_number = db.Column(db.String(256), unique=True, index=True)
    phone_number = db.Column(db.Integer, unique=True)
    identity_card = db.Column(db.Integer, unique=True)
    address = db.Column(db.String(256))

    password_hash = db.Column(db.String(128))

    personal_activities = db.relationship("PersonalActivity", backref='customer')
    shopping_carts = db.relationship("ShoppingCart", backref='customer')

    # password protection
    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def validate_password(self, password):
        return check_password_hash(self.password_hash, password)


class Manager(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    manager_name = db.Column(db.String(30))
    password_hash = db.Column(db.String(128))
    email = db.Column(db.String(256), unique=True, index=True)

    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def validate_password(self, password):
        return check_password_hash(self.password_hash, password)


class Activity(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    activity_name = db.Column(db.String(50), unique=True)

    facility_id = db.Column(db.Integer, db.ForeignKey('facility.id'))
    personal_activities = db.relationship("PersonalActivity", backref='activity')


class PersonalActivity(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    timestamp = db.Column(db.DateTime, default=datetime.utcnow, index=True)

    customer_id = db.Column(db.Integer, db.ForeignKey('customer.id'))
    activity_id = db.Column(db.Integer, db.ForeignKey('activity.id'))


class Facility(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    facility_name = db.Column(db.String(100), unique=True)
    capacity = db.Column(db.Integer)

    activities = db.relationship("Activity", backref="facility")


class Product(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    product_name = db.Column(db.String(256))
    picture = db.Column(db.String(256))
    description = db.Column(db.String(512))
    price = db.Column(db.Integer)

    shopping_cart_id = db.Column(db.Integer, db.ForeignKey('shopping_cart.id'))


class ShoppingCart(db.Model):
    id = db.Column(db.Integer, primary_key=True)

    customer_id = db.Column(db.Integer, db.ForeignKey('customer.id'))
    products = db.relationship("Product", backref="shopping_cart")





