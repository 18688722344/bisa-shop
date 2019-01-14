<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bisa.health.shop.enumerate.GoodsStatusEnum"%>
<%@ page import="com.bisa.health.shop.enumerate.NeedPostType"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	ResourceBundle resource = ResourceBundle.getBundle("resources");
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
    <title>碧沙康健_激活卡管理</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <script>
		var noPost = "<%=NeedPostType.no_post.getValue()%>";  
		var need_Post = "<%=NeedPostType.need_post.getValue()%>";
		
		var in_sale = "<%=GoodsStatusEnum.in_sale.getValue()%>";
		var invalid = "<%=GoodsStatusEnum.invalid.getValue()%>";
		var sold_out = "<%=GoodsStatusEnum.sale_out.getValue()%>";
		
		var shopUrl = "<%=resource.getString("access.allow.url") %>";
	</script>
    <!-- 操作 工具栏 -->
        <script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn layui-btn-sm" lay-event="" href="<%=resource.getString("access.allow.url") %>/health-shop/web/call/goodsDetail?goodsNumber={{ d.goodsNumber }}">预览</a>
			<a class="layui-btn layui-btn-warm layui-btn-sm" lay-event="detail">查看</a>

			<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="" href="admin/commodity/addCommodityPage?goodsNumber={{ d.goodsNumber }}">编辑</a>
			{{# if(d.parentId == 0){ }}
				<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="" href="admin/commodity/Goodscombo?goodsNumber={{ d.goodsNumber }}">套餐</a>
            {{# } }}
        </script>
    	<script type="text/html" id="commodityTypeTpl">
			{{# if(d.goodsStatus == in_sale){ }}
				<span style="color: blue">销售 中</span>
            {{# } }}
			{{# if(d.goodsStatus == invalid){ }}
				<span style="color: red">下架 了</span>
            {{#  } }}
			{{# if(d.goodsStatus == sold_out){ }}
				<span style="color: #9900cc">售完 了</span>
            {{#  } }}
		</script>
		<script type="text/html" id="postTpl">
            {{# if(d.needPost == noPost){ }}
                	否
            {{# }else{ }}
                	是
            {{#  } }}
    	</script>
		<script type="text/html" id="storeNumberTpl">
            {{# if(d.storeNumber < 100){ }}
                	<span style="color: red">{{ d.storeNumber }}</span>
            {{# }else{ }}
                	{{ d.storeNumber }}
            {{#  } }}
    	</script>
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
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
   		<!-- 头部导航部分 -->
        <%@ include file="../admin_common/header.jsp" %>
        <!-- 左侧导航区域 -->
        <%@ include file="orderNavigation.jsp" %>
        
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px;">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                    	查看激活卡
                </p>
  				   <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <!-- 这里用layui的数据表格的重载 -->
                    <form class="layui-form" action="">
                        <div class="layui-form-item mb-0" pane="">
                            <label class="layui-form-label f-14">搜索：</label>
                            <div class="layui-input-block">
                                <div class="layui-inline">
                                    <select name="searchabout" lay-verify="required" id="searchabout" lay-search="">
                                        <option value="">请选择您要查询的内容</option>
                                        <option value="1">请输入激活卡的ID</option>
                                    </select>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="incontent" id="incontent" lay-verify="required" autocomplete="off" class="layui-input">
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
                    	商品列表
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <table id="commodityList" lay-filter="test"></table>
                </div>
            </div>
        </div>
        <!-- 底部固定区域 -->
   		<%@ include file="../admin_common/fooder.jsp" %>
    </div>
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_order/adminCardRecord.js"></script>
</body>
</html>