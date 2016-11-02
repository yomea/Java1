package may.hong;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class TestCallableSatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			//无参
//			CallableStatement cs = conn.prepareCall("Call test()");
			//入参
//			CallableStatement cs = conn.prepareCall("Call intest(?)");
			//出参
			CallableStatement cs = conn.prepareCall("Call outtest(?)");
			//cs.setInt(1, 3);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			System.out.println(cs.getInt(1));
			/*ResultSet rs = cs.getResultSet();
			while(rs.next()) {
				System.out.println(rs.getInt(1));
			}*/
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
