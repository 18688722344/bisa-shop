$(document).ready(function () {

    //获取地址栏的  商品编号
    var url = document.location.href;
    var goodsNumber = url.substring(url.indexOf("=") + 1, url.indexOf("&"));
    var goodsClassify;//商品的分类id
    $("#img_goods_number").val(goodsNumber);

    //隐藏简体，繁体的提交按钮
    document.getElementById("updateCN").style.display = "none";
    document.getElementById("updateUS").style.display = "none";

    $(".lang_btn button").click(function () {
        if ($(this).attr('data-num') == 1) {
            //简体
            $("#lang").val(1);
            loadImgs(goodsNumber, 1);
            $(this).removeClass("layui-btn-primary");
            $(".lang_btn button:eq(1)").addClass('layui-btn-primary');
            $(".lang_btn button:eq(2)").addClass('layui-btn-primary');
        } else if ($(this).attr('data-num') == 2) {
            //繁体
            $("#lang").val(2);
            loadImgs(goodsNumber, 2);
            $(this).removeClass("layui-btn-primary");
            $(".lang_btn button:eq(0)").addClass('layui-btn-primary');
            $(".lang_btn button:eq(2)").addClass('layui-btn-primary');
        } else {
            //英文
            $("#lang").val(3);
            loadImgs(goodsNumber, 3);
            $(this).removeClass("layui-btn-primary");
            $(".lang_btn button:eq(0)").addClass('layui-btn-primary');
            $(".lang_btn button:eq(1)").addClass('layui-btn-primary');
        }
    })
    //加载商品信息(繁体)
    $.ajax({
        url: 'admin/commodity/loadMainGoodsObject',
        type: "GET",
        data: {
            goodsNumber: goodsNumber
        },
        async: false,
        success: function (data) {
            $('#internationalzation').val(2);
            showMainGoods(data.goods);
            showParentSelect(data.goods, data.list);  //父级商品下拉框
            goodsClassify = data.formSelectsDto;
        }
    });

    //监听繁体的提交
    $("#hk").click(function () {
        document.getElementById("input1").style.display = "";
        document.getElementById("input2").style.display = "";
        document.getElementById("input3").style.display = "";
        document.getElementById("input4").style.display = "";
        document.getElementById("input5").style.display = "";
        document.getElementById("updateHK").style.display = "";
        document.getElementById("reset").style.display = "";
        document.getElementById("updateCN").style.display = "none";
        document.getElementById("updateUS").style.display = "none";
        $.ajax({
            url: 'admin/commodity/loadMainGoodsObject',
            type: "GET",
            data: {
                goodsNumber: goodsNumber
            },
            async: false,
            success: function (data) {
                showMainGoods(data.goods);
                showParentSelect(data.goods, data.list);  //父级商品下拉框
                goodsClassify = data.formSelectsDto;
            }
        });
    });

    //监听中文的提交
    $("#china").click(function () {
        document.getElementById("input1").style.display = "none";
        document.getElementById("input2").style.display = "none";
        document.getElementById("input3").style.display = "none";
        document.getElementById("input4").style.display = "none";
        document.getElementById("input5").style.display = "none";
        document.getElementById("updateCN").style.display = "";
        document.getElementById("updateHK").style.display = "none";
        document.getElementById("updateUS").style.display = "none";
        document.getElementById("reset").style.display = "none";
        $.ajax({
            url: 'admin/commodity/loadGoodsInternationalization',
            type: "GET",
            data: {
                goods_number: goodsNumber,
                internationalization: 1
            },
            dataType: "json",
            async: false,
            success: function (data) {

                $('#goodsName').val("");//清空上次input框里的数据
                $('#title').val("");
                $('#description').val("");
                if (data.list != null) {
                    $('#goodsName').val(data.list.goods_name);  //往input框里传值
                    $('#title').val(data.list.title);
                    $('#description').val(data.list.description);
                    $('#internationalzation').val(1);
                } else {
                    layer.msg('没有该语言版本，请核对后修改！', {icon: 5, time: 2000}, function () {
                    });
                }
            }
        });
    });

    //监听英文的提交
    $("#english").click(function () {
        document.getElementById("input1").style.display = "none";
        document.getElementById("input2").style.display = "none";
        document.getElementById("input3").style.display = "none";
        document.getElementById("input4").style.display = "none";
        document.getElementById("input5").style.display = "none";
        document.getElementById("updateUS").style.display = "";
        document.getElementById("updateHK").style.display = "none";
        document.getElementById("updateCN").style.display = "none";
        document.getElementById("reset").style.display = "none";
        $.ajax({
            url: 'admin/commodity/loadGoodsInternationalization',
            type: "GET",
            data: {
                goods_number: goodsNumber,
                internationalization: 3
            },
            dataType: "json",
            async: false,
            success: function (data) {
                $('#goodsName').val("");//清空上次input框里的数据
                $('#title').val("");
                $('#description').val("");
                if (data.list != null) {
                    $('#goodsName').val(data.list.goods_name);  //往input框里传值
                    $('#title').val(data.list.title);
                    $('#description').val(data.list.description);
                    $('#internationalzation').val(3);
                } else {
                    layer.msg('没有该语言版本，请核对后修改！', {icon: 5, time: 2000}, function () {
                    });
                }
            }
        });
    });

    //初始化layui第三方的插件
    layui.config({
        base: 'plugin/formSelects/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });

    /*layui方面js*/
    layui.use(['form', 'formSelects'], function () {
        var form = layui.form,
            formSelects = layui.formSelects;

        formSelects.config('select15', {
            success: function (id, url, val, result) {
                formSelects.value('select15', eval(goodsClassify));//类别回显["2/10"]
            },
            error: function (id, url, val, err) {
                console.log("err回调: " + url);
            }
        });

        //获取远程的数据
        formSelects.data('select15', 'server', {
            url: 'admin/commodity/getClassifyInfo',
            linkage: true,
            linkageWidth: 100,   //代表每一级别的宽度, 默认是100
        });

        //监听繁体按钮提交
        form.on('submit(updateHK)', function (data) {
            var obj = data.field;
            var classifyId = getGoodsClassify(obj.classify);

            $.ajax({
                url: 'admin/commodity/updateCommodity',
                data: {
                    goodsNumber: goodsNumber,
                    classifyId: classifyId,
                    description: obj.description,
                    goodsName: obj.goodsName,
                    goodsPrice: obj.goodsPrice,
                    goodsStatus: obj.goodsStatus,
                    needPost: obj.needpost,
                    storeNumber: obj.storeNumber,
                    title: obj.title,
                    parentId: obj.parentId
                },
                type: "POST",
                success: function (data) {
                    if (data.msg == "100" || data.msg == "200") {//编辑父级商品
                        layer.msg('修改成功！', {icon: 6, time: 1500}, function () {
                            window.location.reload();
                        });
                    }
                    if (data.flag == false) {
                        layer.msg('输入框不能为空，请核对后添加!', {icon: 5, time: 2000}, function () {
                        });
                    }
                }
            });
            return false;
        });

        //监听中文按钮提交
        form.on('submit(updateCN)', function () {
            var goodsName = document.getElementById('goodsName').value;
            var description = document.getElementById('description').value;
            var title = document.getElementById('title').value;
            $.ajax({
                url: 'admin/commodity/updateInternational',
                data: {
                    internationalization: 1,
                    goodsNumber: goodsNumber,
                    description: description,
                    goodsName: goodsName,
                    title: title,
                },
                type: "POST",
                async: false,
                success: function (data) {
                    if (data.flag == true) { //添加父级商品，需要添加图片；子级商品，加载图片
                        layer.msg('修改成功！', {icon: 6, time: 1000}, function () {
                            $(".goods-img-div").removeClass("dis-n");//添加图片
                            $("#img_goods_number").val(data.msg);
                            loadImgs(data.msg);
                        });
                    } else if (data.flag == false) {
                        layer.msg('输入框不能为空，请核对后添加!', {icon: 5, time: 2000}, function () {

                        });
                    }
                }
            });
            return false;
        });

        //监听英文按钮提交
        form.on('submit(updateUS)', function () {
            var goodsName = document.getElementById('goodsName').value;
            var description = document.getElementById('description').value;
            var title = document.getElementById('title').value;
            $.ajax({
                url: 'admin/commodity/updateInternational',
                data: {
                    internationalization: 3,
                    goodsNumber: goodsNumber,
                    description: description,
                    goodsName: goodsName,
                    title: title,
                },
                type: "POST",
                async: false,
                success: function (data) {
                    if (data.flag == true) { //添加父级商品，需要添加图片；子级商品，加载图片
                        layer.msg('修改成功！', {icon: 6, time: 1000}, function () {
                            $(".goods-img-div").removeClass("dis-n");//添加图片
                            $("#img_goods_number").val(data.msg);
                            loadImgs(data.msg);
                        });
                    } else if (data.flag == false) {
                        layer.msg('输入框不能为空，请核对后添加!', {icon: 5, time: 2000}, function () {

                        });
                    }
                }
            });
            return false;
        });

    });

});

//父级商品下拉框加载
function showParentSelect(goods, list) {
    var html = '<option value="0">请选择父级商品 (非必选)</option>';
    for (var index in list) {
        if (goods.parentId == list[index].id) {
            html += "<option value='" + list[index].id + "' selected='selected'>" + list[index].goodsName + "</option>";
        } else {
            html += "<option value='" + list[index].id + "'>" + list[index].goodsName + "</option>";
        }
    }
    $("#option").html(html);
}

//编辑商品，填充商品数据
function showMainGoods(data) {
    if (data != "" && data != null) {

        $("input[name='goodsNumber']").val(data.goodsNumber);
        $("input[name='goodsName']").val(data.goodsName);
        $("input[name='title']").val(data.title);
        $("textarea[name='description']").val(data.description);
        $("input[name='goodsPrice']").val(data.goodsPrice);
        $("input[name='storeNumber']").val(data.storeNumber);

        $("input[class=goodsStatus1]").attr("checked", data.goodsStatus == in_sale ? true : false);
        $("input[class=goodsStatus2]").attr("checked", data.goodsStatus == invalid ? true : false);
        $("input[class=goodsStatus3]").attr("checked", data.goodsStatus == sale_out ? true : false);

        $("input[class=needpost1]").attr("checked", data.needPost == need_Post ? true : false);
        $("input[class=needpost2]").attr("checked", data.needPost == noPost ? true : false);
    }
}

//处理数据，将ab/cd处理成 cd
function getGoodsClassify(classifyStr) {
    var index = classifyStr.indexOf("/");
    if (index == -1) {
        return classifyStr;
    } else {
        classifyStr = classifyStr.substring(index + 1, classifyStr.length);
        return classifyStr;
    }
}

