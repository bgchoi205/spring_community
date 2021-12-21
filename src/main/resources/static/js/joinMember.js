let isCorrespondPw = false;
let isLoginIdUnique = false;
let isNicknameUnique = false;

function checkLoginId(){
  let currentLoginId = document.getElementById('loginId').value;

}

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