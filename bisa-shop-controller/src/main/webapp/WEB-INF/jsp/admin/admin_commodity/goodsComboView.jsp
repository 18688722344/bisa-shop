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
    <title>碧沙康健_商品套餐</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
	.layui-table-cell{
		height: 100%;
    	max-width: 100%;
	}
	.laytable-cell-1-11{
		height: 100%;
    	max-width: 100%;
	}
	</style>
	<script type="text/html" id="barDemo">
 	 	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	<script type="text/html" id="imgTpl">
            {{# if(d.imgUrl != null){ }}
                	<div><img src='{{ d.imgUrl}}'></div>
            {{#  } }}
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
            <div style="padding: 30px 50px">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                  	  商品套餐
                </p>
           
                <button type="button" id="addComboBtn" class="layui-btn layui-btn-sm" >添加套餐</button>
                
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200" >
                    <table id="commodityList" lay-filter="test"></table>
                </div>
            </div>
        </div>
               <!-- (查看goods基本信息)============================== 的弹出层的基本内容，供js调用======================= -->
	    <div class="detailcontent dis-n">
	        <div class="clear pd-20 f-16">
	            <div class="clear mb-20 h-40 line-h-40 text-center">
	                <div class="clear dis-ib">
	                    <span class="f-28 ml-10 f-w family-s pull-left h-40 line-h-40">商品基本信息</span>
	                </div>
	            </div>
	            <fieldset class="layui-elem-field">
	                <legend>商品套餐基本信息</legend>
	                <div class="layui-field-box">
	                   
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品名称:</label>
	                        <div class="layui-input-block goodsName line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">原价:</label>
	                        <div class="layui-input-block goodsPrice line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">套餐价格:</label>
	                        <div class="layui-input-block discountPrice line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品编号:</label>
	                        <div class="layui-input-block goodsNumber line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">套餐编号:</label>
	                        <div class="layui-input-block comboGuid line-h-35 f-16">
	                        </div>
	                    </div>
	                </div>
	            </fieldset>
	        </div>
	    </div>
        <!-- 底部固定区域 -->
        <%@ include file="../admin_common/fooder.jsp" %>
    </div>
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_commodity/goodsComboView.js"></script>
</body>
</html>