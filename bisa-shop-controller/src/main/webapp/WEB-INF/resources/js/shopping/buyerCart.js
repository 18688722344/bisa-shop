$(document).ready(function () {
    loadCartAjax();
    //页面加载 ,复选框默认全选
    chooseAll(self);
});

/* ajax加载购物车所有商品数据 */
function loadCartAjax() {
    $.ajax({
        url: 'web/call/loadBuyerCartJson',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            if (data.code != 200) {
                showEmptyCart();
                return data.code;
            } else {
                /* 加载购物车数据到页面 */
                showCartItem(data.shopCarts);
                //计算总价
                countalltips();
            }
        }
    });
}

/* 加载购物车数据到页面 */
function showCartItem(listCart) {
    /* 购物车为空 */
    if (listCart == null || listCart.length == 0) {
        showEmptyCart();
    } else {
        /*整理分类数据 */
        for (var index in listCart) {
            var cartItem = listCart[index];
            if (cartItem instanceof Array) {  //套餐
                var firstDiv = $(".cart-pack-item:first"); //克隆模板元素
                showPackProduct(firstDiv, cartItem);
            } else {  //单品
                var firstDiv = $(".car-pro-item:first"); //克隆模板元素
                showSingleProduct(firstDiv, cartItem);
            }
        }
        /* 隐藏单品模板div */
        $(".car-pro-item:first").remove();
        /* 隐藏套餐模板div */
        $(".cart-pack-item:first").remove();
    }
}

//显示购物车为空
function showEmptyCart() {
    $(".is-emptycart-side").show();
    /* 隐藏单品模板div */
    $(".car-pro-item").remove();
    /* 隐藏套餐模板div */
    $(".cart-pack-item").remove();
}

/* 遍历购物车中的单品 */
function showSingleProduct(firstDivElement, singleProducts) {
    var goods = singleProducts.goodsComboDto;
    var shopCart = singleProducts.goodsShopCart;
    var lastDiv = $(".shop-cart-div").children(".cart-child-div:last");
    firstDivElement.clone().insertAfter(lastDiv);
    /* 修改元素值 */
    var curDiv = $(".shop-cart-div").children(".cart-child-div:last");

    //json格式数据，用于立即结算时提交
    curDiv.find(".cart-item-json").val(JSON.stringify(goods));
    curDiv.find(".cart-pro-img").attr("src", goods.imgUrl);
    curDiv.find(".cart-pro-name").text(goods.goodsName);
    curDiv.find(".cart-price").text(toDecimal2(goods.discountPrice));
    curDiv.find(".cart-amount").val(shopCart.count);
    /* 小计 */
    curDiv.find(".cart-total").text(toDecimal2(goods.discountPrice * shopCart.count));
    curDiv.find(".cart-goods-guid").val(goods.goodsGuid);
    curDiv.find(".cart-id").val(shopCart.id);
    curDiv.find(".cart-goods-type").val(goods.type);
    curDiv.find(".cart-guid").val(shopCart.goodsGuid);

    //这里判断复选框的状态(就是商品有没有货的问题)
    check_box_status(goods.goodsStatus, curDiv);
}

//这里判断复选框的状态(就是商品有没有货的问题)
function check_box_status(goodsStatus, div) {
    //买完了
    if (goodsStatus == sale_out) {
        div.removeClass("car-kinds");
        div.removeClass("is-checkbox");
        div.find(".car-checktips").removeClass("is-check");
        div.find(".car-checktips").removeClass("bg-activate");
        $(".headDiv").find(".car-checkall").removeClass("is-checkall");
        $(".headDiv").find(".car-checkall").removeClass("bg-activate");
        div.find(".car-checktips").addClass("notclick");
        $(".headDiv").addClass("notclick");//这个是按钮，不可选的属性
        div.find(".show_goods_status").html(name_450);
    } else if (goodsStatus == invalid) {
        //下架了
        div.removeClass("car-kinds");
        div.removeClass("is-checkbox");
        div.find(".car-checktips").removeClass("is-check");
        div.find(".car-checktips").removeClass("bg-activate");
        $(".headDiv").find(".car-checkall").removeClass("is-checkall");
        $(".headDiv").find(".car-checkall").removeClass("bg-activate");
        $div.find(".car-checktips").addClass("notclick");
        $(".headDiv").addClass("notclick");
        div.find(".show_goods_status").html(name_451);
    }
}

