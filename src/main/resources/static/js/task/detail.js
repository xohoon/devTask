var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function taskSupport(value, id) {
    if(confirm(value+" 파트에 지원하시겠습니까?")) {
        console.log("DATA::"+value+"::"+typeof (id));
        $.ajax({
            type : 'POST',
            url : '/task/support',
            dataType : 'JSON',
            async: false,
            data : {
                td_id : id
            },
            beforeSend : function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success : function(result, data) {
                location.href="/task/list";
            },
            error : function(result) {
                console.log('ERROR');
            }
        });
    }else {
        return false;
    }
}