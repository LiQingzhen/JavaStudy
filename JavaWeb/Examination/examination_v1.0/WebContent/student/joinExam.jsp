<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/js/MyLayout/frame/layui/css/layui.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/MyLayout/frame/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.4.1.min.js"></script>
<title>模拟考试</title>
<script type="text/javascript">

			layui.use('table', function(){
				var table = layui.table;
				
				table.render({
					elem: '#paper',
					method: 'post',
					height: 420,
					url:'<%=request.getContextPath()%>/getPapersInfo.do',
					cellMinWidth: 80,
					where:{flag:1},
					//toolbar: '#toolbarDemo',
					cols: [[
						{field:'id', title:'试卷编号', width:200, align:'centrt'},
						{field:'grade', title:'难度等级', width:200, align:'centrt'},
						{field:'mdate', title:'制卷日期', width:200, align:'centrt'},
						{fixed: 'right', title:'操作', width: 500, align:'center', toolbar: '#barDemo'}
					]],
					page: true,	// 开启分页
					limit: 10,
					limits:[10,20,30,50],
				});
				
				//监听行工具事件
				  table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
				    var data = obj.data, //获得当前行数据
				    layEvent = obj.event; //获得 lay-event 对应的值
				    if(layEvent === 'join'){			      
				      layer.confirm('确定进入模拟考试吗？', function(index){
				    	  window.location.href = "${pageContext.request.contextPath}/practisePaper.do?id="+data.id+"&examId=no";
					      });
				    }else if(layEvent === 'export'){
				    	layer.confirm('确定导出该试卷吗？', function(index){
					    	  $.ajax({
						  			type: 'POST',
						  			url: '${pageContext.request.contextPath}/exportPaper.do',
						  			data:{
						  				id : data.id
						  			},
						  			cache:false,
						  			success: function(msg){
						  				if(msg=='success'){
						  					layer.msg('试卷成功导出至E盘根目录！',{icon:1,time:1000});
						  				}else{
						  					layer.msg('导出失败!',{icon:5,time:1000});
						  				}
						  			},
						  			error:function(msg) {
						  				layer.msg('导出失败!',{icon:5,time:1000});
						  			}
						  		});	
				  		});
				    };
				})
			})
	</script>
</head>
<body>

<table id="paper" class="layui-hide" lay-filter="test"></table>	
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="join">进入考试</a>
  <a class="layui-btn layui-btn-xs" lay-event="export">导出试卷</a>
</script>
	
</body>

</html>