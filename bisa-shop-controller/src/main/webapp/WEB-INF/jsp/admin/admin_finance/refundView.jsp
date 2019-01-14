<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bisa.health.pay.enumerate.PayStatus"%>
<%@ page import="com.bisa.health.shop.enumerate.AfterSalesStatus"%>  
<%@ page import="com.bisa.health.shop.admin.enums.AfterSalesType"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <base href="<%=request.getContextPath()%>">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title>碧沙康健_退款管理</title>
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
	    var logistics_success = "<%=AfterSalesStatus.logistics_success.getValue()%>";
	    var finance_success = "<%=AfterSalesStatus.finance_success.getValue()%>";
	    var refund_success = "<%=AfterSalesStatus.refund_success.getValue()%>";
	    var refund_failure = "<%=AfterSalesStatus.refund_failure.getValue()%>";
	    
	    var exchange = "<%=AfterSalesType.exchange.getValue()%>";
	    var maintain = "<%=AfterSalesType.maintain.getValue()%>";
	    var refund = "<%=AfterSalesType.refund.getValue()%>";
	</script>
	<script type="text/html" id="statusTpl">
        {{#  if(d.pay_status == logistics_success){ }}  
    <span style="color: #9900ff">物流审核 成功</span>
       {{#  } }}
        {{#  if(d.pay_status == finance_success){ }}  
    <span style="color: #9900ff">退款 中</span>
       {{#  } }}
       {{#  if(d.pay_status == refund_success){ }}  
    <span style="color: red">退款 成功</span>
       {{#  } }}
       {{#  if(d.pay_status == refund_failure){ }}  
    <span style="color: #0000EE">退款 失败</span>
       {{#  } }}
    </script>
    <script type="text/html" id="barDemo">
         {{#  if(d.pay_status == logistics_success){ }}  
		   <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="detailASA">查看原因</a>
           <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="check">退款审核</a>
         {{# }}}    
         {{#  if(d.pay_status == refund_failure){ }}  
		   <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="detailASA">查看原因</a>
           <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="check">退款审核</a>
         {{# }}}    
         {{#  if(d.pay_status == refund_success){ }}  
               已完成退款
         {{# }}} 
         {{#  if(d.pay_status == finance_success){ }}  
               等待系统退款
         {{# }}} 
    </script>
</head>

<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!-- 头部导航部分 -->
        <%@ include file="../admin_common/header.jsp" %>
        <!-- 左侧导航区域 -->
        <%@ include file="financeNavigation.jsp" %>
        
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div class="pd-50">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                    	查询退款信息
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <!-- 这里用layui的数据表格的重载 -->
                    <form class="layui-form" action="">
                    	<div class="layui-form-item" pane="">
                            <label class="layui-form-label f-14">分类：</label>
                            <div class="layui-input-block">
                                <button type="button" value="0" class="layui-btn layui-btn-sm btn-sidebar ">全部</button>
                                <button type="button" value="1" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">已退款</button>
                                <button type="button" value="2" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">待审核</button>
                                <button type="button" value="3" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">退款中</button>
                            </div>
                        </div>
                        <div class="layui-form-item mb-0" pane="">
                            <label class="layui-form-label f-14">查询：</label>
                            <div class="layui-input-block">
                                <div class="layui-inline">
                                    <select name="searchabout" lay-verify="required" lay-filter="sle_type_f">
                                        <option value="">请选择您要查询的内容</option>
                                        <option value="1" selected="selected">交易号</option>
                                        <option value="2">按年查询</option>
                                        <option value="3">按年-月查询</option>
                                        <option value="4">按年-月-日查询</option>
                                    </select>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <!-- 交易号 -->
                                        <input id="ipt_transnum" type="text" name="n_transnum" lay-verify="required" autocomplete="off" class="layui-input sel_value" placeholder="请输入您要查询的交易号">
                                        <!-- 年份 -->
                                        <input id="ipt_year" type="text" name="n_year" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的年份" readonly="readonly">
                                        <!-- 月份 -->
                                        <input id="ipt_month" type="text" name="n_month" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的月份" readonly="readonly">
                                        <!-- 日期 -->
                                        <input id="ipt_day" type="text" name="n_day" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的日期" readonly="readonly">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <button type="submit" class="layui-btn layui-btn-sm" lay-submit="" lay-filter="search1">查询</button>
                                    <button type="button" class="layui-btn layui-btn-sm btn-refresh">刷新</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <p class="f-18 pt-15 pb-15 mt-40 col-8d969d">
                    退款信息列表
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <table id="tradelist" lay-filter="tradelist_filter"></table>
                </div>
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
        
    <!-- (查看userinfo)============================== 的弹出层的基本内容，供js调用======================= -->
    <div class="detailcontent2 dis-n">
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
	    
    	<!-- 修改运单信息的(待审核)的基本内容，供js调用 -->
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
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_finance/refundView.js"></script>
</body>
</html>