$(function () {
    $("#submit").on("click",function() {
        $("#id").val()
        $.ajax({
            url:"/user/studentsave",
            method:"GET",
            data: {
                id: $("#id").val(),
                name: $("#name").val(),
                faculty: $("#faculty").val(),
            }
        }).done(function(msg) {
            $.ajax({
                url:"/user/activitysave",
                method:"POST",
                data: {
                    acid: $("#id").val(),
                    acName: $("#actname").val(),
                    time: $("#time").val(),
                    ygs: parseFloat($("#ygssize").val()),
                }
            }).done(function(data) {
                alert("提交成功");
            })
        })

    })
})