/* 遍历购物车中的套餐 */
function showPackProduct(firstDivElement, packageProducts) {
    var lastDiv = $(".shop-cart-div").children(".cart-child-div:last");
    firstDivElement.clone().insertAfter(lastDiv);
    var totalPrice = 0; // (主商品)  小计的总价格
    var packagePrice = 0; // (套餐)  小计的总价格
    var show_goods_status = 1;//默认表示显示商品的按钮(有货的状态)

    // 一个套餐内有多少商品就循环多少次，套餐的循环在上一个方法中
    for (var index in packageProducts) {
        var packProduct = packageProducts[index];
        var goods = packProduct.goodsComboDto;
        var shopCart = packProduct.goodsShopCart;

        if (goods.goodsStatus == sale_out) {
            //表示商品没有货了
            show_goods_status = sale_out;
        } else if (goods.goodsStatus == invalid) {
            //下架了
            show_goods_status = invalid;
        }

        var itemDiv = $(".cart-pack-item:last");
        var clonePackFitting = itemDiv.find(".cart-pack-fitting:first");
        var lastPackDetailDiv = itemDiv.children(".cart-pack-fitting:last");
        var curDiv;

        if (index == 0) {
            /* 套餐主商品 */
            curDiv = itemDiv.children(".cart-pack-main");
            //json格式数据，用于立即结算时提交
            curDiv.find(".cart-item-json").val(JSON.stringify(goods));
            curDiv.find(".cart-amount").val(goods.count * shopCart.count);
            /* 主商品 小计 */
            totalPrice = totalPrice + goods.discountPrice * goods.count * shopCart.count;
            /* 主商品 小计 */
            curDiv.find(".cart-total").text(toDecimal2(totalPrice));

            itemDiv.find(".cart-id").val(shopCart.id);	//购物车索引，用于修改数量，删除和下单
            itemDiv.find(".cart-goods-guid").val(goods.goodsGuid);
            itemDiv.find(".cart-goods-type").val(goods.type);
            itemDiv.find(".cart-guid").val(shopCart.goodsGuid);
        } else if (packProduct != null) {

            clonePackFitting.clone().insertAfter(lastPackDetailDiv);
            /* 套餐内搭配商品 */
            curDiv = itemDiv.children(".cart-pack-fitting:last");
            curDiv.find(".cart-amount").text(goods.count * shopCart.count);
            /* 套餐小件  小计 */
            packagePrice = packagePrice + goods.discountPrice * goods.count * shopCart.count;
            //套餐 价格的小计
            curDiv.find(".cart-total").text(toDecimal2(packagePrice));
            curDiv.find(".cart-goods-guid").val(goods.goodsGuid);
            curDiv.find(".cart-item-json").val(JSON.stringify(goods));
            curDiv.find(".cart-guid").val(shopCart.goodsGuid);
            curDiv.find(".cart-id").val(shopCart.id);
        }

        curDiv.find(".pack-final-count").val(goods.count);
        /* 修改元素值 */
        curDiv.find(".cart-pack-img").attr("src", goods.imgUrl);
        curDiv.find(".cart-pack-name").text(goods.goodsName);
        curDiv.find(".cart-price").text(toDecimal2(goods.discountPrice));
    }
    // 删除套餐模板div
    $(".cart-pack-item:last").find(".cart-pack-fitting:first").remove();

    //这里判断复选框的状态(就是商品有没有货的问题)
    check_box_status(show_goods_status, itemDiv);
}

