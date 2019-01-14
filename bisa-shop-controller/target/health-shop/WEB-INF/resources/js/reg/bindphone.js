$(document).ready(function () {
    //初始化选项卡样式
    clickBindExistTab();

    show_msg();

    loadAreaList();

    $("#bind-exist-formphone").bootstrapValidator({
        fields: {
            userphone: {
                container: '.exist-phone-msg',
                validators: {
                    notEmpty: {
                        message: name653	//'手机号码不能为空值.'
                    },
                    stringLength: {
                        min: 6,
                        max: 15,
                        message: name654	//'手机号码不能小于6或者大于15.'
                    },
                    regexp: {
                        regexp: /^[0-9-]+$/,
                        message: name655	//'手机号码只能包含数字和连接符.'
                    },
                }
            },
            icode: {
                container: '.exist-code-msg',
                validators: {
                    notEmpty: {
                        message: name656	//'动态密码不能为空值.'
                    },
                    digits: {
                        message: name657	//'只能是数字！'
                    },
                    stringLength: {
                        min: 4,
                        max: 4,
                        message: name658	//'必须是4位数字!'
                    }
                }
            }
        }
    });

    $("#bind-new-formphone").bootstrapValidator({
        fields: {
            userphone: {
                container: '.new-phone-msg',
                validators: {
                    notEmpty: {
                        message: name653	//'手机号码不能为空值.'
                    },
                    stringLength: {
                        min: 6,
                        max: 15,
                        message: name654	//'手机号码不能小于6或者大于15.'
                    },
                    regexp: {
                        regexp: /^[0-9-]+$/,
                        message: name655	//'手机号码只能包含数字和连接符.'
                    },
                }
            },
            icode: {
                container: '.new-code-msg',
                validators: {
                    notEmpty: {
                        message: name656	//'动态密码不能为空值.'
                    },
                    digits: {
                        message: name657	//'只能是数字！'
                    },
                    stringLength: {
                        min: 4,
                        max: 4,
                        message: name658	//'必须是4位数字!'
                    }
                }
            },
            userpasspwd: {
                container: '.userpasspwdmsg',
                validators: {
                    notEmpty: {
                        message: name662	//'密码不能为空值.'
                    },
                    different: {
                        field: 'userphone',
                        message: name663	//'密码和手机号不能一样'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: name664	//'密码只能包含大写、小写、数字或下划线.'
                    },
                    stringLength: {
                        min: 8,
                        max: 16,
                        message: name690	//'密码长度不能小于8位,不能大于16位.'
                    },
                }
            },
            password: {
                container: '.usersetpwdmsg',
                validators: {
                    notEmpty: {
                        message: name709	//'确认密码不能为空值.'
                    },
                    identical: {
                        field: 'usersetpwd',
                        message: name628	//'两次密码不一致.'
                    },
                }
            },
        }
    });
});

//-----------------------------选项卡-------------------

$(".bind-tabcontrolv1").click(function () {
    clickBindExistTab();
    hideMessage();
});
$(".bind-tabcontrolv2").click(function () {
    clickBindNewTab();
    hideMessage();
});

//点击绑定已有账号
function clickBindExistTab() {
    $(".bind-tabcontrolv2").removeClass('title-selected');    // 先删除所有元素的selected样式
    $(".bind-tabcontrolv1").addClass('title-selected');  // 然后为被点击元素添加selected样式

    $(".bind-tabcontentv2").hide();
    $(".bind-tabcontentv1").show();
}

//点击注册新账号
function clickBindNewTab() {
    $(".bind-tabcontrolv1").removeClass('title-selected');    // 先删除所有元素的selected样式
    $(".bind-tabcontrolv2").addClass('title-selected');  // 然后为被点击元素添加selected样式

    $(".bind-tabcontentv1").hide();
    $(".bind-tabcontentv2").show();
}

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
            $(".selectpicker").html(htmlStr);
        }
    });
}

//-----------------------------------------绑定已有账号------验证码----------------------------------------------------

