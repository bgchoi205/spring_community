var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

/* 게시판 관리 - 게시판 추가버튼 */
$('#addBoardBtn').click(function(){
  $('#addBoardInput').css('display', 'block');
});

$('#addBoardClose').click(function(){
  $('#addBoardInput').css('display', 'none');
});

/* 게시판 관리 - 게시판 추가 */
function checkBoardAndAdd(){

    const boardName = $('#boardName').val();

    $.ajax({
        data:JSON.stringify(boardName)
        ,url : "/api/board/" + boardName
        ,type : "POST"
        ,contentType: 'application/json'
        ,beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        }
        ,success : function() {
            alert("게시판 생성 완료");
            location.reload();
        }
        ,error: function () {
            alert('게시판 생성 실패');
            location.reload();
        }
    })

}