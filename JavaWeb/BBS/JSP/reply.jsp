<%@page import="java.sql.*"%>
<%@page import="com.bbs.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	// 当从request中的取值涉及到字符串是指定一下字符编码，以防乱码
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");
	// 区分进入该页面的方式
	if (action != null && action.equals("post")) {
		int parentId = Integer.parseInt(request.getParameter("pid"));
		int rootId = Integer.parseInt(request.getParameter("rootid"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Connection connection = DataBase.getConnection();
		Statement statement = DataBase.getStatement(connection);

		try {
			// 作为一个事务来处理
			boolean temp = connection.getAutoCommit();
			connection.setAutoCommit(false);
			statement.addBatch("insert into article values(null, " + parentId + ", " + rootId + ", '" + title
					+ "', '" + content + "', now(), 0, 0)");
			// 修改父节点isleaf和childnum属性
			statement.addBatch("update article set isleaf = 1 where id = " + parentId);
			statement.addBatch("update article set childnum = childnum+1 where id = "+ parentId);
			statement.executeBatch();
			connection.commit();
			connection.setAutoCommit(temp);
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					// 事务内部异常，回滚当前事务
					connection.rollback();
					connection.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DataBase.close(statement);
			DataBase.close(connection);
		}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回复成功</title>
</head>
<body>
	回复成功！页面将在
	<span id="time">3</span> 
	秒后跳转，如有异常请点击
	<a href="article.jsp">此处链接</a>

	<script type="text/javascript">
		var delay = document.getElementById("time").innerHTML;
		var temp = delay;
		onload = function() {
			setInterval(go, 1000);
		}
		function go() {

			temp--;
			if (temp > 0) {
				document.getElementById("time").innerHTML = temp;
			} else {
				location.href = 'article_tree.jsp';
			}
		}
		// 另一种写法：setTimeout(function(){location.href="article.jsp"} , 3000) 不会读秒
	</script>
</body>
<%
	} else {
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回复</title>
<style>
	.abc {
		text-align: center; /*让div内部文字居中*/
		border-radius: 20px;
		width: 600px;
		height: 350px;
		margin: auto;
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
	}
</style>
</head>
<body>
	<div class="abc">
		<form action="reply.jsp" method="post">
			<input type="hidden" name="action" value="post"> 
			<input type="hidden" name="pid" value=<%=Integer.parseInt(request.getParameter("id"))%>> 
			<input type="hidden" name="rootid" value=<%=Integer.parseInt(request.getParameter("rootid"))%>>
			<br /> 
			<label for="t">标题：</label> <input type="text" id="t" name="title"> 
			<br /> <br /> <br />
			<textarea id="c" name="content" rows=15 cols=80></textarea>
			<br /> <br /> 
			<input type="submit" name="submit" value="提交">
		</form>
	</div>
</body>
<%
	}
%>
</html>