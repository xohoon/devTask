var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
let taskDetailMap = {};
let taskMap = {};
$(function() {
    $("#task_dead_day").datepicker({dateFormat: 'yy-mm-dd'});
});

function addTaskDetail(title, id) { // 분야 추가 이벤트
    var table = id + "Table";
    addTable(title, id); // 테이블 view 추가
    document.getElementById(id).style.display = "none"; // button 숨김
    taskTableList.push(table); // 추가된 테이블이름 리스트에 추가
}

function removeTaskTable(id) { // 테이블 삭제
    if(id) {
        if (confirm("기입한 내용은 모두 삭제됩니다.") == true){
        }else{
            return false;
        }
    }
    var table = id + "Table";
    document.getElementById(id).style.display = "";
    document.getElementById(table).style.display = "none";
    taskTableList.pop(table);
}

function saveTask() { // 데이터 저장
    // detail data
    var td_id;
    var task_btn_id;
    var task_subject;
    var task_part;
    var task_part_personnel;
    var tasking_day;
    var task_need_skill;
    // task data
    var id = $("#id").val();
    var task_title = $("#task_title").val();
    var task_dead_day =$("#task_dead_day").val();
    if(id) { // update
        taskMap["id"] = id; // task map 에 들어가는 id 는 task id
    }
    if(!task_title) {
        alert("제목을 입력해주세요.");
        $("#task_title").focus();
        return false;
    }
    taskMap["task_title"] = task_title;
    if(!task_dead_day) {
        alert("과제 마감일을 설정해주세요.");
        $("#task_dead_day").focus();
        return false;
    }
    taskMap["task_dead_day"] = task_dead_day;
    for (var i in taskTableList) {
        taskDetailMap = {};
        td_id = $("input[name='td_id']").eq(i).val();
        if(td_id) { // update
            taskDetailMap["id"] = td_id; // detailMap 에 들어가는 id 는 task detail id
        }
        task_subject = $("input[name='task_subject']").eq(i).val();
        if(!task_subject) {
            task_subject = "nothing";
        }
        taskDetailMap["task_subject"] = task_subject;

        task_part = $("input[name='task_part']").eq(i).val();
        if(!task_part){
            task_part = "nothing";
        }
        taskDetailMap["task_part"] = task_part;

        task_part_personnel = $("input[name='task_part_personnel']").eq(i).val();
        if(!task_part_personnel) {
            task_part_personnel = "nothing";
        }
        taskDetailMap["task_part_personnel"] = task_part_personnel;

        tasking_day = $("input[name='tasking_day']").eq(i).val();
        if(!tasking_day) {
            tasking_day = "nothing";
        }
        taskDetailMap["tasking_day"] = tasking_day;

        task_need_skill = $("textarea[name='task_need_skill']").eq(i).val();
        if(!task_need_skill) {
            task_need_skill = "nothing";
        }
        taskDetailMap["task_need_skill"] = task_need_skill;

        task_btn_id = $("input[name='task_btn_id']").eq(i).val();
        if(!task_btn_id) {
            task_btn_id = "nothing";
        }
        taskDetailMap["task_btn_id"] = task_btn_id;

        taskMap[taskTableList[i]] = taskDetailMap;
    }

    $.ajax({
        type : 'POST',
        url : '/co/task/register',
        dataType : 'JSON',
        contentType : 'application/json;charset=UTF-8;',
        async: false,
        data : JSON.stringify(taskMap),
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success : function(result, data) {
            location.href="/co/task/main";
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
    html += '<input type="hidden" name="task_subject" id="task_subject" value="'+title+'">';
    html += '<input type="hidden" name="task_btn_id" id="task_btn_id" value="'+id+'">';
        html += '<thead style="border-top: white;border-left: white;border-right: white;">';
            html += '<tr style="text-align: left;">';
                html += '<th scope="col" colSpan="2">TASK # '+title+'</th>';
                html += '<th>';
    html += '<input onClick='+"javascript:removeTaskTable("+changeId+");"+' type="button" class="btn btn-danger btn-sm" style="float:right;" value="삭제" />';
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
                    html += '<input type="text" name="task_part" class="form-control" placeholder="예)백엔드, 프론트엔드">';
                html += '</td>';
                html += '<td>';
                    html += '<input type="text" name="task_part_personnel" class="form-control" placeholder="숫자만 입력">';
                html += '</td>';
                html += '<td>';
                    html += '<input type="text" name="tasking_day" class="form-control" placeholder="day 기준">';
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
                html += '<textarea class="form-control" name="task_need_skill" style="height: 150px;" placeholder="과제에 필요한 기능 및 기술을 상세히 기입해주세요."></textarea>';
                html += '</td>';
            html += '</tr>';
        html += '</tbody>';
    html += '</table>';
    html += '</div>';
    $("#tablePoint").prepend(html);
}
