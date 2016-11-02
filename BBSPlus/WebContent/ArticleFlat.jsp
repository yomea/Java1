<%@ page pageEncoding="utf-8"%>
<%@ page import="java.sql.*,youth.hong.bbs.*,java.util.*"%>
<%!private void flat(List<Article> articles, Connection conn, int start, int pageSize, String socont) {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from article where pid=0 and (title like '%" + socont + "%' or cont like '%" + socont + "%') limit " + start + "," + pageSize;
		//System.out.println(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Article a = new Article();
				a.initArticle(rs);

				articles.add(a);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(rs);
		}

	}%>
<%	int total = 0;
	Connection conn = DB.getConn();
	String socont = request.getParameter("socont");
	if(socont == null || socont.trim().equals("")) {
		socont = "";
	}
	socont = socont.replaceAll("'", "''");
	String pageNo = request.getParameter("pageNo");
	int pageValue = 0;
	int pageSize = 3;
	if (pageNo != null) {
		try {
			pageValue = Integer.parseInt(pageNo.trim());
			

		} catch (NumberFormatException e) {
			pageValue = 1;
		}

	} else {
		pageValue = 1;
	}
	
	Statement stmt = DB.getStmt(conn);
	String sql = "select count(id) from article where pid=0 and (title like '%" + socont + "%' or cont like '%" + socont + "%')";
	//System.out.println(sql);
	ResultSet rs = DB.getRs(stmt, sql);
	rs.next();
	total = rs.getInt(1) % 3 == 0 ? rs.getInt(1) / 3 : rs.getInt(1) / 3 + 1;
	if (pageValue > total) {
		pageValue = total;
	} else if(pageValue < 1) {
		pageValue = 1;
	}

	int start = (pageValue - 1) * 3;
	List<Article> articles = new ArrayList<Article>();
	
	flat(articles, conn, start, pageSize, socont);
	DB.close(conn);
	String path = request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() + request.getContextPath() + request.getServletPath() + "?pageNo=" + pageValue;
	//System.out.println(path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Java语言*初级版</title>
<meta http-equiv="content-type" content="text/html; charset=utf8">
<link rel="stylesheet" type="text/css" href="images/style.css"
	title="Integrated Styles">
<script language="JavaScript" type="text/javascript"
	src="images/global.js"></script>
<link rel="alternate" type="application/rss+xml" title="RSS"
	href="http://bbs.chinajavaworld.com/rss/rssmessages.jspa?forumID=20">
<script language="JavaScript" type="text/javascript"
	src="images/common.js"></script>
<style type="text/css">
	form{ position:absolute; top:145px; right:0;}
