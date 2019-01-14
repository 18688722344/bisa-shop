$(document).ready(function() {
	var path=$("base").attr("href");
    layui.use(['element', 'layer'], function(){
      var element = layui.element;
      var layer = layui.layer;

    });
    $(".layui-colla-item").click(function(){
        var speed = 200;
        $('body,html').animate({ scrollTop: 400 }, speed);
    });
    //选项卡
    $(".instructions-list1").click(function() {
        window.location.href=path+"Instructions";
    });

});

