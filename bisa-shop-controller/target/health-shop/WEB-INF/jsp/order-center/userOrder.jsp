<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bisa.health.pay.enumerate.PayStatus"%>
<%@ page import="com.bisa.health.pay.enumerate.PayType"%>
<%@ include file="../comm/tag.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="initial-scale=0.35,user-scalable=yes">
<!-- necessary -->
<meta name="keywords" content="1,2,3">
<meta name="description" content="">
<!-- description -->
<meta name="renderer" content="webkit">
<title><spring:message code="name216"/></title>
<!-- base -->
<link href="<%=request.getContextPath()%>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/comm/base.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/css/user/user_center.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
<script>
	var ajax_url = "<%=request.getContextPath()%>";
	
	var orderclosed = "<%=PayStatus.close.getValue()%>";  
	var unpaid = "<%=PayStatus.unpaid.getValue()%>";  
    var paid = "<%=PayStatus.paid.getValue()%>"; 
    var shipped = "<%=PayStatus.waitDelivery.getValue()%>";   
    var received = "<%=PayStatus.received.getValue()%>";  
    var review = "<%=PayStatus.appraise_one.getValue()%>";  
    
    var wechatPay = "<%=PayType.wechat.getValue()%>";
    var aliPay = "<%=PayType.alipay.getValue()%>";
    var easyLink = "<%=PayType.easy_link.getValue()%>";
    var visaPay = "<%=PayType.visa.getValue()%>";
</script>
<script type="text/javascript">
	var name233 = "<spring:message code='name233'/>";
	var name234 = "<spring:message code='name234'/>";
	var name235 = "<spring:message code='name235'/>";
	var name236 = "<spring:message code='name236'/>";
	var name237 = "<spring:message code='name237'/>";
	var name_233 = "<spring:message code='name_233'/>";
	var name_236 = "<spring:message code='name_236'/>";
	var name_237 = "<spring:message code='name_237'/>";


	var name221 = "<spring:message code='name221'/>";
	var name218 = "<spring:message code='name218'/>";
	var name219 = "<spring:message code='name219'/>";
	var name238 = "<spring:message code='name238'/>";
	var name239 = "<spring:message code='name239'/>";
	var name240 = "<spring:message code='name240'/>";
	var name241 = "<spring:message code='name241'/>";
	var name242 = "<spring:message code='name242'/>";
	var name243 = "<spring:message code='name243'/>";
	var name244 = "<spring:message code='name244'/>";
	var name245 = "<spring:message code='name245'/>";
	var name246 = "<spring:message code='name246'/>";
    var name_147 ='<spring:message code="name_147"/>';
    var name_245 ='<spring:message code="name_245"/>';
    var name_246 ='<spring:message code="name_246"/>';
