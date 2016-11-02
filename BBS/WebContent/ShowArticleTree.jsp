<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!	String str = "";
	boolean manager = false;
	private void tree(Connection conn, int id, int level) {
		Statement stmt = null;
		ResultSet rs = null;
		String preStr = "";
		for (int i = 0; i < level; i++) {
			preStr += "---";
		}
		try { 

			String sql = "select * from article where pid = " + id;
			//System.out.println("hello");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String stradmin = "";
			while (rs.next()) {
				if(manager) {
					stradmin = "<td><a href='Delete.jsp?id=" + rs.getInt("id") + "&pid=" + rs.getInt("pid")
							+ "'>删除</a>";
				}
				str += "<tr><td>" + rs.getInt("id") + "</td><td>" + "<a href='Cont.jsp?id=" + rs.getInt("id")
				+ "&rootid=" + rs.getInt("rootid") + "'>" + preStr + rs.getString("title") + "</a>"
				+ "</td>" + stradmin +"</td></tr>";
				if (rs.getInt("isleaf") != 0) {
					tree(conn, rs.getInt("id"), level + 1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.sql.*"%>
	<%	String admin = (String)session.getAttribute("admin");
		if(admin != null && admin.equals("true")) {
			manager = true;
		
		}
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs", "root", "root");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from article where pid = 0");
		String stradmin="";
		while (rs.next()) {
			if(manager) {
				stradmin = "<td><a href='Delete.jsp?id=" + rs.getInt("id") + "&pid=" + rs.getInt("pid")
						+ "'>删除</a>";
			}
			str += "<tr><td>" + rs.getInt("id") + "</td><td>" + "<a href='Cont.jsp?id=" + rs.getInt("id")
			+ "&rootid=" + rs.getInt("rootid") + "'>" + rs.getString("title") + "</a>"
			+ "</td>" + stradmin +"</td></tr>";
			if (rs.getInt("isleaf") != 0) {

				tree(conn, rs.getInt("id"), 1);
			}
		}
		conn.close();
		stmt.close();
		rs.close();
	%>
	<table border="1">
		<%=str%>
		<%	
			manager = false;
			str = "";
		%>
	</table>
	
	<form name="send" method="get" action="WriterArticle.jsp">
		<input type="submit" value="朕也要发表主题" />
	</form>
</body>
</html>