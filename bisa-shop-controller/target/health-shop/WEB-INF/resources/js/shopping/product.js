/* 从当前url 获取商品编号 */
var goodsNumber = getURLArg(request_params, "goodsNumber");

$(document).ready(function () {
    /* 头部导航下划线 */
    $(".mainnav").find("a").mouseenter(function () {
        $(this).addClass("navbor");
    });
    $(".mainnav").find("a").mouseleave(function () {
        $(this).removeClass("navbor");
    });
    /* 头部输入框变化 */
    $(".mainsearch").click(function () {
        event.stopPropagation();
        $(".mainsearchinput").fadeIn();
    });
    $(document).click(function () {
        $('.mainsearchinput').fadeOut();
    });
    /* 商品页菜单浮层定位 */
    $(window).scroll(function () {
        var floatscroll = $(".header").height();
        if ($(this).scrollTop() > floatscroll) {
            $(".flo-menuv2-aff").show();
        } else {
            $(".flo-menuv2-aff").hide();
        }
    });
    /* 商品页楼层定位 */
    $(".sf-pointer").click(function () {
        var speed = 200;
        var HKbegindescribeheight = $(".HK-begin-describe").offset().top;
        $('body,html').animate({scrollTop: HKbegindescribeheight}, speed);
        return false;
    });
    /* 商品页返回顶部 */
    $(window).scroll(function () {
        // 下面这句主要是获取网页的总高度，主要是考虑兼容性所以把Ie支持的documentElement也写了，这个方法至少支持IE8
        var htmlHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
        // clientHeight是网页在浏览器中的可视高度，
        var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
        // scrollTop是浏览器滚动条的top位置，
        var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
        // 通过判断滚动条的top位置与可视网页之和与整个网页的高度是否相等来决定是否加载内容；

        var headerheight = $(".header").height() + $(".flo-menuv2").height() + $(".flo-menuv2-aff").height();
        var bannerheight = $(".cont-other").height();
        if (scrollTop < headerheight) {
            $(".hk-upbtn").hide();
        }
        if (scrollTop >= headerheight && scrollTop < headerheight + bannerheight) {
            var vss = (scrollTop - headerheight) / bannerheight;
            var strvss = "rgba(155,210,244," + vss + ")"
            $(".hk-upbtn").show();
            $(".hk-upbtn").css({"background": strvss});
        }
        if (scrollTop >= headerheight + bannerheight) {
            $(".hk-upbtn").show();
            $(".hk-upbtn").addClass("bg-9BD2F4");
        }
    })
    $(".hk-upbtn").click(function () {
        var speed = 200;
        $('body,html').animate({scrollTop: 0}, speed);
    });
    $(".go-top").click(function () {
        var speed = 200;
        $('body,html').animate({scrollTop: 0}, speed);
    });
    //加载商品数据
    loadProductsAjax(goodsNumber);

    /* ajax加载页面所需所有商品数据 */
    function loadProductsAjax(goodsNumber) {
        $.ajax({
            url: 'web/call/loadProductsJson',
            type: 'GET',
            data: {"goodsNumber": goodsNumber},
            dataType: 'json',
            success: function (productsObj) {
                // 加载主商品基础数据
                showProductData(productsObj.mainProduct);
                // 加载主商品所有图片
                showProductPics(productsObj.goodsImgs);

                if (productsObj.isVirtual == false) {
                    // 加载所有套餐数据
                    showPackages(productsObj.packages);
                } else {
                    //虚拟商品
                    $(".standard-product").remove(); //删除标配单品元素
                    $(".package-div").remove();
                    $(".virtual-product").removeClass("dis-n");
                    showVirtualData(productsObj.packages);  //虚拟商品
                }
                // 相关商品
                showRelativeGoods(productsObj.relative);
            },
            error: function (xmlHttpRequest, statusText, errorThrown) {
                // 请求失败
            }
        })
    }

    /* 加载 主商品 参数的方法 */
    function showProductData(main_product) {
        /* 将主商品及相关商品数据初始化到页面 */
        $(".standard-product").find(".goods-guid").val(main_product.goodsNumber);
        $(".standard-product").find(".goods_status").val(main_product.goodsStatus);
        $(".standard-product").find(".pack-discount-price").val(main_product.goodsPrice);

        $(".main-product-title").text(main_product.goodsName);
        $(".main-product-describe").html(main_product.description);
        $(".pro-main-price").text(toDecimal2(main_product.goodsPrice));

        //这里判断一下商品库存按钮显示的问题,只判断第一个按钮的显示，后面的按钮，点击的时候去判断
        button_show_problem(main_product.goodsStatus);

        // 主商品大图
        var picUrl = main_product.imgUrl;
        $("#prode-zoom-main").attr("src", picUrl);
        $("#prode-zoom-main").attr("data-zoom-image", picUrl);
        // 图片放大镜初始化
        var ez = $('#prode-zoom-main').data('elevateZoom');
        ez.swaptheimage(picUrl, picUrl);
    }

    /* 加载商品所有图片 */
    function showProductPics(goodsImgs) {
        for (var index in goodsImgs) {
            var pic = goodsImgs[index];
            var picUrl = pic.imgUrl;
            /* 填充四张个小图 */
            if (index == 0) {
                /* 默认第一张就是 主图 */
                var firstImg = $("#little-img").children("div");
                firstImg.attr("data-image", picUrl);
                firstImg.attr("data-zoom-image", picUrl);
                firstImg.children("img").attr("src", picUrl);
            }
            if (index >= 1 && index <= 3) {
                /* 将元素克隆，修改属性值，然后拼接到元素后面 */
                var littleImg = $("#little-img").children("div:last").clone();
                littleImg.attr("data-image", picUrl);
                littleImg.attr("data-zoom-image", picUrl);
                littleImg.children("img").attr("src", picUrl);
                $("#little-img").append(littleImg);
            }

            if (index >= 4) {
                /!* 填充详情图 *!/
                var detailImg = $("#detail-img").children("div:last").clone();
                detailImg.children("img").attr("src", picUrl);
                $("#detail-img").append(detailImg);
            }
        }
    }

    /* 加载相关商品 */
    function showRelativeGoods(data) {
        for (var index in data) {
            var goods = data[index];
            // 替换url后面的参数 如 ?goodsNumber=xx
            var relativeUrl = changeURLArg(request_href, "goodsNumber", goods.goodsNumber);
            // 先克隆模板元素，然后修改属性值
            var cloneImg = $(".fitting-product:last");
            cloneImg.clone().insertAfter(cloneImg);
            // 获得克隆后的元素（最后一个元素）
            var last_img = $(".fitting-product:last");
            last_img.find("a").attr('href', relativeUrl);   // 修改元素下所有a标签的链接地址
            last_img.find("img").attr('src', goods.imgUrl);   // 修改元素下所有img标签的src值
            last_img.find(".goods-name").text(goods.goodsName);   // span显示商品名称
        }
        // 删掉模板div
        $(".fitting-product:first").remove();
    }

    /*加载虚拟商品， 服务激活卡*/
    function showVirtualData(datas) {
        /* 克隆模板元素 */
        var clonePackage = $(".virtual-product:first");
        for (var i in datas) {
            var goods = datas[i];
            var last_pack = $(".virtual-product:last");
            /* 克隆元素拼到最后一个元素后面 */
            clonePackage.clone().insertAfter(last_pack);
            /* 获得克隆后的元素 */
            last_pack = $(".virtual-product:last");
            last_pack.find(".goods_status").val(goods.goodsStatus);
            last_pack.find(".goods-guid").val(goods.goodsNumber);
            last_pack.find(".package-name").text(goods.goodsName);
            last_pack.find(".pro-name").text(goods.goodsName);

            var totalPrice = 0.00;     // 套餐总价
            var totalDiscount = 0.00;  // 套餐优惠(后)价格

            last_pack.find(".pro-amount").text(goods.count);
            last_pack.find(".pro-fix-price").text(toDecimal2(goods.goodsPrice));
            last_pack.find(".pro-discount-price").text(toDecimal2(goods.discountPrice));

            /* 计算总价 */
            totalPrice += (goods.goodsPrice) * (goods.count);
            totalDiscount += (goods.discountPrice) * (goods.count);

            /* 填充套餐总价和优惠额度 */
            last_pack.find(".pack-fix-price").text(toDecimal2(totalPrice));
            last_pack.find(".pack-discount-price").text(toDecimal2(totalDiscount));

            //把第一个商品套餐的价格回显到价格栏中去
            if (i == 0) {
                $(".pro-main-price").html(toDecimal2(goods.goodsPrice));
            }
        }

        /* 删掉模板div */
        clonePackage.remove();
        $(".combotips-div:first").addClass("bg-col-activate");

        //这里判断一下商品库存按钮显示的问题,只判断第一个按钮的显示，后面的按钮，点击的时候去判断
        for (var index in datas) {
            var goods = datas[index];
            if (index == 0) {
                button_show_problem(goods.goodsStatus);
            }
        }
    }

    /* 加载所有套餐数据 */
    function showPackages(packages) {
        /* 克隆模板元素 */
        var clonePackage = $(".package-div:first");

        for (var i in packages) {
            var pack = packages[i];
            var last_pack = $(".package-div:last");
            /* 克隆元素拼到最后一个元素后面 */
            clonePackage.clone().insertAfter(last_pack);
            /* 获得克隆后的元素 */
            last_pack = $(".package-div:last");
            /* 填充套餐 名称 */
            var flag = parseInt(i) + 1;
            last_pack.find(".package-name").text(name497 + flag);  //套餐名称：如套餐1，套餐2
            last_pack.children(".package-detail p").text(name497 + flag);

            var totalPrice = 0.00;     // 套餐总价
            var totalDiscount = 0.00;  // 套餐优惠(后)价格
            var comboGuid = '';		//套餐编号

            // 克隆悬浮窗口元素
            var clonePackItem = last_pack.children().find(".pack-item:first");
            /* 填充悬浮窗口数据 */
            for (var index in pack) {
                var goodsPackagesDto = pack[index];
                comboGuid = goodsPackagesDto.goodsGuid;
                var last_detail = last_pack.find(".pack-item:last");
                clonePackItem.clone().insertAfter(last_detail);
                // 获得克隆后的元素
                last_detail = last_pack.find(".pack-item:last");

                last_detail.children(".pro-img").find("img").attr("src", goodsPackagesDto.imgUrl);
                last_detail.children(".pro-amount").text(goodsPackagesDto.count);
                last_detail.children(".pro-fix-price").text(toDecimal2(goodsPackagesDto.goodsPrice));
                last_detail.children(".pro-discount-price").text(toDecimal2(goodsPackagesDto.discountPrice));

                /* 计算总价 */
                totalPrice += (goodsPackagesDto.goodsPrice) * (goodsPackagesDto.count);
                totalDiscount += (goodsPackagesDto.discountPrice) * (goodsPackagesDto.count);

                //商品状态赋值（表示有没有货）
                if (goodsPackagesDto.goodsStatus == sale_out || goodsPackagesDto.goodsStatus == invalid) {
                    last_pack.find(".goods_status").val(goodsPackagesDto.goodsStatus);
                }
            }
            last_pack.find(".goods-guid").val(comboGuid);

            /* 删掉模板div */
            last_pack.children().find(".pack-item:first").remove();
            /* 填充套餐总价和优惠额度 */
            last_pack.children().find(".pack-fix-price").text(toDecimal2(totalPrice));
            last_pack.children().find(".pack-discount-price").text(toDecimal2(totalDiscount));
        }
        /* 删掉模板div */
        $(".package-div:first").remove();
        $(".combotips-div:first").addClass("bg-col-activate");
    }

    /* 套餐点击事件 */
    $('.combomainbox').on('click', '.combotips', function () {
        /* 蓝底白字 激活样式 */
        $(".combomainbox").find(".combotips-div").removeClass("bg-col-activate");
        $(this).find(".combotips-div").addClass("bg-col-activate");

        //判断商品的状态
        var goods_status = $(this).find(".goods_status").val();
        button_show_problem(goods_status);

        //回显价格到面板上
        var pack_discount_price = $(this).find(".pack-discount-price").html();
        if (pack_discount_price == "") {
            pack_discount_price = $(this).find(".pack-discount-price").val();
        }
        $(".pro-main-price").html(toDecimal2(pack_discount_price));
    });

    /* 当页面加载完毕后判断套餐的数量，对样式进行排序 */
    Sortingpackages();

    function Sortingpackages() {
        var combotipslength = $(".combomainbox").find(".combotips").length;
        if (combotipslength <= 3) {
            $(".combomainbox").addClass("mb-40-20-ipad");
            $(".combotips").addClass("mt-40-20-ipad");
        }
        if (3 < combotipslength && combotipslength <= 6) {
            $(".combomainbox").addClass("mb-20-10-ipad");
            $(".combotips").addClass("mt-20-10-ipad");
        }
        if (6 < combotipslength) {
            for (var i = 6; i < combotipslength; i++) {
                var combostrtips = " .combotips:eq(" + i + ")";
                $(combostrtips).hide();
            }
            $(".combomainbox").addClass("mb-20-10-ipad");
            $(".combotips").addClass("mt-20-10-ipad");
        }
    };

    // ================点击“添加购物车”触发事件======================
    $(".add-shopcar").click(function () {
        /* 单品或套餐json数据 */
        var goodsGuid = $(".bg-col-activate").siblings(".goods-guid").val();
        var goodsType = $(".bg-col-activate").siblings(".goods-type").val();

        $.ajax({
            url: "web/call/addBuyerCart",
            type: 'post',
            data: {
                goodsGuid: goodsGuid,
                goodsType: goodsType
            },
            success: function (data) {
                if (data == "200") {
                    layer.msg(name509, {icon: 6}, function () {
                        //这个是加载购物车的数量
                        $.ajax({
                            url: "web/call/getCartNumber",
                            type: 'get',
                            success: function (data) {
                                $(".layui-badge").text(data);
                            }
                        });
                    });
                } else if (data == "1006") { //购物车数量达上限
                    layer.msg(name629, {icon: 7});
                } else {
                    layer.msg(name160, {icon: 5});
                }
            },
            error: function () {
                layer.msg(name162, {icon: 5});
            }
        });
    });
    $(".user-appraise").click(function () {
        var productId = $("#productId").val();
        window.location.href = "shopping/Uappraise?productId=" + productId;
    });
    $(".product-shopping").click(function () {
        window.location.href = "shopping";
    });
});

