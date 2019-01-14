$(document).ready(function () {

    //获取地址栏的  商品编号
    var url = document.location.href;
    var goodsNumber = url.split("=")[1];
    /*layui方面js*/
    layui.use(['form', 'table', 'upload', 'element', 'jquery'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table,
            upload = layui.upload;

        //-------执行渲染--------------
        table.render({
            elem: '#commodityList', //指定原始表格元素选择器（推荐id选择器）
            id: 'commodityList',
            url: 'admin/commodity/listGoodsCombo?goodsNumber=' + goodsNumber,
            limit: 10, //每页默认显示的数量
            cols: [
                [
                    //标题栏
                    //{ field: 'id', title: 'ID', width: 200, align: 'center'},
                    {
                        field: 'imgUrl',
                        title: '商品图片',
                        width: 90,
                        style: 'height:100px;',
                        align: 'center',
                        templet: '#imgTpl',
                        style: 'height:70px;width:70px;line-height:70px!important;'
                    },
                    {field: 'goodsName', title: '商品名称', width: 300, align: 'center', event: 'goodsName'},
                    {field: 'goodsPrice', title: '原价', width: 130, align: 'center'},
                    {field: 'discountPrice', title: '套餐价格', width: 130, align: 'center'},
                    {field: 'count', title: '商品数量', width: 100, align: 'center'},
                    {field: 'goodsNumber', title: '商品编号', width: 200, align: 'center'},
                    {field: 'comboGuid', title: '套餐编号', width: 150, align: 'center'},
                    {title: '操作', width: 200, toolbar: '#barDemo', align: 'center'}
                ]
            ],
            done: function (res, curr, count) {
                hoverOpenImg();//显示大图

                $("[data-field = 'putawayTime']").children().each(function (index) {
                    if (index != 0) {
                        var data = $(this).text();
                        $(this).text(getMyDate(parseInt(data)));
                    }
                });
            }
        });
        //===============监听工具条===================
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if (layEvent === 'del') { //删除

                var comboGuid = data.comboGuid;
                layer.confirm('确认删除此套餐么？', function (index) {

                    $.ajax({
                        url: 'admin/commodity/delComboById',
                        data: {
                            comboGuid: comboGuid
                        },
                        type: "POST",
                        success: function (datas) {
                            if (datas == "200") {
                                layer.msg('删除成功！', {icon: 6, time: 1000}, function () {
                                    obj.del();
                                    window.location.reload();
                                });
                            } else {
                                layer.msg('系统故障！', {icon: 5, time: 1000}, function () {
                                    window.location.reload();
                                });
                            }
                        }
                    });
                });
            }
            //查看商品的信息
            if (layEvent === 'detail') {
                var goodsImgList = null;//获取商品下面的所有图片
                var goodsObject = null;//没有图片得时候去获取goods对象拿主图
                var id = data.id;
                $.ajax({
                    url: 'admin/commodity/getGoodsImgList',
                    data: {
                        goodsNumber: data.goodsNumber
                    },
                    type: "GET",
                    async: false,
                    success: function (obj) {
                        goodsImgList = obj;

                        if (obj == "" || obj == null) {
                            $.ajax({
                                url: 'admin/commodity/getCombosObject',
                                data: {
                                    id: id
                                },
                                type: "GET",
                                async: false,
                                success: function (data) {
                                    goodsObject = data;
                                }
                            });
                        }
                    }
                });

                var html = '';
                if (goodsImgList == "") {
                    html += '<img alt="图片" onmouseover="bigger(this)" onmouseout="smaller(this)" src="' + goodsObject.imgUrl + '" style="cursor:pointer;height: 100px;width: 100px;">'
                } else {
                    for (var index in goodsImgList) {
                        html += '<img alt="图片" onmouseover="bigger(this)" onmouseout="smaller(this)" src="' + goodsImgList[index].imgUrl + '" style="cursor:pointer;height: 100px;width: 100px;">'
                    }
                }
                $("#imgUrl").html(html);

                //设值
                /*$("#imgUrl").attr("src",data.imgUrl);*/
                $(".goodsName").html(data.goodsName);
                $(".goodsPrice").html(data.goodsPrice);
                $(".discountPrice").html(data.discountPrice);
                $(".goodsNumber").html(data.goodsNumber);
                $(".comboGuid").html(data.comboGuid);

                layer.open({
                    type: 1,
                    title: "商品名称 为 &nbsp;" + data.goodsName + "&nbsp; 的套餐基本信息",
                    area: ['600px', '400px'], //宽高
                    btn: ['我知道了'],
                    content: $('.detailcontent'),
                });
            }
            else if (layEvent === 'edit') { //编辑
                var discountPrice = data.discountPrice;
                var id = data.id;
                layer.prompt({
                    formType: 2,
                    value: data.discountPrice,
                    title: '编辑商品价格',
                    area: ['150px', '50px'] //自定义文本域宽高
                }, function (value, index, elem) {
                    $.ajax({
                        url: 'admin/commodity/updataCombo',
                        type: "POST",
                        data: {
                            discount_price: value,
                            id: id
                        },
                        success: function (data) {
                            if (data == "200") {
                                layer.msg('修改成功！', {icon: 6, time: 1000}, function () {
                                    window.location.reload();
                                });
                            } else {
                                layer.msg('修改失败！', {icon: 5, time: 1000}, function () {
                                    layer.close(index);
                                });
                            }
                        }
                    });
                });
            }
        });
    });

});

//点击添加套餐
$("#addComboBtn").click(function (event) {
    var url = document.location.href;
    var goodsNumber = url.split("=")[1];
    window.location.href = "admin/commodity/addCombo?goodsNumber=" + goodsNumber;
});

//查看按钮图片放大
function bigger(data) {
    var img = $(data)[0];
    img.style.width = '400px';
    img.style.height = '400px';
}

//查看按钮图片缩小图片
function smaller(data) {
    var img = $(data)[0];
    img.style.width = '100px';
    img.style.height = '100px';
}

//显示大图
function hoverOpenImg() {
    var img_show = null; // tips提示
    $('td img').hover(function () {
        var img = "<img class='img_msg' src='" + $(this).attr('src') + "' style='width:300px;' />";
        img_show = layer.tips(img, this, {
            tips: [2, 'rgba(41,41,41,.5)']
            , area: ['330px']
        });
    }, function () {
        layer.close(img_show);
    });
    $('td img').attr('style', 'max-width:70px');
}
