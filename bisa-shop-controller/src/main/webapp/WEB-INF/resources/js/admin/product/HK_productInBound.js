$(document).ready(function() {
    /*模拟数据从服务器发来的数据和要发送给服务器的数据*/
    var json=$(".json").val();
    var cargodata_in =  eval('(' + json + ')');
    var server_data_out = { "treeroot": [] };
    var path=$("base").attr("href");
    /*主变量初始化*/
    var cargodata = $.extend(true,{},cargodata_in);
    var server_data = $.extend(true,{},server_data_out);
    /*全局变量 选择框被选中的值*/
    var sel_val;
    /*-------------------------------------------------
    传送给服务器的数据模拟介绍
        treeroot:根节点
        name：服务器传来的子商品类型的val
        des：增加了多少种类型的集合
        state：类型，0代表心电仪系列需要输入扫描码的，1代表数据线系列的数量
        val：当state等于0时代表设备的型号，当state等于1时代表产品的数量
    例：
        var server_data = {"treeroot":[{"name":"1000001","des":[{"val":"21000061"},{"val":"21000060"},{"val":"61000059"}],"state":0},{"name":"1000002","des":[{"val":"10"},{"val":"20"},{"val":"30"}],"state":1},{"name":"1000003","des":[{"val":"5"},{"val":"2"}],"state":1},{"name":"1000004","des":[{"val":"1"}],"state":1}]};
    ---------------------------------------------------*/

    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function() {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;
        /*初始化下拉框和订单号*/
        sle_initialize();

        function sle_initialize() {
            var data_commodity_length = cargodata.commodity.length;
            $(".sle_type_c").empty();
            var optionVarsel = "";
            optionVarsel += "<option value=\"\" selected=\"\"><\/option>";
            $(".sle_type_c").append(optionVarsel);
            for (var i = 0; i < data_commodity_length; i++) {
                var opt_key_one = cargodata.commodity[i].val;
                var opt_key_two = cargodata.commodity[i].name + "（现库存" + cargodata.commodity[i].inventory + "件）";
                var optionVar = "";
                optionVar += "<option value=\"" + opt_key_one + "\">" + opt_key_two + "<\/option>";
                $(".sle_type_c").append(optionVar);
            };
            form.render('select');
        };
        /*选择产品时表单的变换*/
        form.on('select(sle_type_f)', function(data) {
            sel_val = data.value; //得到被选中的值
            var data_commodity_length = cargodata.commodity.length;
            for (var i = 0; i < data_commodity_length; i++) {
                var opt_key_one = cargodata.commodity[i].val;
                if (sel_val == opt_key_one) {
                    var pot_key_state = parseInt(cargodata.commodity[i].state);
                    if (pot_key_state == 0) {
                        $('.cargo_v2_input').val("");
                        $('.cargo_v2_box').removeClass('bounceInRight');
                        $('.cargo_v2_box').addClass('fadeOutRightBig');
                        setTimeout(function() {
                            $(".cargo_v2_box").hide();
                            $('.cargo_v2_box').removeClass('fadeOutRightBig');
                            $('.cargo_v2_box').addClass('bounceInRight');
                            $(".cargo_v1_box").show();
                        }, 200);
                    } else if (pot_key_state == 1) {
                        $('.cargo_v1_input').val("");
                        $('.cargo_v1_box').removeClass('bounceInRight');
                        $('.cargo_v1_box').addClass('fadeOutRightBig');
                        setTimeout(function() {
                            $(".cargo_v1_box").hide();
                            $('.cargo_v1_box').removeClass('fadeOutRightBig');
                            $('.cargo_v1_box').addClass('bounceInRight');
                            $(".cargo_v2_box").show();
                        }, 200);
                    };
                };
            };
        });
        /*有SN码的商品入库添加操作*/
        $(".cargo_v1_box_add").click(function() {
            cargo_v1_box_add_action();
        });
        $(".cargo_v1_input").keydown(function(e) {  
           if (e.keyCode == 13) {  
                cargo_v1_box_add_action();
            };  
        });
        function cargo_v1_box_add_action(){
            if (sel_val == "" || sel_val == null) {
                layer.msg("请先选择要入库的商品类型", { icon: 5, anim: 6 });
            } else {
                var cargo_ipt_val = $(".cargo_v1_input").val();
                if (cargo_ipt_val == null || cargo_ipt_val == "" || typeof(cargo_ipt_val) == "undefined") {
                    layer.msg("请使用扫描枪或者填写商品SN码", { icon: 5, anim: 6 });
                } else {
                    if (server_data.treeroot.length == 0) { //第一次增加的时候
                        /*验证修改库存，初始化选择框*/
                        for (var i = 0; i < cargodata.commodity.length; i++) {
                            var opt_key_one = cargodata.commodity[i].val;
                            if (sel_val == opt_key_one) {
                                /*定义内部对象 推入des 推入treeroot*/
                                var tree_obj = { name: sel_val, state: 0, des: [] };
                                var des_obj = { val: cargo_ipt_val };
                                tree_obj.des.push(des_obj);
                                server_data.treeroot.push(tree_obj);
                                canvas_server_data(); //重载
                                $(".cargo_v1_input").val("");
                            }
                        };
                    } else { //后面添加
                        var trueor = false; //添加的内容在框内,有框的情况
                        for (var i = 0; i < server_data.treeroot.length; i++) {
                            var s_name = server_data.treeroot[i].name;
                            if (sel_val == s_name) {
                                trueor = true;
                            }
                        };
                        if (trueor) { //添加的内容在框内,有框的情况
                            var soletrue = false;
                            for (var i = 0; i < server_data.treeroot.length; i++) {
                                var s_name = server_data.treeroot[i].name;
                                if (sel_val == s_name) {
                                    for (var j = 0; j < server_data.treeroot[i].des.length; j++) {
                                        var soleval = server_data.treeroot[i].des[j].val;
                                        if (cargo_ipt_val == soleval) {
                                            soletrue = true;
                                        }
                                    };
                                }
                            };
                            if (soletrue) {
                                $(".cargo_v1_input").val("");
                                layer.msg("SN码为不能重复的设备码，请勿重复添加！", { icon: 5, anim: 6 });
                            } else{
                                /*验证修改库存，初始化选择框*/
                                for (var k = 0; k < cargodata.commodity.length; k++) {
                                    var opt_key_one = cargodata.commodity[k].val;
                                    if (sel_val == opt_key_one) {
                                        var index_ser;
                                        for (var m = 0; m < server_data.treeroot.length; m++) {
                                            if (sel_val == server_data.treeroot[m].name) {
                                                index_ser = m;
                                            };
                                        };
                                        /*推入des*/
                                        var des_obj = { val: cargo_ipt_val };
                                        server_data.treeroot[index_ser].des.push(des_obj);
                                        canvas_server_data(); //重载
                                        $(".cargo_v1_input").val("");
                                    }
                                };
                            }

                        } else { //没有框的情况
                            /*验证修改库存，初始化选择框*/
                            for (var i = 0; i < cargodata.commodity.length; i++) {
                                var opt_key_one = cargodata.commodity[i].val;
                                if (sel_val == opt_key_one) {
                                    /*定义内部对象 推入des 推入treeroot*/
                                    var tree_obj = { name: sel_val, state: 0, des: [] };
                                    var des_obj = { val: cargo_ipt_val };
                                    tree_obj.des.push(des_obj);
                                    server_data.treeroot.push(tree_obj);
                                    canvas_server_data(); //重载
                                    $(".cargo_v1_input").val("");
                                }
                            };
                        }
                    }
                }
            };
        };
        /*无SN码的商品入库添加操作*/
        $(".cargo_v2_box_add").click(function() {
            if (sel_val == "" || sel_val == null) {
                layer.msg("请先选择要配货的商品类型", { icon: 5, anim: 6 });
            }else {
                var cargo_ipt_val = $(".cargo_v2_input").val();
                if (cargo_ipt_val == null || cargo_ipt_val == "" || typeof(cargo_ipt_val) == "undefined") {
                    layer.msg("请输入商品的数量", { icon: 5, anim: 6 });
                } else if(cargo_ipt_val == 0){
                    layer.msg("配货商品数量最少为1件", { icon: 5, anim: 6 });
                } else {
                    if (server_data.treeroot.length == 0) { //第一次增加的时候
                        /*验证修改库存，初始化选择框*/
                        for (var i = 0; i < cargodata.commodity.length; i++) {
                            var opt_key_one = cargodata.commodity[i].val;
                            if (sel_val == opt_key_one) {
                                /*定义内部对象 推入des 推入treeroot*/
                                var tree_obj = { name: sel_val, state: 1, des: [] };
                                var des_obj = { val: cargo_ipt_val };
                                tree_obj.des.push(des_obj);
                                server_data.treeroot.push(tree_obj);
                                canvas_server_data(); //重载
                                $(".cargo_v2_input").val("");
                            }
                        };
                    } else { //后面添加
                        var trueor = false; //添加的内容在框内,有框的情况
                        for (var i = 0; i < server_data.treeroot.length; i++) {
                            var s_name = server_data.treeroot[i].name;
                            if (sel_val == s_name) {
                                trueor = true;
                            }
                        };
                        if (trueor) { //判断添加的内容在框内,有框的情况（true为有框）
                            /*验证修改库存，初始化选择框*/
                            for (var k = 0; k < cargodata.commodity.length; k++) {
                                var opt_key_one = cargodata.commodity[k].val;
                                if (sel_val == opt_key_one) {
                                    var index_ser;
                                    for (var m = 0; m < server_data.treeroot.length; m++) {
                                        if (sel_val == server_data.treeroot[m].name) {
                                            index_ser = m;
                                        };
                                    };
                                    /*推入des*/
                                    var des_obj = { val: cargo_ipt_val };
                                    server_data.treeroot[index_ser].des.push(des_obj);
                                    canvas_server_data(); //重载
                                    $(".cargo_v2_input").val("");
                                }
                            };
                        } else {
                            /*验证修改库存，初始化选择框*/
                            for (var i = 0; i < cargodata.commodity.length; i++) {
                                var opt_key_one = cargodata.commodity[i].val;
                                if (sel_val == opt_key_one) {
                                    /*定义内部对象 推入des 推入treeroot*/
                                    var tree_obj = { name: sel_val, state: 1, des: [] };
                                    var des_obj = { val: cargo_ipt_val };
                                    tree_obj.des.push(des_obj);
                                    server_data.treeroot.push(tree_obj);
                                    canvas_server_data(); //重载
                                    $(".cargo_v2_input").val("");
                                }
                            };
                        };
                    }
                };
            };
        });
        /*根据数据(server_data)画图*/
        canvas_server_data();

        function canvas_server_data() {
            $(".main_singo_box").empty();
            for (var i = 0; i < server_data.treeroot.length; i++) {
                var box_title; //盒子标题
                var c_singlo_btn = ""; //盒子按钮代码
                /*取盒子标题前缀*/
                for (var j = 0; j < cargodata.commodity.length; j++) {
                    if (server_data.treeroot[i].name == cargodata.commodity[j].val) {
                        box_title = cargodata.commodity[j].name;
                    }
                };
                /*取盒子标题后缀和取盒子内部按钮代码*/
                if (parseInt(server_data.treeroot[i].state) == 0) {
                    //取盒子标题后缀
                    var des_length = server_data.treeroot[i].des.length;
                    box_title = "总计：" + box_title + " × " + des_length + "台";
                    //取盒子内部按钮代码
                    for (var j = 0; j < server_data.treeroot[i].des.length; j++) {
                        var btn_val = server_data.treeroot[i].des[j].val;
                        c_singlo_btn += "<button name=\"" + j + "\" class=\"layui-btn layui-btn-sm mt-10 mb-10 ml-10 btn_cargo_sin_del\">" + btn_val + "<i class=\"layui-icon col-white\"><\/i><\/button>";
                    };
                } else if (parseInt(server_data.treeroot[i].state) == 1) {
                    //取盒子标题后缀
                    var num = new Number;
                    for (var j = 0; j < server_data.treeroot[i].des.length; j++) {
                        num = num + parseInt(server_data.treeroot[i].des[j].val);

                        //取盒子内部按钮代码
                        var btn_val = server_data.treeroot[i].des[j].val;
                        c_singlo_btn += "<button name=\"" + j + "\" class=\"layui-btn layui-btn-sm mt-10 mb-10 ml-10 btn_cargo_sin_del\">" + box_title + " × " + btn_val + "件 " + "<i class=\"layui-icon col-white\"><\/i><\/button>";
                        //取盒子内部按钮代码end
                    };
                    box_title = "总计：" + box_title + " × " + num + "件";
                };
                var c_singlo_box = "";
                c_singlo_box += "<fieldset class=\"layui-elem-field mb-20\">";
                c_singlo_box += "   <legend class=\"\">" + box_title + "<\/legend>";
                c_singlo_box += "   <div id=\"" + server_data.treeroot[i].name + "\" class=\"layui-field-box\">";
                c_singlo_box += c_singlo_btn;
                c_singlo_box += "   <\/div>";
                c_singlo_box += "<\/fieldset>";
                $(".main_singo_box").append(c_singlo_box);
            }
            monitordel();
        }
        /*监听删除按钮*/
        function monitordel() {
            $(".btn_cargo_sin_del").click(function() {
                var box_name = $(this).parent().attr("id");
                var this_name = $(this).attr("name");
                layer.confirm('您确定要删除这项入库内容么？', {
                    btn: ['确定', '我在想想'],
                    title: '确认删除'
                }, function() {
                    layer.closeAll();
                    for (var i = 0; i < server_data.treeroot.length; i++) {
                        if (box_name == server_data.treeroot[i].name) {
                            $(".cargo_v1_input").val("");
                            /*更新服务器资料，画图*/
                            server_data.treeroot[i].des.splice(this_name,1);
                            if (server_data.treeroot[i].des.length == 0) {
                                server_data.treeroot.splice(i,1);
                            } 
                            canvas_server_data();
                        }
                    };
                }, function() {});
            });
        }
        /*提交事件*/
        $(".sub_affirm").click(function() {
            var data_length = server_data.treeroot.length;
            if (data_length == 0) {
                layer.msg("请先配置货品内容在做提交", { icon: 5, anim: 6 });
            } else{
                layer.confirm('您确定要入库么？', {
                        btn: ['确定', '我在想想'],
                        title: '确认入库'
                }, function() {
                    layer.closeAll();
                    //server_data为需要提交的数据

                    var json4=[];
                    for(var i=0;i<server_data.treeroot.length;i++){
                        var json2={};
                        json2.product_id=server_data.treeroot[i].name;
                        if(server_data.treeroot[i].state==1){
                            //普通商品
                            var num=0;
                            for(var a=0;a<server_data.treeroot[i].des.length;a++){
                               num+=parseInt(server_data.treeroot[i].des[a].val);
                            }
                            json2.user_guid=num;
                            json2.p_type_id=2;
                            json4.push(json2)
                        }else{
                            //设备的
                          for(var a=0;a<server_data.treeroot[i].des.length;a++){
                                 var json3={};
                                 json3.product_id=server_data.treeroot[i].name;
                                 json3.device_id=server_data.treeroot[i].des[a].val;
                                 var device_id=server_data.treeroot[i].des[a].val;
                                 json3.finishedcategories=device_id.substring(0,3);
                                 json3.year=device_id.substring(3,5);
                                 json3.month=device_id.substring(5,7);
                                 json3.batch_num=device_id.substring(7,9);
                                 json3.country=device_id.substring(9,11);
                                 json3.sales_area=json3.country;
                                 json3.device_fid=device_id.substring(11,17);
                                 json3.finishedpro_num=json3.year+json3.month+json3.batch_num;
                                 json3.sn=json3.year+json3.month+json3.batch_num+json3.device_fid;
                                 json3.shipment_isnot="20";
                                 json3.shipment_country=json3.country;
                                 json3.sn_index=json3.year+"000000";
                                 json3.p_type_id=1;
                                 json3.user_guid=0;
                                 json3.warranty_status="10";
                                 json4.push(json3);
                            }  
                        } 
                    }
                    /*拼接数据结束*/
                    var serdate=JSON.stringify(json4);
                     $.ajax({
                        url: path + "auth/admin/put/adminInventorysubmit",
                        type: "POST",
                        dataType: "text",
                        async: false,
                        data: {
                            "server_data": serdate,
                        },
                        success: function(result) {
                            if(result=="2"){
                                layer.confirm('请先去登录，登录后可以开始入库？', {
                                  btn: ['确定'] //按钮
                                }, function(){
                                  window.location.href=path+"auth/admin/login";
                                });
                            }else if(result=="success"){
                                layer.confirm('产品入库成功！', {
                                  btn: ['确定'] //按钮
                                }, function(){
                                  window.location.href=path+"auth/admin/adminstock";
                                });
                            }else{
                               var cargodata_in =  eval('(' + result + ')');
                           if(cargodata_in.data.length>0){
                             var str="悉心动态心电记录仪 HC3A250 已入过库的设备的设备号有：</br>";
                               var aa="";
                               for(var i=0;i<cargodata_in.data.length;i++){
                                    if(i<cargodata_in.data.length-1){
                                        aa=aa+cargodata_in.data[i]+"</br>";
                                    }else{
                                        aa=aa+cargodata_in.data[i]+"</br>";
                                    }
                                    
                                 }
                                  str=str+aa;
                            }
                            if(cargodata_in.data3.length>0){
                                var str1="悉心动态心电记录仪 HC3A250 入库失败的设备号有：";
                                var bb="";
                                for(var i=0;i<cargodata_in.data3.length;i++){
                                    if(i<cargodata_in.data3.length-1){
                                        bb=bb+cargodata_in.data3[i]+"</br>";
                                    }else{
                                        bb=bb+cargodata_in.data3[i]+"</br>";
                                    }
                                }
                               str1=str1+bb; 
                            }

                            if(cargodata_in.data2.length>0){
                                var cc="";
                                for(var i=0;i<cargodata_in.data2.length;i++){
                                    if(i<cargodata_in.data1.length-1){
                                        cc=cc+cargodata_in.data2[i]+"添加失败</br>";
                                    }else{
                                        cc=cc+cargodata_in.data2[i]+"添加失败</br>";
                                    } 
                                }
                            }
                          if(str==null||str==undefined||str==""){
                            str="";
                          }
                          if(str1==null||str1==undefined||str1==""){
                            str1="";
                          }
                          if(cc==null||cc==undefined||cc==""){
                            cc="";
                          }
                            layer.confirm("以下产品已存在，请勿重复入库。"+"</br>"+str+"</br>"+str1+"</br>"+cc+"</br>"+"注：如多次添加失败，请联系管理员。", {
                                  btn: ['确定'] //按钮
                                }, function(){
                                 window.location.href=path+"auth/admin/adminstock";
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
                }, function() {});
            }
        });
        /*重置事件*/
        $(".reset_cargo").click(function() {
            layer.confirm('您确定要重置目前所配置的入库信息么？', {
                    btn: ['确定', '我在想想'],
                    title: '确认重置'
            }, function() {
                layer.closeAll();
                cargodata = $.extend(true,{},cargodata_in);
                server_data = $.extend(true,{},server_data_out);
                sel_val = null;
                sle_initialize();
                canvas_server_data();
                $(".cargo_v1_box").hide();
                $(".cargo_v2_box").hide();
                $(".cargo_v1_input").val("");
                $(".cargo_v2_input").val("");
            }, function() {});
        });
    });
});