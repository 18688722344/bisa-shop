$(document).ready(function() {
    /*layui方面js*/
var path=$("base").attr("href");
  var userid = $(".userid").val();
            var salesafter_guid = $(".salesafter").val();
    layui.use(['form', 'layer', 'element'], function() {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element;
        $(".print_this").click(function(){
            /*开启加载loading层*/
            var index = layer.load(1);
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
                                  window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
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
        /*录入sn码*/
        $(".btn_enter_sn").click(function(){
            layer.prompt({ title: '请录入SN码', formType: 0 }, function(enter_sn, index) {
                layer.close(index);
                /*enter_sn就是录入的sn码*/
                $.ajax({
                    url: path + "auth/admin/insertsn",
                    type: "POST",
                    dataType: "text",
                    async: false,
                    data: {
                        "sn":enter_sn,
                        "salesafter_guid":salesafter_guid,
                        "user_guid":userid
                    },
                    success: function(result) {
                        if(result==1){
                           layer.confirm('录入sn成功', {
                              btn: ['确定'] //按钮
                            }, function(){
                              window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
                            }); 
                        }else if(result==2){
                             layer.alert('你录入sn码是错误的，和之前订单不匹配，是否不是这个订单里的', {
                                icon: 2,
                                title: '录入sn失败',
                            });
                        }else if(result==4){
                             layer.alert('你录入sn码格式不对，应该是12位的', {
                                icon: 2,
                                title: '通录入sn失败',
                            });
                        }else {
                          layer.alert('录入sn失败，请联系管理员', {
                            icon: 2,
                            title: '录入sn失败，请联系管理员',
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
               // console.log(enter_sn);
            });
        });
        /*重录sn码*/
        $(".btn_re_enter_sn").click(function(){
                layer.confirm('SN码已经录入过，请问是否重新录入？', {
                  btn: ['确定','取消'] //按钮
                }, function(){
                    layer.closeAll();
                    var sn_code = $(".sn_code").text();
                    layer.prompt({ title: '修改原来的SN码，原SN码将删除。', formType: 0 , value: sn_code}, function(enter_sn, index) {
                        layer.close(index);
                        /*enter_sn就是录入的sn码*/
                        $.ajax({
                            url: path + "auth/admin/insertsn",
                            type: "POST",
                            dataType: "text",
                            async: false,
                            data: {
                                "sn":enter_sn,
                                "salesafter_guid":salesafter_guid,
                                "user_guid":userid
                            },
                            success: function(result) {
                                if(result==1){
                                   layer.confirm('录入sn成功', {
                                      btn: ['确定'] //按钮
                                    }, function(){
                                      window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
                                    }); 
                                }else if(result==2){
                                     layer.alert('你录入sn码是错误的，和之前订单不匹配，是否不是这个订单里的', {
                                        icon: 2,
                                        title: '录入sn失败',
                                    });
                                }else if(result==4){
                                     layer.alert('你录入sn码格式不对，应该是12位的', {
                                        icon: 2,
                                        title: '通录入sn失败',
                                    });
                                }else {
                                  layer.alert('录入sn失败，请联系管理员', {
                                    icon: 2,
                                    title: '录入sn失败，请联系管理员',
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
                        //console.log(enter_sn);
                    });
                });            
        });
        /*换货登记*/
        $(".btn_exchanges").click(function(){
            var a = $(".count2").val();
            if (a==0) {
               $(".re_new_sncode").val("");
                var sn_code = $(".sn_code").text();
                $(".old_sn_code").text(sn_code);
                layer.open({
                    type: 1,
                    title: "换货登记",
                    area: ['360px', '500px'], //宽高
                    btn: ['确定', '取消'],
                    btn1: function(index, layero) {
                        var re_new_sn_code = $(".re_new_sncode").val();
                        var huanhuo=$(".huanhuo").val();
                        var old_sn=$(".sn_code").text();
                        var caozuo=$("input[name='gone']:checked").val();
                        if(caozuo=="regood"){
                            caozuo="1";
                        }else{
                            caozuo="2";
                        }
                        if (re_new_sn_code == "") {
                            layer.msg('输入新SN码不能为空', {icon: 5,anim: 6});
                        } else {
                            $.ajax({
                                url: path + "auth/admin/ExchangeRegistration",
                                type: "post",
                                dataType: "text",
                                async: false,
                                data: {
                                    "sn": re_new_sn_code,
                                    "salesafter_guid":salesafter_guid,
                                    "user_guid":userid,
                                    "txt":huanhuo,
                                    "old_sn":old_sn,
                                    "caozuo":caozuo

                                },
                                success: function(result) {
                                    if(result==1){
                                       layer.confirm('换货登记成功', {
                                          btn: ['确定'] //按钮
                                        }, function(){
                                          window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
                                        }); 
                                    }else if(result==3){
                                         layer.alert(':SN码格式不对,应该是12位', {
                                            icon: 2,
                                            title: '换货登记失败',
                                        });
                                    }else if(result==4){
                                         layer.alert('在仓库中查无数据，此产品可能已出库', {
                                            icon: 2,
                                            title: '换货登记失败',
                                        });
                                    }else{
                                         layer.alert('换货登记失败，请联系管理员', {
                                            icon: 2,
                                            title: '换货登记失败，请联系管理员',
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
                        }
                        
                    },
                    content: $('#show_exchanges'),
                }); 
            }else if (a == 1) {
                layer.confirm('该服务单已经登记新产品SN码，是否再次登记？（再次登记后新产品SN码将被覆盖）', {
                  btn: ['确定','取消'] //按钮
                }, function(){
                    layer.closeAll();
                    $(".re_new_sncode").val("");
                    var sn_code = $(".sn_code").text();
                    $(".old_sn_code").text(sn_code);
                    layer.open({
                        type: 1,
                        title: "换货登记",
                        area: ['360px', '500px'], //宽高
                        btn: ['确定', '取消'],
                        btn1: function(index, layero) {
                            var re_new_sn_code = $(".re_new_sncode").val();
                            if (re_new_sn_code == "") {
                                layer.msg('输入新SN码不能为空', {icon: 5,anim: 6});
                            } else {
                                var huanhuo=$(".huanhuo").val();
                                var old_sn=$(".sn_code").text();
                                var caozuo=$("input[name='gone']:checked").val();
                                if(caozuo=="regood"){
                                    caozuo="1";
                                }else{
                                    caozuo="2";
                                }
                                $.ajax({
                                url: path + "auth/admin/ExchangeRegistration",
                                type: "post",
                                dataType: "text",
                                async: false,
                                data: {
                                    "sn": re_new_sn_code,
                                    "salesafter_guid":salesafter_guid,
                                    "user_guid":userid,
                                    "txt":huanhuo,
                                    "old_sn":old_sn,
                                    "caozuo":caozuo

                                },
                                success: function(result) {
                                    if(result==1){
                                       layer.confirm('换货登记成功', {
                                          btn: ['确定'] //按钮
                                        }, function(){
                                          window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
                                        }); 
                                    }else if(result==3){
                                         layer.alert(':SN码格式不对,应该是12位', {
                                            icon: 2,
                                            title: '换货登记失败',
                                        });
                                    }else if(result==4){
                                         layer.alert('在仓库中查无数据，此产品可能已出库', {
                                            icon: 2,
                                            title: '换货登记失败',
                                        });
                                    }else{
                                         layer.alert('换货登记失败，请联系管理员', {
                                            icon: 2,
                                            title: '换货登记失败，请联系管理员',
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
                            }
                            
                        },
                        content: $('#show_exchanges'),
                    });
                });
            }      
        }); 
        /*维修报价*/
        $(".btn_repair_quo").click(function(){
               layer.open({
                    type: 1,
                    title: "维修报价",
                    area: ['360px', '400px'], //宽高
                    btn: ['确定', '取消'],
                    btn1: function(index, layero) {
                        var re_money = $(".ipt_money").val();
                        var repair_txt = $(".repair-txt").val();
                        if (re_money == "") {
                            layer.msg('输入金额不能为空', {icon: 5,anim: 6});
                        } else {
                            $.ajax({
                                url: path + "auth/admin/repairQuotation",
                                type: "post",
                                dataType: "text",
                                async: false,
                                data: {
                                    "txt": repair_txt,
                                    "salesafter_guid":salesafter_guid,
                                    "user_guid":userid,
                                    "money":re_money
                                },
                                success: function(result) {
                                    if(result==1){
                                       layer.confirm('维修报价成功', {
                                          btn: ['确定'] //按钮
                                        }, function(){
                                          window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
                                        }); 
                                    }else{
                                         layer.alert('维修报价失败，请联系管理员', {
                                            icon: 2,
                                            title: '维修报价失败，请联系管理员',
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
                        }
                    },
                    content: $('#show_repair_quo'),
                }); 
             
        });
        /*输入框只能输入金额的校验*/
        $(".ipt_money").keyup(function () {
            var reg = $(this).val().match(/\d+\.?\d{0,2}/);
            var txt = '';
            if (reg != null) {
                txt = reg[0];
            }
            $(this).val(txt);
        }).change(function () {
            $(this).keypress();
            var v = $(this).val();
            if (/\.$/.test(v))
            {
                $(this).val(v.substr(0, v.length - 1));
            }
        });
        /*退款返库*/
        $(".btn_refund").click(function(){
            form.on('checkbox(lay_show_refund_checkbox_v1)', function(data){
                if (data.elem.checked) {
                    $(".lay_show_refund_money_box").slideDown();
                    $(".tuikuan").val("");
                }else{
                    $(".lay_show_refund_money_box").slideUp();
                    $(".tuikuan").val("");
                }
            }); 
            var ss;
            form.on('checkbox(lay_show_refund_checkbox_v2)', function(data){
                console.log(data.elem.checked); //是否被选中，true或者false
                if(data.elem.checked){
                    ss=1;
                }else{
                    ss=2;
                }
            });   
            layer.open({
                type: 1,
                title: "退款返库",
                area: ['300px', '430px'], //宽高
                btn: ['确定', '取消'],
                btn1: function(index, layero) {
                    var money=$(".tuikuan").val();
                    var txt=$(".tuokuan-txt").val();
                    var flag=0;
                    //flag 1：退款 不返库  2：退款  返库 3：退款 自定义金额 返库  4：退款 自定义金额  不返库
                    if(ss==1){
                        if(money==""){
                            console.log("退款 不返库");
                            flag=1;
                        }else{
                             console.log("退款 自定义金额  不返库");
                             flag=3;
                        }
                    }else{
                        if(money==""){
                            console.log("退款 返库");
                            flag=2;
                        }else{
                             console.log("退款 自定义金额  返库");
                             flag=4;
                        }
                    }
                    $.ajax({
                            url: path + "auth/admin/refund",
                            type: "post",
                            dataType: "text",
                            async: false,
                            data: {
                                "flag": flag,
                                "salesafter_guid":salesafter_guid,
                                "user_guid":userid,
                                "txt":txt,
                                "money":money
                            },
                            success: function(result) {
                                if(result==1){
                                   layer.confirm('退款返库成功', {
                                      btn: ['确定'] //按钮
                                    }, function(){
                                      window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
                                    }); 
                                }else if(result==2){
                                    layer.alert('退款返库失败，请联系管理员', {
                                        icon: 2,
                                        title: '填退款返库失败，请联系管理员',
                                    });
                                }else{
                                   layer.alert('没有交易记录或者退款金额过大。', {
                                        icon: 2,
                                        title: '填退款返库失败',
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
                content: $('#show_refund'),
            });
        });
        /*填写运单号*/
        $(".btn_take_waybill").click(function(){
            var paystatus=$(".paystatus").val();
            if(paystatus=="1"){
                layer.alert('请等待客户完成支付或报价失效。', {
                        title: '信息',
                });
            }else{
                var a = $(".count4").val();
                if (a==0) {
                   /*初始化请空所有表单内的值*/
                    $(".aft_sal_situ").val("");
                    $(".cour_company").val("");
                    form.render('select', 'lay_take_waybill');
                    $(".ipt_racking_num").val("");
                    layer.open({
                        type: 1,
                        title: "填写运单号",
                        area: ['600px', '450px'], //宽高
                        btn: ['确定', '取消'],
                        btn1: function(index, layero) {
                            var aft_sal_situ_val = $(".aft_sal_situ").val();
                            var cour_company_val = $(".cour_company").val();
                            var ipt_racking_num_val = $(".ipt_racking_num").val();
                            if (aft_sal_situ_val == "") {
                                layer.msg('售后情况不能为空', {icon: 5,anim: 6});
                            } else if(cour_company_val == ""){
                                layer.msg('快递公司不能为空', {icon: 5,anim: 6});
                            } else if(ipt_racking_num_val == ""){
                                layer.msg('运单号不能为空', {icon: 5,anim: 6});
                            }else{
                                /*进行非空验证后在这里获取数据进行提交*/
                                // console.log(aft_sal_situ_val);
                                // console.log(cour_company_val);
                                // console.log(ipt_racking_num_val);
                                $.ajax({
                                    url: path + "auth/admin/fillTrackingNumber",
                                    type: "post",
                                    dataType: "text",
                                    async: false,
                                    data: {
                                        "flag": aft_sal_situ_val,
                                        "salesafter_guid":salesafter_guid,
                                        "user_guid":userid,
                                        "number":ipt_racking_num_val
                                    },
                                    success: function(result) {
                                        if(result==1){
                                           layer.confirm('填写运单号成功', {
                                              btn: ['确定'] //按钮
                                            }, function(){
                                              window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
                                            }); 
                                        }else{
                                            layer.alert('填写运单号失败，请联系管理员', {
                                                icon: 2,
                                                title: '填写运单号失败，请联系管理员',
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
                            }
                        },
                        content: $('#show_take_waybill'),
                    });
                }else if (a == 1) {
                    layer.confirm('运单号已填写过，请问是否重新填写？', {
                      btn: ['确定','取消'] //按钮
                    }, function(){
                        layer.closeAll();
                        /*初始化请空所有表单内的值*/
                        $(".aft_sal_situ").val("");
                        $(".cour_company").val("");
                        form.render('select', 'lay_take_waybill');
                        $(".ipt_racking_num").val("");
                        layer.open({
                            type: 1,
                            title: "填写运单号",
                            area: ['600px', '450px'], //宽高
                            btn: ['确定', '取消'],
                            btn1: function(index, layero) {
                                var aft_sal_situ_val = $(".aft_sal_situ").val();
                                var cour_company_val = $(".cour_company").val();
                                var ipt_racking_num_val = $(".ipt_racking_num").val();
                                if (aft_sal_situ_val == "") {
                                    layer.msg('售后情况不能为空', {icon: 5,anim: 6});
                                } else if(cour_company_val == ""){
                                    layer.msg('快递公司不能为空', {icon: 5,anim: 6});
                                } else if(ipt_racking_num_val == ""){
                                    layer.msg('运单号不能为空', {icon: 5,anim: 6});
                                }else{
                                    /*进行非空验证后在这里获取数据进行提交*/
                                   // console.log(aft_sal_situ_val);
                                   // console.log(cour_company_val);
                                   // console.log(ipt_racking_num_val);
                                    $.ajax({
                                    url: path + "auth/admin/fillTrackingNumber",
                                    type: "post",
                                    dataType: "text",
                                    async: false,
                                    data: {
                                        "flag": aft_sal_situ_val,
                                        "salesafter_guid":salesafter_guid,
                                        "user_guid":userid,
                                        "number":ipt_racking_num_val
                                    },
                                    success: function(result) {
                                        if(result==1){
                                           layer.confirm('修改运单号成功', {
                                              btn: ['确定'] //按钮
                                            }, function(){
                                              window.location.href=path+"/auth/admin/selectSalesafter/"+salesafter_guid+"/"+userid+"";
                                            }); 
                                        }else{
                                            layer.alert('修改运单号失败，请联系管理员', {
                                                icon: 2,
                                                title: '修改运单号失败，请联系管理员',
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
                                }
                            },
                            content: $('#show_take_waybill'),
                        });
                    });
                }
            }
        });
        /*---------------测试部分--------------*/
       var a = $(".control").val();
        if(a==1){
            $(".btn_enter_sn").show();
            $(".btn_take_waybill").hide();
            $(".btn_refund").hide();
            $(".btn_repair_quo").hide();
            $(".btn_exchanges").hide();
            $(".btn_re_enter_sn").hide();
        }else if(a==2){
            $(".btn_take_waybill").show();
            $(".btn_refund").show();
            $(".btn_repair_quo").show();
            $(".btn_exchanges").show();
            $(".btn_re_enter_sn").show();
            $(".btn_enter_sn").hide();
        }else if(a==3){
            $(".btn_take_waybill").show();
            $(".btn_exchanges").show();
            $(".btn_enter_sn").hide();
            $(".btn_repair_quo").hide();
            $(".btn_refund").hide();
            $(".btn_re_enter_sn").hide();
        }else if(a==4){
            $(".btn_enter_sn").hide();
            $(".btn_take_waybill").hide();
            $(".btn_refund").hide();
            $(".btn_repair_quo").hide();
            $(".btn_exchanges").hide();
            $(".btn_re_enter_sn").hide();
        }else{
            $(".btn_enter_sn").hide();
            $(".btn_refund").hide();
            $(".btn_repair_quo").hide();
            $(".btn_exchanges").hide();
            $(".btn_re_enter_sn").hide();
            $(".btn_take_waybill").show();
        }
    });
});

