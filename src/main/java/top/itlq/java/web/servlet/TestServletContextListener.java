package top.itlq.java.web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 测试servlet全局监听器
 */
public class TestServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent contextEvent){
        contextEvent.getServletContext();
        System.out.println("testServletContextListener method contextInitialized()");
    }
    @Override
    public void contextDestroyed(ServletContextEvent contextEvent){
        System.out.println("testServletContextListener method contextDestroyed()");
    }
}
