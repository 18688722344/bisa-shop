$(document).ready(function () {

    //获取地址栏的  商品编号
    var url = document.location.href;
    var goodsNumber = url.split("=")[1];
    if (goodsNumber != null && goodsNumber != "") {
        goodsNumber = goodsNumber.substring(0, goodsNumber.indexOf("&"));
        //默认加载简体的图片
        $("#lang").val(2);//1 表示值简体
        //loadImgs(goodsNumber, 1);
    }



    //加载layui
    layui.use('upload', function () {
        var $ = layui.jquery,
            upload = layui.upload;

        //普通图片1 上传
        var uploadInst1 = upload.render({
            elem: '#test1' //绑定元素
            , url: 'admin/commodity/uploadPicture' //上传接口
            , data: {
                internationalization: function () {
                    return internationalization;
                },
                position: function () {
                    return position;
                },
                goodsNumber: function () {
                    return goodsNumber;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo0").attr('src', result); //图片链接（base64）
                });
                this.data = {
                    internationalization: $("#lang").val(),
                    position: $("#test1").val(),
                    goodsNumber: goodsNumber == null ? $("#img_goods_number").val() : goodsNumber
                };
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText1');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
                        var demoText = $('#demoText1');
                        demoText.html('<span style="color:red">系统异常!</span>');
                    });
                }
            }
            , error: function () {
                layer.msg('上传图片 失败!', {icon: 5, time: 600}, function () {
                });
                //演示失败状态，并实现重传
                var demoText = $('#demoText1');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst1.upload();
                });
            }
        });
        //普通图片2 上传
        var uploadInst2 = upload.render({
            elem: '#test2' //绑定元素
            , url: 'admin/commodity/uploadPicture' //上传接口
            , data: {
                internationalization: function () {
                    return internationalization;
                },
                position: function () {
                    return position;
                },
                goodsNumber: function () {
                    return goodsNumber;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo1").attr('src', result); //图片链接（base64）
                });
                this.data = {
                    internationalization: $("#lang").val(),
                    position: $("#test2").val(),
                    goodsNumber: goodsNumber == null ? $("#img_goods_number").val() : goodsNumber
                };
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText2');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
                        var demoText = $('#demoText2');
                        demoText.html('<span style="color:red">系统异常!</span>');
                    });
                }
            }
            , error: function () {
                layer.msg('上传图片 失败!', {icon: 5, time: 600}, function () {
                });
                //演示失败状态，并实现重传
                var demoText = $('#demoText2');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst2.upload();
                });
            }
        });
        //普通图片3 上传
        var uploadInst3 = upload.render({
            elem: '#test3' //绑定元素
            , url: 'admin/commodity/uploadPicture' //上传接口
            , data: {
                internationalization: function () {
                    return internationalization;
                },
                position: function () {
                    return position;
                },
                goodsNumber: function () {
                    return goodsNumber;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo2").attr('src', result); //图片链接（base64）
                });
                this.data = {
                    internationalization: $("#lang").val(),
                    position: $("#test3").val(),
                    goodsNumber: goodsNumber == null ? $("#img_goods_number").val() : goodsNumber
                };
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText3');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
                        var demoText = $('#demoText3');
                        demoText.html('<span style="color:red">系统异常!</span>');
                    });
                }
            }
            , error: function () {
                layer.msg('上传图片 失败!', {icon: 5, time: 600}, function () {
                });
                //演示失败状态，并实现重传
                var demoText = $('#demoText3');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst3.upload();
                });
            }
        });
        //普通图片4 上传
        var uploadInst4 = upload.render({
            elem: '#test4' //绑定元素
            , url: 'admin/commodity/uploadPicture' //上传接口
            , data: {
                internationalization: function () {
                    return internationalization;
                },
                position: function () {
                    return position;
                },
                goodsNumber: function () {
                    return goodsNumber;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo3").attr('src', result); //图片链接（base64）
                });
                this.data = {
                    internationalization: $("#lang").val(),
                    position: $("#test4").val(),
                    goodsNumber: goodsNumber == null ? $("#img_goods_number").val() : goodsNumber
                };
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText4');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
                        var demoText = $('#demoText4');
                        demoText.html('<span style="color:red">系统异常!</span>');
                    });
                }
            }
            , error: function () {
                layer.msg('上传图片 失败!', {icon: 5, time: 600}, function () {
                });
                //演示失败状态，并实现重传
                var demoText = $('#demoText4');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst4.upload();
                });
            }
        });
        //普通图片5 上传
        var uploadInst5 = upload.render({
            elem: '#test5' //绑定元素
            , url: 'admin/commodity/uploadPicture' //上传接口
            , data: {
                internationalization: function () {
                    return internationalization;
                },
                position: function () {
                    return position;
                },
                goodsNumber: function () {
                    return goodsNumber;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo4").attr('src', result); //图片链接（base64）
                });
                this.data = {
                    internationalization: $("#lang").val(),
                    position: $("#test5").val(),
                    goodsNumber: goodsNumber == null ? $("#img_goods_number").val() : goodsNumber
                };
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText5');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
                        var demoText = $('#demoText5');
                        demoText.html('<span style="color:red">系统异常!</span>');
                    });
                }
            }
            , error: function () {
                layer.msg('上传图片 失败!', {icon: 5, time: 600}, function () {
                });
                //演示失败状态，并实现重传
                var demoText = $('#demoText5');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst5.upload();
                });
            }
        });
        //普通图片6 上传
        var uploadInst6 = upload.render({
            elem: '#test6' //绑定元素
            , url: 'admin/commodity/uploadPicture' //上传接口
            , data: {
                internationalization: function () {
                    return internationalization;
                },
                position: function () {
                    return position;
                },
                goodsNumber: function () {
                    return goodsNumber;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo5").attr('src', result); //图片链接（base64）
                });
                this.data = {
                    internationalization: $("#lang").val(),
                    position: $("#test6").val(),
                    goodsNumber: goodsNumber == null ? $("#img_goods_number").val() : goodsNumber
                };
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText6');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
                        var demoText = $('#demoText6');
                        demoText.html('<span style="color:red">系统异常!</span>');
                    });
                }
            }
            , error: function () {
                layer.msg('上传图片 失败!', {icon: 5, time: 600}, function () {
                });
                //演示失败状态，并实现重传
                var demoText = $('#demoText6');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst6.upload();
                });
            }
        });
        //普通图片7 上传
        var uploadInst7 = upload.render({
            elem: '#test7' //绑定元素
            , url: 'admin/commodity/uploadPicture' //上传接口
            , data: {
                internationalization: function () {
                    return internationalization;
                },
                position: function () {
                    return position;
                },
                goodsNumber: function () {
                    return goodsNumber;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo6").attr('src', result); //图片链接（base64）
                });
                this.data = {
                    internationalization: $("#lang").val(),
                    position: $("#test7").val(),
                    goodsNumber: goodsNumber == null ? $("#img_goods_number").val() : goodsNumber
                };
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText7');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
                        var demoText = $('#demoText7');
                        demoText.html('<span style="color:red">系统异常!</span>');
                    });
                }
            }
            , error: function () {
                layer.msg('上传图片 失败!', {icon: 5, time: 600}, function () {
                });
                //演示失败状态，并实现重传
                var demoText = $('#demoText7');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst7.upload();
                });
            }
        });

    });
});

//加载商品图片
function loadImgs(goodsNumber, internationalization) {
    //异步加载goods的图片
    $.ajax({
        url: 'admin/commodity/loadGoodsImgInfo',
        type: "GET",
        data: {
            goodsNumber: goodsNumber,
            internationalization: internationalization
        },
        success: function (data) {
            //获取的图片集合的时候，要把以前的img标签中的src 替换成白色背景的图片  resources/img/admin/admin_comm/add-img.jpg
            $(".layui-upload-img").attr("src", "resources/img/admin/admin_comm/add-img.jpg");

            for (var index in data) {
                var demoIndex = data[index].position - 1;
                var demo = "#demo" + demoIndex;
                $(demo).attr("src", data[index].imgUrl);
            }
        }
    });
}
