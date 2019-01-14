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
    <title>碧沙康健_管理人员列表</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delarticle">删除</a>
    </script>
</head>

<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!-- 头部导航部分 -->
        <%@ include file="../admin_common/header.jsp" %>1
        <!-- 左侧导航区域 -->
        <%@ include file="adminUserNavigation.jsp" %>
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px;">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                	搜索人员
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <!-- 这里用layui的数据表格的重载 -->
                    <form class="layui-form" action="">
                        <div class="layui-form-item mb-0" pane="">
                            <label class="layui-form-label f-14">搜索：</label>
                            <div class="layui-input-block">
                                <div class="layui-inline">
                                    <select name="searchabout" lay-verify="required" lay-search="">
                                        <option value="">请选择您要查询的内容</option>
                                        <option value="1">新闻标题</option>
                                        <option value="2">新闻ID</option>
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
                    	工作人员列表
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <table id="adminUserlist" lay-filter="test"></table>
                </div>
            </div>
        </div>
        
        <!-- 底部固定区域 -->
        <%@ include file="../admin_common/fooder.jsp" %>
    </div>

    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_user/adminUserList.js"></script>
</body>
</html>