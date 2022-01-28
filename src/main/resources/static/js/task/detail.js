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

function viewMap(value) {
    if(value == "homepage") {
        var url = $("#url").val();
        window.open('https://www.'+url);
    }else if(value == "map") {
        var address = $("#address").val();
        var company = $("#company").val();
        console.log("TEST1" + address);
        var geocoder = new kakao.maps.services.Geocoder();
        geocoder.addressSearch(address, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                var address_x = result[0].x;
                var address_y = result[0].y;
                window.open('https://map.kakao.com/link/map/'+company+','+address_y+','+address_x);
            }
        });
    }
}
