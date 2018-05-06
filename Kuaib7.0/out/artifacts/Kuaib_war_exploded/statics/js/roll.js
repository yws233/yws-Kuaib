var namelist=['库里','麦迪','詹姆斯','科比','迈克','利拉德','维斯布鲁克','莱昂纳德','沃尔','欧文'];
var mytime=null;
var zhi = $(".wby").val();
$(".save").click(function(){
    namelist = namelist.push(zhi)

})
function chou(){
    var length= namelist.length;
    var num = parseInt(Math.random()*length);
    $(".show").html(namelist[num])
    mytime=setTimeout("chou()",1)
}