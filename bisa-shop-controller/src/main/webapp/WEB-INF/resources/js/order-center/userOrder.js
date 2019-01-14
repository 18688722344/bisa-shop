//每页显示数据
var pagelimit = 6;
var orderStatus = -1;

$(document).ready(function () {

    //-->/health-shop/user/orderCenter?flag=0
    //截取url中订单参数
    var param = window.location.search;
    var value = getURLArg(param, "flag");
    if (value != null) {
        orderStatus = value;
    }

    var curPage = location.hash.replace('#!cur_page=', '');
    var pageOffset = 0;
    if (curPage != "") {
        pageOffset = (curPage - 1) * pagelimit;
    }
    if (orderStatus == null) {
        //默认加载全部订单
        orderStatus = -1;
    } else {
        //切换选项卡样式
        changeTabClass(orderStatus);
    }
    loadOrderPagerDatas(pageOffset, pagelimit);
});

// 加载订单分页数据
function loadOrderPagerDatas(pageOffset, limit) {
    var pageParam = "&page=" + pageOffset + "&limit=" + limit;
    $.ajax({
        type: "GET",
        url: "user/loadUserOrderList?order_status=" + orderStatus + pageParam,
        error: function () {
            $(".main-order-div").hide();
        },
        success: function (orderPager) {
            if (orderPager != null) {
                //加载表格数据
                showOrderData(orderPager.datas, orderPager.total);
            }
        }
    });
}

//显示订单数据
function showOrderData(orderDatas, pageTotal) {

    //先初始化，清空模板元素之后的所有元素
    var cloneOrder = $(".main-order-div:first");
    var length = $(".main-container").find(".main-order-div").length;
    cloneOrder.removeClass("dis-n");
    if (length > 1) {  //元素个数大于数据个数，清空旧数据
        $(".div-order-module").html(cloneOrder);
    }
    if (pageTotal > 0) {
        //隐藏暂无订单
        $(".no-data-div").hide();
        //显示分页
        $(".order-pager").show();
        /* 先克隆模板元素，然后修改属性值 */
        for (var index in orderDatas) {
            //订单信息
            var order = orderDatas[index].order;
            var orderDetail = orderDatas[index].orderGoodsList;

            var last_order = $(".main-order-div:last");
            /* 克隆元素拼到最后一个元素后面 */
            cloneOrder.clone().insertAfter(last_order);
            /* 获得克隆后的元素 */
            last_order = $(".main-order-div:last");
            /*订单信息*/
            showOrderInfo(last_order, order);

            var clone_detail = last_order.find(".order-detail:first");
            var detail_length = $(".main-container").find(".order-detail").length;
            if (orderDetail.length > 0) {
                for (var n in orderDetail) {
                    //订单细节，商品详情
                    var orderGoods = orderDetail[n];
                    //订单细节div
                    var last_detail = last_order.find(".order-detail:last");
                    /* 克隆元素拼到最后一个元素后面 */
                    clone_detail.clone().insertAfter(last_detail);
                    /* 获得克隆后的元素 */
                    last_detail = last_order.find(".order-detail:last");
                    //填充订单及订单细节元素
                    showOrderDetail(last_detail, orderGoods, orderGoods.goodsName, orderGoods.imgUrl);
                }
                clone_detail.remove();
            }
        }
        //隐藏模板元素，便于分页复制元素
        cloneOrder.addClass("dis-n");

        /* 加载分页 */
        layui.use('laypage', function () {
            var laypage = layui.laypage;
            /*分页*/
            laypage.render({
                elem: 'layer-pager'
                , count: pageTotal //数据总数
                , curr: location.hash.replace('#!cur_page=', '') //获取起始页
                , hash: 'cur_page' //自定义hash值
                , limit: pagelimit
                , theme: '#309DE2'
                , jump: function (obj, first) {
                    //页码 1- 10； 2-20；...
                    var pageOffset = (obj.curr - 1) * pagelimit;
                    if (!first) {
                        //一定要加此判断，否则初始时会无限刷新
                        loadOrderPagerDatas(pageOffset, pagelimit);
                    }
                }
            });
        });
    } else {
        //没有数据的时候
        //隐藏模板元素
        $(".main-order-div:first").addClass("dis-n");
        //显示暂无订单
        $(".no-data-div").show();
        //隐藏分页
        $(".order-pager").hide();
    }
}

