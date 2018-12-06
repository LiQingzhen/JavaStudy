<%@ page import="java.sql.*, com.bbs.*, java.util.*, java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!private static void delete(Connection connection, int id, int parentId, int childNum, boolean isLeaf) {
		// 存储直接子节点的集合
		List<Article> articles = new ArrayList<Article>();
		try {
			// 如果不是叶子结点，以下操作将会删除其所有已知的子孙节点
			if (!isLeaf) {
				Statement statement = DataBase.getStatement(connection);
				Statement statement3 = DataBase.getStatement(connection);
				// 找到该节点的所有子节点，存进articles集合
				ResultSet resultSet = DataBase.executeSQL(statement, "select * from article where pid = " + id);
				while (resultSet.next()) {
					Article article = new Article();
					article.setId(resultSet.getInt("id"));
					article.setParentId(resultSet.getInt("pid"));
					article.setLeaf(resultSet.getInt("isleaf") == 0 ? true : false);
					article.setChildNum(resultSet.getInt("childnum"));
					articles.add(article);
				}

				Article article = new Article();
				// 逐个删除找到的子节点
				for (Iterator<Article> iterator = articles.iterator(); iterator.hasNext();) {
					article = iterator.next();
					// 处理当前节点
					delete(connection, article.getId(), article.getParentId(), article.getChildNum(), article.isLeaf());

					// 事务处理；修改当前节点的父节点的childnum值 + 删除当前节点
					Statement statement2 = DataBase.getStatement(connection);
					boolean temp = connection.getAutoCommit();
					connection.setAutoCommit(false);
					statement2.addBatch("update article set childnum = childnum-1 where id = " + article.getParentId());
					statement2.addBatch("delete from article where id = " + article.getId());
					statement2.executeBatch();
					connection.commit();
					connection.setAutoCommit(temp);
					DataBase.close(statement2);
				}
				// 事务处理
				boolean temp = connection.getAutoCommit();
				connection.setAutoCommit(false);
				// 区分根节点与普通节点
				if (parentId != 0)
					statement3.addBatch("update article set childnum = childnum-1 where id = " + parentId);
				statement3.addBatch("delete from article where id = " + id);
				statement3.executeBatch();
				connection.commit();
				connection.setAutoCommit(temp);

				DataBase.close(resultSet);
				DataBase.close(statement3);
				DataBase.close(statement);
			} else {
				// 如果不是叶子结点，直接删除
				Statement statement1 = DataBase.getStatement(connection);
				boolean temp = connection.getAutoCommit();
				connection.setAutoCommit(false);
				// 区分根节点与普通节点
				if (parentId != 0)
					statement1.addBatch("update article set childnum = childnum-1 where id = " + parentId);
				statement1.addBatch("delete from article where id = " + id);
				statement1.executeBatch();
				connection.commit();
				connection.setAutoCommit(temp);
				DataBase.close(statement1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}%>

<%
	// 确认是否为管理员状态，默认不是管理员
	boolean logined = false;
	String adminlogin = (String) session.getAttribute("adminlogin");
	if (adminlogin != null && "true".equals(adminlogin))
		logined = true;

	if (logined) {
		Connection connection = DataBase.getConnection();

		// 从request中取数据
		boolean isLeaf = Boolean.parseBoolean(request.getParameter("isleaf"));
		int id = Integer.parseInt(request.getParameter("id"));
		int parentId = Integer.parseInt(request.getParameter("pid"));
		int childNum = Integer.parseInt(request.getParameter("childnum"));
		// 删除节点（包括其子孙节点）
		delete(connection, id, parentId, childNum, isLeaf);
		// 删除操作结束后，更新article表中的isleaf字段的值
		Statement statement = DataBase.getStatement(connection);
		statement.execute("update article set isleaf = 0 where childnum = 0");
		DataBase.close(statement);
		DataBase.close(connection);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除帖子</title>
</head>
<body>删除成功！
</body>
</html>
<%
	// 重定向
		response.sendRedirect("article_tree.jsp");
	} else {
		response.sendRedirect("login.jsp");
	}
%>