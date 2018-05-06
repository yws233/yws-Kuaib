/*
* 实现实时字数统计
*
* */
function countChar(textareaName,spanName)
{
    document.getElementById(spanName).innerHTML = 10000000 - document.getElementById(textareaName).value.length;
}


/*
function count() {
    var counts = document.getElementById("counter").innerText;
    document.getElementById("write").innerText = 10000000 - counts;
}*/
