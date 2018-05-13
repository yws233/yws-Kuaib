/*$(function(){

    var $textarea = $('#right'),
        $editor = $('#txt-more'),
        $btnText = $('#btn-3');
   /!* $btnHtml.click(function(){
        $textarea.show();
        $textarea.val($editor.html() );

    });*!/
    $btnText.click(function(){
        $textarea.val( $editor.text());
    });
});*/
/*
* 清除编辑区域内的内容
* */
function clear_text() {
    /* var msg = "*确定要删除吗？";
     if (confirm(msg) == true){
         document.getElementById("status").value = "";
         //已写字数清0
         document.getElementById("write").innerText = "0";
     }*/
    $.confirm({
        title: 'Tip',
        content: '确定要删除吗？',
        buttons: {
            confirm: function () {
                document.getElementById("status").value= "";
                //已写字数清0
                document.getElementById("write").innerText = "0";
                $.alert('删除成功!')
            },
            cancel: function () {
                $.alert('取消!');
            },
        }
    });

}
/*
* 统计字数
* */
function countwritenum() {
    var len=document.getElementById("status").value.length;
    document.getElementById("write").innerText = len;
}

/*
* 实时显示字数
* */
function countChar(textareaName,spanName)
{
    document.getElementById(spanName).innerHTML = 10000000 - document.getElementById(textareaName).innerText.length;
}

/*
* 点击查重按钮
* */
function circlebtn() {
    alert("sorry,查重还在测试中...")
}
