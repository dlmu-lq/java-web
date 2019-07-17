package top.itlq.javaweb.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class TestHttpServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("testHttpServlet method doGet()");
        // 全局参数 context-params
        ServletContext servletContext = request.getServletContext();
        Enumeration<String> contextParamNames = servletContext.getInitParameterNames();
        while (contextParamNames.hasMoreElements()){
            String name = contextParamNames.nextElement();
            System.out.println("context-param name:" + name + ";value:"
                    + servletContext.getInitParameter(name));
        }
        // 本servlet参数
        ServletConfig servletConfig = getServletConfig();
        Enumeration<String> initParamNames = servletConfig.getInitParameterNames();
        while (initParamNames.hasMoreElements()){
            String name = initParamNames.nextElement();
            System.out.println("init-param name:" + name + ";value:"
                    + servletConfig.getInitParameter(name));
        }
    }
}
