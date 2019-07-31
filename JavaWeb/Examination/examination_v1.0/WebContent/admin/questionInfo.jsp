<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/js/MyLayout/frame/layui/css/layui.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/MyLayout/frame/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.4.1.min.js"></script>
<title>题目详情</title>
<script type="text/javascript">

			layui.use('table', function(){
				var table = layui.table;
				
				table.render({
					elem: '#questions',
					method: 'post',
					height: 450,
					url:'<%=request.getContextPath()%>/getQuestions.do',
					cellMinWidth: 80,
					//toolbar: '#toolbarDemo',
					cols: [[
						{field:'id', title:'试卷编号', width:200, align:'centrt'},
						{field:'details', title:'题目内容', width:500, align:'centrt'},
						{field:'answer', title:'答案', width:200, align:'centrt'},
						{field:'type', title:'类型', width:200, align:'centrt'},
					]],
					page: true,	// 分页
					limit: 10,
					limits:[10,20,30,50]
					//id: 'testReload'
				});
			})
		
	</script>
</head>
<body>

		<table id="questions" class="layui-hide"></table>	
	
</body>

</html>