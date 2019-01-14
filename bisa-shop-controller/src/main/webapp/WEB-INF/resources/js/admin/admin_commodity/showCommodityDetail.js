$(document).ready(function () {

    //异步加载  goods数据
    $.ajax({
        url: 'admin/commodity/getGoodsDetail',
        type: "POST",
        success: function (data) {
            var obj = eval('(' + data + ')');
            var goodsObject = null;//没有图片得时候去获取goods对象拿主图

            if (obj.GoodsImgList == "" || obj.GoodsImgList == null) {
                $.ajax({
                    url: 'admin/commodity/getGoodsObject',
                    data: {
                        goodsNumber: obj.goods.goodsNumber
                    },
                    type: "POST",
                    async: false,
                    success: function (data) {
                        goodsObject = data;
                    }
                });
            }

            var html = '';
            if (obj.GoodsImgList == "") {
                html += '<img alt="图片" src="' + goodsObject.imgUrl + '" style="cursor:pointer;height: 100px;width: 100px;">'
            } else {
                for (var index in obj.GoodsImgList) {
                    html += '<img alt="图片" src="' + obj.GoodsImgList[index].imgUrl + '" style="cursor:pointer;height: 100px;width: 100px;">'
                }
            }
            document.getElementById("goodsImgs").innerHTML = html;
            //设值
            $(".goodsName").html(obj.goods.goodsName);
            $(".goodsNumber").html(obj.goods.goodsNumber);
            $(".goodsPrice").html(outputmoney(obj.goods.goodsPrice.toString()));
            $(".title").html(obj.goods.title);
            $(".description").html(obj.goods.description);

            $(".needPost").html(needPost(obj.goods.needPost));
            $(".storeNumber").html(obj.goods.storeNumber);
            $(".salesNumber").html(obj.goods.salesNumber);
            $(".appraiseNumber").html(obj.goods.appraiseNumber);
            $(".goodsStatus").html(goodsStatus(obj.goods.goodsStatus));
            $(".putawayTime").html(getMyDate(obj.goods.putawayTime));
            $(".soldoutTime").html(getMyDate(obj.goods.soldoutTime));
        }
    });
});


//获得年月日      得到日期oTime
function getMyDate(data) {
    if (data == "" || data == null) {
        return "- - - - - -";
    }

    data = parseInt(data);

    var oldTime = (new Date(data)).getTime(); //得到毫秒数
    var oDate = new Date(oldTime);
    oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear + '年' + getzf(oMonth) + '月' + getzf(oDay) + '日     ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
    return oTime;
};

//补0操作  
function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}

//商品的状态
function goodsStatus(data) {
    if (data == in_sale) {
        return '<span style="color: blue">销售 中</span>';
    }
    if (data == invalid) {
        return '<span style="color: red">下架 了</span>';
    }
    if (data == sold_out) {
        return '<span style="color: #9900cc">售完 了</span>';
    }
}

//商品是否邮递
function needPost(data) {
    if (data == noPost) {
        return '<span style="color: blue">否</span>';
    }
    if (data == need_Post) {
        return '<span style="color: #9900cc">是</span>';
    }
}

//将数字转换成金额显示
function outputmoney(number) {
    number = number.replace(/\,/g, "");
    if (isNaN(number) || number == "") return "";
    number = Math.round(number * 100) / 100;
    if (number < 0)
        return '-' + outputdollars(Math.floor(Math.abs(number) - 0) + '') + outputcents(Math.abs(number) - 0);
    else
        return "HKD" + outputdollars(Math.floor(number - 0) + '') + outputcents(number - 0);
}

//格式化金额
function outputdollars(number) {
    if (number.length <= 3)
        return (number == '' ? '0' : number);
    else {
        var mod = number.length % 3;
        var output = (mod == 0 ? '' : (number.substring(0, mod)));
        for (i = 0; i < Math.floor(number.length / 3); i++) {
            if ((mod == 0) && (i == 0))
                output += number.substring(mod + 3 * i, mod + 3 * i + 3);
            else
                output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
        }
        return (output);
    }
}

function outputcents(amount) {
    amount = Math.round(((amount) - Math.floor(amount)) * 100);
    return (amount < 10 ? '.0' + amount : '.' + amount);
}