<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="initial-scale=0.2,user-scalable=yes">
	<!-- necessary -->
	<meta name="keywords" content="1,2,3">
	<meta name="description" content="">
	<!-- description -->
	<meta name="renderer" content="webkit">
	<title><spring:message code="name436"/></title>
	<!-- base -->
	<link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
	<!--[if lt IE 9]>
	  <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	  <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<%@ include file="../comm/header.jsp" %> 
	<div class="wrap">
		<div class="container text-center pt-60 pb-60">
			<img src="<%=request.getContextPath() %>/resources/img/solution/solution_banner.jpg" style="width: 45%;" alt="">
		</div>
	</div>
	<%@ include file="../comm/footer.jsp" %> 

<script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/solution/solution.js"></script>
</body>
</html>