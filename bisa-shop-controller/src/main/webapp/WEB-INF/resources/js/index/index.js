$(document).ready(function () {

    //临时加功能-用于跳转显示证书，后期咨询Karen是否还需要，再删
    //?key=FULL_DOC
    var param = window.location.search;
    var value = getURLArg(param, "key");
    if (value == "FULL_DOC") {
        window.location.href = "https://kiki-test.oss-cn-hongkong.aliyuncs.com/CE_DOC_%28signed%29.pdf";
    }

    //获取首页图片的轮播图，图片集合
    getIndexImg();

    //弹出绑定手机号弹出窗
    var bindPhone = accountFlag;
    if (bindPhone == "200") {
        showBindPhoneDiv();//弹出绑定手机号弹出窗
    }

    /*输入框变化*/
    $(".mainsearch").click(function () {
        event.stopPropagation();
        $(".mainsearchinput").fadeIn();
    });
    $(document).click(function () {
        $('.mainsearchinput').fadeOut();
    });

    /*首页部分*/
    /*首页添加阴影*/
    $(".shodow-tip").mouseenter(function () {
        $(this).parent(".swiper-slide").addClass("z-99");
        $(this).find(".shodow-tipl").addClass("sha-leftv1");
        $(this).find(".shodow-tipr").addClass("sha-rightv1");
    });
    $(".shodow-tip").mouseleave(function () {
        $(this).parent(".swiper-slide").removeClass("z-99");
        $(this).find(".shodow-tipr").removeClass("sha-rightv1");
        $(this).find(".shodow-tipl").removeClass("sha-leftv1");
    });
    $(".shodow-tipv2").mouseenter(function () {
        $(this).parents(".swiper-slide").addClass("z-99");
        $(this).find(".shodow-tipl").addClass("sha-leftv1");
        $(this).find(".shodow-tipr").addClass("sha-rightv1");
    });
    $(".shodow-tipv2").mouseleave(function () {
        $(this).parents(".swiper-slide").removeClass("z-99");
        $(this).find(".shodow-tipr").removeClass("sha-rightv1");
        $(this).find(".shodow-tipl").removeClass("sha-leftv1");
    });
    /*遮罩*/
    $(".bannershadev1").mouseenter(function () {
        $(this).fadeOut();
        $(".tipsshadev1").fadeIn();
    });
    $(".tipsshadev1").mouseenter(function () {
        $(this).fadeOut();
        $(".bannershadev1").fadeIn();
    });
    $(".swiper-pagination").mouseenter(function () {
        $(".swiper-pagination-bullet").mouseenter(function () {
            $(this).find(".mainswiper-pagination-bullet-div").removeClass("banneraniv2");
            $(this).find(".mainswiper-pagination-bullet-div").addClass("banneraniv1");
        });
        $(".swiper-pagination-bullet").mouseleave(function () {
            $(this).find(".mainswiper-pagination-bullet-div").removeClass("banneraniv1");
            $(this).find(".mainswiper-pagination-bullet-div").addClass("banneraniv2");
        });
    });
    /*宽度控制*/
    var protipswidth = $(".swiper-container-tipsv1").width() / 4;
    var protipssideheight = $(".protipssidev1").height();
    $(".protipssidev2").css("height", protipssideheight);
    $(".protipshalfheight").css("height", protipssideheight / 2);

});

//获取首页图片的轮播图，图片集合
function getIndexImg() {
    $.ajax({
        url: "web/call/getIndexImg",
        type: "GET",
        success: function (data) {
            for (var index in data) {
                //赋值到轮播图img上
                $(".index-img-" + index).attr("src", data[index].imgUrl);
            }
        }
    });
}

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

