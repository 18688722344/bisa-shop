$(document).ready(function () {
    //加载首页的图片,当前显示香港的图片
    getIndexImgs(2);
    //加载商品还没有那种语言版本
    selectBean();

    $(".carousel_btn button").click(function () {
        if ($(this).attr('data-num') == 1) {
            //简体
            $(this).removeClass("layui-btn-primary");
            $(".carousel_btn button:eq(1)").addClass('layui-btn-primary');
            $(".carousel_btn button:eq(2)").addClass('layui-btn-primary');
            getIndexImgs(1);
        } else if ($(this).attr('data-num') == 2) {
            //繁体
            $(this).removeClass("layui-btn-primary");
            $(".carousel_btn button:eq(0)").addClass('layui-btn-primary');
            $(".carousel_btn button:eq(2)").addClass('layui-btn-primary');
            getIndexImgs(2);
        } else {
            //英文
            $(this).removeClass("layui-btn-primary");
            $(".carousel_btn button:eq(0)").addClass('layui-btn-primary');
            $(".carousel_btn button:eq(1)").addClass('layui-btn-primary');
            getIndexImgs(3);
        }
    })

    //加载layui
    layui.use(['element', 'carousel', 'upload'], function () {
        var element = layui.element;
        var carousel = layui.carousel;
        var upload = layui.upload;

        //轮播使用
        carousel.render({
            elem: '#test1'
            , width: '920px' //设置容器宽度
            , arrow: 'always' //始终显示箭头
        });

        //上传图片按钮
        uploadimg1(upload);
        uploadimg2(upload);
        uploadimg3(upload);
        uploadimg4(upload);
    });
});

//上传图片1
function uploadimg1(upload) {
    //文件拖拽上传
    upload.render({
        elem: '#test11',//绑定元素
        url: 'admin/uploadIndexImg',
        data: {
            position: function () {
                return position;
            },
            internationalization: function () {
                return internationalization;
            }
        },
        before: function () {//文件上传前的回调
            this.data = {internationalization: $(".select1").val(), position: $("#img1").val()};
        },
        done: function (data) {
            if (data.msg == "200") {
                layer.msg('上传图片 成功!', {icon: 6, time: 1000}, function () {
                    var demoText = $('#demoText1');
                    demoText.html('<br/><span style="color:blue">上传成功</span>');
                    location.reload();
                });
            } else if (data.msg == "300") {
                layer.msg('请选择语言版本!', {icon: 5, time: 1000}, function () {
                });
                var demoText = $('#demoText1');
                demoText.html('<br/><span style="color:#FF5722;">请选择语言版本!</span>');
            } else {
                layer.msg('系统异常!', {icon: 5, time: 1000}, function () {
                    var demoText = $('#demoText1');
                    demoText.html('<br/><span style="color: #FF5722;">系统异常，请联系管理员！</span>');
                });
            }
        },
        error: function () {
            layer.msg('上传图片 失败!', {icon: 5, time: 1000}, function () {
            });
            //演示失败状态
            var demoText = $('#demoText1');
            demoText.html('<br/><span style="color: #FF5722;">上传失败，请重试！</span>');
        }
    });
}

//上传图片2
function uploadimg2(upload) {
    //文件拖拽上传
    upload.render({
        elem: '#test12',//绑定元素
        url: 'admin/uploadIndexImg',
        data: {
            position: function () {
                return position;
            },
            internationalization: function () {
                return internationalization;
            }
        },
        before: function () {//文件上传前的回调
            this.data = {internationalization: $(".select2").val(), position: $("#img2").val()};
        },
        done: function (data) {
            if (data.msg == "200") {
                layer.msg('上传图片 成功!', {icon: 6, time: 1000}, function () {
                    var demoText = $('#demoText2');
                    demoText.html('<br/><span style="color:blue">上传成功</span>');
                    location.reload();
                });
            } else if (data.msg == "300") {
                layer.msg('请选择语言版本!', {icon: 5, time: 1000}, function () {
                });
                var demoText = $('#demoText2');
                demoText.html('<br/><span style="color:#FF5722;">请选择语言版本!</span>');
            } else {
                layer.msg('系统异常!', {icon: 5, time: 1000}, function () {
                    var demoText = $('#demoText2');
                    demoText.html('<br/><span style="color: #FF5722;">系统异常，请联系管理员！</span>');
                });
            }
        },
        error: function () {
            layer.msg('上传图片 失败!', {icon: 5, time: 1000}, function () {
            });
            //演示失败状态
            var demoText = $('#demoText2');
            demoText.html('<br/><span style="color: #FF5722;">上传失败，请重试！</span>');
        }
    });
}

