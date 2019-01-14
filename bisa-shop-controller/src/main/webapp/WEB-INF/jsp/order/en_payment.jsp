<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>
<%
	ResourceBundle resource = ResourceBundle.getBundle("pay-config");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=0.35,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name93"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/swiper.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
    var order_no = $("#order_no").val();
    var post_url = '<%=resource.getString("pay.server.url") %>' + "/checkPayResult";
    $.ajax({
            url: post_url,
            data : {
            order_no : order_no
       },
    type:'POST',
    success : function(data){
            if(data.flag == true){
                    window.location.href = "user/orderSuccess?order_no=" + order_no;//跳转到支付成功的 页面
            }
        }
      });
    });
    </script>
</head>
<body>
  	<%@ include file="../comm/header.jsp" %>
    <div class="wrap">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 bg-f5f5f5 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath()%>/index"><spring:message code="homepage"/></a>
                    >
                    <span class="col-252525 t-nonehove"><spring:message code="shopCar"/></span>
                </p>
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-40 cur-d col-252525 family-h f-26">
                    <spring:message code="name94"/>
                </p>
            </div>
            <div class="clear pt-20 pl-20 pr-20 bg-f5f5f5 pb-45 full-w cur-d">
                <div class="clear bg-white">
                    <div class="clear col-sm-2 pt-60 pb-60">
                    	<input type="hidden" id="order_no" value="${order.order_no }">

                        <img class="clear img-80 mg-0-auto dis-b" src="<%=request.getContextPath() %>/resources/img/net_shopping/produ-right.png" alt="">
                    </div>
                    <div class="col-sm-7 pt-40 pb-40">
                        <p class="f-26 family-h col-252525 line-h-30">
                            <spring:message code="name95"/>
                        </p>
                        <p class="f-16 family-h col-252525 line-h-30 pt-10">
                            <spring:message code="name96"/><span class="col-309DE2"><spring:message code="name97"/></span><spring:message code="name98"/>
                        </p>
                        <p class="f-16 family-h col-252525 line-h-30 pt-10">
                            <spring:message code="name99"/><span class="col-252525">${order.detail_address }</span>
                        </p>
                    </div>
                    <div class="col-sm-3 pt-100 pb-40">
                        <p class="f-16 family-h col-252525 line-h-30 pt-10">
                           <spring:message code="name100"/>
                           <span class="f-26 col-309DE2">
                           		<spring:message code="name103"/>
                            	<fmt:formatNumber value="${order.actual_payment}" type="currency" pattern="#0.00"/>
                   		   </span>
                        </p>
                    </div>
                </div>

                <div class="clear mt-40 bg-white pt-25 pb-25 pl-50 pr-50">
                    <p class="f-18 family-h col-666 line-h-30 bor bor-b bor-col-D2D2D2 pb-10 clear">
                        <span class="pull-left dis-ib col-666">
                        	<spring:message code="name101"/>
                        </span>
                        <span class="pull-right dis-ib col-666 f-14">
                            <img class="pos-r t--2" src="<%=request.getContextPath() %>/resources/img/net_shopping/safety.png" alt="">
                            	<spring:message code="name102"/>
                        </span>
                    </p>
                    <p class="f-18 family-h col-252525 line-h-30 pt-20 pb-20">
                        <spring:message code="name104"/>
                    </p>
                    <div class="clear full-w">
                        <div class="clear pull-left bor bor-col-B2B2B2 min-w-150 text-center pt-10 pb-10 pay-way">
                            <!-- 下面是 visa提交的要的 信息============================ -->
                            <form id="service_mes" class="clear" action="${visaPayUrl}" method="post" target="_blank">
                                <input type="hidden" id="access_key" name="access_key" value="">
                                <input type="hidden" id="profile_id" name="profile_id" value="">
                                <input type="hidden" id="transaction_uuid" name="transaction_uuid" value="">
                                <input type="hidden" id="signed_field_names" name="signed_field_names" value="">
                                <input type="hidden" id="unsigned_field_names" name="unsigned_field_names" value="">
                                <input type="hidden" id="signed_date_time" name="signed_date_time" value="">
                                <input type="hidden" id="locale" name="locale" value="">
                                <input type="hidden" id="transaction_type" name="transaction_type" value="">
                                <input type="hidden" id="reference_number" name="reference_number" value="">
                                <input type="hidden" id="amount" name="amount" value="">
                                <input type="hidden" id="currency" name="currency" value="">

                                <input type="hidden" id="bill_to_surname" name="bill_to_surname" value="">
                                <input type="hidden" id="bill_to_forename" name="bill_to_forename" value="">
                                <input type="hidden" id="bill_to_address_line1" name="bill_to_address_line1" value="">
                                <input type="hidden" id="bill_to_email" name="bill_to_email" value="">
                                <input type="hidden" id="bill_to_address_city" name="bill_to_address_city" value="">
                                <input type="hidden" id="bill_to_address_country" name="bill_to_address_country" value="">
                                <input type="hidden" id="bill_to_address_postal_code" name="bill_to_address_postal_code" value="">
                                <input type="hidden" id="bill_to_address_state" name="bill_to_address_state" value="">

                                <input type="hidden" id="signature" name="signature" value="">
                                <div class="text-center">
                                    <a class="easy"><img class="h-45 cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/visapay.png" alt=""></a>
                                </div>
                            </form>
                        </div>

                        <div class="clear pull-left bor bor-col-B2B2B2 min-w-150 text-center ml-20 pt-10 pb-10 pay-way">
                            <!-- 银联支付 -->
                            <form id="service_mes1" class="clear" action="user/easylinkPay" method="post" target="_blank">
                                <input type="hidden" name="order_no" value="${order.order_no }">
                                <div class="text-center">
                                    <a class="unionPay"><img class="h-45 cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/yinlianpay.jpg" alt=""></a>
                                </div>
                            </form>
                        </div>
                     </div>

                     <p class="mt-30 f-14 col-666 mb-30">
                        <spring:message code="name105"/><a class="t-nonehove hovecol-309DE2 col-309DE2" href="<%=request.getContextPath() %>/afterSales"><spring:message code="name106"/></a>
                        <spring:message code="name107"/><a class="t-nonehove hovecol-309DE2 col-309DE2" href="<%=request.getContextPath() %>/disclaimer"><spring:message code="name108"/></a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../comm/footer.jsp" %>

    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/swiper.jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
	<script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
    <script type="text/javascript">
        /*进入页面加载一次，若已支付成功，应跳出该页面（暂时前台处理）*/
        $(document).ready(function () {
            var order_no = $("#order_no").val();
            var post_url = '<%=resource.getString("pay.server.url") %>' + "/checkPayResult";
            $.ajax({
                url: post_url,
                data : {
                    order_no : order_no
                },
                type:'POST',
                success : function(data){
                    if(data.flag == true){
                        window.location.href = "user/orderSuccess?order_no=" + order_no;//跳转到支付成功的 页面
                    }
                }
            });
        });
        
        /*layer渲染*/
        layui.use('layer', function(){
            var layer = layui.layer;
        });

        /*isClick是否点击过支付方式 */
        var isClick=false;

        //银联 支付
 	    $(".unionPay").click(function() {

            if (isClick) {
                /*提示弹框*/
                layer.open({
                    title: '温馨提示'
                    , content: '已经创建支付订单，注意造成多次支付'
                    , btn: ['知道了', '继续支付']
                    , yes: function (index, layero) {
                        //按钮【按钮知道了】的回调
                        layer.close(index);
                    }
                    , btn2: function (index, layero) {
                        //按钮【按钮继续支付】的回调
                        document.getElementById("service_mes1").submit();
                    }
                });
            }else{
                document.getElementById("service_mes1").submit();
                startCheck();
                isClick=true;
            }
        });

    	//visa 支付(这个js不能分离，不然获取不到model中的值
 	    $(".easy").click(function() {
            var orderNumber = '${orderNumber}';
            $.ajax({
                url: 'user/getVisaPayMap',
                data : {
                    orderNumber : orderNumber
                },
                async : false,
                type:'POST',
                success : function(data){
                    $("#access_key").attr("value",data.access_key);
                    $("#profile_id").attr("value",data.profile_id);
                    $("#transaction_uuid").attr("value",data.transaction_uuid);
                    $("#signed_field_names").attr("value",data.signed_field_names);
                    $("#unsigned_field_names").attr("value",data.unsigned_field_names);
                    $("#signed_date_time").attr("value",data.signed_date_time);
                    $("#locale").attr("value",data.locale);
                    $("#transaction_type").attr("value",data.transaction_type);
                    $("#reference_number").attr("value",data.reference_number);
                    $("#amount").attr("value",data.amount);
                    $("#currency").attr("value",data.currency);

                    $("#bill_to_surname").attr("value",data.bill_to_surname);
                    $("#bill_to_forename").attr("value",data.bill_to_forename);
                    $("#bill_to_address_line1").attr("value",data.bill_to_address_line1);
                    $("#bill_to_email").attr("value",data.bill_to_email);
                    $("#bill_to_address_city").attr("value",data.bill_to_address_city);
                    $("#bill_to_address_country").attr("value",data.bill_to_address_country);
                    $("#bill_to_address_postal_code").attr("value",data.bill_to_address_postal_code);
                    $("#bill_to_address_state").attr("value",data.bill_to_address_state);
                    $("#signature").attr("value",data.signature);
                }
            });

            if (isClick) {
                layer.open({
                    title: '温馨提示'
                    , content: '已经创建支付订单，避免造成多次支付'
                    , btn: ['知道了', '继续支付']
                    , yes: function (index, layero) {
                        //按钮【按钮知道了】的回调
                        layer.close(index);
                    }
                    , btn2: function (index, layero) {
                        //按钮【按钮继续支付】的回调
                        $("#service_mes").submit();
                    }
                });

            }else{
                    //下面是 点击了visa支付的按钮之后，开始轮询数据库支付的 状态(visa支付)，因为visa支付要打开一个新的窗口，老的窗口轮询，支付成功了后就跳转到成功的页面
                    isClick=true;
                    $("#service_mes").submit();
                    startCheck();
                }
 	    });

 	    //(轮询)请求查询是否已经支付(1.5秒一次)
 	    function startCheck(){
 	    	 var order_no = $("#order_no").val();
 	    	 var post_url = '<%=resource.getString("pay.server.url") %>' + "/checkPayResult";

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
    </script>
</body>
</html>