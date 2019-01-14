<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bisa.health.shop.enumerate.GoodsStatusEnum"%>
<%@ page import="com.bisa.health.shop.enumerate.NeedPostType"%>  
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
    <title>碧沙康健_新增角色</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <link href="resources/plugin/formSelects/formSelects-v4.css" rel="stylesheet" />
    <link rel="stylesheet" href="resources/ctrl/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="resources/ctrl/ztree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="resources/ctrl/ztree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="resources/ctrl/ztree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="resources/js/admin/admin_user/addAdminRole.js"></script>
    <script>
		var noPost = "<%=NeedPostType.no_post.getValue()%>";  
		var need_Post = "<%=NeedPostType.need_post.getValue()%>";
		
		var in_sale = "<%=GoodsStatusEnum.in_sale.getValue()%>";
		var invalid = "<%=GoodsStatusEnum.invalid.getValue()%>";
		var sale_out = "<%=GoodsStatusEnum.sale_out.getValue()%>";
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
        <%@ include file="adminUserNavigation.jsp" %>
        
        <!-- 内容主体区域  authtree-->
        <div class="layui-body">
				 <div class="layui-container">
					<div class="layui-row">
						<div class="layui-col-md6 layui-col-md-offset1">
							<fieldset class="layui-elem-field layui-field-title mt-40"><legend>增加角色</legend></fieldset>
							<form class="layui-form">
								<div class="layui-form-item">
									<label class="layui-form-label">角色名称</label>
									<div class="layui-input-block">
										<input class="layui-input" type="text" name="name" placeholder="请输入角色名称" />
									</div>
								</div>
								<label class="layui-form-label">选择权限</label>
								<div class="layui-form-item pl-50 ml-50">
									<div class="zTreeDemoBackground left">
										<ul id="treeDemo" class="ztree"></ul>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="submit">提交</button>
										<button class="layui-btn layui-btn-primary" type="reset">重置</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
        </div>
        
        <!-- 底部固定区域 -->
        <%@ include file="../admin_common/fooder.jsp" %>
    </div>
    <script src="resources/ctrl/layui/layui.js"></script>
</body>
</html>