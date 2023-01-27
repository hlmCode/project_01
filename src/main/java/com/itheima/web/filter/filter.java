package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*登录验证过滤器*/
@WebFilter("/*")
public class filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 判断访问的资源的路径是否和登录注册有关
        //1,在数组中存储登陆和注册相关的资源路径
        String[] urls = {"/login.jsp","/login","/css/","/element-ui/","/imgs/","/js/","/less/"};
        //2.获取当前访问的资源路径
        String url = request.getRequestURL().toString();
        //3,遍历数组，获取到每一个需要放行的资源路径
        for (String u : urls) {
            //4,判断当前访问的资源路径字符串是否包含要放行的的资源路径字符串
                /*
                比如当前访问的资源路径是 /schooldorm/login.jsp
                而字符串 /schooldorm/login.jsp 包含了 字符串 /login.jsp ，所以这个字符串就需要放行
                */
            if (url.contains(u)){
                filterChain.doFilter(request,servletResponse);
                return;
            }
        }
        // 1.判断Session中是否存在user
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        // 用户登录过，放行
        if (user!= null){
            filterChain.doFilter(request,servletResponse);
        }else{
            request.setAttribute("filter_msg","您尚未登录");
            request.getRequestDispatcher("/login.jsp").forward(request,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
