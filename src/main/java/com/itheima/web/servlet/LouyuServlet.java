package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Louyu;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;
import com.itheima.service.LouyuService;
import com.itheima.service.impl.LouyuServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/louyu/*")
public class LouyuServlet extends BaseServlet{
    /**
     * 1.获取LouyuService对象
     */
    LouyuService service = new LouyuServiceimpl();

    /**
     * 分页条件查询
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
        Louyu louyu = JSON.parseObject(line, Louyu.class);
        // 3.调用service方法
        Integer begin = (page - 1) * size;
        PageBean<Louyu> louyuPageBean = service.selectPage(begin, size, louyu);
        String jsonString = JSON.toJSONString(louyuPageBean);

        // 返回查询到的数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 得到所有宿管的名字和id
     */
    public void selectByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 2.调用方法获取宿管对象
        List<User> users = service.selectByUser();
        // 3.将查到的数据转为json格式
        String jsonString = JSON.toJSONString(users);
        // 返回查询到的数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加楼宇
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码格式
        request.setCharacterEncoding("utf-8");
        // 读取数据
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 将传输过来的数据转换为 Louyu对象
        Louyu louyu = JSON.parseObject(line, Louyu.class);
        // 调用方法
        boolean flag = service.add(louyu);
        // 返回一个标识符
        if (flag){
            response.getWriter().write("success");
        }else {
            response.getWriter().write("exist");
        }
    }

    /**
     * 根据id删除
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码格式
        request.setCharacterEncoding("utf-8");
        // 读取数据
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 将传输过来的数据转换为 int类型
        Integer id = JSON.parseObject(line, int.class);
        // 调用方法
        service.deleteById(id);
        // 返回一个标识符
        response.getWriter().write("success");
    }

    /**
     * 根据id查询
     */
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码格式
        request.setCharacterEncoding("utf-8");
        // 读取数据
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 将传输过来的数据转换为 int类型
        Integer id = JSON.parseObject(line, int.class);
        // 调用方法
        Louyu louyu = service.selectById(id);
        // 返回数据
        String jsonString = JSON.toJSONString(louyu);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 根据id修改
     */
    public void updateId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码格式
        request.setCharacterEncoding("utf-8");
        // 读取数据
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 将传输过来的数据转换为 int类型
        Louyu louyu = JSON.parseObject(line, Louyu.class);
        // 调用方法
        boolean flag = service.updateId(louyu);
        // 返回一个标识符
        if (flag){
            response.getWriter().write("success");
        }else {
            response.getWriter().write("exist");
        }
    }
}
