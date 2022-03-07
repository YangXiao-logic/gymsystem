
from flask import render_template, flash, redirect, url_for, Blueprint, current_app

home_bp=Blueprint('home', __name__)

@home_bp.route('/')
def test():
    return render_template('hello.html',test="123")