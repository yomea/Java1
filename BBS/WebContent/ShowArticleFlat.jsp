<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	int pageSize = 3;
	String pageNo = (String)request.getParameter("pageNo");
	int pageValue = 1;
	
	if(pageNo != null) {
		try{
			
			pageValue = Integer.parseInt(pageNo.trim());
			
			if(pageValue <= 0) {
				pageValue = 1;
			}
		} catch(NumberFormatException e ) {
			pageValue = 1;	
		}
		
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.sql.*"%>
	<%
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs", "root", "root");
		Statement stmtCount = conn.createStatement(ResultSet.CONCUR_READ_ONLY,ResultSet.TYPE_SCROLL_INSENSITIVE);
		ResultSet rsCount = stmtCount.executeQuery("select * from article where pid=0");
		rsCount.last();
		int total = (rsCount.getRow()%pageSize == 0 ? rsCount.getRow()/3 : rsCount.getRow()/3+1);
		if(pageValue > total) {
			pageValue = 3;
		}
		int startPage = (pageValue - 1)*3;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from article where pid = 0 limit " + startPage + "," + pageSize);
		String str = "";
		while (rs.next()) {
			
			str += "<tr><td>" + rs.getInt("id") + "</td><td>" + "<a href='Cont.jsp?id=" + rs.getInt("id")
			+ "&rootid=" + rs.getInt("rootid") + "'>" + rs.getString("title") + "</a>"
			+ "</td></td></tr>";
			
		}
		conn.close();
		stmt.close();
		rs.close();
	%>
	<table border="1">
		<%=str %>
	</table>
	<span>共<%=total %>页</span>
	<span>第<%=pageValue %>页</span>
	<span><a href="ShowArticleFlat.jsp?pageNo=<%=pageValue-1%>">上一页</a></span>
	<span><a href="ShowArticleFlat.jsp?pageNo=<%=pageValue+1%>">下一页</a></span>
	<form name="changPage" method="get" action="ShowArticleFlat.jsp">
		<select name="pageNo" onchange="document.changPage.submit()">
			<% for(int i = 1; i <= total; i++) {
			%>
				<option value="<%=i%>" <%=(pageValue == i ? "selected" : "") %> >第<%=i %>页</option>
			<% 
			
				}
			%>
			
		</select>
	</form>
	
	<form name="chancePage" method="get" action="ShowArticleFlat.jsp">
		
			<% for(int i = 1; i <= total; i++) {
			%>
				<input type="submit" name="pageNo" value="<%=i%>" />
			<% 
			
				}
			%>
			
		
	</form>
	
	<form name="goPage" method="get" action="ShowArticleFlat.jsp">
		<input type="text" size="4" name="pageNo" />
		<input type="submit" value="go" />
	</form>
	
	<form name="send" method="get" action="WriterArticle.jsp">
		<input type="submit" value="朕也要发表主题" />
	</form>
</body>
</html>