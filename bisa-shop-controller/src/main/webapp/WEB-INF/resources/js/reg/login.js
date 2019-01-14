$(document).ready(function () {

    show_msg();   //显示异常信息

    loadAreaList();  //加载地区、区号相关数据，function位于areaList.js

    /*发送短信验证码*/
    $(".sendMessagebtn").click(function () {//发送短信
        sendMessage();
    });

    /*免密登录表单验证*/
    $(".login-main-formphone").bootstrapValidator({
        submitHandler: function (valiadtor, loginForm, submitButton) {
            valiadtor.defaultSubmit();
        },
        fields: {
            username: {
                container: '.userphonemsg',
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
                container: '.usercodemsg',
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

    /*账号密码登录校验*/
    $(".login-main-formpwd").bootstrapValidator({
        message: name659, 	//'请输入用户名/密码',
        submitHandler: function (valiadtor, loginForm, submitButton) {
            rememberMe($("input[name='rememberMe']").is(":checked"));
            valiadtor.defaultSubmit();
        },
        fields: {
            username: {
                container: '.usernamemsg',
                validators: {
                    notEmpty: {
                        message: name660	//'用户名不能为空值.'
                    },
                    stringLength: {
                        max: 12,
                        message: name661	//'用户名不能大于12位.'
                    },
                },
            },
            userpwd: {
                container: '.userpwdmsg',
                validators: {
                    notEmpty: {
                        message: name662 	//'密码不能为空值.'
                    },
                    different: {
                        field: 'username',
                        message: name663	//'密码和用户名不能一样'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: name664	//'密码只能包含大写、小写、数字和下划线'
                    },
                    stringLength: {
                        min: 8,
                        message: name665	//'密码长度不能小于8位.'
                    }
                }
            },
        },
    });

    $(".show-weixinlogin").on("click", function (event) {
        event.stopPropagation();
        var target = event.target;
        if (!$(target).closest(".weixinlogin-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
            $(".weixinlogin-content").removeClass("ani-weixin-logoin");
            $(".weixinlogin-content").addClass("ani-weixin-logoout");
            $(".show-weixinlogin").fadeOut();
        }
        ;
    });

    /*微信登录 鼠标悬浮事件*/
    $(".login-tabcontrolv3").mouseenter(function () {
        $(this).find("img").attr("src", http_request + "/resources/img/reg/tabcontrolv3h.png");
        $(this).find("span").css("color", "black");
    });
    $(".login-tabcontrolv3").mouseleave(function () {
        $(this).find("img").attr("src", http_request + "/resources/img/reg/tabcontrolv3.png");
        $(this).find("span").css("color", "#999");
    });

    /*下次自动登录*/
    $('#rememberMe').click(function () {
        if ($(this).prop("checked")) {
            $(this).val(1);
        } else {
            $(this).val(0);
        }
    });
});

//----------------------------------发送验证码---------------------------
function sendMessage() {
    var InterValObj; //timer变量，控制时间  
    var count = 90; //间隔函数，1秒执行  
    var curCount;//当前剩余秒数  
    var code = ""; //验证码  
    var codeLength = 4;//验证码长度 
    var curCount = count;
    var phoneNumber = $(".userphoneinput").val();//拿到当前输入的手机号码
    var passverify = $(".phonecode-box").hasClass("has-success");
    var select = $("#selectpicker").val();

    if (passverify) {  //验证手机号
        //产生验证码 ,向用户手机发送验证码由后台实现,前台实现了倒计时
        //向后台发送处理数据
        $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
            type: "GET", //用POST方式传输  
            dataType: "text", //数据格式:text
            url: http_request + "/web/call/icode?phone=" + phoneNumber + "&selectpicker=" + select,
            success: function (msg) {
                if (msg == "200") {
                    //设置button效果，开始计时
                    $(".sendMessagebtn").attr("disabled", "true");//禁用重新发送按钮
                    $(".sendMessagebtn").css("color", "#666");//修改按钮值颜色
                    $(".sendMessagebtn").text(curCount + "s" + name666);//按钮值修改为 '倒计时' + '重新发送'
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                } else {
                    msg = getMessage(msg);
                    showMessage(msg);
                }
            }
        });
    } else {
        $(".userphonemsg").find("small:visible").text(name667);		//"请输入正确的手机号码！"
        $(".userphoneinput").focus();//获取手机号输入框input焦点
        return false;
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) { //当倒计时等于0时            
            window.clearInterval(InterValObj);//停止计时器  
            $(".sendMessagebtn").removeAttr("disabled");//启用重新发送按钮
            $(".sendMessagebtn").css("background-color", "#D7DCDE");
            $(".sendMessagebtn").css("border-color", "#D7DCDE");
            $(".sendMessagebtn").css("color", "#868b8a");//修改按钮值颜色
            $(".sendMessagebtn").text(name666);  //按钮值修改为	重新发送
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效      
        }
        else {
            curCount--;  //当倒计时不等于0时 
            $(".sendMessagebtn").text(curCount + "s" + name666);//倒计时执行计数 
        }
    };
}

//--------------------选项卡切换------------------------------------
function clickLoginTab(pre_type) {
    if (pre_type == "2") {
        clickCodeLogin();
    } else if (pre_type == "1") {
        clickPwdLogin();
    }
}

//点击选项卡切换
$(".login-tabcontrolv1").click(function () {
    clickPwdLogin();
    hideMessage();//清空异常消息栏
});
$(".login-tabcontrolv2").click(function () {
    clickCodeLogin();
    hideMessage();//清空异常消息栏
});

//密码登录  选项卡
function clickPwdLogin() {
    $(".login-tabcontrolv2").removeClass('title-selected');    // 先删除所有元素的selected样式
    $(".login-tabcontrolv1").addClass('title-selected');  // 然后为被点击元素添加selected样式

    $(".login-tabcontentv2").hide();
    $(".login-tabcontentv1").show();
}

//免密登录  选项卡
function clickCodeLogin() {
    $(".login-tabcontrolv1").removeClass('title-selected');    // 先删除所有元素的selected样式
    $(".login-tabcontrolv2").addClass('title-selected');  // 然后为被点击元素添加selected样式

    $(".login-tabcontentv1").hide();
    $(".login-tabcontentv2").show();
}

//------------------------异常消息显示-------------------------------------
function show_msg() {
    var pre_type = type;
    clickLoginTab(pre_type); //切换选项卡
    if (message != null && message != '' && message != 'null' && message != 'unfine') {
        message = getMessage(message);
        showMessage(message);
    } else {
        //从url中获得message参数的值
        var param = window.location.search;
        var msg = getURLArg(param, "msg");
        if (msg != null && msg != "") {
            msg = getMessage(msg);
            showMessage(msg);
        }
    }
}

/*异常信息*/
function showMessage(msg) {
    msg = "<b></b>" + msg;
    $(".msg-error").html(msg);
    $(".msg-error").css("display", "block");
}

//隐藏异常信息框
function hideMessage() {
    msg = "";
    $(".msg-error").html(msg);
    $(".msg-error").css("display", "none");
}

/*根据消息代码显示异常信息*/
function getMessage(msg) {
    if (msg == "200") {
        return name668;
    }		//请登录!
    if (msg == "1001") {
        return name653;
    }		//手机号不能为空！
    if (msg == "1002") {
        return name669;
    }		//账号未注册，请注册新账号！
    if (msg == "1004") {
        return name670;
    }		//操作失败！
    if (msg == "1005") {
        return name671;
    }		//验证码错误！
    if (msg == "1007") {
        return name672;
    }		//地区有误！
    if (msg == "1008") {
        return name673;
    }		//用户名或密码错误！
    if (msg == "1009") {
        return name674;
    }		//账号锁定！
    if (msg == "1010") {
        var pre_type = type;
        if (pre_type == 2) {
            return name675;   //用户名或验证码错误！
        } else {
            return name673;  //用户名或密码错误！
        }
    }
    if (msg == "1011") {
        return name676;
    }		//尝试登录失败超过5次，账号锁定8小时！
}

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