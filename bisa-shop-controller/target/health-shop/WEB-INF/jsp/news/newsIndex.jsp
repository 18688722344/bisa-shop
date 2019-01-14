<%@ page language="java"  pageEncoding="UTF-8"%>
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
    <meta name="viewport" content="initial-scale=0.6,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name92"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/news/HK_News.css" rel="stylesheet">
    <!--[if lt IE 9]>
        <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
	    var http_request = "<%=request.getContextPath() %>";
	    var request_url = "<%=request.getMethod() %>";
    </script>
</head>

<body>
    <%@ include file="../comm/header.jsp" %>
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" id="index" href="<%=request.getContextPath()%>/index"><spring:message code="homepage"/></a> >
                    <span class="col-252525 t-nonehove"><spring:message code="name82"/></span>
                </p>
            </div>
        </div>
        <div class="clear full-ws">
            <img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_NewsIndex/banner.jpg" alt="">
            <p class="full-w-p"><spring:message code="name779"/></p>
            <p class="full-w-p-1"><spring:message code="name776"/></p>
            <p class="full-w-p-2">NEWS CENTER</p>
        </div>
        <!-- 父容器 -->
        <div class="clear container mt-15 mb-15 pt-15 pb-15 mian-div">
     		<!-- 模板元素 -->
           <div class="clear col-sm-6 pt-15 pb-15 pl-0 pr-20 news-div">
           		<input type="hidden" class="news-id" value=""/>
               <div class="clear bg-fafafa">
                   <div class="col-sm-5 clear pd-15">
                       <img class="full-w wh16-9 cur-p news-img"> 
                   </div>
                   <div class="col-sm-7 clear pt-15 pb-15">
                      <p class="line-h-20 h-20 f-16 col-414141 text-overflow cur-p hovecol-309DE2 news-title" >
                      </p>
                       <p class="f-12 col-888 mtb-10-0-ipad h-40 overflow-h line-h-20 text-line-2-1-ipad cur-p hovecol-999 news-subhead"  >
                       </p>
                      <p class="f-12 col-888 mtb-10-0-ipad line-h-20 text-line-2-1-ipad cur-d">
                           	<spring:message code="name86"/><span class="col-888 new-time"></span> 
                           	<spring:message code="name87"/><span class="col-888 new-quantity"></span>
                       </p>
                   </div>
               </div>
           </div>
        </div>
        <!-- layui分页 -->
		<div class="text-center order-pager">
			<div id="layer-pager"></div>
		</div>
    </div>
    
    <%@ include file="../comm/footer.jsp" %>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/bootstrap.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/news/news.js"/></script>
    <script type="text/javascript">
	   <%--  function detail(news) {
	        window.location.href = "<%=request.getContextPath()%>/newsDetail?news_id="+news_id;
	    }  --%>
    </script>
</body>
</html>