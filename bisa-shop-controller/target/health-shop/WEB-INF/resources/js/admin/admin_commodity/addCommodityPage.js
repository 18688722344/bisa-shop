$(document).ready(function () {

    //获取地址栏的  商品编号
    var url = document.location.href;
    var goodsNumber = url.split("=")[1];
    var goodsClassify;//商品的分类id
    //加载主商品
    loadMainGoodsDatas();

    //初始化layui第三方的插件
    layui.config({
        base: 'plugin/formSelects/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });

    /*
        $.ajax({
            url:'admin/commodity/getClassifyInfo',
            dataType:'json',
            type:'GET',
            success:function(data){

                $.each(data,function(index,item){
                    $('#selectID').append(new Option(item.name,item.id));//往下拉菜单里添加元素
                })

                form.render();//菜单渲染 把内容加载进去
            }
        })*/
    /*layui方面js*/
    layui.use(['form', 'layer', 'element', 'formSelects'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            formSelects = layui.formSelects;

        //获取远程的数据
        formSelects.data('select15', 'server', {
            url: 'admin/commodity/getClassifyInfo',
            linkage: true,
            linkageWidth: 100   //代表每一级别的宽度, 默认是100
        });


        //这个是标签的回显操作
        /*formSelects.config('select16', {
        	data : {
        		goodsNumber : goodsNumber
        	},
        	success: function(id, url, val, result){
        		var obj = eval('(' + result.msg + ')');
        		formSelects.value('select16', obj)
        	}
        });
        formSelects.data('select16', 'server', {
        	url: 'admin/commodity/getGoodsTag',
        	linkage: true,
        	linkageWidth: 100   //代表每一级别的宽度, 默认是100
        });*/

        //监听提交
        form.on('submit(addBtn)', function (data) {
            var obj = data.field;
            var classifyId = getGoodsClassify(obj.classify);

            $.ajax({
                url: 'admin/commodity/insertCommodity',
                data: {
                    goodsNumber: goodsNumber,
                    classifyId: classifyId,
                    description: obj.description,
                    goodsName: obj.goodsName,
                    goodsPrice: obj.goodsPrice,
                    goodsStatus: obj.goodsStatus,
                    goodsTag: obj.goodsTag,
                    needPost: obj.needpost,
                    storeNumber: obj.storeNumber,
                    title: obj.title,
                    parentId: obj.parentId
                },
                type: "POST",
                success: function (data) {
                    if (data.flag == true) { //添加父级商品，需要添加图片；子级商品，加载图片
                        layer.msg('添加成功！请添加图片~', {icon: 6, time: 1000}, function () {
                            $(".goods-img-div").removeClass("dis-n");//添加图片
                            $("#img_goods_number").val(data.msg);
                            loadImgs(data.msg);
                        });
                    } else if (data.flag == false) {
                        layer.msg('操作失败！请联系运维人员！', {icon: 5, time: 2000}, function () {

                        });
                    }
                }
            });
            return false;
        });


    });

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

    //处理数据，将ab/cd处理成 ab
    function getParentGoodsClassify(classifyStr) {
        var index = classifyStr.indexOf("/");
        if (index == -1) {
            return classifyStr;
        } else {
            classifyStr = classifyStr.substring(0, index + 1);
            return classifyStr;
        }
    }
});

//加载主商品
function loadMainGoodsDatas() {
    $.ajax({
        url: 'admin/commodity/loadMainGoodsObject',
        type: "GET",
        async: false,
        success: function (data) {
            showParentSelect(null, data.list);	//父级商品下拉框
        }
    });
}

//父级商品下拉框加载
function showParentSelect(goods, list) {
    var html = '<option value="0">请选择父级商品 (服务类商品必须选择)</option>';
    for (var index in list) {
        html += "<option value='" + list[index].id + "'>" + list[index].goodsName + "</option>";
    }
    $("#option").html(html);
}