//套餐按钮显示的方法
function button_show_problem(goods_status) {
    //买完了,隐藏加入购物车和立即购买的按钮
    if (goods_status == sale_out) {
        var div1 = document.getElementById("shopCarButton");
        var div2 = document.getElementById("buyButton");
        var div3 = document.getElementById("sellOutButton");
        var div4 = document.getElementById("soldOutButton");
        div1.style.display = "none";
        div2.style.display = "none";
        div3.style.display = "block";
        div4.style.display = "none";
    } else if (goods_status == invalid) {
        //下架，隐藏加入购物车和立即购买的按钮
        var div1 = document.getElementById("shopCarButton");
        var div2 = document.getElementById("buyButton");
        var div3 = document.getElementById("sellOutButton");
        var div4 = document.getElementById("soldOutButton");
        div1.style.display = "none";
        div2.style.display = "none";
        div3.style.display = "none";
        div4.style.display = "block";
    } else if (goods_status == in_sale) {
        var div1 = document.getElementById("shopCarButton");
        var div2 = document.getElementById("buyButton");
        var div3 = document.getElementById("sellOutButton");
        var div4 = document.getElementById("soldOutButton");
        div1.style.display = "block";
        div2.style.display = "block";
        div3.style.display = "none";
        div4.style.display = "none";
    }
}

