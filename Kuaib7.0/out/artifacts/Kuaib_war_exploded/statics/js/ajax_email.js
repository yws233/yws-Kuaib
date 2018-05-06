/*
* 异步判断邮箱是否存在
* */
function validateTip(element,css,tipString,status){
    element.css(css);
    element.html(tipString);

    element.prev().attr("validateStatus",status);
}
var email = null;
var path = null;
$(function () {
    email = $("#email");
    path = $("#path").val();
    //提示信息
    email.next().html("");

    email.bind("blur",function(){
        //ajax后台验证--userCode是否已存在
        $.ajax({
            type:"GET",//请求类型
            url:path + "/userpwdexist.html",//请求的url,path为获取的页面真实路径
            data:{email:email.val()},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                if(data == "existpwd"){//账号已存在，错误提示
                    validateTip(email.next(),{"color":"red"}, " 该邮箱已存在",false);
                }else if (data == "nullpwd"){
                    validateTip(email.next(),{"color":"red"}, " 邮箱不能为空",false);
                } else{//账号可用，正确提示
                    validateTip(email.next(),{"color":"green"}," 该邮箱可以使用",true);
                }
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                validateTip(email.next(),{"color":"red"}," 您访问的页面不存在",false);
            }
        });
    }).bind("focus",function(){
        //显示友情提示
        validateTip(email.next(),{"color":"#666666"},"* 请输入符合格式的邮箱",false);
    }).focus();

});
