package top.itlq.javaweb.servlet;

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
        servletResponse.getWriter().write("hello from testServlet");
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
