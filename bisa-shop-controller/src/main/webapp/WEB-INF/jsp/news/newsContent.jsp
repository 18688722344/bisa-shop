<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=0.6,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
     <title><spring:message code="name81"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/news/HK_News.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
		var http_request ="<%=request.getContextPath() %>";
		var request_url ="<%=request.getMethod() %>";
		var none = '<spring:message code="none"/>';
    </script>
</head>

<body>
	<%@ include file="../comm/header.jsp" %> 
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" id="index" href="<%=request.getContextPath()%>/index"><spring:message code="homepage"/></a> >
                    <a class="col-666 t-nonehove hovecol-309DE2" id="healthInquiry" href="<%=request.getContextPath() %>/web/call/healthInquiry"><spring:message code="name82"/></a> >
                    <span class="col-252525 t-nonehove"><spring:message code="name83"/></span>
                </p>
            </div>
            <div class="clear h-30 bg-2c9fd9 col-white line-h-30">
                <img class="ml-30 pos-r t--3" src="<%=request.getContextPath() %>/resources/img/news/HK_NewsContent/shapev1.png" alt="">
                <span class="col-white f-18 pl-5"><spring:message code="name84"/></span>
                <img class="pos-r t--5 ml-10" src="<%=request.getContextPath() %>/resources/img/news/HK_NewsContent/shapev2.png" alt="">
                <span class="col-white f-16 pl-5">INFORMATION</span>
            </div>
        </div>
        <div class="clear container pt-40 pl-0 pr-0 pb-40">
            <p class="col-3f3b3c f-24 text-center line-h-30 text-overflow news-title"><spring:message code="name85"/>
            </p>
            <p class="col-898989 f-16 text-center line-h-25 mt-5 mb-5">
                <span class="col-898989"><spring:message code="name86"/></span>
                <span class="col-898989 mr-10 cur-new-time"> </span>
                <span class="col-898989 ml-10"><spring:message code="name87"/></span>
                <span class="col-898989 cur-new-quantity"> </span>
            </p>
        </div>
        <div class="clear main-details container pb-20 bor bor-b bor-col-e4e4e4 news-content">
        </div>
        <div class="clear container pt-15 pb-15 pl-0 pr-0 cur-d" > 
            <p class="col-898989 f-18 line-h-25 pt-5 pb-5">
                <spring:message code="name88"/><a class="col-898989 t-nonehove hovecol-309DE2" id="last-news" href="#"></a>
            </p>
            <p class="col-898989 f-18 line-h-25 pt-5 pb-5" >
                <spring:message code="name89"/><a class="col-898989 t-nonehove hovecol-309DE2" id="next-news" href="#"></a>
            </p>
        </div>
         <div class="clear container pl-0 pr-0 cur-d pb-40">
            <p class="clear line-h-35 pt-5 pb-5">
                <span class="f-24 col-666 pull-left"><spring:message code="name90"/></span>
                <a class="f-18 col-898989 t-nonehove hovecol-309DE2 pull-right" id="more_news" href="<%=request.getContextPath()%>/web/call/healthInquiry"><spring:message code="name91"/>>></a>
            </p>
            <div class="clear pt-15 pb-15">
            <div class="clear bg-fafafa mian-div">
	         <div class="clear col-sm-6 pt-15 pb-15 pl-0 pr-20 news-div">
	                 <div class="col-sm-5 clear pd-15">
	                 <img class="full-w wh16-9 cur-p news-img"  >
	             </div>
	             <div class="col-sm-7 clear pt-15 pb-15">
	                 <p class="line-h-20 h-20 f-16 col-414141 text-overflow cur-p hovecol-309DE2 news-title">
	                 </p>
	                 <p class="f-12 col-888 mtb-10-0-ipad h-40 overflow-h line-h-20 text-line-2-1-ipad cur-p hovecol-999 news-subhead">
	                 </p>
	                 <p class="f-12 col-888 mtb-10-0-ipad line-h-20 text-line-2-1-ipad cur-d">
	                    <spring:message code="name86"/><span class="col-888 news-time"></span> 
	                    <spring:message code="name87"/><span class="col-888 news-quantity"></span>
	                 </p>
	             </div> 
	         </div>
           </div>
         </div>
        </div>
    </div>
   <%@ include file="../comm/footer.jsp" %>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/bootstrap.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/news/newsContent.js"></script>
</body>
</html>