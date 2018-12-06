<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.bbs.DataBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	// 确认是否为管理员状态，默认不是管理员
	boolean logined = false;
	String adminlogin = (String) session.getAttribute("adminlogin");
	String url = request.getParameter("from");
	if(adminlogin != null && "true".equals(adminlogin))	logined = true;
	
	// 是管理员，可以执行删除操作
	if (logined) {
		Connection connection = DataBase.getConnection();
		Statement statement = DataBase.getStatement(connection);
		int id = Integer.parseInt(request.getParameter("id"));
		String sql = "delete from article where rootid = " + id;
		statement.execute(sql);
		DataBase.close(statement);
		DataBase.close(connection);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除帖子</title>
</head>
<body>


<%
		response.sendRedirect(url);
	}else{		
		response.sendRedirect("login.jsp");
	}
%>

</body>
</html>