//--------------------------------------------------以下是对购物车的修改--------------------------------------
/*判断购物车数量是否为正整数*/
function zhzs(value){

    value = value.replace(/[^\d]/g,'');
    if(''!=value){
        value = parseInt(value);
    }else if (value='0'){
        value ='1';
    }

    return value;
}

/*添加数量*/
$(".shop-cart-div").on("click", ".add-count", function () {
    var counts = $(this).parents().closest(".car-kinds").find(".cart-amount");
    var curCount = 0;
    if (counts.length == 1) {  //单品 数量框
        var count = parseInt(counts.val());
        if (count < 99) {
            curCount = count + 1;
            counts.val(curCount);
            //修改小计
            var price = $(this).parent().parent().parent().find(".cart-price").text();
            var total = curCount * parseFloat(price);
            $(this).parent().parent().parent().find(".cart-total").text(toDecimal2(total));
        }

        var goodsNumber = $(this).siblings(".cart-goods-guid").val();
        var cartId = $(this).siblings(".cart-id").val();
        //ajax请求后台，修改购物车数据
        modifyCartItemAmount(cartId, goodsNumber, count + 1);
        //修改总价
        countalltips();

    } else if (counts.length > 1) {
        //套餐数量不可更改
        layer.msg(name634, {icon: 7});
    }

});
/*鼠标失去焦点时提交计算总价*/
function show(){
    var counts = $("#value").val();

    if(counts>=1){
            //修改小计
            var price = document.getElementById("price").innerHTML
            var total = counts * parseFloat(price);
            document.getElementById("cartotal").innerText = total;

    } else {
            return;
        }
        var goodsGuid = $("#goodsGuid").val();
        var cartId = $("#cartId").val();
        //ajax请求后台，修改购物车数据
        modifyCartItemAmount(cartId, goodsGuid, counts);
        //修改总价
        countalltips();

}
/*减少数量*/
$(".shop-cart-div").on("click", ".min-count", function () {
    var counts = $(this).parents().closest(".car-kinds").find(".cart-amount");
    var curCount = 0;
    if (counts.length == 1) {  //单品 数量框
        var count = parseInt(counts.val());

        if (count > 1) {
            curCount = count - 1;
            counts.val(curCount);
            //修改小计
            var price = $(this).parent().parent().parent().find(".cart-price").text();
            var total = curCount * parseFloat(price);
            $(this).parent().parent().parent().find(".cart-total").text(toDecimal2(total));
        } else {
            return;
        }

        var goodsGuid = $(this).siblings(".cart-goods-guid").val();
        var cartId = $(this).siblings(".cart-id").val();
        //ajax请求后台，修改购物车数据
        modifyCartItemAmount(cartId, goodsGuid, count-1);
        //修改总价
        countalltips();

    } else if (counts.length > 1) {
        //套餐数量不可更改
        layer.msg(name634, {icon: 7});
    }

});

/**
 * ajax修改购物单品数量
 * @param cart_item_id  购物车单项id
 * @param amount        增加或减少的差值（如 1，-1）
 */
function modifyCartItemAmount(cartId, goodsGuid, count) {
    $.ajax({
        url: "web/call/updateCart",
        type: 'post',
        data: {
            "index": cartId,
            "goodsGuid": goodsGuid,
            "count": count
        },
        dataType: 'json',
        success: function (data, statusText, xmlHttpRequest) {
        },
        error: function (xmlHttpRequest, statusText, errorThrown) {
        }
    });
}

/*------------------- 删除商品-----------------------------------------------*/
function delCart(self) {
    var goodsGuid = $(self).siblings().find(".cart-goods-guid").val();
    var cartId = $(self).children(".cart-id").val();
    var goodsType = $(self).children(".cart-goods-type").val();
    layer.confirm('确定要删除吗？', {
        // 按钮
        btn: ['确定', '我再想想']
    }, function () {
        $.ajax({
            url: "web/call/deleteCart",
            type: 'POST',
            data: {
                "index": cartId,
                "goodsType": goodsType,
                "goodsGuid": goodsGuid
            },
            success: function (data) {
                if (data == "200") {
                    //删除当前元素
                    $(self).parents().closest(".car-kinds").remove();
                    layer.closeAll();
                    //重新计算总价
                    countalltips();
                }
                /*删除时判断是否还有商品，没有的话显示购物车为空图片*/
                if(loadCartAjax()==200){
                    showEmptyCart();
                }
            }
        })
    }, function () {
    });
}