//填充订单信息
function showOrderInfo(orderElemnt, order) {
    //订单信息 div
    var order_info = orderElemnt.find(".order-info");
    order_info.find(".order-status").text(getTradeStatus(order.tra_status));
    order_info.find(".order-status").addClass(getTradeStatusClass(order.tra_status));
    order_info.find(".order-time").text(longDateParse(order.create_time).format("yyyy-MM-dd hh:mm:ss"));
    order_info.find(".order-no").text(order.order_no);
    order_info.find(".pay-type").text(getPayTypeInfo(order.pay_type));
    order_info.find(".actual-payment").text(order.actual_payment);

    var order_detail_href = "window.location.href='" + ajax_url + "/user/orderDetail?order_no=" + order.order_no + "'";
    //订单操作
    var order_opt = orderElemnt.find(".order-opt");

    //订单详情按键
    order_opt.find(".order-detail-btn").attr("onclick", order_detail_href);

    if (order.tra_status == unpaid) {
        //立即支付按键
        // var order_pay_href = "window.location.href='" + ajax_url + "/user/payMentPage?order_no=" + order.order_no + "'";
        var order_pay_href = 'judgeGoodsRepertory(\'' + order.order_no + '\')';
        order_opt.find(".pay-btn").attr("onclick", order_pay_href);
        order_opt.find(".pay-btn").removeClass("dis-n");  //显示按键
    } else if (order.tra_status == shipped) {
        //立即收货按键
        order_opt.find(".order-opt-no").val(order.order_no);
        order_opt.find(".confirm-delivery-btn").removeClass("dis-n");
    } else if (order.tra_status == received) {
        //显示去评价  按键
        var appraise_href = "window.location.href='" + ajax_url + "/user/orderAppraise?order_no=" + order.order_no + "'";
        order_opt.find(".appraise-btn").attr("onclick", appraise_href);
        order_opt.find(".appraise-btn").removeClass("dis-n");
    } else if (order.tra_status == orderclosed) {
        //订单删除的按钮 delect-btn
        order_opt.find(".order-opt-no").val(order.order_no);
        order_opt.find(".delect-btn").removeClass("dis-n");
    }
}

//刪除订单按钮
$(".main-container").on('click', ".delect-btn", function () {
    var order_no = $(this).parent().find(".order-opt-no").val();
    var i = $(this).parents(".tab-order");

    layui.use('layer', function () {
        var layer = layui.layer;
        layer.confirm(name_233, {
            btn: [name234, name235]
        }, function () {
            $.ajax({
                url: 'user/delectOrder',
                type: "POST",
                data: {
                    "order_no": order_no
                },
                success: function (data) {
                    if (data.flag == true) {
                        layer.msg(name_236, {icon: 1, time: 1500}, function () {
                            window.location.reload();//刷新页面
                        });
                    }
                    if (data.msg == "1") {
                        layer.msg(name_237, {icon: 2, time: 2000}, function () {
                        });
                    }
                }
            });
        });
    });
});

