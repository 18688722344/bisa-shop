<%@ page language="java" pageEncoding="UTF-8"%>

<div class="col-sm-3 pl-30 pr-20">
	<div class="clear bg-white pd-40-10-ipad">
		<p class="mt-10 mb-10 col-212121 f-30 line-h-40 cur-d"><spring:message code="name28"/></p>
		<p
			class="col-757575 f-20-16-ipad line-h-25 mt-40 mb-20 cur-p hovecol-309DE2 user-munu user-order"
			onclick="window.location.href='<%=request.getContextPath()%>/user/orderCenter';">
			<spring:message code="name29"/></p>
            <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 user-munu user-appraise"
             onclick="window.location.href='<%=request.getContextPath()%>/user/userAppraiseList';">
			<spring:message code="name_35"/></p>
		<p class="mt-30 mb-30 col-212121 f-30 line-h-40 cur-d"><spring:message code="name30"/></p>
		<p
			class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 user-munu user-center"
			onclick="window.location.href='<%=request.getContextPath()%>/user/userCenter';">
			<spring:message code="name31"/></p>
		<p
			class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 user-munu user-active"
			onclick="window.location.href='<%=request.getContextPath()%>/user/serviceCardPage';">
			<spring:message code="name32"/></p>
		<p
			class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 user-munu user-address"
			onclick="window.location.href='<%=request.getContextPath()%>/user/userAddress';">
			<spring:message code="name33"/></p>
		<p
			class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 user-munu user-password"
			onclick="window.location.href='<%=request.getContextPath()%>/user/accountSecurity';">
			<spring:message code="name34"/></p>
		<%--  <p class="col-757575 f-20-16-ipad line-h-25 mt-20 mb-20 cur-p hovecol-309DE2 user-munu user-saleApply">
					售后服务
                </p> --%>
	</div>
</div>
