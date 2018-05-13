/*
* 注册用户实现同名异步判断
*
* */
/*
 * 提示信息显示
 * element:显示提示信息的元素（font）
 * css：提示样式
 * tipString:提示信息
 * status：true/false --验证是否通过
 */

function validateTip(element,css,tipString,status){
        element.css(css);
        element.html(tipString);

        element.prev().attr("validateStatus",status);
}
var userCode = null;
var email = null;
var userPassword = null;
var ruserPassword = null;
var path = null;
var reg = null; //注册成功提示
$(function () {
    userCode = $("#userCode");
    userPassword = $("#userPassword");
    ruserPassword = $("#ruserPassword");
    email = $("#email");
    path = $("#path").val();
    reg = $('#regtrue').val();
    //提示信息
    userCode.next().html("");
    userPassword.next().html("");
    ruserPassword.next().html("");
    email.next().html("");

    userCode.bind("blur",function(){
        //ajax后台验证--userCode是否已存在
        $.ajax({
            type:"GET",//请求类型
            url:path + "/userCodeExist.html",//请求的url,path为获取的页面真实路径
            data:{userCode:userCode.val()},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                if(data == "exist"){//账号已存在，错误提示
                    validateTip(userCode.next(),{"color":"red"}, "* 该用户账号已存在",false);
                }else if (data == "nullcode"){
                    validateTip(userCode.next(),{"color":"red"}, "* 账户不能为空且长度不超过10",false);
                } else if (data == "toolen"){
                    validateTip(userCode.next(),{"color":"red"}, "* 注册账号长度不能超过10",false);
                } else{//账号可用，正确提示
                    validateTip(userCode.next(),{"color":"green"},"* 该账号可以使用",true);
                }
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                validateTip(userCode.next(),{"color":"red"},"* 您访问的页面不存在",false);
            }
        });
    }).bind("focus",function(){
        //显示友情提示
        validateTip(userCode.next(),{"color":"#666666"},"* 用户编码是您登录系统的账号",false);
    }).focus();

    email.bind("focus",function(){
        validateTip(email.next(),{"color":"#666666"},"* 请输入邮箱",false);
    }).bind("blur",function(){
        var patrn=/[a-z0-9-]{1,30}@[a-z0-9-]{1,65}.[a-z]{3}/;
        if(email.val().match(patrn)){
            validateTip(email.next(),{"color":"green"},true);
        }else{
            validateTip(email.next(),{"color":"red"},"* 您输入的邮箱格式不正确",false);
        }
    });

    userPassword.bind("focus",function(){
        validateTip(userPassword.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20",false);
    }).bind("blur",function(){
        if(userPassword.val() != null && userPassword.val().length > 6
            && userPassword.val().length < 20 ){
            validateTip(userPassword.next(),{"color":"green"},true);
        }else{
            validateTip(userPassword.next(),{"color":"red"},"* 密码输入不符合规范，请重新输入",false);
        }
    });

    ruserPassword.bind("focus",function(){
        validateTip(ruserPassword.next(),{"color":"#666666"},"* 请输入与上一致的密码",false);
    }).bind("blur",function(){
        if(ruserPassword.val() != null && ruserPassword.val().length > 6
            && ruserPassword.val().length < 20 && userPassword.val() == ruserPassword.val()){
            validateTip(ruserPassword.next(),{"color":"green"},true);
        }else{
            validateTip(ruserPassword.next(),{"color":"red"}," 两次密码输入不一致，请重新输入",false);
        }
    });

    /*//注册不成功提示
    if (reg != null && reg.equals("")){
        alert(reg);
    }*/

});
