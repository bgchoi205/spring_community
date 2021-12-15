/* csrf 토큰 전달을 위한 변수 */
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

// sideBar 클릭으로 나타나고 사라지기
function sideMenu__init(){
  $('.side-menu-btn').click(function(){
    if($('.side-menu-wrap, .side-menu-btn').hasClass('active')){
      $('.side-menu-wrap').removeClass('active');
      $('.side-menu-btn').removeClass('active');
    }else{
      $('.side-menu-wrap').addClass('active');
      $('.side-menu-btn').addClass('active');
    }

  });
}

sideMenu__init();


/* 게시물 뷰 - 게시물 삭제 */
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
            history.back();
        }
        ,error: function () {
            alert('게시물 삭제 실패');
            location.reload();
        }
    })

}