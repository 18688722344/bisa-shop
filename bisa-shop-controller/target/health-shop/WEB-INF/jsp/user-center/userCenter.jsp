<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.bisa.health.pay.enumerate.PayStatus"%>
<%@ include file="../comm/tag.jsp" %>
<%  String menuType="userCenter"; %>

<!DOCTYPE html>
<html>
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
    <title><spring:message code="name568"/></title>
    <!-- base -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css">
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link  href="<%=request.getContextPath() %>/resources/ctrl/cropper/dist/cropper.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/user/user_center.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var request_url = "<%=request.getContextPath() %>";
    	
    	var name588 = "<spring:message code='name588'/>";
		var name589 = "<spring:message code='name589'/>";
		var name590 = "<spring:message code='name590'/>";
		var name591 = "<spring:message code='name591'/>";
		var name592 = "<spring:message code='name592'/>";
		var name148 = "<spring:message code='name148'/>";
    </script>
</head>

<body>
   	<%@ include file="../comm/header.jsp" %> 
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/></a> >
                    <span class="col-252525 t-nonehove"><spring:message code="name569"/></span>
                </p>
            </div>
        </div>

        <div class="clear full-ws">
              <img class="full-w" src="<%=request.getContextPath() %>/resources/img/user/User/userCenter-banner.jpg" alt="">
                <p class="full-w-p"><spring:message code="name780"/></p>
                <p class="full-w-p-1"><spring:message code="name776"/></p>
                <p class="full-w-p-2">NEWS CENTER</p>
        </div>
        <div class="container pl-0 pr-0 mt-30 clear bg-f5f5f5 pt-30 pb-70 mb-60">
         	<%@ include file="../comm/menu.jsp"%>
            <div class="clear col-sm-9 pr-30 pl-0">
                <div class="clear bg-white pb-100 plr-50-10-ipad pt-40">
                    <div class="clear">
                  		<!--   用户头像 -->
                        <div class="clear col-sm-3 pl-0 pr-0">
                            <div class="clear pull-left w-120">
                                <img class="img-120 overflow-h full-radius bor bor-3px bor-col-fff box-sha-imgsha user-avator" src="<%=request.getContextPath() %>/resources/img/default_header/userico_avatar.png" alt="">
                                <p class="text-center cur-p line-h-40 f-20 col-309DE2 set-heads">
                                   	 <spring:message code="name570"/>
                                </p>
                            </div>
                        </div>
                        <div class="clear col-sm-9 pl-0 pr-0">
                        	<!-- 用户名 -->
                            <p class="line-h-40 f-28 col-616161">
                                 <shiro:principal/>
                            </p>
                            <!-- 问候 -->
                            <p class="line-h-40 f-22 col-b0b0b0">
                              <%
								Date d = new Date();
								if(d.getHours() >= 6 && d.getHours() <= 12){
									out.println("Good morning!");
								}else if(d.getHours() >12 && d.getHours() <= 18){
									out.println("Good afternoon!");
								}else{
									out.println("Good evening!");
								}
								%>
                            </p>
                           <!-- 前往报告中心 -->
                           <p class="line-h-40 f-20 col-757575">
                           		<spring:message code="name571"/>
                            </p>
                        </div>
                        <div class="clear col-sm-12 mt-40 bor bor-t bor-col-e0e0e0">
                        </div>
                    </div>
                    
                    <!-- =================订单及激活服务 统计数据  快捷入口======================= -->
                    <div class="clear pt-40 pl-0 pr-0">
                        <div class="clear col-sm-6 pl-0 pr-0 pt-40 pb-40">
                            <div class="clear col-sm-5 pl-0 pr-0 text-center">
                                <img class="img-110 overflow-h full-radius" src="<%=request.getContextPath() %>/resources/img/user/User/center-tipsv1.png" alt="">
                            </div>
                            <div class="clear col-sm-7 pl-0 pr-0 pt-20 pb-20">
                                <p class="line-h-35 f-22 col-757575">
                                    	<spring:message code="name572"/><a class="col-309DE2 t-nonehove col-active unpaid-count" href="<%=request.getContextPath() %>/user/orderCenter?flag=1">0</a>
                                </p>
                                <p class="line-h-35 f-18 col-309DE2 t-nonehove">
                                    <a class="t-nonehove col-active" href="<%=request.getContextPath() %>/user/orderCenter?flag=1"><spring:message code="name573"/><span class="col-active icon-chevron-right f-14 ml-10 pos-r t--1"></span></a>
                                </p>
                            </div>
                        </div>
                        <div class="clear col-sm-6 pl-0 pr-0 pt-40 pb-40">
                            <div class="clear col-sm-5 pl-0 pr-0 text-center">
                                <img class="img-110 overflow-h full-radius" src="<%=request.getContextPath() %>/resources/img/user/User/center-tipsv2.png" alt="">
                            </div>
                            <div class="clear col-sm-7 pl-0 pr-0 pt-20 pb-20">
                                <p class="line-h-35 f-22 col-757575">
                                   	 <spring:message code="name574"/><a class="col-309DE2 t-nonehove col-active delivery-count" href="<%=request.getContextPath() %>/user/orderCenter?flag=3">0</a>
                                </p>
                                <p class="line-h-35 f-18 col-309DE2 t-nonehove">
                                    <a class="col-757575i t-nonehove" href="<%=request.getContextPath() %>/user/orderCenter?flag=3"><spring:message code="name575"/><span class="col-757575i icon-chevron-right f-14 ml-10 pos-r t--1"></span></a>
                                </p>
                            </div>
                        </div>
                        <div class="clear col-sm-6 pl-0 pr-0 pt-40 pb-40">
                            <div class="clear col-sm-5 pl-0 pr-0 text-center">
                                <img class="img-110 overflow-h full-radius" src="<%=request.getContextPath() %>/resources/img/user/User/center-tipsv3.png" alt="">
                            </div>
                            <div class="clear col-sm-7 pl-0 pr-0 pt-20 pb-20">
                                <p class="line-h-35 f-22 col-757575">
                                    	<spring:message code="name576"/><a class="col-309DE2 t-nonehove col-active appraise-count" href="<%=request.getContextPath() %>/user/orderCenter?flag=4">0</a>
                                </p>
                                <p class="line-h-35 f-18 col-309DE2 t-nonehove">
                                    <a class="col-757575i t-nonehove" href="<%=request.getContextPath() %>/user/orderCenter?flag=4"><spring:message code="name577"/><span class="col-757575i icon-chevron-right f-14 ml-10 pos-r t--1"></span></a>
                                </p>
                            </div>
                        </div>
                        <div class="clear col-sm-6 pl-0 pr-0 pt-40 pb-40">
                            <div class="clear col-sm-5 pl-0 pr-0 text-center">
                                <img class="img-110 overflow-h full-radius" src="<%=request.getContextPath() %>/resources/img/user/User/center-tipsv4.png" alt="">
                            </div>
                            <div class="clear col-sm-7 pl-0 pr-0 pt-20 pb-20">
                                <p class="line-h-35 f-22 col-757575">
                                    	<spring:message code="name578"/><a class="col-309DE2 t-nonehove col-active active-count" href="<%=request.getContextPath() %>/user/serviceCardPage">0</a>
                                </p>
                                <p class="line-h-35 f-18 col-309DE2 t-nonehove">
                                    <a class="col-757575i t-nonehove" href="<%=request.getContextPath() %>/user/serviceCardPage"><spring:message code="name579"/><span class="col-757575i icon-chevron-right f-14 ml-10 pos-r t--1"></span></a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   <%@ include file="../comm/footer.jsp" %> 
   
    <!-- =================选择头像弹出层======================= -->
    <div class="clear show-selhead affix t-0 l-0 z-999 full-wh rgba-60 dis-n">
        <div class="pos-a t-150 l-0 full-w">
            <div class="w-900 mg-0-auto clear pos-r bg-white selhead-content radius-5 t--300 modal-content">
                <div class="pt-10 pb-10 line-h-25 pos-r f-20 pl-20 pr-20 col-333 full-w radius-5 cur-d">
                   	 <spring:message code="name580"/>
                    <img class="pos-a t-10 r-20 img-20 close-mod cur-p" id="img_close" src="<%=request.getContextPath() %>/resources/img/user/User/close.png" alt="">
                </div>
                <div class="clear bor bor-t bor-col-f2 pd-20">
                    <div class="clear col-sm-9 h-364 bg-fcfcfc box-sha-inset-big pos-r pl-0 pr-0 overflow-h">
                        <div class="clear " style="width: 643px;height: 364px;">
                            <img id="show-main-img" class="max-w-100p" src="<%=request.getContextPath() %>/resources/img/user/User/user-head-portrait.jpg">
                        </div>
                    </div>
                    <div class="clear col-sm-3">
                        <div class="clear">
                            <img id="show-little-imgv1" class="img-184 img-thumbnail" src="" alt="">
                        </div>
                        <div class="clear mt-15">
                            <img id="show-little-imgv2" class="img-184 img-thumbnail full-radius" src="" alt="">
                        </div>
                    </div>
                    <div class="clear col-sm-12 pl-0 pr-0 mt-30 mb-10">
                        <div class="clear col-sm-9 btn-toolbar" role="toolbar">
                            <div class="btn-group pull-left col-sm-2" role="group">
                                <span class="btn btn-success pos-r cur-p">
                                    <input type="file" id="sel-file" class="opacity-0 pos-a t-0 l-0 full-wh cur-p"><spring:message code="name581"/>
                                </span>
                            </div>
                            <div class="btn-group pull-left col-sm-3" role="group">
                                <button type="button" class="btn btn-success cro-btn-reset"><spring:message code="name582"/></button>
                            </div>
                            <div class="btn-group pull-left pull-right" role="group">
                                <button type="button" class="btn btn-success cro-btn-big"><spring:message code="name583"/></button>
                                <button type="button" class="btn btn-success cro-btn-small"><spring:message code="name584"/></button>
                                <button type="button" class="btn btn-success cro-btn-left"><spring:message code="name585"/></button>
                                <button type="button" class="btn btn-success cro-btn-right"><spring:message code="name586"/></button>
                            </div>
                        </div>
                        <div class="clear col-sm-3">
                            <button class="btn full-w btn-success cro-btn-submit"><spring:message code="name587"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.validate.messages_zh.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/cropper/dist/cropper.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/menu.js"></script> 
    <script src="<%=request.getContextPath() %>/resources/js/user/userCenter.js"></script>
</body>
</html>