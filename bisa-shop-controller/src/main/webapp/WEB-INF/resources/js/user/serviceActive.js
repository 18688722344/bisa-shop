//每页显示数据
var pagelimit = 6;
var serviceStatus = -1;

$(document).ready(function () {
    var param = window.location.search;
    var value = getURLArg(param, "flag");
    if (value != null) {
        serviceStatus = value;
    }

    var curPage = location.hash.replace('#!cur_page=', '');
    var pageOffset = 0;
    if (curPage != "") {
        pageOffset = (curPage - 1) * pagelimit;
    }

    if (serviceStatus == null) { //默认加载全部订单
        serviceStatus = -1;
    } else {
        //切换选项卡样式
        changeTabClass(serviceStatus);
    }
    //加载服务数据
    loadServicePagerDatas(pageOffset, pagelimit);
});

//加载服务数据
function loadServicePagerDatas(pageOffset, limit) {
    var pageParam = "&page=" + pageOffset + "&limit=" + limit;
    $.ajax({
        type: "GET",
        url: "user/loadUserServiceList?status=" + serviceStatus + pageParam,
        success: function (servicePager) {
            //填充页面元素及数据
            showServiceData(servicePager.datas, servicePager.total);
        }
    });
}

//填充页面元素及数据
function showServiceData(datas, pageTotal) {
    //先初始化，清空模板元素之后的所有元素
    var moduleDiv = $(".card-main-div:first"); //模板元素
    var length = $(".main-div").find(".card-main-div").length;
    moduleDiv.removeClass("dis-n");
    if (length > 1) {  //元素个数大于数据个数，清空旧数据
        $(".main-div").html(moduleDiv);
    }
    if (pageTotal > 0) {
        //隐藏暂无数据
        $(".no-data-div").hide();
        //显示分页
        $(".service-pager").show();

        for (var index in datas) {
            var serviceRecord = datas[index];
            //克隆元素
            var lastDiv = $(".card-main-div:last");
            moduleDiv.clone().insertAfter(lastDiv);
            lastDiv = $(".card-main-div:last");

            lastDiv.find(".card-name").text(serviceRecord.cardName);
            lastDiv.find(".card-number").text(serviceRecord.cardNumber);
            lastDiv.find(".card-active-code").text(serviceRecord.activeCode);
            lastDiv.find(".card-count").text(serviceRecord.count);
            lastDiv.find(".card-unit").text(getUnit(serviceRecord.cardType));
            lastDiv.find(".create-time").text(getFormatTime(serviceRecord.createTime));
            lastDiv.find(".card-status").text(getStatus(serviceRecord.cardStatus));
            lastDiv.find(".goods_img").attr("src", serviceRecord.goodsUrl);

            //隐藏数据
            lastDiv.find(".card-number").val(serviceRecord.cardNumber);
            lastDiv.find(".card-record-json").val(JSON.stringify(serviceRecord));
            if (serviceRecord.cardStatus == "0") {
                lastDiv.find(".active-buttons").removeClass("dis-n"); //显示按钮组
            }
            if (serviceRecord.cardStatus == "1") {
                lastDiv.find(".records-buttons").removeClass("dis-n");  //显示查看历史
            }
        }
        moduleDiv.addClass("dis-n");//隐藏模板元素
        /* 加载分页 */
        loadPager(pageTotal);
    } else {
        //没有数据的时候
        //隐藏模板元素
        $(".card-main-div").addClass("dis-n");
        //显示暂无订单
        $(".no-data-div").show();
        //隐藏分页
        $(".service-pager").hide();
    }
}

/* 加载分页 */
function loadPager(pageTotal) {
    /*layui相关方法*/
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
                if (!first) { //一定要加此判断，否则初始时会无限刷新
                    loadServicePagerDatas(pageOffset, pagelimit);
                }
            }
        });
    });
}

//显示服务的状态
$(".service-status").on("click", function () {
    var ServiceDetails;
    $.ajax({
        type: "GET",
        url: "user/loadServiceDetails",
        async: false,
        success: function (data) {
            ServiceDetails = data;
        }
    });
    var html = '';
    for (var index in ServiceDetails) {
        //判断服务的类型
        if (ServiceDetails[index].serviceType == "TIMING") {
            var finishedTime = getFormatTime(ServiceDetails[index].finishedTime)
            var endTime = name749;
            html += '<label>' + endTime + '</label> <label class="ml-15">' + '(' + ServiceDetails[index].category.desc + ')&nbsp;&nbsp;' + finishedTime + '</label>';
        } else {
            var endCount = ServiceDetails[index].count;
            var endCount1 = name750;
            var count = name746;
            html += '<label>' + endCount1 + '</label> <label class="ml-15">' + '(' + ServiceDetails[index].category.desc + ')&nbsp;&nbsp;' + endCount + count + '</label>';
        }
    }
    layer.open({
        title: name748,
        content: html
    });
});

