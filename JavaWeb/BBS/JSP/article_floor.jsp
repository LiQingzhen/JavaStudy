<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.bbs.*"%>
<%@page import="javafx.scene.chart.PieChart.Data"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	int id = Integer.parseInt(request.getParameter("id"));
	
	List<Article> articles = new ArrayList<Article>();
	Connection connection = DataBase.getConnection();
	Statement statement = DataBase.getStatement(connection);
	Statement statement1 = DataBase.getStatement(connection);

	try {
		String sql = "select * from article where rootid = " + id;		
		ResultSet resultSet = DataBase.executeSQL(statement, sql);
		
		while(resultSet.next()){
			Article article = new Article();
			article.setId(resultSet.getInt("id"));
			article.setParentId(resultSet.getInt("pid"));
			article.setTitle(resultSet.getString("title"));
			article.setContent(resultSet.getString("cont"));
			
			if(article.getParentId() == 0) article.setParentId(article.getId());
			ResultSet resultSet1 = DataBase.executeSQL(statement1, "select cont from article where id = " + article.getParentId());
			resultSet1.next();
			article.setParentContent(resultSet1.getString("cont"));
			
			articles.add(article);
			DataBase.close(resultSet1);
		}
		DataBase.close(resultSet);
	} catch(SQLException e){
		e.printStackTrace();
	}finally{
		DataBase.close(statement);
		DataBase.close(statement1);
		DataBase.close(connection);
	}
		
%>
<head>
<title><%= "帖子详情" %></title>
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
		<br>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%"><div id="jive-message-holder">
							<div class="jive-message-list">
								<%
									Article article = new Article(); 
									int count = 0;
									String floor;

									for(Iterator<Article> iterator = articles.iterator(); iterator.hasNext(); ){
										article = iterator.next();
										if(count == 0){
											floor = "楼主：";
										}else {
											floor = count + "楼：";
										}

								%>
								<div class="jive-table">
									<div class="jive-messagebox">
									
									
										<table summary="Message" border="0" cellpadding="0"
											cellspacing="0" width="100%">
											<tbody>
												<tr id="jive-message-780144" class="jive-even" valign="top">
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
																					<td style="padding: 0px;" width="1%"><nobr>
																							LiQingzhen
																						</nobr></td>	
																				</tr>
																			</tbody>
																		</table> <img class="jive-avatar"
																		src="images/avatar-display.jpg" alt="" border="0">
																		<br> <br> <span class="jive-description">
																			发表: ** <br> 点数: **<br> 注册: XX-XX-XX <br>	
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
																			<%=floor + article.getTitle()%></span></td>
																	<td class="jive-rating-buttons" nowrap="nowrap" width="1%"></td>
																	<td width="1%">	
																		<div class="jive-buttons">
                                    										<table border="0" cellpadding="0" cellspacing="0">
                                      											<tbody>
                                        											<tr>
                                          												<td>&nbsp;</td>
                                          												<td class="jive-icon">
                                          													<a href="reply.jsp?id=<%= article.getId() %>&rootid=<%= id %>" title="回复本主题"><img src="images/reply-16x16.gif" alt="回复" border="0" height="16" width="16"></a> 
                                          												</td>
                                          												<td class="jive-icon-label"><a href="reply.jsp?id=<%= article.getId() %>&rootid=<%= id %>" title="回复本帖">回复</a> </td>
                                       												</tr>
                                      											</tbody>
                                    										</table>
                                  										</div>
                                  									</td>
																</tr>
																<tr>
																	<td colspan="4"
																		style="border-top: 1px solid rgb(204, 204, 204);"><br>
																		<%=article.getContent()%> <br> <br></td>
																</tr>
															</tbody>
														</table></td>
												</tr>
											</tbody>
										</table>							
									</div>
								</div>
									<%
										count++;
									}
									%>
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
																href="article.jsp"><img
																	src="images/arrow-left-16x16.gif" alt="返回到主题列表"
																	border="0" height="16" hspace="6" width="16"></a></td>
															<td><a
																href="article.jsp">返回到主题列表</a>
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
</body>
</html>