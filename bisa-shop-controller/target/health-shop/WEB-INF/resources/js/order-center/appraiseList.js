//每页显示数据
var pagelimit = 6;

$(document).ready(function () {
    var curPage = location.hash.replace('#!cur_page=', '');
    var pageOffset = 0;
    if (curPage != "") {
        pageOffset = (curPage - 1) * pagelimit;
    }
    //加载用户评价分页
    loadUserAppraisePager(pageOffset, pagelimit);
});

//加载订单所有商品信息
function loadUserAppraisePager(pageOffset, pagelimit) {
    $.ajax({
        type: "GET",
        url: "user/userAppraiseListRest?page=" + pageOffset + "&limit=" + pagelimit,
        success: function (pagers) {
            if (pagers != null) {
                //加载表格数据
                showAppraiseDatas(pagers.datas, pagers.total);
            } else {
                showEmpty();
            }
        }
    });
}

//动态加载元素
function showAppraiseDatas(datas, total) {
    if (datas.length == 0) {
        showEmpty();
        return;
    }
    //无追评模板元素
    var module_simple = $(".tr-simple:first");
    //有追评模板元素
    var module_conflict = $(".tr-conflict:first");

    //先初始化，清空模板元素之后的所有元素
    var length = $(".main-container").find(".module-tr").length;
    module_simple.removeClass("dis-n");
    module_conflict.removeClass("dis-n");
    if (length > 2) {  //元素个数大于数据个数，清空旧数据
        $(".module-tr:eq(1)").nextAll().remove();
    }

    for (var index in datas) {
        var userAppraise = datas[index].userAppraise;
        var goodsName = datas[index].goodsName;
        var imgUrl = datas[index].imgUrl;

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
        }

        last_div.find(".goods-img").attr("src", imgUrl);
        last_div.find(".goods-name").text(goodsName);
        last_div.find(".appraise-id").val(userAppraise.id);

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
                    loadUserAppraisePager(pageOffset, pagelimit);
                }
            }
        });
    });

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

//数据加载失败
function showEmpty() {
    //显示暂无数据
    $(".no-data-div").removeClass("dis-n");
}

//发表评论 按键点击事件
$(".main-container").on("click", ".submit-appraise", function () {
    var id = $(this).siblings(".appraise-id").val();
    var appraiseTwo = $(this).parent().parent().find(".appraises-two").val();
    //提交表单
    commitAppraise(id, appraiseTwo);
});

//发表评论  提交表单
function commitAppraise(id, appraiseTwo) {
    $.ajax({
        type: "POST",
        url: "user/commitAppraiseTwo",
        data: {
            id: id,
            appraiseTwo: appraiseTwo
        },
        error: function () {
            showEmpty();
        },
        success: function (data) {
            if (data == "200") {
                layer.msg(name213, {icon: 6, time: 1500}, function () {
                    window.location.reload();
                });
            } else {
                layer.msg(name537, {icon: 5, time: 1500}, function () {
                    window.location.reload();
                });
            }
        }
    });
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
