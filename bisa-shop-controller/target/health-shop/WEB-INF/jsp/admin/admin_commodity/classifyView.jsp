<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>碧沙康健_类别管理</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <link href="design/css/layui.css" rel="stylesheet">
    <script type="text/javascript">
    	var requestUrl = "<%=request.getContextPath()%>";
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
        <%@ include file="commodityNavigation.jsp" %>
        
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px; height: 800px;">
                <p class="f-20 pt-15 pb-15 col-8d969d">
               		类别管理
                </p>
                 <fieldset class="layui-elem-field layui-field-title f-10" style="margin-top: 10px;">
				  <legend>为不影响系统正常运行，修改虚拟商品的类别之前，请咨询开发者！</legend>
				</fieldset>
				<fieldset class="layui-elem-field layui-field-title f-10" style="margin-top: 5px;">
				  <legend>为了商品的正常添加，请为父级分类添加子级分类！若无，可添加同名子级分类。</legend>
				</fieldset>
				<a class="layui-btn layui-btn-normal" id="add">新增一级类别</a>
				<table class="layui-hidden" id="treeTable" lay-filter="treeTable"></table>
            </div>
        </div>
        
    <!-- 底部固定区域 -->
    <%@ include file="../admin_common/fooder.jsp" %>
    </div>
    
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="design/layui.js"></script>
    <script src="resources/js/admin/admin_commodity/classifyView.js"></script>
</body>
</html>