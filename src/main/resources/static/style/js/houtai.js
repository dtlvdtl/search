// 按钮
// $(function(){
// 	$("#submit").click(function(){
// 		var flag = confirm("请再次确认提交");
// 	})
// })
$(function() {
    $("#loginOut").on("click",function () {
        $.ajax({
            method: "GET",
            url: "/user/loginOut"
        }).done(function(data){
            if(data == true){
                window.location.replace("/static/login.html");
            }
        })


    })
})