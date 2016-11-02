package youth.hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DmlTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		if(args.length < 5) {
			System.out.println("Parameter short,you must input 5 strings");
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "youth", "youth");
			stmt = conn.createStatement();
			String s = "insert into article values(" + args[0] + ",'" + args[1] + "'," + args[2] + "," + args[3] + ","
					+ args[4] + ")";
			stmt.executeUpdate(s);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (stmt != null) {

					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

}
