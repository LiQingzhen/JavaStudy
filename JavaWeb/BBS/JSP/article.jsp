<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.*, com.bbs.*, java.util.*, java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%	
	// 获得当前页面的url
	String url = request.getRequestURL() + (request.getQueryString() == null ? "" : ("?" + request.getQueryString()));
	// 分页操作
	List<Article> articles = new ArrayList<Article>();	
	Connection connection = DataBase.getConnection();
	Statement statement = DataBase.getStatement(connection);
	Statement statement1 = DataBase.getStatement(connection);
	
	ResultSet resultSet1 = DataBase.executeSQL(statement1, "select count(*) as total from article where pid = 0");
	resultSet1.next();
	// 获取总记录数，用于计算分页信息
	int total = resultSet1.getInt("total");
	int PAGE_SIZE = 7;	// 规定每页最多显示的主题数
	int temp = total/PAGE_SIZE + (total%PAGE_SIZE == 0 ? 0 : 1);	// 总页数
	
	int pageNum;	// 记录当前页码
	if(request.getParameter("pageNum") == null || request.getParameter("pageNum").trim().equals("")) 
		pageNum = 1;
	else{
		pageNum = Integer.parseInt(request.getParameter("pageNum"));
		// 防止越界
		if(pageNum < 1)	pageNum = 1;
		if(pageNum > temp) pageNum = temp;
	}
	// MySQL中的分页处理
	int start = (pageNum-1) * PAGE_SIZE,
		num = pageNum == temp ? ((total%PAGE_SIZE == 0 ? PAGE_SIZE : total%PAGE_SIZE)) : PAGE_SIZE;	
	ResultSet resultSet = DataBase.executeSQL(statement, "select * from article where pid = 0 limit "+ start + ", " + num);
	while(resultSet.next()){
		Article article = new Article();
		article.setId(resultSet.getInt("id"));
		article.setTitle(resultSet.getString("title"));
		article.setPdate(resultSet.getTimestamp("pdate"));
		articles.add(article);
	}
	
	DataBase.close(resultSet);
	DataBase.close(resultSet1);
	DataBase.close(statement1);
	DataBase.close(statement);
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
		<table border="0" cellpadding="3" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td>
						<span class="nobreak"> 页: <%= pageNum %>/<%= temp %> - 
							<span class="jive-paginator">
								 [ 
								<a href="article.jsp?pageNum=1">第一页</a> | 
								<a href="article.jsp?pageNum=<%= pageNum - 1 %>">上一页</a>  | 
								<a href="article.jsp?pageNum=<%= pageNum + 1 %>">下一页</a> | 
								<a href="article.jsp?pageNum=<%= temp %>">最尾页</a>
								]
							</span>
						</span>
					</td>
				</tr>
			</tbody>
		</table>
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
											SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											boolean isOdd = false;
											String classStr;
											
											for (Iterator<Article> iterator = articles.iterator(); iterator.hasNext();) {
												article = iterator.next();
												// 奇偶页区别显示
												if(isOdd) {
													classStr = "jive-odd";
													isOdd = false;
												}
												else {
													classStr = "jive-even";
													isOdd = true;
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
												<a href="article_delete.jsp?id=<%= article.getId() %>&from=<%= url %>">DEL</a>
											</td>
											<td class="jive-thread-name" width="95%">
												<a id="jive-thread-1" href="article_floor.jsp?id=<%= article.getId() %>">
													<%= article.getTitle() %>
												</a>
											</td>
											<td class="jive-author" nowrap="nowrap" width="1%">
												<span class="">LiQingzhen</span>
											</td>
											<td class="jive-view-count" width="1%">***</td>
											<td class="jive-msg-count" width="1%">*</td>
											<td class="jive-last" nowrap="nowrap" width="1%">
												<div class="jive-last-post">
													<br/> 
													<br/>
													<%=sdfs.format(article.getPdate())%>
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
