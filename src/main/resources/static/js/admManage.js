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
        ,success : function(data) {
            if(data){
                alert("게시물 삭제 완료");
                location.reload();
            }else{
                alert('게시물 삭제 실패!');
            }
        }
        ,error: function () {
            alert('통신오류');
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

/* 게시판 관리 - 게시판 수정 열기 */
function openModifyBoard(boardName){

  const modifyInput = $('#modifyBoardInput');
  const prevBoardNameInput = $('#modifyBoardInput > .boardNameDiv > input[name=prevBoardName]');
  const newBoardNameInput = $('#modifyBoardInput > .boardNameDiv > input[name=newBoardName]');

  prevBoardNameInput.val(boardName);
  newBoardNameInput.val(boardName);

  if(!modifyInput.hasClass('active')){
    modifyInput.addClass('active');
  }else{
    modifyInput.removeClass('active');
  }
}

/* 게시판 관리 - 게시판 수정 닫기 */
function closeModifyBoard(){
    const modifyInput = $('#modifyBoardInput');
    if(modifyInput.hasClass('active')){
      modifyInput.removeClass('active');
    }
}

/* 게시판 추가 버튼 활성화 */
addBoard__init();


/* 게시판 관리 - 게시판 추가 */
function checkBoardAndAdd(){

    const boardName = $('#boardName').val();

    if(boardName.trim().length == 0){
        alert('게시판명을 입력해주세요.');
        return false;
    }

    $.ajax({
        data:JSON.stringify(boardName)
        ,url : "/api/board/" + boardName
        ,type : "POST"
        ,contentType: 'application/json'
        ,beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        }
        ,success : function(data) {
            if(data){
                alert("게시판 생성 완료");
                location.reload();
            }else{
                alert('게시판 생성 실패!');
            }
        }
        ,error: function () {
            alert('통신오류');
        }
    })

}

/* 게시판 관리 - 게시판 이름 수정 */
function changeBoardName(){

    const prevBoardName = $('#prevBoardName').val();
    const newBoardName = $('#newBoardName').val();

    if($('#newBoardName').val().trim().length == 0){
        alert('수정할 게시판명을 입력해주세요.');
        return false;
    }

    let boardModifyDto = {"prevBoardName":prevBoardName, "newBoardName":newBoardName}

    $.ajax({
        data:JSON.stringify(boardModifyDto)
        ,url : "/api/board"
        ,type : "PUT"
        ,contentType: 'application/json'
        ,beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        }
        ,success : function(data) {
            if(data){
                alert("게시판 이름 수정 완료");
                location.reload();
            }else{
                alert('게시판 이름 수정 실패!');
            }
        }
        ,error: function () {
            alert('통신오류');
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
        ,success : function(data) {
            if(data){
                alert("게시판 삭제 완료");
                location.reload();
            }else{
                alert('게시판 삭제 실패!');
            }

        }
        ,error: function () {
            alert('통신오류');
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
        ,success : function(data) {
            if(data){
                alert("회원계정 삭제 완료");
                location.reload();
            }else{
                alert('계정 삭제 실패!');
            }

        }
        ,error: function () {
            alert('통신오류');
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


// 게시물 선택삭제
function delCheckedArticles(){

    if( !confirm("정말 삭제하시겠습니까?") ) {
        return false;
    }

    let checkedArticleIds = [];

    $('.admArticleList > tbody > tr > td:first-child > input[type="checkbox"]:checked').each(function(){
        let checkedArticleId = $(this).val();
        checkedArticleIds.push(checkedArticleId);
    });

    $.ajax({
        data: {
            checkedArticleIds : checkedArticleIds
        }
        ,url : "/api/articles/" + checkedArticleIds
        ,type : "DELETE"
        ,contentType: 'application/json'
        ,beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        }
        ,success : function(data) {

            if(data){
                alert("삭제되었습니다");
                location.reload();
            }else{
                alert('삭제 실패!');
            }

        }
        ,error: function () {

            alert('통신오류');
        }
    })

}