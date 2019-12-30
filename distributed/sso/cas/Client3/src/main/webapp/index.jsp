<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>青橙用户中心</title>
</head>
<body>
<%=SecurityContextHolder.getContext().getAuthentication().getName()%>   欢迎来到青橙用户中心
<a href="/logout/cas">退出</a>
</body>
</html>
