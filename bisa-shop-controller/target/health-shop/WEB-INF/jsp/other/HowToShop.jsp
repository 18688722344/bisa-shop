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
    <meta name="viewport" content="initial-scale=0.2,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
   <title><spring:message code="name416"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/news/HK_News.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script type="text/javascript">
		var http_request ="<%=request.getContextPath() %>";
		var request_url ="<%=request.getMethod() %>";
		var request_type ="<%=request.getAttribute("request_type") %>";
	</script>
</head>
<body>
  	<%@ include file="../comm/header.jsp" %> 
    <div class="wrap clear">
        <div class="container clear pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" id="index" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/> </a> >
                    <span class="col-252525 t-nonehove"><spring:message code="shopGuide"/></span>
                </p>
            </div>
        </div>
        <div class="clear full-ws">
            <img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/banner.jpg" alt="">
            <p class="full-w-p"><spring:message code="name777"/></p>
            <p class="full-w-p-1"><spring:message code="name776"/></p>
            <p class="full-w-p-3">SHOPPING DIRECTORY</p>
        </div>
        <div class="container pl-0 pr-0 clear bg-f5f5f5 pt-30 pb-70 mb-60">
            <div class="col-sm-3 pl-30 pr-20">
                <div class="clear bg-white pd-40-10-ipad">
                    <p class="mt-10 mb-10 col-212121 f-30 line-h-40 cur-d">
                        <spring:message code="shopGuide"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-40 mb-20 cur-p hovecol-309DE2 col-active HTS-list HTS-listv1" id="HTS-listv1">
                        <spring:message code="name15"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 HTS-list HTS-listv2" id="HTS-listv2">
                        <spring:message code="name16"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 HTS-list HTS-listv3" id="HTS-listv3">
                        <spring:message code="name17"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 HTS-list HTS-listv4" id="HTS-listv4">
                        <spring:message code="name18"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 HTS-list HTS-listv5" id="HTS-listv5">
                        <spring:message code="name19"/>
                    </p>
                    <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 HTS-list HTS-listv6" id="HTS-listv6">
                        <spring:message code="name20"/>
                    </p>
                </div>
            </div>

            <div class="clear col-sm-9 pr-30 pl-0">
                <div class="clear bg-white HTS-tips HTS-tipsv1 pb-280">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 pl-50 line-h-70-50-ipad pb-20">
                        <spring:message code="name15"/>
                    </p>
                    <div class="clear ml-50 mr-50 bor bor-b bor-col-f5f5f5">
                    </div>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50">
                        <spring:message code="name417"/>
                    </p>
                    <%--<img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/HTS-tipsv1-explainv1.jpg" alt="">--%>
                    <div class="add_car">
                        <img class="add_car_img" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/prod_dail.png"/></img>
                        <div class="add_car_right">
                            <p class="add_car_p1"><spring:message code="name454"/></p>
                            <p class="add_car_p2"><spring:message code="name781"/></p>
                            <p class="add_car_p3"></p>
                            <p class="add_car_p4">1800<spring:message code="name_452"/></p>
                            <p class="add_car_p5"></p>
                            <p class="add_car_p6"><spring:message code="name782"/></p>
                            <p class="add_car_p7">
                                <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/two_xgou.png" alt="">
                                <span><spring:message code="name783"/></span>
                            </p>
                            <p class="add_car_p8"></p>
                            <p class="add_car_p9">
                                <span class="add_car_p9_span1"><spring:message code="name784"/></span>
                                <span class="add_car_p9_span2"><spring:message code="name785"/></span></p>
                            <p class="add_car_p10"><i class="add_car_p10_i"></i><span class="add_car_p10_span">
                                <spring:message code="name786"/></span></p>
                        </div>
                    </div>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50">
                        <spring:message code="name418"/>
                    </p>
                    <p class="mycar"><spring:message code="name438"/></p>
                    <div class="mycar_balance">
                          <div class="mycar_balance_tile">
                              <div class="tile_th mycar_balance_tile1">
                                  <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_true.png" alt=""
                                  /><spring:message code="name787"/></div>
                              <div class="tile_th mycar_balance_tile1"><spring:message code="name440"/></div>
                              <div class="tile_th mycar_balance_tile2"><spring:message code="name441"/></div>
                              <div class="tile_th mycar_balance_tile3"><spring:message code="name442"/></div>
                              <div class="tile_th mycar_balance_tile4"><spring:message code="name443"/></div>
                              <div class="tile_th mycar_balance_tile5"><spring:message code="name444"/></div>
                          </div>

                        <div class="mycar_balance_datil">
                            <div class="b_datil mycar_balance_datil1">
                                <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_true.png" alt="" /></div>
                            <div class="b_datil mycar_balance_datil2"><img
                                    src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_produ.png" alt="">
                                <p> <spring:message code="name454"/></p></div>
                            <div class="b_datil mycar_balance_datil3">1800<spring:message code="name_452"/></div>
                            <div class="b_datil mycar_balance_datil4"><img
                                    src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_num.png" alt="" /></div>
                            <div class="b_datil mycar_balance_datil5">1800<spring:message code="name_452"/></div>
                            <div class="b_datil mycar_balance_datil6"><img
                                    src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_err.png" alt="" /></div>
                        </div>

                        <div class="add_sver">
                            <p class="add_sver_p1">
                                <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_add.png" alt="">
                                <spring:message code="name788"/> <span class=""><spring:message code="name789"/></span>
                            </p>
                            <p class="add_sver_p2">
                                <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_add.png" alt="">
                                <spring:message code="name788"/> <span class=""><spring:message code="name789"/></span>
                            </p>
                        </div>

                        <div class="ctnue">
                            <div class="ctnue_float ctnue_left">
                                <p class="ctnue_left_p">
                                    <spring:message code="name790"/> |  <spring:message code="name446"/>
                                <span class="ctnue_span_color ctnue_left_span">1</span> <spring:message code="name447"/>
                                <span class="ctnue_span_color ctnue_left_span">1</span> <spring:message code="name448"/>
                                </p>
                            </div>
                            <div class="ctnue_float ctnue_centre">
                                <spring:message code="name449"/> 0.00  <spring:message code="name_452"/> | <span
                                    class="ctnue_span_color"><spring:message code="name450"/>100.00<spring:message code="name_452"/></span>
                            </div>
                            <div class="ctnue_float ctnue_right"><span class="ctnue_right_span">
                                 <spring:message code="name791"/></span></div>
                        </div>

                    </div>
                   <%-- <img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/HTS-tipsv1-explainv2.jpg" alt="">--%>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50">
                        <spring:message code="name419"/>
                    </p>

                    <div class="place_order">
                          <div class="order_dail_top">
                              <div class="order_dail_top_1">
                              <span class="order_span_left"><spring:message code="name119"/></span>
                              <span class="order_span_right"><spring:message code="name120"/> > </span>
                          </div>
                           <div class="bro_btm"></div>
                              <div class="pdu_list">
                                      <div class="ord_flot_lf pdu_list_1">
                                          <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_produ.png" alt="">
                                          <spring:message code="name792"/></div>
                                       <div class="ord_flot_lf pdu_list_2">1800 x 1</div>
                                       <div class="ord_flot_lf pdu_list_3"><spring:message code="name793"/></div>
                                       <div class="ord_flot_lf pdu_list_4">1800<spring:message code="name_452"/></div>
                               </div>

                              <div class="pdu_list">
                                  <div class="ord_flot_lf pdu_list_1">
                                      <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/ioc_produ.png"
                                           alt="">
                                      <spring:message code="name792"/></div>
                                  <div class="ord_flot_lf pdu_list_2">1800 x 1</div>
                                  <div class="ord_flot_lf pdu_list_3"><spring:message code="name793"/></div>
                                  <div class="ord_flot_lf pdu_list_4">1800<spring:message code="name_452"/></div>
                              </div>
                              <div class="bro_btm"></div>

                              <div class="pre_list">
                                 <div class="pre_list_1"><p class="pre_list_sp_color">2 件</p><p class="pre_list_sp_mgl"><spring:message
                                         code="name794"/>：</p></div>
                                  <div class="pre_list_1"> <p
                                          class="pre_list_sp_color">2 <spring:message code="name448"/></p><p
                                          class="pre_list_sp_mgl"><spring:message code="name795"/>：
                                  </p></div>
                                  <div class="pre_list_1"> <p class="pre_list_sp_color">3600<spring:message code="name_452"/>
                                      </p><p class="pre_list_sp_mgl"><spring:message code="name796"/>：</p></div>
                                  <div class="pre_list_1"> <p class="pre_list_sp_color">-0<spring:message code="name_452"/></p><p
                                          class="pre_list_sp_mgl"><spring:message code="name797"/>：
                                  </p></div>
                                  <div class="pre_list_1"> <p class="pre_list_sp_color">3630<spring:message code="name_452"/></p><p
                                          class="pre_list_sp_mgl"><spring:message code="name805"/>：
                                      </p></div>
                              </div>

                              <div class="bro_btm"></div>

                              <div class="rq_order">
                                               <div class="rq_order_left">
                                                   <p><spring:message code="name799"/> 138****8000</p>
                                                   <p><spring:message code="name800"/> <span class="rq_order_left_sp"><spring:message
                                                           code="name801"/></span></p>
                                                   </div>
                                               <div class="rq_order_right">
                                                     <span class="rq_order_btn"><spring:message code="name798"/></span>
                                               </div>
                              </div>
                          </div>


                    </div>
                   <%-- <img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/HTS-tipsv1-explainv3.jpg" alt="">--%>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50">
                        <spring:message code="name420"/>
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50 zfdd"><spring:message code="name94"/></p>
                    <div class="p_bgroup">
                        <span class="mg_p">
                        <img class="p_bgroup_img" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/dd_true.png">
                         <p class="p_bgroup_img_p1"><spring:message code="name802"/></p>
                         <p class="p_bgroup_img_p2"><spring:message code="name803"/></p>
                         <p class="p_bgroup_img_p3"><spring:message code="name804"/>：<spring:message code="name800"/><span
                                 class="p_bgroup_img_p3_span1"><spring:message code="name805"/>:
                             <b class="p_bgroup_img_p3_b1">3630.00</b><spring:message code="name_452"/></span></p>
                       </span>
                    </div>

                    <p class="line-h-25 h-25 f-14 col-8c8c8c"></p>
                    <p class="pls"> <spring:message code="name101"/></p>
                    <p class="hg_0"></p>
                    <p class="zfpt"> <spring:message code="name778"/></p>
                    <p class="p_img">
                        <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/online_pay.png" alt=""/></span>
                    </p>
                    <p class="becareful"><spring:message code="name105"/>
                        <span class="p_span"><spring:message code="name106"/></span>  <spring:message code="name806"/> <span
                                class="p_span"> <spring:message code="name108"/>
                    </span></p>
                    <%--<img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/HTS-tipsv1-explainv4.jpg" alt="">--%>
                </div>
                <div class="clear bg-white HTS-tips HTS-tipsv2 pb-280 dis-n">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 pl-50 line-h-70-50-ipad pb-20">
                        <spring:message code="name16"/>
                    </p>
                    <div class="clear ml-50 mr-50 bor bor-b bor-col-f5f5f5">
                    </div>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10">
                        <spring:message code="name421"/>
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50">
                       <spring:message code="name422"/> 
                    </p>
                    <p class="pt-20 f-20 col-696969 pl-50 line-h-30 pb-10">
                        <spring:message code="name423"/>
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50">
                        <spring:message code="name424"/>
                    </p>
                </div>
                <div class="clear bg-white HTS-tips HTS-tipsv3 pb-280 dis-n">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 pl-50 line-h-70-50-ipad pb-20">
                        <spring:message code="name17"/>
                    </p>
                    <div class="clear ml-50 mr-50 bor bor-b bor-col-f5f5f5">
                    </div>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50">
                       <spring:message code="name425"/> 
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-20 pr-50 zfdd">支付订单</p>
                    <div class="p_bgroup">
                        <span class="mg_p">
                        <img class="p_bgroup_img" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/dd_true.png">
                         <p class="p_bgroup_img_p1"><spring:message code="name802"/></p>
                         <p class="p_bgroup_img_p2"><spring:message code="name803"/></p>
                         <p class="p_bgroup_img_p3"><spring:message code="name804"/>：<spring:message code="name800"/><span
                                 class="p_bgroup_img_p3_span1"><spring:message code="name805"/>:
                             <b class="p_bgroup_img_p3_b1">3630.00</b><spring:message code="name_452"/></span></p>
                       </span>
                    </div>
                    <%--<img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/HTS-tipsv3-explainv1.jpg" alt="">--%>
                    <p class="pt-20 f-20 col-696969 pl-50 line-h-20 pb-10 f-w">
                      <spring:message code="name426"/>  
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-20 pb-20 pr-50">
                      <spring:message code="name427"/>  
                    </p>
                </div>
                <div class="clear bg-white HTS-tips HTS-tipsv4 pb-280 dis-n">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 pl-50 line-h-70-50-ipad pb-20 pr-50">
                      <spring:message code="name18"/> 
                    </p>
                    <div class="clear ml-50 mr-50 bor bor-b bor-col-f5f5f5">
                    </div>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10 pr-50">
                        <spring:message code="name428"/>
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10 pr-50">
                        <spring:message code="name429"/>
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10 pr-50 text-ind">
                       <spring:message code="name430"/> 
                    </p>
                </div>
                <div class="clear bg-white HTS-tips HTS-tipsv5 pb-280 dis-n">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 pl-50 line-h-70-50-ipad pb-20 pr-50">
                        <spring:message code="name19"/>
                    </p>
                    <div class="clear ml-50 mr-50 bor bor-b bor-col-f5f5f5">
                    </div>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10 pr-50">
                        <spring:message code="name431"/>
                    </p>
                    <p class="pls"> <spring:message code="name101"/></p>
                    <p class="hg_0"></p>
                    <p class="zfpt"> <spring:message code="name778"/></p>
                    <p class="p_img">
                            <img src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/online_pay.png" alt=""/></span>
                    </p>
                    <p class="becareful"><spring:message code="name105"/>
                        <span class="p_span"><spring:message code="name106"/></span>  <spring:message code="name806"/> <span
                                class="p_span"> <spring:message code="name108"/>
                    </span></p>
                </div>
                <div class="clear bg-white HTS-tips HTS-tipsv6 pb-280 dis-n">
                    <p class="pt-40-20-ipad f-50-40-ipad col-b0b0b0 pl-50 line-h-70-50-ipad pb-20 pr-50">
                        <spring:message code="name20"/>
                    </p>
                    <div class="clear ml-50 mr-50 bor bor-b bor-col-f5f5f5">
                    </div>
                    <%--<img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/HTS-tipsv6-explainv1.jpg" alt="">--%>
                    <img class="full-w" src="<%=request.getContextPath() %>/resources/img/news/HK_HowToShop/shzc.png" alt=""/>

                        <p class="p_s_1"><spring:message code="name807"/></p>
                        <p class="p_s_2"><spring:message code="name808"/></p>
                        <p class="p_s_3"><spring:message code="name809"/></p>

                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10 pr-50">
                        <spring:message code="name432"/>
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10 pr-50">
                       <spring:message code="name433"/> 
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10 pr-50">
                       <spring:message code="name434"/> 
                    </p>
                    <p class="pt-10 f-20 col-696969 pl-50 line-h-30 pb-10 pr-50">
                       <spring:message code="name435"/> 
                    </p>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../comm/footer.jsp" %> 
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/bootstrap.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/guide/guide.js"></script>
</body>
</html>