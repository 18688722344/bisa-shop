$(document).ready(function () {
    loadAreaList();  //加载地区、区号相关数据，function位于areaList.js
    /*找回密码部分js*/
    /*手机找回密码部分校验*/
    $(".findpwd-phone-form").bootstrapValidator({
        fields: {
            phone: {
                container: '.phone-msg',
                validators: {
                    notEmpty: {
                        message: name653    /*'手机号码不能为空值.'*/
                    },
                    stringLength: {
                        min: 6,
                        max: 15,
                        message: name654    /*'手机号码不能小于6或者大于15.'*/
                    },
                    regexp: {
                        regexp: /^[0-9-]+$/,
                        message: name655	//'手机号码只能包含数字和连接符.'
                    },
                }
            },
            icode: {
                container: '.phone-icode-msg',
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
            password: {
                container: '.phone-pwd-msg',
                validators: {
                    notEmpty: {
                        message: name662	//'密码不能为空值.'
                    },
                    different: {
                        field: 'phone',
                        message: name663	//'密码和手机号不能一样'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: name664	//'密码只能包含大写、小写、数字或下划线.'
                    },
                    stringLength: {
                        min: 8,
                        max: 16,
                        message: name690	//'密码长度不能小于8位,不能大于12位.'
                    },
                }
            },
            setpassword: {
                container: '.phone-setpwd-msg',
                validators: {
                    notEmpty: {
                        message: name709	//'确认密码不能为空值.'
                    },
                    identical: {
                        field: 'password',
                        message: name691	//'两次密码不一致.'
                    },
                }
            },
        }
    });

    /*邮箱找回密码部分校验*/
    $(".findpwd-email-form").bootstrapValidator({
        fields: {
            email: {
                container: '.email-msg',
                validators: {
                    notEmpty: {
                        message: name710	//'邮箱不能为空值.'
                    },
                    emailAddress: {
                        message: name711	//'请输入正确的邮箱号码.'
                    },
                    stringLength: {
                        min: 4,
                        max: 320,
                        message: name712	//'邮箱长度少于320.'
                    }
                }
            },
            icode: {
                container: '.email-icode-msg',
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
            password: {
                container: '.email-pwd-msg',
                validators: {
                    notEmpty: {
                        message: name662	//'密码不能为空值.'
                    },
                    different: {
                        field: 'email',
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
            setpassword: {
                container: '.email-setpwd-msg',
                validators: {
                    notEmpty: {
                        message: name709	//'确认密码不能为空值.'
                    },
                    identical: {
                        field: 'password',
                        message: name628	//'两次密码不一致.'
                    },
                }
            },
        }
    });
});

//---------------------短信 找回  发送验证码----------------------------

/*发送短信验证码手机找回方式*/
$(".sendMessagebtn").click(function () {//点击微信登录
    sendMessage();
});

function sendMessage() {
    var InterValObj; //timer变量，控制时间  
    var count = 60; //间隔函数，1秒执行  
    var curCount;//当前剩余秒数  
    var code = ""; //验证码  
    var codeLength = 4;//验证码长度 
    var curCount = count;
    var phoneNumber = $(".findpwdphoneinput").val();//拿到当前输入的手机号码
    var passverify = $(".phonecode-box").hasClass("has-success");
    var select = $("#selectpicker").val();
    if (passverify) {  //验证手机号

        //产生验证码 ,向用户手机发送验证码由后台实现,前台实现了倒计时
        $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
            type: "GET", //用POST方式传输  
            dataType: "text", //数据格式:json
            url: http_request + "/web/call/icode?phone=" + phoneNumber + "&selectpicker=" + select,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
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
        $(".userphonemsg").find("small:visible").text(name667); 	//"请输入正确的手机号码！"
        $(".userphoneinput").focus();//获取手机号输入框input焦点
        $(".email-msg").text("");
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
            $(".sendMessagebtn").text(name666);  //按钮值修改为重新发送
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效      
        }
        else {
            curCount--;  //当倒计时不等于0时 
            $(".sendMessagebtn").text(curCount + "s" + name666);//倒计时执行计数 
        }
    };
}

//--------------------邮件 找回  发送验证码----------------------------
/*发送验证码到邮箱*/
$(".send-email-btn").click(function () {
    sendVcodeEmail();
});

function sendVcodeEmail() {
    var InterValObj; //timer变量，控制时间  
    var count = 90; //间隔函数，1秒执行  
    var curCount;//当前剩余秒数  
    var code = ""; //验证码  
    var codeLength = 4;//验证码长度 
    var curCount = count;
    var mail = $(".email-input").val();//拿到当前输入的手机号码
    var passverify = $(".emailcode-box").hasClass("has-success");

    if (passverify) {  //验证手机号
        //产生验证码 ,向用户手机发送验证码由后台实现,前台实现了倒计时
        //向后台发送处理数据
        $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
            type: "GET", //用POST方式传输  
            url: http_request + "/web/call/sendEmailCode?email=" + mail,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
            success: function (msg) {
                if (msg == "200") {
                    //设置button效果，开始计时
                    $(".send-email-btn").attr("disabled", "true");//禁用重新发送按钮
                    $(".send-email-btn").css("color", "#666");//修改按钮值颜色
                    $(".send-email-btn").text(curCount + "s" + name666);//按钮值修改为 '倒计时' + '重新发送'
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                } else {
                    msg = getMessage(msg);
                    showMessage(msg);
                }
            }
        });
    } else {
        $(".email-msg").find("small:visible").text(name711); 	//"请输入正确的邮箱账号！"
        $(".email-input").focus();//获取手机号输入框input焦点
        $(".email-msg").text("");
        return false;
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) { //当倒计时等于0时            
            window.clearInterval(InterValObj);//停止计时器  
            $(".send-email-btn").removeAttr("disabled");//启用重新发送按钮
            $(".send-email-btn").css("background-color", "#D7DCDE");
            $(".send-email-btn").css("border-color", "#D7DCDE");
            $(".send-email-btn").css("color", "#868b8a");//修改按钮值颜色
            $(".send-email-btn").text(name666);  //按钮值修改为重新发送
            code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效      
        }
        else {
            curCount--;  //当倒计时不等于0时 
            $(".send-email-btn").text(curCount + "s" + name666);//倒计时执行计数 
        }
    };
}


//---------------------短信找回  提交表单----------------------------
$(".find-pwd-sms-submit").click(function () {
    var datas = {};
    var formDatas = $('.findpwd-phone-form').serializeArray();
    $.each(formDatas, function () {
        datas[this.name] = this.value;
    });

    //表单验证
    var flag = $(".findpwd-phone-form").data("bootstrapValidator").isValid();
    if (flag) {
        $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
            type: "POST", //用POST方式传输  
            url: http_request + "/web/call/findPasswordSMS",
            data: datas,
            dataType: 'json',
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
            success: function (msg) {
                if (msg == "200") {
                    showMessage(name713); 	//"密码修改成功！请登录！"
                    setTimeout(function () {
                        window.location.href = http_request + "/login";
                    }, 1500);
                } else {
                    msg = getMessage(msg);
                    showMessage(msg);
                }
            }
        });
    } else {
        $(".findpwd-phone-form").data("bootstrapValidator").validate();
    }
});


