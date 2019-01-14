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
                    $("#ipt_transnum").show();
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
            table.reload('commentlist', {
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
            table.reload('commentlist', {
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

        /*设置(回复评论)部分的提交*/
        form.on('submit(reply)', function (data) {
            var UserAppraiseId = $("#UserAppraiseId").val();
            var textarea = data.field.textarea;

            layer.closeAll();
            layer.confirm('确认回复评论!', {icon: 3, title: '确认回复评论!', btn: ['确认', '我再想想']}, function (index) {
                $.ajax({
                    url: 'admin/comment/replyComment',
                    data: {
                        UserAppraiseId: UserAppraiseId,
                        textarea: textarea
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('回复评论 成功!', {icon: 6, time: 1000}, function () {
                                window.location.reload();
                            });
                        }
                        if (data.flag == false) {
                            layer.msg('系统故障!', {icon: 5, time: 1000}, function () {
                                window.location.reload();
                            });
                        }
                    }
                });
            });
            return false;
        });

        //========执行渲染============
        table.render({
            elem: '#commentlist', //指定原始表格元素选择器（推荐id选择器）
            url: 'admin/comment/commentLists',
            limit: 10,
            page: true,
            cols: [
                [ //标题栏
                    {type: 'numbers'},
                    {field: 'username', title: '评价人', width: 110, align: 'center'},
                    {field: 'goodsNumber', title: '商品编号', width: 160, align: 'center', event: 'goodsNumber'},

                    {
                        field: 'appraiseDegree',
                        title: '评分',
                        width: 120,
                        align: 'center',
                        style: 'background-color: #FDF5E6;',
                        templet: '#appraiseDegreeTpl'
                    },
                    {field: 'appraiseOne', title: '评价内容', width: 180, align: 'center', event: 'appraiseOne'},
                    {field: 'appraiseOneTime', title: '评价时间', width: 200, align: 'center'},
                    {field: 'appraiseTwo', title: '追价内容', width: 180, align: 'center', event: 'appraiseTwo'},
                    {field: 'appraiseTwoTime', title: '追价时间', width: 200, align: 'center'},

                    {field: 'replyUserGuid', title: '后台人员id', width: 100, align: 'center'},
                    {field: 'reply', title: '后台人员回复', width: 180, align: 'center'},
                    {field: 'replyTime', title: '回复时间', width: 200, align: 'center'},

                    {fixed: 'right', title: '评论', width: 100, align: 'center', toolbar: '#barDemo'}
                ]
            ],
            done: function (res, curr, count) {
                $("[data-field = 'appraiseOne']").children().each(function () {
                    if ($(this).text() == "") {
                        $(this).text("该用户还没有评价！");
                    }
                })
                $("[data-field = 'appraiseTwo']").children().each(function () {
                    if ($(this).text() == "") {
                        $(this).text("------");
                    }
                })
                $("[data-field = 'replyUserGuid']").children().each(function () {
                    if ($(this).text() == "") {
                        $(this).text("------");
                    }
                })
                $("[data-field = 'reply']").children().each(function () {
                    if ($(this).text() == "") {
                        $(this).text("------");
                    }
                })
                $("[data-field = 'appraiseOneTime']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
                $("[data-field = 'appraiseTwoTime']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
                $("[data-field = 'replyTime']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
            }
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            /*data: [
                { "id": 10000, "userid": "DTCGH24142235", "googid": "DFE126324125442", "goodname": "HK-悉心心电仪", "grade": "5星", "commentcontent": "非常好用，太好用了，非常好用，太好用了，非常好用，太好用了，非常好用，太好用了，非常好用，太好用了","commenttime": "2017/10/12 9:55:52",  "weight": "99",},
            ]*/
        });
        //监听工具条
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (obj.event === 'appraiseOne') {
                if (data.appraiseOne == null) {
                    return null;
                }
                layer.alert(data.appraiseOne, {
                    title: '评论用户名 为 &nbsp;' + data.username + '&nbsp; 的评论内容',
                });
            }
            if (obj.event === 'appraiseTwo') {
                if (data.appraiseTwo == null) {
                    return null;
                }
                layer.alert(data.appraiseTwo, {
                    title: '评论用户名 为 &nbsp;' + data.username + '&nbsp; 的追论内容',
                });
            }
            // ==== 查询商品编号  对应的商品对象
            if (obj.event === 'goodsNumber') {
                var goodsNumber = data.goodsNumber;
                var goods = null;
                $.ajax({
                    url: 'admin/comment/goods',
                    data: {
                        goodsNumberId: goodsNumber
                    },
                    async: false,
                    type: "POST",
                    success: function (data) {
                        goods = data;
                    }
                });
                $(".goodsName").html(goods.goodsName);
                $(".goodsNumber").html(goods.goodsNumber);
                $(".title").html(goods.title);
                $(".description").html(goods.description);
                layer.open({
                    type: 1,
                    title: "ID为  " + data.id + " 的商品信息",
                    area: ['600px', '580px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent'),
                });
            }
            //---- 回复评论 ------
            if (layEvent === 'replyComment') {
                $("#UserAppraiseId").val(data.id);//用户的id
                $(".detail_waybillnum1").html(data.username);//评价人
                $(".appraise_content").html(data.appraiseOne);//评价内容
                layer.open({
                    type: 1,
                    title: "回复用户名 为 &nbsp;" + data.username + "&nbsp; 的评论",
                    area: ['500px', '380px'], //宽高
                    content: $('.detailwaybill1'),
                });
            }
        });
    });
});

//获得年月日      得到日期oTime  
function getMyDate(data) {
    if (data == "") {
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