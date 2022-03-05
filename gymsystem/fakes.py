
from faker import Faker
import random
from sqlalchemy.exc import IntegrityError

from gymsystem.extension import db
from gymsystem.models import User, Activity, PersonalActivity

fake = Faker()


def fake_users(count):
    for i in range(count):
        user = User(username=fake.name())
        user.set_password('123456')
        try:
            db.session.add(user)
        except IntegrityError:
            db.session.rollback()
    user = User(username='admin', is_admin=True)
    user.set_password('admin')
    db.session.add(user)
    db.session.commit()






