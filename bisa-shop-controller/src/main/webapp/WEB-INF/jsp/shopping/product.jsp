<%@ page import="org.springframework.context.annotation.Import"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../comm/tag.jsp" %>
<% String menuType = "sys3"; %>
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
    <title><spring:message code="name489"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/layer/skin/default/layer.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/shopping/product.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/shopping/appraise.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var request_href = window.location.href;  
		var request_params = window.location.search;
		
		var invalid = "<%=GoodsStatusEnum.invalid.getValue()%>";
		var sale_out = "<%=GoodsStatusEnum.sale_out.getValue()%>";
		var in_sale = "<%=GoodsStatusEnum.in_sale.getValue()%>";

		var name509 = "<spring:message code='name509'/>";
		var name160 = "<spring:message code='name160'/>";
		var name162 = "<spring:message code='name162'/>";
		var name629 = "<spring:message code='name629'/>"; 
		var name_207 = "<spring:message code='name_207'/>"; 
		var name_208 = "<spring:message code='name_208'/>"; 
		var name_209 = "<spring:message code='name_209'/>"; 
		var name497 = "<spring:message code='name497'/>"; 
	</script>
    <style>
    /* 页面嵌入部分CSS为设置zoom框大小 */
    @media (min-width:768px) and (max-width:991px) {
        .zoomLens {
            width: 80px !important;
            height: 80px !important;
        }
        .zoomWindow {
            border-width: 1px !important;
            width: 260px !important;
            height: 260px !important;
        }
    }
    @media (min-width:992px) and (max-width:1199px) {
        .zoomLens {
            width: 100px !important;
            height: 100px !important;
        }
        .zoomWindow {
            border-width: 1px !important;
            width: 366px !important;
            height: 366px !important;
        }
    }
    @media (min-width:1200px) and (max-width:1299px) {
        .zoomLens {
            width: 130px !important;
            height: 130px !important;
        }
        .zoomWindow {
            border-width: 1px !important;
            width: 400px !important;
            height: 400px !important;
        }
    }
    @media (min-width:1300px) {
        .zoomLens {
            width: 130px !important;
            height: 130px !important;
        }
        .zoomWindow {
            border-width: 1px !important;
            width: 400px !important;
            height: 400px !important;
        }
    }
    .yen {
    	font-family:Arial;
    }
    </style>
