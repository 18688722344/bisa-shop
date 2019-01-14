$(document).ready(function () {
    //加载三级联动
    loadAddressInfo();

    //加载用户所有地址信息
    loadUserAddress();

    // 在键盘按下并释放及提交后验证提交表单
    $(".shippingaddress-add").validate({
        rules: {
            shname: {
                required: true,
                minlength: 2
            },
            shphone: {
                required: true,
                minlength: 6
            },
            shaddress: {
                required: true,
                minlength: 5
            },
            input_province: {
                required: true,
            },
            input_city: {
                required: true,
            }
        },
        messages: {
            shname: {
                required: name149,
                minlength: name150
            },
            shphone: {
                required: name151,
                minlength: name152
            },
            shaddress: {
                required: name153,
                minlength: name154
            },
            input_province: {
                required: name155,
            },
            input_city: {
                required: name_167,
            }
        },
        submitHandler: function () {
            /*验证通过，提交表单*/
            addAddress();
        }
    });

    $(".shippingaddress-revise").validate({
        rules: {
            shname: {
                required: true,
                minlength: 2
            },
            shphone: {
                required: true,
                minlength: 6
            },
            shaddress: {
                required: true,
                minlength: 5
            }
        },
        messages: {
            shname: {
                required: name149,
                minlength: name150
            },
            shphone: {
                required: name151,
                minlength: name152
            },
            shaddress: {
                required: name153,
                minlength: name154
            }
        },
        submitHandler: function () {
            updateAddress();
        }
    });
});

// 加载用户所有地址信息
function loadUserAddress() {
    $.ajax({
        url: ajax_url + "/user/loadUserAddresses",
        type: "get",
        dataType: "json",
        async: false,
        success: function (data) {
            var firstDiv = $(".address-div:first");
            for (var index in data) {
                var index = Number(index)
                var lastDiv = $(".address-div:last");
                //显示数据到页面
                showAddress(data[index], firstDiv, lastDiv);
            }
            $(".address-div:first").remove();

            //选中地址，固定边框样式
            var firstDiv1 = $(".address-tips-in:first");
            firstDiv1.removeClass("bor-col-activate");
            firstDiv1.removeClass("bor-2px");
            firstDiv1.addClass("bor-col-activate");
            firstDiv1.addClass("bor-2px");

            var caddress = firstDiv1.find(".add-address").text();
            var addressId = firstDiv1.find(".address-id").val();
            var addressName = firstDiv1.find(".add-name").text();
            var addressPhone = firstDiv1.find(".add-phone").text();
            //这里添加address编号
            $("#addr_id").val(addressId);
            $(".select-address-name").text(addressName);
            $(".select-address-phone").text(addressPhone);
            $(".select-address").text(caddress);
        }
    });
}

function showAddress(data, firstDiv, lastDiv) {
    firstDiv.clone().insertAfter(lastDiv);
    lastDiv = $(".address-div:last");
    lastDiv.find(".add-name").text(data.consignee);
    lastDiv.find(".add-phone").text(data.phone);
    lastDiv.find(".add-address").text(
        data.country + " " + data.province + " " + data.city + " "
        + data.county + " " + data.detail_address);

    // 将地址json数据复制到隐藏input，方便修改和删除
    lastDiv.find(".address-json").val(JSON.stringify(data));
    lastDiv.find(".address-id").val(data.id);
}


/* 添加地址  地址三级联动   start   */
function loadAddressInfo() {
    var html = "";
    $("#input_province").append(html);
    $("#input_county").append(html);
    $.each(pdata, function (idx, item) {
        if (parseInt(item.level) == 0) {
            html += "<option value=" + item.code + " >" + item.names + "</option> ";
        }
    });
    $("#input_province").append(html);
}

