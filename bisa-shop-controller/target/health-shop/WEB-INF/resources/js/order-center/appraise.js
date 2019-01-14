var orderNo = null;
$(document).ready(function () {
    var param = window.location.search;
    orderNo = getURLArg(param, "order_no");

    //加载订单所有商品信息
    loadOrderProducts(orderNo);

    //填充所有的星星，默认五星
    $(".star-tips").addClass("bg-starsel");
    $(".rate-value").val(5);

});

//加载订单所有商品信息
function loadOrderProducts(orderNo) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "user/orderAppraiseRest?order_no=" + orderNo,
        success: function (data) {
            if (data != null) {
                //加载表格数据
                showOrderProducts(data);
            } else {
                showEmpty();
            }
        }
    });
}

//动态加载元素
function showOrderProducts(datas) {
    if (datas.length == 0) {
        showEmpty();
        return;
    }
    var module = $(".module-div:first");

    for (var index in datas) {
        var data = datas[index];

        var last_div = $(".module-div:last");
        /* 克隆元素拼到最后一个元素后面 */
        module.clone().insertAfter(last_div);
        last_div = $(".module-div:last");

        last_div.find(".goods-img").attr("src", data.imgUrl);
        last_div.find(".goods-name").text(data.goodsName);
        last_div.find(".goods-number").val(data.goodsNumber);
    }
    //删除模板元素
    module.remove();
}

//数据加载失败
function showEmpty() {
    layer.msg("数据加载失败！", {icon: 5, time: 1500}, function () {
    });
}

//发表评论 按键点击事件
$("#submit-appraise").click(function () {
    var appraiseArray = new Array();
    var appraiseObject = null;
    var targetDiv = $(".main-container").find(".module-div");
    targetDiv.each(function () {
        var goodsNumber = $(this).find(".goods-number").val();
        var appraise = $(this).find(".appraises-one").val();
        var rateValue = $(this).find(".rate-value").val();
        appraiseObject = {goodsNumber: goodsNumber, appraise: appraise, rateValue: rateValue};
        appraiseArray.push(appraiseObject);
    });
    //提交表单
    commitAppraise(appraiseArray, orderNo);
});

//发表评论,提交表单
function commitAppraise(appraiseArray, order_no) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "user/commitAppraise",
        data: {
            appraise_data: JSON.stringify(appraiseArray),
            order_no: orderNo
        },
        success: function (data) {
            if (data == "200") {
                layer.msg(name213, {icon: 6, time: 1500}, function () {
                    window.location.href = "user/orderCenter";
                });
            } else {
                layer.msg(name537, {icon: 5, time: 1500});
            }
        }
    });
}

/*点击星星事件*/
$(".main-container").on('click', ".star-tips", function () {
    var thisindex = $(this).index() + 1;
    //赋值
    $(this).siblings(".rate-value").val(thisindex);
    $(this).siblings(".star-tips").removeClass("bg-starsel");
    //填充星星
    $(this).addClass("bg-starsel");
    $(this).prevAll().addClass("bg-starsel");

    var recordfaceval = $(this).siblings(".rate-value").val();
});


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