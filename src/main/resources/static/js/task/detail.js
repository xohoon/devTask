var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function taskSupport(value, id) {
    if(value == "supported") {
        alert("이미 지원이 완료되었습니다");
        return false;
    }
    if(confirm(value+" 파트에 지원하시겠습니까?")) {
        $.ajax({
            type : 'POST',
            url : '/task/support',
            dataType : 'JSON',
            async: false,
            data : {
                id : id
            },
            beforeSend : function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success : function(result, data) {
                location.reload();
            },
            error : function(result) {
                console.log('ERROR');
            }
        });
    }else {
        return false;
    }
}