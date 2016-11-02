package youth.hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TestDate {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","youth","youth");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select sysdate from dual");
			while(rs.next()) {
				/*Date d = rs.getDate(1);
				Time t = rs.getTime(1);
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				SimpleDateFormat sdf = new SimpleDateFormat("yyy年MM月dd日 " + t);
		//		SimpleDateFormat sdf2 = new SimpleDateFormat("MM月");
				
				System.out.println(sdf.format(d));
				System.out.println(c.get(Calendar.MONTH) + 1);*/
				Timestamp ts = rs.getTimestamp(1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(sdf.format(ts));
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
