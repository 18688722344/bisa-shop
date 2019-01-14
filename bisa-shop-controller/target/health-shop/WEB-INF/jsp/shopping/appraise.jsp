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
    <title><spring:message code="name510"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/user/user_center.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet" >
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <!-- 本页面是用户查看自己的用户列表 -->
    <%@ include file="../comm/header.jsp" %> 
    <div class="wrap clear bg-f5f5f5">
        <div class="container pl-0 pr-0 pos-r min-h-800">
           <div class="col-sm-8 pt-30 pl-20 pr-10 mb-20">
               
                <div class="clear mt-10 full-w bg-white pt-10 pl-10 pr-10">
                <table class="table">
				    <tbody>
				    	<!-- 模板元素，只有初次评价 -->
					    <tr class="tr-simple">
					        <td width="75%" class="vert-m">
						        <div class="mt-10">
			                		<div class="f-14 col-333 line-h-20 rate-content">还不错，包装结实完好！物流超级给力，一天到货！</div>
			                	</div>
	                			<div class="mt-10"><span class="f-14 col-b0b0b0 rate-date">2018.04.12</span></div>
	                			<div class="mt-10 mb-10">
	                				<div class="f-12 col-8a6d3b line-h-20 rate-reply">解释：亲亲的喜欢，亲亲的认同，都是我们前进的动力，正因为有您的支持，所以我们才会越做越好，我们会以我们的产品来回馈您，让您体验更优质的服务，十分感谢您对The ONE的支持与厚爱，祝您生活愉快！【TheONE官方旗舰店】</div>
	                			</div>
							</td>
					        <td class="col-author vert-m text-center">
					        	<div class="rate-star"></div>
					        	<div class="col-8d665a f-18 rate-user-info">云<span>***</span>0<span>（匿名）</span> </div>
							</td>
					    </tr>
					   	<!--  模板元素，包含追评 -->
					    <tr class="tr-conflict">
					        <td width="75%" class="vert-m">
					        	<!-- 初次评价 -->
	                			<div class="min-h-60 mt-10 rate-premiere">            
		                			<div class="pull-left w-20p f-14 rate-tag">            
		                				<div class="tm-rate-title">初次评价:</div>            
		                				<div class="mt-10"><span class="col-b0b0b0 rate-date">2018.04.12</span></div>
		                			</div>
		                			<div class="w-80p pull-left">
			                			<div class="f-14 col-333 line-h-20 rate-content">此用户没有填写评论!</div>
			                		</div>
	                			</div>
	                			<!-- 追评 -->
                			    <div class="min-h-60 mt-10 rate-append">     
                			    	<div class="min-h-60 pull-left f-14 w-20p">            
		                				<div class="tm-rate-title">收货<span class="rate-daydiff">36</span>天后追加：</div>            
		                			</div>
		                			
		                			<div class="pull-left w-80p">
			                			<div class="f-14 col-333 line-h-20 rate-content">
    										我已经完成了21天训练了。。。从什么都不会到能双手弹曲子！其实我觉得后期服务是很重要的！电子琴音色什么的都不错！超级棒！我会继续努力的！
										</div>
										<div class="f-14 line-h-20 mt-10">
    										<div class="f-12 col-8a6d3b line-h-20 rate-reply">解释：亲亲的喜欢，亲亲的认同，都是我们前进的动力，正因为有您的支持，所以我们才会越做越好，我们会以我们的产品来回馈您，让您体验更优质的服务，十分感谢您对The ONE的支持与厚爱，祝您生活愉快！【TheONE官方旗舰店】</div>
										</div>
			                		</div>
	                			</div>
	                		</td>
					        <td class="col-author vert-m text-center">
					       		<div class="rate-star"></div>
					        	<div class="col-8d665a f-18 rate-user-info">云<span>***</span>0<span>（匿名）</span> </div>
							</td>
					    </tr>
				    </tbody>
				</table>
			    </div>
                
               	<div id="appraiseList">
               	
               	</div>
	            <!--    <div class="clear mt-20 full-w h-50 line-h-50 text-center f-14 col-309DE2 bg-white cur-p <c:if test="${ productDto.total<=6}">dis-n</c:if> appraise-more">
                    	加载更多
              </div>
              <div class="clear mt-20 full-w h-50 line-h-50 text-center f-14 col-309DE2 bg-white cur-p <c:if test="${ productDto.total>6}">dis-n</c:if> appraise-daodi">
                  	  到底了
              </div> -->
               <input type="hidden" class="pager" value="${pager}">
               <input type="hidden" class="biaozhi" value="${biaozhi}">
               <div class="clear mt-20 full-w h-50 line-h-50 text-center f-14 col-309DE2 bg-white cur-p dis-n appraise-daodi">
                    <spring:message code="name511"/>
              </div>
              <div class="clear mt-20 full-w h-50 line-h-50 text-center f-14 col-309DE2 bg-white cur-p appraise-more">
	             <spring:message code="name512"/>
              </div>
           </div>
           
          <!--  PC的右浮标底部 -->
           <div class="affix t-0 mt-100 pl-20 pr-20 w-400 z-99 ml-800 hidden-sm hidden-md aff-appraiselist-right dis-n">
               <div class="clear img-100 bg-white mt-70 cur-p appraise-backup">
                   <img src="<%=request.getContextPath() %>/resources/img/user/Appraise/back-up.png" alt="">
               </div>
           </div>
       </div>
    </div>
    <%@ include file="../comm/footer.jsp" %> 
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/user/appraise.js"></script>

	<script>
		layui.use(['rate'], function(){
	  		var rate = layui.rate;
	  		 //只读
		  	  rate.render({
		  	    elem: '.rate-star'
		  	    ,value: 4
		  	    ,readonly: true
		  	  });
		});
	</script>
</body>
</html>