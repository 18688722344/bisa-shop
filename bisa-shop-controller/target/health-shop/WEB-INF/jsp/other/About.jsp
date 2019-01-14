<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=0.35,user-scalable=yes">
<!-- necessary -->
<meta name="keywords" content="1,2,3">
<meta name="description" content="">
<!-- description -->
<meta name="renderer" content="webkit">
<title><spring:message code="name278"/></title>
<!-- base -->
<link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/comm/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/about/HK_About.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
<!--[if lt IE 9]>
  <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
	var http_request ="<%=request.getContextPath() %>";
	var request_url ="<%=request.getScheme()%>";
</script>
</head>

<body>
	<%@ include file="../comm/header.jsp" %> 
 	<div class="wrap">
        <div class="clear container">
            <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d">
                <a class="col-666 t-nonehove hovecol-309DE2" id="index" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/></a>
                >
                <span class="col-252525 t-nonehove"><spring:message code="about"/></span>
            </p>
        </div>
        <div class="clear full-ws">
            <img class="full-w" src="<%=request.getContextPath() %>/resources/img/HK_About/banner.jpg" alt="">
            <p class="full-w-p"><spring:message code="name775"/></p>
            <p class="full-w-p-1"><spring:message code="name776"/></p>
            <p class="full-w-p-2">OBOUT BISA</p>
        </div>
        <div class="clear container pl-0 pr-0 pl-100 pr-100 pb-100 pt-60">
            <p class="f-40 col-252525 h-70 line-h-70">
                BISA<span class="col-252525 ml-20 mr-20 pos-r t--2">|</span><spring:message code="about"/>
            </p>
            <p class="f-34 col-252525 pl-50 mt-20 mb-20 h-40 line-h-40 eng-h">
                <spring:message code="name279"/>
            </p>
            <div class="clear full-w pb-30">
                <div class="clear col-sm-6 pl-25 pr-25">
                    <div class="clear pt-20 bor bor-t bor-col-dcdcdc">
                        <p class="col-8c918c f-18 text-ind line-h-30">
                    		<spring:message code="name280"/></p>
                        <p class="col-8c918c f-18 text-ind line-h-30">
                            <spring:message code="name281"/>
                        </p>
                    </div>
                </div>
                <div class="clear col-sm-6 pl-25 pr-25">
                    <img class="full-w" src="<%=request.getContextPath() %>/resources/img/HK_About/About-rightv1.png" alt="<spring:message code="name282"/>">
                </div>
            </div>
            <p class="f-34 col-252525 pl-50 mt-20 mb-20 h-40 line-h-40">
                <spring:message code="name283"/>
            </p>
            <div class="clear full-w">
                <div class="clear col-sm-6 pl-25">
                    <div class="clear pt-20 bor bor-t bor-col-dcdcdc">
                        <div class="clear full-w ">
                            <div class="col-sm-3 pl-0 pr-0">
                                <span class="col-aaa f-22 h-25 line-h-25">
                                    <spring:message code="name284"/>
                                </span>
                            </div>
                            <div class="col-sm-9 pl-5 pr-0 pos-r bb">
                                <div class="clear w-100 pos-a t-10 l-15 bor bor-b bor-col-8c918c ">
                                </div>
                                <p class="col-aaa f-18 line-h-25 h-25 mt-20 ft-r">
                                    <spring:message code="name285"/>
                                </p>
                            </div>
                        </div>
                        <div class="clear full-w mgt">
                            <div class="col-sm-3 pl-0 pr-0">
                                <span class="col-aaa f-22 h-25 line-h-25">
                                    <spring:message code="name286"/>
                                </span>
                            </div>
                            <div class="col-sm-9 pl-5 pr-0 pos-r">
                                <div class="clear w-100 pos-a t-10 l-15 bor bor-b bor-col-8c918c">
                                </div>
                                <p class="col-aaa f-18 line-h-25 h-25 mt-20 ft-r">
                                    <spring:message code="name287"/>
                                </p>
                            </div>
                        </div>
                        <div class="clear full-w mgt">
                            <div class="col-sm-3 pl-0 pr-0">
                                <span class="col-aaa f-22 h-25 line-h-25">
                                    <spring:message code="name288"/>
                                </span>
                            </div>
                            <div class="col-sm-9 pl-5 pr-0 pos-r">
                                <div class="clear w-100 pos-a t-10 l-15 bor bor-b bor-col-8c918c">
                                </div>
                                <p class="col-aaa f-18 line-h-25 h-25 mt-20 ft-r">
                                    <spring:message code="name289"/>
                                </p>
                            </div>
                        </div>
                        <div class="clear full-w mgt">
                            <div class="col-sm-3 pl-0 pr-0">
                                <span class="col-aaa f-22 h-25 line-h-25">
                                    <spring:message code="name290"/>
                                </span>
                            </div>
                            <div class="col-sm-9 pl-5 pr-0 pos-r">
                                <div class="clear w-100 pos-a t-10 l-15 bor bor-b bor-col-8c918c">
                                </div>
                                <p class="col-aaa f-18 line-h-25 h-25 mt-20 mb-5 ft-r">
                                    <spring:message code="name291"/>
                                    <spring:message code="name292"/>
                                    <spring:message code="name293"/>
                                </p>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="clear col-sm-6 pl-25 pr-25">
                    <img class="full-w" src="<%=request.getContextPath() %>/resources/img/HK_About/About-rightv2.png" alt="<spring:message code="name282"/>">
                </div>
                <div class="clear col-sm-12 pl-25 pr-25 pull-left">
                    <div class="clear w-12d5p pull-left">
                        <span class="col-aaa f-22 h-25 line-h-25">
                            <spring:message code="name294"/>
                        </span>
                    </div>
                    <div class="clear w-87d5p pull-left pos-r pl-5">
                        <div class="clear w-100 pos-a t-10 l-15 bor bor-b bor-col-8c918c">
                        </div>
                        <p class="col-aaa f-18 line-h-25 mt-20 ft-r-1">
                            <spring:message code="name295"/>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
    <%@ include file="../comm/footer.jsp" %> 
   	<script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/bootstrap.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
</body>
</html>