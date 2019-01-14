$(document).ready(function () {

    /*填写密码校验*/
    $(".formvalidate-setpwd").validate({
        rules: {
            newpwd: {
                required: true,
                minlength: 8,
                /*自定义正则表达示验证方法*/
                checkpwd: true,
            },
            repwd: {
                required: true,
                equalTo: ".user-newpwd",
            },
        },
        messages: {
            newpwd: {
                required: name616,
                minlength: name617,
            },
            repwd: {
                required: name618,
                equalTo: name619,
            },
        },
        submitHandler: function (form) {
            /*在这里验证*/
            //弹出验证手机号
            showVarifyPhoneDiv();
        }
    });

    //自定义正则表达示验证方法  
    $.validator.addMethod("checkpwd", function (value, element, params) {
        var checkpwd = /^\w{8,16}$/g;
        return this.optional(element) || (checkpwd.test(value));
    }, name620);

    /*提交表单时执行的弹出层函数*/
    function showVarifyPhoneDiv() {
        /*弹出修改手机弹出层*/
        var iptphonenum = $('.safety-phonenum').val();
        $('.faceing-phone').text(iptphonenum);
        $(".show-verifyphone").show();
        $(".verifyphone-content").removeClass("ani-selhead-logoout");
        $(".verifyphone-content").addClass("ani-selhead-logoin");
    }

    /*验证手机部分js*/
    /*关闭修改手机弹出层*/
    $(".show-verifyphone").on("click", function (event) {
        event.stopPropagation();
        var target = event.target;
        if (!$(target).closest(".verifyphone-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
            $(".verifyphone-content").removeClass("ani-selhead-logoin");
            $(".verifyphone-content").addClass("ani-selhead-logoout");
            $(".show-verifyphone").fadeOut();
        }
        ;
    });

    /*修改手机内部验证*/
    $(".formvalidate-edit-bound-phone").validate({
        rules: {
            edbophone_code: {
                required: true,
                minlength: 4,
            },
        },
        messages: {
            edbophone_code: {
                required: name621,
                minlength: name622,
            },
        },
        submitHandler: function (form) {
            /*在这里验证*/
            var phoneNumber = $(".faceing-phone").text();//拿到当前输入的手机号码
            var code = $("#varify_phone_code").val(); //验证码
            resetPwdSmsSubmit(phoneNumber, code);
        }
    });
    /*修改手机发送短信验证码*/
    $(".sendphonemsg").click(function () {
        sendphonemsg();
    });

    //发送手机的验证码的按钮
    function sendphonemsg() {
        var InterValObj; //timer变量，控制时间
        var count = 60; //间隔函数，1秒执行
        var curCount;//当前剩余秒数
        var code = ""; //验证码
        var codeLength = 4;//验证码长度
        var curCount = count;
        var phoneNumber = $(".faceing-phone").text();//拿到当前输入的手机号码
        var passverify = $(".phonecode-box").hasClass("has-success");
        $.ajax({
            type: "GET",
            dataType: "text",
            url: request_url + '/user/varifyPhoneCode?username=' + phoneNumber,
            success: function (msg) {
                if (msg == '200') {
                    //设置button效果，开始计时
                    $(".sendphonemsg").attr("disabled", "true");//禁用重新发送按钮
                    $(".sendphonemsg").css("color", "#666");//修改按钮值颜色
                    $(".sendphonemsg").text(curCount + name623);//按钮值修改为 '倒计时' + '重新发送'
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                } else {
                    msg = getMessage(msg);
                    showMessage(msg);
                }
            }
        });

        //timer 倒计时
        function SetRemainTime() {
            if (curCount == 0) { //当倒计时等于0时
                window.clearInterval(InterValObj);//停止计时器
                $(".sendphonemsg").removeAttr("disabled");//启用重新发送按钮
                $(".sendphonemsg").css("background-color", "#D7DCDE");
                $(".sendphonemsg").css("border-color", "#D7DCDE");
                $(".sendphonemsg").css("color", "#868b8a");//修改按钮值颜色
                $(".sendphonemsg").text(name624);  //按钮值修改为重新发送
                code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
            }
            else {
                curCount--;  //当倒计时不等于0时
                $(".sendphonemsg").text(curCount + name623);//倒计时执行计数
            }
        };
    };

    /*验证手机部分jsEND*/
    /*  设置密码成功界面关闭*/
    $(".show-editpwd-success").on("click", function (event) {
        event.stopPropagation();
        var target = event.target;
        if (!$(target).closest(".show-setpwd-success-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
            $(".show-setpwd-success-content").removeClass("ani-selhead-logoin");
            $(".show-setpwd-success-content").addClass("ani-selhead-logoout");
            $(".show-editpwd-success").fadeOut();
        }
        ;
    });
    /*修改密码成功界面关闭*/
    $(".show-editpwd-success").on("click", function (event) {
        event.stopPropagation();
        var target = event.target;
        if (!$(target).closest(".show-editpwd-success-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
            $(".show-editpwd-success-content").removeClass("ani-selhead-logoin");
            $(".show-editpwd-success-content").addClass("ani-selhead-logoout");
            $(".show-editpwd-success").fadeOut();
        }
        ;
    });
});

//----------------------修改密码表单提交----------------------------
/*验证手机号+修改密码*/
function resetPwdSmsSubmit(username, code) {
    var newpwd = $(".user-newpwd").val();
    var repwd = $(".user-repwd").val();

    $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
        type: "POST", //用POST方式传输  
        dataType: "text", //数据格式:JSON  
        url: request_url + '/user/userResetPwdSms', //目标地址  
        data: {
            "phone": username,
            "icode": code,
            "password": newpwd,
            "setpassword": repwd
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        success: function (msg) {
            if (msg == '200') {
                $(".show-verifyphone").hide();
                //设置密码成功,三秒后回到首页，请重新登录！
                layer.alert(name615, {
                    icon: 1,
                    title: name148
                });
                InterValObj = window.setInterval(function () {
                    location.href = request_url + "/logout";
                }, 2000); //启动计时器，2秒执行一次
            } else {
                msg = getMessage(msg);
                showMessage(msg);
            }
        }
    });
}

//----------------------消息提示相关----------------------------

function showMessage(msg) {
    layer.alert(msg, {
        icon: 2,
        title: name148,
    });
}

function getMessage(msg) {
    if (msg == "1001") {
        return name535;
    }
    if (msg == "1002") {
        return name625;
    }
    if (msg == "1003") {
        return name626;
    }
    if (msg == "1004") {
        return name537;
    }
    if (msg == "1005") {
        return name627;
    }
    if (msg == "1008") {
        return name628;
    }
}

//-----------------------------邮箱验证相关-----暂时没用到-------------------------------------

/*弹出修改绑定邮箱弹出层*/
function showVarifyEmailDiv() {
    var iptemail = $('.safety-emailnum').val();
    $('.faceing-email').text(iptemail);
    $(".show-verifyemail").show();
    $(".verifyemail-content").removeClass("ani-selhead-logoout");
    $(".verifyemail-content").addClass("ani-selhead-logoin");
}

/*验证邮箱部分js*/
/*关闭验证弹出层*/
$(".show-verifyemail").on("click", function (event) {
    event.stopPropagation();
    var target = event.target;
    if (!$(target).closest(".verifyemail-content").length > 0 || $(target).attr("class").indexOf("close-mod") != -1) {
        $(".verifyemail-content").removeClass("ani-selhead-logoin");
        $(".verifyemail-content").addClass("ani-selhead-logoout");
        $(".show-verifyemail").fadeOut();
    }
    ;
});
/*验证邮箱的内部验证*/
$(".formvalidate-edit-bound-email").validate({
    rules: {
        edboemail_code: {
            required: true,
            minlength: 4,
        },
    },
    messages: {
        edboemail_code: {
            required: name621,
            minlength: name622,
        },
    },
    submitHandler: function (form) {
        /*在这里验证*/
        var mail = $(".faceing-email").text();//拿到当前输入的手机号码
        var code = $("#varify_mail_code").val(); //验证码
        resetPwdMailSubmit(mail, code);
    }
});
/*发送邮箱验证码*/
$(".sendemailmsg").click(function () {
    sendemailmsg();
});

function sendemailmsg() {
    var InterValObj; //timer变量，控制时间
    var count = 90; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    var code = ""; //验证码
    var codeLength = 4;//验证码长度
    var curCount = count;
    var mail = $(".faceing-email").text();//拿到当前输入的邮箱
    var mails = $(".edboemail_code").val();

    console.log(mail + "|" + mails);
    //产生验证码 ,向用户手机发送验证码由后台实现,前台实现了倒计时
    $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
        type: "GET", //用POST方式传输
        dataType: "text", //数据格式:JSON
        url: request_url + '/user/varifyEmailCode?email=' + mail, //目标地址
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        success: function (msg) {
            console.log(msg);
            if (msg == '200') {
                //设置button效果，开始计时
                $(".sendemailmsg").attr("disabled", "true");//禁用重新发送按钮
                $(".sendemailmsg").css("color", "#666");//修改按钮值颜色
                $(".sendemailmsg").text(curCount + name623);//按钮值修改为 '倒计时' + '重新发送'
                InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
            } else {
                msg = getMessage(msg);
                showMessage(msg);
            }
        }
    });

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) { //当倒计时等于0时
            window.clearInterval(InterValObj);//停止计时器
            $(".sendemailmsg").removeAttr("disabled");//启用重新发送按钮
            $(".sendemailmsg").css("background-color", "#D7DCDE");
            $(".sendemailmsg").css("border-color", "#D7DCDE");
            $(".sendemailmsg").css("color", "#868b8a");//修改按钮值颜色
            $(".sendemailmsg").text(name624);  //按钮值修改为重新发送
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        }
        else {
            curCount--;  //当倒计时不等于0时
            $(".sendemailmsg").text(curCount + name623);//倒计时执行计数
        }
    };
};

