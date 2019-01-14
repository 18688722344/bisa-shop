$(document).ready(function () {

    /*layui方面js*/
    layui.use(['form', 'table', 'element', 'laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            laydate = layui.laydate,
            table = layui.table;

        /*日期部分*/
        /*按年查询*/
        laydate.render({
            elem: '#ipt_year',
            type: 'year',
            max: 0
        });
        /*按月查询*/
        laydate.render({
            elem: '#ipt_month',
            type: 'month',
            max: 0
        });
        /*按日查询*/
        laydate.render({
            elem: '#ipt_day',
            max: 0
        });
        /*下拉框选择之后的联动*/
        form.on('select(sle_type_f)', function (data) {
            switch (parseInt(data.value)) {
                case 1:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_transnum").siblings(".sel_value").val("");
                    $("#ipt_transnum").attr("lay-verify", "required");
                    break;
                case 2:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_year").siblings(".sel_value").val("");
                    $("#ipt_year").show();
                    $("#ipt_year").attr("lay-verify", "required");
                    break;
                case 3:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_month").siblings(".sel_value").val("");
                    $("#ipt_month").show();
                    $("#ipt_month").attr("lay-verify", "required");
                    break;
                case 4:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify", "");
                    $("#ipt_day").siblings(".sel_value").val("");
                    $("#ipt_day").show();
                    $("#ipt_day").attr("lay-verify", "required");
                    break;
                default:
                    break;
            }
        });

        //=============================改变按钮的颜色=====================================
        $(document).on('click', '.btn-sidebar', function () {
            $(".btn-sidebar").removeClass("layui-btn-primary");
            $(this).siblings().addClass("layui-btn-primary");
            var val = $(this).val();
            //执行重载
            table.reload('commodityList', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: {
                        val: val
                    }
                }
            });
        });

        /*查找部分的提交*/
        form.on('submit(search1)', function (data) {
            var searchabout = data.field.searchabout;
            var incontent = null;
            switch (searchabout) {
                case "1":
                    incontent = data.field.incontent;
                    break;
                case "2":
                    incontent = data.field.n_year;
                    break;
                case "3":
                    incontent = data.field.n_month;
                    break;
                case "4":
                    incontent = data.field.n_day;
                    break;
            }
            //执行重载
            table.reload('commodityList', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: {
                        searchabout: searchabout,
                        incontent: incontent
                    }
                }
            });
            return false;
        });
        //刷新 按钮
        $(document).on('click', '.btn-refresh', function () {
            window.location.reload();
        });

        //-------执行渲染--------------
        var tableIns = table.render({
            elem: '#commodityList', //指定原始表格元素选择器（推荐id选择器）
            url: 'admin/commodity/adminCommodityRest',
            page: true,//开启分页
            limit: 10,
            limits: [10, 20, 30],
            cols: [
                [ //标题栏 

                    {
                        field: 'imgUrl',
                        title: '商品图片',
                        width: 90,
                        style: 'height:100px;',
                        align: 'center',
                        templet: "#imgTpl",
                        style: 'height:70px;width:70px;line-height:70px!important;'
                    },
                    {field: 'goodsName', title: '商品名称', width: 200, align: 'center', event: 'goodsName'},

                    {field: 'goodsPrice', title: '价格', width: 130, sort: true},
                    {
                        field: 'storeNumber',
                        title: '库存',
                        width: 100,
                        sort: true,
                        align: 'center',
                        templet: '#storeNumberTpl',
                        style: 'background-color: #eee;'
                    },
                    {field: 'goodsStatus', title: '商品状态', width: 100, align: 'center', templet: '#commodityTypeTpl'},
                    {field: 'goodsNumber', title: '商品编号', width: 200, align: 'center'},
                    {field: 'salesNumber', title: '销量', width: 100, sort: true, align: 'center'},
                    {field: 'appraiseNumber', title: '评论数', width: 100, sort: true, align: 'center'},
                    {field: 'needPost', title: '是否邮递', width: 100, align: 'center', templet: '#postTpl'},
                    {field: 'putawayTime', title: '上架时间', width: 150, align: 'center'},
                    {title: '操作', width: 440, toolbar: '#barDemo', align: 'left'},
                ]
            ],
            done: function (res, curr, count) {
                hoverOpenImg();//显示大图

                $("[data-field = 'putawayTime']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(parseInt(data)));
                    }
                });

                //格式化 金额
                $("[data-field = 'goodsPrice']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        if (money == 0) {
                            $(this).text("----");
                        } else {
                            $(this).text(outputmoney(money));
                        }
                    }
                })
            }
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            /*data: [
                { "id": "321", "orderid": "KSD1514515461SD414541", "goodscontent": "套餐A*1 </br>服务B（3个月）*1</br> 套餐B*5 套餐C*3</br>", },
            ],*/
        });

        //==============================监听工具条=================
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //查看商品的信息
            if (layEvent === 'detail') {
                var goodsImgList = null;//获取商品下面的所有图片
                var goodsObject = null;//没有图片得时候去获取goods对象拿主图



                $.ajax({
                    url: 'admin/commodity/getGoodsImgList',
                    data: {
                        goodsNumber: data.goodsNumber,
                        internationalization:2
                    },
                    type: "GET",
                    async: false,
                    success: function (obj) {
                        goodsImgList = obj;

                        if (obj == "" || obj == null) {
                            $.ajax({
                                url: 'admin/commodity/getGoodsObject',
                                data: {
                                    goodsNumber: data.goodsNumber

                                },
                                type: "GET",
                                async: false,
                                success: function (data) {
                                    goodsObject = data;
                                }
                            });
                        }
                    }
                });

                var html = '';
                if (goodsImgList == "") {
                    html += '<img alt="图片"  src="' + goodsObject.imgUrl + '" style="cursor:pointer;height: 100px;width: 100px;">'
                } else {
                    for (var index in goodsImgList) {
                        html += '<img alt="图片"  src="' + goodsImgList[index].imgUrl + '" style="cursor:pointer;height: 100px;width: 100px;">'
                    }
                }
                document.getElementById("imgUrl").innerHTML = html;
                //设值
                /*$("#imgUrl").attr("src",data.imgUrl);*/
                $(".goodsName").html(data.goodsName);
                $(".goodsNumber").html(data.goodsNumber);
                $(".goodsPrice").html(outputmoney(data.goodsPrice.toString()));
                $(".title").html(data.title);
                $(".description").html(description(data.description));

                $(".needPost").html(needPost(data.needPost));
                $(".salesNumber").html(data.salesNumber);
                $(".storeNumber").html(data.storeNumber);
                $(".appraiseNumber").html(data.appraiseNumber);
                $(".goodsStatus").html(goodsStatus(data.goodsStatus));
                $(".putawayTime").html(getMyDate(data.putawayTime));
                $(".soldoutTime").html(getMyDate(data.soldoutTime));
                hoverOpenImg_dail();
                layer.open({
                    type: 1,
                    title: "商品名称 为 &nbsp;" + data.goodsName + "&nbsp; 的基本信息",
                    area: ['900px', '700px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent'),
                });
            }
            if (layEvent === 'delete'){
                var data = obj.data; //获得当前行数据
                layer.confirm('需要要删除此商品么？', function (index) {
                $.ajax({
                    url:'admin/commodity/delectGoods',
                    data:{
                        goods_number:data.goodsNumber
                    },
                    type: "GET",
                    async: false,
                    success:function (data){
                       if (data.flag==true){
                           layer.msg('删除成功 ！', {icon: 6, time: 1000}, function () {
                               window.location.reload();
                           });
                       }
                       if (data.flag==false){
                           layer.msg('异常！重试或联系运维人员！', {icon: 5, time: 1000}, function () {
                           });
                       }

                }
                });
                });
            }
            if (obj.event === 'goodsName') {
                layer.alert(data.goodsName, {
                    title: '商品ID 为 &nbsp;' + data.id + '&nbsp; 的商品名称',
                });
            }
        });
    });
});