//---------------------邮件找回  提交表单----------------------------
$(".find-pwd-email-submit").click(function () {
    var datas = {};
    var formDatas = $('.findpwd-email-form').serializeArray();
    $.each(formDatas, function () {
        datas[this.name] = this.value;
    });

    //表单验证
    var flag = $(".findpwd-email-form").data("bootstrapValidator").isValid();
    if (flag) {
        $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
            type: "POST", //用POST方式传输  
            url: http_request + "/web/call/findPasswordEmail",
            data: datas,
            dataType: 'json',
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
            success: function (msg) {
                if (msg == "200") {
                    showMessage(name713);	//"密码修改成功！请登录！"
                    setTimeout(function () {
                        window.location.href = http_request + "/login";
                    }, 1500);
                } else {
                    msg = getMessage(msg);
                    showMessage(msg);
                }
            }
        });
    } else {
        $(".findpwd-email-form").data("bootstrapValidator").validate();
    }
});

//-----------------------------选项卡-------------------

/*tab选项卡切换*/
$(".findpwd-tabcontrolv1").click(function () {
    $(".findpwd-tabcontentv2").hide();
    $(".findpwd-tabcontentv1").show();
    hideMessage();//清空异常消息栏
});
$(".findpwd-tabcontrolv2").click(function () {
    $(".findpwd-tabcontentv1").hide();
    $(".findpwd-tabcontentv2").show();
    hideMessage();//清空异常消息栏
});

/*标题点击字体样式改变*/
$(".pwd-title div").click(function (e) {
    $("*").removeClass('title-selected');    // 先删除所有元素的selected样式
    $(this).addClass('title-selected');  // 然后为被点击元素添加selected样式
});

//---------------------异常信息及处理----------------------------

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
    if (msg == "1001") {
        return name714;
    }	//参数为空或参数有误！
    if (msg == "1002") {
        return name715;
    }	//账号未注册！
    if (msg == "1004") {
        return name537;
    }		//操作失败！
    if (msg == "1005") {
        return name671;
    }		//验证码错误！
    if (msg == "1007") {
        return name672;
    }	//地区有误！
}
