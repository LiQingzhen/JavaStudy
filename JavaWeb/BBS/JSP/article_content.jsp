<%@page import="java.sql.*"%>
<%@page import="com.bbs.*"%>
<%@page import="javafx.scene.chart.PieChart.Data"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	int id = Integer.parseInt(request.getParameter("id"));

	// 获取帖子详细内容
	Connection connection = DataBase.getConnection();
	int parentId, rootId = 0;
	String content = null;
	String title = null;
	String parentTitle = null;
	try {
		String sql = "select * from article where id = " + id;
		Statement statement = DataBase.getStatement(connection);
		ResultSet resultSet = DataBase.executeSQL(statement, sql);

		// 阅读API文档
		resultSet.next();
		rootId = resultSet.getInt("rootid");
		title = resultSet.getString("title");
		content = resultSet.getString("cont");

		// 获取本页标题等内容
		parentId = resultSet.getInt("pid");
		if (parentId == 0) {
			parentId = id;
		}
		DataBase.close(resultSet);
		DataBase.close(statement);

		sql = "select title from article where id = " + parentId;
		Statement statement1 = DataBase.getStatement(connection);
		ResultSet resultSet1 = DataBase.executeSQL(statement1, sql);
		resultSet1.next();
		parentTitle = resultSet1.getString("title");
		
		DataBase.close(resultSet1);
		DataBase.close(statement1);

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DataBase.close(connection);
	}
%>
<head>
<title><%=title%></title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="images/style.css"
	title="Integrated Styles">
<script language="JavaScript" type="text/javascript"
	src="images/global.js"></script>
<link rel="alternate" type="application/rss+xml" title="RSS"
	href="http://bbs.chinajavaworld.com/rss/rssmessages.jspa?threadID=744236">
</head>
<body>

	<br>
	<div id="jive-flatpage">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%">
						<p class="jive-page-title">
							主题:
							<%=title%></p>
					</td>
					<td width="1%"><div class="jive-accountbox"></div></td>
				</tr>
			</tbody>
		</table>
		<div class="jive-buttons">
			<table summary="Buttons" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="jive-icon"><a
							href="reply.jsp?id=<%= id %>&rootid=<%= rootId %>"><img
								src="images/reply-16x16.gif" alt="回复本主题" border="0" height="16"
								width="16"></a></td>
						<td class="jive-icon-label"><a id="jive-reply-thread"
							href="reply.jsp?id=<%= id %>&rootid=<%= rootId %>">回复本主题</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%"><div id="jive-message-holder">
							<div class="jive-message-list">
								<div class="jive-table">
									<div class="jive-messagebox">
										<table summary="Message" border="0" cellpadding="0"
											cellspacing="0" width="100%">
											<tbody>
												<tr id="jive-message-780144" class="jive-odd" valign="top">
													<td class="jive-first" width="1%">
														<!-- 个人信息的table -->
														<table border="0" cellpadding="0" cellspacing="0"
															width="150">
															<tbody>
																<tr>
																	<td><table border="0" cellpadding="0"
																			cellspacing="0" width="100%">
																			<tbody>
																				<tr valign="top">
																					<td style="padding: 0px;" width="1%">
																						<nobr>
																							LiQingzhen
																						</nobr>
																					</td>										
																				</tr>
																			</tbody>
																		</table> <img class="jive-avatar"
																		src="images/avatar-display.jpg" alt="" border="0">
																		<br> <br> <span class="jive-description">
																			发表: ** <br> 点数: ***<br> 注册: XX-XX-XX <br>	
																	</span></td>
																</tr>
															</tbody>
														</table> <!--个人信息table结束-->

													</td>
													<td class="jive-last" width="99%"><table border="0"
															cellpadding="0" cellspacing="0" width="100%">
															<tbody>
																<tr valign="top">
																	<td width="1%"></td>
																	<td width="97%"><span class="jive-subject">
																			<%=parentTitle%></span></td>
																	<td class="jive-rating-buttons" nowrap="nowrap"
																		width="1%"></td>
																	
																</tr>
																<tr>
																	<td colspan="4"
																		style="border-top: 1px solid rgb(204, 204, 204);"><br>
																		<%=content%> <br> <br></td>
																</tr>
															</tbody>
														</table></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="jive-message-list-footer">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<td nowrap="nowrap" width="1%"></td>
											<td align="center" width="98%"><table border="0"
													cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td><a
																href="article_tree.jsp"><img
																	src="images/arrow-left-16x16.gif" alt="返回到主题列表"
																	border="0" height="16" hspace="6" width="16"></a></td>
															<td><a
																href="article_tree.jsp">返回到主题列表</a>
															</td>
														</tr>
													</tbody>
												</table></td>
											<td nowrap="nowrap" width="1%">&nbsp;</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div></td>
					<td width="1%"></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>