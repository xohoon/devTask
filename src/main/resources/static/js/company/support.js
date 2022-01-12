var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function taskConfirm(name, id) {
    var td_id = $("#td_id").val();
    var member_id = $("#member_id").val();
    if(confirm(name+"님에게 과제 진행 요청을 하시겠습니까?")) {
        $.ajax({
            type : 'POST',
            url : '/co/task/support/confirm',
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
                console.log(result);
            }
        });
    }else {
        return false;
    }
}