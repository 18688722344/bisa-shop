/*定义加载高度函数，循环控制文章高度*/
/*这里有跨域问题，必须在服务器上测试*/

var iframeheight;
var id = setInterval("test()", 1000);

function test() {
    iframeheight = $('#maindes').contents().find('html').height();
    $('#maindes').height(iframeheight);
}

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
    //高度自适应页面加载完毕后执行一次
    $('#maindes').ready(function () {
        iframeheight = $('#maindes').contents().find('html').height();
        $('#maindes').height(iframeheight);
    });
    /*健康咨询页js*/
    wh169();

    function wh169() {
        var wh169h = $(".wh16-9").width() * 9 / 16;
        $(".wh16-9").css("height", wh169h);
    };

    var param = window.location.search;
    var new_id = getUrlParam(param, "news_id");
    loadNewDatail(new_id);
    initLastAndNext();
});

//从url里截取参数值
function getUrlParam(params, arg) {
    var reg = new RegExp("(^|&)" + arg + "=([^&]*)(&|$)");
    var r = params.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

//初始化,上一篇，下一篇
function initLastAndNext() {
    var last = $("#last-news").text();
    if (last == null || last == "") {
        $("#last-news").attr("href", "#");
        $("#last-news").text("没有了");
    } else {
    }
    var next = $("#next-news").text();
    if (next == null || last == "") {
        $("#next-news").attr("href", "#");
        $("#next-news").text("没有了");
    } else {
    }
}

//加载分页数据
function loadNewDatail(new_id) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "web/call/loadNewsDetail?news_id=" + new_id,
        success: function (datas) {
            //显示新闻数据，填充页面元素
            showNewData(datas);
        }
    });
}

//显示新闻数据
function showNewData(newDatas) {
    //先加载当前新闻数据
    var curNews = newDatas.currentDetail;
    $(".news-title").text(curNews.news_title);
    //时间需要格式化
    $(".cur-new-time").text(jsonDateFormat(curNews.release_time));
    $(".cur-new-quantity").text(curNews.read_quantity);
    $(".news-content").html(curNews.news_content);

    //加载上一篇下一篇数据
    var lastNews = newDatas.lastNew;
    if (lastNews != null) {
        $("#last-news").text(lastNews.news_title);
        var last_url = "web/call/newsContent?news_id=" + lastNews.news_id;
        $("#last-news").attr("href", last_url);
    } else {
        $("#last-news").text(none);
        $("#last-news").attr("href", "javascript:;");
    }

    var nextNews = newDatas.nextNew;
    if (nextNews != null) {
        var next_url = "web/call/newsContent?news_id=" + nextNews.news_id;

        $("#next-news").text(nextNews.news_title);
        $("#next-news").attr("href", next_url);
    } else {
        $("#next-news").text(none);
        $("#next-news").attr("href", "javascript:;");
    }
    //加载相关新闻数据
    var relativeNews = newDatas.relativeNews;

    var cloneNew = $(".news-div:first");
    if (relativeNews.length > 0) {
        //遍历json 数据，填充
        for (var index in relativeNews) {

            var news = relativeNews[index];
            //新闻信息
            var last_news = $(".news-div:last");
            /* 克隆元素拼到最后一个元素后面 */
            cloneNew.clone().insertAfter(last_news);
            /* 获得克隆后的元素 */
            last_news = $(".news-div:last");

            showNewDetail(last_news, news.news_subhead, news.news_title, news.read_quantity,
                news.news_content, news.id, news.release_time, news.img_url);
            var content_url = "window.location.href='web/call/newsContent?news_id=" + news.news_id + "'";
            last_news.attr("onclick", content_url);
        }
        cloneNew.addClass("dis-n");
    }
}

function showNewDetail(detailElement, news_subhead, news_title, read_quantity
    , news_content, news_id, release_time, img_url) {
    detailElement.find(".news-img").attr("src", img_url);//图片
    detailElement.find(".news-img").attr("alt", news_title);//标题
    detailElement.find(".news-time").text(jsonDateFormat(release_time));  //时间
    detailElement.find(".news-quantity").text(read_quantity); //阅读量
    detailElement.find(".news-subhead").text(news_subhead);
    detailElement.find(".news-title").text(news_title);
}

//json日期格式对象转换为正常格式xxxx-xx-xx
function jsonDateFormat(jsonDate) {
    var year = jsonDate.year + 1900;
    var month = jsonDate.month + 1;
    var day = jsonDate.date;
    month = (month > 9) ? ("" + month) : ("0" + month);  //如果得到的数字小于9要在前面加'0'
    day = (day > 9) ? ("" + day) : ("0" + day);
    return year + "-" + month + "-" + day;
}
