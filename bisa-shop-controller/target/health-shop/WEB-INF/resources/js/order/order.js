layui.use('layer', function () {
    //独立版的layer无需执行这一句
    var $ = layui.jquery,
        layer = layui.layer;
});

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
        show_msg(name146);
    } else {

        $.ajax({
            url: 'user/commitOrder',
            type: 'post',
            data: {
                address_id: $("#addr_id").val(),
                token: $("#token").val()
            },
            success: function (data) {
                if (data == "200") {
                    window.location.href = "user/payMentPage";
                } else if (data == "102") {
                    //重复提交表单页面
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
