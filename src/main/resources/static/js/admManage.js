/* csrf 토큰 전달을 위한 변수 */
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");


/* 게시물 관리 - 게시물 삭제 */
function delArticle(articleId){

    if( !confirm("정말 삭제하시겠습니까?") ) {
        return false;
    }

    $.ajax({
        data:JSON.stringify(articleId)
        ,url : "/api/article/" + articleId
        ,type : "DELETE"
        ,contentType: 'application/json'
        ,beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        }
        ,success : function() {
            alert("게시물 삭제 완료");
            location.reload();
        }
        ,error: function () {
            alert('게시물 삭제 실패');
            location.reload();
        }
    })

}


/* 게시판 관리 - 게시판 추가버튼 */
function addBoard__init(){
  $('#addBoardBtn').click(function(){
    $('#addBoardInput').css('display', 'block');
  });

  $('#addBoardClose').click(function(){
    $('#addBoardInput').css('display', 'none');
  });
}

/* 게시판 관리 - 게시판 이름 수정 버튼 */
function modifyBoard__init(){
  $('.modifyAndDelTd > #modifyBoardBtn').click(function(){
    $('.modifyAndDelTd > #modifyBoardBtn ~ #modifyBoardInput').css('display', 'block');
  });

  $('#modifyBoardClose').click(function(){
    $('#modifyBoardInput').css('display', 'none');
  });
}

/* 게시판 추가, 이름수정 버튼 활성화 */
addBoard__init();
modifyBoard__init();


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

/* 게시판 관리 - 게시판 이름 수정 */
function changeBoardName(){

    const boardName = $('#newBoardName').val();

    $.ajax({
        data:JSON.stringify(boardName)
        ,url : "/api/board/" + boardName
        ,type : "PUT"
        ,contentType: 'application/json'
        ,beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        }
        ,success : function() {
            alert("게시판 이름 수정 완료");
            location.reload();
        }
        ,error: function () {
            alert('게시판 이름 수정 실패');
            location.reload();
        }
    })

}

/* 게시판 관리 - 게시판 삭제 */
function delBoard(boardName){

    if( !confirm("정말 삭제하시겠습니까?") ) {
        return false;
    }

    $.ajax({
        data:JSON.stringify(boardName)
        ,url : "/api/board/" + boardName
        ,type : "DELETE"
        ,contentType: 'application/json'
        ,beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        }
        ,success : function() {
            alert("게시판 삭제 완료");
            location.reload();
        }
        ,error: function () {
            alert('게시판 삭제 실패');
            location.reload();
        }
    })

}

/* 회원 관리 - 회원 삭제 */
function delMember(memberId){

    if( !confirm("정말 삭제하시겠습니까?") ) {
        return false;
    }

    $.ajax({
        data:JSON.stringify(memberId)
        ,url : "/api/member/" + memberId
        ,type : "DELETE"
        ,contentType: 'application/json'
        ,beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        }
        ,success : function() {
            alert("회원계정 삭제 완료");
            location.reload();
        }
        ,error: function () {
            alert('회원계정 삭제 실패');
            location.reload();
        }
    })

}

// 모든 체크박스가 선택되었다면 thead의 체크박스도 체크, 하나라도 해제되면 thead도 해제
function countChecked(){

    const $allCheck = $('.admArticleList > thead > tr > td:first-child > input[type="checkbox"]');

    const $CountCheckBoxes = $('.admArticleList > tbody > tr > td:first-child > input[type="checkbox"]').length;

    const $CountCheckedBoxes = $('.admArticleList > tbody > tr > td:first-child > input[type="checkbox"]:checked').length;

    if($CountCheckBoxes == $CountCheckedBoxes){
        $allCheck.prop('checked', true);
    } else{
        $allCheck.prop('checked', false);
    }

}

// thead의 체크박스가 체크되었는지 변화를 캐치해서 전체선택, 전체해제
function admArticleList__init(){

    const $allCheck = $('.admArticleList > thead > tr > td:first-child > input[type="checkbox"]');

    const $checkBoxes = $('.admArticleList > tbody > tr > td:first-child > input[type="checkbox"]');

    $allCheck.change(function(){
        if(this.checked){
            $checkBoxes.prop('checked', true);
        }else{
            $checkBoxes.prop('checked', false);
        }
    });

    $checkBoxes.change(function(){
        countChecked();
    });

}

admArticleList__init();