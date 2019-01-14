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
    <meta name="viewport" content="initial-scale=0.3,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name296"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/about/HK_About.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <script type="text/javascript">
			var name407 = '<spring:message code="name407"/>' 
			var name408 = '<spring:message code="name408"/>' 
			var name409 = '<spring:message code="name409"/>' 
			var name410 = '<spring:message code="name410"/>'
			
			var name411 = '<spring:message code="name411"/>'
			var name412 = '<spring:message code="name412"/>'
			var name413 = '<spring:message code="name413"/>'
			var name414 = '<spring:message code="name414"/>'
			var name415 = '<spring:message code="name415"/>'
			
			var name27 = '<spring:message code="name27"/>'
			var name305 = '<spring:message code="name305"/>'
			var name306 = '<spring:message code="name306"/>'
	</script>
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        label.error {
            display: block;
            margin: 5px 5px
            float: left;
            color: #c00;
        }
    </style>
    <script type="text/javascript">
			var http_request ="<%=request.getContextPath() %>";
			var request_url ="<%=request.getScheme()%>";
			var request_type ="<%=request.getAttribute("request_type") %>";
	</script>
    <!-- 百度地图ak码 -->
   <!--  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=rtNFHX9DxhNX8AYMLSmqftYGuMlTvGbg"></script> -->
    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=rtNFHX9DxhNX8AYMLSmqftYGuMlTvGbg&s=1"></script>
</head>

