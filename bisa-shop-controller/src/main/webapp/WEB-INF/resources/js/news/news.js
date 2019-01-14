var pagelimit = 6;
var newStatus = -1;

$(document).ready(function () {


    /*头部输入框变化*/
    $(".mainsearch").click(function () {
        event.stopPropagation();
        $(".mainsearchinput").fadeIn();
    });
    $(document).click(function () {
        $('.mainsearchinput').fadeOut();
    });
    /*健康咨询页js*/
    wh169();

    function wh169() {
        var wh169h = $(".wh16-9").width() * 9 / 16;
        $(".wh16-9").css("height", wh169h);
    };

    var curPage = location.hash.replace('#!cur_page=', '')
    if (curPage == '') {
        curPage = 1;
    }
    var pageOffset = (curPage - 1) * pagelimit;
    loadNewPagerDatas(pageOffset, pagelimit);
});

// 加载分页数据
function loadNewPagerDatas(page, limit) {
    $.ajax({
        type: "GET",
        url: "web/call/loadNewsDatas?page=" + page + "&limit=" + limit,
        error: function () {
        },
        success: function (newPager) {
            //重载数据
            showNewData(newPager.datas, newPager.total);
        }
    });
}

//显示新闻数据
function showNewData(newDatas, pageTotal) {
    //先初始化，清空模板元素之后的所有元素
    var cloneNew = $(".news-div:first");
    var length = $(".mian-div").find(".news-div").length;
    cloneNew.removeClass("dis-n");
    if (length > 1) {  //元素个数大于数据个数，清空旧数据
        $(".mian-div").html(cloneNew);
    }
    if (pageTotal > 0) {
        //显示分页
        if (newDatas.length > 0) {
            for (var index in newDatas) {
                var news = newDatas[index];
                //新闻信息
                var last_news = $(".news-div:last");
                /* 克隆元素拼到最后一个元素后面 */
                cloneNew.clone().insertAfter(last_news);
                /* 获得克隆后的元素 */
                last_news = $(".news-div:last");
                showNewDetail(last_news, news.news_subhead, news.news_title, news.read_quantity, news.news_content, news.news_id, news.release_time, news.img_url);
            }
            cloneNew.addClass("dis-n");
        }
    }

    /* 加载分页 */
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        /*分页*/
        laypage.render({
            elem: 'layer-pager'
            , count: pageTotal //数据总数
            , curr: location.hash.replace('#!cur_page=', '') //获取起始页
            , hash: 'cur_page' //自定义hash值
            , limit: pagelimit
            , theme: '#309DE2'
            , jump: function (obj, first) {
                //页码 1- 10； 2-20；...
                var pageOffset = (obj.curr - 1) * pagelimit;
                if (!first) {
                    loadNewPagerDatas(pageOffset, pagelimit);
                }
            }
        });
    });
}

//新闻详情
function showNewDetail(detailElement, news_subhead, news_title, read_quantity
    , news_content, news_id, release_time, img_url) {
    detailElement.find(".news-img").attr("src", img_url);//图片
    detailElement.find(".news-img").attr("alt", news_title);//标题
    detailElement.find(".news-id").val(news_id);//标题
    var unixTimestamp = new Date(release_time);
    detailElement.find(".new-time").text(unixTimestamp.toLocaleString());  //时间
    detailElement.find(".new-quantity").text(read_quantity); //阅读量
    detailElement.find(".news-subhead").text(news_subhead);
    detailElement.find(".news-title").text(news_title);

}

//点击图片跳转
$(".mian-div").on("click", ".news-div", function () {
    var news_id = $(this).find(".news-id").val();
    console.log(news_id);
    window.location.href = "web/call/newsContent?news_id=" + news_id;
});

//格式化时间-->2018/6/6
Date.prototype.toLocaleString = function () {
    return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate();
};

