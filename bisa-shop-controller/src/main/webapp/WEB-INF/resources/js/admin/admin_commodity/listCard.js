$(document).ready(function () {

    /*layui方面js*/
    layui.use(['form', 'table', 'element', 'laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            laydate = layui.laydate,
            table = layui.table;

        //=============================改变按钮的颜色=====================================
        $(document).on('click', '.btn-sidebar', function () {
            $(".btn-sidebar").removeClass("layui-btn-primary");
            $(this).siblings().addClass("layui-btn-primary");
            var val = $(this).val();
            //执行重载
            table.reload('loadServiceCardList', {
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

        /*查找部分的提交*/
        form.on('submit(search1)', function (data) {
            var searchabout = data.field.searchabout;
            var incontent = null;
            switch (searchabout) {
                case "1":
                    incontent = data.field.incontent;
                    break;

            }
            //执行重载
            table.reload('loadServiceCardList', {
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
        //刷新 按钮
        $(document).on('click', '.btn-refresh', function () {
            window.location.reload();
        });

        //-------执行渲染--------------
        var tableIns = table.render({
            elem: '#loadServiceCardList', //指定原始表格元素选择器（推荐id选择器）
            url: 'admin/commodity/loadServiceCardList',
            page: true,//开启分页
            limit: 10,
            limits: [10, 20, 30],
            cols: [
                [ //标题栏 
                    {field: 'cardName', title: '卡名称', width: 150, align: 'center'},
                    {field: 'cardNumber', title: '卡号', width: 250, align: 'center', event: 'goodsName'},
                    {field: 'activeCode', title: '激活码', width: 130, sort: true},
                    {field: 'cardType', title: '服务类型（次数型/时限型）', width: 100, sort: true, align: 'center', templet: '#cardTypeTpl'},
                    {field: 'serviceToken', title: '服务编码', width: 250, align: 'center'},
                    {field: 'count', title: '（面值）次数/月份', width: 150, align: 'center'},
                    {field: 'cardStatus', title: '是否售出', width: 100, sort: true, align: 'center'},
                    {field: 'goodsNumber', title: '商品编码', width: 250, sort: true, align: 'center'},
                ]
            ],
            done: function (res, curr, count) {
                $("[data-field='cardStatus']").children().each(function () {
                    if ($(this).text() == '0') {
                        $(this).text("未售出")
                    } else if ($(this).text() == '1') {
                        $(this).text("已售出")
                    }
                });
            }
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            /*data: [
                { "id": "321", "orderid": "KSD1514515461SD414541", "goodscontent": "套餐A*1 </br>服务B（3个月）*1</br> 套餐B*5 套餐C*3</br>", },
            ],*/
        });
    });
});


function outputcents(amount) {
    amount = Math.round(((amount) - Math.floor(amount)) * 100);
    return (amount < 10 ? '.0' + amount : '.' + amount);
}
