<%@ page import="cn.kuaib.pojo.Ex" %>
<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-3-5
  Time: 上午12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>导师交换系统</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/roll.css">
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.2.1.min.js" type="text/javascript"></script>
</head>
<body style="background: #F6F6F6">
<%--选课提交信息--%>
<h1 style="text-align: center;color: white;background: #4B63BB;line-height: 59px">信息交换系统&nbsp;&nbsp;<a style="font-size: 16px;color: white;" href="${pageContext.request.contextPath}/sys/user/backmain.html"><< 返回首页</a></h1>
<%--抽取导师--%>
<div class="roll-area" >
    <h2 class="roll-title">课程抽取</h2>
    <p class="show" style="background: white;color: black">Name Show</p>
    <button class="start" onclick="chou()">开始</button>
    <button class="end" onclick="clearTimeout(mytime)">结束</button>
</div>

<div style="background:white;display:inline-block;border: 1px solid #8FB5E8;width: 400px;height: auto;text-align: center;margin-left: 60px">
    <%--提交表单--%>
        <h2 style="text-align: center;color: #4B63BB">提交、查看已选情况</h2>
    <form action="${pageContext.request.contextPath}/sys/user/exadd.html" method="post">
        <br><br>
        <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath}/sys/user"/><%--存放绝对路径，便于ajax访问--%>
        <%--<span style="margin-left: 84px">--%>昵称：<%--</span>--%><input placeholder="昵称.." id="exname" name="exname" type="text">
        <!-- 放置提示信息 -->
        <span style="display: block" color="red"></span>
        <br>
        导师：<input placeholder="导师.." id="exteacher" name="exteacher" type="text"><br><br>
        电话：<input placeholder="电话.." id="exphone" name="exphone" type="text"><br><br>
        <input name="" type="hidden"><br><br>
        <button id="commit-change" type="submit" style="margin-right: 30px;padding: 10px 30px;color: white;background: #4B63BB;border: none;font-size: 18px;font-weight: bold">提交</button>
        <a style="font-size: 18px;font-weight:bold; font-family:文泉驿微米黑;text-decoration: none;list-style: none" href="${pageContext.request.contextPath}/sys/user/exsave.html">查看</a>
        <%--提交成功提示框--%>
        <%--<p style="text-align: center">--%><span style="color: red">${extip}</span><%--</p>--%>
    </form>

</div>

<div style="background:white;display:inline-block;border: 1px solid #8FB5E8;width: 400px;height: auto;text-align: center;margin: 0 auto">
    <%--提交表单--%>
    <h2 style="text-align: center;color: #4B63BB">修改课程信息</h2>
     <%--修改表单--%>
     <form action="${pageContext.request.contextPath}/sys/user/updateex.html" method="get">
         <br><br><br>
         编码：<input placeholder="请输入编码.." name="exid" type="text"><br><br>
         昵称：<input placeholder="请输入昵称.." name="exname" type="text"><br><br>
         导师：<input placeholder="请输入导师.." name="exteacher" type="text"><br><br>
         电话：<input placeholder="请输入电话.." name="exphone" type="text"><br><br>
         <button id="commit-update" type="submit" style="padding: 10px 30px;margin-right: 30px;background: #4B63BB;border: none;font-size: 18px;color: white;font-weight: bold">修改</button>
         <span style="color: green">${updateUser}</span>
         <%--<p style="text-align: center"></p>--%>
     </form>
</div>

<%--已选信息显示--%>
<h2 style="margin-bottom: 30px;text-align: center;margin-top: 30px;color: #4B63BB">导师选择情况显示</h2>
<div style="border: 1px solid #8FB5E8;width: 600px;height: auto;text-align: center;margin: 0 auto">
    <table style="margin: 0 auto">
        <tr style="margin: 0 auto;display: inline-block">
            <td style="color: #4B63BB; text-align:center;display: inline-block;margin-right: 60px">编码<br></td>
            <td style="color: #4B63BB; text-align:center;display: inline-block;margin-right: 60px">昵称<br></td>
            <td style="color: #4B63BB; text-align:center;display: inline-block;margin-right: 60px">导师<br></td>
            <td style="color: #4B63BB; text-align:center;display: inline-block;margin-right: 70px">电话<br></td>
            <td style="text-align:center;display: inline-block;font-size: 24px;color: #4B63BB">已选学生数：${count}<br></td>
        </tr>
        <!--显示信息表-->
        <c:forEach var="change" items="${exList}" varStatus="status">
            <tr style="margin: 0 auto;display: inline-block">
                <td style=" text-align:center;display: inline-block;margin-right: 60px"><span>${change.exid}</span> <%--编码--%></td>
                <td style=" text-align:center;display: inline-block;margin-right: 60px"><span>${change.exname}</span> <%--昵称--%></td>
                <td style=" text-align:center;display: inline-block;margin-right: 60px"><span>${change.exteacher}</span> <%--导师--%></td>
                <td style=" text-align:center;display: inline-block;margin-right: 60px"><span>${change.exphone}</span> <%--电话--%></td>
            </tr>
        </c:forEach>
       <%-- <tr style="margin: 0 auto;display: inline-block">
            <td ><span>1</span></td>
            <td style="text-align:center;display: inline-block;margin-right: 60px"><span>2</span></td>
            <td style="text-align:center;display: inline-block;margin-right: 60px"><span>3</span></td>
        </tr>--%>
    </table>
</div>
<div class="bottom" style="text-align: center">
    <p style="color: black">Copyright © 2017-2018&nbsp;&nbsp;All Rights Reserved<a  target="_blank" href="https://github.com/yws233/yws-Kuaib" style="color: #4B63BB;text-decoration: none">&nbsp;&nbsp;yws&nbsp;快编系统</a></p>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/ajax_exchange.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/roll.js"></script>
</body>
</html>
