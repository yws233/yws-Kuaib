<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-1-16
  Time: 下午11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>密码</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/backpwd.css">
</head>
<body style="background: #E9EAEE">
<img style="display: block;" src="${pageContext.request.contextPath}/statics/images/headback.png">
<div class="backpwd">
    <h1 style="text-align: center;color: #4B63BB;margin-top: 50px;font-size: 43px">密码</h1>
    <h1 style="color: #4B63BB;text-align: center">Your Password</h1>
    <span style="text-decoration:underline;font-weight: bold;font-size: 32px">${userpwd}</span>
    <a id="back-login" href="${pageContext.request.contextPath}/backlogin.html">&lt;&nbsp;返回登录</a>
</div>
</body>
</html>
