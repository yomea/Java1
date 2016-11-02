package youth.hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTree {
	Connection conn = null;
	Statement stmt = null;
	
	

	public void tree(int vpid, int level) {
		
		String space = "";
		for(int i = 0; i < level; i++) {
			space = space + "***";
		}
		String s = "select * from article where pid = " + vpid;
		try {
			ResultSet rs = stmt.executeQuery(s);
			while(rs.next()) {
				System.out.println(space + rs.getString("cont"));
				if(rs.getInt("isleaf") == 0) {
					tree(rs.getInt("id"),level+1);
				}
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		

	}
	
	public void setConn() {
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","youth","youth");
				stmt = conn.createStatement();
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	
	
	
	public static void main(String[] args) {
		
		TestTree tt = new TestTree();
		tt.setConn();
		tt.tree(0, 0);
	}	
}
