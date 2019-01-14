$(document).ready(function () {
    /*订单详情页*/
    /*根据状态改变样式*/
    orderstate();
    orderTime();

    //查看物流的按钮
    $('#logistics').on('click', function () {
        //loading层
        var index1 = layer.load(1, {
            shade: [0.3, '#fff'] //0.5透明度的白色背景
        });
        var logistics_number = $("#logistics_number").val();
        var logistics_name = $("#logistics_name").val();
        var object;//物流的信息

        $.ajax({
            url: 'user/lookLogistics',
            type: "POST",
            async: false,
            data: {
                "logistics_number": logistics_number
            },
            success: function (data) {
                console.log(data);
                /*var obj = eval('(' + data + ')');  这行代码如果为 '' 浏览器会报错*/
                object = data;
            }
        });
        //关闭加载层
        layer.close(index1);
        //下面判斷有沒有獲取物流的信息
        if (object == '' || object==null) {
            layer.open({
                title: name_147,
                icon: 5,
                content: name_277
            });
        } else {
            var html = '<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;"><legend>物流信息</legend></fieldset><ul class="layui-timeline">';
            for (var index in object.Traces) {
                var logisticsInfo = object.Traces[index]
                html += '<li class="layui-timeline-item"><i class="layui-icon layui-timeline-axis">&#xe63f;</i><div class="layui-timeline-content layui-text"><h5 class="layui-timeline-title">' + logisticsInfo.AcceptTime + '</h5><p>' + logisticsInfo.AcceptStation + '</p></div></li>';
            }
            html += '</ul>';
            layer.open({
                type: 0,
                area: ['700px', '600px'],
                title: "物流状态信息 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;物流公司：" + logistics_name + "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;运单号：" + logistics_number + "",
                resize: true,
                anim: 5,//0-6的动画形式，-1不开启
                btn: ['我知道了'],
                content: html
            });
        }
    })

    //加载order的4个时间
    function orderTime() {
        if (name256 == "cn") {
            //yyyy年MM月dd日 HH:mm
            $(".create_time").html(getMyDate(create_time));
            $(".pay_time").html(getMyDate(pay_time));
            $(".deliver_goods_time").html(getMyDate(deliver_goods_time));
            $(".receive_goods_time").html(getMyDate(receive_goods_time));
        } else {
            $(".create_time").html(getHK_data_tring(create_time));
            $(".pay_time").html(getHK_data_tring(pay_time));
            $(".deliver_goods_time").html(getHK_data_tring(deliver_goods_time));
            $(".receive_goods_time").html(getHK_data_tring(receive_goods_time));
        }
    }

    //获得 香港要的时间格式
    function getHK_data_tring(data) {
        if (data == "") {
            return "";
        }
        var str = (new Date(data)).getTime();
        var oDate = new Date(str),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            //最后拼接时间
            oTime = getzf(oDay) + '-' + getzf(oMonth) + '-' + oYear + '     ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);
        return oTime;
    }

    //获得年月日 得到日期oTime
    function getMyDate(data) {
        if (data == "") {
            return "";
        }
        var str = (new Date(data)).getTime();
        var oDate = new Date(str),
            oYear = oDate.getFullYear(),
            oMonth = oDate.getMonth() + 1,
            oDay = oDate.getDate(),
            oHour = oDate.getHours(),
            oMin = oDate.getMinutes(),
            oSen = oDate.getSeconds(),
            //最后拼接时间
            oTime = oYear + '年' + getzf(oMonth) + '月' + getzf(oDay) + '日     ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);
        return oTime;
    };

    //补0操作  
    function getzf(num) {
        if (parseInt(num) < 10) {
            num = '0' + num;
        }
        return num;
    }

    //交易状态(0:取消订单   1：待付款，2：待发货，3：待收货  4:待评价  5：待追评
    function orderstate() {
        var orderstatenum = Number($(".order-stateinputval").val());
        switch (orderstatenum) {
            case 0:
                // 订单已关闭
                $(".order-status .status-text").text(name271);
                break;
            case 1:
                //待付款
                $(".order-status .status-text").text(name272);
                $(".bg-eee").children().eq(0).addClass("bg-83c441 radius-13 col-white");
                break;
            case 2:
                //已付款
                $(".order-status .status-text").text(name273);
                $(".bg-eee").children().eq(0).addClass("bg-83c441 radius-13l col-white");
                $(".bg-eee").children().eq(1).addClass("bg-83c441 radius-13r col-white");
                break;
            case 3:
                //已发货
                $(".order-status .status-text").text(name274);
                $(".bg-eee").children().eq(0).addClass("bg-83c441 radius-13l col-white");
                $(".bg-eee").children().eq(1).addClass("bg-83c441 col-white");
                $(".bg-eee").children().eq(2).addClass("bg-83c441 radius-13r col-white");
                break;
            case 4:
                //交易成功
                $(".order-status .status-text").text(name275);
                $(".bg-eee").children().eq(0).addClass("bg-83c441 radius-13l col-white");
                $(".bg-eee").children().eq(1).addClass("bg-83c441 col-white");
                $(".bg-eee").children().eq(2).addClass("bg-83c441 col-white");
                $(".bg-eee").children().eq(3).addClass("bg-83c441 radius-13r col-white");
                break;
            default:
                //交易成功(如果评价了  这里还是默认交易成功)
                $(".order-status .status-text").text(name275);
                $(".bg-eee").children().eq(0).addClass("bg-83c441 radius-13l col-white");
                $(".bg-eee").children().eq(1).addClass("bg-83c441 col-white");
                $(".bg-eee").children().eq(2).addClass("bg-83c441 col-white");
                $(".bg-eee").children().eq(3).addClass("bg-83c441 radius-13r col-white");
                break;
        }
        ;
    };
});