/*------------------- 复选框  单选   全选事件 ---------------------------------------------*/

/*复选框页面  全选*/
function chooseAll(self) {
    var ischecked = $(self).hasClass("is-checkall");
    var carkindslength = $(".car-kinds").length;
    if (ischecked) {
        for (var i = 0; i < carkindslength; i++) {
            carkindsstr = ".car-kinds:eq(" + i + ")";
            var strisstopcheck = $(carkindsstr).hasClass("stop-check");

            if (strisstopcheck) {

            } else {
                $(carkindsstr).removeClass("is-checkbox");
                $(carkindsstr).find(".car-checktips").removeClass("is-check");
                $(carkindsstr).find(".car-checktips").removeClass("bg-activate");
            }
        }
        $(".car-checkall").removeClass("is-checkall");
        $(".car-checkall").removeClass("bg-activate");
    } else {
        for (var i = 0; i < carkindslength; i++) {
            carkindsstr = ".car-kinds:eq(" + i + ")";
            var strisstopcheck = $(carkindsstr).hasClass("stop-check");
            if (strisstopcheck) {

            } else {
                $(carkindsstr).addClass("is-checkbox");
                $(carkindsstr).find(".car-checktips").addClass("is-check");
                $(carkindsstr).find(".car-checktips").addClass("bg-activate");
            }
        }
        $(".car-checkall").addClass("is-checkall");
        $(".car-checkall").addClass("bg-activate");
    }
    countalltips();
}

