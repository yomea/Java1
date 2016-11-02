package youth.hong.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import youth.hong.DB.DB;
import youth.hong.gilr.Girl;

public class GirlMysqlDao implements GirlDao {
	
	public void addGirl(Girl girl) {
		Connection conn = DB.getConn();
		String sql = "insert into girl values (null,?,?,?,?,?,?)";
		PreparedStatement pStmt = DB.getPStmt(conn, sql);
		try {
			pStmt.setString(1, girl.getUsername());
			pStmt.setString(2, girl.getSex());
			pStmt.setInt(3,girl.getAge());
			pStmt.setTimestamp(4,girl.getBirthday());
			pStmt.setString(5, girl.getEmail());
			pStmt.setString(6, girl.getMobile());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pStmt);
			DB.close(conn);
		}
	}
	
	public void update(Girl girl) {
		Connection conn = DB.getConn();
		String sql = "update girl set username=?,sex=?,age=?,birthday=?,email=?,mobile=? where id=" + girl.getId();
		PreparedStatement pStmt = DB.getPStmt(conn, sql);
		try {
			pStmt.setString(1, girl.getUsername());
			pStmt.setString(2, girl.getSex());
			pStmt.setInt(3,girl.getAge());
			pStmt.setTimestamp(4,girl.getBirthday());
			pStmt.setString(5, girl.getEmail());
			pStmt.setString(6, girl.getMobile());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pStmt);
			DB.close(conn);
		}
	}
	
	public void deleteGirls(String sql) {
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		
	}
	
	public List<Girl> getGirls() {
		List<Girl> girls = new ArrayList<Girl>();
		Connection conn = DB.getConn();
		String sql = "select * from girl";
		Statement stmt = DB.getStmt(conn);
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while(rs.next()) {
				Girl g = new Girl();
				g.setId(rs.getInt("id"));
				g.setUser_name(rs.getString("username"));
				g.setAge(rs.getInt("age"));
				g.setBirthday(rs.getTimestamp("birthday"));
				g.setEmail(rs.getString("email"));
				g.setMobile(rs.getString("mobile"));
				g.setSex(rs.getString("sex"));
				girls.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return girls;
	}
	
	public Girl getGirl(int id) {
		Girl g = null;
		Connection conn = DB.getConn();
		String sql = "select * form gril where id=" + id;
		Statement stmt = DB.getStmt(conn);
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			if(rs.next()) {
				g = new Girl();
				g.setId(rs.getInt("id"));
				g.setUser_name(rs.getString("username"));
				g.setAge(rs.getInt("age"));
				g.setBirthday(rs.getTimestamp("birthday"));
				g.setEmail(rs.getString("email"));
				g.setMobile(rs.getString("mobile"));
				g.setSex(rs.getString("sex"));
			} else {
				System.out.println("error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return g;
	}
	
	public String Girls(int[] id, 
						String[] name,
						int lowAge,
						int highAge,
						Timestamp lowBirthday,
						Timestamp highBirthday,
						String email,
						String mobile, boolean delOrQuery) {
		
		String sql = "select * from girl where 1=1";
		if(id != null) {
			
			if(id.length > 0) {
				sql += " and id in (";
				for(int i = 0; i < id.length - 1; i++ ) {
					sql += id[i] + ",";
				}
				sql += id[id.length-1] + ")";
			}
		}
		if(name != null)  {
			if(name.length > 0) {
				for(int i = 0; i < name.length; i++ ) {
					sql += " and username like '%" + name[i] + "%'";
				}
			}
			
		}
		
		if(lowAge > 0) {
			sql += " and age >= " + lowAge;
		}
		if(highAge > 0) {
			sql += " and age <= " + highAge;
		}
		if(lowBirthday != null) {
			sql += " and birthday >= '" + new SimpleDateFormat("yyyy-MM-dd").format(lowBirthday) + "'";
		}
		if(highBirthday != null) {
			sql += " and birthday <= '" + new SimpleDateFormat("yyyy-MM-dd").format(highBirthday) + "'";
		}
		if(email != null) {
			sql += " and email like '%" + email + "%'";
		}
		if(mobile != null) {
			sql += " and mobile like '%" + mobile + "%'";
		}
		if(delOrQuery) {
			sql = sql.replaceFirst("select \\*", "delete");
		}
		System.out.println(sql);
		return sql;
	}
	
	public List<Girl> findGirls(String sql) {
		List<Girl> girls = new ArrayList<Girl>();
		Connection conn = DB.getConn();
		
		Statement stmt = DB.getStmt(conn);
		
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while(rs.next()) {
				Girl g = new Girl();
				g.setId(rs.getInt("id"));
				g.setUser_name(rs.getString("username"));
				g.setAge(rs.getInt("age"));
				g.setBirthday(rs.getTimestamp("birthday"));
				g.setEmail(rs.getString("email"));
				g.setMobile(rs.getString("mobile"));
				g.setSex(rs.getString("sex"));
				girls.add(g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return girls;
	}
	
}
