
/* 아이디 및 닉네임 중복확인, 비밀번호 확인을 완료 했는지 여부 */
let isCorrespondPw = false;
let isLoginIdUnique = false;
let isNicknameUnique = false;

/* loginId 중복확인 버튼 */
const doubleCheckLoginId = $('.doubleCheckLoginId');

/* loginId 확인 후 녹색 체크 아이콘 */
const checkLoginIdTrue = $('.checkLoginIdTrue');

/* nickName 중복확인 버튼 */
const doubleCheckNick = $('.doubleCheckNick');

/* nickName 확인 후 녹색 체크 아이콘 */
const checkNickTrue = $('.checkNickTrue');


/* loginId 입력내용 바뀌면 확인체크 none */
function checkLoginIdOnInput(){
    loginIdCheckOff();

    isLoginIdUnique = false;
}

/* loginId 중복확인 눌렀을 때 */
function checkLoginId(){
    let currentLoginId = document.getElementById('loginId').value;

    $.ajax({
        data:JSON.stringify(currentLoginId)
        ,url : "/api/member/checkId/" + currentLoginId
        ,type : "GET"
        ,contentType: 'application/json'
        ,success : function(data) {
            if(data){
                loginIdCheckOn();

                isLoginIdUnique = true;
            }else{
                $('#loginId').addClass('input-needCheck');
            }

        }
        ,error: function () {
            alert('통신오류');
        }
    })

}

/* loginId 중복확인 버튼 none, 확인체크 block */
function loginIdCheckOn(){
    if(doubleCheckLoginId.hasClass('active')){
        doubleCheckLoginId.removeClass('active');
    }

    if($('#loginId').hasClass('input-needCheck')){
        $('#loginId').removeClass('input-needCheck');
    }

    checkLoginIdTrue.addClass('active');
}

/* loginId 중복확인 버튼 block, 확인체크 none */
function loginIdCheckOff(){
    if( !doubleCheckLoginId.hasClass('active') ){
       doubleCheckLoginId.addClass('active');
    }

    if(checkLoginIdTrue.hasClass('active')){
        checkLoginIdTrue.removeClass('active');
    }
}


/* nickName 입력내용 바뀌면 확인체크 none */
function checkNickOnInput(){
    nickCheckOff();

    isNicknameUnique = false;
}

/* nickName 중복확인 눌렀을 때 */
function checkNick(){
    let currentNick = document.getElementById('nickname').value;

    $.ajax({
        data:JSON.stringify(currentNick)
        ,url : "/api/member/checkNick/" + currentNick
        ,type : "GET"
        ,contentType: 'application/json'
        ,success : function(data) {
            if(data){
                nickCheckOn();

                isNicknameUnique = true;
            }else{
                $('#nickname').addClass('input-needCheck');
            }

        }
        ,error: function () {
            alert('통신오류');
        }
    })

}

/* nickName 중복확인 버튼 none, 확인체크 block */
function nickCheckOn(){
    if(doubleCheckNick.hasClass('active')){
        doubleCheckNick.removeClass('active');
    }

    if($('#nickname').hasClass('input-needCheck')){
        $('#nickname').removeClass('input-needCheck');
    }

    checkNickTrue.addClass('active');
}

/* nickName 중복확인 버튼 block, 확인체크 none */
function nickCheckOff(){
    if( !doubleCheckNick.hasClass('active') ){
       doubleCheckNick.addClass('active');
    }

    if(checkNickTrue.hasClass('active')){
        checkNickTrue.removeClass('active');
    }
}


/* 비밀번호, 비밀번호 확인 일치 확인 */
function checkPwOnInput(){
  let currentPw = document.getElementById('loginPw').value;
  let currentCheckPw = document.getElementById('checkPw').value;

  if(currentPw === currentCheckPw){
    $('.pwTrue').addClass('active');
    $('.checkPwTrue').addClass('active');

    if($('.checkPwFalse').hasClass('active')){
      $('.checkPwFalse').removeClass('active');
    }

    isCorrespondPw = true;

  }else{
    $('.pwTrue').removeClass('active');
    $('.checkPwTrue').removeClass('active');
    $('.checkPwFalse').addClass('active');

    isCorrespondPw = false;

  }
}