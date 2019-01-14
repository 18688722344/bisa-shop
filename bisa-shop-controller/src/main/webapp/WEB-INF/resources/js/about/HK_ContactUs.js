$(document).ready(function() {
	
	//判断请求的是哪个链接，然后触发点击事件。
    switch(request_type){
    case "contactUs":
    	 $(".CU-tips").hide();
         $(".CU-tipsv1").show();
         $(".CU-list").removeClass("col-active");
	     $(".CU-listv1").addClass("col-active");
    	break;
    case "disclaimer":
    	 $(".CU-tips").hide();
         $(".CU-tipsv2").show();
         $(".CU-list").removeClass("col-active");
	     $(".CU-listv2").addClass("col-active");
		break;
    case "privacyPrinciple":
    	  $(".CU-tips").hide();
          $(".CU-tipsv3").show();
          $(".CU-list").removeClass("col-active");
 	     $(".CU-listv3").addClass("col-active");
    	break;
    /*default:
        $(this).parent().find(".col-active").removeClass("col-active");
    	$(this).addClass("col-active");
    	break;*/
    }
	
    var path=$("base").attr("href");
    /*留言部分的校验*/
    $.validator.setDefaults({
        submitHandler: function() {
        	var cname = $(".cname").val();
        	var cphone = $(".cphone").val();
        	var cemail = $(".cemail").val();
        	var message = $("textarea").val();
           /* ajax提交留言到后台*/
           $.ajax({
        	   type:"POST",
        	   dataType:"text",
        	   url:http_request+'/contactUs',
        	   data:{name:cname,phone:cphone,mail:cemail,text:message},
        	   error:function (XMLHttpRequest, textStatus, errorThrown) { },
        	   success:function(data){
        		   if(data=="true"){
        			   layer.alert( name407, {
			                icon: 0,
			                title: name408,
			            });
        			   /*将输入框的内容清空*/
        			   $(".cname").val("");
        			   $(".cphone").val("");
        			   $(".cemail").val("");
        			   $("textarea").val("");
        		   }else{
        			   layer.alert(name409, {
			                icon: 2,
			                title: name410,
			            });
        		   }
        	   }
           })
        }
    });
    $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
        $(".contactus-form").validate({
            rules: {
                cname: {
                    required: true
                },
                cphone: {
                    required: true,
                    minlength:8
                },
                cabout: {
                    required: true
                },
                cemail:{
                	required: true
                },
            },
            messages: {
                cname: {
                    required: name411
                },
                cphone: {
                    required: name412,
                    minlength:name413
                },
                cabout: {
                    required: name414
                },
                cemail:{
                	required: name415
                },
            }
        });
    });
    /*--------------------我是一条华丽分割线----------------------------------*/
    // 百度地图API功能
    var sContent="";
        sContent += "<h4 style=\"margin:0 0 5px 0;padding:0.2em 0\">";
        sContent += name27;
        sContent += "<\/h4>";
        sContent += " <img style=\"float:right;margin:4px\" id=\"imgDemo\" src=\"" +path+ "resources\/img\/HK_About\/about_logo.png\" width=\"139\" height=\"104\" title=name27\/>";
        sContent += " <p style=\"margin:0;line-height:1.5;font-size:13px;\">";
        sContent += name305 + "&nbsp;&nbsp;&nbsp;&nbsp;" + name306 + "(852) 2423 0600";
        sContent += " <\/p>";
        sContent += "<\/div>";
    var map = new BMap.Map("bisa-map");    //初始化
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.addControl(new BMap.NavigationControl()); //添加工具类
    map.addControl(new BMap.ScaleControl()); //添加比例尺
    var point = new BMap.Point(114.141702,22.373798); //添加地图上的点
    map.centerAndZoom(point, 19); //缩放比例
    /*--------------------我是一条华丽分割线----------------------------------*/
    var myIcon = new BMap.Icon(path+"resources\/img\/HK_About\/in_this.gif", new BMap.Size(40,40));
    var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注,定义这个点
    map.addOverlay(marker);              // 将标注添加到地图中
    /*--------------------我是一条华丽分割线----------------------------------*/
    var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
    map.addOverlay(marker); //加载弹出层内容
    marker.addEventListener("click", function(){          
       this.openInfoWindow(infoWindow);
       //图片加载完毕重绘infowindow
       document.getElementById('imgDemo').onload = function (){
           infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
       }
    });
    /*选项卡js*/
    $(".CU-list").click(function() {
        $(this).parent().find(".col-active").removeClass("col-active");
        $(this).addClass("col-active");
    }); 
    $(".CU-listv1").click(function() {
        $(".CU-tips").hide();
        $(".CU-tipsv1").show();
    }); 
    $(".CU-listv2").click(function() {
        $(".CU-tips").hide();
        $(".CU-tipsv2").show();
    });
    $(".CU-listv3").click(function() {
        $(".CU-tips").hide();
        $(".CU-tipsv3").show();
    });
});

