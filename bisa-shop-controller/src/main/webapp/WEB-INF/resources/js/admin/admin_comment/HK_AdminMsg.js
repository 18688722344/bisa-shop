$(document).ready(function () {

    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;

        //刷新 按钮
        $(document).on('click', '.btn-refresh', function () {
            window.location.reload();
        });

        //=============================改变按钮的颜色=====================================
        $(document).on('click', '.btn-sidebar', function () {
            $(".btn-sidebar").removeClass("layui-btn-primary");
            $(this).siblings().addClass("layui-btn-primary");
            var val = $(this).val();
            //执行重载
            table.reload('commentlist', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: {
                        val: val
                    }
                }
            });
        });
        //监听提交
        form.on('submit(search1)', function (data) {
            var searchabout = data.field.searchabout;
            var incontent = data.field.incontent;
            //执行重载
            table.reload('commentlist', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: {
                        searchabout: searchabout,
                        incontent: incontent
                    }
                }
            });
            return false;
        });

        /*(留言处理) 部分的提交*/
        form.on('submit(pending)', function (data) {
            var guestbookId = $("#guestbookId").val();
            var textarea = data.field.textarea;
            var HandleWay = data.field.HandleWay;
            var LeaveMsgTpye = data.field.LeaveMsgTpye;

            layer.closeAll();
            layer.confirm('确认处理留言!', {icon: 3, title: '确认处理留言!', btn: ['确认', '我再想想']}, function (index) {
                $.ajax({
                    url: 'admin/comment/handleGuestbook',
                    data: {
                        guestbookId: guestbookId,
                        HandleWay: HandleWay,
                        LeaveMsgTpye: LeaveMsgTpye,
                        textarea: textarea
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('处理留言 成功!', {icon: 6, time: 1000}, function () {
                                window.location.reload();
                            });
                        }
                        if (data.flag == false) {
                            layer.msg('系统故障!', {icon: 5, time: 1000}, function () {
                                window.location.reload();
                            });
                        }
                    }
                });
            });
            return false;
        });

        //=========执行渲染===========
        table.render({
            elem: '#commentlist', //指定原始表格元素选择器（推荐id选择器）
            url: 'admin/comment/leaveMsgList',
            limit: 10,
            page: true,
            cols: [
                [ //标题栏
                    {type: 'numbers'},
                    {field: 'name', title: '姓名', width: 110, align: 'center'},
                    {field: 'phone', title: '电话', width: 140, align: 'center'},
                    {field: 'mail', title: '邮箱', width: 200, align: 'center'},

                    {field: 'message', title: '留言内容', width: 200, event: 'message', align: 'center'},
                    {field: 'message_time', title: '留言时间', width: 200, align: 'center'},
                    {field: 'message_type', title: '留言类型', width: 140, align: 'center', templet: '#message_type'},

                    {field: 'handle_way', title: '处理方式', width: 100, align: 'center', templet: '#handle_way'},
                    {field: 'handle_status', title: '处理状态', width: 100, align: 'center', templet: '#handle_status'},
                    {field: 'handle_time', title: '处理时间', width: 200, align: 'center'},

                    {field: 'reply_content', title: '回复内容', width: 200, event: 'reply_content', align: 'center'},
                    {field: 'reply_name', title: '回复人', width: 110, align: 'center'},
                    {fixed: 'right', title: '查看详细', width: 150, align: 'center', toolbar: '#barDemo'}
                ]
            ],
            done: function (res, curr, count) {
                $("[data-field = 'handle_time']").children().each(function () {
                    if ($(this).text() == "") {
                        $(this).text("--------");
                    }
                })
                $("[data-field = 'reply_name']").children().each(function () {
                    if ($(this).text() == "") {
                        $(this).text("--------");
                    }
                })
                $("[data-field = 'reply_content']").children().each(function () {
                    if ($(this).text() == "") {
                        $(this).text("--------");
                    }
                })
            }
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            /* data: [
                 { "id": 10000, "name": "叶晓晓", "phone": "18546684415", "email": "15515@hk.com","about": "在使用过程中遇到了一个问题，请帮我解决一下。", "time": "2017/10/12 9:55:52", "newremark": "用户说明了问题我们解决了问题。","state": "未回访",},
             ]*/
        });
        //监听工具条
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (obj.event === 'message') {
                layer.alert(data.message, {
                    title: '留言用户名 为 &nbsp;' + data.name + '&nbsp; 的留言内容',
                });
            }
            if (obj.event === 'reply_content') {
                if (data.reply_content == undefined) {
                    return "";
                }
                layer.alert(data.reply_content, {
                    title: '留言回复用户名 为 &nbsp;' + data.name + '&nbsp; 的回复内容',
                });
            }
            //--------查看详细-------------
            if (layEvent === 'detail') {
                window.location.href = "admin/comment/leaveDetailMsg?id=" + data.id;
            }
            //------待处理--------------
            if (layEvent === 'pending') {
                $("#guestbookId").val(data.id);//留言表的id
                $(".name").html(data.name);
                $(".phone").html(data.phone);
                $(".mail").html(data.mail);
                $(".message").html(data.message);
                $(".message_time").html(data.message_time);
                layer.open({
                    type: 1,
                    title: "处理用户名 为 &nbsp;" + data.name + "&nbsp; 的留言",
                    area: ['500px', '730px'], //宽高
                    content: $('.detailwaybill1'),
                });
            }
        });
    });
});