</head>
<body>
   	<%@ include file="../comm/header.jsp" %> 
    <div class="clear full-w bor bor-t bor-col-D6D6D6 sha-float-titlev1 flo-menuv2">
        <div class="containerv2 pt-10 pb-10 clear line-h-30 plr-0-10-ipad">
            <span class="col-black family-h pull-left cur-d f-20 main-product-title" ><spring:message code="name490"/></span>
            <span class="col-black family-h pull-right f-12">
				<!-- 主商品才有介绍（宣传页） -->
				<a href="<%=request.getContextPath()%>/web/call/ecgAdvertisement?goodsNumber=123741238SD">
					<span class="t-nonehove hovecol-309DE2 f-16 col-black cur-p product-shopping"><spring:message code="name491"/></span>
				</a>
            </span>
        </div>
    </div>
    <div class="clear full-w bor bor-t bor-col-D6D6D6 sha-float-titlev1 affix t-0 l-0 bg-white z-99 dis-n flo-menuv2-aff">
        <div class="containerv2 pt-10 pb-10 clear line-h-30 plr-0-10-ipad">
            <span class="col-black family-h pull-left cur-d f-20 main-product-title"><spring:message code="name490"/></span>
             <button class="pull-right f-20 line-h-30 h-30 text-center bor-none bg-309DE2 hovbg-2D90CF col-white pl-20 pr-20 go-top">
             	<spring:message code="name206"/>
            </button>
        </div>
    </div>
    <div class="wrap">
        <div class="containerv2 pl-0 pr-0">
            <div class="clear cont-other">
                <div class="clear full-w ">
                	<!-- 主图下方小图 -->
                    <div class="clear col-sm-5 pl-0 pr-0 pull-left small-imgs">
                    	<!-- 主图 -->	
                        <div class="claer col-md-12 pull-left text-center pd-50-30-ipad">
                            <img id="prode-zoom-main" class="full-w produ-max-show" 
                            src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" 
                            data-zoom-image="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
                        </div>
                        <!-- 三个小图 -->
                        <div class="clear col-md-12 mtb-20-5-ipad pl-0 pr-0 pull-left" id="little-img">
	                        <div class="prode-zoom-img clear dis-ib cur-p col-sm-3 plr-10-5-ipad" 
	                        data-image="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" 
	                        data-zoom-image="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg">
	                            <img class="full-w bor bor-col-B2B2B2 produ-min-tips"
	                             src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" />
	                        </div>
                        </div>
                    </div>
                    
                    <div class="clear col-sm-5 pull-left pt-20-5-ipad plr-25-5-ipad">
                        <div class="clear pb-30 min-h-470-390-ipad pos-r">
                            <div class="clear pos-a t-0 l-0 full-w">
                                <p class="f-30-20-ipad col-black family-h line-h-30 mtb-10-5-ipad pro-main-dir main-product-title">
                                   	 <spring:message code="name494"/>
                                </p>
                                <p class="f-18-14-ipad col-616161 family-hs line-h-25-20-ipad pro-dep-dir overflow-h h-75-60-ipad main-product-describe">
									<spring:message code="name495"/>
                                </p>
                            </div>
                            <div class="clear pos-a b-0 l-0 full-w">
                                <div class="clear line-h-25 ptb-20-0-ipad bor bor-l-none bor-r-none bor-col-B2B2B2-hide-ipad">
                                    <span class="col-309DE2 f-16 family-h dis-ib pull-left" id="yen"><spring:message code="name103"/>&nbsp;</span>
									<span class="col-309DE2 f-30 family-h dis-ib pull-left pro-main-price">0.00</span>
                                </div>
                                <div class="clear full-w combomainbox">

                                    <%--标配单品的按钮--%>
                                    <div class="col-sm-4 text-center cur-p pl-0 combotips standard-product">
                                        <div class="clear bor-col-B2B2B2 bor h-40-30-ipad line-h-40-30-ipad f-18-14-ipad col-black combotips-div">
                                          	 <spring:message code="name496"/>
                                        </div>
                                       	<!-- 单品数据 -->
                                        <input type="hidden" class="goods_status" value=""><%--这里放 是否有货的标志--%>
                                        <input class="goods-guid" type="hidden" value="">
                                        <input class="goods-type" type="hidden" value="0">  <!-- 商品类型：单品 -->
										<input type="hidden" class="pack-discount-price" value=""><!-- 商品类型：价格 -->
                                    </div> 
                                   
                                    <!-- 实体套餐按钮 -->
                                    <div class="col-sm-4 text-center cur-p pl-0 combotips package-div package-down-btn">
                                        <div class="clear bor-col-B2B2B2 w-auto bor h-40-30-ipad line-h-40-30-ipad f-18-14-ipad col-black combotips-div package-name">
                                           	<spring:message code="name497"/>
                                        </div>
                                        <!-- 套餐数据-->
                                        <input type="hidden" class="goods_status" value=""><%--这里放 是否有货的标志--%>
                                        <input class="goods-guid" type="hidden" value="">
                                        <input class="goods-type" type="hidden" value="1">  <!-- 商品类型：套餐 -->
                                        <!-- 套餐悬浮窗口 -->
                                    	<div class="clear text-left pos-a r-0 bg-white w-350 h-auto z-999 bor bor-col-ccc t-29 dis-n package-detail">
                                    		<p class="let-spa-1 text-center f-w f-16 mt-6 mb-6 package-name"><spring:message code="name498"/></p>
                                    		<table class="table w-350 borb-c0c0c0">
											  <thead>
											    <tr>
											      <th><spring:message code="name499"/></th>
											      <th><spring:message code="name500"/></th>
											      <th><spring:message code="name501"/></th>
											      <th><spring:message code="name502"/></th>
											    </tr>
											  </thead>
											  <tbody>
											    <tr class="pack-item">
											      <td class="pro-img"><img class="img-100 ml-10 img-rounded" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg"/></td>
											      <td class="pro-amount" >0</td>
											      <td class="pro-fix-price line-through" >0.00</td>
											      <td class="pro-discount-price col-ff0000" >0.00</td>
											    </tr>
											  </tbody>
											</table>
											<div class="pb-20">
                                    			<div class="f-16 mg-0-20"><spring:message code="name503"/>&emsp;<span class="pack-fix-price line-through"> </span> </div>
                                    			<div class="f-16 mg-0-20"><spring:message code="name504"/>&emsp;<span class="pack-discount-price col-ff0000"> </span> </div>
                                    		</div>
                                    	</div>
                                    </div>
                                    
                                    <!-- 虚拟套餐商品 按钮 -->
                                    <div class="col-sm-5 text-center cur-p pl-0 combotips virtual-product package-down-btn dis-n">
                                    	<div class="clear bor-col-B2B2B2 w-auto bor h-40-30-ipad line-h-40-30-ipad f-18-14-ipad col-black combotips-div package-name">
                                           	<spring:message code="name497"/>
                                        </div>
                                       	<!-- 单品数据 -->
                                        <input type="hidden" class="goods_status" value=""><%--这里放 是否有货的标志--%>
                                        <input class="goods-guid" type="hidden" value="">
                                        <input class="goods-type" type="hidden" value="2">  <!-- 商品类型：虚拟商品 -->
                                        <!-- 悬浮窗口 -->
                                    	<div class="clear text-left pos-a r-0 bg-white w-380 h-auto z-999 bor bor-col-ccc t-29 dis-n package-detail">
                                    		<p class="let-spa-1 text-center f-w f-16 mt-6 mb-6 package-name"><spring:message code="name498"/></p>
                                    		<table class="table borb-c0c0c0">
											  <thead>
											    <tr>
											      <th><spring:message code="name440"/></th>
											      <th><spring:message code="name500"/></th>
											      <th><spring:message code="name501"/></th>
											      <th><spring:message code="name502"/></th>
											    </tr>
											  </thead>
											  <tbody>
											    <tr class="pack-item">
											      <td class="pro-name"></td>
											      <td class="pro-amount" >0</td>
											      <td class="pro-fix-price line-through" >0.00</td>
											      <td class="pro-discount-price col-ff0000" >0.00</td>
											    </tr>
											  </tbody>
											</table>
											<div class="pb-20">
                                    			<div class="f-16 mg-0-20"><spring:message code="name503"/><span class="pack-fix-price line-through"> </span> </div>
                                    			<div class="f-16 mg-0-20"><spring:message code="name504"/><span class="pack-discount-price col-ff0000"> </span> </div>
                                    		</div>
                                    	</div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>

                        <div class="clear ptb-20-10-ipad bor bor-l-none bor-r-none bor-col-B2B2B2">
                        	<!-- 下架  -->
                        	<div class="clear col-sm-8 pl-0 pr-10" id="soldOutButton" style="display: none">
                                <button type="button" disabled="disabled" style="cursor:pointer;" class="line-h-45-30-ipad h-45-30-ipad col-white f-18-14-ipad text-center full-w radius-0 bor-none add-shopcar">
									<spring:message code="soldOut"/>
                                </button>
                            </div>
                            <!-- 售完 -->
                        	<div class="clear col-sm-8 pl-0 pr-10" id="sellOutButton" style="display: none">
                                <button type="button" disabled="disabled" style="cursor:pointer;" class="line-h-45-30-ipad h-45-30-ipad col-white f-18-14-ipad text-center full-w radius-0 bor-none add-shopcar">
									<spring:message code="sellOut"/>
                                </button>
                            </div>

                         	<!-- 加入购物车 -->
                            <div class="clear col-sm-8 pl-0 pr-10" id="shopCarButton">
                                <button type="button" class="line-h-45-30-ipad h-45-30-ipad col-white f-18-14-ipad text-center full-w radius-0 bg-309DE2 bor-none hovbg-2D90CF add-shopcar">
                                	<spring:message code="name505"/></button>
                            </div>
                          	<!-- 立即购买 -->
	                        <div class="clear col-sm-4 pl-10 pr-0" id="buyButton">
	                        	<form id="buyForm" action="user/confirmOrder" method="get">
                                    <input id="buyGoodsGuid" type="hidden" name="goodsGuid" value="" />
                                    <input id="buyGoodsType" type="hidden" name="goodsType" value="" />
	                        	 	<button class="line-h-45-30-ipad h-45-30-ipad col-white f-18-14-ipad text-center full-w radius-0 bg-309DE2 bor-none hovbg-2D90CF" type="button" onclick="buyNow();">
	                           	 		<spring:message code="name206"/>
	                           	 	</button>
	                        	</form>
	                        </div>
                        </div>

                        <p class="pt-10 pb-10 line-h-30 f-16 ">
                            <img class="img-25 mr-5 pos-r t--2" src="<%=request.getContextPath() %>/resources/img/net_shopping/find-more.png" alt="">
                            <a class="col-616161 hovecol-309DE2 t-nonehove" href="<%=request.getContextPath() %>/index"><spring:message code="name506"/></a>
                        </p>
                    </div>
                    
                    <div class="clear col-sm-2 pull-left pt-20 pl-10 pr-10">
                        <div class="pos-r clear text-center line-h-20 f-16-14-ipad col-black pb-20-0-ipad">
                            	<spring:message code="name507"/>
                            <div class="pos-a bor bor-b w-50 t-10 r--30 hidden-sm hidden-md"> </div>
                            <div class="pos-a bor bor-b w-50 t-10 l--30 hidden-sm hidden-md"> </div>
                        </div>
                        <div class="clear pos-r bor bor-col-B2B2B2 pull-left mt-10 mb-10 cur-p text-center f-14 fitting-product">
                            <a href="#" target="_blank">
                            	<img class="full-wh" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
                            </a>
                            <div class="clear h-25 line-h-25 rgba-d2d2d2-20 pos-a b-0 l-0 pl-10 pr-10 full-w hidden-sm hidden-md">
	                            <a class="col-727272 t-nonehove hovecol-309DE2" href="#" target="_blank">
	                               <span class="col-727272 t-nonehove hovecol-309DE2 text-line-1 goods-name"> 
	                               </span>
	                            </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
          	<!-- 导航条 -->
            <div class="clear full-w f-18 pt-10 pb-10 product-info-tab">
				 <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"><spring:message code="name207"/></a></li>
				    <%-- 
				    <li role="presentation"><a href="#parameter" aria-controls="parameter" role="tab" data-toggle="tab"><spring:message code="name492"/></a></li>
				     --%>
				    <li role="presentation"><a class="appraise-tab" href="#evaluation" aria-controls="evaluation" role="tab" data-toggle="tab"><spring:message code="name493"/></a></li>
				 </ul>
            </div>
            
          	<!-- Tab panes -->
		  	<div class="tab-content">
			  	<!-- 详情 -->
			    <div role="tabpanel" class="tab-pane active" id="profile">
				    <!--  产品详情图 -->
		            <div class="clear full-w HK-begin-describe pt-20 pb-20" id="detail-img">
		               <div class="clear full-w">
		                  <img class="product-imgs" src="" alt="">
						 <%--  <div class="dail_1">
						   <h2 class="f-h2">BlueSensor 特点和优势</h2>
						   <p class="fp">BlueSensor 全球最具时间和成本效益的电极</p>
						   </div>
						   <div class="dail_2">
                                     <div class="dail_2_left">
										<img class="d2-img" src="resources/img/net_shopping/product2/product2_descriptions_5.png"/><img>
									 </div>
							         <div class="dail_2_right">
									   <p class="dail_2_p1 ">凭借40年的丰富经验与众不同</p>
									   <p class="dail_2_p2 ">BlueSensor 是最具有时间和成本效益的电极系列,在精确读数、使用便利性和病患舒适感方面具有无可匹敌的出色性能。</p>
									   <p class="dail_2_p2 ">BlueSensor 确保工作流不会被错误警报打断且不需要耗费时间解决电极粘附性相关问题。</p>
									   <p class="dail_2_p1 mg-t">精准检测---清晰图像</p>
									   <p class="dail_2_p2 ">无需备皮,电极即可提供快速且可靠的心电图跟踪,最大限度地保障病患舒适感。</p>
									   <p class="dail_2_p2 ">快速且准确的监测会确保获得清晰的心电图，我们深知这一点是准确诊断必不可少的。</p>
									   <p class="dail_2_p2 ">BlueSensor电极将给您带来绝对品质体验。</p>
						           </div>
						   </div>
						   <div class="dail_3">
							   <h3 class="f-h3">BlueSensor 主要特点</h3>
						   </div>
							 <div class="dail_4">
								  <p class="d4-p">偏离接头</p>
								  <li class="d4-li"><p class="d4-li-p">拉动已连接导线时不会影响电极性能。</p></li>
								  <li class="d4-li"><p class="d4-li-p">偏离接头能够防止人为现象干扰读数,特别适合压力测试和动态心电图监测(Holter)等高要求应用。</p></li>
								  <li class="d4-li"><p class="d4-li-p">容易粘附,易于使用,确保病患的舒适感。</p></li>
								 <img class="d4-img" src="resources/img/net_shopping/product2/product2_descriptions_1.png" alt="">
							  </div>
							  <div class="dail_4">
								  <p class="d4-p">双圈粘胶</p>
								  <li class="d4-li"><p class="d4-li-p">双圈粘胶设计粘附性极好,确保获得准确几率。</p></li>
								  <li class="d4-li"><p class="d4-li-p">外圈的粘附力会随着时间延长而增强,适合长期佩戴。材质轻薄且软,透气性佳。</p></li>
								  <li class="d4-li"><p class="d4-li-p">由聚合材料之上的内圈将导电胶围在中间区域,一贴上就能立即发挥出色粘附效果。</p></li>
								  <li class="d4-li"><p class="d4-li-p">由于结构独特,在佩戴电极之前不需要备皮,且撕下电极时也不会令病患痛苦。</p></li>
								  <img class="d4-img" src="resources/img/net_shopping/product2/product2_descriptions_2.png" alt="">
							  </div>
							  <div class="dail_4">
								  <p class="d4-p">湿润导电胶</p>
								  <li class="d4-li"><p class="d4-li-p">湿润导电胶实现皮肤和电极之间最佳的接触效果。</p></li>
								  <li class="d4-li"><p class="d4-li-p">立即有效降低皮肤阻抗。</p></li>
								  <li class="d4-li"><p class="d4-li-p">精确的湿润导电胶用量是确保获得最佳信号的关键。</p></li>
								  <img class="d4-img" src="resources/img/net_shopping/product2/product2_descriptions_3.png" alt="">
							  </div>
							  <div class="dail_4">
								  <p class="d4-p">银/氯化银电极</p>
								  <li class="d4-li"><p class="d4-li-p">纯银丝--与碳银组合相反。</p></li>
								  <li class="d4-li"><p class="d4-li-p">生物信号到电信号的优化转换保障最高跟踪质量。</p></li>

								  <img class="d4-img" src="resources/img/net_shopping/product2/product2_descriptions_4.png" alt="">
							  </div>--%>
		               </div>
		            </div>
				</div>
				<%-- 
				<!-- 参数 -->
			    <div role="tabpanel" class="tab-pane" id="parameter">
			    	<spring:message code="name492"/>
				</div>
			     --%>
			    <!-- 评价  懒加载 -->
			    <div role="tabpanel" class="tab-pane" id="evaluation">
			  		<!-- 暂无数据 -->
					<div class="no-data-div text-center f-18 col-333 pt-30 pd-30 dis-n">
						<spring:message code="name546"/>
					</div>
					
	                <div class="clear mt-10 full-w bg-245-a pt-10 pl-10 pr-10">
	                <table class="table">
					    <tbody>
					    	<!-- 模板元素，只有初次评价 -->
						    <tr class="module-tr tr-simple dis-n">
						        <td width="70%" class="vert-m">
							        <div class="mt-10">
				                		<div class="f-14 col-333 line-h-20 rate-content"></div>
				                	</div>
		                			<div class="mt-10"><span class="f-14 col-b0b0b0 rate-date"></span></div>
		                			<div class="mt-10 mb-10">
		                				<div class="f-12 col-8a6d3b line-h-20 rate-reply"></div>
		                			</div>
								</td>
						        <td class="col-author vert-m text-center">
						        	<div class="pull-left pl-10 rate-star">
				                        		<span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
				                            	<input type="hidden" class="rate-value" value="">
			                        </div>
						        	<div class="col-8d665a f-18"><span class="rate-user-info"></span><span><spring:message code="name_509"/></span> </div>
								</td>
						    </tr>
						   	<!--  模板元素，包含追评 -->
						    <tr class="module-tr tr-conflict dis-n">
						        <td width="70%" class="vert-m">
						        	<!-- 初次评价 -->
		                			<div class="min-h-60 mt-10 rate-premiere">            
			                			<div class="pull-left w-20p f-14 rate-tag">            
			                				<div class="tm-rate-title"> <spring:message code="name_206"/></div>            
			                				<div class="mt-10"><span class="col-b0b0b0 rate-date"> </span></div>
			                			</div>
			                			<div class="w-80p pull-left">
				                			<div class="f-14 col-333 line-h-20 rate-content"> </div>
				                		</div>
		                			</div>
		                			<!-- 追评 -->
	                			    <div class="min-h-30 mt-10 rate-append">     
	                			    	<div class="min-h-30 pull-left f-14 w-20p">            
			                				<div class="tm-rate-title"><span class="rate-daydiff"></span></div>            
			                			</div>
			                			
			                			<div class="pull-left w-80p">
				                			<div class="f-14 col-333 line-h-20 rate-content"> </div>
											<div class="f-14 line-h-20 mt-10">
	    										<div class="f-12 col-8a6d3b line-h-20 rate-reply"> </div>
											</div>
				                		</div>
		                			</div>
		                		</td>
						        <td class="col-author vert-m text-center">
						       		<div class="pull-left pl-10 rate-star pr-10">
                                        <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
                                        <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
                                        <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
                                        <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
                                        <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
			                        </div>
						        	<div class="col-8d665a f-18"><span class="rate-user-info"></span><span><spring:message code="name_509"/></span> </div>
								</td>
						    </tr>
					    </tbody>
					</table>
				    </div>
                	<!-- 分页 -->
					<div class="text-center appraise-pager">
						<div id="layer-pager"></div>
					</div>
				</div>
		  	</div>
            
        </div>
    </div>
    <div class="img-60 bg-9BD2F4 affix t-80s r-40 z-99 hk-upbtn dis-n cur-p">
        <img src="<%=request.getContextPath() %>/resources/img/net_shopping/up.png" class="img-40 mt-10 ml-10" alt="">
    </div>
    <%@ include file="../comm/footer.jsp" %>
	<script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script> 
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.elevatezoom.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/shopping/product.js"></script>
    <script>
    //商品放大镜插件
    $("#prode-zoom-main").elevateZoom({
        gallery: 'gallery_01',  //缩略图id
        cursor: 'pointer', //光标：十字 
        galleryActiveClass: 'active',
        easing: true, //是否开启动画效果 
        zoomWindowFadeIn: 500,//镜头窗口淡入速度 
        zoomWindowFadeOut: 750 //镜头窗口淡出速度 
    });
    </script>
</body>
</html>