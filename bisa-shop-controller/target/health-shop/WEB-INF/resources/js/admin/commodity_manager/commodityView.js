$(document).ready(function() {
    var path = $("base").attr("href");
    layui.use('table', function(){
    	  var table = layui.table;
    	  
    	  //加载表格数据
    	  table.render({
    	    elem: '#listCommodity'
    	    ,url: path + 'auth/admin/loadCommodityDatas/'
    	    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    	    ,cols: [[
    	      {field:'id', width:100, title: 'ID', sort: true}
    	      ,{field:'title', width:400, title: '商品名称', sort: true}
    	      ,{field:'selling_price', width:100, title: '价格'}
    	      ,{field:'type', width:100, title: '类型'}
    	      ,{field:'product_guid', title: '编码', width:100, sort: true}
    	      ,{field:'updatime', title: '修改时间', sort: true}
    	      ,{fixed: 'right', title: '相关操作', width: 180, align: 'center', toolbar: '#barDemo' }
    	    ]]
    	  });
    
		  //监听工具条
	    table.on('tool(filterForCommodity)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	      var data = obj.data; //获得当前行数据
	      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	      var tr = obj.tr; //获得当前行 tr 的DOM对象
	     
	      if(layEvent === 'detail'){ //查看
	          $(".commodity-title").html(data.title);
	          $(".commodity-description").html(data.description);
	          $(".commodity-sub-description").html(data.description);
	          $(".commodity-price").html(data.selling_price);
	          $(".commodity-type").html(data.type);
	          $(".commodity-classification").html(data.classification);
	          $(".commodity-insertime").html(data.insertime);
	          $(".commodity-updatime").html(data.updatime);
	          layer.open({
	              type: 1,
	              title: "商品\"" + data.title + "\"的详细信息",
	              area: ['800px', '800px'], //宽高
	              btn: ['我知道了'],
	              content: $('.commodity-content'),
	          });
	    	  
	      } else if(layEvent === 'del'){ //删除
	        layer.confirm('真的删除行么', function(index){
	          obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
	          layer.close(index);
	          //向服务端发送删除指令
	          
	        });
	      } else if(layEvent === 'edit'){ //编辑
	    	  $(".modify-title").val(data.title);
	          $(".modify-description").val(data.description);
	          $(".modify-sub-description").val(data.description);
	          $(".modify-price").val(data.selling_price);
	          $(".modify-type").val(data.type);
	          $(".modify-classification").val(data.classification);
	          $(".modify-insertime").val(data.insertime);
	          $(".modify-updatime").val(data.updatime);
              layer.open({
                  type: 1,
                  title: "修改商品\"" + data.title + "\"的详细信息",
                  area: ['800px', '800px'], //宽高
                  content: $('.modify-commodity'),
              });
	        
	        //同步更新缓存对应的值
            /*  
	        obj.update({
	          username: '123'
	          ,title: 'xxx'
	        });
	        */
	      }
	    });
	    
	    /*修改商品的表单提交*/
        form.on('submit(submit-update)', function(data) {
        	
            layer.closeAll();
            $.ajax({
                url: path + "auth/admin/updateCommodity",
                type: "post",
                dataType: "application/x-www-form-urlencoded",
                async: false,
                data: {
                    "value": value,
                    "field": 'address',
                    "order_no": data.order_no,
                    "username": username
                },
                success: function(result) {
                    if (result == "success") {
                      layer.alert('<font style="color: #333;">成功修改 ID 为 <font style="color: red;">' + data.id + '</font> 的收货地址为<font style="color: red;">' + value + '</font></font>',{icon: 1,time:4000,});
                    } else {
                        layer.alert('修改失败，请联系管理员', {
                            icon: 2,
                            title: '修改失败，请联系管理员',
                        });
                    }
                },
                error: function() {
                    layer.alert('修改失败，请联系管理员', {
                        icon: 2,
                        title: '修改失败，请联系管理员',
                     });
                }
            });
        });
	});

});