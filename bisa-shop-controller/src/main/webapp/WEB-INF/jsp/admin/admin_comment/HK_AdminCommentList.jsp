<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bisa.health.shop.admin.enums.AppraiseStatus"%> 
<%
 	String path = request.getContextPath();
 	String basePath = request.getScheme() + "://"
 	+ request.getServerName() + ":" + request.getServerPort()
 	+ path + "/";
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
    <title>碧沙康健_评价管理</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
		var veryBad = "<%=AppraiseStatus.veryBad.getValue()%>";  
		var bad = "<%=AppraiseStatus.bad.getValue()%>";  
		var general = "<%=AppraiseStatus.general.getValue()%>";  
		var good = "<%=AppraiseStatus.good.getValue()%>";  
		var veryGood = "<%=AppraiseStatus.veryGood.getValue()%>";  
	</script>
    <script type="text/html" id="barDemo">
 	   {{#  if(d.reply == null){ }}  
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="replyComment">回复评论</a>
       {{#  }else{ }}
        <a class="layui-btn layui-btn-xs layui-btn">已经回复</a>
	   {{#  } }}
    </script>
    <script type="text/html" id="appraiseDegreeTpl">
       {{#  if(d.appraiseDegree == null){ }}  
<span style="color: blue">该用户还没有评价！</span>
       {{#  } }}
       {{#  if(d.appraiseDegree == veryBad){ }}  
<span style="color: red">失望★</span>
       {{#  } }}
       {{#  if(d.appraiseDegree == bad){ }}  
<span style="color: red">一般★★ </span>
       {{#  } }}
       {{#  if(d.appraiseDegree == general){ }}  
<span style="color: red">满意★★★</span>
       {{#  } }}
       {{#  if(d.appraiseDegree == good){ }}  
<span style="color: red">喜欢★★★★</span>
       {{#  } }}
       {{#  if(d.appraiseDegree == veryGood){ }}  
<span style="color: red">超爱★★★★★</span>
       {{#  } }}
	</script>
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <!-- 头部导航部分 -->
        <%@ include file="../admin_common/header.jsp" %>
        <!-- 左侧导航区域 -->
        <%@ include file="commentNavigation.jsp" %>
        
        <!-- 内容主体区域 -->
        <div class="layui-body">
            <div style="padding: 50px;">
            	<p class="f-18 pt-15 pb-15 col-8d969d">
                    查看评论
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2">
                    <!-- 这里用layui的数据表格的重载 -->
                    <form class="layui-form" action="">
                        <div class="layui-form-item" pane="">
                            <label class="layui-form-label f-14">分类：</label>
                            <div class="layui-input-block">
                                <button type="button" value="0" class="layui-btn layui-btn-sm btn-sidebar ">全部</button>
                                <button type="button" value="1" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">失望★</button>
                                <button type="button" value="2" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">一般★★ </button>
                                <button type="button" value="3" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">满意★★★</button>
                                <button type="button" value="4" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">喜欢★★★★</button>
                                <button type="button" value="5" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">超爱★★★★★</button>
                                <button type="button" value="6" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">已回复</button>
                                <button type="button" value="7" class="layui-btn layui-btn-primary layui-btn-sm btn-sidebar">未回复</button>
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
                                        <input id="ipt_year" type="text" name="n_year" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的年份" readonly="readonly">
                                        <!-- 月份 -->
                                        <input id="ipt_month" type="text" name="n_month" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的月份" readonly="readonly">
                                        <!-- 日期 -->
                                        <input id="ipt_day" type="text" name="n_day" lay-verify="" autocomplete="off" class="layui-input dis-n sel_value cur-p" placeholder="请选择您要查询的日期" readonly="readonly">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <button type="submit" class="layui-btn layui-btn-sm" lay-submit="" lay-filter="search1">搜索</button>
                                    <button type="button" class="layui-btn layui-btn-sm btn-refresh">刷新</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            
                <p class="f-18 pt-15 pb-15 col-8d969d">
                    评价列表
                </p>
                <div class="clear pd-15 bg-fafafa bor bor-col-e8ebf2 min-w-1200">
                    <table id="commentlist" lay-filter="test"></table>
                </div>
            </div>
        </div>
        
        <!-- (查看商品基本信息)============================== 的弹出层的基本内容，供js调用======================= -->
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
	                        <label class="layui-form-label pl-0 pr-0">商品标题:</label>
	                        <div class="layui-input-block title line-h-35 f-16">
	                        </div>
	                    </div>
	                    <div class="layui-form-item mb-0">
	                        <label class="layui-form-label pl-0 pr-0">商品描述:</label>
	                        <div class="layui-input-block description line-h-35 f-16">
	                        </div>
	                    </div>
	                </div>
	            </fieldset>
	        </div>
	    </div>
	    
	<!-- 店家回复评论，供js调用 -->
    <div class="detailwaybill1 dis-n">
        <div class="clear pd-20 f-16">
            <form class="layui-form" action="">
                <input type="hidden" id="UserAppraiseId" value="">
                <div class="clear mb-20 h-40">
                    <div class="clear w-120 pull-left text-right h-40 line-h-40">
                       评价人：
                    </div>
                    <div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10 detail_waybillnum1">
                    </div>
                </div>
                <div class="clear mb-20 h-40">
                    <div class="clear w-120 pull-left text-right h-40 line-h-40">
                       评价内容：
                    </div>
                    <div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10 appraise_content">
                    </div>
                </div>
				<div class="layui-form-item layui-form-text">
					<div class="clear w-120 pull-left text-right h-40 line-h-40">
                        回复内容：
                    </div>
				    <div class="clear w-300 pull-left bg-fafafa h-40 line-h-40 ml-20 pl-10">
				      <textarea placeholder="请输入回复内容..." class="layui-textarea" name="textarea" lay-verify="required"></textarea>
				    </div>
				</div><br/><br/><br/>
                <div class="layui-form-item">
                    <div class="text-right">
                        <button class="layui-btn layui-btn-sm mr-20" lay-submit="" lay-filter="reply">回复</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
	    
    <!-- 底部固定区域 -->
    <%@ include file="../admin_common/fooder.jsp" %>
    </div>
    <script src="resources/js/comm/jquery.min.js"></script>
    <script src="resources/ctrl/layui/layui.js"></script>
    <script src="resources/js/admin/admin_comment/HK_AdminCommentList.js"></script>
</body>
</html>