package top.itlq.java.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;

public class TestHttpServlet extends HttpServlet {

    @Override
    public void init(){
        System.out.println("testHttpServlet method init()");
        // 全局参数 context-params
        ServletContext servletContext = getServletContext();
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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("testHttpServlet method doGet()");
        printRequest(request);
        // 响应
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.getWriter().write("hello from testHttpServlet中国");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("testHttpServlet method doPost()");
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        // 根据不同的请求方式，获取数据的方式不同
        printRequest(request);
        printPostParams(request);
        // 响应
        // 包含了对 setHeader("Content-Type","text/plain;charset:utf-8")和 setCharacterEncoding("UTF-8")
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().write("hello from testHttpServlet中国");
    }

    /**
     * 打印request信息
     * @param request
     * @throws UnsupportedEncodingException
     */
    void printRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        // 请求行参数
        System.out.println("request uri:" + request.getRequestURI());
        System.out.println("request url:" + request.getRequestURL());
        System.out.println("request method:" + request.getMethod());
        System.out.println("request contextPath:" + request.getContextPath());
        System.out.println("request queryString:" + request.getQueryString());
        // 请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            System.out.println("headerName " +
                    headerName + ":" + request.getHeader(headerName));
        }
        System.out.println("request contentType:" + request.getContentType());
        // 请求体
        System.out.println(Arrays.toString(request.getParameterMap().get("a")));
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();
            System.out.println("parameterName " +
                    parameterName + ":" + request.getParameter(parameterName));
            // 此处服务器使用的编码方式与浏览器相同；
            System.out.println("parameterName " +
                    parameterName + ":" + new String(
                    request.getParameter(parameterName).getBytes("ISO8859-1"),"UTF-8"));
            System.out.println("parameterName " + parameterName + ":" +
                    Arrays.toString(request.getParameterValues(parameterName)));
        }
    }

    /**
     * 使用流读取post的request信息并打印；
     * @param request
     * @throws IOException
     */
    void printPostParams(HttpServletRequest request) throws IOException {
        System.out.println("read with InputStream");
        byte[] bytes = new byte[request.getContentLength()];
        BufferedInputStream inputStream = new BufferedInputStream(request.getInputStream());
        // todo 优化读取 https://blog.csdn.net/pyxly1314/article/details/51802652
        inputStream.read(bytes,0,bytes.length);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }


}
