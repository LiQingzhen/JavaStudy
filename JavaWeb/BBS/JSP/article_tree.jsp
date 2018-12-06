<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.*, com.bbs.*, java.util.*, java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!	/* 将帖子以树状结构形式存储在articles集合中 */
	private void tree(Connection connection, List<Article> articles, int id, int grade) {
		Statement statement = DataBase.getStatement(connection);
		String sql = "select * from article where pid = " + id;
		ResultSet resultSet = null;
		try {
			resultSet = DataBase.executeSQL(statement, sql);
			while (resultSet.next()) {
				// 将数据库中读出的数据映射到articles上
				Article article = new Article();
				article.setId(resultSet.getInt("id"));
				article.setParentId(resultSet.getInt("pid"));
				article.setRootId(resultSet.getInt("rootid"));
				article.setTitle(resultSet.getString("title"));
				article.setPdate(resultSet.getTimestamp("pdate"));
				article.setLeaf(resultSet.getInt("isleaf") == 0 ? true : false);
				article.setChildNum(resultSet.getInt("childnum"));
				article.setGrade(grade);
				articles.add(article);

				// 递归调用
				if (!article.isLeaf()) {
					tree(connection, articles, article.getId(), article.getGrade() + 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(resultSet);
			DataBase.close(statement);
		}
	}%>

<%
	List<Article> articles = new ArrayList<Article>();
	Connection connection = DataBase.getConnection();
	tree(connection, articles, 0, 0);
	
	DataBase.close(connection);
%>

<html>
<head>
<title>技术论坛</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="images/style.css"
	title="Integrated Styles">
<script language="JavaScript" type="text/javascript" src="images/global.js"></script>
<link rel="alternate" type="application/rss+xml" title="RSS" href="http://bbs.chinajavaworld.com/rss/rssmessages.jspa?forumID=20">
<script language="JavaScript" type="text/javascript" src="images/common.js"></script>
</head>
<body>
	<br>
	<div id="jive-forumpage">
		<div class="jive-buttons">
			<table summary="Buttons" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="jive-icon">
							<a href="release.jsp">
								<img src="images/post-16x16.gif" alt="发表新主题" border="0" height="16" width="16">
							</a>
						</td>
						<td class="jive-icon-label">
							<a id="jive-post-thread" href="release.jsp">发布新主题</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%">
						<div class="jive-thread-list"><div class="jive-table">
								<table summary="List of threads" cellpadding="0" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th class="jive-first" colspan="3">主题</th>
											<th class="jive-author"><nobr> 作者 &nbsp; </nobr></th>
											<th class="jive-view-count"><nobr> 浏览 &nbsp; </nobr></th>
											<th class="jive-msg-count" nowrap="nowrap">回复</th>
											<th class="jive-last" nowrap="nowrap">最新帖子</th>
										</tr>
									</thead>
									
									<tbody>
										<% 
											Article article = new Article();
											boolean isOdd = false;
											String classStr;
											// 格式化时间
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
											
											for (Iterator<Article> iterator = articles.iterator(); iterator.hasNext();) {
												article = iterator.next();
												// 奇偶行区别显示
												if(isOdd) {
													classStr = "jive-odd";
													isOdd = false;
												}else {
													classStr = "jive-even";
													isOdd = true;
												}
												// 根据节点等级添加前缀
												StringBuffer head = new StringBuffer("");
												for (int i = 0; i < article.getGrade(); i++) {
													head.append("  #  ");
												}
										%>
										<tr class=<%= classStr %>>
											<td class="jive-first" nowrap="nowrap" width="1%">
												<div class="jive-bullet">
													<img src="images/read-16x16.gif" alt="已读" border="0" height="16" width="16">
													<!-- div-->
												</div>
											</td>
											<td nowrap="nowrap" width="1%">
												<a href="article_tree_delete.jsp?id=<%= article.getId() %>&isleaf=<%= article.isLeaf() %>&pid=<%= article.getParentId() %>&childnum=<%= article.getChildNum() %>">DEL</a>
											</td>
											<td class="jive-thread-name" width="95%">
												<a id="jive-thread-1" href="article_content.jsp?id=<%= article.getId() %>">
													<%=head + article.getTitle()%>
												</a>
											</td>
											<td class="jive-author" nowrap="nowrap" width="1%">
												<span class="">LiQingzhen</span>
											</td>
											<td class="jive-view-count" width="1%">***</td>
											<td class="jive-msg-count" width="1%">*</td>
											<td class="jive-last" nowrap="nowrap" width="1%">
												<div class="jive-last-post">
													<%=sdf.format(article.getPdate())%>
													<br/> 
													<br/>
												</div>
											</td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<div class="jive-legend"></div>
					</td>
				</tr>
			</tbody>
		</table>
		<br> <br>
	</div>
</body>
</html>
