<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>碧沙康健_备注信息</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
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
        
        
        <c:set var="pending" value="<%=HandleStatus.pending.getValue()%>"></c:set>
        <c:set var="figured_out" value="<%=HandleStatus.figured_out.getValue()%>"></c:set>
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px;">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                    留言信息
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <div class="layui-row f-16">
                        <div class="layui-col-xs3 pl-10 pr-10">
                            <div class="grid-demo line-h-25 pt-10 pb-10 col-333">
                                ID：<span class="ml-5">${guestbook.id }</span>
                            </div>
                        </div>
                        <div class="layui-col-xs3 pl-10 pr-10">
                            <div class="grid-demo line-h-25 pt-10 pb-10 col-333">
                                姓名：<span class="ml-5">${guestbook.name }</span>
                            </div>
                        </div>
                        <div class="layui-col-xs3 pl-10 pr-10">
                            <div class="grid-demo line-h-25 pt-10 pb-10 col-333">
                                电话：<span class="ml-5">${guestbook.phone }</span>
                            </div>
                        </div>
                        <div class="layui-col-xs3 pl-10 pr-10">
                            <div class="grid-demo line-h-25 pt-10 pb-10 col-333">
                                邮箱：<span class="ml-5">${guestbook.mail }</span>
                            </div>
                        </div>
                        <div class="layui-col-xs3 pl-10 pr-10">
                            <div class="grid-demo line-h-25 pt-10 pb-10 col-333">
                                留言内容：<span class="ml-5">${guestbook.message }</span>
                            </div>
                        </div>
                        <div class="layui-col-xs3 pl-10 pr-10">
                            <div class="grid-demo line-h-25 pt-10 pb-10 col-333">
                                留言时间：<span class="ml-5">${guestbook.message_time }</span>
                            </div>
                        </div>
                        <div class="layui-col-xs3 pl-10 pr-10">
                            <div class="grid-demo line-h-25 pt-10 pb-10 col-333">
                                处理状态：<span class="ml-5">
	                     <c:if test="${guestbook.handle_status eq pending}">待处理</c:if>           
	                     <c:if test="${guestbook.handle_status eq figured_out}">处理完毕</c:if>           
                     </span>
                            </div>
                        </div>
                    </div>
                </div>
                <p class="f-18 pt-15 pb-15 mt-40 col-8d969d">
                    备注列表<button class="layui-btn layui-btn-normal pull-right dis-ib pos-r t--5 layui-btn-sm addremark">添加备注</button>
                </p>
                
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <table id="remarklist" lay-filter="test"></table>
                </div>
            </div>
        </div>
        
        <!-- 添加备注的html，供js调用 -->
	    <div class="detailwaybill dis-n">
	        <div class="clear pd-20 f-16">
	        	<div class="clear mb-20 h-40 line-h-40 text-center">
	                <div class="clear dis-ib">
	                    <img src="resources/img/admin/admin_comm/logov1.png" class="h-35 mt-3 pull-left">
	                    <span class="f-28 ml-10 f-w family-s pull-left h-40 line-h-40">留言备注</span>
	                </div>
            	</div>
	            <form class="layui-form" action="">
	                <input type="hidden" class="guestbookId" value="${guestbook.id }">
	                
	                <div class="clear mb-20 h-40">
	                    <div class="clear w-120 pull-left text-right h-40 line-h-40">
	                        留言人：
	                    </div>
	                    <div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10 name">
	                    ${guestbook.name }
	                    </div>
	                </div>
	                
	                <div class="clear mb-20 h-40">
	                    <div class="clear w-120 pull-left text-right h-40 line-h-40">
	                        留言内容：
	                    </div>
	                    <div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10 message">
	                    ${guestbook.message }
	                    </div>
	                </div>
	                <div class="clear mb-20 h-40">
	                	<div class="clear w-120 pull-left text-right h-40 line-h-40">
	                       备注信息：
	                    </div>
				    	<div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10 message">
				      		<textarea placeholder="请输入备注信息..." class="layui-textarea" name="textarea" lay-verify="required"></textarea>
				   	 	</div>
					</div>
					<br/><br/><br/><br/><br/>
	                <div class="layui-form-item">
					    <div class="layui-input-block">
					      <button class="layui-btn" lay-submit="" lay-filter="search1">立即提交</button>
					      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					    </div>
					</div>
	            </form>
	        </div>
	    </div>
        
        <!-- 底部固定区域 -->
        <%@ include file="../admin_common/fooder.jsp" %>
    </div>
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_comment/HK_AdminMsgDetail.js"></script>
</body>
</html>