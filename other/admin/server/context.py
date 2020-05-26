#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-

# 导入Flask框架，这个框架可以快捷地实现了一个WSGI应用
import os

from flask import Flask

# 传递根目录
cwd = os.getcwd()
app = Flask(__name__, root_path="web")
