$(document).ready(function () {


    //初始化layui第三方的插件
    layui.config({
        base: 'plugin/formSelects/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });

    /*layui方面js*/
    layui.use(['form', 'table', 'upload', 'element', 'laydate', 'jquery', 'formSelects'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            laydate = layui.laydate,
            table = layui.table,
            upload = layui.upload,
            formSelects = layui.formSelects;


        //server
        formSelects.data('select15', 'server', {
            url: 'admin/commodity/getClassifyInfo',
            linkage: true,
            linkageWidth: 100   //代表每一级别的宽度, 默认是100
        });

        //监听提交(确定按钮)
        form.on('submit(demo1)', function (data) {
            var obj = data.field;

            $.ajax({
                url: 'admin/commodity/insertPropertyValue',
                data: {
                    classify_id: obj.classify,
                    property: obj.property,
                    propertyValue: obj.propertyValue
                },
                type: "POST",
                success: function (data) {
                    if (data.flag == true) {
                        layer.msg('保存标签 成功', {icon: 6, time: 2000}, function () {
                            window.location.reload();
                        });
                    }
                    if (data.flag == false) {
                        layer.msg('系统故障!', {icon: 5, time: 2000}, function () {
                            window.location.reload();
                        });
                    }
                }
            });
            return false;
        });

    });
});

