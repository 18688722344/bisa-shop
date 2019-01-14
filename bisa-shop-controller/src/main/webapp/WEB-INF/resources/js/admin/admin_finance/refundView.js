$(document).ready(function () {
    /*layui方面js*/
    layui.use(['form', 'table', 'element', 'laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            laydate = layui.laydate,
            table = layui.table;
        $ = layui.jquery;

        //=============================改变按钮的颜色=====================================
        $(document).on('click', '.btn-sidebar', function () {
            $(".btn-sidebar").removeClass("layui-btn-primary");
            $(this).siblings().addClass("layui-btn-primary");
            var val = $(this).val();
            //执行重载
            table.reload('tradelist', {
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
                    $("#ipt_year").siblings(".sel_value").val("");
                    $("#ipt_year").show();
                    $("#ipt_year").attr("lay-verify", "required");
                    break;
                case 3:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_month").siblings(".sel_value").val("");
                    $("#ipt_month").show();
                    $("#ipt_month").attr("lay-verify", "required");
                    break;
                case 4:
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

        //=======================监听提交======================
        form.on('submit(search1)', function (data) {
            var searchabout = data.field.searchabout;
            var incontent = null;
            switch (searchabout) {
                case "1":
                    incontent = data.field.n_transnum;
                    break;
                case "2":
                    incontent = data.field.n_year;
                    break;
                case "3":
                    incontent = data.field.n_month;
                    break;
                case "4":
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

        /*设置(财务 售后审核 通过)部分的提交*/
        form.on('submit(check_success)', function (data) {
            var orderNO = $(".detail_waybillnum1").html();
            var userId = $(".user_id").val();
            var description = data.field.description;
            var textarea = data.field.textarea;

            layer.closeAll();
            layer.confirm('确认审核通过!', {icon: 3, title: '确认审核通过', btn: ['确认', '我再想想']}, function (index) {
                $.ajax({
                    url: 'admin/finance/financeCheckSuccess',
                    data: {
                        orderNO: orderNO,
                        userId: userId,
                        description: description,
                        textarea: textarea
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('审核通过 成功!', {icon: 6, time: 1000}, function () {
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
        /*设置(财务 售后审核 拒绝)部分的提交*/
        form.on('submit(check_fail)', function (data) {
            var orderNO = $(".detail_waybillnum1").html();
            var userId = $(".user_id").val();
            var description = data.field.description;
            var textarea = data.field.textarea;

            layer.closeAll();
            layer.confirm('确认审核拒绝!', {icon: 3, title: '确认审核拒绝', btn: ['确认', '我再想想']}, function (index) {
                $.ajax({
                    url: 'admin/finance/financeCheckFail',
                    data: {
                        orderNO: orderNO,
                        userId: userId,
                        description: description,
                        textarea: textarea
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('审核拒绝  成功!', {icon: 6, time: 1000}, function () {
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
        //===================执行渲染==============
        var tableIns = table.render({
            elem: '#tradelist', //指定原始表格元素选择器（推荐id选择器）
            url: "admin/finance/tradeRefundList",
            limit: 10,
            cols: [
                [ //标题栏
                    {type: 'numbers'},
                    {field: 'id', title: 'ID', width: 100, event: 'id', sort: true, align: 'center'},
                    {field: 'user_guid', title: '用户ID', width: 80, event: 'user_guid', align: 'center'},
                    {field: 'trade_no', title: '交易号', width: 230, align: 'center'},
                    {field: 'order_no', title: '订单号', width: 230, align: 'center'},
                    {
                        field: 'pay_status',
                        title: '退款状态',
                        width: 130,
                        align: 'center',
                        sort: true,
                        style: 'background-color: #FDF5E6;',
                        templet: '#statusTpl'
                    },

                    {field: 'refund_price', title: '退款金额', width: 120, align: 'center', sort: true},
                    {field: 'refund_time', title: '退款时间', width: 200, align: 'center', sort: true},
                    {fixed: 'right', title: '操作', width: 180, align: 'center', toolbar: '#barDemo'}
                ]
            ],
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            page: true,
            done: function (res, curr, count) {
                $("[data-field = 'refund_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
                //格式化 金额
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

        //==============================监听工具条=================
        table.on('tool(tradelist_filter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象


            //加载用户订单下面的所有商品信息
            if (obj.event === 'id') {
                var dataId = data.id;
                var goodscontent;
                $.ajax({
                    url: 'admin/finance/goodscontent',
                    data: {
                        tradeId: dataId
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
                    content += goodscontent[index].goods_name + "  *  " + goodscontent[index].count + "</br>"
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
            //查看  用户售后 的原因
            if (layEvent === 'detailASA') {
                var dataId = data.id;
                var userGuid = data.user_guid;
                var orderNo = data.order_no;
                var goodscontent;//售后的原因
                $.ajax({
                    url: 'admin/order/AfterSalesCause',
                    data: {
                        userId: userGuid,
                        orderNo: orderNo
                    },
                    async: false,
                    type: "POST",
                    success: function (data) {
                        goodscontent = data;
                    }
                });
                //设值
                $(".uriPic1").attr("src", goodscontent.uriPic);
                $(".detail_consignee1").html(goodscontent.username);
                $(".detail_type").html(toAfterSalesType(goodscontent.afterSalesType));
                $(".detail_orderNO").html(goodscontent.orderNo);
                $(".detail_cause").html(goodscontent.afterForCause);
                $(".detail_time1").html(goodscontent.createTime);
                layer.open({
                    type: 1,
                    title: "ID为 " + data.id + " 的基本信息",
                    area: ['500px', '580px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent1'),
                });
            }

            //加载用户的信息
            if (obj.event === 'user_guid') {
                var user_id = data.user_guid;
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
                    content: $('.detailcontent2'),
                });
            }

            //(退款审核按钮)
            if (layEvent === 'check') {
                $(".user_id").val(data.user_guid);//用户的id
                $(".detail_waybillnum1").html(data.order_no);//订单号
                layer.open({
                    type: 1,
                    title: "审核订号为  " + data.order_no + " 的信息",
                    area: ['500px', '420px'], //宽高
                    content: $('.detailwaybill1'),
                });
            }
        });
    });
});

function toAfterSalesType(data) {
    if (data == exchange) {
        return "换货";
    } else if (data == maintain) {
        return "维修";
    } else if (data == refund) {
        return "退款";
    }
}

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