<body>
    <%@ include file="../comm/header.jsp" %> 
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" id="index" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/></a> >
                    <span class="col-252525 t-nonehove"><spring:message code="name297"/></span>
                </p>
            </div>
        </div>
        <div class="clear full-ws">
            <img class="full-w" src="<%=request.getContextPath() %>/resources/img/HK_About/bannerv3.jpg" alt="">
            <p class="full-w-p"><spring:message code="name778"/></p>
            <p class="full-w-p-1"><spring:message code="name776"/></p>
            <p class="full-w-p-2">CONTACT US</p>
        </div>
        <div class="container mt-30 pl-0 pr-0 clear bg-f5f5f5 pt-30 pb-70 mb-60">
            <div class="col-sm-3 pl-30 pr-20">
                <div class="clear bg-white pd-40-10-ipad">
                    <p class="mt-10 mb-10 col-212121 f-30 line-h-40 cur-d">
                        <spring:message code="relation"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-40 mb-20 cur-p hovecol-309DE2 col-active CU-list CU-listv1">
                        <spring:message code="relation"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 CU-list CU-listv2">
                        <spring:message code="name21"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 CU-list CU-listv3">
                        <spring:message code="name22"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2">
                        <a class="col-8c8c8c t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/resources/img/pdf/certification.pdf" target="_blank">
							<spring:message code="name630"/>
						</a>
                    </p>
                    <p class="col-757575 f-18 line-h-25 mt-20 mb-20 cur-p hovecol-309DE2">
                        <a class="col-8c8c8c t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/resources/img/pdf/Electric_utility_model_certificate.pdf" target="_blank">
                        	<spring:message code="name631"/>
                        </a>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2">
                        <a class="col-8c8c8c t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/resources/img/pdf/CFDA_MZ17030099_front-pages.pdf" target="_blank">
							<spring:message code="name632"/>
							</a>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2">
                        <a class="col-8c8c8c t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/resources/img/pdf/CFDA_MZ17050122_front-pages.pdf" target="_blank">
							<spring:message code="name633"/>
						</a>
                    </p>
                </div>
            </div>
            <div class="clear col-sm-9 pr-30 pl-0">
                <div class="clear bg-white pb-60 pl-50 pr-50 CU-tips CU-tipsv1">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 line-h-70-50-ipad pb-20">
                        <spring:message code="relation"/>
                    </p>
                    <div class="clear bor bor-b bor-col-f5f5f5">
                    </div>
                    <div class="clear mt-40 mb-40">
                        <div id="bisa-map" class="clear" style="width: 770px; height: 550px;">
                        </div>
                    </div>
                    <div class="clear">
                        <div class="clear col-sm-6">
                            <p class="f-20 col-309DE2 mb-30">
                                <spring:message code="name298"/>
                            </p>
                            <form action="" class="clear contactus-form">
                             	<div class="col-sm-3 h-34 line-h-34 f-18 col-616161 mb-20 pl-0 pr-0">
                                    <spring:message code="name130"/>
                                </div>
                                <div class="clear col-sm-9 mb-20 pl-0">
                                    <input type="text" class="form-control radius-0 cname" name="cname">
                                </div>
                                <div class="col-sm-3 h-34 line-h-34 f-18 col-616161 mb-20 pl-0 pr-0">
                                     <spring:message code="name299"/>
                                </div>
                                <div class="clear col-sm-9 mb-20 pl-0">
                                    <input type="text" class="form-control radius-0 cphone" name="cphone">
                                </div>
                                <div class="col-sm-3 h-34 line-h-34 f-18 col-616161 mb-20 pl-0 pr-0">
                                    <spring:message code="name300"/>
                                </div>
                                <div class="clear col-sm-9 mb-20 pl-0">
                                    <input type="text" class="form-control radius-0 cemail" name="cemail">
                                </div>
                                <div class="clear col-sm-12 pl-0 pr-0 mb-20">
                                    <textarea class="form-control radius-0 resize-v" rows="10" placeholder="<spring:message code="name301"/>" name="cabout"></textarea>
                                </div>
                                <div class="clear col-sm-12 pl-0 pr-0 ">
                                    <button type="submit" class="bor-none hovbg-2D90CF bg-309DE2 pl-40 pr-40 line-h-35 f-14 col-white">
                                        <spring:message code="name302"/>
                                    </button>
                                </div>
                            </form>
                        </div>
                        
                        <div class="clear col-sm-6 pl-30">
                            <p class="f-20 col-309DE2 mb-30">
                                <spring:message code="name303"/>
                            </p>
                             <p class="pl-50 f-14 col-696969 line-h-25 pos-r mb-20">
                                <span class="dis-ib pos-a t-0 l-10 col-309DE2 f-26">
                                    <img src="<%=request.getContextPath() %>/resources/img/HK_About/contactus-tipsv6.png" alt="">
                                </span>
                                <spring:message code="name304"/>
                            </p>
                            <p class="pl-50 f-14 col-696969 line-h-25 pos-r mb-20">
                                <span class="dis-ib pos-a t-0 l-10 col-309DE2 f-26">
                                    <img src="<%=request.getContextPath() %>/resources/img/HK_About/contactus-tipsv5.png" alt="">
                                </span>
                                <spring:message code="name305"/>
                            </p>
                            <div class="pl-50 f-14 col-696969 line-h-25 pos-r mb-20">
                                <span class="dis-ib pos-a t--3 l-10 col-696969 f-26">
                                    <img src="<%=request.getContextPath() %>/resources/img/HK_About/contactus-tipsv2.png" alt="">
                                </span>
                                <p class="mb-10">
                                    <span><spring:message code="name306"/></span><a class=" t-nonehove col-696969 hovecol-309DE2" href="tel:852-24230600">(852) 2423 0600</a>
                                </p>
                                <p>
                                    <span class="clear pos-r dis-ib pull-left" style="width: 42px;height: 19px;"></span><a class=" t-nonehove col-696969 hovecol-309DE2" href="tel:852-24230791">(852) 2423 0791</a>
                                </p>
                            </div>
                            <p class="pl-50 f-14 col-696969 line-h-25 pos-r mb-20">
                                <span class="dis-ib pos-a t--3 l-10 col-696969 f-26">
                                    <img src="<%=request.getContextPath() %>/resources/img/HK_About/contactus-tipsv3.png" alt="">
                                </span>
                               
                                <spring:message code="name307"/><a class=" t-nonehove col-696969 hovecol-309DE2" href="tel:0755-26880962">(852) 2423 0148</a>          
                            </p>
                            <p class="pl-50 f-14 col-696969 line-h-25 pos-r mb-20">
                                <span class="dis-ib pos-a t--3 l-10 col-696969 f-26">
                                    <img src="<%=request.getContextPath() %>/resources/img/HK_About/contactus-tipsv4.png" alt="">
                                </span>
                                <spring:message code="name308"/><a class=" t-nonehove col-696969 hovecol-309DE2" href="mailto:bisapecg@bisa.com.hk">bisapecg@bisa.com.hk</a>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="clear bg-white pb-60 pl-50 pr-50 CU-tips CU-tipsv2 dis-n">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 line-h-70-50-ipad pb-20">
                        <spring:message code="name21"/>
                    </p>
                    <div class="clear bor bor-b bor-col-f5f5f5">
                    </div>
                    <div class="clear pd-15">
                        <h2 class="text-center mb-30 f-w">
                            <spring:message code="name309"/>
                        </h2>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name310"/>
                        </p>
                        <p class="f-20 line-h-30 mb-15 f-w">
                            <spring:message code="name311"/>
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                           <spring:message code="name312"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name313"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name314"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name315"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name316"/>
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                            <spring:message code="name317"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name318"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name319"/> 
                        </p>
                        <p class="f-20 line-h-30 mb-15 f-w">
                            <spring:message code="name320"/>
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                            <spring:message code="name321"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name322"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name323"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name324"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name325"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name326"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name327"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name328"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name329"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name330"/> 
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                           <spring:message code="name331"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                          <spring:message code="name332"/>  
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                           <spring:message code="name333"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name334"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name335"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name336"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name337"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name338"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name339"/>  
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                          <spring:message code="name340"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                          <spring:message code="name341"/>  
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                           <spring:message code="name342"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name343"/> 
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                           <spring:message code="name344"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name345"/> 
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                            <spring:message code="name346"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name347"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name348"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name349"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name350"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name351"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                          <spring:message code="name352"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name353"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name354"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name355"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name356"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name357"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                         <spring:message code="name358"/>   
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name359"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name360"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name361"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                         <spring:message code="name362"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                         <spring:message code="name_360"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                         <spring:message code="name_361"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name363"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name364"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                         <spring:message code="name365"/>   
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                        	<spring:message code="name_365"/>   
                        </p>
                        	<p class="text-ind f-16 line-h-30 mb-15 pl-20">
                        	<spring:message code="name_366"/>   
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                          <spring:message code="name366"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name367"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name368"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name369"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name370"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name371"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name372"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name373"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                          <spring:message code="name374"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name375"/> 
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                           <spring:message code="name376"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name377"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name378"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name379"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name380"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name381"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name382"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name383"/> 
                        </p>
                    </div>
                </div>
                <div class="clear bg-white pb-60 pl-50 pr-50 CU-tips CU-tipsv3 dis-n">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 line-h-70-50-ipad pb-20">
                  		<spring:message code="name22"/>
                    </p>
                    <div class="clear bor bor-b bor-col-f5f5f5">
                    </div>
                    <div class="clear pd-15">
                        <h2 class="text-center mb-30 f-w">
                           <spring:message code="name384"/>   
                        </h2>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name385"/> 
                        </p>
                        <p class="f-20 line-h-30 mb-15 f-w">
                          <spring:message code="name386"/>  
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                            <spring:message code="name387"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name388"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name389"/> 
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                           <spring:message code="name390"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name391"/> 
                        </p>
                        <p class="f-16 line-h-30 mb-15 f-w">
                           <spring:message code="name392"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name393"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                            <spring:message code="name394"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                            <spring:message code="name395"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name396"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                           <spring:message code="name397"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-20">
                        	<spring:message code="name398"/>
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-40">
                           <spring:message code="name399"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                          <spring:message code="name400"/>  
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-40">
                           <spring:message code="name401"/> 
                        </p>
                         <p class="text-ind f-16 line-h-30 mb-15 pl-40">
                           <spring:message code="name402"/> 
                        </p>
                         <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name403"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name404"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name405"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-40">
                           <spring:message code="name406"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-40">
                           <spring:message code="name_384"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15">
                           <spring:message code="name_385"/> 
                        </p>
                        <p class="f-20 line-h-30 mb-15 f-w">
                           <spring:message code="name_386"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-40">
                           <spring:message code="name_387"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-40">
                           <spring:message code="name_388"/> 
                        </p>
                        <p class="text-ind f-16 line-h-30 mb-15 pl-40">
                           <spring:message code="name_389"/> 
                        </p>
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
    <script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/about/HK_ContactUs.js"></script>
    
</body>
</html>