//上传图片3
function uploadimg3(upload) {
    //文件拖拽上传
    upload.render({
        elem: '#test13',//绑定元素
        url: 'admin/uploadIndexImg',
        data: {
            position: function () {
                return position;
            },
            internationalization: function () {
                return internationalization;
            }
        },
        before: function () {//文件上传前的回调
            this.data = {internationalization: $(".select3").val(), position: $("#img3").val()};
        },
        done: function (data) {
            if (data.msg == "200") {
                layer.msg('上传图片 成功!', {icon: 6, time: 1000}, function () {
                    var demoText = $('#demoText3');
                    demoText.html('<br/><span style="color:blue">上传成功</span>');
                    location.reload();
                });
            } else if (data.msg == "300") {
                layer.msg('请选择语言版本!', {icon: 5, time: 1000}, function () {
                });
                var demoText = $('#demoText3');
                demoText.html('<br/><span style="color:#FF5722;">请选择语言版本!</span>');
            } else {
                layer.msg('系统异常!', {icon: 5, time: 1000}, function () {
                    var demoText = $('#demoText3');
                    demoText.html('<br/><span style="color: #FF5722;">系统异常，请联系管理员！</span>');
                });
            }
        },
        error: function () {
            layer.msg('上传图片 失败!', {icon: 5, time: 1000}, function () {
            });
            //演示失败状态
            var demoText = $('#demoText3');
            demoText.html('<br/><span style="color: #FF5722;">上传失败，请重试！</span>');
        }
    });
}

//上传图片4
function uploadimg4(upload) {
    //文件拖拽上传
    upload.render({
        elem: '#test14',//绑定元素
        url: 'admin/uploadIndexImg',
        data: {
            position: function () {
                return position;
            },
            internationalization: function () {
                return internationalization;
            }
        },
        before: function () {//文件上传前的回调
            this.data = {internationalization: $(".select4").val(), position: $("#img4").val()};
        },
        done: function (data) {
            if (data.msg == "200") {
                layer.msg('上传图片 成功!', {icon: 6, time: 1000}, function () {
                    var demoText = $('#demoText4');
                    demoText.html('<br/><span style="color:blue">上传成功</span>');
                    location.reload();
                });
            } else if (data.msg == "300") {
                layer.msg('请选择语言版本!', {icon: 5, time: 1000}, function () {
                });
                var demoText = $('#demoText4');
                demoText.html('<br/><span style="color:#FF5722;">请选择语言版本!</span>');
            } else {
                layer.msg('系统异常!', {icon: 5, time: 1000}, function () {
                    var demoText = $('#demoText4');
                    demoText.html('<br/><span style="color: #FF5722;">系统异常，请联系管理员！</span>');
                });
            }
        },
        error: function () {
            layer.msg('上传图片 失败!', {icon: 5, time: 1000}, function () {
            });
            //演示失败状态
            var demoText = $('#demoText4');
            demoText.html('<br/><span style="color: #FF5722;">上传失败，请重试！</span>');
        }
    });
}

//加载首页的图片,当前显示繁体的图片
function getIndexImgs(lang) {
    $.ajax({
        url: 'admin/indexImgs',
        type: "get",
        data: {lang: lang},
        success: function (data) {
            console.log(data);

            for (var index in data) {
                var i = data[index].position - 1;
                $(".img" + i).attr("src", data[index].imgUrl);
            }
        }
    });
}

//加载商品还没有那种语言版本
function selectBean() {
    $.ajax({
        url: 'admin/selectLanguage',
        type: "GET",
        success: function (data) {
            //下拉框赋值
            var html = '<option value="">请选择语言类型</option>';
            for (var index in data) {
                html += "<option value='" + data[index].key + "'>" + data[index].value + "</option>";
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