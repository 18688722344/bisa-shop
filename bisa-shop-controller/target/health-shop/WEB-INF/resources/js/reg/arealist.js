//-------------------------------加载地区、区号相关数据--------------------
function loadAreaList() {
    /*获取国家地区的ajax */
    $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
        type: "GET", //用POST方式传输
        dataType: "json", //数据格式:text
        url: http_request + "/web/call/area_list",
        success: function (msg) {
            var dataStr = eval(msg);
            var htmlStr = "";
            for (var o in dataStr) {
                var area = dataStr[o];
                htmlStr += "<option value=" + area.phoneCode + ">" + area.country + "</option>";
            }
            $("#selectpicker").html(htmlStr);
        }
    });
}