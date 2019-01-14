<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name45"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/about/HK_About.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
    .title-color{ background-color:rgba(1,144,255,0.3);}
    </style>
</head>

<body>
   <%@ include file="../comm/header.jsp" %> 
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/></a> >
                    <span class="col-252525 t-nonehove"><spring:message code="instructions"/></span>
                </p>
            </div>
        </div>
        <div class="clear full-w">
            <img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/banner.jpg" alt="">
        </div>
        <div class="container mt-30 pl-0 pr-0 clear bg-f5f5f5 pt-30 pb-70 mb-60">
            <div class="col-sm-3 pl-30 pr-20">
                <div class="clear bg-white pd-40-10-ipad">
                    <p class="mt-10 mb-10 col-212121 f-30 line-h-40 cur-d">
                        <spring:message code="instructions"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-40 mb-20 cur-p hovecol-309DE2 col-active instructions-list1">
                        <spring:message code="name13"/>
                    </p>
                </div>
            </div>
            <div class="clear col-sm-9 pr-30 pl-0">
                <div class="clear bg-white pb-60 pl-50 pr-50">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 line-h-70-50-ipad pb-20">
                        <spring:message code="name13"/>
                    </p>
                    <div class="clear bor bor-b bor-col-f5f5f5">
                    </div>
                    <div class="layui-collapse mt-40" lay-accordion="">
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title f-18 title-color"><spring:message code="name46"/></h2>
                            <div class="layui-colla-content layui-show">
                                <p class="f-20 col-333 h-20 line-h-20 mt-30">
                                    <spring:message code="name47"/>
                                </p>
                                <p class="f-20 h-20 line-h-20 mt-20">
                                    <a href="" class="col-0190ff t-nonehove hovecol-FF1C1C"><spring:message code="name48"/></a>
                                </p>
                                <p class="f-20 col-333 h-20 line-h-20 mt-30">
                                   <spring:message code="name49"/>
                                </p>
                                <p class="f-20 col-aaa h-20 line-h-20 mt-20">
                                    <spring:message code="name50"/>
                                </p>
                                <div class="clear text-center mt-40 mb-40">
                                    <img class="img-180" src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/qr_code.png" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title f-18 title-color"><spring:message code="name51"/></h2>
                            <div class="layui-colla-content">
                                <p class="f-20 col-333 line-h-30 mt-30">
                                    <spring:message code="name52"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15">
                                    <spring:message code="name53"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15">
                                    <spring:message code="name54"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15">
                                    <spring:message code="name55"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_register_tipsv1.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25">
                                    <spring:message code="name56"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_register_tipsv2.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25">
                                    <spring:message code="name57"/>
                                </p>
                                <div class="clear text-center mt-25 mb-30">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_register_tipsv3.png" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title f-18 title-color"><spring:message code="name58"/></h2>
                            <div class="layui-colla-content">
                                <p class="f-20 col-333 line-h-30 mt-30">
                                    <spring:message code="name59"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15 pl-30">
                                    <spring:message code="name60"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15 pl-30">
                                    <spring:message code="name61"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15 pl-30">
                                    <spring:message code="name62"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_bound_tipsv1.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25 pl-30">
                                    <spring:message code="name63"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15 pl-30">
                                    <spring:message code="name64"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15 pl-30">
                                    <spring:message code="name65"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_bound_tipsv2.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25 pl-30">
                                    <spring:message code="name66"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_bound_tipsv4.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-30">
                                    <spring:message code="name67"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15 pl-30">
                                    <spring:message code="name68"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15 pl-30">
                                    <spring:message code="name69"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_bound_tipsv5.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25 pl-30">
                                    <spring:message code="name70"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-25 pl-30">
                                    <spring:message code="name71"/>
                                </p>
                                <div class="clear text-center mt-25 mb-35">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_bound_tipsv6.png" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="layui-colla-item">
                            <h2 class="layui-colla-title f-18 title-color"><spring:message code="name72"/></h2>
                            <div class="layui-colla-content">
                                <p class="f-20 col-333 line-h-30 mt-30">
                                    <spring:message code="name73"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15">
                                    <spring:message code="name74"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-15">
                                    <spring:message code="name75"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_report_tipsv1.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25">
                                    <spring:message code="name76"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_report_tipsv2.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25">
                                    <spring:message code="name77"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_report_tipsv3.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25">
                                    <spring:message code="name78"/>
                                </p>
                                <div class="clear text-center mt-25">
                                    <img src="<%=request.getContextPath() %>/resources/img/news/HK-Instructions/in_report_tipsv4.png" alt="">
                                </div>
                                <p class="f-20 col-333 line-h-30 mt-25">
                                    <spring:message code="name79"/>
                                </p>
                                <p class="f-20 col-333 line-h-30 mt-25">
                                    <spring:message code="name80"/>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../comm/footer.jsp" %> 
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.validate.messages_zh.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/about/HK-Instructions.js"></script>
</body>

</html>