<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bisa.health.shop.admin.enums.LeaveMsgTpye"%> 
<%@ page import="com.bisa.health.shop.admin.enums.HandleWay"%> 
<%@ page import="com.bisa.health.shop.admin.enums.HandleStatus"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
    <title>碧沙康健_留言管理</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <script>
		var none1 = "<%=LeaveMsgTpye.none.getValue()%>";  
		var product_consult = "<%=LeaveMsgTpye.product_consult.getValue()%>";  
		var service_consult = "<%=LeaveMsgTpye.service_consult.getValue()%>";  
		var after_sale = "<%=LeaveMsgTpye.after_sale.getValue()%>";  
		var account_abnormal = "<%=LeaveMsgTpye.account_abnormal.getValue()%>";  
		var other = "<%=LeaveMsgTpye.other.getValue()%>";  

		var none2 = "<%=HandleWay.none.getValue()%>";  
		var email = "<%=HandleWay.email.getValue()%>";  
		var note = "<%=HandleWay.note.getValue()%>";  
		var call = "<%=HandleWay.call.getValue()%>";  

		var pending = "<%=HandleStatus.pending.getValue()%>";  
		var figured_out = "<%=HandleStatus.figured_out.getValue()%>";  
	</script>
	<script type="text/html" id="barDemo">
		{{#  if(d.handle_status == pending){ }}  
<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="pending">待处理</a>
<a class="layui-btn layui-btn-xs" lay-event="detail">查看详细</a>
        {{#  } }}
		{{#  if(d.handle_status == figured_out){ }}  
<a class="layui-btn layui-btn-xs layui-btn-disabled">已处理</a>
<a class="layui-btn layui-btn-xs" lay-event="detail">查看详细</a>
        {{#  } }}
    </script>
	<script type="text/html" id="handle_status">
       {{#  if(d.handle_status == pending){ }}  
<span style="color: red">待处理</span>
       {{#  } }}
       {{#  if(d.handle_status == figured_out){ }}  
处理完毕
       {{#  } }}
	</script>
	<script type="text/html" id="handle_way">
       {{#  if(d.handle_way == none2){ }}  
<span style="color: red">还未处理</span>
       {{#  } }}
       {{#  if(d.handle_way == email){ }}  
邮箱
       {{#  } }}
       {{#  if(d.handle_way == note){ }}  
短信
       {{#  } }}
       {{#  if(d.handle_way == call){ }}  
通话
       {{#  } }}
	</script>
    <script type="text/html" id="message_type">
       {{#  if(d.message_type == none1){ }}  
<span style="color: red">留言还没有处理</span>
       {{#  } }}
       {{#  if(d.message_type == product_consult){ }}  
商品咨询
       {{#  } }}
       {{#  if(d.message_type == service_consult){ }}  
服务咨询
       {{#  } }}
       {{#  if(d.message_type == after_sale){ }}  
售后咨询
       {{#  } }}
       {{#  if(d.message_type == account_abnormal){ }}  
账户异常
       {{#  } }}
       {{#  if(d.message_type == other){ }}  
其他
       {{#  } }}
	</script>
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!-- 头部导航部分 -->
        <%@ include file="../admin_common/header.jsp" %>
        <!-- 左侧导航区域 -->
        <%@ include file="commentNavigation.jsp" %>
        
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px;">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                    搜索留言
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <!-- 这里用layui的数据表格的重载 -->
                    <form class="layui-form" action="">
                        <div class="layui-form-item" pane="">
                            <label class="layui-form-label f-14">筛选：</label>
                            <div class="layui-input-block">
                                <button type="button" value="0" class="layui-btn layui-btn-sm btn-sidebar">全部</button>
                                <button type="button" value="1" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">待处理</button>
                                <button type="button" value="2" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">已处理</button>
                            </div>
                        </div>
                        <div class="layui-form-item mb-0" pane="">
                            <label class="layui-form-label f-14">搜索：</label>
                            <div class="layui-input-block">
                                <div class="layui-inline">
                                    <select name="searchabout" lay-verify="required" lay-search="">
                                        <option value="1" selected="selected">姓名</option>
                                        <option value="2">电话</option>
                                        <option value="3">邮箱</option>
                                    </select>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="incontent" lay-verify="required" autocomplete="off" class="layui-input">
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
                    留言列表
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <table id="commentlist" lay-filter="test"></table>
                </div>
            </div>
        </div>
        
    <!-- 留言列表的（待处理），供js调用 -->
    <div class="detailwaybill1 dis-n">
        <div class="clear pd-20 f-16">
        	 <div class="clear mb-20 h-40 line-h-40 text-center">
                <div class="clear dis-ib">
                    <img src="resources/img/admin/admin_comm/logov1.png" class="h-35 mt-3 pull-left">
                    <span class="f-28 ml-10 f-w family-s pull-left h-40 line-h-40">留言基本信息</span>
                </div>
            </div>
             <fieldset class="layui-elem-field">
	                <legend>留言基本信息</legend>
	                <div class="layui-field-box">
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">留言人:</label>
	                        <div class="layui-input-block name line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">电话:</label>
	                        <div class="layui-input-block phone line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">邮箱:</label>
	                        <div class="layui-input-block mail line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">留言内容:</label>
	                        <div class="layui-input-block message line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">留言时间:</label>
	                        <div class="layui-input-block message_time line-h-35 f-16">
	                        </div>
	                    </div>
	                </div>
	            </fieldset>
	        <fieldset class="layui-elem-field">
	       		<legend>处理留言</legend>
	            <form class="layui-form" action="">
	            <div class="layui-field-box">
	                <input type="hidden" id="guestbookId" value="">
	                
	                <div class="layui-form-item mb-0">
					    <label class="layui-form-label pl-0 pr-0">留言类型：</label>
					    <div class="layui-input-block">
					      <select name="LeaveMsgTpye" lay-filter="">
					        <option value="" selected=""></option>
					        <option value="1">商品咨询</option>
					        <option value="2">服务咨询</option>
					        <option value="3">售后咨询</option>
					        <option value="4">账户异常</option>
					        <option value="5">其他</option>
					      </select>
					    </div>
					</div><br/>
	                <div class="layui-form-item mb-0">
					    <label class="layui-form-label pl-0 pr-0">处理方式：</label>
					    <div class="layui-input-block">
					      <select name="HandleWay" lay-filter="">
					        <option value="" selected=""></option>
					        <option value="1">邮箱</option>
					        <option value="2">短信</option>
					        <option value="3">通话</option>
					      </select>
					    </div>
					</div><br/>
					<div class="layui-form-item mb-0">
				    	<label class="layui-form-label pl-0 pr-0">回复内容：</label>
				    	<div class="layui-input-block">
				      		<textarea placeholder="请输入回复内容..." class="layui-textarea" name="textarea" lay-verify="required"></textarea>
				   	 	</div>
					</div>
					<br/>
	                <div class="layui-form-item">
	                    <div class="text-right">
	                        <button class="layui-btn layui-btn-sm mr-20" lay-submit="" lay-filter="pending">处理留言</button>
	                    </div>
	                </div>
	                </div>
	            </form>
            </fieldset>
        </div>
    </div>
        
    <!-- 底部固定区域 -->
    <%@ include file="../admin_common/fooder.jsp" %>
    </div>
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_comment/HK_AdminMsg.js"></script>
</body>
</html>