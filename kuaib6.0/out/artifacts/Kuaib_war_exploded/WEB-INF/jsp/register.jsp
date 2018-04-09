<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-1-19
  Time: 上午9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>用户注册</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/register.css">
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.2.1.min.js" type="text/javascript"></script>
</head>
<body style="background: #E9EAEE">
<%--上方logo显示--%>
<div id="bottom-logo" style="background: #4B63BB;width: 1360px;height: 59px;text-align: center;font-weight: bold">
    <span style="font-size: 50px;color: white">Kuaib</span>
</div>
    <div>
        <form id="doregister" action="${pageContext.request.contextPath}/adduser.html" method="post">
            <h1 style="margin-top:70px;text-align: center;color: #4B63BB;font-size: 43px;">用户注册</h1>
            <div>
                <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath}"/><%--存放绝对路径，便于ajax访问--%>
                <input style="background: #EFFFB4;" type="text" id="userCode" placeholder="userCode" name="userCode">
                <!-- 放置提示信息 -->
                <span color="red"></span>
            </div>
            <div>
                <input style="background: #EFFFB4;" type="email" id="email" placeholder="email" name="email">
                <!-- 放置提示信息 -->
                <span color="red"></span>
            </div>
            <div>
                <input style="background: #EFFFB4;" type="password" id="userPassword" autocomplete="new-password" placeholder="userPassword" name="userPassword">
                <!-- 放置提示信息 -->
                <span color="red"></span>
            </div>
            <div>
                <input style="background: #EFFFB4;" type="password" id="ruserPassword" autocomplete="new-password" placeholder="ruserPassword" name="ruserPassword">
                <!-- 放置提示信息 -->
                <span color="red"></span>
            </div>
            <input id="regtrue" type="hidden" value="${regtrue}"><%--放置一个隐藏域，存放注册成功弹出信息--%>
            <button class="button-register" type="submit" value="注册">注册</button>
            <a id="a-login" href="${pageContext.request.contextPath}/backlogin.html">登录</a>
        </form>
    </div>
    <%--下方标语栏目--%>
    <%--<div id="bottom-logo">
        <ul id="bottom-ul">
            <li> <span style="font-size: 50px;color: #4B63BB;display: inline">F</span>ast</li>
            <li> <span style="font-size: 50px;color: #4B63BB;display: inline">S</span>imple</li>
            <li> <span style="font-size: 50px;color: #4B63BB;display: inline">C</span>lear</li>
        </ul>
    </div>--%>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/ajax_register.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/ajax_email.js"></script>
</html>
