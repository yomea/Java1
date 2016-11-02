package youth.hong.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategoryDAO {
	
	
	public static void save(Category c) {
		Connection conn = DB.getConn();
		String sql = "";
		if(c.getId() == -1) {
			sql = "insert into category values (null,?,?,?,?,?)";
		} else {
			sql = "insert into category values (" + c.getId() + ",?,?,?,?,?)";
		}
		PreparedStatement pStmt = DB.getPStmt(conn, sql);
		try {
			pStmt.setInt(1, c.getPid());
			pStmt.setString(2, c.getName());
			pStmt.setString(3, c.getDescr());
			pStmt.setInt(4, c.getCno());
			pStmt.setInt(5, c.getGrade());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(pStmt);
			DB.close(conn);
		}
		
	}
	
	public static void update(int id) {
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "update category set cno=1 where id=" + id;
		DB.executeUpdate(stmt, sql);
		DB.close(conn);
		DB.close(stmt);
	}
	
	public static void getCategory(Connection conn, List<Category> cgs, int id) {
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from category where pid=" + id;
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while(rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setCno(rs.getInt("cno"));
				c.setDescr(rs.getString("descr"));
				c.setGrade(rs.getInt("grade"));
				c.setPid(rs.getInt("pid"));
				cgs.add(c);
				if(rs.getInt("cno") != 0) {
					getCategory(conn,cgs,rs.getInt("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DB.close(stmt);
			DB.close(rs);
		}
		
	}
	
	/*public static List<Category> getCategory() {
		List<Category> categories = new ArrayList<Category>();
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from category";
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while(rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setCno(rs.getInt("cno"));
				c.setDescr(rs.getString("descr"));
				c.setGrade(rs.getInt("grade"));
				c.setPid(rs.getInt("pid"));
				categories.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(stmt);
			DB.close(rs);
		}
		return categories;
	}*/
	
	public static void setCno(int pid) {
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select count(*) from category where pid=" + pid;
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			rs.next();
			if(rs.getInt(1) <= 0) {
				Statement uStmt = DB.getStmt(conn);
				DB.executeUpdate(uStmt, "update category set cno=0 where id=" + pid);
				DB.close(uStmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(conn);
			DB.close(stmt);
			DB.close(rs);
			
		}
	}
	
	public static Connection getConn() {
		Connection conn = DB.getConn();
		return conn;
	}
	public static void deleteCategories(Connection conn, int id) {
		
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from category where pid=" + id;
		ResultSet rs = DB.getRs(stmt, sql);
		String cSql = "select count(*) from product where categoryid=" + id;
		Statement cStmt = DB.getStmt(conn);
		ResultSet cRs = DB.getRs(cStmt, cSql);
		try {
			cRs.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while(rs.next()) {
				
					
					deleteCategories(conn, rs.getInt("id"));
				
			}
			if(cRs.getInt(1) <= 0) {
				DB.executeUpdate(stmt, "delete from category where id=" + id);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(rs);
		}
		
	}
	
	public static Category getCategory(int id) {
		Connection conn = getConn();
		Category c = null;
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from category where id=" + id;
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			if(rs.next()) {
				c = new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setCno(rs.getInt("cno"));
				c.setDescr(rs.getString("descr"));
				c.setGrade(rs.getInt("grade"));
				c.setPid(rs.getInt("pid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static void modifyCategory(int id, String name, String descr) {
		Connection conn = getConn();
		String sql = "update category set name=?,descr=? where id=" + id;
		PreparedStatement pStmt = DB.getPStmt(conn, sql);
		try {
			pStmt.setString(1, name);
			pStmt.setString(2, descr);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