$("#input_province").change(function () {
    if ($(this).val() == "") return;
    $("#input_city option").remove();
    $("#input_county option").remove();
    var code = $(this).find("option:selected").val();
    code = code.substring(0, 2);
    var html = "<option value=''>--" + name156 + "--</option>";
    $("#input_county option").append(html);
    $.each(pdata, function (idx, item) {
        if (parseInt(item.level) == 1 && code == item.code.substring(0, 2)) {
            html += "<option value=" + item.code + " >" + item.names + "</option> ";
        }
    });
    $("#input_city").append(html);
});

$("#input_city").change(function () {
    if ($(this).val() == "") return;
    $("#input_county option").remove();
    var code = $(this).find("option:selected").val();
    code = code.substring(0, 4);
    var html = "<option value=''>--" + name157 + "--</option>";
    $.each(pdata, function (idx, item) {
        if (parseInt(item.level) == 2 && code == item.code.substring(0, 4)) {
            html += "<option value=" + item.code + ">" + item.names + "</option> ";
        }
    });
    $("#input_county").append(html);
});
/*  地址三级联动   end   */

/*鼠标移动*/
$(".address-main-div").on("mouseenter", ".address-tips-in", function () {
    $(this).addClass("bor-col-309DE2");
});

$(".address-main-div").on("mouseleave", ".address-tips-in", function () {
    $(this).removeClass("bor-col-309DE2");
});

/*添加收货地址弹出层相关的*/
$(".show-input-shipping").mouseenter(function () {
    $(this).removeClass("bor-col-B2B2B2");
    $(this).addClass("bor-col-999");
});
$(".show-input-shipping").mouseleave(function () {
    $(this).addClass("bor-col-B2B2B2");
    $(this).removeClass("bor-col-999");
});
$(".show-input-shipping").focusin(function () {
    var shipplaceholder = $(this).siblings(".show-input-shipping-value").val();
    var thispoint = $(this);
    $(this).addClass("bor-col-309DE2");
    $(this).siblings(".show-div-shipping").addClass("col-309DE2");
    $(this).siblings(".show-div-shipping").animate({'top': '-6px', 'font-size': '12px'}, 300);
    setTimeout(function () {
        thispoint.attr("placeholder", shipplaceholder)
    }, 300);
});

$(".show-input-shipping").focusout(function () {
    var shipinputval = $(this).val();
    if (shipinputval == "" || undefined || null || NaN) {
        $(this).siblings(".show-div-shipping").animate({'top': '11px', 'font-size': '14px'}, 300);
    } else {
    }
    $(this).attr("placeholder", "");
    $(this).removeClass("bor-col-309DE2");
    $(this).siblings(".show-div-shipping").removeClass("col-309DE2");
});

/*添加新地址*/
$(".address-tipsv2").mouseenter(function () {
    $(this).find(".address-tipsv2-in").addClass("bor-col-309DE2");
    $(this).find(".address-tipsv2-circle").addClass("bg-309DE2i");
    $(this).find(".address-tipsv2-text").addClass("col-555i");
});
$(".address-tipsv2").mouseleave(function () {
    $(this).find(".address-tipsv2-in").removeClass("bor-col-309DE2");
    $(this).find(".address-tipsv2-circle").removeClass("bg-309DE2i");
    $(this).find(".address-tipsv2-text").removeClass("col-555i");
});

/*添加收货地址弹出层*/
$(".add-address-control").click(function () {
    var conblocklength = $(".address-main-div").find(".address-div").length;
    if (conblocklength >= 6) {
        show_hint_msg(name158);
        setTimeout(funcName, 1000);

        function funcName() {
            window.location.reload();
        }

    } else {
        $(".show-add-shippingaddress").fadeIn();
    }
    ;
    document.documentElement.style.overflow = "hidden";
});

