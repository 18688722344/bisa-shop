<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <meta name="viewport" content="initial-scale=0.35,user-scalable=yes">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title><spring:message code="name513"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/icheck/flat/blue.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/user/user_center.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
	    label.error {
	        display: block;
	        margin: 5px 5px;
	        float: left;
	        color: #c00;
	    }
    </style>
    <script type="text/javascript">
    	var request_url = "<%=request.getContextPath() %>";
    	var message = "<%=request.getAttribute("message") %>";
    </script>
</head>

<body>
  <%@ include file="../comm/header.jsp" %>
     <div class="wrap clear bg-f5f5f5">
        <div class="clear full-w bg-white">
            <div class="container pl-0 pr-0">
                <div class="clear">
                    <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                        <a class="col-666 t-nonehove hovecol-309DE2" href="<%=request.getContextPath() %>/index"><spring:message code="homepage"/></a> >
                        <span class="col-252525 t-nonehove"><spring:message code="name32"/></span>
                    </p>
                </div>
            </div>
        </div>
        <div class="clear full-w">
            <img class="full-w" src="<%=request.getContextPath() %>/resources/img/user/User/userCenter-banner.jpg" alt="">
        </div>
        <div class="clear full-w">
             <div class="container pl-0 pr-0 mt-30 clear bg-f5f5f5 pt-30 pb-70 mb-60">
              	<%@ include file="../comm/menu.jsp" %>
                <div class="clear col-sm-9 pr-30 pl-0">
                    <div class="clear col-sm-8 pd-20">
                        <p class="pb-10 pt-20 f-26 col-757575">
                           	 <spring:message code="name514"/>
                        </p>
                    </div>
                    <div class="clear col-sm-12 clear bor bor-t bor-col-e0e0e0">
                        <div class="claer col-sm-8 pd-30-10-ipad">
                            <p class="f-20 col-666">
                               	<spring:message code="name515"/>
                            </p>
                            <div class="msg-wrap pt-15 pl-15">
                                <p class="msg-error"><b></b><spring:message code="name516"/></p>
                            </div>
                            <form class="form-inline servicact-validate clear" action="<%=request.getContextPath()%>/user/serviceActiveCodePost" method="post">
                                <div class="clear full-w">
                                    <div class="clear col-sm-8 plr-15-5-ipad">
                                     	 <div class="form-group mt-10">
										    <label class="w-85 h-30 line-h-30 f-16" for="exampleInputName2"><spring:message code="name517"/></label>
										    <input type="text" class="form-control radius-0 activate-code" name="cardNumber" placeholder="<spring:message code='name518'/>">
										  </div>
										  <div class="form-group mt-10">
										    <label class="w-85 h-30 line-h-30 f-16" for="exampleInputName2"><spring:message code="name519"/></label>
										    <input type="text" class="form-control radius-0 activate-code" name="cardCode" placeholder="<spring:message code='name520'/>">
										  </div>
										  <div class="form-group mt-10">
										    <label class="w-85 h-30 line-h-30 f-16" for="exampleInputName2"><spring:message code="name521"/></label>
										    <input type="text" class="form-control radius-0 activate-cid" name="account"  placeholder="<spring:message code='name522'/>">
										  </div>
										  <div class="form-group mt-10">
										    <label class="w-85 h-30 line-h-30 f-16" for="exampleInputName2"><spring:message code="name523"/></label>
										     <input type="text" class="form-control radius-0 activate-cidagain" name="setAccount" placeholder="<spring:message code='name524'/>">
										  </div>
                                        <p class="mt-30">
                                            <input type="hidden" class="current-account" value="${currentAccount}">
                                            <input class="activatecheckv1" type="checkbox">
                                            <span class="col-757575 f-16 ml-10 pos-r t-2"><spring:message code="name525"/></span>
                                        </p>
                                    </div>
                                </div>
                                <div class="clear col-sm-12 mt-40 plr-15-5-ipad">
                                    <button class="bor-none pl-40 pr-40 col-white bg-309DE2 hovbg-2D90CF f-18 h-35 line-h-35" type="submit"><spring:message code="name526"/></button>
                                    <p class="col-666 f-14 line-h-20 mt-10">
                                        <spring:message code="name527"/>
                                    </p>
                                </div>
                            </form>
                        </div>
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
	</script>
    <script src="<%=request.getContextPath()%>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/comm/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/comm/jquery.validate.messages_zh.js"></script>
    <script src="<%=request.getContextPath()%>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/ctrl/icheck/icheck.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/menu.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/user/serviceActiveCode.js"></script>
</body>
</html>