/*发送短信验证码*/
$(".bind-exist-send-btn").click(function () {
    var phoneNumber = $(".bind-exist-phone").val();//拿到当前输入的手机号码
    var phoneCode = $(".bind-exist-phonecode").val(); //区号
    var bindType = $(this).siblings(".bind_type").val();

    var passverify = $(".bind-exist-box").hasClass("has-success");
    if (passverify && phoneNumber != null) {
        sendExistCode(phoneNumber, phoneCode, bindType);
        $(".bind-new-phone").val(phoneNumber); //复制到注册新账号input
    }
});

function sendExistCode(phoneNumber, phoneCode, bindType) {
    var InterValObj; //timer变量，控制时间  
    var count = 60; //间隔函数，1秒执行  
    var curCount;//当前剩余秒数  
    var codeLength = 4;//验证码长度 
    var curCount = count;
    var passverify = $(".bind-exist-box").hasClass("has-success");

    if (passverify) {
        $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
            type: "GET", //用POST方式传输  
            dataType: "text", //数据格式:json
            url: http_request + "/web/call/bindPhoneCode",
            data: {
                phone: phoneNumber,
                selectpicker: phoneCode,
                type: bindType
            },
            success: function (msg) {
                if (msg == "200") {
                    //设置button效果，开始计时
                    $(".bind-exist-send-btn").attr("disabled", "true");//禁用重新发送按钮
                    $(".bind-exist-send-btn").css("color", "#666");//修改按钮值颜色
                    $(".bind-exist-send-btn").text(curCount + "s" + name666);//按钮值修改为 '倒计时' + '重新发送'
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                } else {
                    console.log(msg);
                    msg = getMessage(msg);
                    showMessage(msg);
                }
            },
            error: function () {
                showMessage(name730);	//"页面过期！请重新操作！"
            }
        });
    } else {
        $(".exist-phone-msg").text(name667);	//请输入正确的手机号码！
        $(".bind-exist-phone").focus();//获取手机号输入框input焦点
        return false;
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) { //当倒计时等于0时            
            window.clearInterval(InterValObj);//停止计时器  
            $(".bind-exist-send-btn").removeAttr("disabled");//启用重新发送按钮
            $(".bind-exist-send-btn").css("background-color", "#D7DCDE");
            $(".bind-exist-send-btn").css("border-color", "#D7DCDE");
            $(".bind-exist-send-btn").css("color", "#868b8a");//修改按钮值颜色
            $(".bind-exist-send-btn").text(name666);  //按钮值修改为重新发送
        }
        else {
            curCount--;  //当倒计时不等于0时 
            $(".bind-exist-send-btn").text(curCount + "s" + name666);//倒计时执行计数 
        }
    };
};


//-----------------------------------------注册新账号------验证码----------------------------------------------------

/*发送短信验证码*/
$(".bind-new-send-btn").click(function () {
    var phoneNumber = $(".bind-new-phone").val();//拿到当前输入的手机号码
    var phoneCode = $(".bind-new-phonecode").val(); //区号
    var bindType = $(this).siblings(".bind_type").val();
    var passverify = $(".bind-new-box").hasClass("has-success");
    if (passverify && phoneNumber != null) {
        sendNewCode(phoneNumber, phoneCode, bindType);
        $(".bind-exist-phone").val(phoneNumber);
    }

});

