<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/js/MyLayout/frame/layui/css/layui.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/MyLayout/frame/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.4.1.min.js"></script>
<title>考试信息</title>
<script type="text/javascript">

			layui.use('table', function(){
				var table = layui.table;
				
				table.render({
					elem: '#exam',
					method: 'post',
					height: 450,
					url:'<%=request.getContextPath()%>/getExamsInfo.do',
					cellMinWidth: 80,
					//toolbar: '#toolbarDemo',
					cols: [[
						{field:'id', title:'考试编号', width:200, align:'centrt'},
						{field:'name', title:'考试名称', width:200, align:'centrt'},
						{field:'start', title:'开考时间', width:200, align:'centrt'},
						{field:'time', title:'考试时长', width:200, align:'centrt'},
						{field:'score', title:'考试总分', width:200, align:'centrt'},
						{fixed: 'right', title:'操作', width: 200, align:'center', toolbar: '#barDemo'}
					]],
					page: true,	
					limit: 10,
					limits:[10,20,30,50]
				});
				table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				    var data = obj.data, //获得当前行数据
				    layEvent = obj.event; //获得 lay-event 对应的值
				    if(layEvent === 'join'){			      
				      layer.confirm('确定进入考试吗？', function(index){
				    	  $.ajax({
					  			type: 'POST',
					  			url: '${pageContext.request.contextPath}/joinPaper.do',
					  			//dataType: 'json',
					  			data:{
					  				start : data.start,
					  				time : data.time
					  			},
					  			cache:false,
					  			success: function(msg){
					  				if(msg=='early'){
					  					layer.msg('考试还未开始！',{icon:1,time:1000});
					  				}else if(msg=='late'){
					  					layer.msg('考试已经结束！',{icon:1,time:1000});
					  				}else if(msg=='ok'){
					  					window.location.href = "${pageContext.request.contextPath}/practisePaper.do?id="+data.paper+"&examId="+data.id;
					  				}
					  			},
					  			error:function(msg) {
					  				layer.msg('程序异常!',{icon:5,time:1000});
					  			}
					  		});	
					      });
				    }
				})
			})
		
	</script>
</head>
<body>
<table id="exam" class="layui-hide" lay-filter="test"></table>	
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="join">进入考试</a>
</script>	
</body>

</html>