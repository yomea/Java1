package youth.testddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestCreate {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String className = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(className);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","youth","youth");
			pstmt = conn.prepareStatement("create table testCreate(id number primary key)");
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
