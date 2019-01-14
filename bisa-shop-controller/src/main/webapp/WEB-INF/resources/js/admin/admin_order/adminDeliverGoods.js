$(document).ready(function () {

    //刷新  页面按钮
    $(".btn-refresh").click(function () {
        window.location.reload();
    });

    //=======================layui方面js========================================
    layui.use(['form', 'table', 'element', 'jquery'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;
        $ = layui.jquery;

        //=============================改变按钮的颜色=====================================
        $(document).on('click', '.btn-sidebar', function () {
            $(".btn-sidebar").removeClass("layui-btn-primary");
            $(this).siblings().addClass("layui-btn-primary");
            var val = $(this).val();
            //执行重载
            table.reload('DeliverList', {
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
            //执行重载
            table.reload('DeliverList', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: {
                        incontent: $('#incontent').val(),
                        searchabout: $('#searchabout').val()
                    }
                }
            });
            return false;
        });

        /*设置(运单号)部分的提交===============================*/
        form.on('submit(sub_setway)', function (data) {
            /*获取此时的order表的ID*/
            var orderId = $(".ipt_waybill_id").val();

            var myselect = document.getElementById("ipt_waybill_name");
            var index = myselect.selectedIndex;
            var options = myselect.options[index].text;
            layer.closeAll();
            layer.confirm('最后确定   运单号!', {icon: 3, title: '设置运单号', btn: ['确定', '我再想想']}, function (index) {
                //提交了 运单号，后台改为已发货
                $.ajax({
                    url: 'admin/order/updateDeliverOrderStatus',
                    data: {
                        logisticsNumber: data.field.ipt_waybill,
                        //logisticsName : data.field.ipt_waybill_name,
                        logisticsName: options,
                        orderId: orderId
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('运单号  设置成功!', {icon: 6, time: 1000}, function () {
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
        /*设置(物流售后审核 通过)部分的提交*/
        form.on('submit(check_success)', function (data) {
            var order_no = $(".detail_waybillnum1").html();
            var user_id = $(".userId").val();
            var description = data.field.description;
            var textarea = data.field.textarea;

            layer.closeAll();
            layer.confirm('确认审核通过!', {icon: 3, title: '确认审核通过', btn: ['确认', '我再想想']}, function (index) {
                $.ajax({
                    url: 'admin/order/checkAfterSaleSuccess',
                    data: {
                        orderNO: order_no,
                        userId: user_id,
                        description: description,
                        textarea: textarea
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('审核通过 成功!', {icon: 6, time: 1000}, function () {
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
        /*设置(物流 售后审核 拒绝)部分的提交*/
        form.on('submit(check_fail)', function (data) {
            var order_no = $(".detail_waybillnum1").html();
            var user_id = $(".userId").val();
            var description = data.field.description;
            var textarea = data.field.textarea;

            layer.closeAll();
            layer.confirm('确认审核拒绝!', {icon: 3, title: '确认审核拒绝', btn: ['确认', '我再想想']}, function (index) {
                $.ajax({
                    url: 'admin/order/checkAfterSaleFail',
                    data: {
                        orderNO: order_no,
                        userId: user_id,
                        description: description,
                        textarea: textarea
                    },
                    type: "POST",
                    success: function (data) {
                        if (data.flag == true) {
                            layer.msg('审核拒绝  成功!', {icon: 6, time: 1000}, function () {
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
        //=================执行渲染==================
        table.render({
            elem: '#DeliverList', //指定原始表格元素选择器（推荐id选择器）
            id: 'DeliverList',
            url: 'admin/order/selectDeliverGoodsOrderList',
            limit: 10,
            page: true,
            cols: [
                [ //标题栏
                    {type: 'checkbox'},
                    {field: 'id', title: 'ID', width: 60, event: 'id', sort: true, align: 'center'},
                    {field: 'order_no', title: '订单号', width: 250, align: 'center'},
                    {field: 'user_id', title: '购买人', width: 80, event: 'user_id', align: 'center'},
                    {field: 'consignee', title: '收货人', width: 100, align: 'center'},
                    {field: 'phone', title: '收货人手机', width: 130, align: 'center'},
                    {field: 'detail_address', title: '收货地址', width: 300, event: 'detailAddress'},

                    {
                        field: 'tra_status',
                        title: '支付状态',
                        width: 110,
                        sort: true,
                        style: 'background-color: #FDF5E6;',
                        align: 'center',
                        templet: '#traStatusTpl'
                    },
                    {field: 'pay_type', title: '支付类型', width: 110, sort: true, align: 'center', templet: '#payTypeTpl'},
                    {
                        field: 'after_sale_apply',
                        title: '售后状态',
                        width: 150,
                        sort: true,
                        style: 'background-color: #FDF5E6;',
                        align: 'center',
                        templet: '#afterSaleApplyTpl'
                    },

                    {field: 'total_price', title: '商品总额', width: 120, sort: true},
                    {field: 'preferential_price', title: '优惠价格', width: 120, sort: true},
                    {field: 'post_price', title: '邮费', width: 120, sort: true},
                    {field: 'actual_payment', title: '实付款', width: 120, sort: true},

                    {field: 'logistics_name', title: '物流公司', width: 100, align: 'center'},
                    {field: 'logistics_number', title: '运单号', width: 240, align: 'center'},
                    {field: 'create_time', title: '下单时间', width: 200, align: 'center'},
                    {field: 'pay_time', title: '支付时间', width: 200, align: 'center'},
                    {field: 'deliver_goods_time', title: '发货时间', width: 200, align: 'center'},
                    {field: 'receive_goods_time', title: '收货时间', width: 200, align: 'center'},
                    {fixed: 'right', title: '查看详细', width: 200, toolbar: '#barDemo'}
                ]
            ],
            done: function (res, curr, count) {
                $("[data-field = 'logistics_name']").children().each(function (index) {
                    if (index != 0) {
                        var logistics_name = $(this).text();
                        if (logistics_name == "") {
                            $(this).text("-----");
                        } else {
                            $(this).text(logistics_name);
                        }
                    }
                })
                $("[data-field = 'logistics_number']").children().each(function (index) {
                    if (index != 0) {
                        var logistics_number = $(this).text();
                        if (logistics_number == "") {
                            $(this).text("-----");
                        } else {
                            $(this).text(logistics_number);
                        }
                    }
                })
                $("[data-field = 'total_price']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                })
                $("[data-field = 'preferential_price']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                })
                $("[data-field = 'post_price']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                })
                $("[data-field = 'actual_payment']").children().each(function (index) {
                    if (index != 0) {
                        var money = $(this).text();
                        $(this).text(outputmoney(money));
                    }
                })
                $("[data-field = 'create_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
                $("[data-field = 'pay_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
                $("[data-field = 'deliver_goods_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
                $("[data-field = 'receive_goods_time']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(data));
                    }
                })
            }
            /*在这里使用的是静态数据，参考layui文档，使用服务器上的数据进行更替*/
            /* data: [
                 { "id": 10003, "ordernum": "GRECBEW41424541541", "waybillnum": "ETTYCCXBF418524541541", "goodscontent": "血压计(*3)", "consignee": "李先森", "phone": "16698569854", "address": "深圳罗湖区34716号", "time": "2017/10/12 9:55:52", "status": "已发货", "logistics": "顺丰空运", },
             ],*/
        });

        //==============================监听工具条  ==============================================
        table.on('tool(DLtest)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //查看 售后 原因
            if (layEvent === 'detailASA') {
                var dataId = data.id;
                var user_id = data.user_id;
                var order_no = data.order_no;
                var goodscontent;//售后的原因
                $.ajax({
                    url: 'admin/order/AfterSalesCause',
                    data: {
                        userId: user_id,
                        orderNo: order_no
                    },
                    async: false,
                    type: "POST",
                    success: function (data) {
                        goodscontent = data;
                    }
                });
                //设值
                $(".uriPic1").attr("src", goodscontent.uriPic);
                $(".detail_consignee1").html(goodscontent.username);
                $(".detail_type").html(toAfterSalesType(goodscontent.afterSalesType));
                $(".detail_orderNO").html(goodscontent.orderNo);
                $(".detail_cause").html(goodscontent.afterForCause);
                $(".detail_time1").html(goodscontent.createTime);
                layer.open({
                    type: 1,
                    title: "ID为 " + data.id + " 的基本信息",
                    area: ['500px', '580px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent1'),
                });
            }
            //待审核
            if (layEvent === 'check') {
                $(".userId").val(data.user_id);//用户的id
                $(".detail_waybillnum1").html(data.order_no);//订单号
                layer.open({
                    type: 1,
                    title: "审核订单号为  " + data.order_no + " 的信息",
                    area: ['500px', '420px'], //宽高
                    content: $('.detailwaybill1'),
                });
            }
            //查看订单
            if (layEvent === 'detail') {
                var dataId = data.id;
                var goodscontent;//所有的商品 名称和数量
                $.ajax({
                    url: 'admin/order/goodscontent',
                    data: {
                        orderId: dataId
                    },
                    async: false,
                    type: "POST",
                    success: function (data) {
                        goodscontent = data;
                    }
                });
                //"套餐A*1 </br>服务B（3个月）*1</br> 套餐B*5
                var content = "";//格式化  数据
                for (var index in goodscontent) {
                    content += goodscontent[index].goodsName + "  *  " + goodscontent[index].count + "</br>"
                }
                //设值
                $(".detail_consignee").html(data.consignee);
                $(".detail_phone").html(data.phone);
                $(".detail_address").html(data.detail_address);
                $(".detail_ordernum").html(data.order_no);
                $(".detail_goodscontent").html(content);
                $(".detail_status").html(changeTraStatus(data.tra_status));
                $(".detail_logistics").html(logisticsName(data.logistics_name));
                $(".detail_waybillnum").html(logisticsNumber(data.logistics_number));
                $(".detail_time").html(getMyDate(data.create_time));
                layer.open({
                    type: 1,
                    title: "ID为 " + data.id + " 的基本信息",
                    area: ['700px', '700px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent'),
                });
            }
            ;
            //编辑 运单号
            if (layEvent === 'edit') {
                $(".ipt_waybill_id").val(data.id);//订单的ID
                $(".detail_waybillnum").html(data.order_no);//订单号
                layer.open({
                    type: 1,
                    title: "编辑ID为  " + data.id + " 的运单信息",
                    area: ['500px', '360px'], //宽高
                    content: $('.detailwaybill'),
                });
            }
            ;

            //加载用户订单下面的所有商品信息
            if (obj.event === 'id') {
                var dataId = data.id;
                var goodscontent;
                $.ajax({
                    url: 'admin/order/goodscontent',
                    data: {
                        orderId: dataId
                    },
                    type: "POST",
                    async: false,
                    success: function (data) {
                        goodscontent = data;
                    }
                });
                //"套餐A*1 </br>服务B（3个月）*1</br> 套餐B*5
                var content = "";//格式化  数据
                for (var index in goodscontent) {
                    content += goodscontent[index].goodsName + "  *  " + goodscontent[index].count + "</br>"
                }
                //设值
                $(".productName").html(content);
                layer.open({
                    type: 1,
                    title: "ID为 " + data.id + " 的商品基本信息",
                    area: ['500px', '450px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent3'),
                });
            }
            //加载用户的信息
            if (obj.event === 'user_id') {
                var user_id = data.user_id;
                var userinfo;//userinfo对象信息
                //上面不加载 用户的信息，点击以后在加载  减小压力
                $.ajax({
                    url: 'admin/order/userInfo',
                    data: {
                        userId: user_id
                    },
                    type: "POST",
                    async: false,
                    success: function (data) {
                        userinfo = data;
                    }
                });
                //设值
                $(".uriPic").attr("src", userinfo.uriPic);
                $(".name").html(userinfo.name);
                $(".sex").html(getSex(userinfo.sex));
                $(".age").html(userinfo.age);
                $(".weight").html(userinfo.weight);
                $(".height").html(userinfo.height);
                $(".birthday").html(userinfo.birthday);
                layer.open({
                    type: 1,
                    title: "ID为 " + data.id + " 的用户基本信息",
                    area: ['500px', '620px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent2'),
                });
            }
            if (obj.event === 'detailAddress') {
                layer.alert(data.detail_address, {
                    title: '订单ID 为 &nbsp;' + data.id + '&nbsp; 的收货地址',
                });
            }
            //取消用户订单
            if (layEvent === 'cancel') { //取消
                layer.confirm('真的取消该订单么', function (index) {
                    var orderId = data.id;

                    $.ajax({
                        url: 'admin/order/cancelOrders',
                        data: {
                            orderId: orderId
                        },
                        type: "POST",
                        success: function (data) {
                            if (data.flag == true) {
                                layer.msg('取消订单 成功!', {icon: 6, time: 1000}, function () {
                                    obj.del(); //取消对应行（tr）的DOM结构，并更新缓存
                                    layer.close(index);
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
            }
        });

        //===============================打印发货单相关操作===========================================
        var $ = layui.$, active = {
            getCheckData: function () {
                /*获取选中的id*/
                var checkStatus = table.checkStatus('DeliverList');
                var data = checkStatus.data;

                /*判断data的数量是否为0*/
                if (data.length == 0) {
                    layer.alert('请先选择需要打印的订单。', {
                        icon: 6,
                        title: '打印发货单提示'
                    })
                } else {
                    var jsonprintid = [];
                    var manifest_box_content = "";
                    for (var i = 0; i < data.length; i++) {
                        jsonprintid.push(data[i].id);
                        /*把订单添加到相应位置*/
                        manifest_box_content += "<p class=\"clear h-40 line-h-40 col-red\">";
                        manifest_box_content += data[i].order_no;
                        manifest_box_content += "<\/p>";
                    }
                    $(".manifest_box").html(manifest_box_content);
                    layer.open({
                        type: 1,
                        title: "打印发货单",
                        area: ['560px', '700px'], //宽高
                        btn: ['打印', '取消'],
                        btn1: function (index, layero) {
                            /*开启加载loading层*/
                            var index = layer.load(1);
                            var orderlist_data = null;
                            /*在这里进行ajax调用返回json数据*/
                            $.ajax({
                                url: 'admin/order/getPrintOrderList',
                                data: {
                                    jsonprintid: jsonprintid
                                },
                                type: "POST",
                                async: false,
                                success: function (data) {
                                    orderlist_data = data;
                                }
                            });
                            /*定义一条测试数据*/
                            //var orderlist_data = {"order_book":[{"name":"和小凯","order_no":"JKADJFI484184818481","start_time":"2017/11/15 22:10:59","county_address":"深圳市宝安区后亭社区全至科技创新园10E","count_total":"800","sub_total":"￥800.00","post_price":"￥0.00","price_total":"￥2800000.00","page_side":[{"product_name":"HC3A250 悉心心电仪","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"HC3A250 悉心心电仪2","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"HC3A250 悉心心电仪3","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"HC3A250 悉心心电仪4","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"HC3A250 悉心心电仪5","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"HC3A250 悉心心电仪6","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"HC3A250 悉心心电仪7","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"HC3A250 悉心心电仪8","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"HC3A250 悉心心电仪9","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"电极片","ascription_guid":"qwesq7a12321321382c8ee11f781d463","count":"160","price":"￥3200.00"}]},{"name":"和小凯2","order_no":"JKADJFI484184818482","start_time":"2018/11/15 22:10:59","county_address":"香港","count_total":"800","sub_total":"￥800.00","post_price":"￥0.00","price_total":"￥2800000.00","page_side":[{"product_name":"HC3A250 悉心心电仪21212","ascription_guid":"456457ad87f6491982c8ee11f781d463","count":"30","price":"￥54000.00"},{"product_name":"电极片121212","ascription_guid":"qwesq7a12321321382c8ee11f781d463","count":"160","price":"￥3200.00"}]}]};
                            /*在此处进行打印内容的拼接操作*/
                            printorderlist();

                            function printorderlist() {
                                var page = orderlist_data.order_book;
                                var page_length = orderlist_data.order_book.length;
                                var html_page = "";
                                for (i = 0; i < page_length; i++) {
                                    /*定义第一段需要拼接的html*/
                                    var html_page_tipsv1 = "";
                                    html_page_tipsv1 += "<div class=\"clear pd-15\" style=\"page-break-after: always;\">";
                                    html_page_tipsv1 += "<p class=\"f-18 text-center col-black h-25 line-h-25\">";
                                    html_page_tipsv1 += "    BISA";
                                    html_page_tipsv1 += "<\/p>";
                                    html_page_tipsv1 += "<p class=\"f-20 f-w text-center col-black h-25 line-h-25 mb-15 family-s\">";
                                    html_page_tipsv1 += "    碧沙康健";
                                    html_page_tipsv1 += "<\/p>";
                                    html_page_tipsv1 += "<div class=\"clear full-w bor bor-b\">";
                                    html_page_tipsv1 += "<\/div>";
                                    html_page_tipsv1 += "<div class=\"clear full-w mt-15 f-12\">";
                                    html_page_tipsv1 += "    <div class=\"clear layui-col-xs6 line-h-20 col-black\">";
                                    html_page_tipsv1 += "        <span class=\"f-w col-black\">收货人姓名：<\/span>" + page[i].name;
                                    html_page_tipsv1 += "    <\/div>";
                                    html_page_tipsv1 += "    <div class=\"clear layui-col-xs6 line-h-20 col-black\">";
                                    html_page_tipsv1 += "        <span class=\"f-w col-black\">订单编号：<\/span>" + page[i].order_no;
                                    html_page_tipsv1 += "    <\/div>";
                                    html_page_tipsv1 += "<\/div>";
                                    html_page_tipsv1 += "<div class=\"clear full-w mt-10 f-12\">";
                                    html_page_tipsv1 += "    <div class=\"clear layui-col-xs12 line-h-20 col-black\">";
                                    html_page_tipsv1 += "        <span class=\"f-w col-black\">下单日期：<\/span>" + page[i].start_time;
                                    html_page_tipsv1 += "    <\/div>";
                                    html_page_tipsv1 += "<\/div>";
                                    html_page_tipsv1 += "<div class=\"clear full-w mt-10 f-12 mb-15\">";
                                    html_page_tipsv1 += "    <div class=\"clear layui-col-xs12 line-h-20 col-black\">";
                                    html_page_tipsv1 += "        <span class=\"f-w col-black\">收货人地址：<\/span>" + page[i].county_address;
                                    html_page_tipsv1 += "    <\/div>";
                                    html_page_tipsv1 += "<\/div>";
                                    html_page_tipsv1 += "<div class=\"clear full-w bor bor-b\">";
                                    html_page_tipsv1 += "<\/div>";

                                    /*定义第一段需要拼接的html结束*/
                                    /*定义第二段需要拼接的html内容*/
                                    var page_side = page[i].page_side;
                                    var page_side_length = page[i].page_side.length;
                                    var html_page_tipsv2 = "";
                                    for (j = 0; j < page_side_length; j++) {
                                        /*定义第二段内部需要拼接的html内容*/
                                        var html_page_tipsv2_side = "";
                                        html_page_tipsv1 += "<div class=\"clear full-w mt-10 f-12 mb-15\">";
                                        html_page_tipsv1 += "    <div class=\"clear layui-col-xs12 line-h-20 col-black\">";
                                        html_page_tipsv1 += "        <span class=\"f-w col-black\">商品名：<\/span>" + page_side[j].product_name;
                                        html_page_tipsv1 += "    <\/div>";
                                        html_page_tipsv1 += "<\/div>";

                                        html_page_tipsv1 += "<div class=\"clear full-w mt-10 f-12 mb-15\">";
                                        html_page_tipsv1 += "    <div class=\"clear layui-col-xs12 line-h-20 col-black\">";
                                        html_page_tipsv1 += "        <span class=\"f-w col-black\">商品编号：<\/span>" + page_side[j].ascription_guid;
                                        html_page_tipsv1 += "    <\/div>";
                                        html_page_tipsv1 += "<\/div>";

                                        html_page_tipsv1 += "<div class=\"clear full-w mt-10 f-12 mb-15\">";
                                        html_page_tipsv1 += "    <div class=\"clear layui-col-xs12 line-h-20 col-black\">";
                                        html_page_tipsv1 += "        <span class=\"f-w col-black\">商品数量：<\/span>" + page_side[j].count;
                                        html_page_tipsv1 += "    <\/div>";
                                        html_page_tipsv1 += "<\/div>";

                                        html_page_tipsv1 += "<div class=\"clear full-w mt-10 f-12 mb-15\">";
                                        html_page_tipsv1 += "    <div class=\"clear layui-col-xs12 line-h-20 col-black\">";
                                        html_page_tipsv1 += "        <span class=\"f-w col-black\">单项总价：<\/span>" + page_side[j].price;
                                        html_page_tipsv1 += "    <\/div>";
                                        html_page_tipsv1 += "<\/div>";

                                        /*定义第二段需要拼接的html内容结束*/
                                        html_page_tipsv2 += html_page_tipsv2_side;
                                    }
                                    /*定义第二段需要拼接的html内容结束*/
                                    /*定义第三段需要拼接的html*/
                                    var html_page_tipsv3 = "";
                                    html_page_tipsv3 += "<div class=\"clear full-w mt-40 f-12 mb-15\">";
                                    html_page_tipsv3 += "    <div class=\"clear layui-col-xs4 layui-col-xs-offset8 pl-0 pr-0\">";
                                    html_page_tipsv3 += "        <span class=\"f-w col-black dis-ib pull-left\">商品总数：<\/span>";
                                    html_page_tipsv3 += "        <span class=\"col-black dis-ib pull-right\">" + page[i].count_total + "<\/span>";
                                    html_page_tipsv3 += "    <\/div>";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w bor bor-b\">";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w mt-15 f-12\">";
                                    html_page_tipsv3 += "    <div class=\"clear layui-col-xs4 layui-col-xs-offset8 pl-0 pr-0\">";
                                    html_page_tipsv3 += "        <span class=\"f-w col-black dis-ib pull-left\">小计：<\/span>";
                                    html_page_tipsv3 += "        <span class=\"col-black dis-ib pull-right\">" + page[i].sub_total + "<\/span>";
                                    html_page_tipsv3 += "    <\/div>";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w mt-10 f-12\">";
                                    html_page_tipsv3 += "    <div class=\"clear layui-col-xs4 layui-col-xs-offset8 pl-0 pr-0\">";
                                    html_page_tipsv3 += "        <span class=\"f-w col-black dis-ib pull-left\">运费：<\/span>";
                                    html_page_tipsv3 += "        <span class=\"col-black dis-ib pull-right\">" + page[i].post_price + "<\/span>";
                                    html_page_tipsv3 += "    <\/div>";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w mt-10 f-12 mb-15\">";
                                    html_page_tipsv3 += "    <div class=\"clear layui-col-xs4 layui-col-xs-offset8 pl-0 pr-0\">";
                                    html_page_tipsv3 += "        <span class=\"f-w col-black dis-ib pull-left\">合计金额：<\/span>";
                                    html_page_tipsv3 += "        <span class=\"col-black dis-ib pull-right\">" + page[i].price_total + "<\/span>";
                                    html_page_tipsv3 += "    <\/div>";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w bor bor-b\">";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w mt-15 f-12 mb-10 col-black\">";
                                    html_page_tipsv3 += "    感谢您购买碧沙康健的产品，如您对产品感到满意记得登录碧沙商城给我们好评哦！";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w bor bor-b\">";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w mt-15 f-12 col-black text-center\">";
                                    html_page_tipsv3 += "    扫描 二维码 进入碧沙商城";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<div class=\"clear full-w mt-15 text-center\">";
                                    html_page_tipsv3 += "    <img class=\"img-100\" src=\"\/shop-admin\/img\/admin\/admin_comm\/shop_qr.png\" alt=\"\">";
                                    html_page_tipsv3 += "<\/div>";
                                    html_page_tipsv3 += "<\/div>";
                                    /*定义第三段需要拼接的html结束*/
                                    html_page = html_page + html_page_tipsv1 + html_page_tipsv2 + html_page_tipsv3;
                                }
                                /*把拼接好的html内容插入页面进行打印*/
                                $("#print_content").html(html_page);
                                $("#print_content").print();
                            };
                            /*打印结束关闭loading层*/
                            layer.close(index);

                        },
                        content: $('.sureprint'),
                    });
                }
            }
        };
        /*这里应该是cative的回调相关操作，和上面结合用的*/
        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

});

//=============================查看的时候  订单状态转换成中文========================================
function changeTraStatus(data) {
    if (data == paid) {
        return "待发货";
    } else if (data == shipped) {
        return "已发货";
    } else if (data == received) {
        return "已收货";
    } else if (data == review) {
        return "已评价";
    } else if (data == review_two) {
        return "已追评";
    }
}

function logisticsName(data) {
    if (data == "") {
        return "-------";
    } else {
        return data;
    }
}

function logisticsNumber(data) {
    if (data == "") {
        return "-------";
    } else {
        return data;
    }
}

function toAfterSalesType(data) {
    if (data == exchange) {
        return "换货";
    } else if (data == maintain) {
        return "维修";
    } else if (data == refund) {
        return "退款";
    }
}

//判断用户男还是女的枚举
function getSex(data) {
    if (data == "MALE") {
        return "男";
    }
    if (data == "FEMALE") {
        return "女";
    }
}

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

//=================================将数字转换成金额显示=================================
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