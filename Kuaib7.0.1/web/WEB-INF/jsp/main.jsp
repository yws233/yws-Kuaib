<%--
  Created by IntelliJ IDEA.
  User: yws
  Date: 18-1-13
  Time: 上午9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
    <title>首页</title>
    <!--网页标题左侧显示-->
    <link rel="icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <!--收藏夹显示图标-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/statics/images/kuaib.ico" type="image/x-icon">
    <link href="${pageContext.request.contextPath}/statics/css/main.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/jquery-confirm.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/font-awesome-ie7.min.css">
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/wangEditor-1.1.0-min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/wangEditor-1.1.0.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/wangEditor/wangEditor-fullscreen-plugin.css">
    <style rel="stylesheet" type="text/css">
        .zw-href2{
            width: 30px;
            height: 30px;
            display: inline-block;
            background: url("${pageContext.request.contextPath}/statics/images/github.png") no-repeat 0 10px;
            background-size: 20px;
            margin-left: 10px;
            border-radius: 50%;
        }
        .zw-href3{
            width: 30px;
            height: 30px;
            display: inline-block;
            background: url("${pageContext.request.contextPath}/statics/images/wechat.png") no-repeat 0 10px;
            background-size: 20px;
            margin-left: 5px;
            border-radius: 50%;
        }
        .zw-href4{
            width: 30px;
            height: 30px;
            display: inline-block;
            background: url("${pageContext.request.contextPath}/statics/images/qq.png") no-repeat 0 10px;
            background-size: 20px;
            margin-left: 5px;
            border-radius: 50%;
        }
        .zw-href5{
            width: 30px;
            height: 30px;
            display: inline-block;
            background: url("${pageContext.request.contextPath}/statics/images/twitter.png") no-repeat 0 10px;
            background-size: 20px;
            margin-left: 5px;
            border-radius: 50%;
        }
        .zw-href6{
            width: 30px;
            height: 30px;
            display: inline-block;
            background: url("${pageContext.request.contextPath}/statics/images/google.png") no-repeat 0 10px;
            background-size: 20px;
            margin-left: 5px;
            border-radius: 50%;
        }
        .zw-href7{
            width: 30px;
            height: 30px;
            display: inline-block;
            background: url("${pageContext.request.contextPath}/statics/images/facebook.png") no-repeat 0 10px;
            background-size: 20px;
            margin-left: 5px;
            border-radius: 50%;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="navbar-header">
        <div class="logo">
            <h1>
                <a id="logo-1" href="https://github.com/yws233/yws-Kuaib">Kuaib.COM</a>
                <a id="zhitu" style="margin-left: 260px;display: inline-block;border-radius: 50%;" href="https://www.processon.com/;jsessionid=6BEB28B1122B76C01D66E205213ACB83.jvm1"><img src="${pageContext.request.contextPath}/statics/images/zhitu.png" height="20px" width="60px" alt="在线制图" title="可绘制任何形式流程图"></a>
                <a class="zw-href2" href="https://github.com/yws233"></a>
                <a class="zw-href3" href="https://wx.qq.com/"></a>
                <a class="zw-href4" href="https://mail.qq.com/"></a>
                <a class="zw-href5" href="https://twitter.com/"></a>
                <a class="zw-href6" href="https://www.google.com.eg/"></a>
                <a class="zw-href7" href="https://www.facebook.com/"></a>
                <a id="exchange-file" style="font-size: 15px" href="https://app.xunjiepdf.com/">格式转换</a>
                <span  class="span-head" style="margin-left: 80px;">Hello!&nbsp;&nbsp;<span id="loginuser" style="font-weight: bold;font-size: 16px;">${userSession.userCode}</span>&nbsp;|&nbsp;${date}</span>
                <input type="hidden" value="${userPWD.userPassword}"><%--放一个隐藏域，存放用户密码--%>
                <a id="loginout-a" href="${pageContext.request.contextPath}/sys/user/loginout.html"><button type="button" class="loginout">注销</button></a>
            </h1>
        </div>
    </div>

    <%--<div>
        &lt;%&ndash;wangEditor编辑区域&ndash;%&gt;
        <form action="${pageContext.request.contextPath}/sys/user/savetext.html?uid=${userSession.uid}" method="post" enctype="multipart/form-data">
            <div  id="editor" style="background: white">
                <p>欢迎使用</p>
            </div>
            <button id="btn1" style="font-weight:bold;color:white;border:none;background: #4B63BB;height: 40px;width: 120px;margin-top: 10px">获取html</button>
            <button id="btn2" style="font-weight:bold;color:white;border:none;background: #4B63BB;height: 40px;width: 120px;margin-top: 10px">获取text</button>
            <button id="btn4" style="font-weight:bold;color:white;border:none;background: #4B63BB;height: 40px;width: 120px;margin-top: 10px;margin-bottom: 10px">下载</button>
            <input type="hidden" name="wangtext" id="wangtext" value="">
            <input type="submit" style="font-weight:bold;color:white;border:none;background: #4B63BB;height: 40px;width: 120px;margin-top: 10px" id="btn3" value="保存">
        </form>

        <!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/statics/wangEditor/release/wangEditor.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/statics/wangEditor/wangEditor-fullscreen-plugin.js"></script>
        <script type="text/javascript">
            var E = window.wangEditor
            var editor = new E('#editor')
            // 或者 var editor = new E( document.getElementById('editor') )

            editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片

            editor.customConfig.linkImgCheck = function (src) {
                console.log(src) // 图片的链接

                return true // 返回 true 表示校验成功
                // return '验证失败' // 返回字符串，即校验失败的提示信息
            }
            editor.create()
            E.fullscreen.init('#editor');

            document.getElementById('btn1').addEventListener('click', function () {
                // 读取 html
                alert(editor.txt.html())
            }, false)

            document.getElementById('btn2').addEventListener('click', function () {
                // 读取 text
                alert(editor.txt.text())
            }, false)
            document.getElementById('btn3').addEventListener('click', function () {
                // 读取 text
                $("#wangtext").val(editor.txt.text())
            }, false)
        </script>
    </div>--%>

    <div class="btn-head">
        <ul class="ul-head">
            <li>参考部分</li>
            <li>主编辑区</li>
            <li>历史记录</li>
        </ul>
    </div>

    <div class="context">

        <div class="left" id="left" style="height: 480px;width: 395px;word-break: break-all"><%--contentEditable="true" 设置div为可编辑--%>
            <%--<span id="pic-show-indiv">${filepath}</span>--%>
            <%--<input id="txt" type="hidden" value="${txt}">--%>
            <form method="post" name="form1" action="" onSubmit="return chazhao();">
                <img id="upload-img"  src="${pageContext.request.contextPath}/statics/images/upload.png" title="文件上传区域">
                <br>
                <span style="font-weight:bold;color: #4B63BB;font-size: 14px">${upcontext}<span style="color: red;font-size: 14px">${tip}</span></span><br>
                <%--循环遍历输出后台获取内容--%>
                <ul class="wordclick">
                    <li id="textli">
                        <c:forEach var="txtline" items="${txt}" varStatus="status">
                            <c:out value="${txtline}"></c:out>
                        </c:forEach>
                    </li>
                    <%--if word返回文本--%>
                    <li>
                        ${rword}
                    </li>
                </ul><%--显示上传的文本--%>
                <span style="color: red;z-index: 2">${uploadFileError}${clear}</span><%--文件格式有误/删除提示--%>
                <span style="color: #4B63BB;font-size: 10px;font-weight: bold">${upath}</span><a style="font-size: 10px" name="${filename}" href="${pageContext.request.contextPath}/sys/user/download.html?filename=${filename}">${filename}&nbsp;&nbsp;<span style="color: #4B63BB;font-size: 10px;font-weight: bold">${filecount}</span></a>
            </form>
        </div>

        <%--富文本显示--%>
        <div class="middle" style="background: white;margin-top: 10px;height: 480px;box-shadow:  1px 2px 2px #C1C1C1;">

            <form action="${pageContext.request.contextPath}/sys/user/savetext.html?uid=${userSession.uid}" method="post" enctype="multipart/form-data">
                 <textarea id="txt-more" class="xheditor-mfull" rows="17" cols="90" style="margin: 0px;height: 395px;width: 100%;box-shadow: none" name="txt-more">
                     ${wangtext}<%--返回文本信息--%>
                 </textarea>
                <textarea id="status" style="height: 25px" name="status" onkeydown='countChar("status","counter");' onkeyup='countChar("status","counter");'>粘贴至此处可实时计数</textarea>
                <span class="count-span">Count：<span id="counter">10000000</span>/10000000字</span>
                <span class="already">已写:&nbsp;&nbsp;</span><span id="write"></span>
                <input type="hidden" name="wangtext" id="wangtext" value="">
                <input type="submit" style="font-weight:bold;border:none;height: 23px;width: 45px;margin-left:3em;border-radius: 5px" id="btn3" value="保存">
                <%--保存提示信息--%><br>
                <span class="savesucss">${savesucss}</span><%--保存成功--%>
                <span class="savefail">${savefail}</span><%--保存失败--%>
                <script type="text/javascript">
                    document.getElementById('btn3').addEventListener('click', function () {
                        // 读取 text
                        $("#wangtext").val($('#txt-more').val())
                    }, false)
                </script>
            </form>
            <%--<script type="text/javascript">
                document.getElementById('btn3').addEventListener('click', function () {
                    // 读取 text
                    $("#wangtext").val($('#txt-more').val())
                }, false)
            </script>

            <textarea id="status" name="status" onkeydown='countChar("status","counter");' onkeyup='countChar("status","counter");'>实时计数编辑区域...${wangtext}</textarea>

            <span class="count-span">Count：<span id="counter">10000000</span>/10000000字</span>
            <span class="already">已写:&nbsp;&nbsp;</span><span id="write"></span>--%>
        </div>

        <div class="right">
                <textarea id="right" style="height: 480px;width: 405px;margin-bottom: 4px"></textarea>
            <span style="color: #4B63BB;font-weight: bold;font-size: 14px;">重复率为(3以内为高度相似):</span>&nbsp;&nbsp;<span style="color: red;font-size: 14px;font-weight: bold;">${reptxt1}</span><%--放置重复率提示--%>
        </div>

        <!--分享至各个社交软件-->
        <div class="btn-ctrl">
            <%--删除上传文本--%>
            <%--<a href="${pageContext.request.contextPath}/sys/user/deleteupics.html"></a>--%>
            <button id="clear-upload" onclick="clearTxt()" style="margin-left: 12px">删除</button>
            <form id="file-form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/sys/user/addpic.html" method="post">
                <%--for 和 id一致，以此自定义file控件--%>
                <label for="upics">
                    <input id="upics" type="file" style="display: none" name="upicss"/>
                    <span id="btn-1">上传</span>
                </label>
                <input  class="file-save" onclick="txtinput()" type="submit" value="保存文件">
            </form>
                <a id="pdf" href="${pageContext.request.contextPath}/sys/user/pdfdo.do?filename=${filename}">pdf文件</a>
            <%--<button id="btn-showfile" id="zw-href1">--%><a class="download-file" id="download-files" href="${pageContext.request.contextPath}/sys/user/download.html?filename=${filename}">下载文件</a><%--</button>--%>
            <button id="btn-2" type="button" onclick="{location.href='${pageContext.request.contextPath}/sys/user/exchange.html'}"><%--<a id="exhref" href="${pageContext.request.contextPath}/sys/user/exchange.html">--%>信息系统<%--</a>--%></button>
            <button id="clear"  type="button" onclick="clear_text()">清除</button>
            <button id="btn-4" type="button" onclick="countwritenum()">字数统计</button>
                <%--<button id="circle" &lt;%&ndash;onclick="circlebtn()"&ndash;%&gt;>--%><a id="rep-a" href="${pageContext.request.contextPath}/sys/user/rep.html">查重</a><%--</button>--%> <%--查重--%>
            <button id="btn-3" type="button">对比</button>
            <a class="zw-href1" href="http://www.cnki.net/"><img src="${pageContext.request.contextPath}/statics/images/zhiwang.png" height="30px" width="30px" alt="知网查询" title="知网查询"></a>
            <a class="zw-href1" href="https://zh.wikipedia.org/zh-hans/Wiki"><img src="${pageContext.request.contextPath}/statics/images/wiki.png" height="30px" width="30px" alt="维基百科" title="维基百科"></a>
            <a class="zw-href1" href="https://www.baidu.com"><img src="${pageContext.request.contextPath}/statics/images/baidu.png" height="30px" width="30px" alt="百度" title="百度"></a>
            <a class="zw-href1" href="http://www.ccnu.edu.cn/"><img src="${pageContext.request.contextPath}/statics/images/ccnu.png" height="30px" width="30px" alt="华师官网" title="华师官网"></a>
            <a class="zw-href1" href="http://2017.ccrs.org.cn/"><img src="${pageContext.request.contextPath}/statics/images/ccrs.png" height="30px" width="30px" alt="中农数据库" title="中农数据库"></a>
            <a class="zw-href1" href="http://www.proquest.com/"><img src="${pageContext.request.contextPath}/statics/images/proquest.png" height="30px" width="30px" alt="ProQues论文库" title="ProQues论文库"></a>
        </div>

        <%--网站访问次数统计--%>
        <%
            Integer hitsCount =
                    (Integer)application.getAttribute("hitCounter");
            if( hitsCount ==null || hitsCount == 0 ){
       /* 第一次访问 */
                hitsCount = 1;
            }else{
       /* 返回访问值 */
                hitsCount += 1;
            }
            application.setAttribute("hitCounter", hitsCount);
        %>

    </div>

</div>

<div style="margin-top: 40em">
    <%--二维码扫描--%>
    <h1 style="color: #B2BBDE;text-align: center">您可关注下方二维码获取系统详细信息，也可以给予作者支持，不断完善系统，谢谢！</h1>
    <ul style="display: inline-block;margin-left: 4em">
        <li style="display: inline-block"><img width="300" height="300" src="${pageContext.request.contextPath}/statics/images/1523320014.png"></li>
        <li style="display: inline-block"><img width="300" height="300" src="${pageContext.request.contextPath}/statics/images/1523320716.png"></li>
        <li style="display: inline-block"><img title="支付宝支付" width="300" height="300" src="${pageContext.request.contextPath}/statics/images/alplay.png"></li>
        <li style="display: inline-block"><img title="微信支付" width="300" height="300" src="${pageContext.request.contextPath}/statics/images/weixin.png"></li>
    </ul>
</div>

<%--底部--%>
<div class="bottom">
    <p>Copyright © 2017-2018&nbsp;&nbsp;All Rights Reserved<a target="_blank" href="https://github.com/yws233/yws-Kuaib">&nbsp;&nbsp;yws&nbsp;快编系统</a>&nbsp;|&nbsp;<span style="color: #666666">访问量：<%= hitsCount%></span></p>
</div>
</body>

<%--condirm提示框--%>
<%--<script src="/statics/js/jquery-confirm.min.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/xhEditor/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/xhEditor/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/editer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/fileshowtxt.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery-confirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/fileshow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/countsum.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/same_time.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/click_file_edit.js"></script>
<script type="text/javascript">
    $('#txt-more').xheditor({skin:'nostyle'});/*定义不同皮肤，白色*/
    /*$('#txt-more').xheditor({skin:'o2007blue'});*/ //蓝色
    /*$('#txt-more').xheditor({skin:'o2007silver'});*/ //银白色
</script>
</html>