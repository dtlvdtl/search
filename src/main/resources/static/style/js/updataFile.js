$(document).ready(function(){
    $("#upload").click(function(){
        $.ajax({
            url: '/upload',
            type: 'POST',
            cache: false,
            data:new FormData($('#uploadForm')[0]),//h5的DataForm对象
            dataType:"text",
            processData: false,
            contentType: false,

        }).done(function(data){
            alert(data);
        })
    })
})