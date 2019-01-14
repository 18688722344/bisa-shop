$(document).ready(function() {
     var path = $("base").attr("href");
    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function() {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;
        //监听提交;
        /*设置运单号部分的提交*/
        form.on('submit(search1)', function(data) {
            /*获取此时的id*/
            var incontent = $(".sn_num").val();
            var searchabout = data.field.searchabout;
           // layer.closeAll();
            tableIns.reload({
                url: path + 'auth/admin/service/findDevicelist',
                where: {
                    incontent: incontent,
                    searchabout:searchabout
                },page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });
        //执行渲染
        var tableIns = table.render({
            elem: '#DeliverList', //指定原始表格元素选择器（推荐id选择器）
            id: 'DeliverList',
            height: 400, //容器高度
            limits: [6],
            limit: 6, //默认采用60
            cols: [
                [ //标题栏
                    { field: 'sn_num', title: 'SN码', width: "25%", sort: true, fixed: true },
                    { field: 'deviceId', title: '产品设备号', width: "25%"},
                    { field: 'product_title', title: '产品类型', width: "25%"},
                    { field: 'inventory_status', title: '在库状态', width: "25%"},
                    { fixed: 'right', title: '查看详细', width: "25%", align: 'center', toolbar: '#barDemo' }
                ]
            ],
            page: true,
            done: function(res, curr, count) {}
        });
        //监听工具条
        table.on('tool(DLtest)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if (layEvent === 'detail') { //查看
                if (data.inventory_status == "已售出") {
                    $(".detail_consignee").html(data.addressname);
                    $(".detail_phone").html(data.phone);
                    $(".detail_address").html(data.address);
                    $(".detail_ordernum").html(data.ordernum);
                   //$(".detail_username").html(data.username);
                    $(".detail_snnum").html(data.sn_num);
                    $(".detail_goodsstatus").html(data.product_title);
                    $(".detail_time").html(data.ordertime);
                    $(".detail_warrantystatus").html(data.warrantystatus);
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        title: "SN码为" + data.sn_num + "的基本信息",
                        area: ['800px', '700px'], //宽高
                        btn: ['我知道了'],
                        content: $('.detailcontent'),
                    });
                } else{
                    layer.msg("只有已售出的商品才能查看信息!", { icon: 5, anim: 6 });
                }
            };
          

        });
        
    });
});