$(document).ready(function () {
    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;

    /*定义ckeditor*/
    var editor = CKEDITOR.replace('newseditor', {
        customConfig: 'custom/ckeditor_config.js',
        height: 550,
    });
//layui渲染表单
function renderForm() {
       layui.use('form', function () {
       var form = layui.form;
       form.render();
            });
        }
    //获取地址栏的  新闻表的id
    var url = document.location.href;
    var id = url.split("=")[1];
    /*添加新闻*/
        form.on('submit(addNews)', function (data) {
            /*获取ck的数据*/
            var ck_val = CKEDITOR.instances.newseditor.getData();
            /*把ck的值推入到lay数组里面*/
            data.field.newseditor = ck_val;
            //后台编辑=====
            $.ajax({
                url: 'admin/news/addUpdateNews',
                data: JSON.stringify({
                    //internationalization:2,
                    id: data.field.id,
                    img_url: data.field.photo_address,
                    author: data.field.author,
                    news_title: data.field.main_title,
                    news_subhead: data.field.subheading,
                    news_content: data.field.newseditor,
                    read_quantity: data.field.readings
                }),
                type: "POST",
                contentType: "application/json",
                success: function (data) {
                    if (data.flag == false) {
                        layer.msg('异常！重试或联系运维人员！', {icon: 5, time: 1000}, function () {
                        });
                    }
                    if (data.msg == "100") {
                        layer.msg('添加新闻  成功!', {icon: 6, time: 1000}, function () {
                            window.location.href = "admin/news/listNews";
                        });
                    } else if (data.msg == "200") {
                        layer.msg('修改新闻  成功!', {icon: 6, time: 1000}, function () {
                            window.location.href = "admin/news/listNews";
                        });
                    }
                }
            });
            return false;
        });
    });
});


