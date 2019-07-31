<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/js/MyLayout/frame/layui/css/layui.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/MyLayout/frame/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.4.1.min.js"></script>
<title>学生详情</title>
<script type="text/javascript">

			layui.use('table', function(){
				var table = layui.table;
				
				table.render({
					elem: '#stu',
					method: 'post',
					height: 420,
					url:'<%=request.getContextPath()%>/getStudentsInfo.do',
					cellMinWidth: 80,
					//toolbar: '#toolbarDemo',
					cols: [[
						{field:'id', title:'学号', width:200, align:'centrt'},
						{field:'name', title:'姓名', width:200, align:'centrt'},
						{fixed: 'right', title:'操作', width: 500, align:'center', toolbar: '#barDemo'}
					]],
					page: true,	// 分页
					limit: 10,
					limits:[10,20,30,50],
					//id: 'testReload'
				});
				
				//监听行工具事件
				  table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				    var data = obj.data //获得当前行数据
				    ,layEvent = obj.event; //获得 lay-event 对应的值
				    if(layEvent === 'edit'){
				      
				      layer.confirm('确定重置密码么', function(index){
				    	  $.ajax({
					  			type: 'POST',
					  			url: '${pageContext.request.contextPath}/changePwd1.do',
					  			//dataType: 'json',
					  			data:{
					  				id : data.id
					  			},
					  			cache:false,
					  			success: function(msg){
					  				//alert(msg);
					  				if(msg=='success'){
					  					layer.msg('密码重置为1234',{icon:1,time:1000});
					  				}else{
					  					layer.msg('程序异常!',{icon:5,time:1000});
					  				}
					  			},
					  			error:function(msg) {
					  				//alert(msg);
					  				layer.msg('程序异常!',{icon:5,time:1000});
					  			}
					  		});	
				    	 // layer.close(index);
					        //向服务端发送删除指令
					      });

				    } else if(layEvent === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	  $.ajax({
					  			type: 'POST',
					  			url: '${pageContext.request.contextPath}/deleteStudent.do',
					  			//dataType: 'json',
					  			data:{
					  				id : data.id
					  			},
					  			cache:false,
					  			success: function(msg){
					  				//alert(msg);
					  				if(msg=='success'){
					  					obj.del();
					  					layer.msg('删除成功!',{icon:1,time:1000});
					  				}else{
					  					layer.msg('程序异常!',{icon:5,time:1000});
					  				}
					  			},
					  			error:function(msg) {
					  				//alert(msg);
					  				layer.msg('程序异常!',{icon:5,time:1000});
					  			}
					  		});	

				        layer.close(index);
				        //向服务端发送删除指令
				      });
				    } 
				  });
			})
		
	</script>
</head>
<body>

<table id="stu" class="layui-hide" lay-filter="test"></table>	
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">密码重置</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
	
</body>

</html>