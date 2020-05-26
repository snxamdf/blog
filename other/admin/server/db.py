#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-
# 数据库


import pymysql

dbinfo = {
    "host": "localhost",
    "port": 3306,
    "username": "root",
    "password": "root",
    "database": "blog",
    "charset": "utf8"
}


class Database:
    def Connection(self):
        # 连接数据库
        conn = pymysql.connect(host=dbinfo["host"], port=dbinfo["port"], user=dbinfo["username"],
                               password=dbinfo["password"], database=dbinfo["database"], charset=dbinfo["charset"])
        self.conn = conn
        print("连接数据库成功")
        return conn

    def Close(self):
        print("关闭数据库成功")
        self.conn.close()

# db = Database()
# connection = db.Connection()
# print(connection)
