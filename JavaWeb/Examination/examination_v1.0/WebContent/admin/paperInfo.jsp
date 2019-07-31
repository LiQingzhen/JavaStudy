<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/js/MyLayout/frame/layui/css/layui.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/MyLayout/frame/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.4.1.min.js"></script>
<title>试卷详情</title>
<script type="text/javascript">

			layui.use('table', function(){
				var table = layui.table;
				
				table.render({
					elem: '#paper',
					method: 'post',
					height: 420,
					url:'<%=request.getContextPath()%>/getPapersInfo2.do',
					cellMinWidth: 80,
					//toolbar: '#toolbarDemo',
					cols: [[
						{field:'id', title:'试卷编号', width:200, align:'centrt'},
						{field:'grade', title:'难度等级', width:200, align:'centrt'},
						{field:'mdate', title:'制卷日期', width:200, align:'centrt'},
						
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
				    if(layEvent === 'join'){			      
				      layer.confirm('确定进入模拟考试吗？', function(index){
				    	  $.ajax({
					  			type: 'POST',
					  			url: '${pageContext.request.contextPath}/practisePaper.do',
					  			//dataType: 'json',
					  			data:{
					  				id : data.id
					  			},
					  			cache:false,
					  			success: function(msg){
					  				//alert(msg);
					  				if(msg=='success'){
					  					layer.msg('准备开始答题！',{icon:1,time:1000});
					  				}else{
					  					layer.msg('程序异常!',{icon:5,time:1000});
					  				}
					  			},
					  			error:function(msg) {
					  				//alert(msg);
					  				layer.msg('程序异常!',{icon:5,time:1000});
					  			}
					  		});	
					      });
				    }
				  });
			})
		
	</script>
</head>
<body>

<table id="paper" class="layui-hide" lay-filter="test"></table>	
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="join">导出</a>
</script>
	
</body>

</html>