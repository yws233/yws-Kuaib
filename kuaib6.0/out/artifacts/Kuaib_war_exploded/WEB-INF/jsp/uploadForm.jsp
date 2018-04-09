<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-1-22
  Time: 下午2:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>文件上传</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
</head>
<body>
<h2>文件上传</h2>
<form action="upload" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td>文件描述:</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td>请选择文件:</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td><input type="submit" value="上传"></td>
        </tr>
    </table>
</form>
</body>
</html>
