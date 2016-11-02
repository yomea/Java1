package com.bjsxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletConnDatabase extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PrintWriter pw = null;
		ConnDataBase cd = new ConnDataBase();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/*conn = DriverManager.getConnection("jdbc:mysql://localhost/bbs","root","root");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select cont from article");*/
			
			conn = cd.getConn();
			stmt = cd.getStmt();
			rs = stmt.executeQuery("Select * from article");
			pw = response.getWriter();
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			pw.println("<html><meta charset='utf-8'><head><title>Servlet connect Database</title></head><body>");
			while(rs.next()){
				pw.println("<p>" + rs.getString("cont") + "</p>");
			}
			pw.println("</body><html>");
			pw.flush();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
					conn = null;
				}
				if(stmt != null){
					stmt.close();
					stmt = null;
				}
				if(rs != null) {
					rs.close();
					rs = null;
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			if(pw != null) {
				pw.close();
				pw = null;
				
			}
		}
	}
	
}
