$(document).ready(function() {
     var path=$("base").attr("href");
    /*layui方面js*/
    layui.use(['layer', 'element'], function() {
        var layer = layui.layer,
            element = layui.element;
        $(".print_this").click(function(){
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
            var userid = $(".userid").val();
            var salesafter_guid = $(".salesafter").val();
            $.ajax({
                url: path + "auth/admin/service/printsaleAfter",
                type: "post",
                dataType: "json",
                async: false,
                data: {
                    "salesafter_guid":salesafter_guid,
                    "user_guid":userid
                },
                success: function(result) {
                  var print_data=result;
                  printorderlist(print_data);
                },
                error: function() {
                  layer.alert('打印失败，请联系管理员', {
                    icon: 2,
                    title: '打印失败，请联系管理员',
                 });
                }
            });
            // var print_data = [{"service_id":"HDCKL10005356","ordernum":"HAHGUBUN54185245415","producttype":"悉心心电仪","sn_id":"SN4818435868461","repair_state":"保修中","application_time":"2017/10/12 15:30:20","after_class":"维修","service_mode":"寄修","description":"一般快递的物流时间为省内1-3天左右，省外3-6天左右，偏远地区7-10天左右。超过这些时间段没有收到货，或没有新的物流信息更新的订单，物流时间异常，就需要客服去打电话查询包裹的情况。","take_goods_name":"张先森","take_goods_phone":"18580808081","take_goods_address":"深圳市南山区深云路9号(沙河建工村对面)","visit_name":"凯先森","visit_phone":"13684951542"}];
            /*AJAX--------------华丽分割线------------AJAX*/
            //printorderlist();
           function printorderlist(print_data){
                 var sn=print_data.sn;
                if(print_data.sn==""||print_data.sn==null||print_data.sn==undefined){
                    sn="无";
                }
                var product_name;
                if(print_data.product_name==""||print_data.product_name==null||print_data.product_name==undefined){
                    product_name="无";
                }else{
                    product_name=print_data.product_name;
                }
                 var product_problem_ascription="";
                if(print_data.product_problem_ascription==""||print_data.product_problem_ascription==null||print_data.product_problem_ascription==undefined){
                    product_problem_ascription="无";
                }else{
                    product_problem_ascription=print_data.product_problem_ascription;
                }
                var leave_logistics_number="";
                if(print_data.leave_logistics_number==""||print_data.leave_logistics_number==null||print_data.leave_logistics_number==undefined){
                    leave_logistics_number="无";
                }else{
                    leave_logistics_number=print_data.leave_logistics_number;
                }
                /*定义页面信息*/
                var html_page = "";
               // for (i = 0; i < print_data.length; i++) {
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
                    html_page += print_data.salesafter_guid;
                    html_page += "          <\/div>";
                    html_page += "      <\/div>";
                    html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                    html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                    html_page += "              订单号：";
                    html_page += "          <\/div>";
                    html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                    html_page += print_data.order_no;
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
                    html_page += print_data.guarantee_status;
                    html_page += "          <\/div>";
                    html_page += "      <\/div>";
                    html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                    html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                    html_page += "              申请时间：";
                    html_page += "          <\/div>";
                    html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                    html_page += print_data.apply_startime;
                    html_page += "          <\/div>";
                    html_page += "      <\/div>";
                    html_page += "  <\/div>";

                    html_page += "  <div class=\"clear full-w f-12 mb-15\">";
                    html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                    html_page += "          <div class=\"clear layui-col-xs3 pl-0 pr-0 col-black\">";
                    html_page += "              售后类别：";
                    html_page += "          <\/div>";
                    html_page += "          <div class=\"clear layui-col-xs9 pl-0 pr-0 col-black\">";
                    html_page += print_data.service_type;
                    html_page += "          <\/div>";
                    html_page += "      <\/div>";
                    html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                    html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                    html_page += "              服务方式：";
                    html_page += "          <\/div>";
                    html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                    html_page += print_data.service_way;
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
                    html_page += print_data.address_name;
                    html_page += "          <\/div>";
                    html_page += "      <\/div>";
                    html_page += "      <div class=\"clear layui-col-xs6 pl-0 pr-0\">";
                    html_page += "          <div class=\"clear layui-col-xs2 pr-0 col-black\">";
                    html_page += "              手机号：";
                    html_page += "          <\/div>";
                    html_page += "          <div class=\"clear layui-col-xs10 pl-0 col-black\">";
                    html_page += print_data.address_tel;
                    html_page += "          <\/div>";
                    html_page += "      <\/div>";
                    html_page += "  <\/div>";
                    html_page += "      <div class=\"clear layui-col-xs12 pl-0 pr-0 mb-15\">";
                    html_page += "          <div class=\"clear layui-col-xs1 pr-0 col-black\">";
                    html_page += "              地址：";
                    html_page += "          <\/div>";
                    html_page += "          <div class=\"clear layui-col-xs11 pl-0 col-black newline\">";
                    html_page += print_data.address;
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
                    html_page += print_data.contact_man;
                    html_page += "          <\/div>";
                    html_page += "      <\/div>";
                    html_page += "      <div class=\"clear layui-col-xs4 pl-0 pr-0\">";
                    html_page += "          <div class=\"clear layui-col-xs3 pr-0 col-black\">";
                    html_page += "              手机号：";
                    html_page += "          <\/div>";
                    html_page += "          <div class=\"clear layui-col-xs9 pl-0 col-black\">";
                    html_page += print_data.contact_tel;
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
               // };
                $("#print_content").html("");
                $("#print_content").html(html_page);
                $("#print_content").print();
                 layer.close(index);
            };
            /*打印结束关闭loading层*/
            layer.close(index);
        });
        $(".edit_remark").click(function(){
            var remark_content = $(".remark_content").text();
          
            //清楚文字前后空格
            remark_content=remark_content.replace(/^\s+|\s+$/g,"");
           // $(".text_edit_remark").val(remark_content);
            layer.open({
                type: 1,
                title: "编辑备注",
                area: ['600px', '360px'], //宽高
                btn: ['确认编辑', '取消'],
                btn1: function(index, layero) {
                     var remark_content1 = $(".text_edit_remark").val();
                     var userid = $(".userid").val();
                    var salesafter_guid = $(".salesafter").val();
                    $.ajax({
                        url: path + "auth/admin/service/getsaleAftertxt",
                        type: "post",
                        dataType: "text",
                        async: false,
                        data: {
                            "txt": remark_content1,
                            "salesafter_guid":salesafter_guid,
                            "user_guid":userid
                        },
                        success: function(result) {
                            if(result==1){
                               layer.confirm('编辑售后备注成功', {
                                  btn: ['确定'] //按钮
                                }, function(){
                                  window.location.href=path+"/auth/admin/service/selectSalesafter/"+salesafter_guid+"/"+userid;
                                }); 
                            }else{
                                 layer.alert('编辑售后备注失败，请联系管理员', {
                                    icon: 2,
                                    title: '编辑售后备注失败，请联系管理员',
                                });
                            }
                        },
                        error: function() {
                            layer.alert('提交失败，请联系管理员', {
                                icon: 2,
                                title: '提交失败，请联系管理员',
                             });
                        }
                    });
                },
                content: $('.show_edit_remark'),
            });

        });
        $(".getApproved").click(function(){
            var userid = $(".userid").val();
            var salesafter_guid = $(".salesafter").val();
            $.ajax({
                url: path + "auth/admin/service/getApproved",
                type: "post",
                dataType: "text",
                async: false,
                data: {
                    "salesafter_guid":salesafter_guid,
                    "user_guid":userid
                },
                success: function(result) {
                    if(result==1){
                       layer.confirm('通过审核成功', {
                          btn: ['确定'] //按钮
                        }, function(){
                          window.location.href=path+"/auth/admin/service/selectSalesafter/"+salesafter_guid+"/"+userid;
                        }); 
                    }else{
                         layer.alert('通过审核失败，请联系管理员', {
                            icon: 2,
                            title: '通过审核失败，请联系管理员',
                        });
                    }
                },
                error: function() {
                    layer.alert('提交失败，请联系管理员', {
                        icon: 2,
                        title: '提交失败，请联系管理员',
                     });
                }
             });
        });
         /*客服报价操作*/
        $(".btn_quote").click(function(){
            $("#close_aftersale_remark").val("");
              var userid = $(".userid").val();
            var salesafter_guid = $(".salesafter").val();
            layer.open({
                type: 1,
                title: "报价详细",
                area: ['400px', '450px'], //宽高
                btn: ['完成报价', '取消'],
                btn1: function(index, layero) {
                    layer.confirm('是否完成报价？', {
                        title: '确认报价',
                        btn: ['确定','取消'] //按钮
                    }, function(){
                        var i=$(".weixiu-money").text();
                        var ii=i.split("元");
                        console.log(ii[0]);
                         $.ajax({
                            url: path + "auth/admin/service/getApprovedquote",
                             type: "post",
                             dataType: "json",
                             async: false,
                             data: {
                                 "salesafter_guid":salesafter_guid,
                                 "user_guid":userid
                             },
                             success: function(result) {
                             if(result==1){
                                layer.alert('确认报价成功', {
                                icon: 1,
                                 title: '确认报价成功',
                                 });
                             }else{

                              }
                             },error: function() {
                              layer.alert('确认报价失败，请联系管理员', {
                                icon: 2,
                                 title: '确认报价失败',
                              });
                             }
                         });
                        layer.closeAll();
                    }, function(){
                        /*取消的回调*/
                        layer.closeAll();
                    });
                },
                content: $('#show_quote'),
            }); 
        });
        //关闭售后
        // $(".closesaleAfter").click(function(){
        //      var userid = $(".userid").val();
        //     var salesafter_guid = $(".salesafter").val();
        //    $.ajax({
        //         url: path + "auth/admin/service/closesaleAfter",
        //         type: "post",
        //         dataType: "text",
        //         async: false,
        //         data: {
        //             "salesafter_guid":salesafter_guid,
        //             "user_guid":userid
        //         },
        //         success: function(result) {
        //             if(result==1){
        //                layer.confirm('已关闭审核成功', {
        //                   btn: ['确定'] //按钮
        //                 }, function(){
        //                   window.location.href=path+"/auth/admin/service/selectSalesafter/"+salesafter_guid+"/"+userid+"/"+2;
        //                 }); 
        //             }else{
        //                  layer.alert('已关闭审核失败，请联系管理员', {
        //                     icon: 2,
        //                     title: '已关闭审核失败，请联系管理员',
        //                 });
        //             }
        //         },
        //         error: function() {
        //             layer.alert('提交失败，请联系管理员', {
        //                 icon: 2,
        //                 title: '提交失败，请联系管理员',
        //              });
        //         }
        //    }); 
        // });
            /*关闭售后按钮操作*/
        $(".btn_close_aftersale").click(function(){
            $("#close_aftersale_remark").val("");
            layer.open({
                type: 1,
                title: "确认关闭售后",
                area: ['360px', '350px'], //宽高
                btn: ['确定', '取消'],
                btn1: function(index, layero) {
                    var remark_val = $("#close_aftersale_remark").val();
                        var userid = $(".userid").val();
                        var salesafter_guid = $(".salesafter").val();
                       $.ajax({
                            url: path + "auth/admin/service/closesaleAfter",
                            type: "post",
                            dataType: "text",
                            async: false,
                            data: {
                                "salesafter_guid":salesafter_guid,
                                "user_guid":userid,
                                "txt":remark_val
                            },
                            success: function(result) {
                                if(result==1){
                                   layer.confirm('已关闭审核成功', {
                                      btn: ['确定'] //按钮
                                    }, function(){
                                      window.location.href=path+"/auth/admin/service/selectSalesafter/"+salesafter_guid+"/"+userid;
                                    }); 
                                }else{
                                     layer.alert('已关闭审核失败，请联系管理员', {
                                        icon: 2,
                                        title: '已关闭审核失败，请联系管理员',
                                    });
                                }
                            },
                            error: function() {
                                layer.alert('提交失败，请联系管理员', {
                                    icon: 2,
                                    title: '提交失败，请联系管理员',
                                 });
                            }
                       });                   
                },
                content: $('#show_close_aftersale'),
            }); 
        });
        //按钮部分 根据closesaleAfter 关闭售后 getApproved 通过审核 btn_quote 客服报价
        var a = $(".flag").val();
        if(a==10){
            $(".btn_close_aftersale").show();
            $(".getApproved").show();
            $(".btn_quote").hide();
        }else if(a==22){
            $(".btn_close_aftersale").show();
            $(".getApproved").show();
            $(".btn_quote").hide();
        }else if(a==23){
            $(".btn_close_aftersale").show();
            $(".getApproved").hide();
            $(".btn_quote").show();
        }else if(a==30||a==40||a==50){
            $(".btn_close_aftersale").hide();
            $(".getApproved").hide();
            $(".btn_quote").hide();
        }else{
            $(".btn_close_aftersale").show();
            $(".getApproved").show();
            $(".btn_quote").hide();
        }
    });
    
    

});