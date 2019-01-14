<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.bisa.health.basic.entity.SystemContext" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ include file="../comm/tag.jsp" %>
<%  String menuType="userActive"; %>

<!DOCTYPE html>
<html>
<head>
	<link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="initial-scale=0.35,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name540"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath()%>/resources/ctrl/layer/skin/default/layer.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/user/user_center.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	var currentAccount = '${currentAccount}';
    </script>
</head>

<body>
   <%@ include file="../comm/header.jsp" %>
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/></a> >
                    <span class="col-252525 t-nonehove"><spring:message code="name541"/></span>
                </p>
            </div>
        </div>
        <div class="clear full-w">
           <img class="full-w" src="<%=request.getContextPath() %>/resources/img/user/User/userCenter-banner.jpg" alt="">
        </div>
        <div class="clear full-w">
        <div class="container pl-0 pr-0 mt-30 clear bg-f5f5f5 pt-30 pb-70 mb-60">
            <%@ include file="../comm/menu.jsp" %>
            <div class="clear col-sm-9 pr-30 pl-0">
                <div class="clear bg-white pb-280 plr-50-10-ipad">
                    <p class="pt-40-20-ipad f-50-40-ipad col-757575 line-h-70-50-ipad pb-20">
                      	 <spring:message code="name541"/>
                      	 <span class="clear pull-right dis-ib f-14 col-757575 pl-20 pr-20 h-30 line-h-30 bor bor-col-ccc cur-p mt-20 service-status">
							 <span style="color: red">服务状态</span>
                         </span>
                      	 <span class="clear pull-right dis-ib f-14 col-757575 pl-20 pr-20 h-30 line-h-30 bor bor-col-ccc cur-p mt-20">
                          		 <a href="<%=request.getContextPath()%>/user/serviceActiveCode"><spring:message code="name542"/> </a>
                         </span>
                    </p>
                    <div class="clear bor bor-b bor-col-f5f5f5"></div>
					<div class="clear mt-40-20-ipad f-20-14-ipad mb-30-20-ipad line-h-25">
						<span class="col-333 col-active cur-p service-control service-controlv1"><spring:message code="name543"/></span>
						<span class="col-e9e9e9 mlr-20-10-ipad cur-d">|</span> 
						<span class="col-333  cur-p service-control service-controlv2"><spring:message code="name544"/></span> 
						<span class="col-e9e9e9 mlr-20-10-ipad cur-d">|</span>
						<span class="col-333 cur-p service-control service-controlv3"><spring:message code="name545"/></span>
					</div>
					<div class="no-data-div text-center f-18 col-333 pt-30 pd-30">
						<spring:message code="name546"/>
					</div>
                    <div class="clear bor bor-b bor-col-f5f5f5">
                    </div>

                    <div class="main-div">
                    	<!-- 模板元素 -->
	                    <div class="w-350 pull-left mr-35 card-main-div">
	                        <div class="clear mt-40 h-130 line-h-130 text-center">
	                            <div class="col-sm-4 pl-25 pr-25">
	                                <img class="goods_img" src="<%=request.getContextPath()%>/resources/img/user/Service/service-prov1.png" alt="">
	                            </div>
	                            <p class="bor bor-col-b0b0b0 col-309DE2 f-22 col-333 pl-30 pr-10 card-name">
	                               	<spring:message code="name547"/>
	                            </p>
	                        </div>
	                        <div class="clear bor bor-t-none bor-col-b0b0b0">
	                            <div class="clear pl-15 pt-15 pb-15">
	                                <p class="f-16 col-333 line-h-20">
	                                   	 <spring:message code="name548"/><span class="f-14 col-757575 line-h-20 card-number"></span>
	                                </p>
	                                <p class="mt-15 f-16 col-333 line-h-20">
	                                	<spring:message code="name549"/><span class="f-14 col-757575 line-h-20 card-active-code"></span>
	                                </p>
	                                <p class="mt-15 f-16 col-333 line-h-20">
	                                	<spring:message code="name550"/><span class="f-14 col-757575 line-h-20 card-count"></span>&nbsp;<span class="f-14 line-h-20 card-unit"></span>
	                                </p>
	                                <p class="mt-15 f-16 col-333 line-h-20">
	                                	<spring:message code="name551"/><span class="f-14 col-757575 line-h-20 create-time"></span>
	                                </p>
	                                <p class="mt-15 f-16 col-333 line-h-20">
	                                	<spring:message code="name552"/><span class="f-14 col-757575 line-h-20 card-status"></span>
	                                </p>
	                            </div>
	                            <div class="h-75 bor bor-t bor-col-ccc pl-15 pr-40">
	                                <div class="clear pt-20 pb-20 active-buttons dis-n">
	                                    <p class="clear line-h-30">
	                                    	<input type="hidden" class="card-number" value="">
	                                    	<input type="hidden" class="card-record-json" value=""/>
	                                        <button type="button" class="ml-20 btn btn-info active-to-self"><spring:message code="name553"/></button>
	                                        <button type="button" class="ml-20 btn btn-info active-to-other"><spring:message code="name554"/></button>
	                                        <a class="pull-right f-12 col-757575 hovcol-309DE2 t-nonehove" href="index">
	                                           	<spring:message code="name555"/>
	                                        </a>
	                                    </p>
	                                </div>
	                                <div class="clear pt-20 pb-20 records-buttons dis-n">
	                                    <p class="clear line-h-30">
	                                    	<input type="hidden" class="card-record-json" value=""/>
	                                        <a class="pull-right f-12 col-757575 hovcol-309DE2 t-nonehove show-card-record-btn">
	                                           	 <spring:message code="name556"/>
	                                        </a>
	                                    </p>
                                	</div>
	                            </div>
	                        </div>
	                     </div>
                     </div>
                     
                     <!-- 代他人激活服务 弹出层 -->
                     <div class="layui-card-body active-to-other-div f-14 dis-n">
                     <form class="layui-form" id="active_account_form" >
                    	 <div class="layui-form-item">
							<label class="form-label"><spring:message code="name557"/></label>
							<div class="input-block">
								<input type="text" id="active_account" lay-verify="required"
									placeholder="<spring:message code="name558"/>" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="form-label"><spring:message code="name559"/></label>
							<div class="input-block">
								<input type="text" id="set_account" lay-verify="required"
									placeholder="<spring:message code="name560"/>" autocomplete="off" class="layui-input">
							</div>
						</div>
						
						<div class="layui-form-item">
							<div class="text-center">
								<input type="hidden" class="card-record-json" value="" />
								<button type="button" class="layui-btn " lay-submit="" id="sub-active-btn" lay-filter="sub-active-btn"><spring:message code="name526"/></button>
								<button type="reset" class="layui-btn layui-btn-primary" id="reset-report-btn"><spring:message code="name140"/></button>
							</div>
						</div>
						</form>
                     </div>
                     
                     <!-- 服务激活卡使用记录 的弹出层 -->
					<div class="record-div f-14 dis-n pb-15 pr-15">
						<div>
							<div class="mt-15 ml-15">
								<label><spring:message code="name561"/></label>
								<label class="ml-15" id="card-account"></label>
							</div>
							<div class="mt-15 ml-15">
								<label><spring:message code="name562"/></label>
								<label class="ml-15" id="card-time"></label>
							</div>
						</div>
					</div>
                </div>
                
                <!-- 分页 -->
				<div class="clear text-center service-pager">
					<div id="layer-pager"></div>
				</div>
            </div>
        </div>
        </div>
    </div>
    
    <%@ include file="../comm/footer.jsp" %> 
    <script type="text/javascript">
		var name528 = "<spring:message code='name528'/>";
		var name529 = "<spring:message code='name529'/>";
		var name530 = "<spring:message code='name530'/>";
		var name531 = "<spring:message code='name531'/>";
		var name532 = "<spring:message code='name532'/>";
		var name533 = "<spring:message code='name533'/>";
		var name534 = "<spring:message code='name534'/>";
		var name535 = "<spring:message code='name535'/>";
		var name536 = "<spring:message code='name536'/>";
		var name537 = "<spring:message code='name537'/>";
		var name538 = "<spring:message code='name538'/>";
		var name539 = "<spring:message code='name539'/>";

		var name735 = "<spring:message code='name735'/>";
		var name736 = "<spring:message code='name736'/>";
		var name737 = "<spring:message code='name737'/>";
		var name32 = "<spring:message code='name32'/>";
		var name554 = "<spring:message code='name554'/>";
		var name738 = "<spring:message code='name738'/>";
		var name739 = "<spring:message code='name739'/>";
		var name740 = "<spring:message code='name740'/>";
		var name741 = "<spring:message code='name741'/>";
		var name742 = "<spring:message code='name742'/>";
		var name743 = "<spring:message code='name743'/>";
		var name744 = "<spring:message code='name744'/>";
		var name745 = "<spring:message code='name745'/>";
		var name746 = "<spring:message code='name746'/>";
		var name747 = "<spring:message code='name747'/>";

        var name748 = "<spring:message code='name748'/>";
        var name749 = "<spring:message code='name749'/>";
        var name750 = "<spring:message code='name750'/>";
	</script>
	<script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/menu.js"></script>
    <script src="<%=request.getContextPath()%>/resources/ctrl/layer/layer.js"></script>
    <script src="<%=request.getContextPath()%>/resources/ctrl/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/user/serviceActive.js"></script>
</body>
</html>