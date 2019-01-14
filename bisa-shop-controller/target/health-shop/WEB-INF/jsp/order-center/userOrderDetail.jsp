<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../comm/tag.jsp" %>
<%@ page import="com.bisa.health.pay.enumerate.PayStatus" %>
<%@ page import="com.bisa.health.pay.enumerate.PayType" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name247"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/user/user_center.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        var name271 = "<spring:message code='name271'/>";
        var name272 = "<spring:message code='name272'/>";
        var name273 = "<spring:message code='name273'/>";
        var name274 = "<spring:message code='name274'/>";
        var name275 = "<spring:message code='name275'/>";
        var name233 = "<spring:message code='name233'/>";
        var name234 = "<spring:message code='name234'/>";
        var name235 = "<spring:message code='name235'/>";
        var name236 = "<spring:message code='name236'/>";
        var name237 = "<spring:message code='name237'/>";
        var name276 = "<spring:message code='name276'/>";
        var name277 = "<spring:message code='name277'/>";
        var name256 = "<spring:message code='name256'/>";

        var name_147 ='<spring:message code="name_147"/>';
        var name_277 ='<spring:message code="name_277"/>';
        var name_245 ='<spring:message code="name_245"/>';
        var name_246 ='<spring:message code="name_246"/>';

        var create_time = '${order.create_time}';
        var pay_time = '${order.pay_time}';
        var deliver_goods_time = '${order.deliver_goods_time}';
        var receive_goods_time = '${order.receive_goods_time}';
    </script>
    <style type="text/css">
        .cntSeparator {
            font-size: 54px;
            margin: 10px 7px;
            color: #000;
        }
    </style>
</head>

<body>
<%@ include file="../comm/header.jsp" %>

<c:set var="orderclosed" value="<%=PayStatus.close.getValue()%>"/>
<c:set var="unpaid" value="<%=PayStatus.unpaid.getValue()%>"/>
<c:set var="paid" value="<%=PayStatus.paid.getValue()%>"/>
<c:set var="shipped" value="<%=PayStatus.waitDelivery.getValue()%>"/>
<c:set var="received" value="<%=PayStatus.received.getValue()%>"/>

<c:set var="alipay" value="<%=PayType.alipay.getValue()%>"/>
<c:set var="wechat" value="<%=PayType.wechat.getValue()%>"/>
<c:set var="visa" value="<%=PayType.visa.getValue()%>"/>
<c:set var="easy_link" value="<%=PayType.easy_link.getValue()%>"/>

