package may.hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSrollResultSet {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			conn = DriverManager.getConnection(url,"youth","youth");
			stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
					);
			ResultSet rs = stmt.executeQuery("select * from article");
			rs.next();
			System.out.println(rs.getString("cont"));
			rs.last();
			System.out.println(rs.getString(2));
			System.out.println(rs.isLast());
			System.out.println(rs.isAfterLast());
			rs.previous();
			System.out.println(rs.getString("cont"));
			rs.absolute(10);
			System.out.println(rs.getString(2));
			System.out.println(rs.getRow());
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(stmt !=null) {
					stmt.close();
					stmt = null;
				}
				if(conn !=null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

}
