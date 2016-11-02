package youth.hong.shopping.product.DAO;

import java.util.Date;
import java.util.List;

import youth.hong.shopping.product.Product;

public interface ProductDAO {
	public List<Product> getProduct();
	
	public List<Product> getProduct(int pageNo, int pageSize);
	
	public int findProduct(List<Product> products,int[] categoryId, 
									 String keyWord, 
									 double lowNormalPrice, 
									 double highNormalPrice, 
									 double lowMemberPrice,
									 double highMemberPrice,
									 Date startDate,
									 Date endDate,
									 int pageNo,
									 int pageSize);
	
	public List<Product> findProduct(String name);
	
	public boolean deleteProductByCategoryId(int categoryId);
	
	public boolean deleteProductsById(int[] idArray);
	
	public boolean updateProduct(Product p);
	
	public boolean addProduct(Product p);
	
	public int getPageCount(int pageSize);

	public Product getProduct(int id);

	public List<Product> getLatestProduct(int count);
	
	
}
