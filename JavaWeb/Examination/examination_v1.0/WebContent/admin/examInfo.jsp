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
						{field:'paper', title:'考试用卷', width:200, align:'centrt'}
					]],
					//data:[{"eid":"2017010201","escore":100.0,"ename":"WEB工程","epaper":"1","rscore":99.0},{"eid":"2017020201","escore":100.0,"ename":"操作系统","epaper":"2","rscore":32.0}]
					page: true,	// 分页
					limit: 10,
					limits:[10,20,30,50],
					//id: 'testReload'
				});
			})
		
	</script>
</head>
<body>

		<table id="exam" class="layui-hide"></table>	
	
</body>

</html>