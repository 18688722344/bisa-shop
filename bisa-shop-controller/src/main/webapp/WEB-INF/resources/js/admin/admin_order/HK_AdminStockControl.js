$(document).ready(function() {
     var path = $("base").attr("href");
    var shuju=$(".select-shuju").val();
    var kucun=eval('(' + shuju + ')');
    /*初始化下拉框库存*/
    sle_initialize();

     function sle_initialize() {
            $(".sle_type_c").empty();
            var optionVarsel = "";
            optionVarsel += "<option value=\"\" selected=\"\"><\/option>";
            $(".sle_type_c").append(optionVarsel);
            for (var i = 0; i < kucun.length; i++) {
                var opt_key_one = kucun[i].inventory;
                var opt_key_two = kucun[i].name + "（库存" + kucun[i].inventory + "件）";
                var optionVar = "";
                optionVar += "<option value=\"" + opt_key_one + "\">" + opt_key_two + "<\/option>";
                $(".sle_type_c").append(optionVar);
            };
    };
    /*layui方面js*/
    layui.use(['form', 'table', 'element','laydate'], function() {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table,
            laydate = layui.laydate;
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
        /*下拉框选择之后的联动*/
        form.on('select(sle_type_f)', function(data) {
            switch(parseInt(data.value))
            {
                case 1:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify","");
                    $("#sn_num").siblings(".sel_value").val("");
                    $("#sn_num").show();
                    $("#sn_num").attr("lay-verify","required");
                    break;
                case 2:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify","");
                    $("#sn_num2").siblings(".sel_value").val("");
                    $("#sn_num2").show();
                    $("#sn_num2").attr("lay-verify","required");
                    break;
                case 3:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify","");
                    $("#ipt_year").siblings(".sel_value").val("");
                    $("#ipt_year").show();
                    $("#ipt_year").attr("lay-verify","required");
                    break;
                case 4:
                    $(".sel_value").hide();
                    $(".sel_value").attr("lay-verify","");
                    $("#ipt_month").siblings(".sel_value").val("");
                    $("#ipt_month").show();
                    $("#ipt_month").attr("lay-verify","required");
                    break;
                default:
                    break;
            }

        });
        //监听提交;
        /*设置运单号部分的提交*/
        form.on('submit(search1)', function(data) {
            /*获取此时的id*/
            var incontent;
            var searchabout = data.field.searchabout;
            if(searchabout==1){
                incontent =data.field.n_transnum;
            }else if(searchabout==2){
                incontent =data.field.n_ordernum;
            }else if(searchabout==3){
                incontent=data.field.n_year;
            }else if(searchabout==4){
                incontent=data.field.n_month;
            }else{
                incontent =data.field.n_transnum;
            }
                tableIns.reload({
                    url: path + 'auth/admin/findDevicelist',
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
            url: path+'auth/admin/Devicelist',
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
      /*      if (layEvent === 'detail') { //查看
                if (data.inventory_status == "已售出") {
                    $(".detail_consignee").html(data.addressname);
                    $(".detail_phone").html(data.phone);
                    $(".detail_address").html(data.address);
                    $(".detail_ordernum").html(data.ordernum);
                  //  $(".detail_username").html(data.username);
                    $(".detail_snnum").html(data.sn_num);
                    $(".detail_goodsstatus").html(data.status);
                    $(".detail_time").html(data.ordertime);
                  //  $(".detail_warrantystatus").html(data.warrantystatus);
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
            };*/
            if (layEvent === 'del') { //编辑
                var deviceId=data.sn_num;
                layer.confirm('真的删除行么', function(index){
                  obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                  //向服务端发送删除指令
                    $.ajax({
                            url: path + "auth/admin/delDevice",
                            type: "get",
                            dataType: "text",
                            async: false,
                            data: {
                                "device_id": deviceId
                            },
                            success: function(result) {
                                if (result == "1") {
                                   layer.alert('删除成功', {
                                        icon: 1,
                                        title: '删除成功',
                                    });
                                    layer.close(index);
                                } else {
                                    /*layer.msg('[ID: '+ data.id +'] ' + field + '字段修改失败');*/
                                    layer.alert('删除失败，请联系管理员', {
                                        icon: 2,
                                        title: '删除失败，请联系管理员',
                                    });

                                }
                            },
                            error: function() {
                                /*layer.msg('[ID: '+ data.id +'] ' + field + '字段修改失败'); */
                                layer.alert('删除失败，请联系管理员', {
                                    icon: 2,
                                    title: '删除失败，请联系管理员',
                                });
                            }

                    });
                });
            
            };

        });
        
    });
});