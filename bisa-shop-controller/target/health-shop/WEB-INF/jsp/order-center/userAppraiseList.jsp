<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.bisa.health.basic.entity.SystemContext"%>
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
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name205"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <link href="<%=request.getContextPath()%>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/user/user_center.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/shopping/appraise.css" rel="stylesheet">
    <script type="text/javascript">
    	var name213 = "<spring:message code='name213'/>"; 
		var name537 = "<spring:message code='name537'/>"; 
		var name_207 = "<spring:message code='name_207'/>"; 
		var name_208 = "<spring:message code='name_208'/>"; 
		var name_209 = "<spring:message code='name_209'/>"; 
	</script>
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<%@ include file="../comm/header.jsp" %>
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/> </a> >
                    <span class="col-252525 t-nonehove"><spring:message code="name209"/></span>
                </p>
            </div>
        </div>
        <div class="clear full-w">
             <img class="full-w" src="<%=request.getContextPath() %>/resources/img/user/User/userCenter-banner.jpg" alt="">
        </div>
        <div class="container pl-0 pr-30 mt-30 clear bg-f5f5f5 pt-30 pb-70 mb-60">
			<%@ include file="../comm/menu.jsp"%>
			
			<div class="clear col-sm-9 pr-30 pl-0">
				<div class="clear bg-white main-container">
				
				<p class="ml-30 pt-40-20-ipad f-50-40-ipad col-757575 line-h-70-50-ipad pb-20">
							<spring:message code="name196"/></p>
				
	            <div class="clear mt-10 full-w pt-10 pl-10 pr-10">
	            	<div class="no-data-div text-center f-18 col-333 pt-30 pd-30 dis-n">
						<spring:message code="name546"/>
					</div>
	                <table class="table">
					    <tbody>
					    	<!-- 模板元素，只有初次评价 -->
						    <tr class="module-tr tr-simple dis-n">
						    	<td width="25%" class="col-author vert-m text-center">
						       		 <div class="clear pt-10 pb-10">
				                    	<!-- 评分 -->
				                        <div class="clear f-16 h-35 pt-5 pb-10 pull-left text-center rate-div">
			                        		<div class="pull-left pl-10 rate-star">
				                        		<span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
				                            	<input type="hidden" class="rate-value" value="">
			                        		</div>
				                        </div>
				                    </div>
				                   	<!--  商品图片 -->
						        	<div class="col-8d665a f-18 rate-goods-info">
						        		<div class="pb-10">
						                    <div class="clear text-center">
						                        <img class="img-100-100-ipad min-h-100-100-ipad goods-img" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
						                        <p class="f-20 col-333 mt-10 cur-d goods-name">
						                           	商品名称
						                        </p>
						                    </div>
						                </div>
									</div>
								</td>
						        <td class="vert-m">
							        <div class="mt-10">
				                		<div class="f-14 col-333 line-h-20 rate-content"> </div>
				                	</div>
		                			<div class="mt-10"><span class="f-14 col-b0b0b0 rate-date"> </span></div>
		                			<div class="mt-10 mb-10">
		                				<div class="f-12 col-8a6d3b line-h-20 rate-reply"> </div>
		                			</div>
		                			
		                			<div class="clear">
				                       <textarea class="f-16 full-w appraises-two" rows="3" maxlength="100" placeholder="<spring:message code="name211"/>"></textarea>
				                    </div>
				                    
				                    <div class="clear col-sm-12 pt-10 pb-10 text-center">
				                    	 <input type="hidden" class="appraise-id" value="" />
								         <button class="h-30 w-110 f-16 col-white bg-309DE2 hovbg-2D90CF bor-none submit-appraise">
								              <spring:message code="name197"/> <!-- 发表追评 -->
								         </button>
						         	</div>
								</td>
						    </tr>
						   	<!--  模板元素，包含追评 -->
						    <tr class="module-tr tr-conflict dis-n">
						    	<td width="25%" class="col-author vert-m text-center">
						       		 <div class="clear pt-10 pb-10">
				                    	<!-- 评分 -->
				                        <div class="clear f-16 h-35 pt-5 pb-10 pull-left text-center rate-div">
				                        		<div class="pull-left pl-10 rate-star">
					                        		<span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
						                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
						                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
						                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
						                            <span class="img-25 pull-left mr-10 bg-starun star-tips"> </span>
					                            	<input type="hidden" class="rate-value" value="">
				                        		</div>
				                        </div>
				                    </div>
				                   	<!--  商品图片 -->
						        	<div class="col-8d665a f-18 rate-goods-info">
						        		<div class="pb-10">
						                    <div class="clear text-center">
						                        <img class="img-100-100-ipad min-h-100-100-ipad goods-img" src="<%=request.getContextPath() %>/resources/img/net_shopping/imgloadflase.jpg" alt="">
						                        <p class="f-20 col-333 mt-10 cur-d goods-name">
						                           	商品名称
						                        </p>
						                    </div>
						                </div>
									</div>
								</td>
						        <td class="vert-m">
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
	                			    <div class="min-h-60 mt-10 rate-append">     
	                			    	<div class="min-h-60 pull-left f-14 w-20p">            
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
   	<%@ include file="../comm/footer.jsp" %>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/order-center/appraiseList.js"></script>
	<script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
	<script src="<%=request.getContextPath() %>/resources/ctrl/layui/layui.js"></script>
</body>
</html>