package top.itlq.java.web.servlet;

import javax.servlet.*;
import java.io.IOException;

public class TestServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("testServlet inited");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("testServlet service");
//        servletResponse.setCharacterEncoding("utf-8");
        // 只设上面不够用
//        servletResponse.setContentType("text/plain");
        // 只用设下面即可
        servletResponse.setContentType("text/plain;charset=utf-8");
        servletResponse.getWriter().write("hello from testServlet中文");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("testServlet destroyed");
    }
}
