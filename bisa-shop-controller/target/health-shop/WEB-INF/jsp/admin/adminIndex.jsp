<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
  <link rel="icon" href="Images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="Images/favicon.ico" type="image/x-icon"/>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!-- necessary -->
    <meta name="keywords" content="1,2,3">
    <meta name="description" content="">
    <!-- description -->
    <meta name="renderer" content="webkit">
    <title>碧沙康健_管理员首页</title>
    <!-- base -->
    <link href="resources/ctrl/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/ctrl/layui/css/layui.css" rel="stylesheet">
    <link href="resources/css/comm/base.css" rel="stylesheet">
    <link href="resources/css/admin/HK_Admin.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!-- 头部导航部分 -->
    <%@ include file="admin_common/header.jsp" %>
    <!-- 左侧导航区域 -->
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a href="">操作首页图片</a></li>
            </ul>
        </div>
    </div>

    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div style="padding: 15px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                <legend>首页图片 <span class="size">1200×500</span></legend>
            </fieldset>
            <div class="carousel" style="width: 920px;">
                <div class="layui-carousel" id="test1">
                    <div carousel-item>
                        <div><img class="img0" src="" alt=""/></div>
                        <div><img class="img1" src="" alt=""/></div>
                        <div><img class="img2" src="" alt=""/></div>
                        <div><img class="img3" src="" alt=""/></div>
                    </div>
                </div>
                <div class="carousel_btn">
                    <button class="layui-btn layui-btn-radius layui-btn-primary" data-num="1">简体</button>
                    <button class="layui-btn layui-btn-radius" data-num="2">繁体</button>
                    <button class="layui-btn layui-btn-radius layui-btn-primary" data-num="3">英文</button>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                <legend>修改首页语言版本图片</legend>
            </fieldset>
            <form class="layui-form" action="" lay-filter="">
                <div class="img_lis">

                    <%--上传图片div--%>
                    <div class="img_first">
                        <div class="layui-form-item up_img_selt_div" lay-filter="">
                            <label class="layui-form-label up_img_lab" style="width:90px; ">语言版本:</label>
                            <div class="layui-input-block up_img_selt" style="width: 100px;" id="lang1">
                                <select name="langInter" class="langInter select1" lay-verify="required">
                                </select>
                            </div>
                        </div>
                        <input type="hidden" id="img1" value="1">
                        <h2 class="img_title">第一张</h2>
                        <div class="layui-upload-drag" id="test11">
                            <i class="layui-icon"></i>
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                        <span id="demoText1"></span>
                    </div>

                    <div class="img_first">
                        <div class="layui-form-item up_img_selt_div" lay-filter="">
                            <label class="layui-form-label up_img_lab" style="width:90px; ">语言版本:</label>
                            <div class="layui-input-block up_img_selt" style="width: 100px;">
                                <select name="langInter" class="langInter select2" lay-verify="required">
                                </select>
                            </div>
                        </div>
                        <input type="hidden" id="img2" value="2">
                        <h2 class="img_title">第二张</h2>
                        <div class="layui-upload-drag" id="test12">
                            <i class="layui-icon"></i>
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                        <span id="demoText2"></span>
                    </div>

                    <div class="img_first">
                        <div class="layui-form-item up_img_selt_div" lay-filter="">
                            <label class="layui-form-label up_img_lab" style="width:90px; ">语言版本:</label>
                            <div class="layui-input-block up_img_selt" style="width: 100px;">
                                <select name="langInter" class="langInter select3" lay-verify="required">
                                </select>
                            </div>
                        </div>
                        <input type="hidden" id="img3" value="3">
                        <h2 class="img_title">第三张</h2>
                        <div class="layui-upload-drag" id="test13">
                            <i class="layui-icon"></i>
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                        <span id="demoText3"></span>
                    </div>

                    <div class="img_first">
                        <div class="layui-form-item up_img_selt_div" lay-filter="">
                            <label class="layui-form-label up_img_lab" style="width:90px; ">语言版本:</label>
                            <div class="layui-input-block up_img_selt" style="width: 100px;">
                                <select name="langInter" class="langInter select4" lay-verify="required">
                                </select>
                            </div>
                        </div>
                        <input type="hidden" id="img4" value="4">
                        <h2 class="img_title">第四张</h2>
                        <div class="layui-upload-drag" id="test14">
                            <i class="layui-icon"></i>
                            <p>点击上传，或将文件拖拽到此处</p>
                        </div>
                        <span id="demoText4"></span>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- 底部固定区域 -->
    <%@ include file="admin_common/fooder.jsp" %>
</div>
<script src="resources/js/comm/jquery.min.js"></script>
<script src="resources/ctrl/layui/layui.js"></script>
<script src="resources/js/admin/HK_AdminIndex.js"></script>
</body>
</html>