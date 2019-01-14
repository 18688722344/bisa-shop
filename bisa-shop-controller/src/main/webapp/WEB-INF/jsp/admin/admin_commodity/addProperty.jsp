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
    <title>碧莎康健_新增属性</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <link href="resources/plugin/formSelects/formSelects-v4.css" rel="stylesheet" />
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
            <div style="padding: 30px 50px;">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                    	填写套餐详细（如果只有一个商品就是套餐的情况，写在主商品处）
                </p>
				<form class="layui-form" action="" lay-filter="">
				
				  <div class="layui-form-item">
				    <label class="layui-form-label">主商品</label>
				    <div class="layui-input-block" style="width: 200px;">
				      <select name="mainProduct" id="option1" lay-verify="required">
				      </select>
				    </div>
				  </div>
				  <div class="layui-form-item">
				      <label class="layui-form-label">套餐价格</label>
				      <div class="layui-input-block" style="width: 100px;">
				        <input type="number" name="mainProductCount" placeholder="￥" lay-verify="required" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  <div class="layui-form-item">
				      <label class="layui-form-label">套餐比例</label>
				      <div class="layui-input-block" style="width: 100px;">
				        <input type="number" name="mainProductNumber" placeholder="输入套餐比例" lay-verify="required" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <label class="layui-form-label">副商品</label>
				    <div class="layui-input-block" style="width: 200px;">
				      <select name="assistantProduct" id="option2">
				      </select>
				    </div>
				  </div>
				  <div class="layui-form-item">
				      <label class="layui-form-label">套餐价格</label>
				      <div class="layui-input-block" style="width: 100px;">
				        <input type="number" name="assistantProductCount" placeholder="￥" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  <div class="layui-form-item">
				      <label class="layui-form-label">套餐比例</label>
				      <div class="layui-input-block" style="width: 100px;">
				        <input type="number" name="assistantProductNumber" placeholder="输入套餐比例" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn" lay-submit="" lay-filter="demo1" id="demo1">确定</button>
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
    <script src="resources/js/admin/admin_commodity/addCombo.js"></script>
</body>
</html>