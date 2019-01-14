<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title><spring:message code="name185"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/swiper.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
	<script>
		var pay_url = '<%=resource.getString("pay.server.url") %>';
    </script>
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
                    	<input type="hidden" id="actual_payment" value="${order.actual_payment}">
                    	<input type="hidden" id="goodsName" value="${orderGoods.goodsName}">
                    	<input type="hidden" id="goodsNumber" value="${orderGoods.goodsNumber}">
                    	<input type="hidden" id="user_guid" value="${user_guid}">
                        <img class="clear img-80 mg-0-auto dis-b" src="<%=request.getContextPath() %>/resources/img/net_shopping/produ-right.png" alt="">
                    </div>
                    <div class="col-sm-7 pt-40 pb-40">
                        <p class="f-26 family-h col-252525 line-h-30">
                            <spring:message code="name95"/>
                        </p>
                        <p class="f-16 family-h col-252525 line-h-30 pt-10">
                            <spring:message code="name96"/><span class="col-309DE2">
                            <spring:message code="name97"/></span> 
                            <spring:message code="name98"/>
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
                    <div class="clear full-w pay-way">
                        <img class="pull-left dis-b w-150 bor bor-col-B2B2B2 pt-10 pb-10 pl-30 pr-30 cur-p weixinpay" src="<%=request.getContextPath() %>/resources/img/net_shopping/weixinpay.png" alt="">
                        <img class="pull-left dis-b w-150 bor bor-col-B2B2B2 ml-20 pt-10 pb-10 pl-30 pr-30 cur-p zhifubaopay" src="<%=request.getContextPath() %>/resources/img/net_shopping/zhifubaopay.png" alt="">   
                    </div>
                    
                     <p class="mt-30 f-14 col-666 mb-30">
                        <spring:message code="name105"/><a class="t-nonehove hovecol-309DE2 col-309DE2" href="<%=request.getContextPath() %>/afterSales">
                        <spring:message code="name106"/></a> 
                        <spring:message code="name107"/><a class="t-nonehove hovecol-309DE2 col-309DE2" href="<%=request.getContextPath() %>/disclaimer">
                        <spring:message code="name108"/></a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    
    <!-- =======================弹出层================================= -->
    <!-- 微信支付 -->
    <div class="clear show-weixinpay affix t-0 l-0 z-999 full-wh rgba-60 dis-n">
        <div class="pos-a t-30s l-0 full-w">
            <div class="w-400 mg-0-auto clear pos-r bg-white weixinpay-content">
                <div class="pt-15 pb-15 line-h-30 pos-r f-18 pl-20 pr-20 col-4a4a4a full-w bg-f5f5f5">
                    <spring:message code="name186"/>
                    <img class="pos-a t-20 r-20 img-20 close-mod cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/close.png" alt="">
                </div>
                <form class="clear pos-r" action="">
                    <div class="clear">
                        <p class="f-30 pt-60 pb-10 text-center">
                           <img id="weixin-qrcode"  src="" >
                        </p>
                    </div>
                    <div class="clear full-w line-h-30 pt-10 pb-20 text-center">
                        <p class="family-h f-14 col-4a4a4a">
                            <spring:message code="name187"/><span class="col-309DE2">
                            <spring:message code="name188"/></span><spring:message code="name189"/>
                        </p>
                        <p class="family-h f-14 col-4a4a4a">
                            <spring:message code="name190"/>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- 支付宝支付,直接弹出  二维吗 -->
    <div class="clear show-zhifubaopay affix t-0 l-0 z-999 full-wh rgba-60 dis-n">
        <div class="pos-a t-30s l-0 full-w">
            <div class="w-400 mg-0-auto clear pos-r bg-white zhifubaopay-content">
                <div class="pt-15 pb-15 line-h-30 pos-r f-18 pl-20 pr-20 col-4a4a4a full-w bg-f5f5f5">
                    <spring:message code="name191"/>
                    <img class="pos-a t-20 r-20 img-20 close-mod cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/close.png" alt="">
                </div>
                <form class="clear pos-r" action="">
                    <div class="clear">
                        <p class="f-30 pt-60 pb-10 text-center">
                            <img id="alipay-qrcode" src=""  alt="">
                        </p>
                    </div>
                    <div class="clear full-w line-h-30 pt-10 pb-20 text-center">
                        <p class="family-h f-14 col-4a4a4a">
                            <spring:message code="name187"/><span class="col-309DE2">
                            <spring:message code="name192"/></span>
                            <spring:message code="name189"/>
                        </p>
                        <p class="family-h f-14 col-4a4a4a"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  >
                            <spring:message code="name190"/>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="../comm/footer.jsp" %>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/swiper.jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
	<script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/shopping/zh_payment.js"></script>
</body>
</html>