
$(function(){
    // 全选
    $('.result').on('click','.select_all',function(){
        if($(this).prop("checked")){
            $(":checkbox").prop("checked",true);
        }else{
            $(":checkbox").prop("checked",false);
        }
    })
    // 获取选择框的值,上传数据
    $('.result').on('click','#uploadData',function () {
        var checkID = [];//定义一个空数组
        var txts = [];
        $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            checkID[i]=$(this).val();
        });
        if(checkID.length > 0){
            for(var i=0; i<checkID.length;i++){
                var txt = {
                    "acid":parseInt($("#v"+checkID[i]+0).text()),
                    "acName":$("#v"+checkID[i]+1).text(),
                    "time":$("#v"+checkID[i]+2).text(),
                    "ygs":parseFloat($("#v"+checkID[i]+3).val())
                };
                txts.push(txt);
            }
            $.ajax({
                method: "POST",
                url: "/user/activitysaves",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(txts)
            }).done(function( msg ) {
                if(msg === true){
                    alert("上传成功");
                }
            })
        }else{
            alert("请选择上传的数据");

        }

    })

    // 获取选择框的值,删除数据
    $('.result').on('click','#deleteData',function () {
        var checkID = [];//定义一个空数组
        var txts = [];
        $("input[name='check']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            checkID[i]=$(this).val();
        });
        if(checkID.length > 0){
            for(var i=0; i<checkID.length;i++){

                var txt = {
                    "acid":parseInt($("#v"+checkID[i]+0).text()),
                    "acName":$("#v"+checkID[i]+1).text(),
                    "time":$("#v"+checkID[i]+2).text(),
                    "ygs":parseFloat($("#v"+checkID[i]+3).val())
                };
                txts.push(txt);
            }
            $.ajax({
                method: "POST",
                url: "/user/deleteac",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(txts)
            }).done(function( msg ) {
                if(msg === true){
                    alert("删除成功");
                }
            })
        }else{
            alert("请选择上传的数据");

        }

    })
    // 查询活动信息
	$('#submit').click(function(){
        $(".result").html('');
        //学号
		if($(".selectpicker").val() == 1){
			$.ajax({
				method: "GET",
				url: "/user/byactid",
				data: { acid: $("#txt").val()}
			}).done(function( msg ) {

				if(msg.length > 0){
                    var txt='<table><caption>查询结果：</caption><thead><tr class="vertical-middle"><th><input type="checkbox" class="select_all" ></input></th><th>学号</th><th>活动名称</th><th>活动时间</th><th>活动义工时</th></tr></thead><tbody>';
                    for (var i=0; i < msg.length ; i++){
                        txt=txt+'<tr><th><input type="checkbox" name="check" value="'+i+'"></input></th><th id="v'+i+'0'+'">'+msg[i].acid+'</th><th id="v'+i+'1'+'">'+msg[i].acName+'</th><th  id="v'+i+'2'+'">'+msg[i].time+'</th><th><input  id="v'+i+'3'+'" type="text" class="form-control" value="'+msg[i].ygs+'"></th></tr>';
                    }
                    txt=txt+'</tbody></table>';
                    txt=txt+'<div class="form-group"><div class="col-sm-offset-2 col-xs-offset-1 col-sm-2 col-xs-2"><button type="button" class="btn btn-default" id="uploadData">上传数据</button></div><div class="col-sm-offset-3 col-xs-offset-2 col-sm-2 col-xs-2"><button type="reset" class="btn btn-default" id="deleteData">删除</button></div></div>';
                    $(".result").append(txt);
                    $(".result").addClass('table-responsive');
                    $(".result>table").addClass('table');
				}else{
                    $(".result").html("<h4 class='text-center'>查询结果：查无此人</h4>");
				}

			});
		}
		//活动名称
		else if($(".selectpicker").val()==2){
            $.ajax({
                method: "GET",
                url: "/user/byactname",
                data: { acName: $("#txt").val()}
            }).done(function( msg ) {
                if(msg.length > 0){
                    var txt='<table><caption>查询结果：</caption><thead><tr class="vertical-middle"><th><input type="checkbox" class="select_all" ></input></th><th>学号</th><th>活动名称</th><th>活动时间</th><th>活动义工时</th></tr></thead><tbody>';
                    for (var i=0; i < msg.length ; i++){
                        txt=txt+'<tr><th><input type="checkbox" name="check" value="'+i+'"></input></th><th id="v'+i+'0'+'">'+msg[i].acid+'</th><th id="v'+i+'1'+'">'+msg[i].acName+'</th><th  id="v'+i+'2'+'">'+msg[i].time+'</th><th><input  id="v'+i+'3'+'" type="text" class="form-control" value="'+msg[i].ygs+'"></th></tr>';
                    }
                    txt=txt+'</tbody></table>';
                    txt=txt+'<div class="form-group"><div class="col-sm-offset-2 col-xs-offset-1 col-sm-2 col-xs-2"><button type="button" class="btn btn-default" id="uploadData">上传数据</button></div><div class="col-sm-offset-3 col-xs-offset-2 col-sm-2 col-xs-2"><button type="reset" class="btn btn-default" id="deleteData">删除</button></div></div>';
                    $(".result").append(txt);
                    $(".result").addClass('table-responsive');
                    $(".result>table").addClass('table');
                }else{
                    $(".result").html("<h4 class='text-center'>查询结果：查无活动</h4>");
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