/*验证邮箱部分jsEND*/


function aftersuccessforemail() {
    var ishasoldpwd = parseInt($(".is-has-oldpwd").val());
    $(".verifyemail-content").removeClass("ani-selhead-logoin");
    $(".verifyemail-content").addClass("ani-selhead-logoout");
    $(".show-verifyemail").fadeOut();
    /*判断弹出的是修改成功页面还是设置成功页面*/
    if (ishasoldpwd == 0) {
        $(".show-setpwd-success").show();
        $(".show-setpwd-success-content").removeClass("ani-selhead-logoout");
        $(".show-setpwd-success-content").addClass("ani-selhead-logoin");
        $(".btn-sure-sub").attr({"disabled": "disabled"});
    } else {
        $(".show-editpwd-success").show();
        $(".show-editpwd-success-content").removeClass("ani-selhead-logoout");
        $(".show-editpwd-success-content").addClass("ani-selhead-logoin");
        $(".btn-sure-sub").attr({"disabled": "disabled"});
    }
};

//邮箱验证表单提交
function resetPwdMailSubmit(username, code) {
    var newpwd = $(".user-newpwd").val();
    var repwd = $(".user-repwd").val();

    $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
        type: "POST", //用POST方式传输  
        dataType: "text", //数据格式:JSON  
        url: request_url + '/user/userResetPwdEmail', //目标地址  
        data: {
            "mail": username,
            "icode": code,
            "password": newpwd,
            "setpassword": repwd
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        success: function (msg) {
            if (msg == '200') {
                aftersuccessforemail();
                InterValObj = window.setInterval(function () {
                    location.href = request_url + "/logout";
                }, 3000); //启动计时器，2秒执行一次
            } else {
                msg = getMessage(msg);
                showMessage(msg);
            }
        }
    });
}