//付款按钮 查询该订单明细商品库存对比
function payButton(){
    var order_no = $("#order_no").val();
    $.ajax({
        type: "POST",
        url: "user/judgeGoodsRepertory",
        data: {
            order_no: order_no
        },
        success: function (data) {
            if (data == "200") {
                //有货
                window.location.href = "user/payMentPage?order_no=" + order_no;
            } else if (data == "300") {
                //没有货了
                layer.open({
                    title: name_147,
                    icon: 5,
                    content: name_245
                });
            } else if (data = "400") {
                //下架了
                layer.open({
                    title: name_147,
                    icon: 5,
                    content: name_246
                });
            }
        }
    });
}


//确认收货
$(".confirm-delivery-btn").click(function () {
    var order_no = $("#order_no").val();
    var i = $(this).parents(".tab-order");

    layer.confirm(name233, {
        btn: [name234, name235]
    }, function () {
        $.ajax({
            url: 'user/confirmDelivery',
            type: "POST",
            data: {
                "order_no": order_no,
            },
            success: function (data) {
                if (data.flag == true) {
                    layer.msg(name236, {icon: 1, time: 2000}, function () {
                        window.location.href = "user/orderDetail?order_no=" + order_no;
                    });
                }
                if (data.msg == "1") {
                    layer.msg(name237, {icon: 2, time: 2000}, function () {
                        window.location.href = "user/orderDetail?order_no=" + order_no;
                    });
                }
            }
        });
    });
});

//取消订单
function cancelOrder() {
    var order_no = $("#order_no").val();
    layer.confirm(name276, {
        // 按钮
        btn: [name234, name235]
    }, function () {
        $.ajax({
            url: "user/cancelOrder",
            type: 'POST',
            data: {
                "order_no": order_no
            },
            success: function (data) {
                if (data.flag == true) {
                    layer.msg(name277, {icon: 1, time: 2000}, function () {
                        window.location.href = "user/orderDetail?order_no=" + order_no;
                    });
                }
            }
        })
    });
}