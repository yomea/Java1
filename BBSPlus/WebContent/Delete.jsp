<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,youth.hong.bbs.*" %>
<%! private void delete(Connection conn, int id) {
	Statement deStmt = null;
	String sql = "select * from article where pid = " + id;
	Statement stmt = DB.getStmt(conn);
	ResultSet rs = DB.getRs(stmt, sql);
	try {
		while(rs.next()) {
			delete(conn, rs.getInt("id"));	
		}
		deStmt = DB.getStmt(conn);
		String deSql = "delete from article where id=" + id;
		DB.executeUpdate(deStmt, deSql);
	} catch(SQLException e) {
		e.printStackTrace();
	} finally {
		DB.close(stmt);
		DB.close(deStmt);
		DB.close(rs);
		
		
	}
		
	}
%>

<%	String strId = request.getParameter("id");
	int id = 0;
	if(strId == null) {
		out.println("操作不被允许！");
		return;
	} else {
		try {
		id = Integer.parseInt(strId.trim());
			
		} catch(NumberFormatException e) {
			out.println("操作失败!");
			return;
		}
			String admin = (String)session.getAttribute("admin");
			if(admin != null && admin.equals("true")) {
				Connection conn = DB.getConn();
				Statement stmt = DB.getStmt(conn);
				String sql = "select * from article where id=" + id ;
				
				ResultSet rs = DB.getRs(stmt, sql);
				rs.next();
				int pid = rs.getInt("pid");
				//System.out.println(id);
				Statement pStmt = DB.getStmt(conn);
				ResultSet pRs = DB.getRs(pStmt, "select count(*) from article where pid=" + pid);
				pRs.next();
				if(pRs.getInt(1) <= 1) {
					Statement xStmt = DB.getStmt(conn);
					DB.executeUpdate(xStmt, "update article set isleaf=0 where id=" + pid);
				}
				delete(conn,id);
				String url = request.getParameter("url");
				response.sendRedirect(url);
				DB.close(conn);
			} else {
				response.sendRedirect("login.jsp");
		
			}
	
		
	}
%>
