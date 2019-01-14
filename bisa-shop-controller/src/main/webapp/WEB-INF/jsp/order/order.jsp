<%@ page language="java"  pageEncoding="UTF-8"%>
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
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <meta name="renderer" content="webkit">
    <title><spring:message code="name109"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/index/index.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/user/user_center.css" rel="stylesheet">
    <!--[if lt IE 9]>
	<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript">
		var name146 = '<spring:message code="name146"/>';
		var name147 = '<spring:message code="name147"/>';
		var name148 = '<spring:message code="name148"/>';
		var name149 = '<spring:message code="name149"/>';
		var name150 = '<spring:message code="name150"/>';
		var name151 = '<spring:message code="name151"/>';
		var name152 = '<spring:message code="name152"/>';
		var name153 = '<spring:message code="name153"/>';
		var name154 = '<spring:message code="name154"/>';
		var name155 = '<spring:message code="name155"/>';
		var name156 = '<spring:message code="name156"/>';
		var name157 = '<spring:message code="name157"/>';
		var name158 = '<spring:message code="name158"/>';
		
		var name159 = '<spring:message code="name159"/>';
		var name160 = '<spring:message code="name160"/>';
		var name161 = '<spring:message code="name161"/>';
		var name162 = '<spring:message code="name162"/>';
		var name163 = '<spring:message code="name163"/>';
		var name164 = '<spring:message code="name164"/>';
		var name165 = '<spring:message code="name165"/>';
		var name166 = '<spring:message code="name166"/>';
		var name167 = '<spring:message code="name167"/>';

        var name_147 = '<spring:message code="name_147"/>';
        var name_148 = '<spring:message code="name_148"/>';
        var name_167 = "<spring:message code='name_167'/>";
        var name_168 = "<spring:message code='name_168'/>";
	</script>
    <style>
    label.error {
        display: block;
        margin: 5px 5px;
        float: left;
        color: #c00;
    }
    </style>
    <script>
		var ajax_url = "<%=request.getContextPath()%>";
	</script>
