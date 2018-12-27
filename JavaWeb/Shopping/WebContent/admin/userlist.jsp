<%@page import="java.util.Iterator"%>
<%@page import="personal.shopping.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
List<User> list = User.getUsers();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" border=1>
		<tr>
			<td>ID</td>
			<td>用户名</td>
			<td>电话</td>
			<td>地址</td>
			<td>注册日期</td>
		</tr>
		<%
		for(Iterator<User> iterator = list.iterator(); iterator.hasNext();){
			User user = iterator.next();
		%>
		<tr>
			<td><%= user.getId() %></td>
			<td><%= user.getUsername() %></td>
			<td><%= user.getPhone() %></td>
			<td><%= user.getAddress() %></td>
			<td><%= user.getRegisterDate() %></td>
		</tr>		
		<%
		}
		%>
	</table>
</body>
</html>