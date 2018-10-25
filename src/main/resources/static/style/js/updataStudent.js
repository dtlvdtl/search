
$(function(){
    // 全选
    $('.result').on('click','.select_all',function(){
        if($(this).prop("checked")){
            $(":checkbox").prop("checked",true);
        }else{
            $(":checkbox").prop("checked",false);
        }
    })
    $('.result').on('click','#uploadData',function () {
        var checkID = [];//定义一个空数组
        var txts = [];
        $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            checkID[i]=$(this).val();
        });
        if(checkID.length > 0){

            for(var i=0; i<checkID.length;i++){

                var txt = {
                    "id":$("#v"+checkID[i]+0).text(),
                    "name":$("#v"+checkID[i]+1).val(),
                    "faculty":$("#v"+checkID[i]+2).val(),
                    "sumYgs":parseFloat($("#v"+checkID[i]+3).text())
                };
                txts.push(txt);
            }
            $.ajax({
                method: "POST",
                url: "/user/studentsaves",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(txts)
            }).done(function( msg ) {
                // console.log(txts);
            })
        }else{
            alert("请选择上传的数据");

        }

    })

	$('#submit').click(function(){
        $(".result").html('');
		if($(".selectpicker").val() == 1){
		    if($("#txt").val()>2100000000){
                $(".result").html("<h4 class='text-center'>学号位数错误</h4>");
            }
		    else{
                $.ajax({
                    method: "GET",
                    url: "/user/byid",
                    data: { id: $("#txt").val()}
                }).done(function( msg ) {
                    if(msg.length > 0){
                        var txt='<table><caption>查询结果：</caption><thead><tr class="vertical-middle"><th><input type="checkbox" class="select_all" ></input></th><th>学号</th><th>姓名</th><th>院别</th><th>总义工时</th></tr></thead><tbody>';
                        for (var i=0; i < msg.length ; i++){
                            txt=txt+'<tr><th><input type="checkbox" name="check" value="'+i+'"></input></th><th id="v'+i+'0'+'">'+msg[i].id+'</th><th><input  id="v'+i+'1'+'" type="text" class="form-control" value="'+msg[i].name+'"></th><th><input  id="v'+i+'2'+'" type="text" class="form-control" value="'+msg[i].faculty+'"></th><th  id="v'+i+'3'+'">'+msg[i].sumYgs+'</th></tr>';
                        }
                        txt=txt+'</tbody></table>';
                        txt=txt+'<div class="form-group"><div class="col-sm-offset-2 col-xs-offset-1 col-sm-2 col-xs-2"><button type="button" class="btn btn-default" id="uploadData">上传数据</button></div></div>';
                        $(".result").append(txt);
                        $(".result").addClass('table-responsive');
                        $(".result>table").addClass('table');
                    }else{
                        $(".result").html("<h4 class='text-center'>查询结果：查无此人</h4>");
                    }

                });
            }

		}else if($(".selectpicker").val()==2){
            $.ajax({
                method: "GET",
                url: "/user/byname",
                data: { name: $("#txt").val()}
            }).done(function( msg ) {

                if(msg.length > 0){
                    var txt='<table><caption>查询结果：</caption><thead><tr class="vertical-middle"><th><input type="checkbox" class="select_all" ></input></th><th>学号</th><th>姓名</th><th>院别</th><th>总义工时</th></tr></thead><tbody>';
                    for (var i=0; i < msg.length ; i++){
                        txt=txt+'<tr><th><input type="checkbox" name="check" value="'+i+'"></input></th><th id="v'+i+'0'+'">'+msg[i].id+'</th><th><input  id="v'+i+'1'+'" type="text" class="form-control" value="'+msg[i].name+'"></th><th><input  id="v'+i+'2'+'" type="text" class="form-control" value="'+msg[i].faculty+'"></th><th  id="v'+i+'3'+'">'+msg[i].sumYgs+'</th></tr>';
                    }
                    txt=txt+'</tbody></table>';
                    txt=txt+'<div class="form-group"><div class="col-sm-offset-2 col-xs-offset-1 col-sm-2 col-xs-2"><button type="button" class="btn btn-default" id="uploadData">上传数据</button></div></div>';
                    $(".result").append(txt);
                    $(".result").addClass('table-responsive');
                    $(".result>table").addClass('table');
                }else{
                    $(".result").html("<h4 class='text-center'>查询结果：查无此人</h4>");
                }

            });
		}
		// $.ajax({
		// 	method: "POST",
		// 	url: "some.php",
		// 	data: { name: "John",
		// 	 		location: "Boston" }
		// }).done(function( msg ) {
		// 	alert( "Data Saved: " + msg );
		// });
	})

})