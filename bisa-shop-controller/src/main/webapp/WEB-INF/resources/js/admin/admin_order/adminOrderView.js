$(document).ready(function () {
    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
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
        $(".btn-refresh").click(function () {
            window.location.reload();
        });

        //执行渲染
        var tableIns = table.render({
            elem: '#orderlist', //指定原始表格元素选择器（推荐id选择器）
            url: 'admin/order/selectOrderList',
            page: true,//开启分页
            limit: 10,
            id: 'testReload',
            cols: [
                [ //标题栏 
                    {field: 'id', title: 'ID', width: 60, event: 'id', sort: true, align: 'center'},
                    {field: 'order_no', title: '订单号', width: 250, align: 'center'},
                    {field: 'user_id', title: '购买人', width: 80, event: 'user_id', align: 'center'},

                    {
                        field: 'tra_status',
                        title: '支付状态',
                        width: 110,
                        sort: true,
                        style: 'background-color: #FDF5E6;',
                        align: 'center',
                        templet: '#traStatusTpl'
                    },
                    {field: 'pay_type', title: '支付类型', width: 110, sort: true, align: 'center', templet: '#payTypeTpl'},
                    {field: 'create_time', title: '下单时间', width: 200, align: 'center'},
                    {field: 'pay_time', title: '支付时间', width: 200, align: 'center'},

                    {field: 'consignee', title: '收货人', width: 100, align: 'center'},
                    {field: 'phone', title: '收货人手机', width: 130, align: 'center'},
                    {field: 'detail_address', title: '收货地址', width: 300, event: 'detail_address'},

                    {field: 'total_price', title: '商品总额', width: 120},
                    {field: 'preferential_price', title: '优惠价格', width: 120},
                    {field: 'post_price', title: '邮费', width: 120},
                    {field: 'actual_payment', title: '实付款', width: 120},

                    {field: 'orderEmail', title: '账单邮箱', width: 200, align: 'center'/*, templet:'#orderEmailTpl'*/},
                    {field: 'logisticsAccount', title: '物流公司付费账号', width: 170, align: 'center'/*, templet:'#logisticsAccountTpl'*/},

                    {field: 'logistics_name', title: '物流公司', width: 260, align: 'center'},
                    {field: 'logistics_number', title: '运单号', width: 200, align: 'center'},
                    {field: 'deliver_goods_time', title: '发货时间', width: 200, align: 'center'},
                    {field: 'receive_goods_time', title: '收货时间', width: 200, align: 'center'},
                    {
                        field: 'after_sale_apply',
                        title: '售后状态',
                        width: 150,
                        sort: true,
                        style: 'background-color: #FDF5E6;',
                        align: 'center',
                        templet: '#afterSaleApplyTpl'
                    },
                ]
            ],
            //转义 数据渲染完的回调
            done: function (res, curr, count) {
                $("[data-field = 'logistics_name']").children().each(function (index) {
                    if (index != 0) {
                        var logistics_name = $(this).text();
                        if (logistics_name == "") {
                            $(this).text("-----");
                        } else {
                            $(this).text(logistics_name);
                        }
                    }
                });
                $("[data-field = 'orderEmail']").children().each(function (index) {
                    if (index != 0) {
                        var orderEmail = $(this).text();
                        if (orderEmail == "") {
                            $(this).text("-----");
                        } else {
                            $(this).text(orderEmail);
                        }
                    }
                });
                $("[data-field = 'logisticsAccount']").children().each(function (index) {
                    if (index != 0) {
                        var logisticsAccount = $(this).text();
                        if (logisticsAccount == "") {
                            $(this).text("-----");
                        } else {
                            $(this).text(logisticsAccount);
                        }
                    }
                });
                $("[data-field = 'logistics_number']").children().each(function (index) {
                    if (index != 0) {
                        var logistics_number = $(this).text();
                        if (logistics_number == "") {
                            $(this).text("-----");
                        } else {
                            $(this).text(logistics_number);
                        }
                    }
                });
                $("[data-field = 'total_price']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                });
                $("[data-field = 'preferential_price']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                });
                $("[data-field = 'post_price']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                });
                $("[data-field = 'actual_payment']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                });
                $("[data-field = 'create_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                });
                $("[data-field = 'pay_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                });
                $("[data-field = 'deliver_goods_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                });
                $("[data-field = 'receive_goods_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                });
            }
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            /*data: [
                { "id": "321", "orderid": "KSD1514515461SD414541", "goodscontent": "套餐A*1 </br>服务B（3个月）*1</br> 套餐B*5 套餐C*3</br>", },
            ],*/
        });
        //=========================================下面 弹出框===============================
        //监听单元格事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;

            //加载用户订单下面的所有商品信息
            if (obj.event === 'id') {
                var dataId = data.id;
                var goodscontent;
                $.ajax({
                    url: 'admin/order/goodscontent',
                    data: {
                        orderId: dataId
                    },
                    type: "POST",
                    async: false,
                    success: function (data) {
                        goodscontent = data;
                    }
                });
                //"套餐A*1 </br>服务B（3个月）*1</br> 套餐B*5
                var content = "";//格式化  数据
                for (var index in goodscontent) {
                    content += goodscontent[index].goodsName + "  *  " + goodscontent[index].count + "</br>"
                }
                //设值
                $(".productName").html(content);
                layer.open({
                    type: 1,
                    title: "ID为 " + data.id + " 的商品基本信息",
                    area: ['500px', '450px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent1'),
                });
            }
            //加载用户的信息
            if (obj.event === 'user_id') {
                var user_id = data.user_id;
                var userinfo;//userinfo对象信息
                //上面不加载 用户的信息，点击以后在加载  减小压力
                $.ajax({
                    url: 'admin/order/userInfo',
                    data: {
                        userId: user_id
                    },
                    type: "POST",
                    async: false,
                    success: function (data) {
                        userinfo = data;
                    }
                });
                //设值
                $(".uriPic").attr("src", userinfo.uriPic);
                $(".name").html(userinfo.name);
                $(".sex").html(getSex(userinfo.sex));
                $(".age").html(userinfo.age);
                $(".weight").html(userinfo.weight);
                $(".height").html(userinfo.height);
                $(".birthday").html(userinfo.birthday);
                layer.open({
                    type: 1,
                    title: "ID为 " + data.id + " 的用户基本信息",
                    area: ['500px', '620px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent'),
                });
            }
            if (obj.event === 'detail_address') {
                layer.alert(data.detail_address, {
                    title: '订单ID 为 &nbsp;' + data.id + '&nbsp; 的收货地址',
                });
            }
        });
    });
});

//判断用户男还是女的枚举
function getSex(data) {
    if (data == "MALE") {
        return "男";
    }
    if (data == "FEMALE") {
        return "女";
    }
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
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear + '年' + getzf(oMonth) + '月' + getzf(oDay) + '日 ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
    return oTime;
};

//补0操作  
function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}

//将数字转换成金额显示
function outputmoney(number) {
    number = number.replace(/\,/g, "");
    if (isNaN(number) || number == "") return "";
    number = Math.round(number * 100) / 100;
    if (number < 0)
        return '-' + outputdollars(Math.floor(Math.abs(number) - 0) + '') + outputcents(Math.abs(number) - 0);
    else
        return "HKD" + outputdollars(Math.floor(number - 0) + '') + outputcents(number - 0);
}

//格式化金额
function outputdollars(number) {
    if (number.length <= 3)
        return (number == '' ? '0' : number);
    else {
        var mod = number.length % 3;
        var output = (mod == 0 ? '' : (number.substring(0, mod)));
        for (i = 0; i < Math.floor(number.length / 3); i++) {
            if ((mod == 0) && (i == 0))
                output += number.substring(mod + 3 * i, mod + 3 * i + 3);
            else
                output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
        }
        return (output);
    }
}

function outputcents(amount) {
    amount = Math.round(((amount) - Math.floor(amount)) * 100);
    return (amount < 10 ? '.0' + amount : '.' + amount);
}