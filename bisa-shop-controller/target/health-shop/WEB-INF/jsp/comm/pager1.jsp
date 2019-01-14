<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav aria-label="Page navigation">
	<ul class="pagination">
		<c:if test="${requestScope.pageBean.pageNum != 1}">
			<li><a
				href="${pageContext.request.contextPath}/${pageBean.url}?pageNum=${requestScope.pageBean.pageNum-1}">
					<span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:forEach begin="${requestScope.pageBean.start}"
			end="${requestScope.pageBean.end}" step="1" var="i">
			<c:if test="${requestScope.pageBean.pageNum == i}">
				<li><a style="background-color: #76b5ea;">${i}</a></li>
			</c:if>
			<c:if test="${requestScope.pageBean.pageNum != i}">
				<li><a
					href="${pageContext.request.contextPath}/${pageBean.url}?pageNum=${i}">${i}</a></li>
			</c:if>
		</c:forEach>

		<c:if
			test="${requestScope.pageBean.pageNum != requestScope.pageBean.totalPage && requestScope.pageBean.list.size()!=0}">
			<li><a
				href="${pageContext.request.contextPath}/${pageBean.url}?pageNum=${requestScope.pageBean.pageNum+1}">
					<span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>

	</ul>
</nav>

