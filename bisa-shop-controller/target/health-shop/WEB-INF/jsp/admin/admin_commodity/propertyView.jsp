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
    <title>碧沙康健_属性列表</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/html" id="propertyBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
 		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="showValue">属性值</a>

    </script>
    <script type="text/html" id="valueBar">
        <a class="layui-btn layui-btn-xs" lay-event="editValue">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delValue">删除</a>
    </script>
</head>

<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!-- 头部导航部分 -->
        <%@ include file="../admin_common/header.jsp" %>
        <!-- 左侧导航区域 -->
        <%@ include file="commodityNavigation.jsp" %>
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px;">
				 <p class="f-20 pt-15 pb-15 col-8d969d">
               		类别：${classifyName}
                </p>
				<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
				  <legend>商品属性及属性值，用于商品筛选页面，目前商城尚无该页面，所以无需维护。2018.9.25</legend>
				</fieldset>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 w-450 pull-left mt-40 mr-50">
	                <p class="f-18 pt-15 pb-15 col-8d969d">
	                   	 属性列表
	                </p>
	                <button type="button" id="addPropertyBtn" class="layui-btn layui-btn-sm">添加属性</button>
                    <table id="propertyList" lay-filter="propertyTable"></table>
                </div>
                
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 mt-40 w-450 pull-left property-value-div dis-n">
                 	<p class="f-18 pt-15 pb-15 col-8d969d">
	                   	 属性值
	                </p>
	          		<button type="button" id="addPropertyValueBtn" class="layui-btn layui-btn-sm">添加属性值</button> 
                    <table id="propertyValueList" lay-filter="valueTable"></table>
                </div>
                <input type="hidden" id="classifyId" value="${classifyId}" />
                <input type="hidden" id="propertyId" value=""/>
            </div>
        </div>
        
        <!-- 底部固定区域 -->
        <%@ include file="../admin_common/fooder.jsp" %>
    </div>
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_commodity/propertyView.js"></script>
</body>
</html>