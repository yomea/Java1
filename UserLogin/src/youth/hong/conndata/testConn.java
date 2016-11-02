package youth.hong.conndata;

import java.sql.Connection;
import java.sql.Statement;

public class testConn {

	public static void main(String[] args) {
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
	}

}
