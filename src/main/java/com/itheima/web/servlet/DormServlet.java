package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Dorm;
import com.itheima.pojo.Louyu;
import com.itheima.pojo.PageBean;
import com.itheima.service.DormService;
import com.itheima.service.impl.DormServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/dorm/*")
public class DormServlet extends BaseServlet{
    /**
     * 1.获取DormService对象
     */
    DormService service = new DormServiceimpl();

    /**
     * 条件分页查询
     */
    public void selectPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.接收用户的数据
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        // 2.将字符串转换成整型对象
        Integer page = Integer.valueOf(currentPage);
        Integer size = Integer.valueOf(pageSize);

        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        Dorm dorm = JSON.parseObject(line, Dorm.class);
        // 3.调用service方法
        PageBean<Dorm> louyuPageBean = service.selectPage(page, size, dorm);
        String jsonString = JSON.toJSONString(louyuPageBean);

        // 返回查询到的数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