//点击，查看激活记录
$(".main-div").on("click", ".show-card-record-btn", function () {
    var data = $(this).siblings(".card-record-json").val();
    var dataObject = JSON.parse(data);

    $("#card-account").text(dataObject.account);
    $("#card-time").text(getFormatTime(dataObject.activeTime));
    layer.open({
        type: 1,
        title: name735,
        maxWidth: 380,
        maxHeight: 320,
        content: $('.record-div'),
        scrollbar: false  //不出现滚动条
    });
});

//再次确认账号弹窗
function confirmAccount(currentAccount, cardNumber) {
    var msg = name736 + currentAccount + name737;
    layer.confirm(msg, {icon: 3, title: name32}, function (index) {
        activeServiceAjax(currentAccount, cardNumber);
        layer.close(index);
    });
}

//代他人激活
$(".main-div").on("click", ".active-to-other", function () {
    var data = $(this).siblings(".card-record-json").val();
    $(".active-to-other-div").find(".card-record-json").val(data);
    layer.open({
        type: 1,
        title: name554,
        area: 'auto',
        maxWidth: 300,
        maxHeight: 320,
        content: $('.active-to-other-div'),
        scrollbar: false  //不出现滚动条
    });
});

//立即激活-为当前账户激活
$(".main-div").on("click", ".active-to-self", function () {
    var current_user = currentAccount;  //jsp页面传递，从request获取
    if (current_user == null || current_user == "null") {
        return;
    }
    var cardNumber = $(this).siblings(".card-number").val();
    confirmAccount(current_user, cardNumber);
});

//代他人激活  确认激活 按键点击事件
$("#sub-active-btn").click(function () {
    var data = $(this).siblings(".card-record-json").val();
    var dataObject = JSON.parse(data);

    var active_account = $("#active_account").val();
    var set_account = $("#set_account").val();
    if (active_account != set_account) {
        showMessage(name738);
        return;
    }
    if (set_account == null || set_account == "null") {
        showMessage(name739);
        return;
    }
    confirmAccount(set_account, dataObject.cardNumber);
    return false;
});

//激活服务的ajax
function activeServiceAjax(account, cardNumber) {
    $.ajax({
        url: "user/activeServiceCard",
        type: "POST",
        data: {
            account: account,
            cardNumber: cardNumber
        },
        error: function () {
            showMessage(getMessage("1004"));
        },
        success: function (data) {
            if (data == "200") {
                showSuccessMessage(name740);
                setTimeout(function () {
                    window.location.reload();//刷新页面
                }, 1500);
            } else {
                var msg = getMessage(data);
                showMessage(msg);
            }
        }
    });
}

//-------------------------------消息提示相关-------------------------------
//消息弹出框(失败的)
function showMessage(msg) {
    layer.msg(msg, {icon: 5});
}

//消息弹出框(成功的)
function showSuccessMessage(msg) {
    layer.msg(msg, {icon: 6});
}


function getMessage(msg) {
    if (msg == "1002") {
        return name536;
    }
    if (msg == "1004") {
        return name741;
    }
    if (msg == "1006") {
        return name742;
    }
    return name743;
}


//-------------------------------选项卡切换------------------------------------
$(".service-controlv1").click(function () { //全部
    serviceStatus = -1;
    changeTabClass(serviceStatus);
    loadServicePagerDatas(0, pagelimit);
});
$(".service-controlv2").click(function () { //未使用
    serviceStatus = 0;
    changeTabClass(serviceStatus);
    loadServicePagerDatas(0, pagelimit);
});
$(".service-controlv3").click(function () { //已使用
    serviceStatus = 1;
    changeTabClass(serviceStatus);
    loadServicePagerDatas(0, pagelimit);
});

//切换选项卡
function changeTabClass(flag) {
    if (flag == 0) {  //未使用
        $(".service-control").removeClass("col-active");
        $(".service-controlv2").addClass("col-active");
    } else if (flag == 1) {  //已使用
        $(".service-control").removeClass("col-active");
        $(".service-controlv3").addClass("col-active");
    } else {  //全部
        $(".service-control").removeClass("col-active");
        $(".service-controlv1").addClass("col-active");
    }
}

//充值面额的单位
function getUnit(cardType) {
    if (cardType == "COUNT") {
        return name746;
    }
    if (cardType == "TIMING") {
        return name747;
    }
}

//卡的使用状态
function getStatus(cardStatus) {
    if (cardStatus == "0") {
        return name744;
    }
    if (cardStatus == "1") {
        return name745;
    }
}

//json日期格式转换为正常格式
function jsonDateFormat(jsonDate) {
    var year = jsonDate.year + 1900;
    var month = jsonDate.month + 1;
    var day = jsonDate.date;
    month = (month > 9) ? ("" + month) : ("0" + month);  //如果得到的数字小于9要在前面加'0'
    day = (day > 9) ? ("" + day) : ("0" + day);
    return year + "-" + month + "-" + day;
}

//获取url参数
function getURLArg(params, arg) {
    var reg = new RegExp("(^|&)" + arg + "=([^&]*)(&|$)");
    var r = params.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
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

    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + " " + hour + ":" + minute + ":" + seconds;
};

//一位数字前补零
function handleString(str) {
    if (str.length < 2) {
        return "0" + str;
    } else {
        return str;
    }
}