$(document).ready(function () {

    var param = window.location.search;
    var classifyId = getURLArg(param, "classifyId");
    loadPropertyDatas(classifyId);

    function showPropertyDatas(datas) {
        /*layui方面js*/
        layui.use(['form', 'table', 'element'], function () {
            var layer = layui.layer,
                element = layui.element,
                table = layui.table;
            //=================执行渲染==================
            table.render({
                elem: '#propertyList', //指定原始表格元素选择器（推荐id选择器）
                id: 'propertyList',
                data: datas,
                cols: [
                    [ //标题栏
                        {field: 'id', title: 'ID', width: 60, sort: true, align: 'center'},
                        {field: 'name', title: '属性', width: 120, align: 'center'},
                        {fixed: 'right', title: '操作', width: 180, align: 'center', toolbar: '#propertyBar'}
                    ]
                ],
            });

            //===============监听工具条===================
            table.on('tool(propertyTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if (layEvent === 'del') { //删除
                    var id = data.id;
                    layer.confirm('确认删除此属性么？', function (index) {
                        $.ajax({
                            url: 'admin/commodity/deleteProperty',
                            data: {
                                id: id
                            },
                            type: "POST",
                            success: function (datas) {
                                if (datas == "200") {
                                    layer.msg('删除成功！', {icon: 6, time: 1000}, function () {
                                        obj.del();
                                        layer.close(index);
                                    });
                                } else {
                                    layer.msg('删除失败！', {icon: 5, time: 1000}, function () {
                                        window.location.reload();
                                    });
                                }
                            }
                        });
                    });
                } else if (layEvent === 'edit') { //编辑
                    layer.prompt({
                        formType: 2,
                        value: data.name,
                        title: '编辑属性',
                        area: ['150px', '50px'] //自定义文本域宽高
                    }, function (value, index, elem) {
                        var classifyId = $("#classifyId").val();

                        $.ajax({
                            url: 'admin/commodity/editProperty',
                            type: "POST",
                            data: {
                                id: data.id,
                                propertyName: value
                            },
                            success: function (datas) {
                                if (datas == "200") {
                                    layer.msg('修改成功！', {icon: 6, time: 1000}, function () {
                                        window.location.reload();
                                    });
                                } else {
                                    layer.msg('修改失败！', {icon: 5, time: 1000}, function () {
                                        layer.close(index);
                                    });
                                }
                            }
                        });
                    });
                } else if (layEvent === 'showValue') {	//加载属性值
                    $("#propertyId").val(data.id);	//用来添加属性值

                    $.ajax({
                        url: 'admin/commodity/selectPropertyValues?propertyId=' + data.id,
                        type: "GET",
                        success: function (values) {
                            loadPropertyValue(values);
                        }
                    });

                    //显示属性值div
                    $(".property-value-div").removeClass("dis-n");
                }
            });
        });
    }

    function loadPropertyValue(values) {
        layui.use(['form', 'table', 'element'], function () {
            var layer = layui.layer,
                element = layui.element,
                table = layui.table;

            table.render({
                elem: '#propertyValueList', //指定原始表格元素选择器（推荐id选择器）
                id: 'propertyValueList',
                data: values,
                cols: [
                    [ //标题栏
                        {field: 'id', title: 'ID', width: 60, sort: true, align: 'center'},
                        {field: 'valueId', title: '属性值编码', width: 120, align: 'center'},
                        {field: 'name', title: '属性值', width: 120, align: 'center'},
                        {fixed: 'right', title: '操作', width: 120, align: 'center', toolbar: '#valueBar'}
                    ]
                ],
            });

            table.on('tool(valueTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值
                var tr = obj.tr; //获得当前行 tr 的DOM对象


                if (layEvent === 'delValue') { //删除
                    var id = data.id;
                    layer.confirm('确认删除此属性么？', function (index) {
                        $.ajax({
                            url: 'admin/commodity/deletePropertyValues',
                            data: {
                                id: id
                            },
                            type: "POST",
                            success: function (datas) {
                                if (datas == "200") {
                                    layer.msg('删除成功！', {icon: 6, time: 1000}, function () {
                                        obj.del();
                                        layer.close(index);
                                    });
                                } else {
                                    layer.msg('删除失败！', {icon: 5, time: 1000}, function () {
                                        window.location.reload();
                                    });
                                }
                            }
                        });
                    });
                } else if (layEvent === 'editValue') { //编辑
                    layer.prompt({
                        formType: 2,
                        value: data.name,
                        title: '编辑属性值',
                        area: ['150px', '50px'] //自定义文本域宽高
                    }, function (value, index, elem) {

                        $.ajax({
                            url: 'admin/commodity/updataPropertyValue',
                            type: "POST",
                            data: {
                                id: data.id,
                                propertyValue: value
                            },
                            success: function (data) {
                                if (data == "200") {
                                    layer.msg('修改成功！', {icon: 6, time: 1000}, function () {
                                        window.location.reload();
                                    });
                                } else {
                                    layer.msg('修改失败！', {icon: 5, time: 1000}, function () {
                                        layer.close(index);
                                    });
                                }
                            }
                        });
                    });
                }
            });
        });
    }

    function loadPropertyDatas(classifyId) {
        $.ajax({
            url: 'admin/commodity/selectPropertyList?classifyId=' + classifyId,
            type: "GET",
            success: function (data) {
                showPropertyDatas(data);
            }
        });
    }
});
//添加属性弹出层
$("#addPropertyBtn").click(function () {
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.prompt({
            formType: 2,
            title: '新增属性',
            area: ['150px', '50px'] //自定义文本域宽高
        }, function (value, index, elem) {
            var classifyId = $("#classifyId").val();

            $.ajax({
                url: 'admin/commodity/addProperty',
                type: "POST",
                data: {
                    classifyId: classifyId,
                    propertyName: value
                },
                success: function (data) {
                    if (data == "200") {
                        layer.msg('添加成功！', {icon: 6, time: 1000}, function () {
                            window.location.reload();
                        });
                    } else {
                        layer.msg('添加失败！', {icon: 5, time: 1000}, function () {
                            layer.close(index);
                        });
                    }
                }
            });
        });
    });
});

//添加属性值弹出层
$("#addPropertyValueBtn").click(function () {
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.prompt({
            formType: 2,
            title: '新增属性值',
            area: ['150px', '50px'] //自定义文本域宽高
        }, function (value, index, elem) {
            var propertyId = $("#propertyId").val();

            $.ajax({
                url: 'admin/commodity/insertPropertyValue',
                type: "POST",
                data: {
                    propertyId: propertyId,
                    propertyName: value
                },
                success: function (data) {
                    if (data == "200") {
                        layer.msg('添加成功！', {icon: 6, time: 1000}, function () {
                            window.location.reload();
                        });
                    } else {
                        layer.msg('添加失败！', {icon: 5, time: 1000}, function () {
                            layer.close(index);
                        });
                    }
                }
            });
        });
    });
});

/**
 * 获取url参数
 * @param url 目标url
 * @param arg 目标参数名
 * @returns
 */
function getURLArg(params, arg) {
    var reg = new RegExp("(^|&)" + arg + "=([^&]*)(&|$)");
    var r = params.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
