$(function () {
    $("#submit").on("click",function() {
        $.ajax({
            url:"/user/addpower",
            method:"POST",
            data: {
                user: $("#id").val(),
                password: $.md5($("#password").val()),
                position: $("#work").val(),
                power: parseInt("0")
            }
        }).done(function(msg) {
            alert("提交成功");
        })

    })
    $.ajax({
        url: '/user/power',
        method: "GET"
    }).done(function(msg) {
        if(msg == 0){
            $(".result").html("");
            $(".result").append("<h2 class='text-center'>您的权限不足</h2>");
        }else if(msg == 1){
            getuser();
        }
    });
})