// “立即购买”按键点击触发事件	
function buyNow() {
    //这里根据按钮变的颜色来获取信息
    /* 单品或套餐json数据 */
    var goodsGuid = $(".bg-col-activate").siblings(".goods-guid").val();
    var goodsType = $(".bg-col-activate").siblings(".goods-type").val();

        //表单赋值
        $("#buyGoodsGuid").val(goodsGuid);
        $("#buyGoodsType").val(goodsType);

    $("#buyForm").submit();
}

/* 商品页图片转换， 小图鼠标悬浮切换事件 */
$('.small-imgs').on('mouseenter', '.prode-zoom-img', function () {
    // 动态添加的元素用普通事件$(".prode-zoom-img").mouseenter(function() {})将失效
    // 动态事件绑定
    var smallImage = $(this).attr("data-image");
    var largeImage = $(this).attr("data-zoom-image");
    // 重新加载放大镜
    var ez = $('#prode-zoom-main').data('elevateZoom');
    ez.swaptheimage(smallImage, largeImage);
    // 修改外框颜色
    $(this).siblings().find("img").removeClass("bor-col-309DE2");
    $(this).find("img").removeClass("bor-col-B2B2B2");
    $(this).find("img").addClass("bor-col-309DE2");
});

$('.small-imgs').on('mouseleave', '.prode-zoom-img', function () {
    $(this).find("img").addClass("bor-col-B2B2B2");
});

