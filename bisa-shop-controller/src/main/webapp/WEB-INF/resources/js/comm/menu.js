$(document).ready(function () {
    //判断请求的是哪个链接，然后触发点击事件。
    $(".user-munu").click(function () {
        $(this).parent().find(".col-active").removeClass("col-active");
        $(this).addClass("col-active");
    });
});