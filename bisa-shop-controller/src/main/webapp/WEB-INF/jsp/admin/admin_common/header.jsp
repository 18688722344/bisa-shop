<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>

<!-- 头部导航部分 -->
<div class="layui-header">
	<div class="layui-logo pd-10 clear">
		<img src="resources/img/admin/admin_comm/logov1.png" class="full-w pull-left">
	</div>
	<ul class="layui-nav layui-layout-left">
		<li class="layui-nav-item"><a href="index">管理中心</a></li>
		<!-- 仅超级管理员可见 -->
		<shiro:hasAnyRoles name="ROLE_ADMIN" >
			<li class="layui-nav-item"><a href="admin/finance/tradeInfo">财务管理</a></li>
			<li class="layui-nav-item"><a href="admin/adminUserList">用户管理</a></li>
		</shiro:hasAnyRoles>
		
		<shiro:hasAnyRoles name="ROLE_ADMIN, ROLE_CUSTOMER, ROLE_STORE">
			<li class="layui-nav-item"><a href="admin/order/orderList">订单管理</a></li>
		</shiro:hasAnyRoles>
		
		<!-- 仅超级管理员和客服可见 -->
		<shiro:hasAnyRoles name="ROLE_ADMIN, ROLE_CUSTOMER" >
			<li class="layui-nav-item"><a href="admin/commodity/commodityView">商品管理</a></li>
			<li class="layui-nav-item"><a href="admin/news//listNews">新闻管理</a></li>
			<li class="layui-nav-item"><a href="admin/comment/commentList">评价留言</a></li>
		</shiro:hasAnyRoles>
		
	</ul>
	<ul class="layui-nav layui-layout-right">
		<li class="layui-nav-item"><a href="javascript:;"> <img src="resources/img/admin/admin_comm/admin-header.png" class="layui-nav-img">
				<shiro:principal/>
		</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="#">基本资料</a>
				</dd>
				<dd>
					<a href="#">安全设置</a>
				</dd>
			</dl></li>
		<li class="layui-nav-item"><a href="logout">退出</a></li>
	</ul>
</div>