<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>

<html>
<head>
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <%--移动设备网页缩放比例--%>
    <meta name="viewport" content="initial-scale=0.3,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">

    <title> <spring:message code="Services"/></title>

    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/swiper.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/reg/HK_Reg.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <link href="<%=request.getContextPath()%>/resources/css/menuList/menuList.css" rel="stylesheet" >
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>


<body>
        <!-- 第三方账号绑定手机号悬浮窗口 -->
        <%@ include file="../reg/bindPhone.jsp" %>
        <div class="clear pos-a t-0 l-0 full-wh rgba-30 bind-phone-shadev z-99 dis-n"> </div>
        <%@ include file="../comm/header.jsp" %>

        <div class="pos-r menu-main">
                 <%--网站地址--%>
                <div class="container h-40 menu-main-address">
                    <a class="menu-main-address-a1" href=""> <spring:message code="homepage"/>,</a> >
                    <a class="menu-main-address-a2" href=""><spring:message code="name810"/></a>
                </div>

              <%--背景图--%>
            <div class="container pos-r mt-20 pl-0 pr-0">
                <img  height="200" src="<%=request.getContextPath() %>/resources/img/menuList/mListBgImg.png"/>
                <span class="menu-main-bgimg-span pos-a"><spring:message code="certificate"/></span>
            </div>

              <%--心电仪产品+横线--%>
            <div class="container mt-40 pos-r menu-main-title">

                <p class="menu-main-title-p1"><spring:message code="name810"/></p>
                <span class="menu-main-title-span pos-a" ></span>
                <p class="menu-main-title-p2"> </p>
                <p  class="menu-main-title-p3 pl-0 pr-0 container"></p>
            </div>

             <%--主产品--%>
             <div class="container menu-main-product mt-60 pl-0 pr-0">

                 <!-- slide1 -->
                   <div class="menu-main-product-border pull-left" >
                       <div ><img width="168" height="243" src="<%=request.getContextPath() %>/resources/img/menuList/ecg.png" /></div>
                   </div>
                   <div class="menu-main-product-border pull-left" >
                       <p class="menu-main-product-desc-p1"><spring:message code="name811"/></p>

                       <div class="mt-20 ml-40 mr-40 mb-44 f-16 color-6C6C6C">
                               <p class="color-6C6C6C"><spring:message code="name816"/>：3</p>
                               <p class="color-6C6C6C"><a title="<spring:message code="name841"/>" style="cursor:default"><spring:message
                                       code="name817"/>：250<spring:message code="name841"/></a></p>
                               <p class="color-6C6C6C"><spring:message code="name818"/>：0-3.3V</p>
                               <p class="color-6C6C6C"><spring:message code="name819"/>：33g</p>
                               <p class="color-6C6C6C"><spring:message code="name820"/>：56X46X13mm</p>
                               <p class="color-6C6C6C"><spring:message code="name821"/>：<spring:message code="name842"/>4.0</p>
                       </div>
                   </div>

                 <!-- slide2 -->
                 <div class="menu-main-product-border pull-left" >
                     <img width="168" height="243" src="<%=request.getContextPath() %>/resources/img/menuList/djtp.png" />
                 </div>
                 <div class="menu-main-product-border pull-left" >
                     <p class="menu-main-product-desc-p1"><spring:message code="name812"/>
                     </p>

                     <div class="mt-20 ml-40 mr-40 mb-44 f-16 color-6C6C6C">
                         <p class="color-6C6C6C"><spring:message code="name822"/>：L-00-S</p>
                         <p class="color-6C6C6C"><spring:message code="name823"/>：68.2X55mm</p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name843"/>" style="cursor:default"><spring:message
                                 code="name824"/>：HOLTER、<spring:message code="name843"/></a></p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name844"/>" style="cursor:default"><spring:message
                                 code="name825"/>： <spring:message code="name844"/></a></p>
                         <p class="color-6C6C6C"><spring:message code="name820"/>：56X46X13mm</p>
                         <p class="color-6C6C6C"><spring:message code="name826"/>：<spring:message code="name845"/></p>
                     </div>
                 </div>
                 <!-- slide3 -->

                 <div class="menu-main-product-border pull-left" >
                     <p class="menu-main-product-desc-p1"><spring:message code="name813"/>
                     </p>
                     <div class="mt-20 ml-40 mr-40 mb-44 f-16 color-6C6C6C">
                         <p class="color-6C6C6C"><a title="HC3A250<spring:message code="name846"/>" style="cursor:default"><spring:message
                                 code="name827"/>：	HC3A250<spring:message code="name846"/></a></p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name847"/>" style="cursor:default"><spring:message
                                 code="name828"/>：	<spring:message code="name847"/></a></p>
                         <p class="color-6C6C6C"><spring:message code="name829"/>：	<spring:message code="name848"/></p>
                         <p class="color-6C6C6C"><spring:message code="name830"/>：	OTG/<spring:message code="name849"/></p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name850"/>" style="cursor:default"><spring:message
                                 code="name831"/>： <spring:message code="name850"/></a></p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name851"/>" style="cursor:default"><spring:message
                                 code="name832"/>： <spring:message code="name851"/></a></p>
                     </div>
                 </div>
                 <div class="menu-main-product-border pull-left" >
                     <img width="168" height="243" src="<%=request.getContextPath() %>/resources/img/menuList/ecgXdbg.png" />
                 </div>
                 <!-- slide4 -->

                 <div class="menu-main-product-border pull-left" >
                     <p class="menu-main-product-desc-p1"><spring:message code="name814"/></p>

                     <div class="mt-20 ml-40 mr-40 mb-44 f-16 color-6C6C6C">
                         <p class="color-6C6C6C"><a title="<spring:message code="name827"/>" style="cursor:default"><spring:message
                                 code="name827"/>：	HC3A250<spring:message code="name846"/></a></p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name852"/>" style="cursor:default"><spring:message
                                 code="name833"/>：	<spring:message code="name852"/></a></p>
                         <p class="color-6C6C6C"><spring:message code="name834"/>：	<spring:message code="name853"/></p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name842"/>" style="cursor:default"><spring:message
                                 code="name835"/>：	<spring:message code="name842"/>/<spring:message code="name849"/></a></p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name850"/>" style="cursor:default"><spring:message
                                 code="name836"/>： <spring:message code="name850"/></a></p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name854"/>" style="cursor:default"><spring:message code="name837"/>：<spring:message code="name854"/></a>	</p>
                     </div>
                 </div>

                 <div class="menu-main-product-border pull-left" >
                     <img width="168" height="243" src="<%=request.getContextPath() %>/resources/img/menuList/jbfw.png" />
                 </div>
                 <!-- slide5 -->
                 <div class="menu-main-product-border pull-left" >
                     <img width="168" height="243" src="<%=request.getContextPath() %>/resources/img/menuList/usb.png" />
                 </div>
                 <div class="menu-main-product-border pull-left" >
                     <p class="menu-main-product-desc-p1"><spring:message code="name815"/>
                     </p>`

                     <div class="mt-20 ml-40 mr-40 mb-44 f-16 color-6C6C6C">
                         <p class="color-6C6C6C"> <spring:message code="name838"/>：	0.25M</p>
                         <p class="color-6C6C6C"><spring:message code="name839"/>：	micro USB</p>
                         <p class="color-6C6C6C"><a title="<spring:message code="name855"/>" style="cursor:default"><spring:message code="name840"/>：	<spring:message code="name855"/></a></p>
                     </div>
                 </div>




             </div>

        </div>

        <%@ include file="../comm/footer.jsp" %>
        <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
        <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
</body>
</html>
