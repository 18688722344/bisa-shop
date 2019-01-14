<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bisa.health.pay.enumerate.PayStatus"%>
<%@ page import="com.bisa.health.pay.enumerate.PayType"%>
<%@ page import="com.bisa.health.shop.enumerate.AfterSalesStatus"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">

<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title>碧沙康健_查看订单</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
		var orderclosed = "<%=PayStatus.close.getValue()%>";  
		var unpaid = "<%=PayStatus.unpaid.getValue()%>";  
	    var paid = "<%=PayStatus.paid.getValue()%>"; 
	    var shipped = "<%=PayStatus.waitDelivery.getValue()%>";   
	    var received = "<%=PayStatus.received.getValue()%>";  
	    var review = "<%=PayStatus.appraise_one.getValue()%>";  
	    var review_two = "<%=PayStatus.appraise_two.getValue()%>";  
	    
	    var wechatPay = "<%=PayType.wechat.getValue()%>";
	    var aliPay = "<%=PayType.alipay.getValue()%>";
	    var easyLink = "<%=PayType.easy_link.getValue()%>";
	    var visaPay = "<%=PayType.visa.getValue()%>";
	    
	    var finance_paid = "<%=AfterSalesStatus.paid.getValue()%>";
	    var apply_for = "<%=AfterSalesStatus.apply_for.getValue()%>";
	    var apply_for_ing = "<%=AfterSalesStatus.apply_for_ing.getValue()%>";
	    var service_success = "<%=AfterSalesStatus.service_success.getValue()%>";
	    var service_fail = "<%=AfterSalesStatus.service_fail.getValue()%>";
	    var logistics_success = "<%=AfterSalesStatus.logistics_success.getValue()%>";
	    var logistics_fail = "<%=AfterSalesStatus.logistics_fail.getValue()%>";
	    var finance_success = "<%=AfterSalesStatus.finance_success.getValue()%>";
	    var finance_fail = "<%=AfterSalesStatus.finance_fail.getValue()%>";
	    var refund_success = "<%=AfterSalesStatus.refund_success.getValue()%>";
	    var refund_failure = "<%=AfterSalesStatus.refund_failure.getValue()%>";
	    var complete = "<%=AfterSalesStatus.complete.getValue()%>";
	    var cancel = "<%=AfterSalesStatus.cancel.getValue()%>";
	</script> 
	<script type="text/html" id="traStatusTpl">
		{{#  if(d.tra_status == orderclosed){ }}  
         已取消
        {{#  } }}
		{{#  if(d.tra_status == unpaid){ }}  
        待付款 
        {{#  } }}
		{{#  if(d.tra_status == paid){ }}  
         待发货 
        {{#  } }}
		{{#  if(d.tra_status == shipped){ }}  
         已发货 
        {{#  } }}
		{{#  if(d.tra_status == received){ }}  
         已收货 
        {{#  } }}
		{{#  if(d.tra_status == review){ }}  
         已评价 
        {{#  } }}
		{{#  if(d.tra_status == review_two){ }}  
         已追评 
        {{#  } }}
	</script>
	<script type="text/html" id="payTypeTpl">
		{{#  if(d.pay_type == wechatPay){ }}  
         微信 
        {{#  } }}
		{{#  if(d.pay_type == aliPay){ }}  
        支付宝 
        {{#  } }}
		{{#  if(d.pay_type == easyLink){ }}  
         银联 
        {{#  } }}
		{{#  if(d.pay_type == visaPay){ }}  
    visa 
        {{#  } }}
		{{#  if(d.pay_type == '0'){ }}  
    --- 
        {{#  } }}
	</script>
	<script type="text/html" id="orderEmailTpl">
		{{#  if(d.orderEmail == ""){ }}  
         ----------
        {{#  } else{ }}
			{{  d.orderEmail }}
		{{#  } }}
	</script>
	<script type="text/html" id="logisticsAccountTpl">
		{{#  if(d.logisticsAccount == ""){ }}  
         ----------
        {{#  } else{ }}
			{{  d.logisticsAccount }}
		{{#  } }}
	</script>
	<script type="text/html" id="afterSaleApplyTpl">
       {{#  if(d.after_sale_apply == finance_paid){ }}  
         --------- 
       {{#  } }}
       {{#  if(d.after_sale_apply == apply_for){ }}  
          待申请
       {{#  } }}
       {{#  if(d.after_sale_apply == apply_for_ing){ }}  
<span style="color: #9900ff">申请中</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == service_success){ }}  
<span style="color: red">客服审核  成功</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == service_fail){ }}  
<span style="color: #0000EE">客服审核  失败</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == logistics_success){ }}  
<span style="color: red">物流审核 成功</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == logistics_fail){ }}  
<span style="color: #0000EE">物流审核 失败</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == finance_success){ }} 
<span style="color: red">退款 中</span> 
       {{#  } }}
       {{#  if(d.after_sale_apply == finance_fail){ }}  
<span style="color: #0000EE">财务审核 失败</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == refund_success){ }} 
<span style="color: red">退款成功</span> 
       {{#  } }}
       {{#  if(d.after_sale_apply == refund_failure){ }}  
<span style="color: #0000EE">退款失败</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == complete){ }}  
<span style="color: blue">已完成</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == cancel){ }}  
            已取消
       {{#  } }}
   </script>
</head>

<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
   		<!-- 头部导航部分 -->
        <%@ include file="../admin_common/header.jsp" %>
        
        <!-- 左侧导航区域 -->
        <%@ include file="orderNavigation.jsp" %>
        
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px;">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                    查看订单
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <!-- 这里用layui的数据表格的重载 -->
                    <form class="layui-form" action="">
                        <div class="layui-form-item mb-0" pane="">
                            <label class="layui-form-label f-14">搜索：</label>
                            <div class="layui-input-block">
                                <div class="layui-inline">
                                    <select name="searchabout" lay-verify="required" id="searchabout" lay-search="">
                                        <option value="">请选择您要查询的内容</option>
                                        <option value="1">订单号</option>
                                        <option value="2">运单号</option>
                                    </select>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="incontent" id="incontent" lay-verify="required" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <button type="submit" class="layui-btn layui-btn-sm" lay-submit="" lay-filter="search1">搜索</button>
                                    <button type="button" class="layui-btn layui-btn-sm btn-refresh">刷新</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <p class="f-18 pt-15 pb-15 mt-40 col-8d969d">
                    查看订单列表
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <table id="orderlist" lay-filter="test"></table>
                </div>
            </div>
        </div>
        
    <!-- (查看userinfo)============================== 的弹出层的基本内容，供js调用======================= -->
    <div class="detailcontent dis-n">
        <div class="clear pd-20 f-16">
            <div class="clear mb-20 h-40 line-h-40 text-center">
                <div class="clear dis-ib">
                    <img src="resources/img/admin/admin_comm/logov1.png" class="h-35 mt-3 pull-left">
                    <span class="f-28 ml-10 f-w family-s pull-left h-40 line-h-40">用户基本信息</span>
                </div>
            </div>
            <fieldset class="layui-elem-field">
                <legend>用户信息</legend>
                <div class="layui-field-box">
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">头像:</label>
                        	<img alt="头像" src="" class="uriPic" style="border-radius:50%;width:100px; height:100px;">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">名字:</label>
                        <div class="layui-input-block name line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">性别:</label>
                        <div class="layui-input-block sex line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">年龄:</label>
                        <div class="layui-input-block age line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">体重:</label>
                        <div class="layui-input-block weight line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">身高:</label>
                        <div class="layui-input-block height line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">生日:</label>
                        <div class="layui-input-block birthday line-h-35 f-16">
                        </div>
                    </div>
                </div>
            </fieldset>
        </div>
       	<!-- 底部固定区域 -->
    	<%@ include file="../admin_common/fooder.jsp" %>
    </div>
    
    <!-- (查看用户 购买的商品信息 )============================== 的弹出层的基本内容，供js调用======================= -->
    <div class="detailcontent1 dis-n">
        <div class="clear pd-20 f-16">
            <div class="clear mb-20 h-40 line-h-40 text-center">
                <div class="clear dis-ib">
                    <img src="resources/img/admin/admin_comm/logov1.png" class="h-35 mt-3 pull-left">
                    <span class="f-28 ml-10 f-w family-s pull-left h-40 line-h-40">商品基本信息</span>
                </div>
            </div>
            <fieldset class="layui-elem-field">
                <legend>商品信息</legend>
                <div class="layui-field-box">
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">商品内容:</label>
                        <div class="layui-input-block productName line-h-35 f-16">
                        </div>
                    </div>
                </div>
            </fieldset>
        </div>
    </div>
        
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_order/adminOrderView.js"></script>
</body>

</html>