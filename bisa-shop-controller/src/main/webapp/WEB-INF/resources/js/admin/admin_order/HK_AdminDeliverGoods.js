$(document).ready(function() {
    /*layui方面js*/
    var path = $("base").attr("href");
    layui.use(['form', 'table', 'element'], function() {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;
        //监听提交
        /*查找部分的提交*/
        form.on('submit(search1)', function(data) {
            var searchabout = data.field.searchabout;
            var incontent = data.field.incontent;
            //获取被点击的分类
            /*layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })*/
             tableIns.reload({
                url: path + 'auth/admin/find_shipping_clerk',
                where: {
                  'searchabout': searchabout,
                   'incontent':incontent
                },page: {
                    curr: 1 //重新从第 1 页开始
                }

            });
            return false;
        });
        /*设置运单号部分的提交*/
        form.on('submit(sub_setway)', function(data) {
            /*获取此时的id*/
            var this_id = $(".ipt_waybill_id").val();
            /*当前运单号*/
            var detail_waybillnum=$(".detail_waybillnum").html();
            layer.closeAll();
            /*layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })*/
            tableIns.reload({
                url: path + 'auth/admin/set_logistics_number',
                where: {
                  'orderNo': this_id,
                  'logistics_number':data.field.ipt_waybill,
                  'detail_waybillnum':detail_waybillnum
                }
            });
            return false;
        });
        //执行渲染
        var tableIns = table.render({
            elem: '#DeliverList', //指定原始表格元素选择器（推荐id选择器）
            id: 'DeliverList',
            height: 400, //容器高度
            url: path+'auth/admin/shipping_clerk',
            limits: [6],
            limit: 6, //默认采用60
            cols: [
                [ //标题栏
                    { checkbox: true, fixed: true },
                  /*  { field: 'id', title: 'ID', width: 80, sort: true, fixed: true },*/
                    { field: 'orderNo', title: '订单号', width: 175, event: 'orderNo_e',fixed: true },
                    { field: 'status', title: '订单状态', width: 130 },
                    { field: 'logisticsNo', title: '运单号', width: 180, event: 'logisticsNo_e' },
                    { field: 'productContent', title: '商品内容', width: 240, event: 'productContent_e' },
                    { field: 'contactName', title: '收货人', width: 100 },
                    { field: 'contactPhone', title: '联系电话', width: 160 },
                    { field: 'contactAddress', title: '收货地址', width: 180 },
                    { field: 'timeZone', title: '收货时区', width: 120 },
                    { field: 'user_zonetime', title: '下单时间', width: 180 },
                    { field: 'logisticsName', title: '物流公司', width: 120, sort: true },
                    { fixed: 'right', title: '查看详细', width: 220, align: 'center', toolbar: '#barDemo' }
                ]
            ],

            page: true,
        });
        //监听工具条
        table.on('tool(DLtest)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if (layEvent === 'detail') { //查看
                $(".detail_consignee").html(data.contactName);
                $(".detail_phone").html(data.contactPhone);
                $(".detail_ordernum").html(data.orderNo);
                $(".detail_waybillnum").html(data.logisticsNo);
                $(".detail_goodscontent").html(data.productContent);
                $(".detail_address").html(data.contactAddress);
                $(".detail_time").html(data.user_zonetime);
                $(".detail_status").html(data.status);
                $(".detail_logistics").html(data.logisticsName);
                layer.open({
                    type: 1,
                    title: "ID为" + data.orderNo + "的基本信息",
                    area: ['800px', '700px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent'),
                });
            };
            if (layEvent === 'edit') { //编辑
                if(data.status=="已配货"||data.status=="待收货"){
                    $(".ipt_waybill_id").val(data.orderNo);
                    $(".ipt_waybill").val("");
                    $(".detail_waybillnum").html(data.logisticsNo);
                    layer.open({
                        type: 1,
                        title: "编辑ID为" + data.orderNo + "的运单信息",
                        area: ['500px', '300px'], //宽高
                        content: $('.detailwaybill'),
                    });
                }else{
                    layer.alert('请先去仓库配货', {
                      icon: 2,
                      skin: 'layer-ext-moon' 
                    });
                }
            };
            if(layEvent === 'cargo') { //备货
                var data_status = data.status;
                var data_ordernum = data.orderNo;
                if (data.status == "待配货") {
                    window.location.href=path+"auth/admin/allocationOfCargo/"+data_ordernum+"/"+data.uid;
                } else {
                    layer.alert("订单状态只有为 <span style='color:red;'>待配货</span> 的订单才能配货!", {
                        title: '提示',
                    });
                }
            };
            if (obj.event === 'orderNo_e') {
                var lay_order_no = layer.alert('<span id="foo_orderNo" class="col-333">'+data.orderNo+'</span>'+'<div class="mt-20 clear"><button id="copy_orderNo" class="layui-btn layui-btn-sm pull-right layui-btn-normal" data-clipboard-target="#foo_orderNo">复制订单号</button></div>', {
                    title: 'ID 为 <font style="color: red;">' + data.orderNo + '</font> 的订单号为',
                    btn: [],
                });
                var clipboard = new Clipboard('#copy_orderNo');
                clipboard.on('success', function(e) {
                    layer.close(lay_order_no);
                    layer.msg('成功把订单号复制到粘贴板', {icon: 1});
                    e.clearSelection();
                });
                clipboard.on('error', function(e) {
                    layer.close(lay_order_no);
                    layer.msg('复制失败，请手动复制。', {icon: 5});
                });;
            };
            if (obj.event === 'logisticsNo_e') {
                var lay_logisticsNo = layer.alert('<span id="foo_logisticsNo" class="col-333">'+data.logisticsNo+'</span>'+'<div class="mt-20 clear"><button id="copy_logisticsNo" class="layui-btn layui-btn-sm pull-right layui-btn-normal" data-clipboard-target="#foo_logisticsNo">复制运单号</button></div>', {
                    title: 'ID 为 <font style="color: red;">' + data.orderNo + '</font> 的运单号为',
                    btn: [],
                });
                var clipboard = new Clipboard('#copy_logisticsNo');
                clipboard.on('success', function(e) {
                    layer.close(lay_logisticsNo);
                    layer.msg('成功把运单号复制到粘贴板', {icon: 1});
                    e.clearSelection();
                });
                clipboard.on('error', function(e) {
                    layer.close(lay_logisticsNo);
                    layer.msg('复制失败，请手动复制。', {icon: 5});
                });;
            };
            if (obj.event === 'productContent_e') {
                layer.alert(data.productContent, {
                    title: 'ID 为 &nbsp;' + data.orderNo + '&nbsp; 商品内容',
                });
            };
        });
        /*打印发货单相关操作*/
        var $ = layui.$, active = {
                getCheckData: function() {
                    /*获取选中的id*/
                    var checkStatus = table.checkStatus('DeliverList');
                    var data = checkStatus.data;
                    /*判断data的数量是否为0*/
                    if (data.length == 0) {
                        layer.alert('请先选择需要打印的订单。', {
                            icon: 6,
                            title: '打印发货单提示'
                        })
                    }else{
                        var jsonprintid = [];
                        var manifest_box_content = "";
                        for (var i = 0; i < data.length; i++) {
                            /*把id封装成json*/
                            var row ={};
                            row.uid = data[i].uid;
                            row.orderNo=data[i].orderNo;
                            jsonprintid.push(row);
                            //console.log(jsonprintid);
                            var JSONcartsubmit = JSON.stringify(jsonprintid);
                            /*把订单添加到相应位置*/
                            manifest_box_content += "<p class=\"clear h-40 line-h-40 col-red\">";
                            manifest_box_content += data[i].orderNo;
                            manifest_box_content += "<\/p>";
                        }
                        $(".manifest_box").html(manifest_box_content);
                        layer.open({
                            type: 1,
                            title: "打印发货单",
                            area: ['560px', '700px'], //宽高
                            btn: ['打印', '取消'],
                            btn1: function(index, layero) {
                                /*开启加载loading层*/
                                 //console.log(JSONcartsubmit);
                                var index = layer.load(1);
                                /*在这里进行ajax调用返回json数据*/
                                    $.ajax({
                                        url: path + "auth/admin/print_shipping_clerk",
                                        type: "get",
                                        dataType: "json",
                                        async: false,
                                        data: {
                                            "printId": JSONcartsubmit,
                                        },
                                        success: function(result) {
                                          orderlist_data=result;
                                          printorderlist(orderlist_data);
                                        },
                                        error: function() {
                                          layer.alert('打印失败，请联系管理员', {
                                            icon: 2,
                                            title: '打印失败，请联系管理员',
                                         });
                                        }
                                    });
                                /*在此处进行打印内容的拼接操作*/
                                //printorderlist();
                                function printorderlist(orderlist_data){
                                    //console.log(orderlist_data);
                                    var path1=$("base").attr("href");
                                    var page = orderlist_data.order_book;
                                    var page_length = orderlist_data.order_book.length;
                                    var html_page = "";
                                    for (i=0; i < page_length; i++) {
                                        /*定义第一段需要拼接的html*/
                                            var html_page_tipsv1="";
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
                                                html_page_tipsv1 += "        <span class=\"f-w col-black\">收货人姓名：<\/span>"+page[i].name;
                                                html_page_tipsv1 += "    <\/div>";
                                                html_page_tipsv1 += "    <div class=\"clear layui-col-xs6 line-h-20 col-black\">";
                                                html_page_tipsv1 += "        <span class=\"f-w col-black\">订单编号：<\/span>"+page[i].order_no;
                                                html_page_tipsv1 += "    <\/div>";
                                                html_page_tipsv1 += "<\/div>";
                                                html_page_tipsv1 += "<div class=\"clear full-w mt-10 f-12\">";
                                                html_page_tipsv1 += "    <div class=\"clear layui-col-xs12 line-h-20 col-black\">";
                                                html_page_tipsv1 += "        <span class=\"f-w col-black\">下单日期：<\/span>"+page[i].start_time;
                                                html_page_tipsv1 += "    <\/div>";
                                                html_page_tipsv1 += "<\/div>";
                                                html_page_tipsv1 += "<div class=\"clear full-w mt-10 f-12 mb-15\">";
                                                html_page_tipsv1 += "    <div class=\"clear layui-col-xs12 line-h-20 col-black\">";
                                                html_page_tipsv1 += "        <span class=\"f-w col-black\">收货人地址：<\/span>"+page[i].county_address;
                                                html_page_tipsv1 += "    <\/div>";
                                                html_page_tipsv1 += "<\/div>";
                                                html_page_tipsv1 += "<div class=\"clear full-w bor bor-b\">";
                                                html_page_tipsv1 += "<\/div>";
                                                html_page_tipsv1 += "<div class=\"clear full-w mt-15 f-12\">";
                                                html_page_tipsv1 += "    <div class=\"clear layui-col-xs3 f-w col-black pl-0 pr-0 newline\">";
                                                html_page_tipsv1 += "        商品名";
                                                html_page_tipsv1 += "    <\/div>";
                                                html_page_tipsv1 += "    <div class=\"clear layui-col-xs5 f-w col-black pl-0 pr-0 text-center newline\">";
                                                html_page_tipsv1 += "        商品编号";
                                                html_page_tipsv1 += "    <\/div>";
                                                html_page_tipsv1 += "    <div class=\"clear layui-col-xs2 f-w col-black pl-0 pr-0 text-center newline\">";
                                                html_page_tipsv1 += "        商品数量";
                                                html_page_tipsv1 += "    <\/div>";
                                                html_page_tipsv1 += "    <div class=\"clear layui-col-xs2 f-w col-black pl-0 pr-0 text-right newline\">";
                                                html_page_tipsv1 += "        单项总价";
                                                html_page_tipsv1 += "    <\/div>";
                                                html_page_tipsv1 += "<\/div>";
                                        /*定义第一段需要拼接的html结束*/
                                        /*定义第二段需要拼接的html内容*/
                                            var page_side = page[i].orderdetailList;
                                            var page_side_length = page[i].orderdetailList.length;
                                            var html_page_tipsv2 = "";
                                            for (j=0; j < page_side_length; j++) {
                                                /*定义第二段内部需要拼接的html内容*/
                                                var html_page_tipsv2_side="";
                                                    html_page_tipsv2_side += "<div class=\"clear full-w mt-15 f-12\">";
                                                    html_page_tipsv2_side += "    <div class=\"clear layui-col-xs3 col-black pl-0 pr-0 newline\">";
                                                    html_page_tipsv2_side += page_side[j].cartdir;
                                                    html_page_tipsv2_side += "    <\/div>";
                                                    html_page_tipsv2_side += "    <div class=\"clear layui-col-xs5 col-black pl-0 pr-0 text-center newline\">";
                                                    html_page_tipsv2_side += page_side[j].cartid;
                                                    html_page_tipsv2_side += "    <\/div>";
                                                    html_page_tipsv2_side += "    <div class=\"clear layui-col-xs2 col-black pl-0 pr-0 text-center newline\">";
                                                    html_page_tipsv2_side += page_side[j].cartnum;
                                                    html_page_tipsv2_side += "    <\/div>";
                                                    html_page_tipsv2_side += "    <div class=\"clear layui-col-xs2 col-black pl-0 pr-0 text-right newline\">";
                                                    html_page_tipsv2_side += page_side[j].cartprice;
                                                    html_page_tipsv2_side += "    <\/div>";
                                                    html_page_tipsv2_side += "<\/div>";
                                                    /*定义第二段需要拼接的html内容结束*/
                                                    html_page_tipsv2 += html_page_tipsv2_side;
                                            }
                                        /*定义第二段需要拼接的html内容结束*/
                                        /*定义第三段需要拼接的html*/
                                            var html_page_tipsv3="";
                                                html_page_tipsv3 += "<div class=\"clear full-w mt-40 f-12 mb-15\">";
                                                html_page_tipsv3 += "    <div class=\"clear layui-col-xs4 layui-col-xs-offset8 pl-0 pr-0\">";
                                                html_page_tipsv3 += "        <span class=\"f-w col-black dis-ib pull-left\">商品总数：<\/span>";
                                                html_page_tipsv3 += "        <span class=\"col-black dis-ib pull-right\">"+page[i].count_total+"<\/span>";
                                                html_page_tipsv3 += "    <\/div>";
                                                html_page_tipsv3 += "<\/div>";
                                                html_page_tipsv3 += "<div class=\"clear full-w bor bor-b\">";
                                                html_page_tipsv3 += "<\/div>";
                                                html_page_tipsv3 += "<div class=\"clear full-w mt-15 f-12\">";
                                                html_page_tipsv3 += "    <div class=\"clear layui-col-xs4 layui-col-xs-offset8 pl-0 pr-0\">";
                                                html_page_tipsv3 += "        <span class=\"f-w col-black dis-ib pull-left\">小计：<\/span>";
                                                html_page_tipsv3 += "        <span class=\"col-black dis-ib pull-right\">"+page[i].sub_total+"<\/span>";
                                                html_page_tipsv3 += "    <\/div>";
                                                html_page_tipsv3 += "<\/div>";
                                                html_page_tipsv3 += "<div class=\"clear full-w mt-10 f-12\">";
                                                html_page_tipsv3 += "    <div class=\"clear layui-col-xs4 layui-col-xs-offset8 pl-0 pr-0\">";
                                                html_page_tipsv3 += "        <span class=\"f-w col-black dis-ib pull-left\">运费：<\/span>";
                                                html_page_tipsv3 += "        <span class=\"col-black dis-ib pull-right\">"+page[i].post_price+"<\/span>";
                                                html_page_tipsv3 += "    <\/div>";
                                                html_page_tipsv3 += "<\/div>";
                                                html_page_tipsv3 += "<div class=\"clear full-w mt-10 f-12 mb-15\">";
                                                html_page_tipsv3 += "    <div class=\"clear layui-col-xs4 layui-col-xs-offset8 pl-0 pr-0\">";
                                                html_page_tipsv3 += "        <span class=\"f-w col-black dis-ib pull-left\">合计金额：<\/span>";
                                                html_page_tipsv3 += "        <span class=\"col-black dis-ib pull-right\">"+page[i].price_total+"<\/span>";
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
                                                html_page_tipsv3 += "    <img class=\"img-100\" src=\""+path1+"\/resources\/img\/admin\/admin_comm\/shop_qr.png\" alt=\"\">";
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
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
          });
        $(".shippingclerk").click(function() {
         tableIns.reload({
                url: path + 'auth/admin/shipping_clerk',
                where: {
                }
            });
        });
        $(".shippingclerk1").click(function() {
         tableIns.reload({
                url: path + 'auth/admin/isnotshipping_clerk',
                where: {
                }
            });
        });
        $(".shippingclerk2").click(function() {
         tableIns.reload({
                url: path + 'auth/admin/is_shipping_clerk',
                where: {
                }
            });
        }); 
        $(".shippingclerk3").click(function() {
         tableIns.reload({
                url: path + 'auth/admin/allocationOfCargo',
                where: {
                }
            });
        });
    });
    
    
    /*页面方面js*/
    $(".btn-sidebar").click(function() {
        $(".btn-sidebar").removeClass("layui-btn-primary");
        $(".btn-sidebar").removeClass("bor-009688");
        $(this).siblings().addClass("layui-btn-primary");
        $(this).addClass("bor-009688");
    });
    /*$(".wwwwww").click(function() {
        $(".aaaaaa").print();
    });*/
});