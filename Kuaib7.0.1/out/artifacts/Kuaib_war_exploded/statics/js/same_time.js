$(function () {
    var $txt = $("#btn-3"),
        $middle = $("#status"),
        $right = $('#right');
    $txt.click(function(){
        $right.val(/*$middle.val()*/$('#txt-more').val());
        /*$right.append($middle.text()); //实现文本内容的追加*/
        $right.css("color","#4B63BB");
    });
});
/*
* 删除上传区域中的区域 
* */

function deleteupload() {
        var context = document.getElementById("text_show");
        if (context != null){
            context.innerHTML = "";
        }
        document.getElementById("clear-txt").value = "";
}

