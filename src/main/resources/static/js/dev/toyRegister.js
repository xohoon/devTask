var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
let toyMap = {};
let toyDetailMap = {};
$(function() {
    $("#toy_dead_day").datepicker({dateFormat: 'yy-mm-dd'});
});

function addToyDetail(title, id) { // 분야 추가 이벤트
    var table = id + "Table";
    addTable(title, id); // 테이블 view 추가
    document.getElementById(id).style.display = "none"; // button 숨김
    ToyTableList.push(table); // 추가된 테이블이름 리스트에 추가
}

function removeToyTable(id) { // 테이블 삭제
    if(id) {
        if (confirm("기입한 내용은 모두 삭제됩니다.") == true){
        }else{
            return false;
        }
    }
    var table = id + "Table";
    document.getElementById(id).style.display = "";
    document.getElementById(table).style.display = "none";
    ToyTableList.pop(table);
}

function saveToy() { // 데이터 저장
    // detail data
    var td_id;
    var toy_subject;
    var toy_part;
    var toy_part_personnel;
    var toying_day;
    var toy_need_skill;
    var toy_btn_id;
    // toy data
    var id = $("#id").val();
    var toy_title = $("#toy_title").val();
    var toy_content = $("textarea[name='toy_content']").val();
    var toy_dead_day =$("#toy_dead_day").val();

    if(id) { // update
        toyMap["id"] = id; // toy map 에 들어가는 id 는 toy id
    }
    if(!toy_title) {
        alert("제목을 입력해주세요.");
        $("#toy_title").focus();
        return false;
    }
    toyMap["toy_title"] = toy_title;
    if(!toy_content) {
        alert("상세 내용을 입력해주세요.");
        $("#toy_content").focus();
        return false;
    }
    toyMap["toy_content"] = toy_content;
    if(!toy_dead_day) {
        alert("과제 마감일을 설정해주세요.");
        $("#toy_dead_day").focus();
        return false;
    }
    toyMap["toy_dead_day"] = toy_dead_day;
    // detail data
    for (var i in ToyTableList) {
        toyDetailMap = {};
        td_id = $("input[name='td_id']").eq(i).val();
        if(td_id) { // update
            toyDetailMap["id"] = td_id; // detailMap 에 들어가는 id 는 toy detail id
        }
        toy_subject = $("input[name='toy_subject']").eq(i).val();
        if(!toy_subject) {
            toy_subject = "nothing";
        }
        toyDetailMap["toy_subject"] = toy_subject;

        toy_part = $("input[name='toy_part']").eq(i).val();
        if(!toy_part){
            toy_part = "nothing";
        }
        toyDetailMap["toy_part"] = toy_part;

        toy_part_personnel = $("input[name='toy_part_personnel']").eq(i).val();
        if(!toy_part_personnel) {
            toy_part_personnel = "nothing";
        }
        toyDetailMap["toy_part_personnel"] = toy_part_personnel;

        toying_day = $("input[name='toying_day']").eq(i).val();
        if(!toying_day) {
            toying_day = "nothing";
        }
        toyDetailMap["toying_day"] = toying_day;

        toy_need_skill = $("textarea[name='toy_need_skill']").eq(i).val();
        if(!toy_need_skill) {
            toy_need_skill = "nothing";
        }
        toyDetailMap["toy_need_skill"] = toy_need_skill;

        toy_btn_id = $("input[name='toy_btn_id']").eq(i).val();
        if(!toy_btn_id) {
            toy_btn_id = "nothing";
        }
        toyDetailMap["toy_btn_id"] = toy_btn_id;

        toyMap[ToyTableList[i]] = toyDetailMap;
    }

    $.ajax({
        type : 'POST',
        url : '/dev/toy/register',
        dataType : 'JSON',
        contentType : 'application/json;charset=UTF-8;',
        async: false,
        data : JSON.stringify(toyMap),
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success : function(result, data) {
            location.href="/dev/toy/main";
        },
        error : function(result) {
            console.log('ERROR');
        }
    });
}

function addTable(title, id) {
    var changeId = JSON.stringify(id);
    var html = '';
    html += '<div id="'+id+'Table" value="test11123123123">';
    html += '<table class="table table-bordered" >';
    html += '<input type="hidden" name="toy_subject" id="toy_subject" value="'+title+'">';
    html += '<input type="hidden" name="toy_btn_id" id="toy_btn_id" value="'+id+'">';
    html += '<thead style="border-top: white;border-left: white;border-right: white;">';
    html += '<tr style="text-align: left;">';
    html += '<th scope="col" colSpan="2">TASK #'+title+'</th>';
    html += '<th>';
    html += '<input onClick='+"javascript:removeToyTable("+changeId+");"+' type="button" class="btn btn-danger btn-sm" style="float:right;" value="삭제" />';
    html += '</th>';
    html += '</tr>';
    html += '</thead>';
    html += '<thead class="table-light" style="text-align: center;">';
    html += '<tr>';
    html += '<th scope="col">과제분야</th>';
    html += '<th scope="col">모집인원</th>';
    html += '<th scope="col">과제 진행 기간</th>';
    html += '</tr>';
    html += '</thead>';
    html += '<tbody>';
    html += '<tr>';
    html += '<td>';
    html += '<input type="text" name="toy_part" class="form-control" placeholder="예)백엔드, 프론트엔드">';
    html += '</td>';
    html += '<td>';
    html += '<input type="text" name="toy_part_personnel" class="form-control" placeholder="숫자만 입력">';
    html += '</td>';
    html += '<td>';
    html += '<input type="text" name="toying_day" class="form-control" placeholder="day 기준">';
    html += '</td>';
    html += '</tr>';
    html += '</tbody>';
    html += '<thead class="table-light" style="text-align: center;">';
    html += '<tr>';
    html += '<th scope="col" colSpan="3">주요스킬</th>';
    html += '</tr>';
    html += '</thead>';
    html += '<tbody>';
    html += '<tr>';
    html += '<td colSpan="3">';
    html += '<textarea class="form-control" name="toy_need_skill" style="height: 150px;" placeholder="과제에 필요한 기능 및 기술을 상세히 기입해주세요."></textarea>';
    html += '</td>';
    html += '</tr>';
    html += '</tbody>';
    html += '</table>';
    html += '</div>';
    $("#tablePoint").prepend(html);
}
