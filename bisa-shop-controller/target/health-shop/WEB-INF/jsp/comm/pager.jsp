<%@ page import="com.bisa.health.basic.entity.SystemContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pg_url" value='<%=request.getParameter("url")%>'></c:set>
<c:set var="pg_items" value='<%=request.getParameter("items")%>'></c:set>
<!-- 用于存放分页查询的附加参数 -->
<c:set var="pg_flag" value='<%=request.getParameter("flag")%>'></c:set>
   <pg:pager export="currentPageNumber=pageNumber" items="${pg_items }"
	maxPageItems="<%=SystemContext.getPageSize()%>" url="${pg_url}" >
	
  	<pg:index export="items,pages">
  		<%-- 
        <c:if test="${!empty pg_items}">
            <c:forEach items="${pg_items}" var="item">
            <pg:param name="${item.key}" value="${item.value}"/>
            <input name="${item.key}" value="${item.value}" type="hidden"/>
            </c:forEach>
        </c:if>
         --%>
        <c:if test="${!empty pg_flag}">
        	<pg:param name="flag" value="${pg_flag}"/>
        </c:if>

       	<nav aria-label="Page navigation" class="col-sm-12 Page">
		<ul class="pagination">
		
       <pg:prev>
			<li><a href="${pageUrl}" aria-label="Previous" onclick="return setOffset(this,${pageNumber })?pageSubmit(this):true;" class="pager_link"> 
			<span aria-hidden="false">&laquo;</span>
			</a>
			</li>
		</pg:prev>
		
        <pg:pages>
        
            <c:choose>
            <c:when test="${currentPageNumber eq pageNumber }">
                <li class="active"><a href="${pageUrl}" class="pager_link">${pageNumber }</a></li>
            </c:when>
            
            <c:otherwise>
                <li><a href="${pageUrl}"  class="pager_link">${pageNumber}</a></li>
            </c:otherwise>
            </c:choose>
            
        </pg:pages>
        
        <pg:next>
			<li><a href="${pageUrl}" aria-label="Next" onclick="return setOffset(this,${pageNumber })?pageSubmit(this):true;"> <span
					aria-hidden="false">&raquo;</span>
			</a></li>
		</pg:next>
		</ul>
		</nav>
    </pg:index> 
</pg:pager>
