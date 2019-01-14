$(document).ready(function () {

    /*定义ckeditor*/
    var editor = CKEDITOR.replace('newseditor', {
        customConfig: 'custom/ckeditor_config.js',
        height: 550,
});

    //获取地址栏的  新闻表的id
    var url = document.location.href;
    var id = url.split("=")[1];

    //加载语言版本的下拉框
    SelectBean();
    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;

        //监听提交
        form.on('submit(addNewsInternation)', function (data) {
            var ck_val = CKEDITOR.instances.newseditor.getData();
            /*把ck的值推入到lay数组里面*/
            data.field.newseditor = ck_val;
            var obj = data.field;
            var internationalization = obj.langInter;
            $.ajax({
                url:'admin/news/addNewsInternationalization',
                data: JSON.stringify({
                    new_id: id,
                    internationalization:internationalization,
                    img_url: data.field.photo_address,
                    author: data.field.author,
                    news_content: data.field.newseditor,
                    news_subhead: data.field.subheading,
                    news_title: data.field.main_title
                }),
                type: "POST",
                contentType: "application/json",
                success: function (data) {
                    if (data.flag == false) {
                        layer.msg('已有该语言版本请核对后添加！', {icon: 5, time: 1000}, function () {
                        });
                    }
                    if (data.flag == true) {
                        layer.msg('添加新闻国际化语言成功!', {icon: 6, time: 1000}, function () {

                        });
                    }
                }
            });
            return false;
        });

    });
});
function SelectBean(){
    $.ajax({
        url: 'admin/commodity/newsFindLanguage',
        type: "GET",
        data: {
        },
        success: function (data) {
            //下拉框赋值
            var html = '<option value="">请选择语言类型</option>';
            for (var index in data.list) {
                html += "<option value='" + data.list[index].key + "'>" + data.list[index].value + "</option>";
            }
            $(".langInter").html(html);
            renderForm();
        }
    });
}
//layui渲染表单
function renderForm() {
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });
}

