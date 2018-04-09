/*
* 导师交换注册实现同名异步判断
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
var exname = null;
$(function () {
    exname = $("#exname");
    path = $("#path").val();
    //提示信息
    exname.next().html("");
    exname.bind("blur",function(){
        //ajax后台验证--exname是否已存在
        $.ajax({
            type:"GET",//请求类型
            url:path + "/exnameExist.html",//请求的url,path为获取的页面真实路径
            data:{exname:exname.val()},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                if(data == "exist"){//账号已存在，错误提示
                    validateTip(exname.next(),{"color":"red"}, "* 该昵称已存在",false);
                }else if (data == "nullcode"){
                    validateTip(exname.next(),{"color":"red"}, "* 昵称不能为空",false);
                } else{//账号可用，正确提示
                    validateTip(exname.next(),{"color":"green"},"* 该昵称可以使用",true);
                }
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                validateTip(exname.next(),{"color":"red"},"* 您访问的页面不存在",false);
            }
        });
    }).bind("focus",function(){
        //显示友情提示
        validateTip(exname.next(),{"color":"#666666"},"* 用户编码是您的名称可不实名",false);
    }).focus();

});

