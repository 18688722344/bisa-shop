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
    <title><spring:message code="name205"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/user/user_center.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
	<script type="text/javascript">
		var name200 = "<spring:message code='name200'/>"
		var name201 = "<spring:message code='name201'/>"
		var name202 = "<spring:message code='name202'/>"
		var name203 = "<spring:message code='name203'/>"
		var name204 = "<spring:message code='name204'/>"
	</script>
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <!-- 本页面是用户查看自己的用户列表 -->
    <%@ include file="../comm/header.jsp" %> 
    <div class="wrap clear bg-f5f5f5">
        <div class="clear full-w bor bor-t bor-col-D6D6D6 sha-float-titlev1 bg-white top-tit">
            <div class="container pt-10 pb-10 clear line-h-30 plr-0-10-ipad">
                <span class="col-black family-h pull-left cur-d f-20">${productDto.title}</span>
                <span class="col-black family-h pull-right f-12">
                 <!-- <span class="t-nonehove hovecol-309DE2 col-black cur-p">概述</span>
                <span>|</span>
                <span class="t-nonehove hovecol-309DE2 col-black cur-p">参数</span>
                <span>|</span>
                <span class="t-nonehove hovecol-309DE2 col-black cur-p">用户评价</span> -->
                </span>
            </div>
        </div>
        <div class="clear full-w bor bor-t bor-col-D6D6D6 sha-float-titlev1 affix t-0 l-0 bg-white z-999 dis-n aff-tit">
            <div class="container pt-10 pb-10 clear line-h-30 plr-0-10-ipad">
                <span class="col-black family-h pull-left cur-d f-20 ">${productDto.title}</span>
                <button class="pull-right f-20 line-h-30 h-30 text-center bor-none bg-309DE2 hovbg-2D90CF col-white pl-20 pr-20">
                    <spring:message code="name206"/>
                </button>
            </div>
        </div>
        <div class="container pl-0 pr-0 pos-r">
           <div class="col-sm-8 pt-30 pl-20 pr-10 mb-20"> 
                       <div class="clear mt-20 full-w bg-white pt-40 pb-35 pr-15">
                           <div class="clear col-sm-2 pl-0 pr-0">
                               <img class="img-70 pull-right" src="<%=request.getContextPath() %>/resources/${productDto.userImg}" alt="">
                           </div>
                           <div class="clear col-sm-10 pl-20 pr-20">
                               <p class="line-h-20 mb-20 clear">
                                   <input type="hidden" class="appraisesuccess-state" value="${productDto.appraise_degree}">
                                   <span class="col-8d665a f-18 pull-left dis-ib">${productDto.user_guid}</span>
                                   <span class="f-20 col-ffd600 dis-ib pull-right cur-d mr-10 appraisesuccess-statetext">
                                       <c:if test="${productDto.appraise_degree==1 }">
                                          <spring:message code="name200"/>
                                       </c:if>
                                       <c:if test="${productDto.appraise_degree==2 }">
                                          <spring:message code="name201"/>
                                       </c:if>
                                       <c:if test="${productDto.appraise_degree==3 }">
                                         <spring:message code="name202"/>
                                       </c:if>
                                       <c:if test="${productDto.appraise_degree==4 }">
                                          <spring:message code="name203"/>
                                       </c:if>
                                       <c:if test="${productDto.appraise_degree==5 }">
                                          <spring:message code="name204"/>
                                       </c:if>
                                   </span>
                                   <span class="img-20 pull-right dis-ib mr-10 cur-d bg-faceauto appraisesuccess-stateface">
                                   </span>
                               </p>
                               <p class="full-w mb-25">
                                   <span class="f-14 col-b0b0b0">
                                           <fmt:formatDate pattern="yyyy-MM-dd" value="${productDto.update_time}"/>
                                   </span>
                               </p>
                               <p class="f-14 col-333 line-h-20">
                                  ${productDto.appraise_one}
                               </p>
                           </div>
                       </div>
           </div>
           <!-- 一下4个浮标嵌入的数据一致 -->
           <!--在ipad下显示的右浮标-->
           <div class="col-sm-4 pt-50 pl-20 pr-20 visible-sm visible-md">
               <div class="clear bg-white text-center pt-40 pb-40">
                   <div class="clear pl-40 pr-40">
                       <a href="">
                           <img class="full-w" src="<%=request.getContextPath() %>/resources/${productDto.main_picture}" alt="">
                       </a>
                   </div>
                   <p class="mt-30 f-20-16-ipad col-333 text-center">
                       <a class="col-333 t-nonehove hovecol-333" href="">${productDto.title}</a>
                   </p>
                   <p class="mt-10 f-20-16-ipad col-309DE2 text-center">
                    <fmt:formatNumber value="${productDto.price}" type="currency" pattern="#0.00"/>
                        <spring:message code="name103"/>
                   </p>
                   <div class="clear mt-40 text-center pl-30 pr-30">
                       <button class="h-40-30-ipad full-w text-center line-h-40-30-ipad col-white bg-309DE2 bor-none hovbg-2D90CF f-14-12-ipad" onclick="window.location.href='<%=request.getContextPath() %>/shopping/getCommodityId?shop_number=${productDto.product_number}'">
                           <spring:message code="name207"/>
                       </button>
                   </div>
               </div>
           </div>
           <!--  PC的右浮标顶部-->
           <div class="poa-a mt-50 pl-20 pr-20 w-400 z-99 ml-800 hidden-sm hidden-md posa-appraiselist-righttop">
               <div class="clear bg-white h-500 text-center pt-60">
                   <a href="">
                       <img class="img-200" src="<%=request.getContextPath() %>/resources/${productDto.main_picture}" alt="">
                   </a>
                   <p class="mt-30 f-20 col-333 text-center">
                       <a class="col-333 t-nonehove hovecol-333" href="">${productDto.title}</a>
                   </p>
                   <p class="mt-10 f-20 col-309DE2 text-center">
                   <fmt:formatNumber value="${productDto.price}" type="currency" pattern="#0.00"/>
                      <spring:message code="name103"/>
                   </p>
                   <div class="clear mt-40 text-center">
                       <button class="h-40 w-225 text-center line-h-40 col-white bg-309DE2 bor-none hovbg-2D90CF f-14" onclick="window.location.href='<%=request.getContextPath() %>/shopping/getCommodityId?shop_number=${productDto.product_number}'">
                           <spring:message code="name207"/>
                       </button>
                   </div>
               </div>
               <div class="clear img-100 bg-white mt-70 cur-p appraise-backup">
                   <img src="<%=request.getContextPath() %>/resources/img/user/Appraise/back-up.png" alt="">
               </div>
           </div>
           <!-- PC的右浮标中部-->
           <div class="pos-a b-0 mt-50 mb-20 pl-20 pr-20 w-400 z-99 ml-800 hidden-sm hidden-md posa-appraiselist-rightbottom dis-n">
               <div class="clear bg-white h-500 text-center pt-60">
                   <a href="">
                       <img class="img-200" src="<%=request.getContextPath() %>/resources/${productDto.main_picture}" alt="">
                   </a>
                   <p class="mt-30 f-20 col-333 text-center">
                       <a class="col-333 t-nonehove hovecol-333" href="">${productDto.title}</a>
                   </p>
                   <p class="mt-10 f-20 col-309DE2 text-center">
                        <fmt:formatNumber value="${productDto.price}" type="currency" pattern="#0.00"/>
                        	<spring:message code="name103"/>
                   </p>
                   <div class="clear mt-40 text-center">
                       <button class="h-40 w-225 text-center line-h-40 col-white bg-309DE2 bor-none hovbg-2D90CF f-14" onclick="window.location.href='<%=request.getContextPath() %>/shopping/getCommodityId?shop_number=${productDto.product_number}'">
                           <spring:message code="name207"/>
                       </button>
                   </div>
               </div>
               <div class="clear img-100 bg-white mt-70 cur-p appraise-backup">
                   <img src="<%=request.getContextPath() %>/resources/img/user/Appraise/back-up.png" alt="">
               </div>
           </div>
          <!--  PC的右浮标底部 -->
           <div class="affix t-0 mt-100 pl-20 pr-20 w-400 z-99 ml-800 hidden-sm hidden-md aff-appraiselist-right dis-n">
               <div class="clear bg-white h-500 text-center pt-60">
                   <a href="">
                       <img class="img-200" src="<%=request.getContextPath() %>/resources/${productDto.main_picture}" alt="">
                   </a>
                   <p class="mt-30 f-20 col-333 text-center">
                       <a class="col-333 t-nonehove hovecol-333" href="">${productDto.title}</a>
                   </p>
                   <p class="mt-10 f-20 col-309DE2 text-center">
                       <fmt:formatNumber value="${productDto.price}" type="currency" pattern="#0.00"/>
                       	<spring:message code="name103"/>
                   </p>
                   <div class="clear mt-40 text-center">
                       <button class="h-40 w-225 text-center line-h-40 col-white bg-309DE2 bor-none hovbg-2D90CF f-14" onclick="window.location.href='<%=request.getContextPath() %>/shopping/getCommodityId?shop_number=${productDto.product_number}'">
                           <spring:message code="name207"/>
                       </button>
                   </div>
               </div>
               <div class="clear img-100 bg-white mt-70 cur-p appraise-backup">
                   <img src="<%=request.getContextPath() %>/resources/img/user/Appraise/back-up.png" alt="">
               </div>
           </div>
       </div>
    </div>
    
    <%@ include file="../comm/footer.jsp" %> 
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/comm/menu.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
	<script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/order-center/appraise.js"></script>
</body>
</html>