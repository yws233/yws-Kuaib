<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-1-17
  Time: 下午4:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件储存路径</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
</head>
<body>
<%
    String path = (String) session.getAttribute("filepath");
    path = "file://" + path;
    session.setAttribute("path",path);

%>
    <h1>
        文件路径为：<a href="${path}">${path}</a>
    </h1>
</body>
</html>