/*//查看按钮图片放大
function bigger(data) {
    var img = $(data)[0];
    img.style.width = '380px';
    img.style.height = '380px';
}

//查看按钮图片缩小图片
function smaller(data) {
    var img = $(data)[0];
    img.style.width = '100px';
    img.style.height = '100px';
}*/

//商品是否邮递
function needPost(data) {
    if (data == noPost) {
        return '<span style="color: blue">否</span>';
    }
    if (data == need_Post) {
        return '<span style="color: #9900cc">是</span>';
    }
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

//商品的描述
function description(data) {
    if (data == null || data == "") {
        return "---------";
    } else {
        return data;
    }
}

//显示大图
function hoverOpenImg() {
    var img_show = null; // tips提示
    $('td img').hover(function () {
        var img = "<img class='img_msg' src='" + $(this).attr('src') + "' style='width:300px;' />";
        img_show = layer.tips(img, this, {
            tips: [2, 'rgba(41,41,41,.5)']
            , area: ['330px']
        });
    }, function () {
        layer.close(img_show);
    });
    $('td img').attr('style', 'max-width:70px');
}

//显示大图
function hoverOpenImg_dail() {
    var img_show = null; // tips提示
    $('#imgUrl img').hover(function () {
        var img = "<img class='img_msg' src='" + $(this).attr('src') + "' style='width:300px;' />";
        img_show = layer.tips(img, this, {
            tips: [2, 'rgba(41,41,41,.5)']
            , area: ['330px']
        });
    }, function () {
        layer.close(img_show);
    });
}

//获得年月日      得到日期oTime  
function getMyDate(data) {
    if (data == "" || data == null) {
        return "- - - - - -";
    }
    data = parseInt(data);
    //var oldTime = (new Date(data)).getTime(); //得到毫秒数
    var oDate = new Date(data);
    oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear + '年' + getzf(oMonth) + '月' + getzf(oDay) + '日';//最后拼接时间
    return oTime;
};

//补0操作  
function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
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
