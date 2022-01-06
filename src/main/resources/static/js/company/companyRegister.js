var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var tableList = new Array();
function addTaskDetail(value, title, id) {
    var table = id + "Table";
    addTable(value, title, id);
    console.log("나와라"+id);
    document.getElementById(id).style.display = "none";
    tableList.push(table);
}

function removeTable(id) {
    var table = id + "Table";
    console.log("??" + id + ":" + table);
    document.getElementById(id).style.display = "";
    document.getElementById(table).style.display = "none";
    tableList.pop(table);
}

var taskMap = {};
var dataMap = {};
function saveTask() {
    var task_subject;
    var task_part;
    var task_part_personnel;
    var tasking_day;
    var task_need_skill;
    var task_title = $("#task_title").val();
    var task_dead_day =$("#task_dead_day").val();
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
    for (var i in tableList) {
        dataMap = {};
        task_subject = $("input[name='task_subject']").eq(i).val();
        if(!task_subject) {
            task_subject = "nothing";
        }
        dataMap["task_subject"] = task_subject;

        task_part = $("input[name='task_part']").eq(i).val();
        if(!task_part){
            task_part = "nothing";
        }
        dataMap["task_part"] = task_part;

        task_part_personnel = $("input[name='task_part_personnel']").eq(i).val();
        if(!task_part_personnel) {
            task_part_personnel = "nothing";
        }
        dataMap["task_part_personnel"] = task_part_personnel;

        tasking_day = $("input[name='tasking_day']").eq(i).val();
        if(!tasking_day) {
            tasking_day = "nothing";
        }
        dataMap["tasking_day"] = tasking_day;

        task_need_skill = $("textarea[name='task_need_skill']").eq(i).val();
        if(!task_need_skill) {
            task_need_skill = "nothing";
        }
        dataMap["task_need_skill"] = task_need_skill;

        taskMap[tableList[i]] = dataMap;
    }

    $.ajax({
        type : 'POST',
        url : '/co/get/task/register',
        dataType : 'JSON',
        contentType : 'application/json;charset=UTF-8;',
        async: false,
        data : JSON.stringify(taskMap),
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success : function(result, data) {
            location.href="/co/task/list";
        },
        error : function(result) {
            console.log('ERROR');
        }
    });

}

function addTable(value, title, id) {
    var changeId = JSON.stringify(id);
    var html = '';
    html += '<div id="'+id+'Table" value="test11123123123">';
    html += '<table class="table table-bordered" >';
    html += '<input type="hidden" name="task_subject" id="task_subject" value="'+title+'">';
        html += '<thead style="border-top: white;border-left: white;border-right: white;">';
            html += '<tr>';
                html += '<th scope="col" colSpan="2">TASK #'+title+'</th>';
                html += '<th>';
    html += '<input onClick='+"javascript:removeTable("+changeId+");"+' type="button" class="btn btn-danger btn-sm" style="float:right;" value="삭제" />';
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
                    html += '<input type="text" name="task_part" class="form-control" value="test" placeholder="예)백엔드, 프론트엔드">';
                html += '</td>';
                html += '<td>';
                    html += '<input type="text" name="task_part_personnel" class="form-control" value="1" placeholder="숫자만 입력">';
                html += '</td>';
                html += '<td>';
                    html += '<input type="text" name="tasking_day" class="form-control" value="2" placeholder="day 기준">';
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
                html += '<textarea class="form-control" name="task_need_skill" style="height: 150px;" placeholder="과제에 필요한 기능 및 기술을 상세히 기입해주세요.">qwe123</textarea>';
                html += '</td>';
            html += '</tr>';
        html += '</tbody>';
    html += '</table>';
    html += '</div>';
    $("#tablePoint").prepend(html);
}