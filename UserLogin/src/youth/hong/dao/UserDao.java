package youth.hong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import youth.hong.conndata.DB;
import youth.hong.user.User;

public class UserDao {
	public boolean addUser(User user) {
		Connection conn = DB.getConn();
		String sql = "insert into user values (null,?,?)";
		PreparedStatement pStmt = DB.getPStmt(conn, sql);
		try {
			pStmt.setString(1, user.getUsername());
			pStmt.setString(2, user.getPassword());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(pStmt);
			DB.close(conn);
		}
		return true;
	}
	
	public boolean checkUser(User user) throws UsernameNotFoundException, PasswordNotCorrectException {
		Connection conn = DB.getConn();
		String checkusernamesql = "select * from user where username='" + user.getUsername() + "'";
		String checkpasswordsql = "select * from user where password='" + user.getPassword() + "'";
		System.out.println(checkusernamesql);
		System.out.println(checkpasswordsql);
		Statement checkusernamestmt = DB.getStmt(conn);
		ResultSet checkusernamers = DB.getRs(checkusernamestmt, checkusernamesql);
		Statement checkpasswordstmt = DB.getStmt(conn);
		ResultSet checkpasswordrs = DB.getRs(checkpasswordstmt, checkpasswordsql);
		try {
			if(!checkusernamers.next()) {
				throw new UsernameNotFoundException("Username Not Found!");
			} else if(!checkpasswordrs.next()) {
				throw new PasswordNotCorrectException("Password Not Correct!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.close(checkusernamestmt);
			DB.close(checkpasswordstmt);
			DB.close(conn);
		}
		return true;
	}
}
