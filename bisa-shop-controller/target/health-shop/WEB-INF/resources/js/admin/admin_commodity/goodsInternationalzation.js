$(document).ready(function () {

    //获取地址栏的  商品编号
    var url = document.location.href;
    var goodsNumber = url.split("=")[1];
    if (goodsNumber != null && goodsNumber != "") {
        loadImgs(goodsNumber);
    }
    var internationalization;

    //加载主商品
    SelectBean(goodsNumber);

    //初始化layui第三方的插件
    layui.config({
        base: 'plugin/formSelects/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });

    /*layui方面js*/
    layui.use(['form', 'layer', 'element', 'upload'], function () {
        var form = layui.form,
            layer = layui.layer,
            upload = layui.upload;

        //监听提交
        form.on('submit(addBtn)', function (data) {
            var obj = data.field;
            internationalization = obj.langInter;

            $.ajax({
                url: 'admin/commodity/insertGoodsInsternationalzation',
                data: {
                    internationalization: internationalization,
                    goodsNumber: goodsNumber,
                    description: obj.description,
                    goodsName: obj.goodsName,
                    storeNumber: obj.storeNumber,
                    title: obj.title,
                },
                type: "POST",
                success: function (data) {
                    if (data.flag == true) {
                        layer.msg('添加成功！', {icon: 6, time: 1000}, function () {
                            //隐藏提交和重置的按钮
                            $(".update-btn").hide();
                            //锁定输入框
                            $("[name='goodsName']").attr("disabled", true);
                            $("[name='title']").attr("disabled", true);
                            $("[name='description']").attr("disabled", true);
                            //下拉框锁定
                            $("[name='langInter']").attr("disabled", true);
                            form.render();

                            //添加图片
                            $(".goods-img-div").removeClass("dis-n");
                            $("#img_goods_number").val(data.msg);
                            loadImgs(data.msg);
                        });
                    } else if (data.msg == "300") {
                        layer.msg('已有该语言版本，请核对后添加!', {icon: 5, time: 2000}, function () {
                        });
                    } else {
                        layer.msg('系统故障！', {icon: 5, time: 2000}, function () {
                        });
                    }
                }
            });
            return false;
        });

        //普通图片1 上传
        var uploadInst1 = upload.render({
            elem: '#test1' //绑定元素
            , url: 'admin/commodity/uploadPicture' //上传接口
            , data: {
                goodsNumber: goodsNumber,
                position: $("#test1").val(),
                internationalization: function () {
                    return internationalization;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo0").attr('src', result); //图片链接（base64）
                });
                this.data = {internationalization: internationalization, position: $("#test1").val(), goodsNumber: goodsNumber}
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText1');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
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
                goodsNumber: goodsNumber,
                position: $("#test2").val(),
                internationalization: function () {
                    return internationalization;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo1").attr('src', result); //图片链接（base64）
                });
                this.data = {internationalization: internationalization, position: $("#test2").val(), goodsNumber: goodsNumber}
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText2');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
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
                goodsNumber: goodsNumber,
                position: $("#test3").val(),
                internationalization: function () {
                    return internationalization;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo2").attr('src', result); //图片链接（base64）
                });
                this.data = {internationalization: internationalization, position: $("#test3").val(), goodsNumber: goodsNumber}
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText3');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
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
                goodsNumber: goodsNumber,
                position: $("#test4").val(),
                internationalization: function () {
                    return internationalization;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo3").attr('src', result); //图片链接（base64）
                });
                this.data = {internationalization: internationalization, position: $("#test4").val(), goodsNumber: goodsNumber}
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText4');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
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
                goodsNumber: goodsNumber,
                position: $("#test5").val(),
                internationalization: function () {
                    return internationalization;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo4").attr('src', result); //图片链接（base64）
                });
                this.data = {internationalization: internationalization, position: $("#test5").val(), goodsNumber: goodsNumber}
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText5');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
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
                goodsNumber: goodsNumber,
                position: $("#test6").val(),
                internationalization: function () {
                    return internationalization;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo5").attr('src', result); //图片链接（base64）
                });
                this.data = {internationalization: internationalization, position: $("#test6").val(), goodsNumber: goodsNumber}
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText6');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
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
                goodsNumber: goodsNumber,
                position: $("#test7").val(),
                internationalization: function () {
                    return internationalization;
                }
            }
            , before: function (obj) {
                obj.preview(function (index, file, result) {
                    $("#demo6").attr('src', result); //图片链接（base64）
                });
                this.data = {internationalization: internationalization, position: $("#test7").val(), goodsNumber: goodsNumber}
            }
            , done: function (data) {
                if (data.flag == true) {
                    layer.msg('上传图片 成功!', {icon: 6, time: 600}, function () {
                        var demoText = $('#demoText7');
                        demoText.html('<span style="color:blue">上传成功</span>');
                    });
                } else {
                    layer.msg('系统异常!', {icon: 5, time: 600}, function () {
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

//加载商品还没有那种语言版本
function SelectBean(goodsNumber) {
    $.ajax({
        url: 'admin/commodity/findLanguage',
        type: "GET",
        data: {
            goodsNumber: goodsNumber
        },
        success: function (data) {
            //下拉框赋值
            var html = '<option value="">请选择语言类型</option>';
            for (var index in data.list) {
                html += "<option value='" + data.list[index].key + "'>" + data.list[index].value + "</option>";
            }
            $(".langInter").html(html);
            renderForm();
        }
    });
}

//layui渲染表单
function renderForm() {
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });
}

//加载商品图片
function loadImgs(goodsNumber) {
    //异步加载goods的图片
    $.ajax({
        url: 'admin/commodity/loadGoodsImgInfo',
        type: "GET",
        data: {
            goodsNumber: goodsNumber
        },
        success: function (data) {
            for (var index in data) {
                var demoIndex = data[index].position - 1;
                var demo = "#demo" + demoIndex;
                $(demo).attr("src", data[index].imgUrl);
            }
        }
    });
}