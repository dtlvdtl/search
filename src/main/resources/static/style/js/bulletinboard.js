$(function(){
    $("#submit").on("click",function() {
        $.ajax({
            url:"/other/setboard",
            method:"GET",
            data: {
                content: $("#board").val()
            }
        }).done(function(msg) {
            alert("提交成功");
        })
    })
})