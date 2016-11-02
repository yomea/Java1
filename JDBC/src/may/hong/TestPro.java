package may.hong;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class TestPro {

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "youth", "youth");
			cstmt = conn.prepareCall("call p(?,?,?,?)");
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.setInt(1, 3);
			cstmt.setInt(2, 4);
			cstmt.setInt(4, 5);
			cstmt.execute();
			System.out.println(cstmt.getInt(3) + "\n" + cstmt.getInt(4));
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try{
				if(cstmt != null) {
					
					cstmt.close();
					cstmt = null;
				}
				if(conn != null)
				conn.close();
				conn = null;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
