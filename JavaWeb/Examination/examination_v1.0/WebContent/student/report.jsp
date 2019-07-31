<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/js/MyLayout/frame/layui/css/layui.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/MyLayout/frame/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.4.1.min.js"></script>
<title>成绩单详情</title>
<script type="text/javascript">

			layui.use('table', function(){
				var table = layui.table;
				
				table.render({
					elem: '#report',
					//method: 'post',
					//height: 450,
					url:'<%=request.getContextPath()%>/getReports.do',
					cellMinWidth: 80,
					where:{sid:<%=request.getSession().getAttribute("id")%>},
					//toolbar: '#toolbarDemo',
					cols: [[
						{field:'eid', title:'考试编号', width:200, align:'centrt'},
						{field:'ename', title:'考试名称', width:200, align:'centrt'},
						{field:'epaper', title:'考试用卷', width:200, align:'centrt'},
						{field:'escore', title:'考试总分', width:200, align:'centrt'},
						{field:'rscore', title:'考试得分', width:200, align:'centrt'}
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

		<table id="report" class="layui-hide"></table>	
	
</body>

</html>