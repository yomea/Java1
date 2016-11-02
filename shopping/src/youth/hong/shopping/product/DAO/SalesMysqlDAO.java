package youth.hong.shopping.product.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import youth.hong.shopping.Cart;
import youth.hong.shopping.CartItem;
import youth.hong.shopping.Category;
import youth.hong.shopping.DB;
import youth.hong.shopping.SalesItem;
import youth.hong.shopping.SalesOrder;
import youth.hong.shopping.User;
import youth.hong.shopping.product.Product;

public class SalesMysqlDAO {

	public boolean saveOrder(SalesOrder so) {

		Connection conn = DB.getConn();
		String sql = "insert into salesorder values (null,?,?,?,?)";
		PreparedStatement pStmt = DB.getPStmt(conn, sql, true);
		try {
			conn.setAutoCommit(false);
			pStmt.setInt(1, so.getUser().getId());
			pStmt.setString(2, so.getAddr());
			pStmt.setTimestamp(3, so.getoDate());
			pStmt.setInt(4, so.getStatus());
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			rs.next();
			int key = rs.getInt(1);
			Cart c = so.getCart();
			List<CartItem> items = c.getItem();
			String sqlItem = "insert into salesitem values (null,?,?,?,?)";
			PreparedStatement ip = DB.getPStmt(conn, sqlItem);
			for (int i = 0; i < items.size(); i++) {
				CartItem ci = items.get(i);
				ip.setInt(1, ci.getProductId());
				ip.setDouble(2, ci.getPrice());
				ip.setInt(3, ci.getCount());
				ip.setInt(4, key);
				ip.addBatch();
			}
			ip.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {

			e.printStackTrace();
			try {
				conn.setAutoCommit(true);
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;

		}
		return true;
	}

	public int getOrders(List<SalesOrder> list, int pageNo, int pageSize) {
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		int pageCount = 0;
		String sql = "select u.id uid,u.username uun,"
				+ "u.phone up, u.addr ua, u.password upd, "
				+ "s.id sid, s.userid su, s.addr sa,s.odate so, "
				+ "s.status ss from user u right join salesorder s on (u.id = s.userid) limit "
				+ (pageNo - 1) * pageSize + "," + pageSize;
		// System.out.println(sql);
		Statement cStmt = DB.getStmt(conn);
		String cSql = "select count(*) from user u join salesorder s on (u.id = s.userid)";
		ResultSet cRs = DB.getRs(cStmt, cSql);
		try {
			cRs.next();
			pageCount = cRs.getInt(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("uid"));
				u.setUsername(rs.getString("uun"));
				u.setPhone(rs.getString("up"));
				u.setAddress(rs.getString("ua"));
				u.setPassword(rs.getString("upd"));
				SalesOrder so = new SalesOrder();
				so.setId(rs.getInt("sid"));
				so.setUser(u);
				so.setStatus(rs.getInt("ss"));
				so.setAddr(rs.getString("sa"));
				so.setoDate(rs.getTimestamp("so"));
				list.add(so);
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

	public List<SalesItem> getSalesItems(SalesOrder so) {
		List<SalesItem> list = new ArrayList<SalesItem>();
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select p.id pid,p.name pn,p.descr pd, p.normalprice pnp, p.memberprice pmp, p.pdate pp, p.categoryid pc"
				+ ",ssm.id ssmid,ssm.productid ssmp,"
				+ "ssm.unitprice ssmu, ssm.pcount ssmpt, ssm.orderid ssmo,"
				+ " ssd.id ssdid, ssd.userid ssdu, ssd.addr ssda,ssd.odate ssdo, "
				+ "ssd.status ssds from salesitem ssm join salesorder ssd on (ssm.orderid = ssd.id) "
				+ "join product p on (ssm.productid = p.id) where ssd.id=" + so.getId();
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("pid"));
				p.setName(rs.getString("pn"));
				p.setDescr(rs.getString("pd"));
				p.setNormalPrice(rs.getDouble("pnp"));
				p.setMemberPrice(rs.getDouble("pmp"));
				p.setPdate(rs.getTimestamp("pp"));
				p.setCategoryid(rs.getInt("pc"));
				SalesItem si = new SalesItem();
				si.setOrder(so);
				si.setpCount(rs.getInt("ssmpt"));
				si.setProduct(p);
				si.setUnitPrice(rs.getDouble("ssmu"));
				list.add(si);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return list;
	}

	public SalesOrder getSalesOrderById(int id) {
		SalesOrder so = null;
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "select u.id uid,u.username uun,"
				+ "u.phone up, u.addr ua, u.password upd, "
				+ "s.id sid, s.userid su, s.addr sa,s.odate so, "
				+ "s.status ss from user u right join salesorder s on (u.id = s.userid) where s.id=" + id;
		//System.out.println(sql);
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			if(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("uid"));
				u.setUsername(rs.getString("uun"));
				u.setPhone(rs.getString("up"));
				u.setAddress(rs.getString("ua"));
				u.setPassword(rs.getString("upd"));
				so = new SalesOrder();
				so.setId(rs.getInt("sid"));
				so.setUser(u);
				so.setStatus(rs.getInt("ss"));
				so.setAddr(rs.getString("sa"));
				so.setoDate(rs.getTimestamp("so"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return so;
	}

	public boolean modify(int id, int status) {
		Connection conn = DB.getConn();
		Statement stmt = DB.getStmt(conn);
		String sql = "update salesorder set status=" + status + " where id=" + id;;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
		return true;
	}

}
