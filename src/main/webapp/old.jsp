<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDateTime" %>
<html>
<body>
<%! private int a = 1; %>
<%
    out.println("星期" + LocalDateTime.now().getDayOfWeek().getValue());
%>
<br/>
<%= "url:" + request.getRequestURL()%>
<br/>
<%= "第" + (a++) + "次访问"%>
</body>
</html>