function sendNewCode(phoneNumber, phoneCode, bindType) {
    var InterValObj; //timer变量，控制时间  
    var count = 60; //间隔函数，1秒执行  
    var curCount;//当前剩余秒数  
    var codeLength = 4;//验证码长度 
    var curCount = count;
    var passverify = $(".bind-new-box").hasClass("has-success");

    if (passverify) {
        $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
            type: "GET", //用POST方式传输  
            dataType: "text", //数据格式:json
            url: http_request + "/web/call/bindPhoneCode",
            data: {
                phone: phoneNumber,
                selectpicker: phoneCode,
                type: bindType
            },
            success: function (msg) {
                if (msg == "200") {
                    //设置button效果，开始计时
                    $(".bind-new-send-btn").attr("disabled", "true");//禁用重新发送按钮
                    $(".bind-new-send-btn").css("color", "#666");//修改按钮值颜色
                    $(".bind-new-send-btn").text(curCount + "s" + name666);//按钮值修改为 '倒计时' + '重新发送'
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                } else {
                    msg = getMessage(msg);
                    showMessage(msg);
                }
            },
            error: function () {
                showMessage(name730);	//"页面过期！请重新操作！"
            }
        });
    } else {
        $(".new-phone-msg").find("small:visible").text(name667);		//请输入正确的手机号码！
        $(".bind-new-phone").focus();//获取手机号输入框input焦点
        return false;
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) { //当倒计时等于0时
            window.clearInterval(InterValObj);//停止计时器
            $(".bind-new-send-btn").removeAttr("disabled");//启用重新发送按钮
            $(".bind-new-send-btn").css("background-color", "#D7DCDE");
            $(".bind-new-send-btn").css("border-color", "#D7DCDE");
            $(".bind-new-send-btn").css("color", "#868b8a");//修改按钮值颜色
            $(".bind-new-send-btn").text(name666);  //按钮值修改为重新发送
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        }
        else {
            curCount--;  //当倒计时不等于0时
            $(".bind-new-send-btn").text(curCount + "s" + name666);//倒计时执行计数
        }
    };
};


/**
 * 获取url参数
 * @param url 目标url
 * @param arg 目标参数名
 * @returns
 */
function getURLArg(params, arg) {
    var reg = new RegExp("(^|&)" + arg + "=([^&]*)(&|$)");
    var r = params.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}


//---------------------异常信息及处理----------------------------
function show_msg() {
    var param = window.location.search;
    var msg = getURLArg(param, "msg");
    if (msg != null && msg != '' && msg != 'null' && msg != 'unfine' && msg != '') {
        msg = getMessage(msg);
        showMessage(msg);
    } else {
        showMessage(name721);	//"本登录账号需要绑定手机号"
    }
}

//隐藏异常信息框
function hideMessage() {
    msg = "";
    $(".msg-error").html(msg);
    $(".msg-error").css("display", "none");
}

/*异常信息*/
function showMessage(msg) {
    msg = "<b></b>" + msg;
    $(".msg-error").html(msg);
    $(".msg-error").css("display", "block");
}

function getMessage(msg) {
    if (msg == "200") {
        return name732;	//"绑定手机号成功！请重新登录!";
        setTimeout(function () {
            windows.location.href = http_request + "/logout";
        }, 2000);
    }
    if (msg == "1001") {
        return name653;
    }	//"手机号不能为空！"
    if (msg == "1002") {
        clickBindNewTab();
        var curPhone = $(".bind-new-phone").val();
        $(".bind-exist-phone").val(curPhone);
        return name669; 	//"手机号未注册，请注册新账号！";
    }
    if (msg == "1003") {
        clickBindExistTab();
        return name733;		//"手机号已存在，请直接绑定已有账号！";
    }
    if (msg == "1004") {
        return name670;
    }		//"操作失败！"
    if (msg == "1005") {
        return name671;
    }		//"验证码错误！"
    if (msg == "1006") {
        setTimeout(function () {
            window.location.href = "login";
        }, 2000);
        //定时跳转页面
        return name734;	//"页面已过期，三秒后回到登录页面。";
    }
    if (msg == "1007") {
        return name672;
    }	//"地区有误！"
}


//--------------------------绑定手机号弹出窗-------------------------
//弹出绑定手机号弹出窗
function showBindPhoneDiv() {
    $(".bind-phone-shadev").removeClass("dis-n");//遮罩
    //除非提交验证，否则不允许任何操作，不允许关闭弹出层。
    $(".show-bind-phone-div").removeClass("dis-n");  //显示悬浮窗口
}

//隐藏绑定手机号弹出窗，在bindPhone.js调用此方法
function hideBindPhoneDiv() {
    $(".bind-phone-shadev").addClass("dis-n");//遮罩
    //除非提交验证，否则不允许任何操作，不允许关闭弹出层。
    $(".show-bind-phone-div").addClass("dis-n");  //隐藏悬浮窗口
}
