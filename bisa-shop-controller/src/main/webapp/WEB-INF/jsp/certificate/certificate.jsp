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

    <title> <spring:message code="certificate"/></title>

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
   <style>
       .zhens{
           width: 550px;
           height:500px ;
           background:rgba(216,233,255,1);
           float: left;
           position: relative;
       }
       .zhens img{
           margin: 16px 102px 36px 102px;
       }
       .zhens p{
           position: absolute;
           bottom: 0;
           width: 100%;
           height: 100px;
           text-align: center;
           line-height: 100px;
           color:rgba(255,255,255,1);
           background-color: #309de2db;
           font-size:26px;
           font-family:SourceHanSansCN-Light;
           font-weight:600;
       }
       .FCC_authentication{
          margin-left: 140px;
      }
       .QBQ_authentication{
           margin-top: 100px;
       }
   </style>
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
                    <a class="menu-main-address-a2" href=""><spring:message code="certificate"/></a>
                </div>

              <%--背景图--%>
            <div class="container pos-r mt-20 pl-0 pr-0">
                <img  height="200" src="<%=request.getContextPath() %>/resources/img/menuList/mListBgImg.png"/>
                <span style="right: 80px;" class="menu-main-bgimg-span pos-a"><spring:message code="name880"/></span>
            </div>

              <%--心电仪产品+横线--%>
            <div class="container mt-40 pos-r menu-main-title">

                <p class="menu-main-title-p1"><spring:message code="name881"/></p>
                <span class="menu-main-title-span pos-a" ></span>
                <p class="menu-main-title-p2"> </p>
                <p  class="menu-main-title-p3 pl-0 pr-0 container"></p>
            </div>

             <%--Context--%>
             <div class="container menu-main-product mt-60 pl-0 pr-0">
                       <%--slide1--%>
                       <div class="CE_authentication zhens">
                           <a href="javascript:void(0)">
                           <img width="347" src="<%=request.getContextPath() %>/resources/img/certificate/CE@2x.png"/>
                           <p>CE <spring:message code="name882"/></p>
                           </a>
                       </div>
                       <%--slide2--%>
                       <div class="FCC_authentication zhens">
                           <a href="javascript:void(0)">
                           <img src="<%=request.getContextPath() %>/resources/img/certificate/FCC@2x.png"/>
                           <p>FCC <spring:message code="name882"/></p></a>
                       </div>
                       <%--slide3--%>
                       <div class="QBQ_authentication zhens">
                           <a href="javascript:void(0)">
                           <img src="<%=request.getContextPath() %>/resources/img/certificate/QBQ@2x.png"/>
                           <p><spring:message code="name842"/> QBQ <spring:message code="name882"/></p></a>
                       </div>


             </div>

        </div>

        <%@ include file="../comm/footer.jsp" %>
        <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
        <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>


</body>
</html>
