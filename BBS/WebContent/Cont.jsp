<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"  %>
<%  Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs","root","root");
	Statement stmt = conn.createStatement();
	String strId = request.getParameter("id");
	
	    int id = 0;
	    int rootid = 0;
	   
	    String strRootId = request.getParameter("rootid");
	    if(strId == null || strRootId == null) {
		    out.println("操作被拒绝！请通过正确路径进入");
	    	return;
	    } else if(strId.trim()==""||strRootId.trim()==""){
	    	out.println("操作被拒绝！请通过正确路径进入");
	    	return;
	    } else {
	    	id = Integer.parseInt(request.getParameter("id")); 
		    rootid = Integer.parseInt(request.getParameter("rootid"));
	    }
 
	ResultSet rs = stmt.executeQuery("select * from article where id = '" + id + "'"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	if(rs.next()) {%>
	<table border="1">
		<tr>
			<td>id</td>
			<td><%= rs.getInt("id") %></td>
		</tr>
		<tr>
			<td>title</td>
			<td><%= rs.getString("title") %></td>
		</tr>
		<tr>
			<td>cont</td>
			<td><%= rs.getString("cont") %></td>
		</tr>
	</table>
	<a href="huifu.jsp?id=<%= id %>&rootid=<%= rootid %>">回复</a>	
<% 	}
	conn.close();
	stmt.close(); 
	rs.close(); 
	%>
</body>
</html>