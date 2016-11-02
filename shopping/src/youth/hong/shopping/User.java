package youth.hong.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class User {
	private int id;
	private String username;
	private String password;
	private String phone;
	private String address;
	private Timestamp rdate;
	
	public User() {
		
	}
	
	public User(String username, String password, String phone, String address) {
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.rdate = new Timestamp(System.currentTimeMillis());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getRdate() {
		return rdate;
	}
	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	
	public  void save() {
		Connection conn = DB.getConn();
		String sql = "insert into user values (null,?,?,?,?,?)";
		PreparedStatement pStmt = DB.getPStmt(conn, sql);
		try {
			pStmt.setString(1, username);
			pStmt.setString(2, password);
			pStmt.setString(3, phone);
			pStmt.setString(4, address);
			pStmt.setTimestamp(5, rdate);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(pStmt);
			DB.close(conn);
		}
		
	}
	
	public static User checkUser(String username, String password) throws UserNameNotFoundException, PasswordNotCurrectException {
		Connection conn = DB.getConn();
		User user = null;
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from user where username='" + username + "'";
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			if(!rs.next()) {
				throw new UserNameNotFoundException();
			} else if(!password.equals(rs.getString("password"))) {
				throw new PasswordNotCurrectException();
			} else {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("addr"));
				user.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static void userModify(int id,String username, String phone, String address) {
		Connection conn = DB.getConn();
		String sql = "update user set username=?,phone=?,addr=? where id=" + id;
		PreparedStatement pStmt = DB.getPStmt(conn, sql);
		try {
			pStmt.setString(1, username);
			pStmt.setString(2,phone);
			pStmt.setString(3, address);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(pStmt);
		}
		
		
	}

	
}
