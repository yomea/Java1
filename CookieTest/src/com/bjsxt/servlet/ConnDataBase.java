package com.bjsxt.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDataBase {
	private Connection conn = null;
	private Statement stmt = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	public Connection getConn() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bbs","root","root");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	
	public Statement getStmt() {
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return stmt;
	}
	
	
	
}
