$(document).ready(function() {
	
	show_msg();
        // 在键盘按下并释放及提交后验证提交表单
        $(".servicact-validate").validate({
            rules: {
            	cardNumber: {
                    required: true,
                },
                cardCode: {
                    required: true,
                    minlength: 8,
                },
                account: {
                    required: true,
                },
                setAccount: {
                    required: true,
                    equalTo: ".activate-cid",
                },
            },
            messages: {
            	cardNumber: {
                    required: name528
                },
                cardCode: {
                    required: name529,
                    minlength: name530
                },
                account: {
                    required: name531
                },
                setAccount: {
                    required: name532,
                    equalTo: name533
                }
            }
        });
    });

/*激活部分选项框的样式*/
$('.activatecheckv1').iCheck({
    checkboxClass: 'icheckbox_flat-blue',
    radioClass: 'iradio_flat-blue'
});
$('.activatecheckv1').on('ifChecked', function(event){
    $(".activatecheckv1").val(1);
    var currentUser = $(".current-account").val();
    $(".activate-cid").val(currentUser);
    $(".activate-cidagain").val(currentUser);

});
$('.activatecheckv1').on('ifUnchecked', function(event){
    $(".activatecheckv1").val(0);
    $(".activate-cid").val("");
    $(".activate-cidagain").val("");
});


function show_msg(){
	if(message != null && message != '' && message != 'null' && message != 'unfine'){
		message = getMessage(message);
		showMessage(message);
	}
}

/*根据消息代码显示异常信息*/
function getMessage(msg){
	if(msg == "200"){return name534;}
	if(msg == "1001"){ return name535; }
	if(msg == "1002"){ return name536; }
	if(msg == "1004"){ return name537;}
	if(msg == "1005"){ return name538;}
	if(msg == "1008"){ return name539; }
}

/*异常信息*/
function showMessage(msg){
	msg = "<b></b>" + msg;
	$(".msg-error").html(msg);
	$(".msg-error").css("display", "block"); 
}
