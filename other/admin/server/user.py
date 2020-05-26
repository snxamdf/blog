#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-

# 导入Flask框架，这个框架可以快捷地实现了一个WSGI应用
import datetime
import traceback

from flask import Response, jsonify, render_template, request

from server.context import app
from server.db import Database


# 默认路径访问登录页面
@app.route('/')
def login():
    return render_template('login.html')


# 默认路径访问注册页面
@app.route('/regist')
def regist():
    return render_template('regist.html')


# 设置响应头
def Response_headers(content):
    resp = Response(content)
    resp.headers['Access-Control-Allow-Origin'] = '*'
    return resp


# 获取注册请求及处理
@app.route('/registuser', methods=['POST'])
def getRigistRequest():
    # 把用户名和密码注册到数据库中
    name = request.form.get('name')
    pwd = request.form.get('pwd')
    dt = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    # 连接数据库,此前在数据库中创建数据库TESTDB
    db = Database()
    conn = db.Connection()
    # 使用cursor()方法获取操作游标
    cursor = conn.cursor()
    # SQL 插入语句
    sql = "INSERT INTO user(name, pwd, time) VALUES ('%s', '%s', '%s')" % (
        name, pwd, dt)
    print("SQL：", sql)
    try:
        # 执行sql语句
        cursor.execute(sql)
        # 提交到数据库执行
        conn.commit()
        # 注册成功之后跳转到登录页面
        return render_template('login.html')
    except:
        # 抛出错误信息
        traceback.print_exc()
        # 如果发生错误则回滚
        conn.rollback()
        return '注册失败'
    finally:
        # 关闭数据库连接
        db.Close()


# 获取登录参数及处理
@app.route('/login', methods=['POST'])
def getLoginRequest():
    # 查询用户名及密码是否匹配及存在
    # 连接数据库,此前在数据库中创建数据库TESTDB
    db = Database()
    conn = db.Connection()
    # 使用cursor()方法获取操作游标
    cursor = conn.cursor()
    # SQL 查询语句
    name = request.form.get('name')
    pwd = request.form.get('pwd')

    sql = "select * from user where name='%s' and pwd='%s';" % (name, pwd)
    print("SQL：", sql)
    try:
        # 执行sql语句
        cursor.execute(sql)
        results = cursor.fetchall()
        print(results)
        if len(results) == 1:
            data = {}
            data["code"] = '登录成功'
            for row in results:
                data["id"] = row[0]
                data["pwd"] = row[1]
                data["name"] = row[2]
                data["time"] = row[3]
            return jsonify(data)
        else:
            return '用户名或密码不正确'
    except:
        # 如果发生错误则回滚
        traceback.print_exc()
        conn.rollback()
    finally:
        # 关闭数据库连接
        db.Close()