//---------------------点击评价，加载评论数据--------------------------
$(".product-info-tab").on("click", ".appraise-tab", function () {
    //每页显示数据
    var pagelimit = 10;
    var pageOffset = 0;
    loadAppraiseDatas(pageOffset, pagelimit, goodsNumber);
});

//加载订单所有商品信息
function loadAppraiseDatas(pageOffset, pagelimit, goodsNumber) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "web/call/appraiseRest?goodsNumber=" + goodsNumber + "&page=" + pageOffset + "&limit=" + pagelimit,
        error: function () {
            showEmpty();
        },
        success: function (pagers) {
            if (pagers != null) {
                //加载表格数据
                showAppraiseDatas(pagers.datas, pagers.total, pagelimit);
            } else {
                showEmpty();
            }
        }
    });
}

//动态加载元素，填充数据
function showAppraiseDatas(datas, total, pagelimit) {
    if (datas.length == 0) {
        showEmpty();
        return;
    }
    //无追评模板元素
    var module_simple = $(".tr-simple:first");
    //有追评模板元素
    var module_conflict = $(".tr-conflict:first");

    //先初始化，清空模板元素之后的所有元素
    var length = $("#evaluation").find(".module-tr").length;
    module_simple.removeClass("dis-n");
    module_conflict.removeClass("dis-n");
    if (length > 2) {  //元素个数大于数据个数，清空旧数据
        $(".module-tr:eq(1)").nextAll().remove();
    }

    for (var index in datas) {
        var userAppraise = datas[index];

        var last_div = $(".module-tr:last");
        //判断是否有追评
        if (userAppraise.appraiseTwo == null || userAppraise.appraiseTwo == "") {
            //无追评
            module_simple.clone().insertAfter(last_div);
            last_div = $(".module-tr:last");
            if (userAppraise.appraiseOne == null || userAppraise.appraiseOne == "") {
                last_div.find(".rate-content").text(name_207);  //此用户没有填写评论!
            } else {
                last_div.find(".rate-content").text(userAppraise.appraiseOne);
            }
            last_div.find(".rate-date").text(getFormatTime(userAppraise.appraiseOneTime));
            last_div.find(".rate-reply").text(userAppraise.reply);
        } else {
            //有追评
            module_conflict.clone().insertAfter(last_div);
            last_div = $(".module-tr:last");

            last_div.find(".rate-premiere .rate-content").text(userAppraise.appraiseOne);
            last_div.find(".rate-premiere .rate-date").text(getFormatTime(userAppraise.appraiseOneTime));
            last_div.find(".rate-append .rate-content").text(userAppraise.appraiseTwo);
            var diff = getDiffDay(userAppraise.appraiseTwoTime, userAppraise.appraiseOneTime);
            if (diff == 0) {
                diff = name_208;  //当天
            } else {
                diff = diff + name_209;  //x天后
            }
                last_div.find(".rate-append .rate-daydiff").text(diff);
            last_div.find(".rate-reply").text(userAppraise.reply);
            last_div.find(".rate-user-info").text(userAppraise.username);
        }
        var rateValue = userAppraise.appraiseDegree;
        //填充小星星
        last_div.find(".star-tips").eq(rateValue - 1).addClass("bg-starsel");
        last_div.find(".star-tips").eq(rateValue - 1).prevAll().addClass("bg-starsel");
    }

    //隐藏模板元素，便于分页复制元素
    module_simple.addClass("dis-n");
    module_conflict.addClass("dis-n");
    /* 加载分页 */
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        /*分页*/
        laypage.render({
            elem: 'layer-pager'
            , count: total //数据总数
            , curr: location.hash.replace('#!cur_page=', '') //获取起始页
            , hash: 'cur_page' //自定义hash值
            , limit: pagelimit
            , theme: '#309DE2'
            , jump: function (obj, first) {
                //页码 1- 10； 2-20；...
                var pageOffset = (obj.curr - 1) * pagelimit;
                if (!first) { //一定要加此判断，否则初始时会无限刷新
                    loadAppraiseDatas(pageOffset, pagelimit, goodsNumber);
                }
            }
        });
    });
}

