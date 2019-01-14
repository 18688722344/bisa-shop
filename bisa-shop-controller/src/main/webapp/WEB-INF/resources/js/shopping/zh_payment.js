$(document).ready(function() {
    /*头部导航下划线*/
    $(".mainnav").find("a").mouseenter(function() {
        $(this).addClass("navbor");
    });
    $(".mainnav").find("a").mouseleave(function() {
        $(this).removeClass("navbor");
    });
    /*头部输入框变化*/
    $(".mainsearch").click(function() {
        event.stopPropagation();
        $(".mainsearchinput").fadeIn();
    });
    $(document).click(function() {
        $('.mainsearchinput').fadeOut();
    });
    
    //================================下面 控制弹出框===============================
    //订单提交成功页弹出 (微信) 二维码
    $(".weixinpay").click(function() {
    	onclickWXPayResult();
        $(".show-weixinpay").fadeIn();
    });
    $(".show-weixinpay").on("click", function(event) {
        event.stopPropagation();
        var target = event.target;
        if (!$(target).closest(".weixinpay-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
            $(".show-weixinpay").fadeOut();
        };
    });
	//订单提交成功页弹出 (支付宝)二维码
    $(".zhifubaopay").click(function() {
    	onclickAlipayResult();
        $(".show-zhifubaopay").fadeIn();
    });
    $(".show-zhifubaopay").on("click", function(event) {
        event.stopPropagation();
        var target = event.target;
        if (!$(target).closest(".zhifubaopay-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
            $(".show-zhifubaopay").fadeOut();
        };
    });
    
    //=========================下面 加载 (支付宝 微信) 二维码图片 和一个定时器===================================================
    //下面是 异步加载 (支付宝) 支付图片的二维码
    function onclickAlipayResult(){
    	var order_no = $("#order_no").val();
	    var goodsName = $("#goodsName").val();

	    var post_url = pay_url + "/aliPay";
	    var getQRCode = pay_url + "/getQRCode";
	    
    	$.ajax({
	           url: post_url,
	           data : { 
	        	   order_no : order_no,
	        	   body : goodsName
	           },
	           type:'POST',
	           dataType:'json',
	           success:function(data){
	        	   $("#alipay-qrcode").attr('src', getQRCode + '?code_url=' + data.url);
	        	   startCheck();//(轮询)请求查询
	           }
	       });
    }
    
    //下面是 异步加载 (微信) 支付图片的二维码
    function onclickWXPayResult(){
		  var order_no = $("#order_no").val();
		  var goodsName = $("#goodsName").val();
		  var goodsNumber = $("#goodsNumber").val();
		  var user_guid = $("#user_guid").val();

		  var post_url = pay_url + "/wxPay";
		  var getQRCode = pay_url + "/getQRCode";
		  
		  $.ajax({
	           url:post_url,
	           data : {
	        	   order_no : order_no,
	        	   user_guid : user_guid,
	        	   goodsName : goodsName,
	        	   goodsNumber : goodsNumber
	           },
	           type:'POST',
	           dataType:'json',
	           success:function(data){ 
	        	   $("#weixin-qrcode").attr('src', getQRCode +'?code_url=' + data.url);
	        	   startCheck();//(轮询)请求查询
	           }
	       });
	 }
    
    //等图片出来的时候  (轮询)请求查询是否已经支付(3秒一次)
    function startCheck(){
    	 var order_no = $("#order_no").val();
    	 var post_url= pay_url + "/checkPayResult";
    	 
         var id = window.setInterval(function(){
        	 $.ajax({
                 url: post_url,
                 data : {
              	   order_no : order_no 
                 },
                 type:'POST',
                 success : function(data){ 
                    if(data.flag == true){
                 	  	window.clearInterval(id);
                 	  	window.location.href = "user/orderSuccess?order_no=" + order_no;//跳转到支付成功的 页面
                    }
                 }
            });
         },500);
    };

    //==================================================================================
    //订单提交成功页支付方式的边框颜色改变
    $(".pay-way").find("img").mouseenter(function() {
        $(this).addClass("bor-col-309DE2");
    });
    $(".pay-way").find("img").mouseleave(function() {
        $(this).removeClass("bor-col-309DE2");
     });
});