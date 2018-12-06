<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String action = request.getParameter("action");
// 收到表单信息
if(action != null && action.trim().equals("post")){
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	// 确认用户名和密码
	if("admin".equals(username) && "admin".equals(password)){
		session.setAttribute("adminlogin", "true");
		response.sendRedirect("article.jsp");
	}else{
		out.println("用户名或密码错误");	
	}
}
%>
	<form action="login.jsp" method="post">
		<input type="hidden" name="action" value="post">
		<label for="t">用户名：</label> <input type="text" id="t" name="username"> <br><br>
		<label for="p">密码：</label> <input type="password" id="p" name="password"> <br><br>
		<input type="submit" name="submit" value="登录">
	</form>
</body>
</html>