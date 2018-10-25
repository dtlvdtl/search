$(function(){
    $.ajax({
        method: "POST",
        url: "/other/getboard",
        data: {
            boardname: "公告栏"
        },
        success : function(t) {
            $("#board").text(t.content);
        }
    })
    //     .done(function (content) {
    //     if(xhr.readystate == 200)
    //     $("#board").text(content);
    // })
	$('#search').on('click',function(){
		if( parseInt($('#id').val()) / 1000000000 < 10 && parseInt($('#id').val()) < 2100000000){
            $.ajax({
                method: "GET",
                url: "/user/bynameid",
                data: {
                    id: parseInt($('#id').val()),
                    name: $('#name').val()
                }
            }).done(function (data) {
                $(".result").html('');
                if (data == "") {
                    alert("查无此人");
                } else {

                    var txt = '<table class="col-xs-12 col-sm-10 col-md-10 col-lg-10  center table"><caption>姓名：' + data.name + '  学号：' + data.id + '  总义工时：' + data.sumYgs + '</caption><thead><tr class="vertical-middle"><th>活动名称</th><th>活动时间</th><th>服务时长</th></tr></thead><tbody>';
                    $.ajax({
                        method: "GET",
                        url: "/user/byactid",
                        data: {
                            acid: parseInt($('#id').val()),
                        }
                    }).done(function (acdata) {
                        for (var i = 0; i < acdata.length; i++) {
                            txt = txt + '<tr><th>' + acdata[i].acName + '</th><th>' + acdata[i].time + '</th><th>' + acdata[i].ygs + '</th></tr>';
                        }
                        txt = txt + '</tbody></table>';
                        $(".result").append(txt);

                    })

                }
            });
        }else{
            alert("查无此人");
        }
	})
		
	
	

	// 验证输入合法性
	$(function(){
		 $('.loginForm').validate({
			rules:{
				username:{
					required: true,//输入不允许为空
					rangelength:[2,10],//长度3-16位
				},
				id:{
					required: true,//输入不允许为空
					digits:true,//必须输入整数。
					rangelength:[10,10],//长度5-16位
				},
				
			},
			messages:{
				username:{
					required: '姓名不允许为空',//输入不允许为空
					rangelength:'姓名长度为3-16位',//长度3-16位
				},
				id:{
					required: '学号不允许为空',//输入不允许为空
					digits:'学号为数字',
					rangelength:'学号长度为10位',//长度3-16位
				},
				
			}
		 })
	})
})