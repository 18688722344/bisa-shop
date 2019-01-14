<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="comm/tag.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--移动设备网页缩放比例--%>
    <meta name="viewport" content="initial-scale=0.7,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="indexTitle"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/swiper.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/reg/HK_Reg.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- 百度统计引入代码 -->
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?3f12daeedcda3dd17c0b977baf7f2051";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();

        var http_request = "<%=request.getContextPath() %>";
        var accountFlag = "<%=request.getAttribute("bindPhone") %>";
    </script>
</head>
<body onload="javascript:window.resizeTo(screen.availWidth,screen.availHeight)">
<div class="pos-r">

    <!-- 第三方账号绑定手机号悬浮窗口 -->
    <%@ include file="./reg/bindPhone.jsp" %>
    <div class="clear pos-a t-0 l-0 full-wh rgba-30 bind-phone-shadev z-99 dis-n"> </div>
    <%@ include file="./comm/header.jsp" %>

    <div class="wrap">
        <div class="container pl-0 pr-0">
            <!-- banner -->
            <div class="clear pos-r pl-0 pr-0">
                <div class="swiper-container swiper-container-main">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide cur-p" onClick="window.location.href='<%=request.getContextPath()%>/web/call/ecgAdvertisement?goodsNumber=BISA201812212041499694';">
                            <img class="full-w index-img-0" src="<%=request.getContextPath() %>/resources/img/index/banner5.jpg" alt="">
                        </div>
                        <div class="swiper-slide cur-p" onClick="window.location.href='<%=request.getContextPath()%>/web/call/newsContent?news_id=423423';">
                            <img class="full-w index-img-1" src="<%=request.getContextPath() %>/resources/img/index/banner3.jpg" alt="">
                        </div>
                        <div class="swiper-slide cur-p" onClick="window.location.href='<%=request.getContextPath()%>/web/call/newsContent?news_id=342342';">
                            <img class="full-w index-img-2" src="<%=request.getContextPath() %>/resources/img/index/banner4.jpg" alt="">
                        </div>
                        <div class="swiper-slide cur-p" onClick="window.location.href='<%=request.getContextPath()%>/web/call/newsContent?news_id=123423';">
                            <img class="full-w index-img-3" src="<%=request.getContextPath() %>/resources/img/index/banner2.png" alt="">
                        </div>
                    </div>
                    <!-- 如果需要分页器 -->
                    <div class="swiper-pagination swiper-paginationmian clear" style="width: auto;height: 48px;"></div>
                </div>
                <div class="clear pos-a t-0 l-0 full-wh rgba-30-white z-999 dis-n bannershadev1">
                </div>
            </div>

            <div class="clear full-w bgimg-interlayer text-center pt-50 pb-50 line-h-30">
                <a class="col-white t-nonehove f-18 cur-p family-h" onClick="window.location.href='<%=request.getContextPath()%>/web/call/ecgAdvertisement?goodsNumber=BISA201812212041499694';"><spring:message code="moreProduct"/> ></a>
            </div>

            <!-- 6张种类图 -->
             <div class="productCount">
                 <img width="1240" src="<%=request.getContextPath() %>/resources/img/index/1-6 3（2）@2x.png"/>
              <%--   <div class="count_1"><img src="<%=request.getContextPath() %>/resources/img/index/productCount_1.png" alt=""></div>
                 <div class="count_2"><img src="<%=request.getContextPath() %>/resources/img/index/productCount_2.png" alt=""></div>
                 <div class="count_1"><img src="<%=request.getContextPath() %>/resources/img/index/productCount_3.png" alt=""></div>
                 <div class="count_2"><img src="<%=request.getContextPath() %>/resources/img/index/productCount_4.png" alt=""></div>
                 <div class="count_1"><img src="<%=request.getContextPath() %>/resources/img/index/productCount_5.png" alt=""></div>
                 <div class="count_2"><img src="<%=request.getContextPath() %>/resources/img/index/productCount_6.png" alt=""></div>--%>
             </div>

            <!-- 主要内容 -->
            <div class="clear pos-r pl-0 pr-0 full-w">
                <div class="swiper-container swiper-container-tipsv1 full-w">
                    <div class="swiper-wrapper">
                        <!-- slide1 -->
                        <div class="swiper-slide protips">
                            <div class="full-w pl-0 pr-0 bor bor-r pos-r bor-col-E9E9E9 protipssidev1 shodow-tip min-h-600">
                                <div class="clear shodow-tipl">
                                    <div class="clear shodow-tipr">
                                        <div class="clear full-h bor bor-l bor-col-E9E9E9 pos-a t-9 l-0 t-0">
                                        </div>
                                        <div class="clear ">
                                            <div class="clear full-w text-center pt-30 pb-30 bg-e2f3fe cur-p" onclick="window.location.href='<%=request.getContextPath() %>/web/call/ecgAdvertisement?goodsNumber=BISA201812212041499694';">
                                                <img class="" src="<%=request.getContextPath() %>/resources/img/index/producttips1.png" alt="">
                                            </div>
                                            <div class="clear pt-30 protipsplmedia cur-d pb-80-20-ipad">
                                                <p class="line-h-30 h-30 f-16 f-w col-252525">
                                                    <spring:message code="xixinProduct"/>
                                                </p>
                                                <p class="line-h-25 h-45 f-14 col-8c8c8c pt-20 w-b">
                                                    <spring:message code="considerate"/>,
                                                    <spring:message code="electrocardiogram"/>,
                                                    <spring:message code="record"/>,
                                                    <spring:message code="stockpile"/>
                                                </p>

                                                <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                </p>
                                                <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                </p>
                                                <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                </p>
                                                <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                </p>
                                                <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                </p>
                                                <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                </p>
                                                <p class="line-h-25 h-25 f-14 pt-20 w-b">
                                                    <a class="col-8c8c8c t-nonehove hovecol-333" href="<%=request.getContextPath() %>/web/call/ecgAdvertisement?goodsNumber=BISA201812212041499694"><spring:message code="detail"/> ></a>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- slide2 -->
                        <div class="swiper-slide protips">
                            <div class="full-w pl-0 pr-0 bor bor-r bor-col-E9E9E9 shodow-tip min-h-600">
                                <div class="clear shodow-tipl">
                                    <div class="clear shodow-tipr">
                                        <div class="clear full-w text-center pt-30 pb-30 bg-e2f3fe cur-p" onclick="window.location.href='<%=request.getContextPath() %>/web/call/goodsDetail?goodsNumber=BISA201812212045364724';">
                                            <img src="<%=request.getContextPath() %>/resources/img/index/producttips6.png" alt="">
                                        </div>
                                        <div class="clear pt-30 protipsplmedia cur-d pb-80-20-ipad" white-space:normal>
                                            <p class="line-h-30 h-30 f-16 f-w col-252525">
                                                <spring:message code="name1"/>
                                            </p>

                                            <p class="line-h-25 h-45 f-14 col-8c8c8c pt-20 w-b">
                                                <spring:message code="name4"/>,
                                                <spring:message code="name5"/>
                                            </p>

                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>

                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 pt-20 w-b">
                                                <a class="col-8c8c8c t-nonehove hovecol-333" href="<%=request.getContextPath() %>/web/call/goodsDetail?goodsNumber=BISA201812212045364724"><spring:message code="detail"/> ></a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- slide3 -->
                        <div class="swiper-slide protips">
                            <div class="full-w pl-0 pr-0 bor bor-r bor-col-E9E9E9 shodow-tip min-h-600 ">
                                <div class="clear shodow-tipl">
                                    <div class="clear shodow-tipr">
                                        <div class="clear full-w text-center pt-30 pb-30 bg-e2f3fe cur-p"
                                             onclick="window.location.href='<%=request.getContextPath() %>/web/call/goodsDetail?goodsNumber=BISA201812212007406365';">
                                            <img src="<%=request.getContextPath() %>/resources/img/index/producttips10.jpg" alt="">
                                        </div>
                                        <div class="clear pt-30 protipsplmedia pb-80-20-ipad cur-d">
                                            <p class="line-h-30 h-30 f-16 f-w col-252525">
                                                <spring:message code="name6"/>（<spring:message code="name7"/>）
                                            </p>
                                            <p class="line-h-25 h-40 f-14 col-8c8c8c pt-20 w-b">
                                                <spring:message code="name8"/>,
                                                <spring:message code="name9"/>,
                                                <spring:message code="name10"/>,
                                                <spring:message code="name11"/>,
                                                <spring:message code="name12"/>
                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                            </p>
                                            <p class="line-h-25 h-25 f-14 pt-20 w-b">
                                                <a class="col-8c8c8c t-nonehove hovecol-333" href="<%=request.getContextPath() %>/web/call/goodsDetail?goodsNumber=BISA201812212007406365"><spring:message code="detail"/> ></a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- slide4 -->
                        <div class="swiper-slide protips">
                            <div class="full-w pl-0 pr-0 bor bor-r bor-col-E9E9E9 min-h-600">
                                <div class="clear full-w pull-left protipshalfheight shodow-tipv2">
                                    <div class="clear shodow-tipr">
                                        <div class="clear shodow-tipl">
                                            <div class="clear shodow-tipr">
                                                <div class="clear full-w text-center pt-30 pb-30 bg-e2f3fe cur-p "
                                                     onclick="window.location.href='<%=request.getContextPath() %>/web/call/goodsDetail?goodsNumber=BISA201812212002536092';">
                                                    <img src="<%=request.getContextPath() %>/resources/img/index/producttips11.png" alt="">
                                                </div>
                                                <div class="clear pt-30 protipsplmedia pb-80-20-ipad cur-d">
                                                    <p class="line-h-30 h-30 f-16 f-w col-252525">
                                                        <spring:message code="name_12"/>
                                                    </p>
                                                    <p class="line-h-25 h-40 f-14 col-8c8c8c pt-20 w-b">
                                                        <spring:message code="name_13"/>
                                                        <spring:message code="name_14"/>,
                                                        <spring:message code="name_15"/>,
                                                        <spring:message code="name_16"/>,
                                                        <spring:message code="name_17"/>,
                                                        <spring:message code="name_18"/>
                                                    </p>

                                                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                    </p>
                                                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                    </p>
                                                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                    </p>
                                                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                    </p>
                                                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                    </p>
                                                    <p class="line-h-25 h-25 f-14 col-8c8c8c">

                                                    </p>
                                                    <p class="line-h-25 h-25 f-14 pt-20 w-b">
                                                        <a class="col-8c8c8c t-nonehove hovecol-333" href="<%=request.getContextPath() %>/web/call/goodsDetail?goodsNumber=BISA201812212002536092"><spring:message code="detail"/> ></a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 这里是控制部分，目前只有四个所以先把这个隐藏了 -->
                    <div class="clear pos-a t-0 l-0 rgba-90 z-99 dis-n">
                        <div class="swiper-button-prev swiper-tipsv1-button-prev"></div>
                        <div class="swiper-button-next swiper-tipsv1-button-next"></div>
                    </div>
                </div>
                <div class="clear pos-a t-0 l-0 full-wh rgba-30 tipsshadev1 z-99">
                </div>
            </div>
        </div>
    </div>
    <%@ include file="./comm/footer.jsp" %>
</div>
<script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/comm/bootstrapValidator.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/comm/swiper.jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
<script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/reg/bindphone.js"></script>
<script>
    var mySwiper = new Swiper ('.swiper-container-main', {
        loop: true,
        autoplay : 3000,
        autoplayDisableOnInteraction : false,
        autoHeight: true,
        pagination: '.swiper-paginationmian',
        paginationClickable :true,
        paginationBulletRender: function (swiper, index, className) {
            return '<span class="' + className + '">' + (index + 1) + '<div class=\"mainswiper-pagination-bullet-div\"></div>'+'</span>';
        }
    })
    var mySwiperv2 = new Swiper ('.swiper-container-tipsv1', {
        prevButton:'.swiper-tipsv1-button-prev',
        nextButton:'.swiper-tipsv1-button-next',
        slidesPerView : 4,
        spaceBetween : 0,
    })
    /*鼠标悬停控制轮播播放或者停止*/
    $('.swiper-container-main').mouseenter(function () {
        mySwiper.stopAutoplay();
    })
    $('.swiper-container-main').mouseleave(function () {
        mySwiper.startAutoplay();
    })
</script>
<script src="<%=request.getContextPath() %>/resources/js/index/index.js"></script>
</body>
</html>