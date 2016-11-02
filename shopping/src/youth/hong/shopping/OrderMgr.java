package youth.hong.shopping;

import java.util.List;

import youth.hong.shopping.product.DAO.SalesMysqlDAO;

public class OrderMgr {
	private SalesMysqlDAO dao;

	private static OrderMgr om;
	
	static {
		om = new OrderMgr();
		om.setDao(new SalesMysqlDAO());
		
	}
	public static OrderMgr getIntance() {
		return om;
	}
	
	public SalesMysqlDAO getDao() {
		return dao;
	}
	
	public void setDao(SalesMysqlDAO dao) {
		this.dao = dao;
	}
	
	public boolean saveOrder(SalesOrder so) {
		return dao.saveOrder(so);
	}
	
	public int getOrders(List<SalesOrder> list, int pageNo, int pageSize) {
		return dao.getOrders(list, pageNo, pageSize);
	}
	
	public List<SalesItem> getSalesItems(SalesOrder so) {
		return dao.getSalesItems(so);
	}
	
	public SalesOrder getSalesOrderById(int id) {
		return dao.getSalesOrderById(id);
	}
	
	public boolean modify(int id, int status) {
		return dao.modify(id, status);
	}
	
	
}
