$(document).ready(function () {
    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function () {
        var layer = layui.layer,
            element = layui.element,
            table = layui.table;
        //有关表格的重载参考layui数据表格
        table.render({
            elem: '#adminRolelist', //指定原始表格元素选择器（推荐id选择器）
            height: 400, //容器高度
            cols: [
                [ //标题栏
                    /*id是隐藏的*/
                    {field: 'id', title: 'ID', width: 80, sort: true},
                    {field: 'name', title: '角色名称', width: 160},
                    {field: 'role_type', title: '角色类型', width: 160},
                    {field: 'sn', title: '角色编码', width: 160},
                    {fixed: 'right', title: '操作', width: 240, align: 'center', toolbar: '#barDemo'}
                ]
            ],
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            data: [
                {"id": 1, "name": "ROLE_ADMIN", "role_type": "ROLE_ADMIN", "sn": "16354651312"},
                {"id": 2, "name": "ROLE_USER", "role_type": "ROLE_USER", "sn": "3131354564545"},
                {"id": 3, "name": "ROLE_CUSTOMER", "role_type": "ROLE_CUSTOMER", "sn": "132121245454"},
                {"id": 4, "name": "ROLE_DOCTOR", "role_type": "ROLE_DOCTOR", "sn": "135132124545"},
            ],

        });
        //监听工具条
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (layEvent === 'edit') { //编辑
                layer.open({
                    type: 1,
                    title: '向&nbsp;' + data.username + '&nbsp;发送密码重置短信',
                    area: ['420px', '220px'], //宽高
                    content: senmsgcontent,
                });
            } else if (layEvent === 'del') { //删除
                layer.open({
                    type: 1,
                    title: '向&nbsp;' + data.username + '&nbsp;发送密码重置邮件',
                    area: ['420px', '220px'], //宽高
                    content: senemailcontent,
                });
            }
        });
    });
});