</style>
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td width="40%"><img src="images/header-stretch.gif" alt=""
					border="0" height="57" width="100%"></td>
				<td width="1%"><img src="images/header-right.gif" alt=""
					height="57" border="0"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<div id="jive-forumpage">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="98%"><p class="jive-breadcrumbs">论坛: Java语言*初级版
							(模仿)</p>
						<p class="jive-description">探讨Java语言基础知识,基本语法等 大家一起交流
							共同提高！谢绝任何形式的广告</p></td>
				</tr>
			</tbody>
		</table>
		<div class="jive-buttons">
			<table summary="Buttons" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="jive-icon"><a href="post.jsp"><img
								src="images/post-16x16.gif" alt="发表新主题" border="0" height="16"
								width="16"></a></td>
						<td class="jive-icon-label"><a id="jive-post-thread"
							href="post.jsp">发表新主题</a> <a
							href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;isBest=1"></a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		<table border="0" cellpadding="3" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td><span class="nobreak"> 页: <%=pageValue %>- <span
							class="jive-paginator"> [ <a
								href="ArticleFlat.jsp?socont=<%=socont %>&pageNo=<%=pageValue-1%>" style="<%=pageValue==1 ? "color:red" : ""%>">上一页</a>
								<%for(int i = 1; i <= total; i++) {
									
								 %>
								| <a
								href="ArticleFlat.jsp?socont=<%=socont %>&pageNo=<%=i %>"
								class="" style="<%=pageValue==i ? "color:pink" : ""%>"><%=i %></a> 
								<%} %>
								 | <a
								href="ArticleFlat.jsp?socont=<%=socont %>&pageNo=<%=pageValue+1%>" style="<%=pageValue==total ? "color:red" : ""%>">下一页</a>
								]
						</span>
					</span></td>
					
				</tr>
			</tbody>
		</table>
			<form name="sosuo" method="get" action="ArticleFlat.jsp">
				<input type="text" name="socont" />
				<input type="submit" value="搜索" />
			</form>
		<table style="width: 100%;">
			<tbody>
				<tr valign="top">
					<td width="99%"><div class="jive-thread-list">
							<div class="jive-table">
								<table summary="List of threads" style="width: 100%;">
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
										<%	if(total != 0) {
											for (Iterator<Article> it = articles.iterator(); it.hasNext();) {
												String preStr = "";
												Article article = it.next();
												
										%>
										<tr class="jive-even">
											<td class="jive-first" nowrap="nowrap" width="1%"><div
													class="jive-bullet">
													<img src="images/read-16x16.gif" alt="已读" border="0"
														height="16" width="16">
													<!-- div-->
												</div></td>


											<%	String check = (String)session.getAttribute("admin");
												if(check != null && check.trim().equals("true")) {
													
												%>
											<td nowrap="nowrap" width="1%">
											<a
												href="Delete.jsp?id=<%=article.getId()%>&admin=<%=(String) session.getAttribute("admin")%>&url=<%=path %>">DEL</a>
											</td>
										<%} else {%>	
											<td nowrap="nowrap" width="1%">
											<a
												href="Modify.jsp?id=<%=article.getId()%>&rootId=<%=article.getRootId()%>">MODIFY</a>
											</td>
											<%} %>
											<td class="jive-thread-name" width="95%"><a
												id="jive-thread-1"
												href="ArtticleDetailFlat.jsp?rootId=<%=article.getId()%>"><%=article.getTitle()%></a></td>
											<td class="jive-author" nowrap="nowrap" width="1%"><span
												class=""> <a
													href="http://bbs.chinajavaworld.com/profile.jspa?userID=226030">youth</a>
											</span></td>
											<td class="jive-view-count" width="1%">10000</td>
											<td class="jive-msg-count" width="1%">0</td>
											<td class="jive-last" nowrap="nowrap" width="1%"><div
													class="jive-last-post">
													<br> by: <a
														href="http://bbs.chinajavaworld.com/thread.jspa?messageID=780182#780182"
														title="jingjiangjun" style="">youth &#187;</a>


												</div></td>
										</tr>
										<%
											}} else {
										
											out.println("啥都没找到！！！");
										} %>
										<%--
                  <tr class="jive-odd">
                    <td class="jive-first" nowrap="nowrap" width="1%"><div class="jive-bullet"> <img src="images/read-16x16.gif" alt="已读" border="0" height="16" width="16">
                        <!-- div-->
                      </div></td>
                    <td nowrap="nowrap" width="1%">&nbsp;
                      
                      
                      
                      
                      &nbsp;</td>
                    <td class="jive-thread-name" width="95%"><a id="jive-thread-2" href="http://bbs.chinajavaworld.com/thread.jspa?threadID=744234&amp;tstart=25">请 兄弟们指点下那里 错误，，，</a></td>
                    <td class="jive-author" nowrap="nowrap" width="1%"><span class=""> <a href="http://bbs.chinajavaworld.com/profile.jspa?userID=226028">403783154</a> </span></td>
                    <td class="jive-view-count" width="1%"> 52</td>
                    <td class="jive-msg-count" width="1%"> 2</td>
                    <td class="jive-last" nowrap="nowrap" width="1%"><div class="jive-last-post"> 2007-9-13 上午8:40 <br>
                        by: <a href="http://bbs.chinajavaworld.com/thread.jspa?messageID=780172#780172" title="downing114" style="">downing114 &#187;</a> </div></td>
                  </tr>
                    --%>

									</tbody>
								</table>
							</div>
						</div>
						<div class="jive-legend"></div></td>
				</tr>
			</tbody>
		</table>
		<br> <br>
	</div>
</body>
</html>
