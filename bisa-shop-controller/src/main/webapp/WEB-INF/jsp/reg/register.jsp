<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% ResourceBundle resource = ResourceBundle.getBundle("resources"); %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="Images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><spring:message code="name680"/></title>
    <!-- base -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/ctrl/layui/css/layui.css">
    <link href="<%=request.getContextPath() %>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/bootstrapValidator.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/resources/css/reg/HK_Reg.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        var http_request = "<%=request.getContextPath()%>";
        var message = "<%=request.getAttribute("message")%>";
    </script>
</head>

<body class="h-auto">
<img class="full-wh z-0 pos-a t-0 l-0" src="<%=request.getContextPath() %>/resources/img/reg/reg-bg.jpg" alt="">
<div class="clear min-h-560 mg-0-auto bg-white w-520 z-9 pos-r mt-100 bor bor-col-white radius-5 pt-40 pb-20">
    <div class="register-title">
        <div class="cur-p w-296 pull-left text-center pb-10">
            <span class="ml-10 f-20 title-selected"><spring:message code="name681"/></span>
        </div>
    </div>
    <div class="clear pl-41 msg-wrap mt-40">
        <div class="msg-error ">
            <b></b><span></span>
        </div>
    </div>
    <div class="clear pl-40 pr-40 min-h-350 register-tabcontentv1">
        <form class="register-main-formphone form-horizontal" action="<%=request.getContextPath() %>/web/call/reg"
              method="post">
            <div class="clear h-50 form-group ml-0i mr-0i mt-10 phonecode-box">
                <div class="clear input-group">
                    <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-phone f-14 line-h-50"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-100 pull-left pt-7 pb-7 pl-5 pr-5 input-group-addon bg-white bor bor-c">
                        <select class="form-control bor-col-white boxsha-none focu-bor-66afe9 pr-12i radius-3i sel-telphone"
                                id="selectpicker" name="selectpicker">
                        </select>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-296 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i bor-l-none f-14 userphoneinput"
                               type="text" name="phone" maxlength="15" placeholder="<spring:message code="name667"/>">
                        <!-- 请输入正确的手机号码！ -->
                        <span class="userphonemsg h-25 line-h-25 clear dis-b text-left pull-left"></span>
                        <button type="button"
                                class="pos-a t-10 r-20 z-99 pl-15 pr-15 pt-5 pb-5 h-30 line-h-20 btn btn-success text-center min-w-120 sendMessagebtn">
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
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" id="varify_code"
                               type="text" name="icode" maxlength="4" placeholder="<spring:message code="name649"/>">
                        <!-- 请输入您接收的动态密码. -->
                        <span class="usercodemsg h-25 line-h-25 clear dis-b text-left"></span>
                    </div>
                </div>
            </div>
            <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                <div class="clear input-group">
                    <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-asterisk f-14 line-h-50"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="password"
                               name="password" maxlength="16" placeholder="<spring:message code="name682"/>">
                        <!-- 请设置您的密码. -->
                        <span class="user-pwd-msg h-25 line-h-25 clear dis-b text-left"></span>
                    </div>
                </div>
            </div>
            <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                <div class="clear input-group">
                    <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-asterisk f-14 line-h-50"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="password"
                               name="setpassword" maxlength="16" placeholder="<spring:message code="name683"/>">
                        <!-- 请确认您的密码. -->
                        <span class="user-setpwd-msg h-25 line-h-25 clear dis-b text-left"></span>
                    </div>
                </div>
            </div>
            <div class="clear mt-30">
                <button type="submit" class="btn full-w text-center btn btn-success h-50 f-18 register-submit">
                    <spring:message code="name684"/></button><!-- 注册 -->
            </div>
        </form>
    </div>
    <div class="clear h-20 line-h-20 f-14 col-666 pl-40 pr-40 text-right mt-5 mb-5">
        <a class="col-999 t-nonehove hovecol-666 mr-15 dis-ib"
           href="<%=request.getContextPath() %>/login"><spring:message code="name686"/>>></a>    <!-- 已有账号，立即登录>> -->
    </div>
</div>

<script type="text/javascript">
    /* js的国际化变量 ，如果有更好的办法替代这种方式，是最好不过的了 */
    var name537 = "<spring:message code='name537'/>";
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
    var name692 = "<spring:message code='name692'/>";
    var name664 = "<spring:message code='name664'/>";
</script>
<script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/comm/bootstrapValidator.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ctrl/layer/layer.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/reg/arealist.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/reg/register.js"></script>
</body>