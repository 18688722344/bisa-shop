<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bisa.health.shop.enumerate.GoodsStatusEnum"%>
<%@ page import="com.bisa.health.shop.enumerate.NeedPostType"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	ResourceBundle resource = ResourceBundle.getBundle("resources");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
	<link rel="icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="Images/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title>碧沙康健_商品管理</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <script>
		var noPost = "<%=NeedPostType.no_post.getValue()%>";  
		var need_Post = "<%=NeedPostType.need_post.getValue()%>";
		
		var in_sale = "<%=GoodsStatusEnum.in_sale.getValue()%>";
		var invalid = "<%=GoodsStatusEnum.invalid.getValue()%>";
		var sold_out = "<%=GoodsStatusEnum.sale_out.getValue()%>";
	</script>
    <!-- 操作 工具栏 -->
        <script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn-warm layui-btn-sm" lay-event="detail" data-num="2">查看</a>
			<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="" href="admin/commodity/updateCommodityPage?goodsNumber={{ d.goodsNumber }}&">编辑</a>
			<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="internationalization" href="admin/commodity/goodsInternationalzation?goodsNumber={{ d.goodsNumber }}">添加语言版本</a>
			<a class="layui-btn layui-btn-warm layui-btn-sm" lay-event="delete">删除</a>
			{{# if(d.parentId == 0){ }}
				<a class="layui-btn layui-btn layui-btn-sm" lay-event="" href="<%=resource.getString("access.allow.url") %>/web/call/goodsDetail?goodsNumber={{ d.goodsNumber }}">预览</a>
				<a class="layui-btn layui-btn-primary layui-btn-sm" lay-event="" href="admin/commodity/goodsComboView?goodsNumber={{ d.goodsNumber }}">套餐</a>
            {{# } }}
        </script>
    	<script type="text/html" id="commodityTypeTpl">
			{{# if(d.goodsStatus == in_sale){ }}
				<span style="color: blue">销售 中</span>
            {{# } }}
			{{# if(d.goodsStatus == invalid){ }}
				<span style="color: red">下架 了</span>
            {{#  } }}
			{{# if(d.goodsStatus == sold_out){ }}
				<span style="color: #9900cc">售完 了</span>
            {{#  } }}
		</script>
		<script type="text/html" id="postTpl">
            {{# if(d.needPost == noPost){ }}
                	否
            {{# }else{ }}
                	是
            {{#  } }}
    	</script>
		<script type="text/html" id="storeNumberTpl">
            {{# if(d.storeNumber < 100){ }}
                	<span style="color: red">{{ d.storeNumber }}</span>
            {{# }else{ }}
                	{{ d.storeNumber }}
            {{#  } }}
    	</script>
    	<script type="text/html" id="imgTpl">
            {{# if(d.imgUrl != null){ }}
                	<div><img src='{{ d.imgUrl}}'></div>
            {{#  } }}
    	</script>
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
	.layui-table-cell{
		height: 100%;
    	max-width: 100%;
	}
	.laytable-cell-1-11{
		height: 100%;
    	max-width: 100%;
	}
	</style>
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
   		<!-- 头部导航部分 -->
        <%@ include file="../admin_common/header.jsp" %>
        <!-- 左侧导航区域 -->
        <%@ include file="commodityNavigation.jsp" %>
        
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px;">
                <p class="f-18 pt-15 pb-15 col-8d969d">
                    	查看商品
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <!-- 这里用layui的数据表格的重载 -->
                    <form class="layui-form" action="">
                        <div class="layui-form-item" pane="">
                            <label class="layui-form-label f-14">分类：</label>
                            <div class="layui-input-block">
                                <button type="button" value="0" class="layui-btn layui-btn-sm btn-sidebar ">全部</button>
                                <button type="button" value="1" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">销售中</button>
                                <button type="button" value="2" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">下架中 </button>
                                <button type="button" value="3" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">没货了</button>
                                <button type="button" value="4" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">不用邮递</button>
                                <button type="button" value="5" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">需要邮递</button>
                            </div>
                        </div>
                        <div class="layui-form-item mb-0" pane="">
                            <label class="layui-form-label f-14">搜索：</label>
                            <div class="layui-input-block">
                                <div class="layui-inline">
                                    <select name="searchabout" lay-verify="required" id="searchabout" lay-search="" lay-filter="sle_type_f">
                                        <option value="1" selected="selected">商品编号</option>
                                        <option value="2">按年查询</option>
                                        <option value="3">按年-月查询</option>
                                        <option value="4">按年-月-日查询</option>
                                    </select>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <!-- 商品编号 -->
                                        <input id="ipt_transnum" type="text" name="incontent" lay-verify="required" autocomplete="off" class="layui-input sel_value" placeholder="请输入您要查询的商品编号">
                                        <!-- 年份 -->
                                        <input id="ipt_year" type="text" name="n_year" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的年份(上架时间)" readonly="readonly">
                                        <!-- 月份 -->
                                        <input id="ipt_month" type="text" name="n_month" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的月份(上架时间)" readonly="readonly">
                                        <!-- 日期 -->
                                        <input id="ipt_day" type="text" name="n_day" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的日期(上架时间)" readonly="readonly">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <button type="submit" class="layui-btn layui-btn-sm" lay-submit="" lay-filter="search1">搜索</button>
                                    <button type="button" class="layui-btn layui-btn-sm btn-refresh">刷新</button>
                                    <button type="button" class="layui-btn layui-btn-sm" onClick="window.location.href='admin/commodity/addCommodityPage';">添加商品</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                
                <p class="f-20 pb-15 mt-20 col-8d969d">
                    	商品列表
                </p>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
				  <legend>服务激活卡数量不足时，需要批量生成，请前往"新增服务卡"。</legend>
				</fieldset>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <table id="commodityList" lay-filter="test"></table>
                </div>
            </div>
        </div>
        
        <!-- (查看goods基本信息)============================== 的弹出层的基本内容，供js调用======================= -->
	    <div class="detailcontent dis-n">
	        <div class="clear pd-20 f-16">
	            <div class="clear mb-20 h-40 line-h-40 text-center">
	                <div class="clear dis-ib">
	                    <img src="resources/img/admin/admin_comm/logov1.png" class="h-35 mt-3 pull-left">
	                    <span class="f-28 ml-10 f-w family-s pull-left h-40 line-h-40">商品基本信息</span>
	                </div>
	            </div>
	            <fieldset class="layui-elem-field">
	                <legend>商品基本信息</legend>
					<%--<div class="layui-input-block">
						<div class="lang_btn">
							&lt;%&ndash;国际化语言类型&ndash;%&gt;
							<input type="hidden" id="lang" value="">
							<button class="layui-btn layui-btn-radius layui-btn-primary" data-num="1" lay-submit="" lay-filter="china" id="china">简体</button>
							<button class="layui-btn layui-btn-radius" data-num="2" lay-submit="" lay-filter="hk" id="hk">繁体</button>
							<button class="layui-btn layui-btn-radius layui-btn-primary" data-num="3" lay-submit="" lay-filter="english" id="english">英文</button>
						</div>
					</div>--%>
	                <div class="layui-field-box">
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品图片:</label>
	                        <div class="layui-input-block line-h-35 f-16" id="imgUrl">
		                        <!-- <img alt="图片" src="" id="imgUrl" style="height: 100px;width: 100px;"> -->
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品名称:</label>
	                        <div class="layui-input-block goodsName line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品编号:</label>
	                        <div class="layui-input-block goodsNumber line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品价格:</label>
	                        <div class="layui-input-block goodsPrice line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品标题:</label>
	                        <div class="layui-input-block title line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品描述:</label>
	                        <div class="layui-input-block description line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">是否邮递:</label>
	                        <div class="layui-input-block needPost line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">库存数量:</label>
	                        <div class="layui-input-block storeNumber line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">销售数量:</label>
	                        <div class="layui-input-block salesNumber line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">评价数量:</label>
	                        <div class="layui-input-block appraiseNumber line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品状态:</label>
	                        <div class="layui-input-block goodsStatus line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">上架时间:</label>
	                        <div class="layui-input-block putawayTime line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">下架时间:</label>
	                        <div class="layui-input-block soldoutTime line-h-35 f-16">
	                        </div>
	                    </div>
	                </div>
	            </fieldset>
	        </div>
	    </div>
	    
        <!-- 底部固定区域 -->
   		<%@ include file="../admin_common/fooder.jsp" %>
    </div>
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_commodity/commodityView.js"></script>
</body>
</html>