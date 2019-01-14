$(document).ready(function () {
    /*头部导航下划线*/
    $(".mainnav").find("a").mouseenter(function () {
        $(this).addClass("navbor");
    });
    $(".mainnav").find("a").mouseleave(function () {
        $(this).removeClass("navbor");
    });
    /*头部输入框变化*/
    $(".mainsearch").click(function () {
        event.stopPropagation();
        $(".mainsearchinput").fadeIn();
    });
    $(document).click(function () {
        $('.mainsearchinput').fadeOut();
    });

    //判断是否有收货地址
    var addressCount = $(".address-main-div").find(".address-div").length;
    if (addressCount == 0) {
        layer.alert(name147, {
            icon: 0,
            title: name148,
        })
    }
});

$(".conanorder-tipsadd").mouseenter(function () {
    $(this).addClass("bor-col-309DE2");
    $(this).find(".conanorder-tips-add").removeClass("col-B2B2B2");
    $(this).find(".conanorder-tips-add").addClass("col-309DE2");
    $(this).find(".conanorder-tips-addv2").removeClass("bg-eee");
    $(this).find(".conanorder-tips-addv2").addClass("bg-309DE2");
});

$(".conanorder-tipsadd").mouseleave(function () {
    $(this).removeClass("bor-col-309DE2");
    $(this).addClass("bor-col-7A7A7A");
    $(this).find(".conanorder-tips-add").addClass("col-B2B2B2");
    $(this).find(".conanorder-tips-add").removeClass("col-309DE2");
    $(this).find(".conanorder-tips-addv2").addClass("bg-eee");
    $(this).find(".conanorder-tips-addv2").removeClass("bg-309DE2");
});

$(".conanorder-moreaddress").mouseenter(function () {
    $(this).find("i").removeClass("col-252525");
    $(this).find("i").addClass("col-white");
});

$(".conanorder-moreaddress").mouseleave(function () {
    $(this).find("i").addClass("col-252525");
    $(this).find("i").removeClass("col-white");
});


/*鼠标点击收货地址发生的变化*/
$(".address-main-div").on("click", ".address-tips-in", function () {
    //选中地址，固定边框样式
    $(".address-main-div").find(".address-tips-in").removeClass("bor-col-activate");
    $(".address-main-div").find(".address-tips-in").removeClass("bor-2px");
    $(this).addClass("bor-col-activate");
    $(this).addClass("bor-2px");

    var caddress = $(this).find(".add-address").text();
    var addressId = $(this).find(".address-id").val();
    var addressName = $(this).find(".add-name").text();
    var addressPhone = $(this).find(".add-phone").text();
    //这里添加address编号
    $("#addr_id").val(addressId);
    $(".select-address-name").text(addressName);
    $(".select-address-phone").text(addressPhone);
    $(".select-address").text(caddress);
});

//下单按钮  提交表单
function submitOrder() {
    //尚未选择收货地址
    var select_address = $(".select-address").text();
    if (select_address == "" || select_address == null) {
        //尚未选择收货地址
        layer.msg(name146, {icon: 7});
    } else {
        var email = $("#email").val();
        // 判断邮件地址是否为空， 邮件地址格式是否正确
        if (!handleEmail(email)) {
            layer.msg(name711, {icon: 7});
            return;
        }

        var postCompany = "";	//物流公司
        var postAccount = "";	//物流付费账号
        var needPost = $("#needPost").val();
        if (needPost != 0) { // 需要邮递
            postCompany = $("#choose option:selected").text();
            // 获取Select选中的索引值
            var checkIndex = $("#choose").get(0).selectedIndex;
            if (checkIndex >= 3) { // 到付，必填付费账号
                postAccount = $("#postAccount").val();
                if (postAccount == "" || postAccount == null) {
                    // 请输入物流付费账号
                    layer.msg(name_146, {icon: 7});
                    return;
                }
            }
        }

        // 提交表单
        $.ajax({
            url: 'user/commitOrder',
            type: 'post',
            data: {
                logistics_index: $("#choose").val(),
                address_id: $("#addr_id").val(),
                token: $("#token").val(),
                email: email,
                postCompany: postCompany,
                postAccount: postAccount
            },
            success: function (data) {
                if (data == "200") {
                    window.location.href = "user/payMentPage";
                } else if (data == "102") {
                    window.location.href = "user/payRepetition";
                } else if (data == "300") {
                    //表示商品库存存不够了
                    layer.open({
                        title: name_147,
                        icon: 5,
                        content: name_148
                    });
                }
            }
        });
    }
}

// 初始化邮费
initPostFee();

function initPostFee() {
    var postPrice = 0;	//初始默认第一个物流
    var needPost = $("#needPost").val();
    if (needPost == 0) { //免邮；或无需邮递
        //免郵，郵遞方式不可選
        $(".free-post-div").removeClass("dis-n");
        $(".post-method-div").addClass("dis-n");
    } else {
        postPrice = postCompanyFee(1);//初始默认第一个物流
        $("#postPrice").text(postPrice);
    }

    //重新计算订单实际支付总额
    showActualPay(postPrice);
}

//邮递方式，下拉框选择事件
$("select#choose").click(function () {
    var index = $(this).val();
    var postPrice = postCompanyFee(index);
    if (postPrice == 0) {	//选择到付
        $(".post-account-div").removeClass("dis-n");
    } else {
        $(".post-account-div").addClass("dis-n");
    }
    $("#postPrice").text(postPrice);
    $("#postPrice1").val(postPrice);

    //重新计算订单实际支付总额
    showActualPay(postPrice);
});

//计算订单实际支付总额
function showActualPay(postPrice) {
    var totalPrice = $("#totalPrice1").val();
    var discountPirce = $("#preferentialPrice1").val();
    var actual = parseFloat(totalPrice) - parseFloat(discountPirce) + parseFloat(postPrice);
    console.log(parseFloat(totalPrice)+"   "+ parseFloat(discountPirce)+"  "+ parseFloat(postPrice)+">>>>>>")
    $("#actualPayment").text(actual);
    $("#actuallyPrice1").val(actual);
}

//物流公司邮费判断
function postCompanyFee(index) {
    if (index <= 3) {	//国际快件
        return 125.00;
    } else {		//邮费到付
        return 0;
    }
}

//判断邮箱地址是否正确
function handleEmail(email) {
    if (email != null && email != "") {
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!myreg.test(email)) {
            return false;
        } else {
            return true;
        }
    }
    return true;
}

