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