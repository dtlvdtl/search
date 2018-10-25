$(function(){
    function getuser() {
        $.ajax({
            url:"/user/getpower",
            method:"GET"
        }).done(function(data) {
            if(data.length > 1){
                var txt='<table class="col-sm-5 col-xs-12"><caption>账号列表：</caption><thead><tr class="vertical-middle"><th><input type="checkbox" class="select_all" ></input></th><th>账号</th><th>职务描述</th></tr></thead><tbody>';
                for (var i=0; i < data.length ; i++){
                    if(data[i].power == 0){
                        txt=txt+'<tr><th><input type="checkbox" name="check" value="'+i+'"></input></th><th id="v'+i+'0'+'">'+data[i].user+'</th><th id="v'+i+'1'+'">'+data[i].position+'</th></tr>';
                    }
                }
                txt=txt+'</tbody></table>';
                txt=txt+'<div class="form-group"><div class="col-sm-offset-5 col-xs-offset-2 col-sm-2 col-xs-4"><button type="reset" class="btn btn-default" id="deleteData">删除</button></div></div>';
                $(".result").append(txt);
                $(".result").addClass('table-responsive');
                $(".result>table").addClass('table');
            }else{
                $(".result").html("<h4 class='text-center'>账号列表：无普通管理员</h4>");
            }
        })
    }
    $.ajax({
        url: '/user/power',
        method: "GET"
    }).done(function(msg) {
        if(msg == 0){
            $(".result").append("<h2 class='text-center'>您的权限不足</h2>");
        }else if(msg == 1){
            getuser();
        }
    });
    // 全选
    $('.result').on('click','.select_all',function(){
        if($(this).prop("checked")){
            $(":checkbox").prop("checked",true);
        }else{
            $(":checkbox").prop("checked",false);
        }
    })
    $('.result').on('click','#deleteData',function () {
        var checkID = [];//定义一个空数组
        var txts = new Array();
        $("input[name='check']:checked").each(function (i) {//把所有被选中的复选框的值存入数组
            checkID[i] = $(this).val();
        });
        if (checkID.length > 0) {
            for (var i = 0; i < checkID.length; i++) {
                console.log($("#v" + checkID[i] + 0).text());
                console.log($("#v" + checkID[i] + 1).text());
                txts[i] = $("#v" + checkID[i] + 0).text();

            }
            $.ajax({
                method: "POST",
                url: "/user/deleteuser",
                data: {
                    user:txts
                },
            }).done(function (msg) {
                if (msg === true) {
                    alert("删除成功");
                    getuser();
                }
            })

        } else {
            alert("请选择上传的数据");
        }
    })
})