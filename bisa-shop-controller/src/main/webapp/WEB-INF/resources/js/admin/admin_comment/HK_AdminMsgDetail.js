$(document).ready(function () {

    //获取地址栏,留言表id
    var url = document.location.href;
    var id = url.split("=")[1];

    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;

        //监听提交
        form.on('submit(search1)', function (data) {
            var textarea = data.field.textarea;
            var guestbookId = $(".guestbookId").val();

            layer.closeAll();
            layer.confirm('确认留言备注!', {icon: 3, title: '确认留言备注!', btn: ['确认', '我再想想']}, function (index) {
                $.ajax({
                    url: 'admin/comment/leaveMsgRemark',
                    data: {
                        guestbookId: guestbookId,
                        textarea: textarea
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('备注 成功!', {icon: 6, time: 1000}, function () {
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
        //------添加备注HTML页面的弹出框---------
        $(document).on('click', '.addremark', function () {
            layer.open({
                type: 1,
                title: "留言备注",
                area: ['500px', '500px'], //宽高
                content: $('.detailwaybill'),
            });
        });
        //执行渲染
        table.render({
            elem: '#remarklist', //指定原始表格元素选择器（推荐id选择器）
            url: 'admin/comment/guestBookRemarkList?guestbookId=' + id,
            limit: 10,
            page: true,
            cols: [
                [ //标题栏
                    {type: 'numbers'},
                    {field: 'remark_name', title: '备注人', width: 140, align: 'center'},
                    {field: 'remark_content', title: '备注内容', width: 400, event: 'remark_content', align: 'center'},
                    {field: 'create_time', title: '备注时间', width: 200, align: 'center'},
                ]
            ],
            done: function (res, curr, count) {
                $("[data-field = 'create_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
            }
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            /*data: [
                { "id": 10000, "about": "已经解决用户反映的问题。", "time": "2017/10/12 9:55:52","remarkuser": "小王","state": "未回访",},
            ],*/
        });
        //监听工具条
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (obj.event === 'remark_content') {
                layer.alert(data.remark_content, {
                    title: '时间为 &nbsp;' + data.time + '&nbsp; 的备注信息',
                });
            }
        });
    });
});

//获得年月日      得到日期oTime  
function getMyDate(data) {
    if (data == "" || data == null) {
        return "- - - - - -";
    }
    data = parseInt(data);
    var oldTime = (new Date(data)).getTime(); //得到毫秒数
    var oDate = new Date(oldTime);
    oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear + '年' + getzf(oMonth) + '月' + getzf(oDay) + '日     ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
    return oTime;
};

//补0操作  
function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
} 