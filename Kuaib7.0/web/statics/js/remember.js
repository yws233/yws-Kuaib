/**
 *
 * 用户点击记住密码，储存cookie
 */

/*
*添加cookie
* */
function setCookie ( name, value, expdays )
{
    var expdate = new Date();
    //设置Cookie过期日期
    expdate.setDate(expdate.getDate() + expdays) ;
    //添加Cookie
    document.cookie = name + "=" + escape(value) + ";expires=" + expdate.toUTCString();
}
/*
* 获取Cookie
*
* */
function getCookie ( name )
{
    //获取name在Cookie中起止位置
    var start = document.cookie.indexOf(name+"=") ;

    if ( start != -1 )
    {
        start = start + name.length + 1 ;
        //获取value的终止位置
        var end = document.cookie.indexOf(";", start) ;
        if ( end == -1 )
            end = document.cookie.length ;
        //截获cookie的value值,并返回
        return unescape(document.cookie.substring(start,end)) ;
    }
    return "" ;
}
/*
* 删除Cookie
*
* */
function delCookie ( name )
{
    setCookie ( name, "", -1 ) ;
}


function check ()
{
    //获取表单输入:用户名,密码,是否保存密码
    var username = document.getElementById("userCode").value.trim() ;
    var password = document.getElementById("userPassword").value.trim() ;
    var isRmbPwd = document.getElementById("remember").checked ;

    //判断用户名,密码是否为空(全空格也算空)
    if ( username.length != 0 && password.length != 0 )
    {
        //若复选框勾选,则添加Cookie,记录密码
        if ( isRmbPwd == true )
        {
            setCookie ( "This is username", username, 7 ) ;
            setCookie ( username, password, 7 ) ;
        }
        //否则清除Cookie
        else
        {
            delCookie ( "This is username" ) ;
            delCookie ( username ) ;
        }
        return true ;
    }
    //非法输入提示
    else
    {
        alert('请输入必填字段!!!');
        return false ;
    }


}
window.onload = function ()
{
    //从Cookie获取到用户名
    var username = getCookie("This is username") ;
    //如果用户名为空,则给表单元素赋空值
    if ( username == "" )
    {
        document.getElementById("userCode").value="" ;
        document.getElementById("userPassword").value="" ;
        document.getElementById("remember").checked=false ;
    }
    //获取对应的密码,并把用户名,密码赋值给表单
    else
    {
        var password = getCookie(username) ;

        document.getElementById("userCode").value = username ;
        document.getElementById("userPassword").value = password ;
        document.getElementById("remember").checked = true ;
    }
}




