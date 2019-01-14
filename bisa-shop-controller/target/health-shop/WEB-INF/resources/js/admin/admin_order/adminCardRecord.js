$(document).ready(function () {

    /*layui方面js*/
    layui.use(['form', 'table', 'element', 'laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            laydate = layui.laydate,
            table = layui.table;

        //监听提交
        form.on('submit(search1)', function (data) {
            //执行重载

            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: {
                        incontent: $('#incontent').val(),
                        searchabout: $('#searchabout').val()
                    }
                }
            });
            return false;
        });


        //刷新 按钮
        $(document).on('.btn-refresh', function () {
            window.location.reload();
        });

        //-------执行渲染--------------
        var tableIns = table.render({
            elem: '#commodityList', //指定原始表格元素选择器（推荐id选择器）
            url: 'admin/order/pagerServiceCardRecord',
            page: true,//开启分页
            limit: 10,
            limits: [10, 20, 30],
            id: 'testReload',
            cols: [
                [ //标题栏 

                    {field: 'id', title: 'ID', width: 50, align: 'center', event: 'goodsName'},
                    {field: 'cardName', title: '卡名称', width: 160, sort: true},
                    {field: 'cardNumber', title: '卡号', width: 250, sort: true, align: 'center'},
                    {field: 'activeCode', title: '激活码', width: 100, align: 'center'},
                    {field: 'cardType', title: '卡类型', width: 160, align: 'center'},
                    {field: 'account', title: '激活帐号', width: 100, sort: true, align: 'center'},
                    {field: 'count', title: '（面值）次数/月份', width: 160, sort: true, align: 'center'},
                    {field: 'createTime', title: '关联时间', width: 180, align: 'center',},
                    {field: 'cardStatus', title: '使用情况', width: 100, align: 'center'}
                ]
            ],
            done: function (res, curr, count) {
                $("[data-field = 'cardNumber']").children().each(function (index) {
                    if (index != 0) {
                        var logistics_number = $(this).text();
                        if (logistics_number == "") {
                            $(this).text("-----");
                        } else {
                            $(this).text(logistics_number);
                        }
                    }
                })

                $("[data-field='cardStatus']").children().each(function () {
                    if ($(this).text() == '0') {
                        $(this).text("未激活")
                    } else if ($(this).text() == '1') {
                        $(this).text("已激活")
                    }
                })
                $("[data-field = 'createTime']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })

            }
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            /*data: [
                { "id": "321", "orderid": "KSD1514515461SD414541", "goodscontent": "套餐A*1 </br>服务B（3个月）*1</br> 套餐B*5 套餐C*3</br>", },
            ],*/
        });

        //==============================监听工具条=================
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

        });
    });
});

function outputcents(amount) {
    amount = Math.round(((amount) - Math.floor(amount)) * 100);
    return (amount < 10 ? '.0' + amount : '.' + amount);
}


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
        oTime = oYear + '年' + getzf(oMonth) + '月' + getzf(oDay) + '日 ';//最后拼接时间
    return oTime;
};

//补0操作  
function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
} 
