$(function(){
    $("#submit").on("click",function () {
        $.ajax({
            method: "POST",
            url: "/user/login",
            data: { user: $("#username").val(),
                password: $.md5($("#password").val()) }
        }).done(function( msg ) {
            if(msg==true){
                window.location.replace("/static/houtai/admin.html");
            }
            else{
                alert("密码错误");
            }
        });
    })



})


// 验证输入合法性
$(function(){
    $('.loginForm').validate({
        rules:{
            username:{
                required: true,//输入不允许为空
                rangelength:[3,16],//长度3-16位
            },
            password:{
                required: true,//输入不允许为空
                rangelength:[6,12],//长度6-12位
            },
            captcha:{
                required: true,//输入不允许为空

            }

        },
        messages:{
            username:{
                required: '账号不允许为空',//输入不允许为空
                rangelength:'账号长度为3-16位',//长度3-16位
            },
            password:{
                required: '密码不允许为空',//输入不允许为空
                rangelength:'密码长度为长度6-12位',//长度3-16位
            },
            captcha:{
                required: '验证码不允许为空',//输入不允许为空

            }
        }
    })
})