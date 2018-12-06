<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="com.bbs.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");
	// 判断是否收到<form>
	if (action != null && action.equals("post")) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Connection connection = DataBase.getConnection();
		Statement statement = DataBase.getStatement(connection);
		Statement statement1 = DataBase.getStatement(connection);
		Statement statement2 = DataBase.getStatement(connection);

		try {
			/* Question:此时要求rootid 和 id（主键，自增）字段相同，如何做插入？
			 * 方法1。先 得到rootid，再插入；
			 */
			statement.execute("insert into article values(null, 0, null, '" + title + "', '" + content
					+ "', now(), 0, 0)");
			ResultSet resultSet = DataBase.executeSQL(statement1, "select max(id) as temp from article");
			resultSet.next();
			int rootId = resultSet.getInt("temp");
			statement2.execute("update article set rootid = "+ rootId +" where rootid is null");

			DataBase.close(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(statement2);
			DataBase.close(statement1);
			DataBase.close(statement);
			DataBase.close(connection);
		}
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布成功</title>
</head>
<body>
	发布成功！页面将在
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
	</script>
</body>
<%
	} else {
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布新帖</title>
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
		<form action="release.jsp" method="post">
			<input type="hidden" name="action" value="post"> <br /> 
			<label for="t">标题：</label> <input type="text" id="t" name="title">
			<br /> <br /> <br />
			<textarea id="c" name="content" rows=15 cols=80></textarea>
			<br /> <br /> 
			<input type="submit" name="submit" value="发布">
		</form>
	</div>
</body>
<%
	}
%>
</html>