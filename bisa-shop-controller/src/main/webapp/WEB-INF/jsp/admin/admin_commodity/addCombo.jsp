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
    <title>碧沙康健_添加套餐</title>
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
		var shopUrl = "<%=resource.getString("access.allow.url") %>";
	</script>
    <!-- 操作 工具栏 -->
        <script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
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
                <p class="f-20 col-8d969d">
                    	添加套餐
                </p>
				<form class="layui-form" action="" lay-filter="">
				
				  <div class="layui-form-item">
				    <label class="layui-form-label">主商品</label>
				    <div class="layui-input-block" style="width: 200px;">
				      <select name="mainGoodsNumber" id="option1" lay-verify="required">
				      </select>
				    </div>
				  </div>
				  <div class="layui-form-item">
				      <label class="layui-form-label">套餐价格</label>
				      <div class="layui-input-block" style="width: 200px;">
				        <input type="number" name="mainGoodsPrice" placeholder="￥ 折扣价" lay-verify="required" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  <div class="layui-form-item">
				      <label class="layui-form-label">商品个数</label>
				      <div class="layui-input-block" style="width: 200px;">
				        <input type="number" name="mainGoodsCount" placeholder="商品个数" lay-verify="required" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  
				  <hr>
				  
				  <div class="layui-form-item mt-10">
				    <label class="layui-form-label">副商品</label>
				    <div class="layui-input-block" style="width: 200px;">
				      <select name="assistantGoodsNumber" id="option2">
				      </select>
				    </div>
				  </div>
				  <div class="layui-form-item">
				      <label class="layui-form-label">套餐价格</label>
				      <div class="layui-input-block" style="width: 200px;">
				        <input type="number" name="assistantGoodsPrice" placeholder="￥ 折扣价" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  <div class="layui-form-item">
				      <label class="layui-form-label">商品个数</label>
				      <div class="layui-input-block" style="width: 200px;">
				        <input type="number" name="assistantGoodsCount" placeholder="商品个数" autocomplete="off" class="layui-input">
				      </div>
				  </div>
				  
				  <div class="layui-form-item">
				    <div class="layui-input-block">
				      <button class="layui-btn" lay-submit="" lay-filter="addComboBtn" id="addComboBtn">添加套餐</button>
				      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
				    </div>
				  </div>
				</form>  
				
				<p class="f-18 mt-20 pt-15 pb-15 col-8d969d">
                    	参考商品
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <table class="layui-hide" id="commodityList" lay-filter="commodityListTable"></table>
                </div>
            </div>
        </div>
         <!-- 底部固定区域 -->
   		<%@ include file="../admin_common/fooder.jsp" %>
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
	    
       
    </div>
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_commodity/addCombo.js"></script>
</body>
</html>