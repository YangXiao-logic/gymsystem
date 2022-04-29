from faker import Faker
import random
from sqlalchemy.exc import IntegrityError

from gymsystem.extension import db
from gymsystem.models import Customer, Activity, PersonalActivity, Manager, Employee, Facility, ShoppingCart, Product
import random
fake = Faker()


def fake_employee(count):
    for i in range(count):
        employee = Employee(employee_name=fake.name(),
                            email=fake.email())
        employee.set_password('123456')
        try:
            db.session.add(employee)
        except IntegrityError:
            db.session.rollback()
    db.session.commit()


def fake_manager(count):
    for i in range(count):
        manager = Manager(manager_name=fake.name(),
                          email=fake.email())
        manager.set_password('123456')
        try:
            db.session.add(manager)
        except IntegrityError:
            db.session.rollback()
    db.session.commit()


def fake_customer(count):
    gender=["male", "famale"]
    for i in range(count):
        portrait='random_%d.jpg' % i
        customer = Customer(customer_name=fake.name(),
                            portrait=portrait,
                            email=fake.email(),
                            credit_card_number=fake.credit_card_number(),
                            gender=gender[random.randint(0,1)],
                            phone_number=fake.phone_number(),
                            identity_card=fake.ssn(),
                            address=fake.address(),
                            age=random.randint(18,60))
        customer.set_password('123456')
        try:
            db.session.add(customer)
        except IntegrityError:
            db.session.rollback()
    db.session.commit()


def fake_facility():
    facility_names = ["Swimming pool", "Fitness room", "Squash courts", "Sports hall"]
    for facility_name in facility_names:
        facility = Facility(facility_name=facility_name, capacity=100)
        try:
            db.session.add(facility)
        except IntegrityError:
            db.session.rollback()
    db.session.commit()


def fake_activity():
    activity_names = ["General use", "Lane swimming", "Lessons", "Team events"]
    facility = Facility.query.get(1)
    for activity_name in activity_names:
        activity = Activity(activity_name=activity_name,facility=facility)
        try:
            db.session.add(activity)
        except IntegrityError:
            db.session.rollback()
    db.session.commit()


def fake_personal_activity(count):
    for i in range(count):
        customer = Customer.query.get(random.randint(1, Customer.query.count()))
        activity = Activity.query.get(random.randint(1, Activity.query.count()))
        personal_activity = PersonalActivity(customer=customer, activity=activity)
        try:
            db.session.add(personal_activity)
        except IntegrityError:
            db.session.rollback()
    db.session.commit()


def fake_shopping_cart(count):
    for i in range(count):
        customer = Customer.query.get(random.randint(1, Customer.query.count()))
        shopping_cart = ShoppingCart(customer=customer)
        try:
            db.session.add(shopping_cart)
        except IntegrityError:
            db.session.rollback()
    db.session.commit()


def fake_product(count):
    for i in range(count):
        picture = 'random_%d.jpg' % i
        shopping_cart = ShoppingCart.query.get(random.randint(1, ShoppingCart.query.count()))
        product = Product(product_name=fake.word(),
                          picture=picture,
                          description=fake.sentence(),
                          price=random.randint(30,400),
                          shopping_cart=shopping_cart)
        try:
            db.session.add(product)
        except IntegrityError:
            db.session.rollback()
    db.session.commit()
