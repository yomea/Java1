package youth.hong.shopping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
	
	private static int pageValue;
	
	public static List<User> getUsers(int pageStart, int pageSize) {
		List<User> users = new ArrayList<User>();
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from user limit " + pageStart + "," + pageSize;
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("addr"));
				user.setId(rs.getInt("id"));
				user.setRdate(rs.getTimestamp("rdate"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(stmt);
			DB.close(rs);
			
		}
		return users;
		
	}
	
	public static List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from user";
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("addr"));
				user.setId(rs.getInt("id"));
				user.setRdate(rs.getTimestamp("rdate"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(stmt);
			DB.close(rs);
			
		}
		return users;
		
	}
	
	public static void delete(int id) {
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "delete from user where id=" + id;
		DB.executeUpdate(stmt, sql);
		DB.close(conn);
		DB.close(stmt);
		
	}
	
	public static List<User> getUsers(int pageValue) {
		List<User> users = new ArrayList<User>();
		
		Connection conn = DB.getConn();
		users = getUsers();
		int pageCount = getPageCount()[0];
		if(pageValue < 1) {
			pageValue = 1;
		} else if(pageValue > pageCount) {
			pageValue = pageCount;
		}
		int pageStart = (pageValue - 1) * getPageCount()[1];
		users = getUsers(pageStart, getPageCount()[1]);
		UserManager.pageValue = pageValue;
		DB.close(conn);
		return users;
		
	}
	
	public static int getPageValue() {
		return UserManager.pageValue;
	}
	
	public static int[] getPageCount() {
		int pageSize = 3;
		int userCount = getUsers().size();
		int pageCount = userCount % pageSize == 0 ? userCount / pageSize : userCount / pageSize + 1;
		int[] page = new int[2];
		page[0] = pageCount;
		page[1] = pageSize;
		return page;
	}
}
