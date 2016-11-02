package may.hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.Statement;

public class TestTranscation {

	public static void main(String[] args){
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","youth","youth");
			conn.setAutoCommit(false);
			/*stmt = conn.createStatement();
			stmt.addBatch("insert into testBatch values (1,'500','haha')");
			stmt.addBatch("insert into testBatch values (2,'500','haha')");
			stmt.addBatch("insert into testBatch values (3,'500','haha')");
			stmt.addBatch("insert into testBatch values (4,'500','haha')");
			stmt.addBatch("insert into testBatch values (5,'500','haha')");
			stmt.executeBatch();
			*/
			pstmt = conn.prepareStatement("insert into testBatch values (?,?,?)");
			pstmt.setInt(1, 100);
			pstmt.setString(2, "1000");
			pstmt.setString(3,"hehe");
			
			pstmt.addBatch();
			pstmt.setInt(1, 200);
			pstmt.setString(2, "1000");
			pstmt.setString(3,"hehe");
			
			pstmt.addBatch();
			pstmt.setInt(1, 300);
			pstmt.setString(2, "1000");
			pstmt.setString(3,"hehe");
			
			pstmt.addBatch();
			
			pstmt.executeBatch();
			
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
			try {
				if(conn != null) {
					
					conn.rollback();
					conn.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}finally {
			try {
				/*if(stmt!=null) {
					stmt.close();
					stmt = null;
				}*/
				
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
