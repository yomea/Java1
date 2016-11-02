package youth.hong.shopping.product.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import youth.hong.shopping.Category;
import youth.hong.shopping.DB;
import youth.hong.shopping.product.Product;

public class ProductMysqlDAO implements ProductDAO {

	public List<Product> getProduct() {
		List<Product> products = new ArrayList<Product>();
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from product";
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescr(rs.getString("descr"));
				p.setCategoryid(rs.getInt("categoryid"));
				p.setMemberPrice(rs.getDouble("memberprice"));
				p.setNormalPrice(rs.getDouble("normalprice"));
				p.setPdate(rs.getTimestamp("pdate"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return products;
	}

	public List<Product> getProduct(int pageNo, int pageSize) {
		List<Product> products = new ArrayList<Product>();
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select p.id pid,p.name pn,p.descr pd, p.normalprice pnp, p.memberprice pmp, p.pdate pp, p.categoryid pc, c.id cid,c.pid cpid, c.name cn, c.descr cd, c.cno cc, c.grade cg from product p join category c on p.categoryid = c.id limit "
				+ (pageNo - 1) * pageSize + "," + pageSize;
		//System.out.println(sql);
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("pid"));
				p.setName(rs.getString("pn"));
				p.setDescr(rs.getString("pd"));
				p.setCategoryid(rs.getInt("pc"));
				p.setMemberPrice(rs.getDouble("pmp"));
				p.setNormalPrice(rs.getDouble("pnp"));
				p.setPdate(rs.getTimestamp("pp"));
				Category c = new Category();
				c.setId(rs.getInt("cid"));
				c.setPid(rs.getInt("cpid"));
				c.setCno(rs.getInt("cc"));
				c.setDescr(rs.getString("cd"));
				c.setGrade(rs.getInt("cg"));
				c.setName(rs.getString("cn"));
				p.setCategory(c);
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return products;
	}

	public int findProduct(List<Product> products, int[] categoryId, String keyWord, double lowNormalPrice,
			double highNormalPrice, double lowMemberPrice, double highMemberPrice, Date startDate, Date endDate,
			int pageNo, int pageSize) {

		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		int pageCount = 0;
		String sql = "select * from Product where 1=1";
		if (categoryId != null && categoryId.length > 0) {
			sql += " and categoryid in (";
			for (int i = 0; i < categoryId.length - 1; i++) {
				sql += categoryId[i] + ",";
			}
			sql += categoryId[categoryId.length - 1] + ")";
		}
		if (keyWord != null) {
			keyWord = keyWord.trim();
			sql += " and name like '%" + keyWord + "%' and descr like '%" + keyWord + "%'";
		}
		if (lowNormalPrice != -1) {
			sql += " and normalprice >= " + lowNormalPrice;
		}
		if (highNormalPrice != -1) {
			sql += " and normalprice <= " + highNormalPrice;
		}
		if (lowMemberPrice != -1) {
			sql += " and memberprice >= " + lowMemberPrice;
		}
		if (highMemberPrice != -1) {
			sql += " and memberprice <= " + highMemberPrice;
		}
		if (startDate != null) {
			sql += " and pdate >= " + new SimpleDateFormat("yyyy-MM-dd").format(startDate);
		}
		if (endDate != null) {
			sql += " and pdate <= " + new SimpleDateFormat("yyyy-MM-dd").format(endDate);
		}
		String sqlCount = sql.replaceAll("select \\*", "select count(*)");
		// System.out.println(sqlCount);
		Statement sqlStmt = DB.getStmt(conn);
		ResultSet sqlRs = DB.getRs(sqlStmt, sqlCount);
		try {
			sqlRs.next();
			pageCount = sqlRs.getInt(1) % pageSize == 0 ? sqlRs.getInt(1) / pageSize : sqlRs.getInt(1) / pageSize + 1;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		sql += " limit " + (pageNo - 1) * pageSize + "," + pageSize;
		ResultSet rs = DB.getRs(stmt, sql);
		// System.out.println(sql);
		try {
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescr(rs.getString("descr"));
				p.setCategoryid(rs.getInt("categoryid"));
				p.setMemberPrice(rs.getDouble("memberprice"));
				p.setNormalPrice(rs.getDouble("normalprice"));
				p.setPdate(rs.getTimestamp("pdate"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return pageCount;
	}

	public List<Product> findProduct(String name) {
		return null;
	}

	public boolean deleteProductByCategoryId(int categoryId) {
		return false;
	}

	public boolean deleteProductsById(int[] idArray) {
		return false;
	}

	public boolean updateProduct(Product p) {
		Connection conn = DB.getConn();
		String sql = "update product set name=?,descr=?,normalprice=?,memberprice=?,categoryid=?,pdate=? where id=?";
		PreparedStatement pStmt = DB.getPStmt(conn, sql);
		try {
			pStmt.setString(1, p.getName());
			pStmt.setString(2, p.getDescr());
			pStmt.setDouble(3, p.getNormalPrice());
			pStmt.setDouble(4, p.getMemberPrice());
			pStmt.setInt(5, p.getCategoryid());
			pStmt.setTimestamp(6, p.getPdate());
			pStmt.setInt(7, p.getId());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean addProduct(Product p) {
		Connection conn = DB.getConn();
		String sql = "insert into product values (null,?,?,?,?,?,?)";
		PreparedStatement pStmt = DB.getPStmt(conn, sql);

		try {
			pStmt.setString(1, p.getName());
			pStmt.setString(2, p.getDescr());
			pStmt.setDouble(3, p.getNormalPrice());
			pStmt.setDouble(4, p.getMemberPrice());
			pStmt.setTimestamp(5, p.getPdate());
			pStmt.setInt(6, p.getCategoryid());
			pStmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
			return false;

		}
		return true;
	}

	@Override
	public int getPageCount(int pageSize) {
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select count(*) from product p join category c on p.categoryid = c.id";
		ResultSet rs = DB.getRs(stmt, sql);
		int total = 0;
		try {
			rs.next();
			total = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

	}

	@Override
	public Product getProduct(int id) {
		Product p = null;
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from Product where id=" + id;
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			if(rs.next()) {
				p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescr(rs.getString("descr"));
				p.setNormalPrice(rs.getDouble("normalprice"));
				p.setMemberPrice(rs.getDouble("memberprice"));
				p.setCategoryid(rs.getInt("categoryid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(rs);
			DB.close(conn);
		}
		return p;
	}

	@Override
	public List<Product> getLatestProduct(int count) {
		List<Product> products = new ArrayList<Product>();
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select * from product limit 0," + count;
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescr(rs.getString("descr"));
				p.setCategoryid(rs.getInt("categoryid"));
				p.setMemberPrice(rs.getDouble("memberprice"));
				p.setNormalPrice(rs.getDouble("normalprice"));
				p.setPdate(rs.getTimestamp("pdate"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return products;
	}

}
