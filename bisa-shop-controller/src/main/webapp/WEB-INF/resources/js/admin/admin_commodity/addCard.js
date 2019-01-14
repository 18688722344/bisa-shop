$(document).ready(function () {

    //加载layui表单元素
    layui.use('form', function () {
        var form = layui.form;
        form.render();

        //监听提交
        form.on('submit(createBtn)', function (data) {
            var obj = data.field;

            $.ajax({
                url: 'admin/commodity/createServiceCard',
                data: {
                    cardName: $("#cardName").val(),
                    cardType: obj.serviceType,
                    serviceToken: obj.serviceToken,
                    count: obj.count,
                    goodsNumber: obj.goodsNumber,
                    amount: obj.amount
                },
                type: "POST",
                success: function (data) {
                    var index = layer.load(1); //加载
                    if (data.flag == true) {
                        layer.close(index);  //关闭
                        layer.msg(data.msg + '张激活卡已全部生成！', {icon: 6, time: 1500}, function () {
                        });
                    } else {

                        layer.msg("异常！已生成" + data.msg + "张，继续生成或请联系运维人员！", {icon: 5, time: 2000}, function () {
                        });

                        layer.close(index);  //关闭
                    }
                }
            });
            return false;
        });
        //选择商品下拉框
        form.on('select(goodsNumber)', function (data) {
            var cardName = $(".goods-select-div .layui-this").text();
            $("#cardName").val(cardName);
        });
    });

    //加载服务编码下拉框
    loadServiceCategory();

    //加载所属商品下拉框
    loadGoodsInfo();
});

//加载商品类型
function loadServiceCategory() {
    $.ajax({
        url: 'admin/commodity/loadServiceCategoryObject',
        type: "GET",
        async: false,
        success: function (list) {
            loadServiceCategorySelect(list);
        }
    });
}

function loadServiceCategorySelect(list) {
    var html = '';
    for (var index in list) {
        html += '<option value="' + list[index].stoken + '">' + list[index].desc + '</option>';
    }
    $("#service_select").html(html);
}

//加载主商品
function loadGoodsInfo() {
    //加载主商品
    $.ajax({
        url: 'admin/commodity/listVirtualGoods',
        type: "GET",
        async: false,
        success: function (data) {
            var html = '<option value="">--请选择--</option>';
            for (var index in data) {
                html += '<option value="' + data[index].goodsNumber + '">' + data[index].goodsName + '</option>';
            }
            $("#goodsNumber").html(html);
        }
    });
}
