<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>
<%@ page import="com.bisa.health.shop.enumerate.GoodsStatusEnum"%>

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
    <title><spring:message code="name437"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
		var http_request = "<%=request.getContextPath() %>";
		var request_href = window.location.href;
		var request_params = window.location.search;

        var invalid = "<%=GoodsStatusEnum.invalid.getValue()%>";
        var sale_out = "<%=GoodsStatusEnum.sale_out.getValue()%>";
        var in_sale = "<%=GoodsStatusEnum.in_sale.getValue()%>";

        var name_450 = "<spring:message code='name_450'/>";
        var name_451 = "<spring:message code='name_451'/>";
        var name634 = "<spring:message code='name634'/>";
    </script>
    <style type="text/css">
        .notclick{
            pointer-events: none;
        }
    </style>
</head>
<body>
	<%@ include file="../comm/header.jsp" %>
    <div class="clear wrap">
        <div class="container pl-0 pr-0">
            <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 bg-f5f5f5 cur-d">
                <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath()%>/index"><spring:message code="homepage"/> </a>
                >
                <span class="col-333 t-nonehove hovecol-309DE2" href=""><spring:message code="shopCar"/></span>
            </p>
            <p class="col-252525 f-26 pd-20 ">
				<spring:message code="name438"/>
            </p>

            <div class="clear full-w bg-f5f5f5 pd-15 cur-d mb-40">
                <!-- 列表菜单 -->
                <!-- 全选的是否被选择为类is-checkall,单选的是否被选择为is-checkbox，禁止选择类是stop-check和隐藏类dis-n一起使用。没有这些选项的类默认为未选中. -->
                <div class="clear full-w bg-white headDiv">
                    <div class="clear col-md-2 col-sm-1 pl-0 pr-0 pt-20 pb-20 line-h-30">
                        <div class="clear col-sm-8 pl-0 pr-0">
                        <!-- 添加复选框点击事件 -->
                            <div  onclick="chooseAll(this)" class="img-25-20-ipad pull-right bg-9a9a9a bg-activate text-center mt-3-5-ipad car-checkall is-checkall">
                                <img class="pos-r t--4--6-ipad img-20-15-ipad cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/ico_ok.png" alt="">
                            </div>
                        </div>
                        <div class="clear col-sm-4 pl-0 pr-0 text-center col-252525 f-16 hidden-sm">
                           	 <spring:message code="name439"/>
                        </div>
                    </div>
                    <div class="clear col-md-3 col-sm-4 pl-0 pr-0 pt-20 pb-20 line-h-30 text-center col-252525 f-16"><spring:message code="name440"/></div>
                    <div class="clear col-sm-1 pl-0 pr-0 pt-20 pb-20 line-h-30 text-right pr-15 col-252525 f-16"><spring:message code="name441"/></div>
                    <div class="clear col-sm-2 pl-0 pr-0 pt-20 pb-20 line-h-30 text-center col-252525 f-16"><spring:message code="name442"/></div>
                    <div class="clear col-sm-2 pl-0 pr-0 pt-20 pb-20 line-h-30 text-right pr-40 col-252525 f-16"><spring:message code="name443"/></div>
                    <div class="clear col-sm-2 pl-0 pr-0 pt-20 pb-20 line-h-30 pl-40 col-252525 f-16"><spring:message code="name444"/></div>
                </div>

                <!-- 购物车为空的时候 -->
                <input type="hidden" class="is-emptycart-control" value="0">
                <div class="clear bg-white mt-4 mb-4 pt-40 pb-40 is-emptycart-side dis-n">
                    <div class="clear col-sm-6 h-180">
                        <img class="pull-right" src="<%=request.getContextPath() %>/resources/img/net_shopping/HK-Empty_Cart.png" alt="">
                    </div>
                    <div class="clear col-sm-6 h-180 line-h-180 col-268fff f-30">
                    	<spring:message code="name445"/>
                    </div>
                </div>

                <!-- 单品循环模板div-->
                <div class="clear full-w bg-white mt-4 mb-4 car-kinds car-pro-item cart-child-div">
                   <input type="hidden" class="cart-item-json" value="" />
                   <input type="hidden" class="cart-guid" value="" />
                   <div class="clear col-md-2 col-sm-1 pl-0 pr-0 pt-10 pb-10 line-h-30">
                       <div class="clear col-sm-8 pl-0 pr-0 pt-20 pb-20">
                           <div class="show_goods_status">
                               <!-- 显示商品状态的地方-->
                           </div>
                           <div  onclick="checkClick(this)" class="img-25-20-ipad pull-right bg-9a9a9a bg-activate cur-p text-center mt-3-5-ipad car-checktips is-check">
                               <img class="pos-r t--4--6-ipad img-20-15-ipad cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/ico_ok.png" alt="">
                           </div>
                       </div>
                       <div class="clear col-sm-4 pl-0 pr-0 pt-20 pb-20 text-center col-252525 f-16">
                       </div>
                   </div>
                   <div class="clear col-md-3 col-sm-4 pl-0 pr-0 pt-10 pb-10 line-h-30 text-center col-252525 f-16-14-ipad">
                       <div class="clear col-sm-3 pl-0 pr-0 pt-0-5-ipad">
                           <img class="full-w cart-pro-img" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
                       </div>
                       <div class="clear col-sm-9 pl-0 pr-0 pt-20 pb-20 text-left pl-15">
                           <span class="col-252525 cart-pro-name"><spring:message code="name440"/></span>
                       </div>
                   </div>
                   <div class="clear col-sm-1 pl-0 pt-30 pb-30 line-h-30 text-right pr-15-0-ipad col-252525 f-16-14-ipad">
                       <!-- 单价 -->
                       <spring:message code="name103"/>
                       <span class="col-252525 cart-price" id="price">0.00</span>
                   </div>
                   <div class="clear col-sm-2 pl-0 pr-0 pt-30 pb-30 line-h-30 text-center col-252525 f-16">
                       <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 pos-r pl-0 pr-0 line-h-20 h-20 mt-5 mb-5 cart-pro-amount-div">
                           <span class="pos-a img-20 t-2 l-5 f-16 f-w select-none cur-p min-count">-</span>
                           <!--<input class="full-w bor-col-B2B2B2 bor text-center focus-input-pri cart-amount"  maxlength="2" type="text" value="0" id="value">-->
                             <input class="full-w bor-col-B2B2B2 bor text-center focus-input-pri cart-amount" onkeyup="value=zhzs(this.value)" id="value" lay-verify="required|number" value="0" maxlength="2" type="text" onblur="show()">
                           <span class="pos-a img-20 t-2 r-5 f-16 f-w select-none cur-p add-count">+</span>
                           <input type="hidden" class="cart-goods-guid" id="goodsGuid" value="" />
                           <input type="hidden" class="cart-id" id="cartId" value="" />
                       </div>
                   </div>
                   <div class="clear col-sm-2 pl-0 pr-0 pt-30 pb-30 line-h-30 text-right pr-40 col-309DE2 f-16-14-ipad">
                        <spring:message code="name103"/>
                       <span class="col-309DE2 cart-total" id="cartotal"></span>
                   </div>
                   <div onclick="delCart(this)" class="clear col-sm-2 pl-0 pr-0 pt-30 pb-30 line-h-30 pl-40 col-252525 f-16 car-pro-del-div">
                        <input type="hidden" class="cart-id" value="" />
                        <input type="hidden" class="cart-goods-type" value="" />
                        <!-- 删除图标 -->
                       <span class="img-30 pd-5 cur-p car-pro-del">
                           <img class="img-20" src="<%=request.getContextPath() %>/resources/img/net_shopping/close.png" alt="">
                       </span>
                   </div>
                </div>

                <!-- 套餐循环 模板div -->
                <div class="clear full-w bg-white mt-4 mb-4 car-kinds cart-pack-item cart-child-div">
                    <!-- 套餐内主商品 -->
                    <div class="clear full-w pl-0 pr-0 mb-10 cart-pack-main">
                        <input type="hidden" class="cart-item-json" value="" />
                        <input type="hidden" class="cart-guid" value="" />
                        <div class="clear col-md-2 col-sm-1 pl-0 pr-0 pt-10 pb-10 line-h-30">
                            <div class="clear col-sm-8 pl-0 pr-0 pt-20 pb-20">
                                <div class="show_goods_status">
                                    <!-- 显示商品状态的地方-->
                                </div>
                                <div onclick="checkClick(this)" class="img-25-20-ipad pull-right bg-9a9a9a bg-activate cur-p text-center mt-3-5-ipad car-checktips is-check">
                                    <img class="pos-r t--4--6-ipad img-20-15-ipad cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/ico_ok.png" alt="">
                                </div>
                            </div>
                            <div class="clear col-sm-4 pl-0 pr-0 pt-20 pb-20 text-center col-252525 f-16">
                            </div>
                        </div>
                        <div class="clear col-md-3 col-sm-4 pl-0 pr-0 pt-10 pb-10 line-h-30 text-center col-252525 f-16-14-ipad">
                            <div class="clear col-sm-3 pl-0 pr-0 pos-r pt-0-5-ipad">
                                <img class="full-w cart-pack-img" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
                                <div class="clear pos-a b--21--30-ipad l-25-20-ipad img-25 triangle-cart-up">
                                </div>
                            </div>
                            <div class="clear col-sm-9 pl-0 pr-0 pt-20 pb-20 text-left pl-15">
                                <span class="col-252525 cart-pack-name"><spring:message code="name440"/></span>
                            </div>
                        </div>
                        <div class="clear col-sm-1 pl-0 pt-30 pb-30 line-h-30 text-right pr-15-0-ipad col-252525 f-16-14-ipad">
                            <!-- 单价 -->
                            <spring:message code="name103"/>
                            <span class="col-252525 cart-price">0.00</span>
                        </div>
                        <div class="clear col-sm-2 pl-0 pr-0 pt-30 pb-30 line-h-30 text-center col-252525 f-16">
                            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 pos-r pl-0 pr-0 line-h-20 h-20 mt-5 mb-5 cart-pack-amount-div">
                              <!--   套餐数量点击事件   减少- -->
                                <span class="pos-a img-20 t-2 l-5 f-16 f-w select-none min-count">-</span>

                                <input class="full-w bor-col-B2B2B2 bor text-center focus-input-pri cart-amount" onkeyup="value=zhzs(this.value)"  readonly lay-verify="required|number" value="0" maxlength="2" type="text"  onBlur=" ">
                                 <!--    套餐数量点击事件  增加 -->

                                <span class="pos-a img-20 t-2 r-5 f-16 f-w select-none add-count">+</span>

                                <input type="hidden" class="pack-final-count" value="">
                                <input type="hidden" class="cart-goods-guid"  value="" />
                                <input type="hidden" class="cart-id"  value="" />
                            </div>
                        </div>
                        <div class="clear col-sm-2 pl-0 pr-0 pt-30 pb-30 line-h-30 text-right pr-40 col-309DE2 f-16-14-ipad">
                            <spring:message code="name103"/>
                            <span class="col-309DE2 cart-total">0.00</span>
                        </div>
                        <div  onclick="delCart(this)"  class="clear col-sm-2 pl-0 pr-0 pt-30 pb-30 line-h-30 pl-40 col-252525 f-16 car-pack-del-div">
                            <input type="hidden" class="cart-id" value="" />
                            <input type="hidden" class="cart-goods-type" value="" />
                            <span class="img-30 pd-5 cur-p car-pack-del">
                                <img class="img-20" src="<%=request.getContextPath() %>/resources/img/net_shopping/close.png" alt="">
                            </span>
                        </div>
                    </div>

                    <!-- 套餐搭配商品 -->
                    <div class="clear col-sm-12 pl-0 mb-10 pr-0 f-14-12-ipad bg-white h-60-40-ipad line-h-60-40-ipad car-combo-tips cart-pack-fitting">
                        <input type="hidden" class="cart-item-json" value="" />
                        <input type="hidden" class="cart-goods-guid" value="" />
                        <input type="hidden" class="cart-guid" value="" />
                        <input type="hidden" class="cart-id" value="" />
                        <!-- 本套餐的部分信息 -->
                        <div class="clear col-sm-4 col-sm-offset-1 col-md-3 col-md-offset-2 bg-eee pl-0 pr-0">
                            <div class="clear col-sm-3 pl-0 pr-0 ">
                                <div class="clear img-40-30-ipad pull-left bg-white ml-15 mt-10-5-ipad mb-10-5-ipad">
                                    <img class="img-30-20-ipad dis-ib pull-left mg-5 cart-pack-img" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
                                </div>
                            </div>
                            <div class="clear col-sm-9 pl-0 pr-0 text-left pl-15 f-14">
                                <span class="col-252525 cart-pack-name"><spring:message code="name440"/></span>
                            </div>
                        </div>
                        <div class="clear col-sm-1 bg-eee pl-0 text-right pr-15 col-252525">
                            <spring:message code="name103"/>
                            <span class="col-252525 car-combo-tips-unp cart-price"> 0.00 </span>
                        </div>
                        <div class="clear col-sm-2 bg-eee pl-0 pr-0 text-center h-60-40-ipad">
                            <span class="col-252525 car-combo-tips-numcase cart-amount">0</span>
                            <input type="hidden" class="pack-final-count" value="">
                        </div>
                        <div class="clear col-sm-2 bg-eee pl-0 text-right pr-40 col-309DE2">
                            <spring:message code="name103"/>
                            <span class="col-309DE2 car-combo-tips-tot cart-total">0.00</span>
                        </div>
                        <div class="clear col-sm-1 bg-eee pl-0 pr-0 h-60-40-ipad">
                        </div>
                    </div>
                </div>

                <!-- 购物车动态拼接元素 父容器 -->
                <div class="clear full-w car-single car-pro-items shop-cart-div">
	                <div class="cart-child-div"></div>
               </div>

                <!-- 结算 -->
                <div class="clear full-w bg-white mb-30 mt-15">
                    <div class="clear col-lg-3 col-lg-offset-1 pl-0 pr-0 h-60 line-h-40 col-252525 f-16 hidden-sm hidden-md mt-20 ml-20">
                        <span class="col-252525" onclick="window.location.href='<%=request.getContextPath()%>/index'" style="color: red;cursor:pointer;"><spring:message code="name176"/></span>
                        <span class="col-252525 pl-5 pr-5">|</span>
                        <span class="col-252525"><spring:message code="name446"/></span>
                        <span class="col-309DE2 tot-num-of-kinds">0</span>
                        <span class="col-252525"><spring:message code="name447"/></span>
                        <span class="col-309DE2 tot-num-of-goods">0</span>
                        <span class="col-252525"><spring:message code="name448"/></span>
                    </div>
                    <div
                            class="clear col-sm-8 col-sm-offset-1 col-md-6 col-md-offset-3 col-lg-5 col-lg-offset-0 pl-0 pr-0 h-60 line-h-40 col-252525 f-16 pl-25 mt-20 ml-20">
                        <span class="col-252525 "><spring:message code="name449"/></span>
                        <span class="col-252525"><spring:message code="name103"/></span>
                        <span class="col-252525 pl-5 pr-5">0.00</span>
                        <span class="col-252525 pl-5 pr-5">|</span>
                        <span class="col-309DE2 "><spring:message code="name450"/></span>
                        <span class="col-309DE2 "><spring:message code="name103"/></span>
                        <span class="col-309DE2 f-24 total-price">0.00</span>
                    </div>
                    <div class="clear col-sm-3 pl-0 pr-0 line-h-60 h-60 mt-15 ml-30">
                    	<!-- 去结算 -->
                       <form class="full-wh" method="post" id="submit_cart" action="user/shopCartConfirmOrder">
                            <input class="cart-submit-input" type="hidden" name="productData" value="">
                            <button onclick="submitOrderGoods()" class="bor-none full-wh bg-309DE2 col-white f-24 text-center pos-r t--2 hovbg-38B3FF HK-cartsubmit" type="button" >
                              	 <spring:message code="name451"/>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../comm/footer.jsp"%>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/bootstrap.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/shopping/buyerCart.js"></script>
</body>
</html>