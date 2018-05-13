var verifyCode = new GVerify("v_container");

document.getElementById("code_input").onblur = function(){
    var res = verifyCode.validate(document.getElementById("code_input").value);
    if(res){
        document.getElementById("confirm-pic").value = "验证成功!";
        document.getElementById("confirm-pic").style.color = "green";
    }else{
        document.getElementById("confirm-pic").value = "验证失败!";
        document.getElementById("confirm-pic").style.color = "red";
    }
}