<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-4-25
  Time: 上午10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" href="/statics/editormd/lib/codemirror/codemirror.min.css">
    <script type="text/javascript" src="/statics/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/statics/editormd/editormd.min.js"></script>
    <link rel="stylesheet" href="/statics/editormd/css/editormd.css">
</head>
<body>
<div id="layout">
    <header>
        <h1>动态创建 Editor.md</h1>
        <p>Dynamic create Editor.md</p>
        <br>
        <div class="btns" style="margin:0;">
            <button id="create-btn">动态创建一个 Editor.md</button>
            <button id="remove-btn">移除 Editor.md</button>
        </div>
    </header>
</div>
<script type="text/javascript">
    var testEditormd;

    $(function () {
        $("#create-btn").click(function () {

            $("#layout").append("<div id=\"test-editormd\"></div>");

            testEditormd = editormd("test-editormd", {
                width: "90%",
                height: 640,
                emoji: true,
                markdown: "### 动态创建 Editor.md\r\n\r\nDynamic create Editor.md",
                path: '${pageContext.request.contextPath}/statics/editormd/lib/'

            });

        });

        $("#remove-btn").click(function () {
            testEditormd.editor.remove();
        });
    });
</script>
</body>
</html>
