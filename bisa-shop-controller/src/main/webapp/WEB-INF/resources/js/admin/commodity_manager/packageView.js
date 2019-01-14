$(document).ready(function() {
    var path = $("base").attr("href");
    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function() {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;
        //监听提交
        form.on('submit(search1)', function(data) {
            var searchabout = data.field.searchabout;
            var incontent = data.field.username;
            var orderid = data.field.orderid;
            var pathv2 = path + 'auth/admin/find_searchabout';
            tableIns.reload({
                url: path + 'auth/admin/find_searchabout',
                where: {
                    username: incontent,
                    orderid:orderid
                },page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });
        //执行渲染
        var tableIns = table.render({
            elem: '#orderlist',
            id: 'orderlist',
            //url: path+'find_adminOrder1',
            method: 'get',
            height: 400, //容器高度
            limits: [6],
            limit: 6, //默认采用60
            cols: [
                [ //标题栏
                    { field: 'id', title: 'ID', width: 80, sort: true, fixed: true },
                    { field: 'name', title: '收货姓名', width: 100, event: 'editusername', },
                    { field: 'tel', title: '联系电话', width: 130, event: 'edittel' },
                    //{ field: 'addr_num', title: '地址编号', width: 260},
                    { field: 'county', title: '收货地区', width: 120, event: 'editcounty' },
                    { field: 'address', title: '收货地址', width: 180, event: 'editaddress' },
                    { field: 'order_no', title: '订单号', width: 175, event: 'orderidabout', },
                    { field: 'logistics_number', title: '运单号', width: 180, event: 'widabout', },
                    { field: 'product', title: '商品内容', width: 240, event: 'goodsabout'},
                    { field: 'start_time', title: '下单时间', width: 180, sort: true },
                    { field: 'shouhuo_time', title: '收货时间', width: 180 },
                    { field: 'tra_status', title: '状态', width: 120, sort: true },
                    { field: 'logistics_name', title: '物流公司', width: 120 },
                    { fixed: 'right', title: '相关操作', width: 180, align: 'center', toolbar: '#barOrderlist' }
                ]
            ],
            page: true,
            done: function(res, curr, count) {}
        });
        //监听单元格事件
        table.on('tool(filorderlist)', function(obj) {
            var data = obj.data;
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            var username = $("input[name='username']").val();
            var county = $("input[name='county']").val();
            /*修改收货人姓名*/
            if (obj.event === 'editusername') {
                layer.prompt({
                    formType: 3,
                    title: '修改 ID 为 <font style="color: red;">' + data.id + '</font> 的收货姓名',
                    value: data.name,
                    maxlength: 40,
                }, function(value, index) {
                    layer.close(index);
                    var loadtier = layer.load(1);
                    //这里一般是发送修改的Ajax请求
                     $.ajax({
                        url: path + "auth/admin/update_address",
                        type: "get",
                        dataType: "text",
                        async: false,
                        data: {
                            "value": value,
                            "field": 'name',
                            "order_no": data.order_no,
                            "username": username
                        },
                        success: function(result) {
                            if (result == "success") {
                                layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data.id + '</font> 的收货姓名为<font style="color: red;">' + value + '</font></font>',{icon: 1});
                            } else {
                                layer.alert('修改失败，请联系管理员', {
                                icon: 2,
                                title: '修改失败，请联系管理员',
                                });  
                            }
                        },
                        error: function() {
                            layer.alert('修改失败，请联系管理员', {
                                icon: 2,
                                title: '修改失败，请联系管理员',
                             });
                        }
                    });
                    layer.close(loadtier);
                    //同步更新表格和缓存对应的值
                    obj.update({
                        name: value
                    });
                   /* layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data.id + '</font> 的收货姓名为<font style="color: red;">' + value + '</font></font>',{icon: 1}); */
                });
            };
            /*修改收货人电话*/
            if (obj.event === 'edittel') {
                layer.prompt({
                    formType: 3,
                    title: '修改 ID 为 <font style="color: red;">' + data.id + ' </font>的联系电话',
                    value: data.tel,
                    maxlength: 20,
                }, function(value, index) {
                    layer.close(index);
                    var loadtier = layer.load(1);
                    //这里一般是发送修改的Ajax请求
                       $.ajax({
                        url: path + "auth/admin/update_address",
                        type: "get",
                        dataType: "text",
                        async: false,
                        data: {
                            "value": value,
                            "field": 'tel',
                            "order_no": data.order_no,
                            "username": username
                        },
                        success: function(result) {
                            if (result == "success") {
                                
                                layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data.id + '</font> 的联系电话为<font style="color: red;">' + value + '</font></font>',{icon: 1,time:4000,});
                            } else {
                                layer.alert('修改失败，请联系管理员', {
                                    icon: 2,
                                    title: '修改失败，请联系管理员',
                                });
                            }
                        },
                        error: function() {
                            layer.alert('修改失败，请联系管理员', {
                                icon: 2,
                                title: '修改失败，请联系管理员',
                            });
                        }
                    });
                    layer.close(loadtier);
                    //同步更新表格和缓存对应的值
                    obj.update({
                        tel: value
                    });
                    /*layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data.id + '</font> 的联系电话为<font style="color: red;">' + value + '</font></font>',{icon: 1,time:4000,}); */
                });
            };
            /*修改收货人地址*/
            if (obj.event === 'editaddress') {
                layer.prompt({
                    formType: 2,
                    title: '修改 ID 为 <font style="color: red;">' + data.id + ' </font>的收货地址',
                    value: data.address,
                    maxlength: 20,
                }, function(value, index) {
                    layer.close(index);
                    var loadtier = layer.load(1);
                    //这里一般是发送修改的Ajax请求
                     $.ajax({
                        url: path + "auth/admin/update_address",
                        type: "get",
                        dataType: "text",
                        async: false,
                        data: {
                            "value": value,
                            "field": 'address',
                            "order_no": data.order_no,
                            "username": username

                        },
                        success: function(result) {
                            if (result == "success") {
                              layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data.id + '</font> 的收货地址为<font style="color: red;">' + value + '</font></font>',{icon: 1,time:4000,});
                            } else {
                                layer.alert('修改失败，请联系管理员', {
                                    icon: 2,
                                    title: '修改失败，请联系管理员',
                                });
                            }
                        },
                        error: function() {
                            layer.alert('修改失败，请联系管理员', {
                                icon: 2,
                                title: '修改失败，请联系管理员',
                             });
                        }
                    });
                    layer.close(loadtier);
                    //同步更新表格和缓存对应的值
                    obj.update({
                        address: value
                    });
                   /* layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data.id + '</font> 的收货地址为<font style="color: red;">' + value + '</font></font>',{icon: 1,time:4000,}); */
                });
            };
            /*修改收货人地区*/
            if (obj.event === 'editcounty') {
                /*弹出层赋值*/
                $(".detail_oldcounty").html(data.county);
                $(".c_newcounty").val("");
                form.render();
                var data_this = data;
                layer.open({
                    type: 1,
                    title: '修改 ID 为 <font style="color: red;">' + data.id + ' </font>的收货地区',
                    area: ['500px', '500px'], //宽高
                    content: $('.detailcounty'),
                });
                /*监听收货地区的提交*/
                form.on('submit(sub_county)', function(data) {
                    /*这里是需要的三个值*/
                    var new_county_val = data.field.nm_newcounty;
                    /*关闭所有弹出层，加载loading层*/
                    layer.closeAll();
                    var loadtier = layer.load(1);
                    /*在这里进行AJAX*/
                    //这里一般是发送修改的Ajax请求
                     $.ajax({
                        url: path + "auth/admin/update_address",
                        type: "get",
                        dataType: "text",
                        async: false,
                        data: {
                            "value": new_county_val,
                            "field": 'county',
                            "order_no": data_this.order_no,
                            "username": username,
                        },
                        success: function(result) {
                            if (result == "success") {
                                layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data_this.id + '</font> 的收货地区从<font style="color: red;">' + data_this.county + '</font>修改为为<font style="color: red;">' + new_county_val + '</font></font>',{icon: 1});
                            } else {
                                layer.alert('修改失败，请联系管理员', {
                                    icon: 2,
                                    title: '修改失败，请联系管理员',
                                });
                            }
                        },
                        error: function() {
                            layer.alert('修改失败，请联系管理员', {
                                icon: 2,
                                title: '修改失败，请联系管理员',
                            });
                        }
                    });
                    /*----------AJAX---------华丽参考线-------AJAX-------*/
                    /*成功之后关闭loading层，更新缓存，并且提示信息*/
                    layer.close(loadtier);
                    obj.update({
                        county: new_county_val,
                    });
                    /*layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data_this.id + '</font> 的收货地区从<font style="color: red;">' + data_this.county + '</font>修改为为<font style="color: red;">' + new_county_val + '</font></font>',{icon: 1});*/

                    return false;
                });
            };
            /*查看商品内容*/
            if (obj.event === 'goodsabout') {
                layer.alert(data.product, {
                    title: 'ID 为 <font style="color: red;">' + data.id + '</font> 的商品内容',
                });
            };
            /*查看订单号*/
            if (obj.event === 'orderidabout') {
                var lay_order_no = layer.alert('<span id="foo_order_no" class="col-333">'+data.order_no+'</span>'+'<div class="mt-20 clear"><button id="copy_order_no" class="layui-btn layui-btn-sm pull-right layui-btn-normal" data-clipboard-target="#foo_order_no">复制订单号</button></div>', {
                    title: 'ID 为 <font style="color: red;">' + data.id + '</font> 的订单号为',
                    btn: [],
                });
                var clipboard = new Clipboard('#copy_order_no');
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
            /*查看运单号*/
            if (obj.event === 'widabout') {
                layer.alert(data.logistics_number, {
                    title: 'ID 为 <font style="color: red;">' + data.id + '</font> 的运单号',
                });
            };
            /*每一行的查看操作*/
            if (layEvent === 'detail') {
                $(".detail_consignee").html(data.name);
                $(".detail_phone").html(data.tel);
                $(".detail_area").html(data.county);
                $(".detail_address").html(data.address);
                $(".detail_ordernum").html(data.order_no);
                $(".detail_waybillnum").html(data.logistics_number);
                $(".detail_goodscontent").html(data.product);
                $(".detail_orderstime").html(data.start_time);
                $(".detail_taketime").html(data.shouhuo_time);
                $(".detail_status").html(data.tra_status);
                $(".detail_logistics").html(data.logistics_name);
                layer.open({
                    type: 1,
                    title: "ID为" + data.id + "的基本信息",
                    area: ['800px', '800px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent'),
                });
            };
        });
        /*监听单元格编辑*/
        table.on('edit(filorderlist)', function(obj) {
            var value = obj.value;
            var field = obj.field;
            var data = obj.data; //得到所在行所有键值
            var addr_num = data.addr_num;
            var username = $("input[name='username']").val();

            /*layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);*/
            $.ajax({
                url: path + "auth/admin/update_address",
                type: "get",
                dataType: "text",
                async: false,
                data: {
                    "value": value,
                    "field": field,
                    "addr_num": addr_num,
                    "username": username
                },
                success: function(result) {
                    if (result == "success") {
                        layer.alert('修改成功', {
                            icon: 1,
                            title: '修改成功',
                        })
                    } else {
                        /*layer.msg('[ID: '+ data.id +'] ' + field + '字段修改失败');*/
                        layer.alert('修改失败，请联系管理员', {
                            icon: 2,
                            title: '修改失败，请联系管理员',
                        });

                    }
                },
                error: function() {
                    /*layer.msg('[ID: '+ data.id +'] ' + field + '字段修改失败'); */
                    layer.alert('修改失败，请联系管理员', {
                        icon: 2,
                        title: '修改失败，请联系管理员',
                    });
                }

            });
        });
    });
});