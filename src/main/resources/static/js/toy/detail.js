var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function toySupport(value, id) {
    var portfolio = portfolioCheck();
    if (portfolio == "success") {
        if (value == "supported") {
            alert("이미 지원이 완료되었습니다");
            return false;
        }
        if (confirm(value + " 파트에 지원하시겠습니까?")) {
            $.ajax({
                type: 'POST',
                url: '/toy/support',
                dataType: 'JSON',
                async: false,
                data: {
                    id: id
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function (result, data) {
                    location.reload();
                },
                error: function (result) {
                    console.log('ERROR');
                }
            });
        } else {
            return false;
        }
    }else if(portfolio == "fail") {
        var cf = confirm("포트폴리오를 등록하셔야 지원 가능합니다.\n포트폴리오 등록 페이지로 이동하시겠습니까?");
        if(cf == true) {
            location.href = "/dev/detail";
            return false;
        }else {
            return false;
        }
    }
}

function portfolioCheck() {
    var portfolio = "not";
    $.ajax({
        type : 'GET',
        url : '/task/portfolio',
        dataType : 'JSON',
        async: false,
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success : function(result, data) {
            portfolio = result.result;
        },
        error : function(result) {
            location.reload();
        }
    });
    return portfolio;
}