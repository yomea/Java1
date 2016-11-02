package youth.hong.shopping.product;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import youth.hong.shopping.product.DAO.ProductDAO;

public class ProductMgr {
	
	private static ProductMgr pm = null;
	
	private static ProductDAO dao = null;
	
	static {
		
		pm = new ProductMgr();
		//使用配置文件的方式选择数据库
		Properties pps = new Properties();
		try {
			pps.load(ProductMgr.class.getClassLoader().getResourceAsStream("config/dao.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String className = pps.getProperty("varDao");
		//System.out.println(className);
		try {
			dao = (ProductDAO)Class.forName(className).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//pm.setDao(new ProductMysqlDAO());
	}
	
	public static ProductMgr getInstance() {
		if(pm == null) {
			pm = new ProductMgr();
		}
		return pm;
	}
	
	public ProductDAO getDao() {
		return dao;
	}
	
	public void setDao(ProductDAO dao) {
		ProductMgr.dao = dao;
	}
	
	public List<Product> getProduct() {
		return dao.getProduct();
	}
	
	public List<Product> getProduct(int pageNo, int pageSize) {
		return dao.getProduct(pageNo, pageSize);
	}
	
	public int findProduct(List<Product> products, int[] categoryId, 
									 String keyword, 
									 double lowNormalPrice, 
									 double highNormalPrice, 
									 double lowMemberPrice,
									 double highMemberPrice,
									 Date startDate,
									 Date endDate,
									 int pageNo,
									 int pageSize) {
		return dao.findProduct(products, categoryId, keyword, lowNormalPrice, highNormalPrice, lowMemberPrice, highMemberPrice, startDate, endDate, pageNo, pageSize);
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
		return dao.updateProduct(p);
	}
	
	public boolean addProduct(Product p) {
		
		return dao.addProduct(p);
	}
	
	public int getPageCount(int pageSize) {
		return dao.getPageCount(pageSize);
	}
	
	public Product getProduct(int id) {
		return dao.getProduct(id);
	}
	
	public List<Product> getLatestProduct(int count) {
		return dao.getLatestProduct(count);
	}
}
 