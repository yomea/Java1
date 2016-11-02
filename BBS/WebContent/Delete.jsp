<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%! private void delete(Connection conn,int id) {
	Statement stmt = null;
	ResultSet rs = null;
	try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from article where pid = " + id);
			
			 while(rs.next()) {
				delete(conn, rs.getInt("id"));
			} 
			 stmt.executeUpdate("delete from article where id = " + id);
		} catch(SQLException e) {
			e.printStackTrace();
		
		} finally {
			try{
				stmt.close();
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
}
%>

<%	String admin = (String)session.getAttribute("admin");
	if(admin == null) {
		out.println("操作不被允许！你必须管理员的身份才能操作！");
		return;
	}	

	int id = Integer.parseInt(request.getParameter("id"));	
	int pid = Integer.parseInt(request.getParameter("pid"));
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs", "root", "root");
	Statement stmt = conn.createStatement();
	conn.setAutoCommit(false);
	delete(conn, id);
	ResultSet rs = stmt.executeQuery("select count(*) from article where pid = " + pid);
	rs.next();
	int count = rs.getInt(1);
	if(count <= 0) {
		Statement upStmt = conn.createStatement();
		upStmt.executeUpdate("update article set isleaf = 0 where id = " + pid);
		upStmt.close();
	}
	conn.commit();
	conn.setAutoCommit(true);
	stmt.close();
	rs.close();
	conn.close();
	response.sendRedirect("ShowArticleTree.jsp");

%>	
