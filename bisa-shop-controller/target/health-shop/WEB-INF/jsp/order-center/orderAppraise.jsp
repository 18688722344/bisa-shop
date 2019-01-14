<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.bisa.health.basic.entity.SystemContext"%>
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
	<meta name="viewport" content="initial-scale=0.35,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name208"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/user/user_center.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/shopping/appraise.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
	<script type="text/javascript">
		var name213 = "<spring:message code='name213'/>"; 
		var name537 = "<spring:message code='name537'/>"; 
	</script>
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<%@ include file="../comm/header.jsp" %>
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/> </a> >
                    <span class="col-252525 t-nonehove"><spring:message code="name209"/></span>
                </p>
            </div>
        </div>
        <div class="clear full-w">
             <img class="full-w" src="<%=request.getContextPath() %>/resources/img/user/User/userCenter-banner.jpg" alt="">
        </div>
        <div class="container pl-0 pr-30 mt-30 clear bg-f5f5f5 pt-30 pb-70 mb-60">
			<%@ include file="../comm/menu.jsp"%>
			
			<div class="clear col-sm-9 pr-30 pl-0">
				<div class="clear bg-white main-container">
				
				<p class="ml-30 pt-40-20-ipad f-50-40-ipad col-757575 line-h-70-50-ipad pb-20">
							<spring:message code="name195"/></p>
				
	            <div class="clear col-sm-12 mt-10 mb-10 pr-30 pl-0 bor-top-c0c0c0 bg-white module-div">
	                <div class="col-sm-5 pt-20 pb-10">
	                    <div class="clear text-center">
	                        <img class="img-100-100-ipad min-h-100-100-ipad goods-img" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
	                        <p class="f-20 col-333 mt-10 cur-d goods-name">
	                           	商品名称
	                        </p>
	                       	<%--  
	                        <p class="col-309DE2 f-20 mt-10 cur-d">
	                            <spring:message code="name103"/>
	                         	1000.00
	                        </p>
	                         --%>
	                    </div>
	                </div>
	                <div class="col-sm-7 mt-10">
	                    <div class="clear pt-10 pb-10">
	                    	<!-- 评分 -->
	                        <div class="clear f-16 h-35 pt-5 pb-10 pull-left text-center rate-div">
	                        		<span class="pull-left"> <spring:message code="name194"/></span>
	                        		<div class="pull-left pl-10 rate-star">
		                        		<span class="img-25 pull-left mr-10 cur-p bg-starun star-tips"> </span>
			                            <span class="img-25 pull-left mr-10 cur-p bg-starun star-tips"> </span>
			                            <span class="img-25 pull-left mr-10 cur-p bg-starun star-tips"> </span>
			                            <span class="img-25 pull-left mr-10 cur-p bg-starun star-tips"> </span>
			                            <span class="img-25 pull-left mr-10 cur-p bg-starun star-tips"> </span>
		                            	<input type="hidden" class="rate-value" value="">
	                        		</div>
	                        </div>
	                    </div>
	                    <div class="clear">
	                       <textarea class="f-16 full-w appraises-one" rows="5" maxlength="100" placeholder="<spring:message code="name211"/>" id="appraises-one"></textarea>
	                   	  <input type="hidden" class="goods-number" value="">
	                    </div>
	                </div>
	            </div>
	            
		         <!-- 发表评价 -->
		         <div class="clear col-sm-12 mt-10 mb-10 pt-20 pb-20 text-center bor-top-c0c0c0 bg-white">
			         <button class="h-30 w-110 f-16 col-white bg-309DE2 hovbg-2D90CF bor-none" id="submit-appraise">
			              <spring:message code="name210"/>
			         </button>
	         	</div>
	         	</div>
	        </div>
        </div>
    </div>
    
   	<%@ include file="../comm/footer.jsp" %> 
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/order-center/appraise.js"></script>
	<script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
	<script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
</body>
</html>