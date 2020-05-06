package com.sxm.blog.admin.controller;import com.sxm.blog.admin.utils.Result;import com.sxm.blog.admin.utils.Results;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.ResponseBody;import javax.servlet.http.HttpServletRequest;/** * @author * @description * @date 2020/5/6 */@Controllerpublic class LoginController {    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})    @ResponseBody    public Result<String> login(@RequestParam(value = "error", required = false) String error,                                @RequestParam(value = "logout", required = false) String logout) {        if (error != null) {            return Results.fail(-1, "用户名或者密码不正确", null);        }        if (logout != null) {            return Results.success(10, "退出成功", null);        }        return Results.success(20, "返回登录页面", null);    }    @RequestMapping(value = "/main", method = RequestMethod.GET)    @ResponseBody    public Result<String> main(HttpServletRequest request) {        return Results.success(1000, "登录成功返回", "登录成功返回");    }}