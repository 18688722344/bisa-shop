<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 第三方账号绑定手机 -->
<div class="clear min-h-335 mg-0-auto bg-white w-520 z-999 pos-r mt-100 bor bor-col-white radius-5 pt-20 pb-20 show-bind-phone-div dis-n"
     id="bind_phone_div">
    <div class="login-title">
        <div class="cur-p w-223 pull-left text-center ml-40 pb-20 bind-tabcontrolv1">
            <span class="ml-10 f-20"><spring:message code="name720"/></span><!-- 绑定已有账号 -->
        </div>
        <div class="cur-p w-223 pull-left text-center pb-20 bind-tabcontrolv2">
            <span class="ml-10 f-20"><spring:message code="name721"/></span><!-- 注册新账号 -->
        </div>
    </div>
    <div class="clear pl-41 msg-wrap mt-20">
        <div class="msg-error">
            <b></b><span><spring:message code="name722"/></span><!-- 本登录账号需要绑定手机号 -->
        </div>
    </div>

    <!-- 绑定已有账号 -->
    <div class="clear pl-40 pr-40 bind-tabcontentv1">
        <form class="form-horizontal" id="bind-exist-formphone" action="./user/bindPhone" method="post">
            <div class="clear h-50 form-group ml-0i mr-0i mt-40 bind-exist-box">
                <div class="clear input-group">
                    <div class="clear h-50 line-h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-phone f-14 pos-r"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-100 pull-left pt-7 pb-7 pl-5 pr-5 input-group-addon bg-white bor bor-c">
                        <select class="form-control bor-col-white boxsha-none focu-bor-66afe9 pr-12i radius-3i selectpicker bind-exist-phonecode"
                                name="selectpicker">

                        </select>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-296 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i bor-l-none f-14 bind-exist-phone"
                               type="text" name="userphone" maxlength="15"
                               placeholder="<spring:message code="name648"/>">
                        <span class="exist-phone-msg h-25 line-h-25 clear dis-b text-left pull-left"></span>
                        <button type="button"
                                class="pos-a t-10 r-20 z-99 pl-15 pr-15 pt-5 pb-5 h-30 line-h-20 btn btn-info text-center min-w-120 bind-exist-send-btn">
                            <spring:message code="name650"/> <!-- 获取动态密码 -->
                        </button>
                        <input type="hidden" name="type" class="bind_type" value="1">
                    </div>
                </div>
            </div>
            <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                <div class="clear input-group">
                    <div class="clear h-50 line-h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-link f-14 pos-r"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="text"
                               name="icode" maxlength="4" placeholder="<spring:message code="name649"/>">
                        <!-- 请输入您接收的动态密码. -->
                        <span class="exist-code-msg h-25 line-h-25 clear dis-b text-left"></span>
                    </div>
                </div>
            </div>

            <div class="clear mt-30">
                <button type="submit" class="btn full-w text-center btn btn-info h-50 f-18 bind-exist-submit">
                    <spring:message code="name723"/></button>
            </div>
        </form>
    </div>

    <!--  注册新账号 -->
    <div class="clear pl-40 pr-40 min-h-350 bind-tabcontentv2 dis-n">
        <form class="form-horizontal" id="bind-new-formphone" action="./user/bindPhone" method="post">
            <div class="clear h-50 form-group ml-0i mr-0i mt-40 bind-new-box">
                <div class="clear input-group">
                    <div class="clear h-50 line-h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-phone f-14 pos-r"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-100 pull-left pt-7 pb-7 pl-5 pr-5 input-group-addon bg-white bor bor-c">
                        <select class="form-control bor-col-white boxsha-none focu-bor-66afe9 pr-12i radius-3i selectpicker bind-new-phonecode"
                                name="selectpicker">

                        </select>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-296 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i bor-l-none f-14 bind-new-phone"
                               type="text" name="userphone" maxlength="15"
                               placeholder="<spring:message code="name648"/>">    <!-- 请输入您的手机号码 -->
                        <span class="new-phone-msg h-25 line-h-25 clear dis-b text-left pull-left"></span>
                        <button type="button"
                                class="pos-a t-10 r-20 z-99 pl-15 pr-15 pt-5 pb-5 h-30 line-h-20 btn btn-info text-center min-w-120 bind-new-send-btn">
                            <spring:message code="name650"/><!-- 获取动态密码 -->
                        </button>
                        <input type="hidden" name="type" class="bind_type" value="2">
                    </div>
                </div>
            </div>
            <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                <div class="clear input-group">
                    <div class="clear h-50 line-h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-link f-14 pos-r"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="text"
                               name="icode" maxlength="4" placeholder="<spring:message code="name649"/>">
                        <!-- 请输入您接收的动态密码. -->
                        <span class="new-code-msg h-25 line-h-25 clear dis-b text-left"></span>
                    </div>
                </div>
            </div>

            <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                <div class="clear input-group">
                    <div class="clear h-50 line-h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-asterisk f-14 pos-r"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="password"
                               name="userpasspwd" maxlength="16" placeholder="<spring:message code="name682"/>">
                        <!-- 请设置您的密码. -->
                        <span class="userpasspwdmsg h-25 line-h-25 clear dis-b text-left"></span>
                    </div>
                </div>
            </div>
            <div class="clear h-50 form-group ml-0i mr-0i mt-30">
                <div class="clear input-group">
                    <div class="clear h-50 line-h-50 text-center w-40 pull-left input-group-addon pd-0 bg-white bor-c radius-3">
                        <span class="glyphicon glyphicon-asterisk f-14 pos-r"></span>
                    </div>
                    <div class="clear h-50 line-h-50 text-center w-396 pull-left mr-0 ml-0 radius-0 pos-r">
                        <input class="form-control pt-7 pb-7 pl-5 pr-5 full-wh radius-tf-0i f-14" type="password"
                               name="password" maxlength="16" placeholder="<spring:message code="name683"/>">
                        <!-- 请确认您的密码. -->
                        <span class="usersetpwdmsg h-25 line-h-25 clear dis-b text-left"></span>
                    </div>
                </div>
            </div>

            <div class="clear mt-30">
                <button type="submit" class="btn full-w text-center btn btn-info h-50 f-18 bind-new-submit">
                    <spring:message code="name724"/></button><!-- 注册新账号并绑定 -->
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    /* js的国际化变量 ，如果有更好的办法替代这种方式，是最好不过的了 */
    var name628 = "<spring:message code='name628'/>";
    var name653 = "<spring:message code='name653'/>";
    var name654 = "<spring:message code='name654'/>";
    var name655 = "<spring:message code='name655'/>";
    var name656 = "<spring:message code='name656'/>";
    var name657 = "<spring:message code='name657'/>";
    var name658 = "<spring:message code='name658'/>";
    var name659 = "<spring:message code='name659'/>";
    var name660 = "<spring:message code='name660'/>";
    var name662 = "<spring:message code='name662'/>";
    var name663 = "<spring:message code='name663'/>";
    var name664 = "<spring:message code='name664'/>";
    var name666 = "<spring:message code='name666'/>";
    var name667 = "<spring:message code='name667'/>";
    var name669 = "<spring:message code='name669'/>";
    var name670 = "<spring:message code='name670'/>";
    var name671 = "<spring:message code='name671'/>";
    var name672 = "<spring:message code='name672'/>";
    var name690 = "<spring:message code='name690'/>";
    var name709 = "<spring:message code='name709'/>";
    var name721 = "<spring:message code='name721'/>";
    var name730 = "<spring:message code='name730'/>";
    var name732 = "<spring:message code='name732'/>";
    var name733 = "<spring:message code='name733'/>";
    var name734 = "<spring:message code='name734'/>";
</script>
