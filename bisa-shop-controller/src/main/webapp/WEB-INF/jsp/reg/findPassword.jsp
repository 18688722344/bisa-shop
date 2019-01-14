<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><spring:message code="name700"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/bootstrapValidator.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/reg/HK_Reg.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
     var http_request = "<%=request.getContextPath() %>";
    </script>
</head>

<body class="h-auto">
	<img class="full-wh z-0 pos-a t-0 l-0" src="<%=request.getContextPath() %>/resources/img/reg/reg-bg.jpg" alt="">
    <div class="clear min-h-560 mg-0-auto bg-white w-520 z-9 pos-r mt-100 bor bor-col-white radius-5 pt-40 pb-20">
    	<div class="pwd-title">
			<div class="cur-p w-223 pull-left text-center ml-30 pb-10 findpwd-tabcontrolv1">
				<span class="ml-10 f-20 title-selected"><spring:message code="name701"/></span><!-- 短信找回 -->
			</div>
			<div class="cur-p w-223 pull-left text-center pb-10 findpwd-tabcontrolv2">
				<span class="ml-10 f-20"><spring:message code="name702"/></span><!-- 邮件找回 -->
			</div>
		</div>
	  	<div class="pl-41 msg-wrap mt-35">
           <div class="msg-error">
              <b></b>
           </div>
        </div>
       <!--  短信找回 -->
        <div class="clear pl-40 pr-40 min-h-350 findpwd-tabcontentv1">
            <form class="findpwd-phone-form form-horizontal" onkeydown="if(event.keyCode==13)return false;">
                <div class="clear h-50 form-group ml-0i mr-0i mt-10 phonecode-box">
                    <div class="clear input-group">
                        <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                            <span class="glyphicon glyphicon-phone f-14 line-h-50"></span>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-100 pull-left pt-7 pb-7 pl-5 pr-5 input-group-addon bg-white bor bor-c">
                            <select class="form-control bor-col-white boxsha-none focu-bor-66afe9 pr-12i radius-3i sel-telphone" id="selectpicker" name="selectpicker">
                            </select>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-296 pull-left mr-0 ml-0 radius-0 pos-r">
                            <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i bor-l-none f-14 findpwdphoneinput" type="text" name="phone" maxlength="15" placeholder="<spring:message code="name648"/>">	<!-- 请输入您的手机号码 -->
                            <span class="phone-msg h-25 line-h-25 clear dis-b text-left pull-left"></span>
                            <button type="button" class="pos-a t-10 r-20 z-99 pl-15 pr-15 pt-5 pb-5 h-30 line-h-20 btn btn-success text-center min-w-120 sendMessagebtn">
                               	<spring:message code="name650"/><!--  获取动态密码 -->
                            </button>
                        </div>
                    </div>
                </div>
                <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                    <div class="clear input-group">
                        <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                            <span class="glyphicon glyphicon-link f-14 line-h-50"></span>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                            <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="text" name="icode" maxlength="4" placeholder="<spring:message code="name649"/>"><!-- 请输入您接收的动态密码. -->
                            <span class="phone-icode-msg h-25 line-h-25 clear dis-b text-left"></span>
                        </div>
                    </div>    
                </div>
                <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                    <div class="clear input-group">
                        <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                            <span class="glyphicon glyphicon-asterisk f-14 line-h-50"></span>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                            <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="password" name="password" maxlength="16" placeholder="<spring:message code="name707"/>"><!-- 请设置您的新密码. -->
                            <span class="phone-pwd-msg h-25 line-h-25 clear dis-b text-left"></span>
                        </div>
                    </div>
                </div>
                <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                    <div class="clear input-group">
                        <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                            <span class="glyphicon glyphicon-asterisk f-14 line-h-50"></span>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                            <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="password" name="setpassword" maxlength="16" placeholder="<spring:message code="name708"/>"><!-- 请确认您的新密码. -->
                            <span class="phone-setpwd-msg h-25 line-h-25 clear dis-b text-left"></span>
                        </div>
                    </div>
                </div>
                <div class="clear mt-30">
                    <button type="button" class="btn full-w text-center btn btn-success h-50 f-18 find-pwd-sms-submit"><!-- 确认找回 --><spring:message code="name705"/></button>
                </div>
            </form>
        </div>
        <div class="clear pl-40 pr-40 min-h-350 findpwd-tabcontentv2 dis-n">
            <form class="findpwd-email-form form-horizontal" onkeydown="if(event.keyCode==13)return false;">
                <div class="clear h-50 form-group ml-0i mr-0i mt-10 emailcode-box">
                    <div class="clear input-group">
                        <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                            <span class="glyphicon glyphicon-envelope f-14 line-h-50"></span>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                            <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14 email-input" type="text" name="email" maxlength="36" placeholder="<spring:message code="name703"/>"><!-- 请输入您的邮箱地址 -->
                            <span class="email-msg h-25 line-h-25 clear dis-b text-left pull-left"></span>
                            <button type="button" class="pos-a t-10 r-20 z-99 pl-15 pr-15 pt-5 pb-5 h-30 line-h-20 btn btn-success text-center min-w-120 send-email-btn">
                                <spring:message code="name650"/><!-- 获取动态密码 -->
                            </button>
                        </div>
                    </div>
                </div>
                <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                    <div class="clear input-group">
                        <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                            <span class="glyphicon glyphicon-link f-14 line-h-50"></span>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                            <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="text" name="icode" maxlength="4" placeholder="<spring:message code="name704"/>"><!-- 请输入邮件收到的动态密码. -->
                            <span class="email-icode-msg h-25 line-h-25 clear dis-b text-left"></span>
                        </div>
                    </div>    
                </div>
                <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                    <div class="clear input-group">
                        <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                            <span class="glyphicon glyphicon-asterisk f-14 line-h-50"></span>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                            <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="password" name="password" maxlength="12" placeholder="<spring:message code="name707"/>"><!-- 请设置您的新密码. -->
                            <span class="email-pwd-msg h-25 line-h-25 clear dis-b text-left"></span>
                        </div>
                    </div>
                </div>
                <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                    <div class="clear input-group">
                        <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                            <span class="glyphicon glyphicon-asterisk f-14 line-h-50"></span>
                        </div>
                        <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                            <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="password" name="setpassword" maxlength="12" placeholder="<spring:message code="name708"/>"><!-- 请确认您的新密码. -->
                            <span class="email-setpwd-msg h-25 line-h-25 clear dis-b text-left"></span>
                        </div>
                    </div>
                </div>
                <div class="clear mt-30">
                    <button type="button" class="btn full-w text-center btn btn-success h-50 f-18 find-pwd-email-submit"><spring:message code="name705"/></button><!-- 确认找回 -->
                </div>
            </form>
        </div>
        <div class="clear h-20 line-h-20 f-14 col-666 pl-40 pr-40 text-right mt-5 mb-5">
            <a class="col-999 t-nonehove hovecol-666 mr-15 dis-ib" href="<%=request.getContextPath() %>/web/call/reg"><spring:message code="name706"/>>></a><!-- 现在注册 -->
        </div>
        <div class="clear h-20 line-h-20 f-14 col-666 pl-40 pr-40 text-right mt-5 mb-5">
            <a class="col-999 t-nonehove hovecol-666 mr-15 dis-ib" href="<%=request.getContextPath() %>/login"><spring:message code="name686"/>>></a><!-- 已有账号，立即登录>> -->
        </div>
    </div>
    <script type="text/javascript">
		/* js的国际化变量 ，如果有更好的办法替代这种方式，是最好不过的了 */
		var name537 = "<spring:message code='name537'/>";
		var name628 = "<spring:message code='name628'/>";
		var name653 = "<spring:message code='name653'/>";
		var name654 = "<spring:message code='name654'/>";
		var name655 = "<spring:message code='name655'/>";
		var name656 = "<spring:message code='name656'/>";
		var name657 = "<spring:message code='name657'/>";
		var name658 = "<spring:message code='name658'/>";
		var name662 = "<spring:message code='name662'/>";
		var name663 = "<spring:message code='name663'/>";
		var name664 = "<spring:message code='name664'/>";
		var name666 = "<spring:message code='name666'/>";
		var name667 = "<spring:message code='name667'/>";
		var name671 = "<spring:message code='name671'/>";
		var name672 = "<spring:message code='name672'/>";
		var name690 = "<spring:message code='name690'/>";
		var name691 = "<spring:message code='name691'/>";
		var name709 = "<spring:message code='name709'/>";
		var name710 = "<spring:message code='name710'/>";
		var name711 = "<spring:message code='name711'/>";
		var name712 = "<spring:message code='name712'/>";
		var name713 = "<spring:message code='name713'/>";
		var name714 = "<spring:message code='name714'/>";
		var name715 = "<spring:message code='name715'/>";
	</script>
	
	
    <script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/comm/bootstrapValidator.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/reg/arealist.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/reg/findpassword.js"></script>
</body>

</html>