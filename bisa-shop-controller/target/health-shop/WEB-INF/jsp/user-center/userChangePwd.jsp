<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../comm/tag.jsp" %>
<%  String menuType="userPassword"; %>

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
    <title><spring:message code="name513"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css">
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/user/user_center.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
    label.error {
        display: block;
        margin: 3px 0px 0px 7px;
        height: 20px;
        line-height: 20px;
        float: left;
        color: #c00;
    }
    </style>
</head>

<body>
    <%@ include file="../comm/header.jsp" %> 
    <div class="wrap clear">
        <div class="container pl-0 pr-0">
            <div class="clear">
                <p class="pt-10 pb-10 pl-15 pr-15 line-h-20 cur-d ">
                    <a class="col-666 t-nonehove hovecol-309DE2" href=""><spring:message code="homepage"/></a> >
                    <span class="col-252525 t-nonehove"><spring:message code="name594"/></span>
                </p>
            </div>
        </div>
        <div class="clear full-w">
             <img class="full-w" src="<%=request.getContextPath() %>/resources/img/user/User/userCenter-banner.jpg" alt="">
        </div>
        <div class="container pl-0 pr-0 mt-30 clear bg-f5f5f5 pt-30 pb-70 mb-60">
  			<%@ include file="../comm/menu.jsp" %>
            <div class="clear col-sm-9 pr-30 pl-0">
                <div class="clear bg-white pb-100 plr-50-10-ipad pt-40">
                    <!-- 设置或者修改密码的标题 -->
                    <p class="pt-40-20-ipad f-50-40-ipad col-757575 line-h-70-50-ipad pb-20">
                    	<spring:message code="name595"/>
                    </p>
                    <div class="clear bor bor-b bor-col-f5f5f5">
                    </div>
                    <p class="col-616161 f-20 mt-25 mb-25">
                       	 <spring:message code="name596"/>
                    </p>
                    <!-- 在这里进行相关的赋值和js操作 -->
                    <input type="hidden" class="safety-phonenum" value="${phone}">
                    
                    <form action="" class="formvalidate-setpwd">
                        <div class="clear">
                            <div class="form-group col-xs-5 pl-0 pr-0 min-h-65 mb-20">
                                <input type="password" class="form-control radius-0 user-newpwd" name="newpwd" placeholder="<spring:message code='name597'/>">
                            </div>
                        </div>
                        <div class="clear">
                            <div class="form-group col-xs-5 pl-0 pr-0 min-h-65 mb-20">
                                <input type="password" class="form-control radius-0 user-repwd" name="repwd" placeholder="<spring:message code='name598'/>">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary hovbg-2D90CF radius-0 bg-309DE2 bor-none pl-30 pr-30 btn-sure-sub"><spring:message code="name599"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../comm/footer.jsp" %>
    
    <!-- 验证手机弹出层 -->
    <div class="clear show-verifyphone affix t-0 l-0 z-999 full-wh rgba-60 dis-n">
        <div class="pos-a t-150 l-0 full-w">
            <div class="w-500 mg-0-auto clear pos-r bg-white verifyphone-content radius-5 t--300 modal-content">
                <div class="pt-10 pb-10 line-h-25 pos-r f-20 pl-20 pr-20 col-333 full-w radius-5 cur-d">
                    <spring:message code="name602"/>
                    <img class="pos-a t-10 r-20 img-20 close-mod cur-p" src="<%=request.getContextPath() %>/resources/img/user/User/close.png" alt="">
                </div>
                <div class="clear bor bor-t bor-col-f2 pt-20 pb-20 pl-60 pr-60">
                    <p class="col-212121 f-16 mt-20">
                        <spring:message code="name603"/>
                    </p>
                    <p class="col-212121 f-16 mt-30">
                        <spring:message code="name604"/><span class="faceing-phone col-9d9d9d"></span>
                    </p>
                    <form action="" class="formvalidate-edit-bound-phone">
                        <div class="clear mt-30">
                            <div class="clear col-xs-8 pl-0 pr-20 h-40 line-h-40">
                                <input type="text" class="full-w h-40 line-h-40 form-control" id="varify_phone_code" placeholder="<spring:message code='name605'/>" maxlength="4" name="edbophone_code">
                            </div>
                            <div class="clear col-xs-4 pl-0 pr-0">
                                <button type="button" class="btn btn-default h-40 full-w sendphonemsg"><spring:message code="name606"/></button>
                            </div>
                        </div>
                        <div class="clear mt-40">
                            <div class="clear col-xs-6 text-left">
                                <button type="submit" class="btn btn-success pl-40 pr-40 varify_phone_submit"><spring:message code="name234"/></button>
                            </div>
                            <div class="clear col-xs-6 text-right">
                                <button type="reset" class="btn btn-default pl-40 pr-40 close-mod"><spring:message code="name607"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 验证邮箱弹出层 -->
    <div class="clear show-verifyemail affix t-0 l-0 z-999 full-wh rgba-60 dis-n">
        <div class="pos-a t-150 l-0 full-w">
            <div class="w-500 mg-0-auto clear pos-r bg-white verifyemail-content radius-5 t--300 modal-content">
                <div class="pt-10 pb-10 line-h-25 pos-r f-20 pl-20 pr-20 col-333 full-w radius-5 cur-d">
                    <spring:message code="name608"/>
                    <img class="pos-a t-10 r-20 img-20 close-mod cur-p" src="<%=request.getContextPath() %>/resources/img/user/User/close.png" alt="">
                </div>
                <div class="clear bor bor-t bor-col-f2 pt-20 pb-20 pl-60 pr-60">
                    <p class="col-212121 f-16 mt-20">
                        <spring:message code="name609"/>
                    </p>
                    <p class="col-212121 f-16 mt-30">
                        <spring:message code="name610"/><span class="faceing-email col-9d9d9d"></span>
                    </p>
                    <form action="" class="formvalidate-edit-bound-email">
                        <div class="clear mt-30">
                            <div class="clear col-xs-8 pl-0 pr-20 h-40 line-h-40">
                                <input type="text" class="full-w h-40 line-h-40 form-control" id="varify_mail_code" placeholder="<spring:message code='name611'/>" maxlength="4" name="edboemail_code">
                            </div>
                            <div class="clear col-xs-4 pl-0 pr-0">
                                <button type="button" class="btn btn-default h-40 full-w sendemailmsg"><spring:message code="name612"/></button>
                            </div>
                        </div>
                        <div class="clear mt-40">
                            <div class="clear col-xs-6 text-left">
                                <button type="submit" class="btn btn-success pl-40 pr-40 varify_mail_submit"><spring:message code="name234"/></button>
                            </div>
                            <div class="clear col-xs-6 text-right">
                                <button type="reset" class="btn btn-default pl-40 pr-40 close-mod"><spring:message code="name607"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	var request_url = "<%=request.getContextPath() %>";
		var name148 = "<spring:message code='name148'/>";
		var name535 = "<spring:message code='name535'/>";
		var name537 = "<spring:message code='name537'/>";
    	var name615 = "<spring:message code='name615'/>";
    	var name616 = "<spring:message code='name616'/>";
		var name617 = "<spring:message code='name617'/>";
		var name618 = "<spring:message code='name618'/>";
		var name619 = "<spring:message code='name619'/>";
		var name620 = "<spring:message code='name620'/>";
		var name621 = "<spring:message code='name621'/>";
		var name622 = "<spring:message code='name622'/>";
		var name623 = "<spring:message code='name623'/>";
		var name624 = "<spring:message code='name624'/>";
		var name625 = "<spring:message code='name625'/>";
		var name626 = "<spring:message code='name626'/>";
		var name627 = "<spring:message code='name627'/>";
		var name628 = "<spring:message code='name628'/>";
    </script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.validate.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.validate.messages_zh.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/base.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/menu.js"></script> 
    <script src="<%=request.getContextPath() %>/resources/js/user/userPassword.js"></script>
</body>
</html>