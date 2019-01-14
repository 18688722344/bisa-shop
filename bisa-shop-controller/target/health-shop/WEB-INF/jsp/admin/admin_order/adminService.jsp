<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bisa.health.pay.enumerate.PayStatus"%>
<%@ page import="com.bisa.health.pay.enumerate.PayType"%>
<%@ page import="com.bisa.health.shop.enumerate.AfterSalesStatus"%>  
<%@ page import="com.bisa.health.shop.admin.enums.AfterSalesType"%>  
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
    <title>碧沙康健_客服管理</title>
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
	    var received = "<%=PayStatus.received.getValue()%>";  
	    var review = "<%=PayStatus.appraise_one.getValue()%>";  
	    var review_two = "<%=PayStatus.appraise_two.getValue()%>";  
	    
	    var wechatPay = "<%=PayType.wechat.getValue()%>";
	    var aliPay = "<%=PayType.alipay.getValue()%>";
	    var easyLink = "<%=PayType.easy_link.getValue()%>";
	    var visaPay = "<%=PayType.visa.getValue()%>";
	    
	    var apply_for_ing = "<%=AfterSalesStatus.apply_for_ing.getValue()%>";
	    var logistics_fail = "<%=AfterSalesStatus.logistics_fail.getValue()%>";
	    
	    var exchange = "<%=AfterSalesType.exchange.getValue()%>";
	    var maintain = "<%=AfterSalesType.maintain.getValue()%>";
	    var refund = "<%=AfterSalesType.refund.getValue()%>";
	</script>
	<script type="text/html" id="afterSaleApplyTpl">
       {{#  if(d.after_sale_apply == apply_for_ing){ }}  
			<span style="color: red">用户 申请中</span>
       {{#  } }}
       {{#  if(d.after_sale_apply == logistics_fail){ }}  
			<span style="color: #0000EE">物流审核 失败</span>
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
	</script>
	<script type="text/html" id="traStatusTpl">
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
	<script type="text/html" id="barDemo">
        {{#  if(d.after_sale_apply == apply_for_ing || d.after_sale_apply == logistics_fail){ }}	
            <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="detailASA">查看原因</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="check">待审核</a>
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
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
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
                <!-- layui数据 -->
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <table id="DeliverList" lay-filter="DLtest"></table>
                </div>
            </div>
        </div>
        
        <!-- 底部固定区域 -->
        <%@ include file="../admin_common/fooder.jsp" %>
    </div>
    
    <!-- (查看原因)=== ====的弹出层的基本内容，供js调用 ============================-->
    <div class="detailcontent1 dis-n">
        <div class="clear pd-20 f-16">
            <div class="clear mb-20 h-40 line-h-40 text-center">
                <div class="clear dis-ib">
                    <img src="resources/img/admin/admin_comm/logov1.png" class="h-35 mt-3 pull-left">
                    <span class="f-28 ml-10 f-w family-s pull-left h-40 line-h-40">用户售后基本信息</span>
                </div>
            </div>
            <fieldset class="layui-elem-field">
                <legend>申请售后原因信息</legend>
                <div class="layui-field-box">
                	<div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">头像:</label>
                        	<img alt="头像" src="" class="uriPic1" style="border-radius:50%;width:100px; height:100px;">
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">申请人:</label>
                        <div class="layui-input-block detail_consignee1 line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">申请订单:</label>
                        <div class="layui-input-block detail_orderNO line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">申请类型:</label>
                        <div class="layui-input-block detail_type line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">申请原因:</label>
                        <div class="layui-input-block detail_cause line-h-35 f-16">
                        </div>
                    </div>
                    <div class="layui-form-item mb-0">
                        <label class="layui-form-label pl-0 pr-0">申请时间:</label>
                        <div class="layui-input-block detail_time1 line-h-35 f-16">
                        </div>
                    </div>
                </div>
            </fieldset>
        </div>
    </div> 
    
    <!-- (查看用户 购买的商品信息 )============================== 的弹出层的基本内容，供js调用======================= -->
    <div class="detailcontent3 dis-n">
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
    
    <!-- 客服售后(待审核)的基本内容，供js调用 -->
    <div class="detailwaybill1 dis-n">
        <div class="clear pd-20 f-16">
            <p class="f-14 col-red mb-20 pl-20">
                温馨提示：审核操作要实事求是!
            </p>
            <form class="layui-form" action="">
                <input type="hidden" class="user_id" value="">
                
                <div class="clear mb-20 h-40">
                    <div class="clear w-120 pull-left text-right h-40 line-h-40">
                        订单号：
                    </div>
                    <div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10 detail_waybillnum1">
                    </div>
                </div>
                <div class="clear mb-20 h-40">
                	<div class="clear w-120 pull-left text-right h-40 line-h-40">
                        用户描述：
                    </div>
				    <div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10">
				      <input type="radio" name="description" value="1" title="符合" checked="">
				      <input type="radio" name="description" value="2" title="不符合">
				    </div>
				</div>
				<div class="layui-form-item layui-form-text">
					<div class="clear w-120 pull-left text-right h-40 line-h-40">
                        审核留言：
                    </div>
				    <div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10">
				      <textarea placeholder="请输入审核留言..." class="layui-textarea" name="textarea" lay-verify="required"></textarea>
				    </div>
				</div><br/><br/><br/>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-sm mr-20" lay-submit="" lay-filter="check_success">审核通过</button>
                        <button class="layui-btn layui-btn-sm layui-btn-danger mr-20" lay-submit="" lay-filter="check_fail">审核拒绝</button>
                    </div>
                </div>
            </form>
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
    </div>
    
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/js/comm/jQuery.print.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_order/adminService.js"></script>
</body>
</html>