$(document).ready(function () {

    /*layui方面js*/
    layui.use(['form', 'upload'], function () {
        var form = layui.form,
            upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            , url: 'admin/news/newsPictureUpload'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (data) {
                //如果上传失败
                if (data.flag == false) {
                    return layer.msg('上传失败');
                }
                //上传成功
                if (data.flag == true) {
                    var demoText = $('#demoText');
                    demoText.html('图片地址请妥善保管：<span style="color: red;font-size:20px; font-weight:bold;">' + data.msg + '</span>');

                    layer.msg('图片上传成功!', {icon: 6, time: 1000}, function () {
                    });
                }
            }
            , error: function () {
                layer.msg('图片上传失败！', {icon: 5, time: 1000}, function () {
                });
            }
        });

    });
});


