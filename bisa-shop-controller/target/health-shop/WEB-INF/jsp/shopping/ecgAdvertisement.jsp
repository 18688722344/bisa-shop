<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.bisa.health.basic.entity.SystemContext"%>
<%@ include file="../comm/tag.jsp" %>
<% String menuType = "sys3"; %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=0.3,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name452"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath()%>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/shopping/HK_Pro_details.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body onload="javascript:window.resizeTo(screen.availWidth,screen.availHeight)">
    <%@ include file="../comm/header.jsp" %>
            <div class="clear full-w bor bor-t bor-col-D6D6D6 sha-float-titlev1 flo-menuv2">
                    <div class="containerv2 pt-10 pb-10 clear line-h-30  plr-0-10-ipad">
                            <span class="col-black family-h pull-left cur-d f-20 main-product-title " style="margin-left:380px;"><spring:message code="name453"/></span>
                                <span class="col-black family-h pull-right f-12" >
                                    <!-- 主商品才有介绍（宣传页） -->
                                     <a href="<%=request.getContextPath()%>/web/call/goodsDetail?goodsNumber=BISA201812212041499694">
                                          <button class="pull-right f-20 line-h-30 h-30 text-center bor-none bg-309DE2 hovbg-2D90CF col-white pl-20 pr-20 go-top" style="margin-right:380px;">
                                           <spring:message code="name206"/>
                                          </button>
                                      </a>
                             </span>
                    </div>
            </div>
            <div class="clear full-w bor bor-t bor-col-D6D6D6 sha-float-titlev1 affix t-0 l-0 bg-white z-99 dis-n flo-menuv2-aff">
                    <div class="containerv2 pt-10 pb-10 clear line-h-30 plr-0-10-ipad">
                            <span class="col-black family-h pull-left cur-d f-20 main-product-title" style="margin-left:380px;"><spring:message code="name453"/></span>
                            <a href="<%=request.getContextPath()%>/web/call/goodsDetail?goodsNumber=BISA201812212041499694">
                                <button class="pull-right f-20 line-h-30 h-30 text-center bor-none bg-309DE2 hovbg-2D90CF col-white pl-20 pr-20 go-top" style="margin-right:380px;">
                                    <spring:message code="name206"/>
                                </button>
                            </a>
                    </div>
            </div>
    <!-- 主要内容 -->
    <div class="wrap clear">
        <!-- first floor -->
        <div class="clear full-w min-h-600-500-ipad first-floor ">
            <div class="container clear">
                <div class="col-sm-5 clear pt-82 pb-82">
                    <img class="pull-right" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/first-floor-tipsv1.png" alt="">
                </div>
                <div class="col-sm-7 clear pt-170 pb-150">
                    <div class="clear pull-left pos-r t-20 opa-0 first-floor-aniv1 ani-topa-re" white-space:pre-wrap>
                        <p class="f-38-28-ipad col-309DE2 cur-d line-h-40 h-40 fw-600" white-space:pre-wrap>
                            <spring:message code="name454"/>
                            <span
                                    class="f-20 dis-ib h-40 line-h-40 pos-r  top--5 col-309DE2 ml-10-5-ipad bor pl-15 pr-15 bor-col-309DE2 radius-3"><spring:message code="name455"/></span>
                        </p>
                        <p class="line-h-25 h-25 f-14 col-8c8c8c">

                        </p>
                        <p class="full-w col-929292 h-40 line-h-40 f-30-24-ipad mt-40 mr-30 cur-d" white-space:pre-wrap>
                            <spring:message code="name456"/>
                        </p>

                    </div>
                    <div class="clear mt-60 full-w pull-left col-309DE2 cur-d line-h-40 h-40 fw-600 f-30-24-ipad  pos-r t-20 opa-0 first-floor-aniv2" >
                        <spring:message code="name103"/>&nbsp;${goods.goodsPrice}
                    </div>
                </div>
            </div>
        </div>
        <!-- second floor -->
        <div class="clear full-w min-h-600 bg-f8f7f5 second-floor">
            <div class="container clear">

                <div class="clear text-center f-20-16-ipad cur-d pt-10 pb-10">
                    <div class="clear dis-ib">
                        <p class="col-666 line-h-25 h-25 mt-10 mb-10 second-floor-aniv2 pos-r t-20 opa-0">
                           <spring:message code="name459"/>
                        </p>
                        <p class="col-666 line-h-25 h-25 mt-10 mb-10 full-w text-left second-floor-aniv3 pos-r t-20 opa-0">
                            <spring:message code="name460"/>
                        </p>
                    </div>
                </div>
                <div class="col-sm-5 clear text-center pt-50">
                    <img class="" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/second-floor-tipsv1.png" alt="">
                </div>
                <div class="col-sm-2 clear text-center pt-50 min-h-300 pl-0 pr-0 line-h-300 cur-d select-none">
                    <span class="icon-circle mr-5 ml-5 col-309DE2 f-14"></span>
                    <span class="icon-circle mr-5 ml-5 col-309DE2 f-14"></span>
                    <span class="icon-circle mr-5 ml-5 col-309DE2 f-14"></span>
                    <span class="icon-circle mr-5 ml-5 col-309DE2 f-14"></span>
                </div>
                <div class="col-sm-5 clear text-center pt-75">
                    <img class="" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/second-floor-tipsv2.png" alt="">
                </div>
            </div>
        </div>
        <!-- third floor -->
        <div class="clear full-w min-h-860-650-ipad third-floor">
            <div class="container clear">
                <!-- third floor duplexv1-->
                <div class="clear text-center  cur-d">
                    <p class="f-38 col-black mt-40 line-h-40 h-40 third-floor-aniv1 pos-r t-20 opa-0">
                        <spring:message code="name461"/>
                    </p>
                    <p class="f-50 col-309DE2 mt-10 line-h-60 h-60 third-floor-aniv2 pos-r t-20 opa-0">
                        <spring:message code="name462"/>
                    </p>
                </div>
                <!-- third floor duplexv2-->
                <div class="clear cur-d">
                    <div class="clear full-w mt-20 mb-20 third-floor-aniv3 pos-r t-20 opa-0">
                        <div class="col-sm-3"></div>
                        <div class="col-sm-3 pos-r">
                            <div class="clear full-w thirdf-w-is-h full-radius bor bor-3px bor-col-309DE2 pos-r z-9 overflow-h">
                                <img class="full-w pos-a img-thirdfloor-tips z-1 max-w-200p hov-thirdimg" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/third-floor-tipsv1.png" alt="">
                            </div>
                            <div class="clear thirdf-w-is-hv2 full-radius bor bor-3px bor-col-309DE2 z-99 pos-a bg-white text-center">
                                <span class="col-309DE2 f-36-18-ipad mt-5 mb-5 dis-ib">1</span>
                            </div>
                            <p class="text-center f-16 col-black h-20 line-h-20 mt-10 mb-10">
                                <spring:message code="name463"/>
                            </p>
                        </div>
                        <div class="col-sm-3 pos-r">
                            <div class="clear full-w thirdf-w-is-h full-radius bor bor-3px bor-col-309DE2 pos-r z-9 overflow-h">
                                <img class="full-w pos-a img-thirdfloor-tips z-1 max-w-200p hov-thirdimg" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/third-floor-tipsv2.png" alt="">
                            </div>
                            <div class="clear thirdf-w-is-hv2 full-radius bor bor-3px bor-col-309DE2 z-99 pos-a bg-white text-center">
                                <span class="col-309DE2 f-36-18-ipad mt-5 mb-5 dis-ib">2</span>
                            </div>
                            <p class="text-center f-16 col-black h-20 line-h-20 mt-10 mb-10">
                                <spring:message code="name464"/>
                            </p>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                    <div class="clear full-w mt-20 third-floor-aniv4 pos-r t-20 opa-0">
                        <div class="col-sm-3"></div>
                        <div class="col-sm-3 pos-r">
                            <div class="clear full-w thirdf-w-is-h full-radius bor bor-3px bor-col-309DE2 pos-r z-9 overflow-h">
                                <img class="full-w pos-a img-thirdfloor-tips z-1 max-w-200p hov-thirdimg" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/third-floor-tipsv3.png" alt="">
                            </div>
                            <div class="clear thirdf-w-is-hv2 full-radius bor bor-3px bor-col-309DE2 z-99 pos-a bg-white text-center">
                                <span class="col-309DE2 f-36-18-ipad mt-5 mb-5 dis-ib">3</span>
                            </div>
                            <p class="text-center f-16 col-black h-20 line-h-20 mt-10 mb-10">
                                <spring:message code="name465"/>
                            </p>
                        </div>
                        <div class="col-sm-3 pos-r">
                            <div class="clear full-w thirdf-w-is-h full-radius bor bor-3px bor-col-309DE2 pos-r z-9 overflow-h">
                                <img class="full-w pos-a img-thirdfloor-tips z-1 max-w-200p hov-thirdimg" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/third-floor-tipsv4.png" alt="">
                            </div>
                            <div class="clear thirdf-w-is-hv2 full-radius bor bor-3px bor-col-309DE2 z-99 pos-a bg-white text-center">
                                <span class="col-309DE2 f-36-18-ipad mt-5 mb-5 dis-ib">4</span>
                            </div>
                            <p class="text-center f-16 col-black h-20 line-h-20 mt-10 mb-10">
                                <spring:message code="name466"/>
                            </p>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- fourth floor -->
        <div class="clear full-w min-h-600 pos-r overflow-h bg-a0d8f8 fourth-floor">
            <div class="clear pos-r z-99 full-w min-h-600">
                <div class="container clear">
                    <div class="pt-110">
                        <p class="col-white f-50-40-ipad line-h-60 h-60 text-center cur-d fourth-floor-aniv1 pos-r t-20 opa-0">
                            <spring:message code="name467"/>
                        </p>
                    </div>
                    <div class="pt-75">
                        <p class="col-423f3f f-38-28-ipad line-h-60 pl-40 pr-40 cur-d text-ind fourth-floor-aniv2 pos-r t-20 opa-0">
                            <spring:message code="name468"/>
                        </p>
                    </div>
                </div>
            </div>
            <img class="z-1 pos-a w-1920 max-w-1920 img-peodetailfv4-1" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/fourth-floor-bgv2.png" alt="">
        </div>
        <!-- fifth floor -->
        <div class="clear full-w min-h-860 bg-white overflow-h fifth-floor">
            <div class="container clear pos-r z-9">
                <div class="clear full-w pt-110 cur-d z-9 pos-r">
                    <p class="col-309DE2 f-50-40-ipad line-h-60 h-60 mt-20 mb-20 fifth-floor-aniv1 pos-r t-20 opa-0">
                       <spring:message code="name469"/>
                    </p>
                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                    </p>
                    <p class="col-309DE2 f-50-40-ipad line-h-60 h-60 mt-20 mb-20 fifth-floor-aniv2 pos-r t-20 opa-0">
                        <spring:message code="name470"/>
                    </p>
                </div>
                <div class="clear full-w pt-280-200-ipad cur-d z-9 pos-r">
                    <div class="clear pull-right col-sm-7">
                        <p class="col-666 f-26-20-ipad line-h-35 pt-20 pb-30 bor bor-b bor-col-d2d2d2 fifth-floor-aniv3 pos-r t-20 opa-0">
                            <spring:message code="name471"/>
                        </p>
                    </div>
                </div>
                <div class="clear full-w pt-20 cur-d z-9 pos-r">
                    <div class="clear pull-right col-sm-7">
                        <p class="col-979797 f-18-14-ipad line-h-25 fifth-floor-aniv4 pos-r t-20 opa-0">
                            <spring:message code="name472"/>
                        </p>
                    </div>
                </div>
                <img class="pos-a z-1 opa-0 fifth-floor-aniv5" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/fifth-floor-bgv2.png" alt="" >
                <img class="pos-a z-1 opa-0 fifth-floor-aniv6" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/fifth-floor-bgv3.png" alt="">
            </div>
        </div>
        <!-- six floor -->
        <div class="clear full-w min-h-600 bg-a7dbfb six-floor">
            <div class="container clear">
                <div class="clear pt-82 text-center">
                    <p class="text-center col-white f-50-40-ipad cur-d line-h-55 h-55 six-floor-aniv1 pos-r t-20 opa-0">
                       <spring:message code="name473"/>
                    </p>
                    <div class="clear dis-ib">
                        <p class="col-white f-26 cur-d line-h-30 mt-10 mb-10 six-floor-aniv2 pos-r t-20 opa-0">
                           <spring:message code="name474"/>
                        </p>
                        <p class="col-white f-26 cur-d line-h-30 full-w text-left mt-10 mb-10 six-floor-aniv3 pos-r t-20 opa-0">
                             <spring:message code="name475"/>
                        </p>
                    </div>
                </div>
                <div class="clear text-center mt-30">
                    <div class="clear img-300 bg-white full-radius dis-ib text-center line-h-300">
                        <img class="six-floor-aniv4 pos-r t-20 opa-0" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/six-floor-tipsv1.png" alt="">
                    </div>
                </div>
            </div>
        </div>
        <!-- seven floor -->
        <div class="clear full-w min-h-860-650-ipad bg-f8f7f5 seven-floor">
            <div class="container clear cur-d">
                <div class="col-sm-8 clear pt-80">
                    <p class="col-309DE2 f-50-36-ipad line-h-60 h-60 mt-5 mb-5 seven-floor-aniv1 pos-r t-20 opa-0">
                        <spring:message code="name476"/>
                    </p>
                    <p class="col-309DE2 f-50-36-ipad line-h-60 h-60 mt-5 mb-5 seven-floor-aniv2 pos-r t-20 opa-0">
                        <spring:message code="name477"/>
                    </p>
                    <p class="col-666 f-36-28-ipad line-h-45 h-45 mt-20 mb-20 seven-floor-aniv3 pos-r t-20 opa-0">
                        <spring:message code="name478"/>
                    </p>
                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                    </p>
                    <p class="col-666 f-26-20-ipad line-h-35 mt-30 mb-15 seven-floor-aniv4 pos-r t-20 opa-0">
                        <spring:message code="name479"/>
                    </p>
                </div>
                <div class="col-sm-4 clear pl-50 pt-80">
                    <img class="seven-floor-aniv5 pos-r t-20 opa-0" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/seven-floor-tipsv1.png" alt="">
                </div>
            </div>
        </div>
        <!-- eight floor -->
        <div class="clear full-w min-h-760-650-ipad eight-floor">
            <div class="container clear cur-d plr-15-0-ipad">
                <div class="col-sm-7 clear min-h-760 pos-r eight-floor-aniv1 t-20 opa-0">
                    <img class="pos-a b-0 l-0" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/eight-floor-tipsv1.png" alt="">
                </div>
                <div class="col-sm-5 clear pt-130">
                    <p class="col-309DE2 f-50-38-ipad line-h-60 h-60 mtb-20-0-ipad eight-floor-aniv1 pos-r t-20 opa-0">
                        <spring:message code="name480"/>
                    </p>
                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                    </p>
                    <p class="col-309DE2 f-50-38-ipad line-h-60 h-60 mtb-20-0-ipad eight-floor-aniv2 pos-r t-20 opa-0">
                        <spring:message code="name481"/>
                    </p>
                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                    </p>
                    <p class="col-666 f-26-20-ipad line-h-40 mt-30-0-iapd pt-30 mb-15 eight-floor-aniv3 pos-r t-20 opa-0">
                       <spring:message code="name482"/>
                    </p>
                </div>
            </div>
        </div>
        <!-- nine floor -->
        <div class="clear full-w min-h-860-760-ipad bg-f8f7f5 nine-floor">
            <div class="container clear cur-d">
                <div class="col-sm-7 clear pt-160-100-ipad">
                    <p class="col-309DE2 f-50-40-ipad line-h-60 h-60 mt-10 mb-10 nine-floor-aniv1 pos-r t-20 opa-0">
                        <spring:message code="name483"/>
                    </p>
                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                    </p>
                    <p class="col-666 f-40-26-ipad line-h-60 h-60 mt-10 mb-10 nine-floor-aniv2 pos-r t-20 opa-0">
                        <spring:message code="name484"/>
                    </p>
                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                    </p>
                    <p class="col-666 f-24-16-ipad line-h-40 mt-10 mb-20 pt-50 nine-floor-aniv3 pos-r t-20 opa-0">
                        <spring:message code="name485"/>
                    </p>
                    <div class="clear text-center mt-25 pt-30">
                        <img class="nine-floor-aniv4 pos-r t-20 opa-0" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/nine-floor-tipsv1.png" alt="">
                    </div>
                </div>
                <div class="col-sm-5 clear pos-r min-h-860-760-ipad nine-floor-aniv4 t-20 opa-0">
                    <img class="pos-a b-0 r-30-0-ipad" src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/nine-floor-tipsv2.png" alt="">
                </div>
            </div>
        </div>
        <!-- ten floor -->
        <!--<div class="clear full-w min-h-250 ten-floor">
            <div class="container clear cur-d">
                <div class="clear col-sm-4 text-center pt-35 pb-35 text-center">
                    <div class="clear img-120 full-radius bg-a3d9fb mt-30 mb-10 line-h-120 text-center dis-ib">
                        <img src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/ten-floor-tipsv1.png" alt="">
                    </div>
                    <div class="full-w col-979797 f-18 text-center">
                        <spring:message code="name486"/>
                    </div>
                </div>
                <div class="clear col-sm-4 text-center pt-35 pb-35 text-center">
                    <div class="clear img-120 full-radius bg-a3d9fb mt-30 mb-10 line-h-120 text-center dis-ib">
                        <img src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/ten-floor-tipsv2.png" alt="">
                    </div>
                    <div class="full-w col-979797 f-18 text-center">
                        <spring:message code="name487"/>
                    </div>
                </div>
                <div class="clear col-sm-4 text-center pt-35 pb-35 text-center">
                    <div class="clear img-120 full-radius bg-a3d9fb mt-30 mb-10 line-h-120 text-center dis-ib">
                        <img src="<%=request.getContextPath() %>/resources/img/HK_Pro_details/ten-floor-tipsv3.png" alt="">
                    </div>
                    <div class="full-w col-979797 f-18 text-center">
                        <spring:message code="name488"/>
                    </div>
                </div>
            </div>
        </div>-->
    </div>



   <%@ include file="../comm/footer.jsp" %>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/shopping/ecgAdvertisement.js"></script>
	<script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
</body>
</html>