//数据加载失败
function showEmpty() {
    //显示暂无数据
    $(".no-data-div").removeClass("dis-n");
}

//计算追评和初次评价相差天数
function getDiffDay(date1, date2) {
    //这里的date1、date2为日期的字符串
    //将date1,date2转换为Date对象
    var _dt1 = new Date(date1);
    var _dt2 = new Date(date2);
    var dt1 = _dt1.getTime();
    var dt2 = _dt2.getTime();
    return parseInt(Math.abs(dt1 - dt2) / 1000 / 60 / 60 / 24);//这里是计算天数,如果想获得周数则在该返回值上再除以7
}

/**
 * 拼接图片url，后期如果有专门的图片服务器，这里可以不用
 * @param pic_url
 * @returns
 */
function handlePicUrl(pic_url) {
    return http_request + "/resources/" + pic_url;
}

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

/**
 * 获取url参数
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

/**
 * 替换url参数 url 目标url arg 需要替换的参数名称 arg_val 替换后的参数的值 return url 参数替换后的url
 * changeURLArg('http://www.daimajiayuan.com/test.php?class_id=3&id=2','class_id',4);
 */
function changeURLArg(url, arg, arg_val) {
    var pattern = arg + '=([^&]*)';
    var replaceText = arg + '=' + arg_val;
    if (url.match(pattern)) {
        var tmp = '/(' + arg + '=)([^&]*)/gi';
        tmp = url.replace(eval(tmp), replaceText);
        return tmp;
    } else {
        if (url.match('[\?]')) {
            return url + '&' + replaceText;
        } else {
            return url + '?' + replaceText;
        }
    }
    return url + '\n' + arg + '\n' + arg_val;
}

//毫秒时间格式化
function getFormatTime(time) {
    var unixTimestamp = new Date(time);
    return unixTimestamp.toLocaleString();
}

//毫秒时间格式化
Date.prototype.toLocaleString = function () {
    var hour = handleString(new String(this.getHours()));
    var minute = handleString(new String(this.getMinutes()));
    var seconds = handleString(new String(this.getSeconds()));
    return this.getFullYear() + "." + (this.getMonth() + 1) + "." + this.getDate();
};

//一位数字前补零
function handleString(str) {
    if (str.length < 2) {
        return "0" + str;
    } else {
        return str;
    }
}