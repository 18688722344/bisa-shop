<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bisa.health.pay.enumerate.PayType"%>    
<%@ page import="com.bisa.health.pay.enumerate.PayLocation"%>    
<%@ page import="com.bisa.health.shop.enumerate.AfterSalesStatus"%>    
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
   <base href="<%=request.getContextPath()%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title>碧沙康健_交易流水</title>
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
	    var wechatPay = "<%=PayType.wechat.getValue()%>";
	    var aliPay = "<%=PayType.alipay.getValue()%>";
	    var easyLink = "<%=PayType.easy_link.getValue()%>";
	    var visaPay = "<%=PayType.visa.getValue()%>";
	    
	    var android = "<%=PayLocation.android.getValue()%>";
	    var ios = "<%=PayLocation.ios.getValue()%>";
	    var pc = "<%=PayLocation.pc.getValue()%>";
	    var web = "<%=PayLocation.web.getValue()%>";
	    
	    var paid = "<%=AfterSalesStatus.paid.getValue()%>";
	    var logistics_success = "<%=AfterSalesStatus.logistics_success.getValue()%>";
	    var finance_success = "<%=AfterSalesStatus.finance_success.getValue()%>";
	    var finance_fail = "<%=AfterSalesStatus.finance_fail.getValue()%>";
	    var refund_success = "<%=AfterSalesStatus.refund_success.getValue()%>";
	    var refund_failure = "<%=AfterSalesStatus.refund_failure.getValue()%>";
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
                    	查询交易流水
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <!-- 这里用layui的数据表格的重载 -->
                    <form class="layui-form" action="">
                        <div class="layui-form-item mb-0" pane="">
                            <label class="layui-form-label f-14">查询：</label>
                            <div class="layui-input-block">
                                <div class="layui-inline">
                                    <select name="searchabout" lay-verify="required" lay-filter="sle_type_f">
                                        <option value="">请选择您要查询的内容</option>
                                        <option value="1">交易号</option>
                                        <option value="2" selected="selected">订单号</option>
                                        <option value="3">按年查询</option>
                                        <option value="4">按年-月查询</option>
                                        <option value="5">按年-月-日查询</option>
                                    </select>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <!-- 交易号 -->
                                        <input id="ipt_transnum" type="text" name="n_transnum" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value" placeholder="请输入您要查询的交易号">
                                        <!-- 订单号 -->
                                        <input id="ipt_orderno" type="text" name="n_orderno" lay-verify="" autocomplete="off" class="layui-input sel_value" placeholder="请输入您要查询的订单号">
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
                        <input type="hidden" id="dataValue" value="${data}">
                    </form>
                </div>
                <p class="f-18 pt-15 pb-15 mt-40 col-8d969d">
                   	交易流水
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <table id="tradelist" lay-filter="tradelist_filter"></table>
                </div>
            </div>
        </div>
         <!-- 底部固定区域 -->
    	<%@ include file="../admin_common/fooder.jsp" %>
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
    
    <script type="text/html" id="pay_typeTpl">
       {{#  if(d.pay_type == wechatPay){ }}  
       微信
       {{#  } }}
       {{#  if(d.pay_type == aliPay){ }}  
        支付宝
       {{#  } }}
       {{#  if(d.pay_type == visaPay){ }}  
   visa
       {{#  } }}
       {{#  if(d.pay_type == easyLink){ }}  
          银联
       {{#  } }}
   </script>
   <script type="text/html" id="pay_statusTpl">
        {{#  if(d.pay_status == paid){ }}  
    <span style="color: blue">已付款</span>
       {{#  } }}
        {{#  if(d.pay_status == logistics_success){ }}  
    <span style="color: red">物流 审核通过</span>
       {{#  } }}
        {{#  if(d.pay_status == finance_success){ }}  
    <span style="color: red">退款 中</span>
       {{#  } }}
       {{#  if(d.pay_status == finance_fail){ }}  
    <span style="color: #000000">财务 审核失败</span>
       {{#  } }}
       {{#  if(d.pay_status == refund_success){ }}  
    <span style="color: red">退款成功</span>
       {{#  } }}
       {{#  if(d.pay_status == refund_failure){ }}  
    <span style="color: #000000">退款失败</span>
       {{#  } }}
   </script>
   <script type="text/html" id="pay_locationTpl">
       {{#  if(d.pay_location == android){ }}  
        安卓 
       {{#  } }}
       {{#  if(d.pay_location == ios){ }}  
   IOS
       {{#  } }}
       {{#  if(d.pay_location == web){ }}  
        电脑网页端
       {{#  } }}
       {{#  if(d.pay_location == pc){ }}  
        电脑PC端
       {{#  } }}
   </script>
   
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_finance/tradeInfo.js"></script>
</body>
</html>