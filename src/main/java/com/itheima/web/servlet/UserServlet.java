package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
 * HttpServlet 是根据get或者post分发方法，现在咱们自己分发方法，不用区别get或者post
 *       get和post只是数据传输的方法不同
 * */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    // 1.获取service对象
    UserService service = new UserServiceimpl();
    /*
     登录
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 获取前台发送过来的数据
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String type = req.getParameter("type");
        // 封装到User 对象中
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(passWord);
        user.setType(type);
        // 调用service对象的登录方法进行逻辑判断
        User login = service.login(user);

        // 如果返回的 login 对象不是空，说明在数据库中查到数据了  可以登录  进行重定向跳转
        if (login != null){
            // 将登录的用户设置一个session，以此可以验证 不是非法入侵
            HttpSession session = req.getSession();
            session.setAttribute("user",login);
            // 页面重定向，跳转到index页面
            String path = req.getContextPath();
            System.out.println(path);
            resp.sendRedirect(path + "/index.jsp");
        }else{
            // 如果login对象为空，则登录失败
            req.setAttribute("login_msg","账号或密码错误！");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    /*
        退出
     */
    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 1.销毁session对象
        session.invalidate();
        // 2.获取路径
        // request.getScheme()获取协议http   request.getServerName()获取ip名localhost
        // request.getServerPort()获取端口8080   request.getContextPath()获取虚拟目录schooldorm
        //String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/";
        // 3.重定向到登录页
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    /*
        查询所有宿管
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取全部宿管
        List<User> users = service.selectAll();
        // 2.将users转换为json格式返回
        String jsonString = JSON.toJSONString(users);
        // 3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /*
        新增宿管
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.接收用户的数据
        /*因为前端发送过来的是json数据格式，是一连串的字符*/
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 2.将字符串转换成User对象
        User user = JSON.parseObject(line, User.class);
        // 3.调用service方法
        boolean falg = service.add(user);
        // 4.返回一个标识
        if (falg){
            response.getWriter().write("success");
        }else{
            response.getWriter().write("exist");
        }
    }

    /*
        根据id删除宿管
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.接收用户的数据
        /*因为前端发送过来的是json数据格式，是一连串的字符*/
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 2.将字符串转换成User对象
        Integer id = JSON.parseObject(line, int.class);
        // 3.调用service方法
        service.deleteById(id);
        // 4.返回一个标识
        response.getWriter().write("success");
    }

    /*
        根据id查询宿管
     */
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.接收用户的数据
        /*因为前端发送过来的是json数据格式，是一连串的字符*/
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 2.将字符串转换成User对象
        Integer id = JSON.parseObject(line, int.class);
        // 3.调用service方法
        User user = service.selectById(id);
        // 4.将查询到的用户信息进行返回
        String jsonString = JSON.toJSONString(user);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /*
        根据id修改
     */
    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.接收用户的数据
        /*因为前端发送过来的是json数据格式，是一连串的字符*/
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 2.将字符串转换成User对象
        User user = JSON.parseObject(line, User.class);
        // 3.调用service方法
        boolean falg = service.updateById(user);
        // 4.返回一个标识
        if (falg){
            response.getWriter().write("success");
        }else{
            response.getWriter().write("exist");
        }
    }

    /*
        分页查询宿管
     */
    public void selectBypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.接收用户的数据
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        // 2.将字符串转换成整型对象
        Integer page = Integer.valueOf(currentPage);
        Integer size = Integer.valueOf(pageSize);

        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        User user = JSON.parseObject(line, User.class);
        // 3.调用service方法
        int pageM = (page - 1) * size;
        PageBean<User> pageBean = service.selectBypage(pageM, size,user);
        String jsonString = JSON.toJSONString(pageBean);
        // 返回查询到的数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /*
        多条删除
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.接收前端传过来的ids数组
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        // 2.通过ajax发送过来的数据时json格式，这里进行一个转换
        int[] ids = JSON.parseObject(line, int[].class);
        // 3.调用方法
        service.deleteByIds(ids);
        // 4.返回一个标识符
        response.getWriter().write("success");
    }
}
