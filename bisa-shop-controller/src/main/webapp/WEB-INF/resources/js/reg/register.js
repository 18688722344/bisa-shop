$(document).ready(function () {

    loadAreaList();  //加载地区、区号相关数据，function位于areaList.js

    //弹出提示信息
    show_msg();

    function show_msg() {
        var msg = message;
        if (msg != null && msg != '' && msg != 'null' && msg != 'unfine' && msg != '') {
            msg = getMessage(msg);
            showMessage(msg);
        }
    }

    /*注册部分的js*/
    /*手机注册校验*/
    $(".register-main-formphone").bootstrapValidator({
        fields: {
            phone: {
                container: '.userphonemsg',
                validators: {
                    notEmpty: {
                        message: name653 	//'手机号码不能为空值.'
                    },
                    stringLength: {
                        min: 6,
                        max: 15,
                        message: name654 	//'手机号码不能小于6或者大于15.'
                    },
                    regexp: {
                        regexp: /^[0-9-]+$/,
                        message: name655 	//'手机号码只能包含数字和连接符.'
                    },
                }
            },
            icode: {
                container: '.usercodemsg',
                validators: {
                    notEmpty: {
                        message: name656 	//'动态密码不能为空值.'
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
                container: '.user-pwd-msg',
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
            setpassword: {
                container: '.user-setpwd-msg',
                validators: {
                    notEmpty: {
                        message: name662	//'密码不能为空值.'
                    },
                    different: {
                        field: 'userphone',
                        message: name663	//'密码和手机号不能一样.'
                    },
                    identical: {
                        field: 'password',
                        message: name691	//'两次密码不一致.'
                    },
                }
            },
        }
    });
    /*发送短信验证码*/
    $(".sendMessagebtn").click(function () {//点击微信登录
        sendMessage()
    });

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
        console.log(select);
        if (passverify) {  //验证手机号
            //产生验证码 ,向用户手机发送验证码由后台实现,前台实现了倒计时
            //向后台发送处理数据
            $.ajax({  //当点击发送验证码时,可能向后台执行的ajax事件
                type: "GET", //用POST方式传输
                dataType: "text", //数据格式:json
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url: http_request + "/web/call/phoneRegisterCode?phone=" + phoneNumber + "&selectpicker=" + select,
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
            $(".userphonemsg").find("small:visible").text(name667);		//请输入正确的手机号码！
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
                $(".sendMessagebtn").text(name666);  //按钮值修改为重新发送
                code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效      
            }
            else {
                curCount--;  //当倒计时不等于0时 
                $(".sendMessagebtn").text(curCount + "s" + name666);//倒计时执行计数 
            }
        };
    };
});

/*微信登录 鼠标悬浮事件*/
$(".register-tabcontrolv3").mouseenter(function () {
    $(this).find("img").attr("src", http_request + "/resources/img/reg/tabcontrolv3h.png");
    $(this).find("span").css("color", "black");
});
$(".register-tabcontrolv3").mouseleave(function () {
    $(this).find("img").attr("src", http_request + "/resources/img/reg/tabcontrolv3.png");
    $(this).find("span").css("color", "#999");
});

/*异常信息*/
function showMessage(msg) {
    msg = "<b></b><span>" + msg + "</span>";
    $(".msg-error").html(msg);
    $(".msg-error").css("display", "block");
}

/*根据消息代码显示异常信息*/
function getMessage(msg) {
    if (msg == "1001") {
        return name653;
    }	//"手机号不能为空！"
    if (msg == "200" || msg == "1003") {
        return name692;
    }	//"账号已注册，请登录！"
    if (msg == "1004") {
        return name537;
    }		//"操作失败！"
    if (msg == "1005") {
        return name671;
    }		//验证码错误！
    if (msg == "1007") {
        return name672;
    }		//地区有误！
}