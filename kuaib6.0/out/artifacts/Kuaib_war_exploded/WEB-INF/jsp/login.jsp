<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-1-12
  Time: 下午4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>Kuaib系统</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/statics/css/login.css" rel="stylesheet" type="text/css">
    <%--<link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/favicon.ico">--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/default.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/component.css" />
    <script src="${pageContext.request.contextPath}/statics/js/modernizr.custom.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <style type="text/css">
        body,td,th {
            font-family: Lato, Calibri, Arial, sans-serif;
        }
    </style>
</head>
<body style="background: #E9EAEE">
<div class="navbar-header">
    <div class="logo">
        <h1 id="logo-png">
            <a id="head-a-hover" href="https://github.com/yws233/yws-Kuaib"><span style="font-size: 50px">K</span>uaib.com</a>
        </h1>
    </div>
</div>

<%--图标显示区域--%>
<div class="container demo-3">

    <ul class="grid cs-style-4">
        <li>
            <figure>
                <div><img src="${pageContext.request.contextPath}/statics/images/img01.png" alt="img05"></div>
                <figcaption>
                    <a href="https://github.com/yws233/yws-Kuaib">便捷查询</a>
                </figcaption>
            </figure>
        </li>
        <li>
            <figure>
                <div><img src="${pageContext.request.contextPath}/statics/images/img02.png" alt="img06"></div>
                <figcaption>
                    <a href="https://github.com/yws233/yws-Kuaib">更加实用</a>
                </figcaption>
            </figure>
        </li>
        <li>
            <figure>
                <div><img src="${pageContext.request.contextPath}/statics/images/img05.png" alt="img02"></div>
                <figcaption>
                    <a href="https://github.com/yws233/yws-Kuaib">赏心悦目</a>
                </figcaption>
            </figure>
        </li>
        <li>
            <figure>
                <div><img src="${pageContext.request.contextPath}/statics/images/img03.png" alt="img04"></div>
                <figcaption>
                    <a href="https://github.com/yws233/yws-Kuaib">单一窗口</a>
                </figcaption>
            </figure>
        </li>
        <li>
            <figure>
                <div><img src="${pageContext.request.contextPath}/statics/images/img04.png" alt="img01"></div>
                <figcaption>
                    <a href="https://github.com/yws233/yws-Kuaib">众多功能</a>
                </figcaption>
            </figure>
        </li>
        <li>
            <figure>
                <div><img src="${pageContext.request.contextPath}/statics/images/img06.png" alt="img03"></div>
                <figcaption>
                    <a href="https://github.com/yws233/yws-Kuaib">直达所思</a>
                </figcaption>
            </figure>
        </li>
    </ul>
</div><!-- /container -->
<script src="${pageContext.request.contextPath}/statics/js/toucheffects.js"></script>

<div style="display: inline-block;margin-top: 150px;margin-left:147px;font-size: 30px;color: #4B63BB;position: absolute">
    <h3>在这里，</h3>
    <h3>让编写变得简单纯粹，高效实用；</h3>
    <h3>也让查询更加友好。${msg}</h3>
</div>
<h1 id="welcome" style="margin-left: 760px">Welcome to Kuaib</h1>
<div class="all">
    <div class="container">

        <div class="panel-heading">
            <div class="col-1">
                <a href="#" class="active">Login</a>
            </div>
            <div class="col-2">
                <a href="${pageContext.request.contextPath}/register.html">Register</a>
            </div>
        </div>
        <div class="panel-body">
            <%--登录界面--%>
            <form id="login-form" action="${pageContext.request.contextPath}/dologin.html" method="post" role="form" style="display: block;">
                <div class="form-group">
                    <input type="text" name="userCode" id="userCode" tabindex="1" class="form-control" placeholder="Username" value="">
                    <span style="color: red;margin-left: 50px">${error}</span>
                </div>
                <div class="form-group">
                    <input type="password" name="userPassword" id="userPassword" tabindex="2" class="form-control" placeholder="Password">
                </div>
                <%--图片验证码--%>
                <div class="form-group">
                    <input style="width: 260px;display: inline-block;margin-left: 51px" type="text" id="code_input" value="" placeholder="请输入验证码" />
                    <p id="v_container" style="display: inline-block;height: 40px;width: 130px"></p>
                    <input type="text" id="confirm-pic" name = "picconfirm">
                </div>
                <div class="text-center"><%--此处设置cookie缓存--%>
                    <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                    <label for="remember"> Remember Me</label>
                </div>
                <div class="login-submit">
                        <div class="log">
                            <input onclick="check()" type="submit" name="login-submit" id="login-submit" tabindex="4"  value="Log In">
                        </div>
                </div>
                <div >
                    <a class="forget-pwd" href="${pageContext.request.contextPath}/back.html">Forgot Password?</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 来必力City版安装代码 -->
<h2  style="margin-top:10px;color: black;background: white;font-weight: bold;font-family:"Helvetica Neue", Helvetica, Arial, sans-serif"><span style="color: #6E43FC;margin-left: 5px;font-size: 50px">K</span><span style="color: #F11A26">u</span><span style="color: #F5CC00">a</span><span style="color: #6E43FC"></span><span style="color: #00BB4B">i</span><span style="color: #F11A26">b</span>：可在下方评论，您的批评与意见是我们最大的财富！</h2>
<div id="lv-container" data-id="city" data-uid="MTAyMC8zNTU1Mi8xMjA4OA==">
    <script type="text/javascript">
        (function(d, s) {
            var j, e = d.getElementsByTagName(s)[0];

            if (typeof LivereTower === 'function') { return; }

            j = d.createElement(s);
            j.src = 'https://cdn-city.livere.com/js/embed.dist.js';
            j.async = true;

            e.parentNode.insertBefore(j, e);
        })(document, 'script');
    </script>
    <noscript>为正常使用来必力评论功能请激活JavaScript</noscript>
</div>
<!-- City版安装代码已完成 -->
<div class="bottom">
    <p style="color: black">Copyright © 2017-2018&nbsp;&nbsp;All Rights Reserved<a target="_blank" href="https://github.com/yws233/yws-Kuaib" style="color: #4B63BB">&nbsp;&nbsp;yws&nbsp;快编系统</a></p>
</div>

<!--点击切换登录和注册-->
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/remember.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/login_pic.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/login_pic_onblur.js"></script>
<%--注册异步验证--%>

</body>
</html>