<div class="wrap clear">
    <div class="container pl-0 pr-0">
        <div class="clear">
            <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/index"><spring:message
                        code="homepage"/> </a> >
                <span class="col-252525 t-nonehove"><spring:message code="name29"/></span>
            </p>
        </div>
    </div>
    <div class="clear full-w">
        <img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_NewsIndex/banner.jpg" alt="">
    </div>
    <div class="container mt-30 pl-0 pr-0 clear bg-f5f5f5 pt-30 pb-70 mb-60">
        <%@ include file="../comm/menu.jsp" %>
        <div class="clear col-sm-9 pr-30 pl-0">
            <div class="clear bg-white pb-280 pl-50 pr-50">
                <p class="pt-40-20-ipad f-50-40-ipad col-757575 line-h-70-50-ipad pb-20">
                    <spring:message code="name232"/>
                </p>
                <c:if test="${order.tra_status == unpaid}">
                    <!-- 倒计时的地方 -->
                    <!--
                    <div class="w-370 h-130" id="countdown1"></div>
                    -->
                    <div class="clear pull-left f-20-14-ipad col-red h-auto line-h-30 mb-10 ">
                      <spring:message code="name248"/>[${orderExpireTime}]<spring:message code="name249"/>
                    </div>
                </c:if>

                <br/>
                <div class="clear full-w">
                    <div class="clear pull-left f-20-14-ipad col-333 h-30 line-h-30 mb-10">
                        <spring:message code="name183"/>${order.order_no }
                    </div>
                    <input type="hidden" value="${order.order_no}" id="order_no">
                    <input type="hidden" value="${order.logistics_name}" id="logistics_name">
                    <input type="hidden" value="${order.logistics_number}" id="logistics_number">

                    <div class="clear pull-right h-30 line-h-30 mb-10 ">
                        <c:if test="${order.tra_status eq unpaid}">
                            <!--取消订单按钮-->
                            <button onclick="cancelOrder()"
                                    class="h-30 line-h-30 f-14 col-757575 bg-white pl-5 pr-5 text-center bor bor-col-ccc hovbg-757575 hovecol-white mr-20" >
                                <spring:message code="name250"/>
                            </button>
                            <!--支付按钮 -->
                            <button onclick='payButton()' class="h-30 line-h-30 f-14 bor-none col-white bg-309DE2 w-110 text-center hovbg-2D90CF">
                                <spring:message code="name230"/>
                            </button>
                        </c:if>

                        <!-- 查看物流的按钮 这里是发货了才会显示的按钮 -->
                        <c:if test="${Logistics_button == 'true'}">
                            <c:if test="${order.tra_status >= shipped}">
                                <button id="logistics" class="h-30 line-h-30 f-14 col-757575 bg-white w-110 text-center bor bor-col-ccc hovbg-757575 hovecol-white mr-20">
                                    <spring:message code="logistics"/>
                                </button>
                            </c:if>
                        </c:if>

                        <%--这里是考虑了 只有虚拟商品的时候就没有发货的状态了--%>
                        <c:if test="${order.tra_status eq shipped}">
                            <%--确认收货--%>
                            <button class="h-30 line-h-30 f-14 bor-none col-white bg-309DE2 w-110 text-center hovbg-2D90CF confirm-delivery-btn">
                                <spring:message code="name231"/>
                            </button>
                        </c:if>

                        <c:if test="${order.tra_status eq received}">
                            <%--评价按钮--%>
                            <button onclick="window.location.href='<%=request.getContextPath()%>/user/orderAppraise?order_no=${order.order_no}'" class="h-30 line-h-30 f-14 bor-none col-white bg-309DE2 w-110 text-center hovbg-2D90CF">
                                <spring:message code="name239"/>
                            </button>
                        </c:if>
                    </div>
                </div>
                <div class="clear bor bor-b bor-col-f5f5f5">
                </div>

                <!-- ===========在这里判断状态，根据值来判断要显示的内容====================================== -->
                <input class="order-stateinputval" type="hidden" value="${order.tra_status}">

                <div class="clear">
                    <div class="clear pt-20 pb-20 order-status">
                        <p class="col-309DE2 f-20 line-h-25 status-text">
                            <spring:message code="name251"/>
                        <div id="countdown"></div>
                        </p>
                    </div>
                    <div class="clear mt-5 mb-5 radius-13 bg-eee h-25 line-h-25 cur-d col-424242">
                        <div class="clear w-25s text-center pull-left"><spring:message code="name252"/></div>
                        <div class="clear w-25s text-center pull-left"><spring:message code="name253"/></div>
                        <div class="clear w-25s text-center pull-left"><spring:message code="name254"/></div>
                        <div class="clear w-25s text-center pull-left"><spring:message code="name255"/></div>
                    </div>
                </div>

                <!-- ========================上面是进度类型================================ -->
                <div class="clear mt-5 mb-5 h-25 line-h-25 cur-d">
                    <div class="clear w-25s col-757575 text-center pull-left create_time"></div>
                    <div class="clear w-25s col-757575 text-center pull-left pay_time"></div>
                    <div class="clear w-25s col-757575 text-center pull-left deliver_goods_time"></div>
                    <div class="clear w-25s col-757575 text-center pull-left receive_goods_time"></div>
                </div>

                <%--物流的信息--%>
                <c:if test="${order.logistics_number != ''}">
                    <div class="clear bor bor-b bor-col-cfeeab pt-20 pb-10">
                        <span class="col-757575">
                           	<spring:message code="name257"/>${order.logistics_name }
                        </span>
                        <span class="ml-20 col-757575">
                           	 <spring:message code="name258"/>${order.logistics_number}
                        </span>
                    </div>
                </c:if>

                <!-- ===================================订单下面的商品================================== -->
                <div class="clear full-w pt-20 pb-20">
                    <c:forEach items="${orderGoodsList}" var="orderDetail">
                        <div class="clear full-w">
                            <div class="col-sm-1 col-md-1 pl-0 pr-0 mt-15 mb-15">
                                <img class="img-60-35-ipad" src="${orderDetail.imgUrl}" alt="">
                            </div>
                            <div class="col-sm-7 col-md-5 col-lg-4 f-14 col-333 h-60-35-ipad line-h-60-ipad mt-15 mb-15 text-line-1">
                                    ${orderDetail.goodsName }
                            </div>
                            <div class="col-sm-4 col-md-6 col-lg-7 f-14 col-333 h-60-35-ipad line-h-60-ipad mt-15 mb-15">
                                <fmt:formatNumber value="" type="currency" pattern="#0.00"/>
                                    ${orderDetail.discountPrice } X ${orderDetail.count }
                                <fmt:formatNumber value="" type="currency" pattern="#0.00"/>
                            </div>
                            <div class="clear bor bor-b bor-col-f5f5f5 full-w">
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="clear pt-15 pb-20">
                    <p class="mb-15 f-20 col-333">
                        <spring:message code="name99"/>
                    </p>
                    <p class="f-14 col-757575 mt-20 line-h-20">
                        <span class="col-757575"><spring:message code="name259"/></span>
                        <span class="col-757575 ml-10">${order.consignee }</span>
                    </p>
                    <p class="f-14 col-757575 mt-15 line-h-20">
                        <span class="col-757575"><spring:message code="name260"/></span>
                        <span class="col-757575 ml-10">${order.phone }</span>
                    </p>
                    <p class="f-14 col-757575 mt-15 line-h-20 mb-30">
                        <span class="col-757575"><spring:message code="name261"/></span>
                        <span class="col-757575 ml-10">${order.detail_address }</span>
                    </p>
                    <div class="clear bor bor-b bor-col-f5f5f5 full-w">
                    </div>
                </div>

                <c:if test="${order.tra_status >= paid}">
                    <div class="clear pt-15 pb-20">
                        <p class="mb-15 f-20 col-333"><spring:message code="name266"/></p>
                        <p class="f-14 col-757575 mt-20 line-h-20 mb-30">
                            <span class="col-757575"><spring:message code="name266"/></span>
                            <span class="col-757575 ml-10">
                            	<c:if test="${order.pay_type eq wechat}">
                                    <spring:message code="name262"/>
                                </c:if>
                            	<c:if test="${order.pay_type eq alipay}">
                                    <spring:message code="name263"/>
                                </c:if>
                            	<c:if test="${order.pay_type eq visa}">
                                    <spring:message code="name264"/>
                                </c:if>
                            	<c:if test="${order.pay_type eq easy_link}">
                                    <spring:message code="name265"/>
                                </c:if>
                            </span>
                        </p>
                        <div class="clear bor bor-b bor-col-f5f5f5 full-w"></div>
                    </div>
                </c:if>

                <div class="clear full-w pt-20 pb-20">
                    <div class="clear">
                        <div class="col-md-10 col-sm-9 pl-0 pr-0 text-right f-14 col-757575 line-h-25">
                            <spring:message code="name267"/>
                        </div>
                        <div class="col-md-2 col-sm-3 pl-0 pt-0 text-right f-14 col-309DE2 line-h-25">
                            <spring:message code="name103"/>
                            <fmt:formatNumber value="${order.total_price}" type="currency" pattern="#0.00"/>
                        </div>
                    </div>
                    <div class="clear">
                        <div class="col-md-10 col-sm-9 pl-0 pr-0 text-right f-14 col-757575 line-h-25">
                            <spring:message code="name268"/>
                        </div>
                        <div class="col-md-2 col-sm-3 pl-0 pt-0 text-right f-14 col-309DE2 line-h-25">
                            -
                            <spring:message code="name103"/>
                            <fmt:formatNumber value="${order.preferential_price }" type="currency" pattern="#0.00"/>
                        </div>
                    </div>
                    <div class="clear">
                        <div class="col-md-10 col-sm-9 pl-0 pr-0 text-right f-14 col-757575 line-h-25">
                            <spring:message code="name269"/>
                        </div>
                        <div class="col-md-2 col-sm-3 pl-0 pt-0 text-right f-14 col-309DE2 line-h-25">
                            <spring:message code="name103"/>
                            <fmt:formatNumber value="${order.post_price }" type="currency" pattern="#0.00"/>
                        </div>
                    </div>
                    <div class="clear pt-10 pb-10">
                        <div class="col-md-10 col-sm-9 pl-0 pr-0 text-right f-14 col-757575 line-h-25">
                            <spring:message code="name270"/>
                        </div>
                        <div class="col-md-2 col-sm-3 pl-0 pt-0 text-right f-14 col-309DE2 line-h-25">
                                <span class="f-22 mr-5 col-309DE2">
	                                <spring:message code="name103"/>
                               		<fmt:formatNumber value="${order.actual_payment }" type="currency" pattern="#0.00"/> 
                                </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../comm/footer.jsp" %>
<script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
<script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
<script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/comm/menu.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/countdown/jquery.countdown.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/order-center/orderDetails.js"></script>
<!--
    <script type="text/javascript">
    	var countdownDate = '${countdownDate}';
    	console.log(countdownDate);
    	
		//这里是设置 倒计时的时间
		$('#countdown1').countdown({
			image : 'resources/img/countdown/digits.png',
			startTime : countdownDate,
			format : 'hh:mm:ss'
		});
	</script>
	 -->
</body>
</html>