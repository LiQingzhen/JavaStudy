<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
  <head>  
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
    <title>成绩管理</title>  
  </head> 
  <body> 
	<div style="color:blue;font-size:30px">  
		<form action="${pageContext.request.contextPath}/report.do" method="post">
			学号：<input type="text" name="sid">
			考试编号：<input type="text" name="eid">
			成绩单保存路径：<input type="text" name="path" value="E:/">
			<input type="button" value="打印成绩单">
		</form>
	</div> 
  </body>  
</html>
