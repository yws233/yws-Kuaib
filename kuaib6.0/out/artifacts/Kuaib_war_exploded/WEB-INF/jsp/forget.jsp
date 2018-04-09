<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-1-16
  Time: 下午11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>忘记密码</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/forget.css">

</head>
<body style="background: #E9EAEE">
<%--上方logo显示--%>
<div id="bottom-logo" style="background: #4B63BB;width: 1360px;height: 59px;text-align: center;font-weight: bold">
    <span style="font-size: 50px;color: white">Kuaib</span>
</div>
<div class="context">
    <div class="confirm_register">
        <h1 style="text-align: center;color: #4B63BB;margin-top: 50px;font-size: 43px">找回密码</h1>
        <form id="register-form" action="${pageContext.request.contextPath}/backpassword.html" method="post" role="form" autocomplete="off">
            <div class="form-group">
                <label for="email" style="margin-left: 23px">Email Address</label>
                <input onclick="btn()" type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="" autocomplete="off" required="" onmousemove="btnclear()">
                <span id="email-message">${message}</span>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="sub-btn">
                        <input type="submit" name="recover-submit" id="recover-submit" tabindex="2" class="btn-success" value="Recover Account">
                    </div>
                    <div id="sub-btn">
                        <a id="back-login" href="${pageContext.request.contextPath}/backlogin.html">Back Login</a>
                    </div>
                </div>
            </div>
            <input type="hidden" class="hide" name="token" id="token" value="59236086ea3e53b5d97dfd730f78e4a5">
        </form>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/forget.js"></script>
</body>
</html>
