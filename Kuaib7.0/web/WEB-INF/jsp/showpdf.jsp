<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-2-9
  Time: 下午1:28
  To change this template use File | Settings | File Templates.
  pdf操作文件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>pdf上传</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <link href="${pageContext.request.contextPath}/statics/css/showpdf.css" rel="stylesheet">
    <script type="text/javascript">
        function back() {
            history.go(-1);
        }
    </script>
</head>
<body style="background: #E9EAEE">
<span>请上传您的pdf文件!</span><span id="back-btn" onclick="back()">&lt;&lt;返回</span>
<%--<iframe width="100%" height="1000" src="<c:url value="${pageContext.request.contextPath}/statics/pdf/web/viewer.html"/>?file=<c:url value="&lt;%&ndash;${pageContext.request.contextPath}/&ndash;%&gt;${pdfpath}"/>">
        </iframe>--%>
<%--<iframe width="100%" height="1000" src="<c:url value=""/>?file=${pdfpath}">--%>
<iframe width="100%" height="1000" src="${pageContext.request.contextPath}/statics/pdf/web/viewer.jsp">
</iframe>
<%--<span style="color: #4B63BB">文件路径:${pdfpath}</span><br>
<span style="color: #4B63BB">name:${pdfname}</span>--%>
</body>
</html>
