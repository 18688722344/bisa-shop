$(document).ready(function() {
     var path = $("base").attr("href");
    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function() {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;
        //监听提交
        /*查找部分的提交*/
        form.on('submit(search1)', function(data) {
            var searchabout = data.field.searchabout;
            var incontent = data.field.username;
            var filed = data.field.incontent;
           // console.log(searchabout+"|"+incontent+"|"+filed);
            tableIns.reload({
                url: path + 'auth/admin/service/customer_findAfterSaleList',
                where: {
                    username: incontent,
                    filed:filed,
                    status:searchabout
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
            layer.closeAll();
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            //console.log(JSON.stringify(data.field));
            return false;
        });
        //执行渲染
         var tableIns = table.render({
            elem: '#DeliverList', //指定原始表格元素选择器（推荐id选择器）
            id: 'DeliverList',
            height: 400, //容器高度
            cols: [
                [ //标题栏
                    { checkbox: true, fixed: true },
                    { field: 'salesafter_guid', title: '服务单号', width: 160, sort: true},
                    { field: 'user_name', title: '用户名', width: 160},
                    { field: 'order_no', title: '订单号', width: 240},
                    { field: 'sn', title: 'SN码', width: 160},
                    { field: 'product_name', title: '产品类型', width: 100},
                    { field: 'apply_startime', title: '申请时间', width: 160 },
                    { field: 'sale_apply_status', title: '售后状态', width: 160 },
                    { field: 'pay_status', title: '支付状态', width: 120 },
                    { fixed: 'right', title: '操作', width: 180, align: 'center', toolbar: '#barDemo' }
                ]
            ],
            page: true,
        });
        //监听工具条
        table.on('tool(DLtest)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') { //查看详情
                /*取data里面需要的值，跳转页面*/
                window.location.href=path+'auth/admin/selectSalesafter/'+data.salesafter_guid+'/'+data.user_guid;
            };
            if (layEvent === 'repair_record') { //维修记录
                var layloading = layer.load(2, {
                    shade: [0.1,'#fff']
                });
                /*在这里进行ajax调用返回json数据*/
               $.ajax({
                        url: path + "auth/admin/service/getrepair_List",
                        type: "post",
                        dataType: "json",
                        async: false,
                        data: {
                            "user_guid": data.user_guid,
                            "salesafter_guid":data.salesafter_guid
                        },
                        success: function(result) {
                            console.log(data.salesafter_guid);
                          service_log_data=result;
                        },
                        error: function() {
                          layer.alert('查看失败，请联系管理员', {
                            icon: 2,
                            title: '查看失败，请联系管理员',
                         });
                        }
               });
                /*模拟延迟,引入了ajax就把这个延迟给取消掉*/
               var set_tim_out = setTimeout(function open_detael(){
                    layer.close(layloading); //关闭loading层
                    var tr_list=""; //定义列表变量
                    for (var i = 0; i < service_log_data.length; i++) {
                            var status1;
                        if(service_log_data[i].status=="1"){
                            status1="录入sn码";
                        }else if(service_log_data[i].status=="2"){
                             status1="退款反库";
                        }else if(service_log_data[i].status=="3"){
                             status1="换货登记";
                        }else if(service_log_data[i].status=="4"){
                             status1="维修报价";
                        }else if(service_log_data[i].status=="5"){
                             status1="填写运单号";
                        }else if(service_log_data[i].status=="6"){
                             status1="退款终止";
                        }else if(service_log_data[i].status=="7"){
                             status1="换货完成";
                        }else if(service_log_data[i].status=="8"){
                             status1="换货终止";
                        }else if(service_log_data[i].status=="9"){
                             status1="维修完成";
                        }else if(service_log_data[i].status=="10"){
                             status1="维修终止";
                        }else{
                           status1="退款成功"; 
                        }
                        var status2;
                        if(service_log_data[i].sale_apply_status=="10"){
                            status2="待审核";
                        }else if(service_log_data[i].sale_apply_status=="21"){
                             status2="待寄回";
                        }else if(service_log_data[i].sale_apply_status=="22"){
                             status2="服务中";
                        }else if(service_log_data[i].sale_apply_status=="30"){
                             status2="已完成";
                        }else if(service_log_data[i].sale_apply_status=="40"){
                             status2="已取消";
                        }else{
                            status2="已关闭";
                        }
                       var new_sn="";
                        if(service_log_data[i].new_sn==""||service_log_data[i].new_sn==null){
                            new_sn="无";
                        }
                        var repair_txt="";
                        if(service_log_data[i].repair_txt==""||service_log_data[i].repair_txt==null){
                            repair_txt="无";
                        }
                            tr_list += "<tr>";
                            tr_list += "    <td>"+service_log_data[i].repairid+"<\/td>";
                            tr_list += "    <td>"+status1+"<\/td>";
                            tr_list += "    <td>"+service_log_data[i].insert_time+"<\/td>";
                            tr_list += "    <td>"+service_log_data[i].sn+"<\/td>";
                            tr_list += "    <td>"+new_sn+"<\/td>";
                            tr_list += "    <td>"+service_log_data[i].money+"<\/td>";
                            tr_list += "    <td>"+service_log_data[i].update_time+"<\/td>";
                            tr_list += "    <td>"+status2+"<\/td>";
                            tr_list += "    <td>"+repair_txt+"<\/td>";
                            tr_list += "<\/tr>";
                    };
                    $("#table_service_log").empty();
                    $("#table_service_log").html(tr_list);
                    layer.open({
                        type: 1,
                        title: "服务单号为" + data.service_id + "的维修记录",
                        area: ['80%', '700px'], //宽高
                        btn: ['我知道了'],
                        content: $('.detailcontent'),
                    });
                },400);
                
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
                        layer.alert('请先选择需要打印的条目。', {
                            icon: 6,
                            title: '打印服务单提示'
                        })
                    }else{
                        var jsonprintid = [];
                        var manifest_box_content = "";
                        for (var i = 0; i < data.length; i++) {
                            /*把id封装成json*/
                            var row ={};
                            row.user_guid = data[i].user_guid;
                            row.salesafter_guid = data[i].salesafter_guid;
                            jsonprintid.push(row);
                            var JSONcartsubmit = JSON.stringify(jsonprintid);
                            /*把订单添加到相应位置*/
                            manifest_box_content += "<p class=\"clear h-40 line-h-40 col-red\">";
                            manifest_box_content += data[i].salesafter_guid;
                            manifest_box_content += "<\/p>";
                        }
                        $(".manifest_box").html(manifest_box_content);
                        layer.open({
                            type: 1,
                            title: "打印服务单",
                            area: ['560px', '700px'], //宽高
                            btn: ['打印', '取消'],
                            btn1: function(index, layero) {
                                /*开启加载loading层*/
                                var index = layer.load(1);
                                /*在这里进行ajax调用返回json数据*/
                                /*-------------------------------
                                说明：
                                service_id-----------售后单号
                                ordernum-------------订单号
                                producttype----------产品类型
                                sn_id----------------SN码
                                repair_state---------保修状态
                                application_time-----申请时间
                                after_class----------售后类别
                                service_mode---------服务方式
                                description----------问题描述
                                take_goods_name------收货姓名
                                take_goods_phone-----收货手机
                                take_goods_address---收货地址
                                visit_name-----------回访姓名
                                visit_phone----------回访手机
                                --------------------------------*/
                                
                                /*AJAX--------------华丽分割线------------AJAX*/

                                $.ajax({
                                        url: path + "auth/admin/service/print_saleList",
                                        type: "post",
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
                                function printorderlist(orderlist_data){
                                    var path1=$("base").attr("href");
                                    var page = orderlist_data.order_book;
                                     var page_length = orderlist_data.order_book.length;
                                    /*定义页面信息*/
                                    var html_page = "";
                                    for (i = 0; i < page_length; i++) {      
                                        var sn=page[i].sn;
                                        if(page[i].sn==""||page[i].sn==null||page[i].sn==undefined){
                                            sn="无";
                                        }
                                        var product_name;
                                        if(page[i].product_name==""||page[i].product_name==null||page[i].product_name==undefined){
                                            product_name="无";
                                        }else{
                                            product_name=page[i].product_name;
                                        }
                                         var product_problem_ascription="";
                                        if(page[i].product_problem_ascription==""||page[i].product_problem_ascription==null||page[i].product_problem_ascription==undefined){
                                            product_problem_ascription="无";
                                        }else{
                                            product_problem_ascription=page[i].product_problem_ascription;
                                        }
                                        var leave_logistics_number="";
                                        if(page[i].leave_logistics_number==""||page[i].leave_logistics_number==null||page[i].leave_logistics_number==undefined){
                                            leave_logistics_number="无";
                                        }else{
                                            leave_logistics_number=print_data.leave_logistics_number;
                                        }
                                        var html_page = "";
                                        html_page += "<div class=\"clear\">";
                                        html_page += "  <div class=\"clear h-40 mb-5 line-h-40 text-center f-16\">";
                                        html_page += "      <div class=\"clear dis-ib\">";
                                        html_page += "          <span class=\"f-28 ml-10 f-w family-s pull-left h-40 line-h-40 col-black family-s\">BISA<\/span>";
                                        html_page += "          <span class=\"f-28 ml-10 f-w family-s pull-left h-40 line-h-40 col-black\">售后服务单<\/span>";
                                        html_page += "      <\/div>";
                                        html_page += "  <\/div>";
                                        html_page += "  <fieldset class=\"layui-elem-field layui-field-title bor-col-black mt-30\">";
                                        html_page += "      <legend class=\"col-black bg-white\">售后信息<\/legend>";
                                        html_page += "  <\/fieldset>";
                                        html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              售后单号：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += page[i].salesafter_guid;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              订单号：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += page[i].order_no;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "  <\/div>";
                                        html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              产品类型：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += product_name;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              SN码：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += sn;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "  <\/div>";
                                        html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              保修状态：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += page[i].guarantee_status;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              申请时间：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += page[i].apply_startime;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "  <\/div>";

                                        html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pl-0 pr-0 col-black\">";
                                        html_page += "              售后类别：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 pr-0 col-black\">";
                                        html_page += page[i].service_type;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              服务方式：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += page[i].service_way;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "  <\/div>";

                                        html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                                        html_page += "      <div class=\"clear pr-0 col-black pull-left\" style=\"width:12.5%;\">";
                                        html_page += "           出厂运单号：";
                                        html_page += "      <\/div>";
                                        html_page += "      <div class=\"clear pl-0 col-black pull-left\" style=\"width:87.5%;\">";
                                        html_page += leave_logistics_number;
                                        html_page += "      <\/div>";
                                         html_page += " <\/div>";

                                        html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                                        html_page += "      <div class=\"clear layui-col-xs4 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs4 pr-0 col-black\">";
                                        html_page += "              问题描述：";
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "  <\/div>";
                                        html_page += "  <div class=\"clear full-w f-12 mb-15 line-h-20 text-ind col-black\">";
                                        html_page += product_problem_ascription;
                                        html_page += "  <\/div>";
                                        html_page += "  <fieldset class=\"layui-elem-field layui-field-title bor-col-black mt-30\">";
                                        html_page += "      <legend class=\"col-black bg-white\">收货信息<\/legend>";
                                        html_page += "  <\/fieldset>";
                                        html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs2 pr-0 col-black\">";
                                        html_page += "              姓名：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs10 pl-0 col-black\">";
                                        html_page += page[i].address_name;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs2 pr-0 col-black\">";
                                        html_page += "              手机号：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs10 pl-0 col-black\">";
                                        html_page += page[i].address_tel;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "  <\/div>";
                                        html_page += "      <div class=\"clear layui-col-xs12 pl-0 pr-0 mb-15\">";
                                        html_page += "          <div class=\"clear layui-col-xs1 pr-0 col-black\">";
                                        html_page += "              地址：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs11 pl-0 col-black newline\">";
                                        html_page += page[i].address;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "  <fieldset class=\"layui-elem-field layui-field-title bor-col-black mt-30\">";
                                        html_page += "      <legend class=\"col-black bg-white\">回访信息<\/legend>";
                                        html_page += "  <\/fieldset>";
                                        html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                                        html_page += "      <div class=\"clear layui-col-xs4 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              姓名：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += page[i].contact_man;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "      <div class=\"clear layui-col-xs4 pl-0 pr-0\">";
                                        html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                                        html_page += "              手机号：";
                                        html_page += "          <\/div>";
                                        html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                                        html_page += page[i].contact_tel;
                                        html_page += "          <\/div>";
                                        html_page += "      <\/div>";
                                        html_page += "  <\/div>";
                                        html_page += "  <div class=\"clear full-w mt-60 pt-60 f-12 col-black text-center\">";
                                        html_page += "      扫描 二维码 进入碧沙商城";
                                        html_page += "  <\/div>";
                                        html_page += "  <div class=\"clear full-w mt-15 text-center\">";
                                        html_page += "    <img class=\"img-100\" src=\""+path+"\/resources\/img\/admin\/admin_comm\/shop_qr.png\" alt=\"\">";
                                        html_page += "  <\/div>";
                                        html_page += "  <div class=\"clear pd-15\" style=\"page-break-after: always;\">";
                                        html_page += "  <\/div>";
                                        html_page += "<\/div>";
                                    };
                                    $("#print_content").html("");
                                    $("#print_content").html(html_page);
                                    $("#print_content").print();
                                     layer.close(index);
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
    });
    
    
    /*页面方面js*/
    $(".btn-sidebar").click(function() {
        $(".btn-sidebar").removeClass("layui-btn-primary");
        $(".btn-sidebar").removeClass("bor-009688");
        $(this).siblings().addClass("layui-btn-primary");
        $(this).addClass("bor-009688");
    });
});