//立即支付按钮，后台要判断库存是否够
function judgeGoodsRepertory(order_no) {
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

//填充订单商品详情
function showOrderDetail(detailElement, orderGoods, good_name, good_img) {
    var last_detail = detailElement.find(".order-detail:last");
    detailElement.find(".goods-img").attr("src", good_img);
    detailElement.find(".goods-name").text(good_name);
    detailElement.find(".goods-price").text("￥" + orderGoods.discountPrice + " x " + orderGoods.count);
}

// 确认收货
$(".main-container").on('click', ".confirm-delivery-btn", function () {
    var order_no = $(this).parent().find(".order-opt-no").val();
    var i = $(this).parents(".tab-order");

    layui.use('layer', function () {
        var layer = layui.layer;
        layer.confirm(name233, {
            btn: [name234, name235]
        }, function () {
            $.ajax({
                url: 'user/confirmDelivery',
                type: "POST",
                data: {
                    "order_no": order_no
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
});

//-------------------------------选项卡切换------------------------------------

/*选项卡点击事件*/
$(".Order-controlv1").click(function () { //全部订单
    orderStatus = -1;
    changeTabClass(orderStatus);
    loadOrderPagerDatas(0, pagelimit);
});
$(".Order-controlv2").click(function () {  //待支付
    orderStatus = unpaid;
    changeTabClass(orderStatus);
    loadOrderPagerDatas(0, pagelimit);
});
$(".Order-controlv6").click(function () { //待发货
    orderStatus = paid;
    changeTabClass(orderStatus);
    loadOrderPagerDatas(0, pagelimit);
});
$(".Order-controlv3").click(function () { //待收货
    orderStatus = shipped;
    changeTabClass(orderStatus);
    loadOrderPagerDatas(0, pagelimit);
});
$(".Order-controlv4").click(function () {  //待評價
    orderStatus = received;
    changeTabClass(orderStatus);
    loadOrderPagerDatas(0, pagelimit);
});
$(".Order-controlv5").click(function () {  //已关闭
    orderStatus = orderclosed;
    changeTabClass(orderStatus);
    loadOrderPagerDatas(0, pagelimit);
});

//修改选项卡样式
function changeTabClass(orderStatus) {
    switch (orderStatus) {
        case -1:
            $(".Order-control").removeClass("col-active");
            $(".Order-controlv1").addClass("col-active");
            break;
        case orderclosed:
            $(".Order-control").removeClass("col-active");
            $(".Order-controlv5").addClass("col-active");
            break;
        case unpaid:
            $(".Order-control").removeClass("col-active");
            $(".Order-controlv2").addClass("col-active");
            break;
        case paid:
            $(".Order-control").removeClass("col-active");
            $(".Order-controlv6").addClass("col-active");
            break;
        case shipped:
            $(".Order-control").removeClass("col-active");
            $(".Order-controlv3").addClass("col-active");
            break;
        case received:
            $(".Order-control").removeClass("col-active");
            $(".Order-controlv4").addClass("col-active");
            break;
        default:
            break;
    }
}

//根据订单状态获取中文状态
function getTradeStatus(status) {
    if (status == orderclosed) {
        return name221;
    } else if (status == unpaid) {
        return name218;
    } else if (status == paid) {
        return name238;
    } else if (status == shipped) {
        return name219;
    } else if (status == received) {
        return name239;
    } else if (status == review) {
        return name240;
    } else {
        return name241;
    }
}

//根据支付类型获取中文类型
function getPayTypeInfo(type) {
    if (type == wechatPay) {  //微信支付
        return name242;
    } else if (type == aliPay) {  //支付宝支付
        return name243;
    } else if (type == easyLink) {   //银联支付
        return name244;
    } else if (type == visaPay) {   //visa支付
        return name245;
    } else {
        return name246;
    }
}

//根据订单状态获得样式
function getTradeStatusClass(status) {
    if (status == unpaid) {
        return "col-309DE2";
    } else if (status == shipped) {
        return "col-757575";
    } else {
        return "col-309DE2";
    }
}

//long时间转成标准时间
function longDateParse(longTypeDate) {
    var date = new Date();
    date.setTime(longTypeDate);
    return date;
}

Date.prototype.format = function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var i in args) {
        var n = args[i];
        if (new RegExp("(" + i + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
    }
    return format;
};


/**
 * 获取url参数，后期可删
 * @param url 目标url
 * @param arg 目标参数名
 * @returns
 */
function getURLArg(params, arg) {
    var reg = new RegExp("(^|&)" + arg + "=([^&]*)(&|$)");
    var r = params.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
