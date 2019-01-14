<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
    String jspUrl = request.getServletPath();
%> 
  
<header class="clear full-w min-h-120 header dip-n ">
    <div class="container">
        <div class="col-xs-2 pt-35 pb-35 pl-0 pr-0">
           <a href="<%=request.getContextPath()%>/index"><img src="<%=request.getContextPath()%>/resources/img/index/logov1.png" alt=""></a>
        </div>
        <div class="clear col-xs-10 pl-0 pr-0">
            <div class="col-xs-12 pt-20 pl-0 pr-0 mgl-1">
                <ul class="clear h-30 line-h-30 pull-right">
                	<!-- 游客模式 -->
                    <shiro:guest>
                    	<li class="pull-left pr-10"><a class="col-333 hovecol-black" href="${pageContext.request.contextPath}/web/call/reg"><spring:message code="register"/></a></li>
					 	<li class="pull-left pr-10"><a class="col-333 hovecol-black" href="${pageContext.request.contextPath}/login"><spring:message code="login"/></a></li>
					</shiro:guest>
                    <li class="pull-left pr-10">

					<!-- 用户已经身份验证/记住我登录后显示相应的信息 -->
					<shiro:user> 
	                    <div class="clear h-30 line-h-30 pull-left cur-p pos-r bg-white z-9 app_down_btn">
	                         <span class="clear h-30 line-h-30 dis-ib pl-5 pr-5 bg-white bor bor-col-fff pos-r z-9  text-center" style="min-width: 80px;">
	                         	<shiro:principal/>
	                         </span>
	                         <div class="clear pos-a l-0 bg-white full-w z--1 bor bor-col-ccc  dis-n">
	                             <p class="h-30 line-h-30 text-center f-14">
	                                 <a class="col-333 hovecol-black" href="<%=request.getContextPath()%>/user/orderCenter"><spring:message code="orderCenter"/></a>
	                             </p>
	                             <p class="h-30 line-h-30 text-center f-14">
	                                 <a class="col-333 hovecol-black" href="<%=request.getContextPath()%>/user/userCenter"><spring:message code="userCenter"/></a>
	                             </p>
	                         </div>
	                     </div>
	                 	</li> 
	                 	<li class="pull-left pr-10"><a class="col-333 hovecol-black" href="${pageContext.request.contextPath}/logout"><spring:message code="logout"/></a></li> 
                    </shiro:user>
                    
                    
                    <!-- app下载二维码 -->
                    <li class="pull-left pr-5">
                        <div class="clear h-30 line-h-30 pull-left cur-p pos-r bg-white z-9 app_down_btn">
                            <span class="clear h-30 line-h-30 dis-ib pl-5 pr-5 bg-white bor bor-col-fff pos-r z-9">
                                <spring:message code="appdownload"/>
                            </span>
                            <div class="clear pos-a r-0 bg-white h-150 z--1 bor bor-col-ccc t-29 dis-n" style="width:160px;height: auto;">
                                <div class="clear full-w pt-10 pl-10 pr-10 text-center">
                                    <img class="img-80" src="<%=request.getContextPath()%>/resources/img/index/app_qr.png" alt="">
                                </div>
                                <p class="h-30 line-h-30 text-center f-14">
                                   	 <spring:message code="scandownload"/>
                                </p>
                                <p class="line-h-20 f-12 text-center">
                                 	 <spring:message code="WXscan"/>
                                </p>
                                
                            </div>
                        </div>
                    </li>
                    <li class="pull-left pr-5 col-333 cur-d hovecol-black">
                    <i class="icon-shopping-cart pr-5 f-14 col-333 hovecol-black"></i>
                    <a href="<%=request.getContextPath()%>/web/call/toCart"><spring:message code="shopCar"/><span class="layui-badge layui-bg-blue ml-5">0</span></a>  
                    </li>
                    <li class="pull-left pr-5 col-333 cur-d hovecol-black pl-20">
                    	<spring:message code="localLang"/>:
					    <a id="lang-hk" href="${pageContext.request.contextPath}/index?lang=zh_HK"><spring:message code="language.hk"/></a> |
					    <a id="lang-cn" href="${pageContext.request.contextPath}/index?lang=zh_CN"><spring:message code="language.cn"/></a> |
					    <a id="lang-us" href="${pageContext.request.contextPath}/index?lang=en_US"><spring:message code="language.us"/></a>
                    </li>
                   <!--  <li class="pull-left pr-5 pl-5 bg-eee hovebg-ddd mainsearch">
                        <input type="text" class="h-26 line-h-26 w-100 mt--2 mb-2 bor bor-col-ccc dis-n mainsearchinput">
                        <span class="col-333 t-nonehove cur-p" href=""><i class="icon-search pr-5 f-14"></i>搜索</span>
                    </li> -->
                </ul>
            </div>

            <!--产品与服务-->
            <div class="col-xs-12 pt-15 pl-0 pr-0 mgl-2">
                <ul class="col-xs-10 clo-md-9 pull-left mainnav clear dis-ib pl-0 pr-0" style="width: 100%;">
                     <li class="col-xs-2 text-center f-14 cur-p pd-10 f-w">
                        <a id="1000A" class="col-309DE2 t-nonehove <% if(jspUrl.contains("index")){ %>navborpitch<% } %> pb-7" href="<%=request.getContextPath()%>/index"><spring:message code="homepage"/></a>
                    </li>
                    <li class="col-xs-2 text-center f-14 cur-p pd-10 f-w ">
                        <a id="2000A" class="col-309DE2 t-nonehove <% if(jspUrl.contains("menuList")){ %>navborpitch<% }%>  pb-7" href="<%=request.getContextPath()%>/menuList"><spring:message code="Services"/></a>
                        <!--鼠标经过context-->
                        <div class="mainnav_list mainnav_list_w-mc" style="display: none;">
                            <div class="mainnav_li" >
                                  <div class="mainnav_list_left">
                                    <span class="mainnav_list_title"><spring:message code="name810"/></span>
                                      <span>|</span>
                                    <ul>
                                        <li><spring:message code="name811"/></li>
                                        <li><spring:message code="name812"/></li>
                                        <li><spring:message code="name813"/></li>
                                        <li><spring:message code="name814"/></li>
                                    </ul>
                                  </div>
                                  <div class="mainnav_list_right">
                                      <span class="mainnav_list_title"><spring:message code="name856"/></span>
                                      <span>|</span>
                                      <ul>
                                          <li><spring:message code="name857"/></li>
                                          <li><spring:message code="name858"/></li>
                                      </ul>
                                  </div>

                                <div class="mainnav_list_hr"></div>
                            </div>

                            <div class="mainnav_li" >
                            <div class="mainnav_list_left">
                                <span class="mainnav_list_title"><spring:message code="name859"/><spring:message code="name871"/></span>
                                <span>|</span>
                                <ul>
                                    <li><spring:message code="name860"/></li>
                                    <li><spring:message code="name861"/></li>
                                </ul>
                            </div>

                            <div class="mainnav_list_hr"></div>
                        </div>

                            <div class="mainnav_li" >
                                <div class="mainnav_list_left">
                                    <span class="mainnav_list_title"><spring:message code="name862"/><spring:message code="name871"/></span>
                                    <span>|</span>
                                    <ul>
                                        <li><spring:message code="name863"/></li>
                                    </ul>
                                </div>

                                <div class="mainnav_list_hr"></div>
                            </div>

                            <div class="mainnav_li" >
                                <div class="mainnav_list_left">
                                    <span class="mainnav_list_title"><spring:message code="name864"/><spring:message
                                            code="name871"/></span>
                                    <span>|</span>
                                    <ul>
                                        <li><spring:message code="name865"/></li>
                                    </ul>
                                </div>

                                <div class="mainnav_list_hr"></div>
                            </div>

                            <div class="mainnav_li" >
                                <div class="mainnav_list_left">
                                    <span class="mainnav_list_title"><spring:message code="name866"/><spring:message code="name871"/></span>
                                    <span>|</span>
                                    <ul>
                                        <li><spring:message code="name867"/></li>
                                    </ul>
                                </div>
                            </div>


                        </div>

                    </li>


                    <li class="col-xs-2 text-center f-14 cur-p pd-10 f-w">
                        <a id="3000A" class="col-309DE2 t-nonehove pb-7 <% if(jspUrl.contains("solution")){ %>navborpitch<% } %> " href="<%=request.getContextPath()%>/solution"><spring:message code="solution"/></a>
                        <div class="mainnav_list mainnav_list_w-mc" style="display: none;">
                            <div class="mainnav_li"  >
                                <div class="mainnav_list_left">
                                    <span class="mainnav_list_title"><spring:message code="solution"/></span>
                                    <span>|</span>
                                    <ul>
                                        <li><spring:message code="name868"/><spring:message code="solution"/></li>
                                        <li><spring:message code="name869"/><spring:message code="solution"/></li>
                                        <li><spring:message code="name870"/><spring:message code="solution"/></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="col-xs-2 text-center f-14 cur-p pd-10 f-w">
                        <a id="4000A" class="col-309DE2 t-nonehove  <% if(jspUrl.contains("news")){ %>navborpitch<% } %> pb-7" href="<%=request.getContextPath()%>/web/call/healthInquiry"><spring:message code="information"/></a>
                    </li>
                    <%--购物指南--%>
                    <li class="col-xs-2 text-center f-14 cur-p pd-10 f-w">
                        <a id="5000A" class="col-309DE2 t-nonehove  <% if(jspUrl.contains("HowToShop")){ %>navborpitch<% }%> pb-7" href="<%=request.getContextPath()%>/shoppingGuide"><spring:message code="certificate"/></a>
                        <div class="mainnav_list mainnav_list_w-mc" style="display: none;">
                            <div class="mainnav_li"  >
                                <div class="mainnav_list_left">
                                    <span class="mainnav_list_title"><spring:message code="certificate"/></span>
                                    <span>|</span>
                                    <ul>
                                        <li><spring:message code="name872"/><spring:message code="certificate"/></li>
                                        <li><spring:message code="name859"/><spring:message code="certificate"/></li>
                                        <li><spring:message code="name862"/><spring:message code="certificate"/></li>
                                        <li><spring:message code="name864"/><spring:message code="certificate"/></li>
                                        <li><spring:message code="name866"/><spring:message code="certificate"/></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </li>

                    <li class="col-xs-2 text-center f-14 cur-p pd-10 f-w">
                        <a id="6000A" class="col-309DE2 t-nonehove  <% if(jspUrl.contains("ContactUs")){ %>navborpitch<% } %>  pb-7" href="<%=request.getContextPath()%>/contactUs"><spring:message code="relation"/></a>

                    </li>
                    <li class="dis-n">
                        <a id="6000A" class="dis-n <% if(jspUrl.contains("user")|| jspUrl.contains("shopping")||jspUrl.contains("order")||jspUrl.contains("solution")||jspUrl.contains("active")){ %> <% } %>" href="javascript:;"></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
