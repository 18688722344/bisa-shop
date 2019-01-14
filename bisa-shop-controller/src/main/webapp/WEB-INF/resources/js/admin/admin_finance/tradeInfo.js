$(document).ready(function () {

    /*layui方面js*/
    layui.use(['form', 'table', 'element', 'laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            laydate = layui.laydate,
            table = layui.table;
        $ = layui.jquery;

        /*日期部分*/
        /*按年查询*/
        laydate.render({
            elem: '#ipt_year',
            type: 'year',
            max: 0
        });
        /*按月查询*/
        laydate.render({
            elem: '#ipt_month',
            type: 'month',
            max: 0
        });
        /*按日查询*/
        laydate.render({
            elem: '#ipt_day',
            max: 0
        });

        /*下拉框选择之后的联动*/
        form.on('select(sle_type_f)', function (data) {
            switch (parseInt(data.value)) {
                case 1:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_transnum").siblings(".sel_value").val("");
                    $("#ipt_transnum").show();
                    $("#ipt_transnum").attr("lay-verify", "required");
                    break;
                case 2:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_orderno").siblings(".sel_value").val("");
                    $("#ipt_orderno").show();
                    $("#ipt_orderno").attr("lay-verify", "required");
                    break;
                case 3:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_year").siblings(".sel_value").val("");
                    $("#ipt_year").show();
                    $("#ipt_year").attr("lay-verify", "required");
                    break;
                case 4:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_month").siblings(".sel_value").val("");
                    $("#ipt_month").show();
                    $("#ipt_month").attr("lay-verify", "required");
                    break;
                case 5:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_day").siblings(".sel_value").val("");
                    $("#ipt_day").show();
                    $("#ipt_day").attr("lay-verify", "required");
                    break;
                default:
                    break;
            }

        });

        //==============================监听提交=====================
        form.on('submit(search1)', function (data) {
            var searchabout = data.field.searchabout;
            var incontent = null;
            switch (searchabout) {
                case "1":
                    incontent = data.field.n_transnum;
                    break;
                case "2":
                    incontent = data.field.n_orderno;
                    break;
                case "3":
                    incontent = data.field.n_year;
                    break;
                case "4":
                    incontent = data.field.n_month;
                    break;
                case "5":
                    incontent = data.field.n_day;
                    break;
            }
            tableIns.reload({
                where: {
                    searchabout: searchabout,
                    incontent: incontent
                }, page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });
        //刷新 按钮
        $(document).on('click', '.btn-refresh', function () {
            window.location.reload();
        });
        //=========================执行渲染====================
        var tableIns = table.render({
            elem: '#tradelist', //指定原始表格元素选择器（推荐id选择器）
            url: "admin/finance/tradeview",
            limit: 10,
            cols: [
                [ //标题栏
                    {field: 'id', title: 'ID', width: 80, event: 'id', sort: true, align: 'center'},
                    {field: 'user_guid', title: '用户ID', width: 80, event: 'user_guid', align: 'center'},
                    {field: 'order_no', title: '订单号', width: 250, align: 'center'},
                    {field: 'trade_no', title: '交易号', width: 250, align: 'center'},

                    {field: 'price', title: '交易金额', width: 120, sort: true, align: 'center'},
                    {field: 'pay_type', title: '支付方式', width: 100, sort: true, align: 'center', templet: '#pay_typeTpl'},
                    {
                        field: 'pay_status',
                        title: '状态',
                        width: 100,
                        sort: true,
                        style: 'background-color: #FDF5E6;',
                        align: 'center',
                        templet: '#pay_statusTpl'
                    },
                    {field: 'pay_location', title: '支付地址', width: 100, sort: true, align: 'center', templet: '#pay_locationTpl'},

                    {field: 'create_time', title: '支付时间', width: 200, align: 'center'},
                    {field: 'refund_price', title: '退款金额', width: 120, sort: true, align: 'center'},
                    {field: 'refund_time', title: '退款时间', width: 200, align: 'center'},
                ]
            ],
            page: true,
            done: function (res, curr, count) {
                $("[data-field = 'create_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
                $("[data-field = 'refund_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
                //格式化 金额
                $("[data-field = 'price']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                })
                $("[data-field = 'refund_price']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        if (money == 0) {
                            $(this).text("----");
                        } else {
                            $(this).text(outputmoney(money));
                        }
                    }
                })
            }
        });

        //====================监听单元格事件=======================
        table.on('tool(tradelist_filter)', function (obj) {
            var data = obj.data;

            //加载用户订单下面的所有商品信息
            if (obj.event === 'id') {
                var orderNo = data.order_no;
                var goodscontent;
                $.ajax({
                    url: 'admin/finance/orderGoodsNames',
                    data: {
                        orderNo: orderNo
                    },
                    type: "GET",
                    async: false,
                    success: function (orderGoods) {
                        if (orderGoods != null) {
                            //"套餐A*1 </br>服务B（3个月）*1</br> 套餐B*5
                            var content = "";//格式化  数据
                            for (var index in orderGoods) {
                                content += orderGoods[index].goodsName + "  *  " + orderGoods[index].count + "</br>"
                            }
                            //设值
                            $(".productName").html(content);
                            layer.open({
                                type: 1,
                                title: "ID为 " + data.id + " 的商品基本信息",
                                area: ['500px', '450px'], //宽高
                                btn: ['我知道了'],
                                content: $('.detailcontent3'),
                            });
                        }
                    }
                });
            }
            //加载用户的信息
            if (obj.event === 'user_guid') {
                var user_id = data.user_guid;
                //上面不加载 用户的信息，点击以后在加载  减小压力
                $.ajax({
                    url: 'admin/order/userInfo',
                    data: {
                        userId: user_id
                    },
                    type: "GET",
                    async: false,
                    success: function (userinfo) {
                        if (userinfo == null) {
                            layer.msg("该用户无个人信息！");
                        } else {
                            //设值
                            $(".name").html(userinfo.name);
                            $(".sex").html(getSex(userinfo.sex));
                            $(".age").html(userinfo.age);
                            layer.open({
                                type: 1,
                                title: "ID为 " + data.user_guid + " 的用户基本信息",
                                area: ['500px', '620px'], //宽高
                                btn: ['我知道了'],
                                content: $('.detailcontent2'),
                            });
                        }
                    }
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
    if (data == "") {
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

//============================将数字转换成金额显示============================
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