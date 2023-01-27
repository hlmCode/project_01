package com.itheima.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 我自己的servlet
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求的路径
        String uri = req.getRequestURI(); // /schooldorm/user/login
        // 2./login 就是我需要调用的方法     user 是继承我的servlet 起的名字
            // 2.1获取 / 的位置
        int index = uri.lastIndexOf("/");
            // 2.2截取方法名
        String methodName = uri.substring(index + 1);

        // 3.执行方法
        // 3.1获取BrandServlet / UserServlet 字节码对象的Class
        // 谁调用我（this 所在的方法），我（this）代表谁
        Class<? extends BaseServlet> cls = this.getClass();

        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 这样我们就做到了根据请求路径来执行方法，做到了servlet的复用
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
