package youth.hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","root","root");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from table1");
			while(rs.next()) {
				System.out.println(rs.getString(2));
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
				if(rs != null) {
					rs.close();
					rs = null;
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

}
