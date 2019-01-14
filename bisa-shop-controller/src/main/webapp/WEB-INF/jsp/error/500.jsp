<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name42"/></title>
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/comm/base.css"/>
    <style>
        .col-ce9001{color:#ce9001;}
        .col-71a4b5{color: #71a4b5;}
        .bor-col-71a4b5{border-color: #71a4b5;}
        .back-index:hover{background-color:#71a4b5 }
        .back-index:hover > span{color: white;}
    </style>
</head>
<body>
    <div class="container clear text-center" style="padding-top: 10%;">
        <div class="clear dis-ib">
            <div class="clear clear text-left pull-left mr-40">
                <p class="mb-30 col-ce9001" style="font-size: 100px;">
                    500
                </p>
                <p class="f-28 f-w mb-40 col-71a4b5">
                    <spring:message code="name37"/>
                </p>
                <p class="f-26 mb-20 col-71a4b5">
                    <spring:message code="name38"/>
                </p>
                <p class="f-26 col-71a4b5">
                    <spring:message code="name39"/>
                </p>
                <p class="f-26 mb-20 col-71a4b5">
                    ${messege == null?"请求服务器出错": messege }
                </p>
                <div class="clear full-w pd-5">
                    <!-- 返回上一页的事件 -->
                    <div class="clear full-w bor bor-2px bor-col-71a4b5 h-50 pt-15 pb-15 pl-15 pr-10 cur-p back-index" onclick="window.history.go(-1);">
                        <span class="f-18 col-71a4b5 f-w pos-r t--3"><spring:message code="name40"/></span>
                        <span class="pull-right f-w f-22 pos-r t--3 col-71a4b5  icon-angle-right"></span>
                    </div>
                </div>
                <div class="clear full-w pd-5">
                    <!-- 返回首页的事件 -->
                    <div class="clear full-w bor bor-2px bor-col-71a4b5 h-50 pt-15 pb-15 pl-15 pr-10 cur-p back-index" onClick="window.location.href='<%=request.getContextPath()%>/index';">
                        <span class="f-18 col-71a4b5 f-w pos-r t--3"><spring:message code="name41"/></span>
                        <span class="pull-right f-w f-22 pos-r t--3 col-71a4b5  icon-angle-right"></span>
                    </div>
                </div>
            </div>
            <div class="clear pull-left" style="height: 500px;">
                <img style="height: 500px;" src="<%=request.getContextPath() %>/resources/img/error/show_error.png" alt="">
            </div>
        </div>
    </div>
</body>
</html>