/*购物车复选框 单个点击事件*/
function checkClick(self) {
    var thischecktips = $(self);
    var ischeckedtips = $(self).hasClass("is-check");
    if (ischeckedtips) {  //取消选中
        thischecktips.removeClass("is-check");
        thischecktips.removeClass("bg-activate");
        $(".car-checkall").removeClass("is-checkall");
        $(".car-checkall").removeClass("bg-activate");

        thischecktips.parents().closest(".car-kinds").removeClass("is-checkbox");
    } else {  //选中
        thischecktips.addClass("is-check");
        thischecktips.addClass("bg-activate");
        thischecktips.parents().closest(".car-kinds").addClass("is-checkbox");
        var carkindslength = $(".car-kinds").length;

        function isallture() {
            for (var i = 0; i < carkindslength; i++) {
                carkindsstr = ".car-kinds:eq(" + i + ")";

                var strisstopcheck = $(carkindsstr).hasClass("stop-check");
                if (strisstopcheck) {
                } else {
                    var truele = $(carkindsstr).hasClass("is-checkbox");
                    if (truele) {
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }

        if (isallture()) {
            $(".car-checkall").addClass("is-checkall");
            $(".car-checkall").addClass("bg-activate");
        } else {
        }
    }
    countalltips();
}

/*--------------------------计算数量---------------------------------------*/
function countalltips() {

    /*总商品件数赋值*/
    var total_carts_count = $(".car-kinds").length;
    $(".tot-num-of-kinds").text(total_carts_count);

    /*所有选中复选框的数量*/
    var allcheckbox = $(".is-checkbox").length;
    /*选中的商品件数赋值*/
    $(".tot-num-of-goods").text(allcheckbox);

    /*全部商品的总价之和*/
    var totalPrices = Number(0);

    //获得所有复选框选中元素
    //子元素的cart-total的text()
    $(".is-checkbox").find(".cart-total").each(function () {
        totalPrices += Number($(this).text());
    });

    /*总价赋值，保存两位小数*/
    $(".total-price").text(totalPrices.toFixed(2));
    //判断是否可以去结算
    if (totalPrices == 0) {
        $(".HK-cartsubmit").attr("disabled", "true");
        $(".HK-cartsubmit").css("cursor", "not-allowed");
        $(".HK-cartsubmit").css("opacity", "0.5");
        $(".HK-cartsubmit").removeClass("hovbg-38B3FF");
    } else {
        $(".HK-cartsubmit").removeAttr("disabled");
        $(".HK-cartsubmit").css("cursor", "pointer");
        $(".HK-cartsubmit").css("opacity", "1");
        $(".HK-cartsubmit").addClass("hovbg-38B3FF");
    }
}

/*复选框*/
function countsingletips() {
    /*套餐部分*/
    var combotipslength = $(".car-combo").find(".car-kinds").length;
    for (var i = 0; i < combotipslength; i++) {
        var combotipsstr = ".car-kinds:eq(" + i + ")";
        var comboprice = parseFloat($(".car-combo").find(combotipsstr).find(".cart-price").val());
        var combonum = parseInt($(".car-combo").find(combotipsstr).find(".cart-combo-textbox").val());
        $(".car-combo").find(combotipsstr).find(".cart-price-show").text(comboprice.toFixed(2));
        $(".car-combo").find(combotipsstr).find(".cart-total").text((combonum * comboprice).toFixed(2));
        var combotipslengthv = $(".car-combo").find(combotipsstr).siblings(".car-combo-tips").length;
        for (var j = 0; j < combotipslengthv; j++) {
            var carcombotipsstr = ".car-combo-tips:eq(" + j + ")";
            var comboprice = $(".car-combo").find(combotipsstr).siblings(carcombotipsstr).find(".car-combo-tips-unp").text();
            $(".car-combo").find(combotipsstr).siblings(carcombotipsstr).find(".car-combo-tips-numcase").text(combonum);
            $(".car-combo").find(combotipsstr).siblings(carcombotipsstr).find(".car-combo-tips-tot").text((comboprice * combonum).toFixed(2));
        }
        ;
    }
    ;
    /*单品部分*/
    var singletipslength = $(".car-single").find(".car-kinds").length;
    for (var i = 0; i < singletipslength; i++) {
        var singletipsstr = ".car-kinds:eq(" + i + ")";
        var singleprice = parseFloat($(".car-single").find(singletipsstr).find(".cart-price").val());

        var singlenum = parseInt($(".car-single").find(singletipsstr).find(".cart-textbox").val());//cart-textbox

        $(".car-single").find(singletipsstr).find(".cart-price-show").text(singleprice);
        $(".car-single").find(singletipsstr).find(".cart-total").text((singleprice * singlenum).toFixed(2));
    }
    ;
};

/**
 * 金额格式化，强制保留2位小数，如：2，会在2后面补上00.即2.00
 */
function toDecimal2(number) {
    var f = parseFloat(number);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(number * 100) / 100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return s;
}

/*------------------------------去结算----------------------------------*/
function submitOrderGoods() {
    //此处可能需要优化！！！！！！！！！！！！！！！！
    //数据的组装及传递方面

    var dataArray = [];
    var data = null;
    //获得所有选中的购物车项目
    $(".is-checkbox").find(".cart-item-json").each(function () {
        var amount = $(this).parent().find(".cart-amount").val();
        if (amount == null || amount == "") {
            amount = $(this).parent().find(".cart-amount").text();
        }
        var cartGuid = $(this).siblings(".cart-guid").val();
        var cartId = $(this).parent().find(".cart-id").val();
        data = new Object();
        data.goodsComboDto = $(this).val();
        data.cartGuid = cartGuid;
        data.count = amount;
        data.cartIndex = cartId;	//该参数用于删除购物车数据
        dataArray.push(data);
    });
    $(".cart-submit-input").val(JSON.stringify(dataArray));
    //提交表单
    $('#submit_cart').submit();
}