$(".show-add-shippingaddress").on("click", function (event) {
    event.stopPropagation();
    var target = event.target;
    if (!$(target).closest(".show-add-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
        $(".show-add-shippingaddress").fadeOut();
        document.documentElement.style.overflow = "scroll";
    }
    ;
});

/*修改收货地址弹出层*/
//on方法要先找到原选择器（非动态添加的）,再找到动态添加的选择器
$(".address-main-div").on("click", ".address-tips-edit", function () {
    $(".show-revise-shippingaddress").fadeIn();

    var addressData = $(this).parent().siblings(".address-json").val();
    addressData = JSON.parse(addressData);

    $("#up_id").val(addressData.id);
    $("#upname").val(addressData.consignee);
    $("#upphone").val(addressData.phone);

    loadUpdateAddressInfo(addressData.province, addressData.city, addressData.county);
    $("#upaddress").text(addressData.detail_address);
    $("#address_label[text='" + addressData.address_label + "']").attr("checked", true);   //设置Select的Text值为jQuery的项选中
    setIsDefaultValue(addressData.is_default);
    $("#show-revise-shippingaddress").find(".show-div-shipping").animate({'top': '-6px', 'font-size': '12px'}, 10);
    document.documentElement.style.overflow = "hidden";

});

/* 修改地址的 地址三级联动   start   */
function loadUpdateAddressInfo(province, city, county) {
    /*初始化*/
    var html = "";
    var html1 = "";
    var html2 = "";
    var provinceCode="";
    var cityCode="";

    $.each(pdata, function (idx, item) {
        if (parseInt(item.level) == 0) {
            item.names==province? provinceCode=item.code:'';
            html += "<option value=" + item.code + " >" + item.names + "</option> ";
        }
        if (parseInt(item.level) == 1 && provinceCode.substring(0, 2) == item.code.substring(0, 2)) {
            item.names==city? cityCode=item.code:'';
            html1 += "<option value=" + item.code + " >" + item.names + "</option> ";
        }
        if (parseInt(item.level) == 2 && cityCode.substring(0, 4) == item.code.substring(0, 4)) {
            html2 += "<option value=" + item.code + ">" + item.names + "</option> ";
        }
    });
    $("#update_province").html("<option value="+provinceCode+">" + province + "</option>");
    $("#update_province").append(html);
    $("#update_city").html("<option value="+cityCode+">" + city + "</option>");
    $("#update_city").append(html1);
    $("#update_county").html("<option value="+cityCode+">" + county + "</option>");
    $("#update_county").append(html2);
}

$("#update_province").change(function () {
    if ($(this).val() == "") return;
    $("#update_city option").remove();
    $("#update_county option").remove();
    var code = $(this).find("option:selected").val();
    code = code.substring(0, 2);
    var html = "<option value=''>--" + name156 + "--</option>";
    $("#update_county option").append(html);
    $.each(pdata, function (idx, item) {
        if (parseInt(item.level) == 1 && code == item.code.substring(0, 2)) {
            html += "<option value=" + item.code + " >" + item.names + "</option> ";
        }
    });
    $("#update_city").append(html);
});

$("#update_city").change(function () {
    if ($(this).val() == "") return;
    $("#update_county option").remove();
    var code = $(this).find("option:selected").val();
    code = code.substring(0, 4);
    var html = "<option value=''>--" + name157 + "--</option>";
    $.each(pdata, function (idx, item) {
        if (parseInt(item.level) == 2 && code == item.code.substring(0, 4)) {
            html += "<option value=" + item.code + ">" + item.names + "</option> ";
        }
    });
    $("#update_county").append(html);
});

/* 修改地址的 地址三级联动   end   */

$(".show-revise-shippingaddress").on("click", function (event) {
    event.stopPropagation();
    var target = event.target;
    if (!$(target).closest(".show-revise-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
        $(".show-revise-shippingaddress").fadeOut();
        document.documentElement.style.overflow = "scroll";
    }
    ;
});

//添加地址，提交表单
function addAddress() {
    var str = {
        "name": $("#shname").val(),
        "tel": $("#shphone").val(),
        "input_province": $("#input_province option:selected").text(),
        "input_city": $("#input_city option:selected").text(),
        "input_county": $("#input_county option:selected").text(),
        "area": $("#shaddress").val(),
        "address_label": $("#address_label option:selected").text(),
        "is_default": $("#is_default").val()
    };

    var addressJson = JSON.stringify(str);

    $.ajax({
        url: ajax_url + "/user/addAddress",
        type: "post",
        dataType: "json",
        async: false,
        data: {
            "addressJson": addressJson,
        },
        success: function (data) {
            if (data == "1001") {
                show_hint_msg(name159);
            }
            if (data == "1005") {
                show_hint_msg(name160);
            }
            if (data == "200") {
                show_success_msg(name161);
            }
        },
        error: function () {
            show_fail_msg(name162);
        }
    });
}

//是否默认地址 
//如果值为1， 设置选中状态
function setIsDefaultValue(value) {
    if (value == 1) {  //选中
        $("#up_is_default").attr("checked");
    } else {
        $("#up_is_default").remove("checked");
    }
}

//是否默认地址（选中为1， 不选为0）
$('.is-default').click(function () {
    if ($(this).prop("checked")) {
        $(this).val(1);
    } else {
        $(this).val(0);
    }
});

//修改地址提交表单
function updateAddress() {
    var str = {
        "id": $("#up_id").val(), "name": $("#upname").val(),
        "tel": $("#upphone").val(),
        "input_province": $("#update_province option:selected").text(),
        "input_city": $("#update_city option:selected").text(),
        "input_county": $("#update_county option:selected").text(),
        "area": $("#upaddress").val(),
        "address_label": $("#up_address_label option:selected").text(),
        "is_default": $("#up_is_default").val()
    };
    var addressJson = JSON.stringify(str);
    $.ajax({
        url: ajax_url + "/user/updateAddress",
        type: "post",
        dataType: "json",
        data: {
            "addressJson": addressJson,
        },
        success: function (data) {
            if (data == "1001") {
                show_hint_msg(name159);
            }
            if (data == "1005") {
                show_hint_msg(name163);
            }
            if (data == "200") {
                show_success_msg(name164);
            }
        },
        error: function () {
            show_fail_msg(name162);
        }
    });
}

/*删除收货地址弹出层*/
$(".address-main-div").on("click", ".address-tips-del", function () {
    var addressId = $(this).parent().siblings(".address-id").val();

    layer.open({
        content: name165,
        yes: function (index, layero) {
            $.ajax({
                url: ajax_url + "/user/delAddress",
                type: "POST",
                data: {
                    "id": addressId,
                },
                success: function (data) {
                    if (data == "1005") {
                        show_hint_msg(name166);
                    }
                    if (data == "200") {
                        show_success_msg(name167);
                    }
                },
                error: function () {
                    show_fail_msg(name162);
                }
            });
        }
    });
});

$(".show-del-shippingaddress").on("click", function (event) {
    event.stopPropagation();
    var target = event.target;
    if (!$(target).closest(".show-del-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
        $(".show-del-shippingaddress").fadeOut();
        document.documentElement.style.overflow = "scroll";
    }
    ;
});

//确认删除收货地址
$(".del-address-form").submit(function (envent) {
    envent.preventDefault();
    var addressId = $(".is-del-shippingaddress-id").val();

    $.ajax({
        url: ajax_url + "/user/delAddress",
        type: "POST",
        data: {
            "id": addressId,
        },
        success: function (data) {
            if (data == "1005") {
                show_fail_msg(name166);
            }
            if (data == "200") {
                show_success_msg(name167);
            }
        },
        error: function () {
            show_fail_msg(name162);
        }
    });
});

//消息弹出框(成功)
function show_success_msg(msg) {
    layer.msg(msg, {icon: 6, time: 1500}, function () {
        window.location.reload();
    });
}

//消息弹出框(失败)
function show_fail_msg(msg) {
    layer.msg(msg, {icon: 5, time: 1500}, function () {
    });
}

//消息弹出框(提示)
function show_hint_msg(msg) {
    layer.msg(msg, {icon: 7, time: 1500});
}