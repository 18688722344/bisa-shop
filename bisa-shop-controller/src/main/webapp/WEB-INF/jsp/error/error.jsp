<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="name43"/></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/error.css" />
</head>
<body>
<div id="container">
	<div id = "error">
		<span><spring:message code="name44"/></span>
		<div id="message"><span class="errorContainer">${exception.message}</span></div>
		<div id="upPage"><a href="javascript:history.go(-1)"><spring:message code="name40"/></a></div>
	</div>
</div>
</body>
</html>