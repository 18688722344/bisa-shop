<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    ResourceBundle resource = ResourceBundle.getBundle("resources");
%>
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
    <title><spring:message code="name640"/></title>
    <!-- base -->
    <link href="<%=request.getContextPath()%>/resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/ctrl/bootstrap-3.3.7-dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/comm/bootstrapValidator.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/comm/base.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/reg/HK_Reg.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        var http_request = "<%=request.getContextPath()%>";
        var type = "<%=request.getAttribute("type")%>";
        var message = "<%=request.getAttribute("message")%>";
    </script>
</head>

<body class="h-auto">
<div class="main">
    <div class="login-div">
        <div class="clear min-h-380 bg-white w-450 z-9 pos-r bor bor-col-white radius-5 pt-40 pb-20">
            <div class="login-title">
                <div class="cur-p w-223 pull-left text-center pb-10 login-tabcontrolv2">
                    <span class="ml-10 f-20"><spring:message code="name641"/></span>
                </div>
                <div class="cur-p w-223 pull-left text-center pb-10 login-tabcontrolv1">
                    <span class="ml-10 f-20"><spring:message code="name642"/></span>
                </div>
            </div>
            <div class="clear pl-35 msg-wrap mt-40">
                <div class="msg-error ">
                    <b></b><span></span>
                </div>
            </div>

            <!-- 账户登录 -->
            <div class="pl-35 pr-30 min-h-250 login-tabcontentv2">
                <form class="login-main-formpwd form-horizontal clear" method="post">
                    <div class="clear h-50 form-group ml-0i mr-0i pt-10">
                        <div class="clear input-group mb-20">
                            <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                                <span class="glyphicon glyphicon-user f-14 line-h-50"></span>
                            </div>
                            <div class="clear h-50 line-h-50 text-center w-335 pull-left mr-0 ml-0 radius-0 pos-r ">
                                <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14 usernemeinput"
                                       type="text" name="username" maxlength="36"
                                       placeholder="<spring:message code="name643"/>">
                                <span class="usernamemsg h-25 line-h-25 clear dis-b text-left pull-left"></span>
                            </div>
                        </div>
                    </div>
                    <div class="h-50 form-group ml-0i mr-0i pt-10 mt-30">
                        <div class="clear input-group mb-20">
                            <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                                <span class="glyphicon glyphicon-asterisk f-14 line-h-50"></span>
                            </div>
                            <div class="clear h-50 line-h-50 text-center w-335 pull-left mr-0 ml-0 radius-0 pos-r">
                                <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14 userpwdinput pull-left"
                                       type="password" name="password" maxlength="12"
                                       placeholder="<spring:message code="name644"/>">
                                <span class="userpwdmsg h-25 line-h-25 clear dis-b text-left pull-left"></span>
                            </div>
                        </div>
                    </div>

                    <div class="clear mt-10 h-30 line-h-30">
                        <!-- 自动登录 值为0时候未选中，为一的时候为选中 -->
                        <%--<div class="ml-0 mr-10 clear pull-left pos-r i-check">
                            <input name="rememberMe" id="rememberMe" type="checkbox" value="0">
                            <label></label>
                        </div>
                        <label class="col-999 pull-left cur-p f-14 line-h-30"><spring:message code="name645"/></label>--%>
                        <a class="ml-0 mr-10 clear pull-right pos-r t--2 col-999 f-14 t-nonehove hovecol-5cb85c-bold"
                           href="<%=request.getContextPath() %>/web/call/findPassword">
                            <spring:message code="name646"/>
                        </a>
                    </div>
                    <%--登入按钮--%>
                    <div class="clear mt-5 w-374">
                        <button type="submit" class="btn full-w text-center btn btn-success h-50 f-18" id="pwd-btn">
                            <spring:message code="name647"/></button>
                    </div>
                </form>
            </div>

            <!--  免密登录 -->
            <div class="pl-35 pr-30 min-h-250 login-tabcontentv1 dis-n">
                <form class="login-main-formphone form-horizontal" method="post">
                    <div class="clear h-50 form-group ml-0i mr-0i phonecode-box">
                        <div class="input-group mt-10 mb-20">
                            <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                                <span class="glyphicon glyphicon-phone f-14 line-h-50"></span>
                            </div>
                            <div class="clear h-50 line-h-50 text-center w-100 pull-left input-group-addon pt-7 pb-7 pl-5 pr-4 bg-white bor-c radius-3">
                                <select class="form-control bor-col-white boxsha-none focu-bor-66afe9 pr-12i radius-3i sel-telphone selectpicker"
                                        onchange="selectArea()" id="selectpicker" name="selectpicker">
                                </select>
                            </div>
                            <div class="clear h-50 line-h-50 text-center w-235 pull-left mr-0 ml-0 radius-0 pos-r">
                                <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i bor-l-none f-14 userphoneinput"
                                       type="text" name="username" maxlength="15"
                                       placeholder="<spring:message code="name648"/>">
                                <span class="userphonemsg h-25 line-h-25 clear dis-b text-left pull-left"></span>
                            </div>
                        </div>
                    </div>
                    <div class="clear h-50 form-group ml-0i mr-0i mb-50 mt-30">
                        <div class="clear input-group mt-10 mb-20">
                            <div class="clear h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                                <span class="glyphicon glyphicon-link f-14 line-h-50"></span>
                            </div>
                            <div class="clear h-50 line-h-50 text-center w-335 pull-left mr-0 ml-0 radius-0 pos-r">
                                <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="text"
                                       name="icode" maxlength="4" placeholder="<spring:message code="name649"/>">
                                <button type="button"
                                        class="pos-a t-10 r-20 z-99 pl-15 pr-15 pt-5 pb-5 h-30 line-h-20 btn btn-success text-center min-w-120 sendMessagebtn">
                                    <spring:message code="name650"/>
                                </button>
                                <span class="usercodemsg h-25 line-h-25 clear dis-b text-left"></span>
                            </div>
                        </div>
                    </div>
                    <%--登入按钮--%>
                    <div class="w-374">
                        <button type="submit" class="full-w text-center btn btn-success h-50 f-18" id="nopwd-btn">
                            <spring:message code="name647"/></button>
                    </div>
                </form>
            </div>

            <%--微信登录--%>
            <div class="clear min-h-50 pl-40 pr-40">
                <div class="cur-p text-center login-tabcontrolv3">
                    <a class="t-nonehove" target="_blank" href="<%=resource.getString("wechat.login.url")%>">
                        <img class="img-42" src="<%=request.getContextPath() %>/resources/img/reg/tabcontrolv3.png"
                             alt="">
                        <span class="col-999 ml-10 f-16"><spring:message code="name651"/></span></a>
                </div>
            </div>
            <div class="clear h-20 line-h-20 f-14 col-666 pl-20 pr-40 text-right mt-5 mb-5">
                <a class="hovecol-5cb85c-bold t-nonehove mr-5 dis-ib"
                   href="<%=request.getContextPath() %>/web/call/reg"><spring:message code="name652"/></a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    /* js的国际化变量 ，如果有更好的办法替代这种方式，是最好不过的了 */
    var name653 = "<spring:message code='name653'/>";
    var name654 = "<spring:message code='name654'/>";
    var name655 = "<spring:message code='name655'/>";
    var name656 = "<spring:message code='name656'/>";
    var name657 = "<spring:message code='name657'/>";
    var name658 = "<spring:message code='name658'/>";
    var name659 = "<spring:message code='name659'/>";
    var name660 = "<spring:message code='name660'/>";
    var name661 = "<spring:message code='name661'/>";
    var name662 = "<spring:message code='name662'/>";
    var name663 = "<spring:message code='name663'/>";
    var name664 = "<spring:message code='name664'/>";
    var name665 = "<spring:message code='name665'/>";
    var name666 = "<spring:message code='name666'/>";
    var name667 = "<spring:message code='name667'/>";
    var name668 = "<spring:message code='name668'/>";
    var name669 = "<spring:message code='name669'/>";
    var name670 = "<spring:message code='name670'/>";
    var name671 = "<spring:message code='name671'/>";
    var name672 = "<spring:message code='name672'/>";
    var name673 = "<spring:message code='name673'/>";
    var name674 = "<spring:message code='name674'/>";
    var name675 = "<spring:message code='name675'/>";
    var name676 = "<spring:message code='name676'/>";
</script>
<script src="<%=request.getContextPath() %>/resources/js/comm/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/ctrl/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/comm/bootstrapValidator.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/reg/arealist.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/reg/login.js"></script>
</body>
</html>