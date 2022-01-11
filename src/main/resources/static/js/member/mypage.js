var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function memberModify() {
    var modifyKey = $("#modifyKey").val();
    if(modifyKey == "close") {
        $("#username").attr("readonly", false);
        $("#email").attr("readonly", false);
        $("#age").attr("readonly", false);
        $("#password").attr("readonly", false);
        $("#modifyKey").val("open");
        $("#modifyBtn").text("수정완료");
        $("#modifyBtn").removeClass("btn-success");
        $("#modifyBtn").addClass("btn-outline-danger");
    }else if (modifyKey == "open") {
        var username = $("#username").val();
        var email = $("#email").val();
        var age = $("#age").val();
        var id = $("#id").val();
        var password = $("#password").val();
        if(!username || !email || !age || !id || !password) {
            alert("정보를 입력해주세요.");
            return false;
        }else {
            userUpdate(username, email, age, id, password);
        }
    }
}

function userUpdate(username, email, age, id, password) {
    $.ajax({
        type: 'POST',
        url: '/modify',
        dataType: 'JSON',
        data: {
            username : username,
            email : email,
            age : age,
            id : id,
            password : password
        },
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function (result, data) {
            if (result.chk == "success") {
                alert("수정 완료되었습니다.");
                location.reload();
            } else if (result.chk == "fail") {
                alert("수정에 문제가 발생했습니다. 다시 시도해주세요.");
                return false;
            }
        },
        error: function (result) {
            console.log('ERROR');
        }
    });
}