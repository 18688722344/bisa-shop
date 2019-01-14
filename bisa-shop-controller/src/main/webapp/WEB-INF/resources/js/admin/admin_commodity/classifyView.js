$(document).ready(function () {
    var amount = 1;//数据的总数
    var editObj = null, ptable = null, treeGrid = null, tableId = 'treeTable', layer = null;

    layui.config({
        base: 'design/extend/'
    }).extend({
        treeGrid: 'treeGrid'
    }).use(['jquery', 'treeGrid', 'layer'], function () {
        var $ = layui.jquery;
        treeGrid = layui.treeGrid;//很重要
        layer = layui.layer;

        ptable = treeGrid.render({
            id: tableId,
            elem: '#' + tableId,
            idField: 'id',
            url: 'admin/commodity/classifyInfo',
            cellMinWidth: 100,
            treeId: 'id',	//树形id字段名称
            treeUpId: 'superId',		//树形父id字段名称
            treeShowName: 'classifyName',	//以树形式显示的字段
            page: false,
            height: 'full-5',
            cols: [[
                {
                    width: 150, title: '操作', align: 'right'/*toolbar: '#barDemo'*/
                    , templet: function (d) {
                        var html = "";
                        if (d.superId == 0) {
                            html += '<a class="layui-btn layui-btn layui-btn-xs" lay-event="add">添加</a>';
                            html += '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="property">属性</a>';
                        }
                        html += '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="del">删除</a>';
                        return html;
                    }
                }
                , {field: 'classifyName', edit: 'text', width: 300, title: '类别名称'}
                , {field: 'id', width: 100, title: 'id'}
                , {field: 'superId', title: '父级类别'}
            ]],
            done: function (res, curr, count) {
                amount = count;//数据的总数  赋值

                $("[data-field = 'superId']").children().each(function (index) {
                    if (index != 0) {
                        var superId = $(this).text();
                        if (superId == "" || superId == null || superId == 0) {
                            $(this).text("一级分类");
                        } else {
                            $(this).text(superId);
                        }
                    }
                })
            }
        });

        //监听单元格编辑
        treeGrid.on('edit(treeTable)', function (obj) {
            var value = obj.value //得到修改后的值
                , data = obj.data //得到所在行所有键值
                , field = obj.field; //得到字段

            $.ajax({
                url: 'admin/commodity/updateClassify',
                data: {
                    id: data.id,
                    classifyName: value
                },
                type: "POST",
                success: function (datas) {
                    console.log("data>>" + data.superId);
                    if (datas.flag == true) {
                        if (data.superId == 0) {//添加父级分类
                            layer.msg('请为父级分类添加子级类别!', {icon: 6, time: 1000}, function () {
                            });
                        } else {
                            layer.msg('修改成功!', {icon: 6, time: 1000}, function () {
                            });
                        }
                    }
                    if (datas.flag == false) {
                        layer.msg('系统故障!', {icon: 5, time: 1000}, function () {
                            window.location.reload();
                        });
                    }
                }
            });
        });

        //监听 页面按钮
        treeGrid.on('tool(' + tableId + ')', function (obj) {
            if (obj.event === 'del') {//删除行
                del(obj);
            } else if (obj.event === "add") {//添加
                add(obj.data);
            } else if (obj.event === "property") {	//属性
                showProperty(obj.data);
            }
        });

    });

    //删除
    function del(obj) {
        layer.confirm("你确定删除数据吗？如果存在下级节点则一并删除，此操作不能撤销！", {icon: 3, title: '提示'},
            function (index) {//确定回调
                $.ajax({
                    url: 'admin/commodity/delClassify',
                    data: {
                        id: obj.data.id
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('删除数据 成功!', {icon: 6, time: 1000}, function () {
                                obj.del();
                                layer.close(index);
                            });
                        }
                        if (data.flag == false) {
                            layer.msg('系统故障!', {icon: 5, time: 1000}, function () {
                                window.location.reload();
                            });
                        }
                    }
                });
            }, function (index) {//取消回调
                layer.close(index);
            }
        );
    }

    //添加
    function add(pObj) {
        var superId;
        if (pObj == null) {
            superId = 0;
        } else {
            superId = pObj.id;
        }

        $.ajax({
            url: 'admin/commodity/insertClassify',
            data: {
                superId: superId,
                id: ++amount,
                classifyName: '请填写名称'
            },
            type: "POST",
            success: function (data) {
            }
        });
        //下面的是操作数据表格
        var param = {};
        param.classifyName = '请填写名称';
        param.id = amount;
        param.superId = pObj ? pObj.id : '一级分类';
        treeGrid.addRow(tableId, pObj ? pObj.LAY_TABLE_INDEX + 1 : 0, param);
    }

    //查看类别属性
    function showProperty(data) {
        window.location.href = "admin/commodity/propertyView?classifyId=" + data.id;
    }

    //新增一行(新增的是最高级别分类)
    $("#add").click(function () {
        add(null);
    })

});
