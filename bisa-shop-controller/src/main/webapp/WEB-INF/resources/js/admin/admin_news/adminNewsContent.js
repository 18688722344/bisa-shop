$(document).ready(function () {
    /*layui方面js*/
    layui.use(['form', 'table', 'element'], function () {
        var form = layui.form,
            layer = layui.layer,
            element = layui.element,
            table = layui.table;
        //获取地址栏的  新闻表的id
        var url = document.location.href;
        var id = url.split("=")[1];

    /*定义ckeditor*/
    var editor = CKEDITOR.replace('newseditor', {
        customConfig: 'custom/ckeditor_config.js',
        height: 550,
    });
       //隐藏简体，繁体的提交按钮
        document.getElementById("updateCN").style.display = "none";
        document.getElementById("updateUS").style.display = "none";
//layui渲染表单
function renderForm() {
       layui.use('form', function () {
       var form = layui.form;
       form.render();
            });
        }

        $(".lang_btn button").click(function () {
            if ($(this).attr('data-num') == 1) {
                //简体
                $(this).removeClass("layui-btn-primary");
                $(".lang_btn button:eq(1)").addClass('layui-btn-primary');
                $(".lang_btn button:eq(2)").addClass('layui-btn-primary');
            } else if ($(this).attr('data-num') == 2) {
                //繁体
                $(this).removeClass("layui-btn-primary");
                $(".lang_btn button:eq(0)").addClass('layui-btn-primary');
                $(".lang_btn button:eq(2)").addClass('layui-btn-primary');
            } else {
                //英文
                $(this).removeClass("layui-btn-primary");
                $(".lang_btn button:eq(0)").addClass('layui-btn-primary');
                $(".lang_btn button:eq(1)").addClass('layui-btn-primary');
            }
        })

    //异步加载 要编辑的数据
        $.ajax({
            url: 'admin/news/selectNewsInternationalizationById',
            type: "GET",
            data: {
                id: id,
                internationalization:2
            },
            async: false,
            success: function (data) {
                    document.getElementById("readings").style.display = "";
                    var editor = CKEDITOR.instances["newseditor"]; //你的编辑器的"name"属性的值
                    if (editor) {
                        editor.destroy(true);//销毁编辑器
                    }
                    CKEDITOR.replace(newseditor); //替换编辑器，editorID为ckeditor的"id"属性的值
                    $("#newseditor").val(data.news_content);
                    $("input[name='id']").val(data.id);
                    $("input[name='main_title']").val(data.news_title);
                    $("input[name='subheading']").val(data.news_subhead);
                    $("input[name='author']").val(data.author);
                    $("input[name='readings']").val(data.read_quantity);
                    $("input[name='code']").val(data.news_id);
                    $("input[name='photo_address']").val(data.img_url);
            }
        });
/*监听繁体的回显*/
        $("#hk").click(function () {
            $.ajax({
                url: 'admin/news/selectNewsInternationalizationById',
                type: "GET",
                data: {
                    id: id,
                    internationalization:2
                },
                async: false,
                success: function (data) {
                    document.getElementById("updateHK").style.display = "";
                    document.getElementById("updateUS").style.display = "none";
                    document.getElementById("updateCN").style.display = "none";
                    document.getElementById("readings").style.display = "";
                    var editor = CKEDITOR.instances["newseditor"]; //你的编辑器的"name"属性的值
                    if (editor) {
                        editor.destroy(true);//销毁编辑器
                    }
                    CKEDITOR.replace(newseditor); //替换编辑器，editorID为ckeditor的"id"属性的值
                    $("#newseditor").val(data.news_content);
                    $("input[name='id']").val(data.id);
                    $("input[name='main_title']").val(data.news_title);
                    $("input[name='subheading']").val(data.news_subhead);
                    $("input[name='author']").val(data.author);
                    $("input[name='readings']").val(data.read_quantity);
                    $("input[name='code']").val(data.news_id);
                    $("input[name='photo_address']").val(data.img_url);
                }
            });
        });
    /*异步加载简体的回显*/
    $("#china").click(function () {
        document.getElementById("updateHK").style.display = "none";
        document.getElementById("updateUS").style.display = "none";
        document.getElementById("updateCN").style.display = "";
        $.ajax({
            url: 'admin/news/selectNewsInternationalizationById',
            type: "GET",
            data: {
                id: id,
                internationalization:1
            },
            async: false,
            success: function (data) {
                document.getElementById("readings").style.display = "none";
                if (data.news_title != null && data.news_content!=null) {
                    var editor = CKEDITOR.instances["newseditor"]; //你的编辑器的"name"属性的值
                    if (editor) {
                        editor.destroy(true);//销毁编辑器
                    }
                    CKEDITOR.replace(newseditor); //替换编辑器，editorID为ckeditor的"id"属性的值
                    $("input[name='readings']").val(data.read_quantity);
                    $("#newseditor").val(data.news_content);
                    $("input[name='main_title']").val(data.news_title);
                    $("input[name='subheading']").val(data.news_subhead);
                    $("input[name='author']").val(data.author);
                    $("input[name='photo_address']").val(data.img_url);
                }else {
                    layer.msg('没有简体语言版本，请添加后修改！', {icon: 5, time: 2000}, function () {
                    })
                }
            }
        });
    });
    /*异步加载英文的回显*/
    $("#english").click(function () {
        document.getElementById("updateHK").style.display = "none";
        document.getElementById("updateCN").style.display = "none";
        document.getElementById("updateUS").style.display = "";
        $.ajax({
            url: 'admin/news/selectNewsInternationalizationById',
            type: "GET",
            data: {
                id: id,
                internationalization:3
            },
            async: false,
            success: function (data) {
                document.getElementById("readings").style.display = "none";
                if (data.news_title != null && data.news_content!=null) {
                    var editor = CKEDITOR.instances["newseditor"]; //你的编辑器的"name"属性的值
                    if (editor) {
                        editor.destroy(true);//销毁编辑器
                    }
                    CKEDITOR.replace(newseditor); //替换编辑器，editorID为ckeditor的"id"属性的值
                    $("input[name='readings']").val(data.read_quantity);
                    $("#newseditor").val(data.news_content);
                    $("input[name='main_title']").val(data.news_title);
                    $("input[name='subheading']").val(data.news_subhead);
                    $("input[name='author']").val(data.author);
                    $("input[name='photo_address']").val(data.img_url);
                    form.render();
                }else {
                    layer.msg('没有英文语言版本，请添加后修改！', {icon: 5, time: 2000}, function () {
                    })
                }
            }
        });
    });
        /*监听繁体的提交*/
        form.on('submit(updateHK)', function (data) {
            var main_title=document.getElementById('main_title').value;
            var subheading=document.getElementById('subheading').value;
            var author=document.getElementById('author').value;
            var photo_address=document.getElementById('photo_address').value;
            var readings=document.getElementById('readings1').value;
            document.getElementById("updateHK").style.display = "";
            document.getElementById("updateCN").style.display = "none";
            document.getElementById("updateUS").style.display = "none";
            /*获取ck的数据*/
            var ck_val = CKEDITOR.instances.newseditor.getData();
            /*把ck的值推入到lay数组里面*/
            data.field.newseditor = ck_val;
            $.ajax({
                url: 'admin/news/updateNewsInternationalization',
                data: {
                    read_quantity:readings,
                    img_url:photo_address,
                    author:author,
                    news_content: data.field.newseditor,
                    news_subhead:subheading,
                    news_title:main_title,
                    id:id,
                    internationalization:2
                },
                type: "POST",
                success: function (data) {
                    if (data.flag == true) { //添加父级商品，需要添加图片；子级商品，加载图片
                        layer.msg('修改成功！', {icon: 6, time: 1000}, function () {
                        });
                    } else if (data.flag == false) {
                        layer.msg('输入框不能为空，请核对后添加!', {icon: 5, time: 2000}, function () {

                        });
                    }
                }
            });
        });

        /*监听中文的提交*/
        form.on('submit(updateCN)', function (data) {
            var readings=document.getElementById('readings1').value;
           var main_title=document.getElementById('main_title').value;
            var subheading=document.getElementById('subheading').value;
            var author=document.getElementById('author').value;
            var photo_address=document.getElementById('photo_address').value;
            document.getElementById("updateCN").style.display = "";
            document.getElementById("updateHK").style.display = "none";
            document.getElementById("updateUS").style.display = "none";
            /*获取ck的数据*/
            var ck_val = CKEDITOR.instances.newseditor.getData();
            /*把ck的值推入到lay数组里面*/
            data.field.newseditor = ck_val;
            $.ajax({
                url: 'admin/news/updateNewsInternationalization',
                data: {
                    read_quantity:readings,
                    img_url:photo_address,
                    author:author,
                    news_content: data.field.newseditor,
                    news_subhead:subheading,
                    news_title:main_title,
                    id:id,
                    internationalization:1
                },
                type: "POST",
                success: function (data) {
                    if (data.flag == true) { //添加父级商品，需要添加图片；子级商品，加载图片
                        layer.msg('修改成功！', {icon: 6, time: 1000}, function () {
                        });
                    } else if (data.flag == false) {
                        layer.msg('输入框不能为空，请核对后添加!', {icon: 5, time: 2000}, function () {

                        });
                    }
                }
            });
        });
        /**
         * 监听英文的提交
         */
        form.on('submit(updateUS)', function (data) {
            var readings=document.getElementById('readings1').value;
            var main_title=document.getElementById('main_title').value;
            var subheading=document.getElementById('subheading').value;
            var author=document.getElementById('author').value;
            var photo_address=document.getElementById('photo_address').value;
            document.getElementById("updateCN").style.display = "none";
            document.getElementById("updateHK").style.display = "none";
            document.getElementById("updateUS").style.display = "";
            /*获取ck的数据*/
            var ck_val = CKEDITOR.instances.newseditor.getData();
            /*把ck的值推入到lay数组里面*/
            data.field.newseditor = ck_val;
            $.ajax({
                url: 'admin/news/updateNewsInternationalization',
                data: {
                    read_quantity:readings,
                    img_url:photo_address,
                    author:author,
                    news_content: data.field.newseditor,
                    news_subhead:subheading,
                    news_title:main_title,
                    id:id,
                    internationalization:3
                },
                type: "POST",
                success: function (data) {
                    if (data.flag == true) { //添加父级商品，需要添加图片；子级商品，加载图片
                        layer.msg('修改成功！', {icon: 6, time: 1000}, function () {
                        });
                    } else if (data.flag == false) {
                        layer.msg('输入框不能为空，请核对后添加!', {icon: 5, time: 2000}, function () {
                        });
                    }
                }
            });
        });
    });
});