</script>
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<%@ include file="../comm/header.jsp"%>
	<div class="wrap clear">
		<div class="container pl-0 pr-0">
			<div class="clear">
				<p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
					<a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath()%>/index"><spring:message code="homepage"/></a>>
					<span class="col-252525 t-nonehove"><spring:message code="name29"/></span>
				</p>
			</div>
		</div>
		<div class="clear full-w">
			<img class="full-w" src="<%=request.getContextPath()%>/resources/img/user/User/userCenter-banner.jpg" alt="">
		</div>

		<div class="container pl-0 pr-0 mt-30 clear bg-f5f5f5 pt-30 pb-70 mb-60">
			<%@ include file="../comm/menu.jsp"%>
			<div class="clear col-sm-9 pr-30 pl-0">
				<div class="clear bg-white pb-280 plr-50-10-ipad main-container">
					<p class="pt-40-20-ipad f-50-40-ipad col-757575 line-h-70-50-ipad pb-20">
						<spring:message code="name29"/></p>
					<div class="clear bor bor-b bor-col-f5f5f5"></div>
					<div class="clear mt-40-20-ipad f-20-14-ipad mb-30-20-ipad line-h-25">
						<span class="col-333 col-active cur-p Order-control Order-controlv1"><spring:message code="name217"/></span>
						<span class="col-e9e9e9 mlr-20-10-ipad cur-d">|</span> 
						<span class="col-333 cur-p Order-control Order-controlv2"><spring:message code="name218"/></span>
						<span class="col-e9e9e9 mlr-20-10-ipad cur-d">|</span>
						<span class="col-333 cur-p Order-control Order-controlv6"><spring:message code="name_218"/></span>
						<span class="col-e9e9e9 mlr-20-10-ipad cur-d">|</span>
						<span class="col-333 cur-p Order-control Order-controlv3"><spring:message code="name219"/></span>
						<span class="col-e9e9e9 mlr-20-10-ipad cur-d">|</span> 
						<span class="col-333 cur-p Order-control Order-controlv4"><spring:message code="name220"/></span>
						<span class="col-e9e9e9 mlr-20-10-ipad cur-d">|</span> 
						<span class="col-333 cur-p Order-control Order-controlv5"><spring:message code="name221"/></span>
					</div>
					<div class="no-data-div text-center f-18 col-333 pt-30 pd-30">
						<spring:message code="name222"/>
					</div>
					<div class="div-order-module">
					
					<!-- =================用户下面的    订单列表============================== -->
					<div class="clear main-order-div">
						<div class="clear full-w bor tab-order bor-col-67b7ea min-h-200 mb-25"> <!--  bor-col-ccc -->
							<div class="clear full-w  min-h-88 bor bor-b  pt-15 pb-10 bg-deeffa bor-col-67b7ea plr-20-5-ipad order-info">  <!-- bor-col-ccc -->
								<p class="pt-5">
									<span class="f-20 order-status"><spring:message code="name223"/></span> 
								</p>
								<ul class="clear h-35 pos-r cur-d">
									<li class="col-757575 f-12 pull-left mt-17 mr-5"><spring:message code="name224"/></li>
									<li class="col-757575 f-12 pull-left mt-17 order-time"></li>
									<li class="col-e9e9e9 ml-5 mr-5 cur-d f-12 pull-left mt-17">|</li>
									<li class="col-757575 f-12 pull-left mt-17 mr-5"><spring:message code="name225"/></li>
									<li class="col-757575 f-12 pull-left mt-17 order-no">xxxxxx</li>
									<li class="col-e9e9e9 ml-5 mr-5 cur-d f-12 pull-left mt-17">|</li>
									<li class="col-757575 f-12 pull-left mt-17 pay-type"><spring:message code="name226"/></li><!-- 支付宝支付/微信支付 -->
									<li class="col-333 f-22-14-ipad pull-right mt-10-17-ipad mr-5 ml-5 actual-payment"><spring:message code="name227"/></li>
									<li class="col-757575 f-12 pull-right mt-17 "><spring:message code="name103"/></li>
									<li class="col-757575 f-12 pull-right mt-17"><spring:message code="name228"/></li>
								</ul>
							</div>
							
							<!-- =================订单列表下面的每一个 订单商品详情======================= -->
							<div class="clear pt-15 pb-15 plr-20-5-ipad">
								<div class="clear pull-left pb-15 plr-20-5-ipad w-60p div-detail-module">
									<div class="clear order-detail mt-10">
										<div class="clear pull-left">
											<img class="img-80 cur-p goods-img" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
										</div>
										<div class="clear pull-left cur-d ml-15">
											<p class="pt-15 line-h-25 col-333 f-14 goods-name"><spring:message code="name229"/></p>
											<p class="pb-15 line-h-25 col-333 f-14 goods-price">￥1000</p>
										</div>
									</div>
								</div>
								
								<!-- ==============================下面的是订单的基本操作============================================= -->
								<div class="clear pull-right order-opt">
									<input type="hidden" class="order-opt-no" value="" />
                                    <input type="hidden" class="order_no" value="" />
                                    <!-- 立即支付 -->
                                    <div class="clear h-40 pb-10 dis-n pay-btn">
                                        <button class="h-30 line-h-30 f-14 bor-none col-white bg-309DE2 w-110 text-center hovbg-2D90CF">
											<spring:message code="name230"/></button>
									</div>
									<!-- 确认收货 -->
									<div class="clear h-40 pb-10 confirm-delivery-btn dis-n">
										<button class="h-30 line-h-30 f-14 bor-none col-white bg-309DE2 w-110 text-center hovbg-2D90CF">
											<spring:message code="name231"/></button>
									</div>
									<!-- 订单详情 -->
									<div class="clear h-40 pt-10 order-detail-btn">
										<button class="h-30 line-h-30 f-14 col-757575 bg-white w-110 text-center bor bor-col-ccc hovbg-757575 hovecol-white">
										<spring:message code="name232"/></button>
									</div>
									<!-- 立即评价 -->
									<div class="clear h-40 pt-10 appraise-btn dis-n">
										<button class="h-30 line-h-30 f-14 col-757575 bg-white w-110 text-center bor bor-col-ccc hovbg-757575 hovecol-white">
										<spring:message code="name198"/></button>
									</div>
									<!-- 删除订单 -->
									<div class="clear h-40 pt-10 delect-btn dis-n">
										<button class="h-30 line-h-30 f-14 col-757575 bg-white w-110 text-center bor bor-col-ccc hovbg-757575 hovecol-white">
										 <spring:message code="name_198"/></button>
									</div>
								</div>
							</div>
							</div>	
						</div>
						</div>
						<!-- 分页 -->
						<div class="text-center order-pager">
							<div id="layer-pager"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	<%@ include file="../comm/footer.jsp"%>
	<script src="<%=request.getContextPath()%>/resources/js/comm/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/comm/base.js"></script>
	<script src="<%=request.getContextPath()%>/resources/ctrl/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/comm/menu.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/order-center/userOrder.js"></script>
</body>
</html>