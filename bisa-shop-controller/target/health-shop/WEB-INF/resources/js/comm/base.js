$(document).ready(function () {

    //获取地址栏的url，不要？后面的东西 注意了
    var url = window.location.href;
    var url_hk, url_cn, url_en;

    // ?为null
    if (url.indexOf("?") == -1) {
        url_hk = url + "?lang=zh_HK";
        url_cn = url + "?lang=zh_CN";
        url_en = url + "?lang=en_US";

        // ?不为null   lang null
    } else if (url.indexOf("lang") == -1 && url.indexOf("?") != -1) {
        url_hk = url + "&lang=zh_HK";
        url_cn = url + "&lang=zh_CN";
        url_en = url + "&lang=en_US";

        // lang和？都不为 null
    } else if (url.indexOf("lang") != -1 && url.indexOf("?") != -1) {
        url_hk = url.substring(0, url.indexOf("lang") + 5) + "zh_HK";
        url_cn = url.substring(0, url.indexOf("lang") + 5) + "zh_CN";
        url_en = url.substring(0, url.indexOf("lang") + 5) + "en_US";
    }

    $("#lang-hk").attr("href", url_hk);
    $("#lang-cn").attr("href", url_cn);
    $("#lang-us").attr("href", url_en);

    //这个是加载购物车的数量
    getCartNumber();

});

//这个是加载购物车的数量
function getCartNumber() {
    $.ajax({
        url: "web/call/getCartNumber",
        type: 'get',
        success: function (data) {
            $(".layui-badge").text(data);
        },
        error: function () {
            $(".layui-badge").text(0);
        }
    });
}

/*================页脚固定在底部===========*/
$(window).bind("load resize", function () {
    var h = $(".footer").height();
    $(".wrap").css({paddingBottom: h + 'px'});
    $(".footer").css({marginTop: (-h) + 'px'})

    /*鼠标经过导航栏内容控制元素显示隐藏*/
   $(".mainnav_list").mouseover(function () {
        $(this).css('display','block');
    })
    $(".mainnav_list").mouseout(function () {
        $(this).css('display','none');
    })

    $(".mainnav .col-xs-2").mouseover(function () {
        $(this).css("padding-bottom","25px")
        $(this).find(".mainnav_list").css('display','block');
    })
    $(".mainnav .col-xs-2").mouseout(function () {
        $(this).css("padding-bottom","0px")
        $(this).find(".mainnav_list").css('display','none');
    })


    /*头部导航下划线*/
    $(".mainnav").find("a").mouseenter(function () {
        $(this).addClass("navbor");
    });
    $(".mainnav").find("a").mouseleave(function () {
        $(this).removeClass("navbor");
    });


})

