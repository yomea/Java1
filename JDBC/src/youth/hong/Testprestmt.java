package youth.hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Testprestmt {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(args.length < 5) {
			System.out.println("Parameter short,you must input 5 strings");
		}
		int id = 0;
		String cont = null;
		int pid = 0;
		int isleaf = 0;
		int alevel = 0;
		try{
			id = Integer.parseInt(args[0]);
			pid = Integer.parseInt(args[2]);
			isleaf = Integer.parseInt(args[3]);
			alevel = Integer.parseInt(args[4]);
		}catch(NumberFormatException e) {
			System.out.println("format error");
		}
		
		cont = args[1];

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "youth", "youth");
			pstmt = conn.prepareStatement("insert into article values (?,?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, cont);
			pstmt.setInt(3, pid);
			pstmt.setInt(4, isleaf);
			pstmt.setInt(5, alevel);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {

					pstmt.close();
					pstmt = null;
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
