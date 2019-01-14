$(document).ready(function () {
    loadPageDatas();  //加载页面的数据
});

//----------加载用户数据，订单数据统计数据---------------
function loadPageDatas() {
    $.ajax({
        type: "GET",
        dataType: "json", //数据格式:text
        url: request_url + '/user/userCenterRest', //目标地址
        success: function (data) {
            showDatas(data);//填充数据到页面
        }
    });
}

//填充数据到页面
function showDatas(data) {
    $(".user-avator").attr("src", data.img_pic);
    $(".unpaid-count").text(data.payCount);
    $(".delivery-count").text(data.deliveryCount);
    $(".appraise-count").text(data.appraiseCount);
    $(".active-count").text(data.activeCount);
}

//----------------------------上传头像-------------------------
function submitImgBase64(submiturl) {
    $.ajax({
        type: "POST", //用POST方式传输
        dataType: "text", //数据格式:text
        url: request_url + '/user/uploadPortrait', //目标地址
        jsonp: 'callback',
        data: {
            "img_portrait": submiturl,
        },//传参数
        success: function (msg) {
            if (msg == "200") {
                layer.alert(name588, {
                    icon: 1,
                    title: name148,
                });
                setTimeout(function () {
                    $("#img_close").click();//模拟点击关闭弹出窗按键
                    window.location.reload();//刷新页面
                }, 2000);
            } else {
                msg = getMessage(msg);
                show_msg(msg);
            }
        }
    });
}

/*选择头像部分图像操作部分js*/
cutimg();

function cutimg() {
    $('#show-main-img').cropper({
        aspectRatio: 1 / 1,
        crop: function (e) {
            var nowspace = $('#show-main-img').cropper("getCroppedCanvas", {width: 200, height: 200});
            var dataurl = nowspace.toDataURL('image/jpeg');
            $("#show-little-imgv1").attr("src", dataurl);
            $("#show-little-imgv2").attr("src", dataurl);
        }
    });
};

/*选择头像的弹出层*/
$(".set-heads").click(function () {
    $(".show-selhead").show();
    $(".selhead-content").removeClass("ani-selhead-logoout");
    $(".selhead-content").addClass("ani-selhead-logoin");
});
$(".show-selhead").on("click", function (event) {
    event.stopPropagation();
    var target = event.target;
    if (!$(target).closest(".selhead-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
        $(".selhead-content").removeClass("ani-selhead-logoin");
        $(".selhead-content").addClass("ani-selhead-logoout");
        $(".show-selhead").fadeOut();
    }
    ;
});

//上传文件 按键禁用
$(".cro-btn-submit").attr('disabled', true);
/*选择文件触发事件*/
$('#sel-file').change(function (e) {
    var file = e.target.files[0];
    var bloburl = URL.createObjectURL(file);
    $('#show-main-img').attr("src", bloburl);
    $('#show-main-img').cropper("destroy");
    cutimg();
    //上传文件 按键可点击
    $(".cro-btn-submit").attr('disabled', false);
});
$(".cro-btn-big").click(function () {
    $('#show-main-img').cropper("zoom", 0.1);
})
$(".cro-btn-small").click(function () {
    $('#show-main-img').cropper("zoom", -0.1);
})
$(".cro-btn-left").click(function () {
    $('#show-main-img').cropper("rotate", -90);
})
$(".cro-btn-right").click(function () {
    $('#show-main-img').cropper("rotate", 90);
})
$(".cro-btn-reset").click(function () {
    $('#show-main-img').cropper("reset");
})
$(".cro-btn-submit").click(function () {
    var submitspace = $('#show-main-img').cropper("getCroppedCanvas", {width: 200, height: 200});
    var submiturl = submitspace.toDataURL('image/jpeg');

    var imgLength = submiturl.length;
    if (imgLength == 0) {
        layer.alert(name589, {
            icon: 2,
            title: name590,
        });
    } else {
        submitImgBase64(submiturl);
    }
    var layindex = layer.load(2, {
        shade: [0.4, '#000'] //0.1透明度的白色背景
    });
    //此处用setTimeout演示ajax的回调
    setTimeout(function () {
        layer.close(layindex);
    }, 3000);
})

/*根据消息代码显示异常信息*/
function getMessage(msg) {
    if (msg == "1001") {
        return name591;
    }
    if (msg == "1004") {
        return name592;
    }
}

//消息弹出框
function show_msg(msg) {
    layer.alert(msg, {
        icon: 2,
        title: name148,
    });
}
