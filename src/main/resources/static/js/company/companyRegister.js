function addTaskDetail(value, title, id) {
    console.log("나와라"+title);
    addTable(value, title, id);
    document.getElementById(id).style.display = "none";
}

function removeTable(id) {
    var table = id + "Table";
    console.log("??" + id + ":" + table);
    document.getElementById(id).style.display = "";
    document.getElementById(table).style.display = "none";
}

function addTable(value, title, id) {
    var change = JSON.stringify(id);
    var html = '';
    html += '<table class="table table-bordered" id="'+id+'Table">';
        html += '<thead style="border-top: white;border-left: white;border-right: white;">';
            html += '<tr>';
                html += '<th scope="col" colSpan="2">TASK #'+title+'</th>';
                html += '<th>';
    html += '<input onClick='+"javascript:removeTable("+change+");"+' type="button" class="btn btn-danger btn-sm" style="float:right;" value="삭제" />';
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
                    html += '<input type="text" class="form-control" placeholder="예)백엔드, 프론트엔드">';
                html += '</td>';
                html += '<td>';
                    html += '<input type="text" class="form-control" placeholder="숫자만 입력">';
                html += '</td>';
                html += '<td>';
                    html += '<input type="text" class="form-control" placeholder="day 기준">';
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
                html += '<textarea class="form-control" style="height: 150px;" placeholder="과제에 필요한 기능 및 기술을 상세히 기입해주세요."></textarea>';
                html += '</td>';
            html += '</tr>';
        html += '</tbody>';
    html += '</table>';
    $("#tablePoint").prepend(html);
}