</head>
<body>
    <%@ include file="../comm/header.jsp" %>
    <div class="wrap">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 bg-f5f5f5 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath()%>/index"><spring:message code="homepage"/></a> >
                    <span class="col-252525 t-nonehove" onclick="window.location.href='<%=request.getContextPath()%>/user/Cart'"><spring:message code="shopCar"/></span>
                </p>
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-40 cur-d col-252525 family-h f-26">
                    <spring:message code="name110"/>
                </p>
            </div>
            <div class="clear pt-20 pl-20 pr-20 bg-f5f5f5 pb-45 full-w cur-d">
                <div class="clear bg-white pd-20">
                    <p class="pl-15 pr-15 line-h-30 col-252525 f-18">
                       	 <spring:message code="name111"/>
                    </p>
                    <div class="clear full-w conanorder-main address-main-div">
                       <!-- 循环 获取收货地址信息   -->
                       <div class="clear col-sm-3 pd-15-5-ipad conanorder-block address-div">
                           <div class="clear pd-15-5-ipad bor bor-col-7A7A7A min-h-190-160-ipad address-tips-in">
                               <p class="line-h-30-20-ipad f-18 family-h">
                                   <span class="col-252525 add-name"></span>
                               </p>
                               <p class="line-h-20 mt-15-0-ipad f-14 family-h">
                                   <span class="col-555 add-phone"></span>
                               </p>
                               <p class="line-h-25 f-14 family-h text-line-4">
                                   <span class="col-555 add-address"></span>
                               </p>
                               <input type="hidden" class="address-id" value="">
                               <input type="hidden" class="address-json" value=""/>
                               <p class="clear ptb-10-5-ipad f-14 family-h line-h-20">
                                   <span class="pull-right col-309DE2 cur-p t-nonehove address-tips-del ml-10"><spring:message code="name112"/></span>
                                   <span class="pull-right col-309DE2 cur-p t-nonehove address-tips-edit"><spring:message code="name113"/></span>
                               </p>
                           </div>
                       </div>
                       <!-- 添加收货地址 -->
                       <div class="clear col-sm-3 pd-15-5-ipad address-tipsv2">
                           <div class="clear pd-50-30-ipad bor bor-col-B2B2B2 min-h-190-160-ipad conanorder-tipsadd add-address-control">
                               <div class="col-white family-h f-26 line-h-50 f-w text-center clear img-50 full-radius bg-eee mg-0-auto cur-p conanorder-tips-addv2 address-tipsv2-circle">
                                   +
                               </div>
                               <p class="text-center pt-10 f-14 family-h line-h-25 address-tipsv2-text">
                                   <a class="col-B2B2B2 t-nonehove conanorder-tips-add " href=""><spring:message code="name114"/></a>
                               </p>
                           </div>
                       </div>
                    </div>
                    
                    <div class="clear mt-20 pt-20 pb-20 line-h-30 cur-d bor bor-b bor-col-B2B2B2">
                        <span class="f-18 col-252525 family-h dis-ib mr-30">
                            <spring:message code="name115"/>
                        </span>
                        <span class="f-14 col-309DE2 family-h dis-ib">
                            <spring:message code="name116"/>
                        </span>
                    </div>
                    <div class="clear pt-20 pb-20 line-h-30 cur-d bor bor-b bor-col-B2B2B2">
                        <span class="f-18 col-252525 family-h dis-ib mr-30">
                            <spring:message code="name117"/>
                        </span>
                        <span class="f-14 col-309DE2 family-h dis-ib">
                            <spring:message code="name118"/>
                        </span>
                    </div>
                    <div class="clear mt-20 pt-20 line-h-30 cur-d bor bor-b bor-col-B2B2B2">
                        <span class="f-18 col-252525 family-h dis-ib mr-30">
                            <spring:message code="name119"/>
                        </span>
                        <a class="f-14 col-252525 mr-10 family-h dis-ib pull-right t-nonehove hovecol-309D E2" onclick="window.location.href='<%=request.getContextPath()%>/web/call/toCart'">
                            <spring:message code="name120"/>>
                        </a>
                  </div>
				  
                  <!-- 1------------------------下面就是商品的列表------------------------------------------------1 -->  
              	  <c:if test="${not empty goodsList}">
                        <c:forEach items="${goodsList}" var="goodsCombo" varStatus="status">
                            <div class="clear full-w pt-10 pb-10">
                                <div class="clear col-sm-7 line-h-30 h-30 pl-0 pr-0">
                                    <img class="img-30" src="${goodsCombo.imgUrl}" alt="">
                                    <span class="f-14 family-h col-252525">${goodsCombo.goodsName }</span>
                                </div>
                                <div class="clear col-sm-5  pl-0 pr-0">
                                    <div class="col-sm-4 line-h-30 h-30 pl-0 pr-0">
                                        <span class="f-14 family-h col-252525">
                                           <fmt:formatNumber value="${goodsCombo.discountPrice}" pattern="#0.00"/>
                                        </span>
                                        <span class="f-14 family-h col-252525">
                                            ×
                                        </span>
                                        <span class="f-14 family-h col-252525">
                                        	 ${goodsCombo.count}
                                        </span>
                                    </div>
                                    <div class="col-sm-4 line-h-30 h-30 pl-0 pr-0">
                                        <span class="f-14 family-h col-252525">
                                            <spring:message code="name121"/>
                                        </span>
                                    </div>
                                    <div class="col-sm-4 line-h-30 h-30 pl-0 pr-0">
                                    	<span class="f-14 family-h col-309DE2">
                                            <spring:message code="name103"/>
                                        </span>
                                        <span class="f-14 family-h col-309DE2">
                                             <fmt:formatNumber value="${goodsCombo.discountPrice * goodsCombo.count}" pattern="#0.00"/>
                                        </span>
                                        
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

               <!-- 1-----------------------------所有商品的合计信息的  地方------------------------------------------1 -->
               <div class="clear full-w pt-20 pb-20 bor bor-t bor-col-B2B2B2">
                   <div class="clear col-sm-3 pull-right pr-0 pl-0">
                       <div class="col-sm-6 text-right h-20 line-h-20 pr-0 pl-0">
                           <span class="f-14 family-h col-252525"><spring:message code="name122"/></span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pr-0 pl-0">
                           <span class="f-14 family-h col-309DE2">
                                ${totalCount }
                           </span>
                           <span class="f-14 family-h col-309DE2">
                               <spring:message code="name123"/>
                           </span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pr-0 pl-0">
                           <span class="f-14 family-h col-252525"><spring:message code="name124"/></span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pr-0 pl-0">
                           <span class="f-14 family-h col-309DE2">
                               <spring:message code="name103"/>
                           </span>
                           <span class="f-14 family-h col-309DE2" id="totalPrice">
                                <fmt:formatNumber value="${totalPrice }" type="currency" pattern="#0.00"/>
                           </span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pr-0 pl-0">
                           <span class="f-14 family-h col-252525"><spring:message code="name125"/></span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pr-0 pl-0">
                           <span class="f-14 family-h col-309DE2">
                               <spring:message code="name103"/>
                           </span>
                           <span class="f-14 family-h col-309DE2" id="preferentialPrice">
                          		 <fmt:formatNumber value="${discountPirce}" pattern="#0.00"/>
                           </span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pr-0 pl-0">
                           <span class="f-14 family-h col-252525"><spring:message code="name126"/></span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pr-0 pl-0">
                           <span class="f-14 family-h col-309DE2">
                               <spring:message code="name103"/>
                           </span>
                           <%--邮费--%>
                           <span class="f-14 family-h col-309DE2" id="postPrice">
                               <fmt:formatNumber value="${postPrice}" pattern="#0.00"/>
                           </span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pt-20 pr-0 pl-0">
                           <span class="f-14 family-h col-252525"><spring:message code="name127"/></span>
                       </div>
                       <div class="col-sm-6 text-right h-20 line-h-20 pt-20 pr-0 pl-0">
                           <span class="f-14 family-h col-309DE2">
                               <spring:message code="name103"/>
                           </span>
                           <span class="f-20 family-h col-309DE2" id="actualPayment">
                               <fmt:formatNumber value="${actuallyPrice }" pattern="#0.00"/>
                           </span>
                       </div>
                   </div>
               </div>
                
          <!-- 1-----------------------------------------下单按钮-------------------------------------1 -->  
          <form action="" method="post">
              <div class="clear mt-5 bg-white pd-20">
                  <div class="col-sm-9 clear pl-10 pr-10 cur-d conanorder-tips-showmsg">
                      <p class="f-16 family-h line-h-20">
                          <input type="hidden" id="addr_id" name="addr_id">
                      </p>
                      <p class="f-16 family-h line-h-20">
                      	<span class="col-252525 select-address-name"></span>
                      	<span class="col-252525 select-address-phone"></span>
                        <span class="col-252525 select-address"></span>
                      </p>
                      <input type="hidden" name="token" id="token" value="${token}">
                  </div>						
                  <div class="col-sm-3 clear pl-0 pr-0">
                      <button class="full-w h-40 line-h-40 col-white f-14 family-h text-center bor-none bg-309DE2 hovbg-2D90CF" type="button" onclick="submitOrder();">
                          	<spring:message code="name128"/>
                      </button>
                  </div>
              </div>
           </form>
            </div>
        </div>
     </div>
        
    <!-- 1---------------------------下面是模态框-------------------------------------------------1 -->
    <!-- 添加收货地址 -->
    <div class="clear show-add-shippingaddress affix t-0 l-0 z-999 full-wh rgba-60 dis-n">
        <div class="pos-a t-30s l-0 full-w">
            <div class="w-600 mg-0-auto clear pos-r bg-white show-add-content">
                <div class="pt-15 pb-15 line-h-30 pos-r f-18 pl-20 pr-20 col-4a4a4a full-w bg-f5f5f5">
                   	<spring:message code="name129"/>
                    <img class="pos-a t-20 r-20 img-20 close-mod cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/close.png" alt="">
                </div>
                 <form class="clear pos-r shippingaddress-add" action="">
                    <div class="clear pd-40 mt-20">
                        <div class="clear full-w">
                            <div class="col-xs-6 text-center pl-0 pr-10 pos-r">
                                <input type="hidden" class="show-input-shipping-value" value="<spring:message code="name143"/>">
                                <input class="full-w bor h-36 line-h-36 bor-col-B2B2B2 pl-15 family-h f-14 col-252525 show-input-shipping" type="text" name="shname" id="shname">
                                <div class="clear pos-a t-11 l-15 f-14 show-div-shipping col-9a9a9a bg-white"><spring:message code="name130"/></div>
                            </div>
                            <div class="col-xs-6 text-center pl-10 pr-10 pos-r">
                                <input type="hidden" class="show-input-shipping-value" value="<spring:message code="name144"/>">
                                <input class="full-w bor h-36 line-h-36 bor-col-B2B2B2 pl-15 family-h f-14 col-252525 show-input-shipping" type="text" name="shphone" id="shphone">
                                <div class="clear pos-a t-11 l-15 f-14 show-div-shipping col-9a9a9a bg-white"><spring:message code="name131"/></div>
                            </div>
                        </div>
                    	<!-- 地址三级联动 -->
                        <div class="form-group mt-10">
				            <span class="clear col-sm-2 f-14 mt-10"><spring:message code="name111"/></span>
				            <div class="col-sm-3 pl-10">
				                <select name="input_province" id="input_province" class="form-control" >
				                    <option value="">--<spring:message code="name132"/>--</option>
				                </select>
				            </div>
				            <div class="col-sm-3 pl-10">
				                <select name="input_city" id="input_city" class="form-control">
				                    <option value=""></option>
				                </select>
				            </div>
				            <div class="col-sm-3 pl-10">
				                <select name="input_county" id="input_county" class="form-control">
				                    <option value=""></option>
				                </select>
				            </div>
				        </div>
				        <!-- 详细地址 -->
                        <div class="col-xs-12 text-center mt-15 pl-0 pr-0 pos-r">
                            <input type="hidden" class="show-input-shipping-value" value="<spring:message code="name145"/>">
                            <textarea rows="2" cols="20" class="full-w bor line-h-36 bor-col-B2B2B2 pl-15 family-h f-14 col-252525 show-input-shipping" type="text" name="shaddress" id="shaddress"></textarea>
                            <div class="clear pos-a t-11 l-15 f-14 show-div-shipping col-9a9a9a bg-white"><spring:message code="name133"/></div>
                        </div>
                        <div class="form-group mt-15">
                        	<span class="clear col-sm-2 f-14 mt-20"><spring:message code="name134"/></span>
	                        <div class="col-sm-3 pl-10 mt-10">
					            <select name="address_label" id="address_label" class="form-control">
					                <option value="1"><spring:message code="name135"/></option>
					                <option value="2"><spring:message code="name136"/></option>
					                <option value="10"><spring:message code="name137"/></option>
					            </select>
					        </div>
				        	<!-- 值为0时候未选中，为一的时候为选中 -->
				          	 <div class="clear col-sm-3 f-14 mt-20 ml-20">
			                    <div class="i-check clear pull-left pos-r">  
							       <input class="is-default" name="is_default" id="is_default" type="checkbox" value="0">
							       <label></label>  
								</div> 
								<label class="pull-left cur-p f-14 line-h-30 ml-10"><spring:message code="name138"/></label>
							 </div>
				        </div>
                    </div>
                    <div class="clear full-w h-75 bg-f5f5f5 mt-10 pt-20 pb-20 line-h-35 text-center">
                        <button type="submit" class="full-h w-150 mr-10 bor-none bg-309DE2 hovbg-2D90CF col-white"><spring:message code="name139"/></button>
                        <button type="reset" class="full-h w-150 ml-10 bor-none bg-9a9a9a hovbg-666 col-white"><spring:message code="name140"/></button>
                    </div>
                 </form> 
            </div>
        </div>
    </div>
    <!-- 修改收货地址 -->
    <div class="clear show-revise-shippingaddress affix t-0 l-0 z-999 full-wh rgba-60 dis-n">
        <div class="pos-a t-30s l-0 full-w">
            <div class="w-600 mg-0-auto clear pos-r bg-white show-revise-content">
                <div class="pt-15 pb-15 line-h-30 pos-r f-18 pl-20 pr-20 col-4a4a4a full-w bg-f5f5f5">
                  	  <spring:message code="name141"/>
                    <img class="pos-a t-20 r-20 img-20 close-mod cur-p" src="<%=request.getContextPath() %>/resources/img/net_shopping/close.png" alt="">
                </div>
                <form class="clear pos-r shippingaddress-revise" action="">
                    <div class="clear pd-40 mt-20">
                        <div class="full-w clear">
                            <div class="col-xs-6 text-center pl-0 pr-10 pos-r">
                                <input type="hidden" class="show-input-shipping-value" value="<spring:message code="name143"/>">
                                <input class="full-w bor h-36 line-h-36 pl-15 family-h f-14 col-252525 show-input-shipping valid bor-col-B2B2B2" type="text" name="shname" id="upname" placeholder="" aria-required="true" aria-invalid="false">
                                <div class="clear pos-a t-11 l-15 f-14 show-div-shipping col-9a9a9a bg-white" style="top: -6px; font-size: 12px;"><spring:message code="name130"/></div>
                            </div>
                       		<div class="col-xs-6 text-center pl-10 pr-0 pos-r">
                                <input type="hidden" class="show-input-shipping-value" value="<spring:message code="name144"/>">
                                <input class="full-w bor h-36 line-h-36 pl-15 family-h f-14 col-252525 show-input-shipping valid bor-col-B2B2B2" type="text" name="shphone" id="upphone" placeholder="" aria-required="true" aria-invalid="false">
                                <div class="clear pos-a t-11 l-15 f-14 show-div-shipping col-9a9a9a bg-white" style="top: -6px; font-size: 12px;"><spring:message code="name131"/></div>
                            </div>
                        </div>
                        
                        <input id="up_id" name="up_id" type="hidden" value=""/>
                        
                    	<!--收货地址 三级联动 -->
                        <div class="form-group mt-10">
				            <span class="clear col-sm-2 f-14 mt-10"><spring:message code="name111"/></span>
				            <div class="col-sm-3 pl-10">
				                <select name="update_province" id="update_province" class="form-control" >
				                </select>
				            </div>
				            <div class="col-sm-3 pl-10">
				                <select name="update_city" id="update_city" class="form-control">
				                </select>
				            </div>
				            <div class="col-sm-3 pl-10">
				                <select name="update_county" id="update_county" class="form-control">
				                </select>
				            </div>
				        </div>
				        
                        <div class="col-xs-12 text-center mt-15 pl-0 pr-0 pos-r">
                            <input type="hidden" class="show-input-shipping-value" value="<spring:message code="name145"/>">
                            <textarea rows="2" cols="20" class="full-w bor line-h-36 pl-15 family-h f-14 col-252525 show-input-shipping bor-col-B2B2B2 valid" type="text" name="shaddress" id="upaddress" placeholder="" aria-required="true" aria-invalid="false"></textarea>
                            <div class="clear pos-a t-11 l-15 f-14 show-div-shipping col-9a9a9a bg-white" style="top: -6px; font-size: 12px;"><spring:message code="name133"/></div>
                        </div>
                        <div class="form-group mt-15">
                        	<span class="clear col-sm-2 f-14 mt-20"><spring:message code="name134"/></span>
	                        <div class="col-sm-3 pl-10 mt-10">
					            <select name="up_address_label" id="up_address_label" class="form-control">
					                <option value="家"><spring:message code="name135"/></option>
					                <option value="公司"><spring:message code="name136"/></option>
					                <option value="学校"><spring:message code="name142"/></option>
					                <option value="其他"><spring:message code="name137"/></option>
					            </select>
					        </div>
				          	 <!-- 值为0时候未选中，为一的时候为选中 -->
				          	 <div class="clear col-sm-3 f-14 mt-20 ml-20">
			                    <div class="i-check clear pull-left pos-r">  
							       <input class="is-default" name="up_is_default" id="up_is_default" type="checkbox" value="0">
							       <label></label>  
								</div> 
								<label class="pull-left cur-p f-14 line-h-30 ml-10"><spring:message code="name138"/></label>
							 </div>
				        </div>
                    </div>
                    <div class="clear full-w h-75 bg-f5f5f5 mt-10 pt-20 pb-20 line-h-35 text-center">
                        <button type="submit" class="full-h w-150 mr-10 bor-none bg-309DE2 hovbg-2D90CF col-white"><spring:message code="name139"/></button>
                        <button type="reset" class="full-h w-150 ml-10 bor-none bg-9a9a9a hovbg-666 col-white"><spring:message code="name140"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </div>
    
    <%@ include file="../comm/footer.jsp" %>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.validate.messages_zh.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/user/userAddress.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/user/address.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/order/order.js"></script>
</body>
</html>