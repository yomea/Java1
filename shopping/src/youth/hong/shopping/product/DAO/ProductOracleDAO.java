package youth.hong.shopping.product.DAO;

import java.util.Date;
import java.util.List;

import youth.hong.shopping.product.Product;

public class ProductOracleDAO implements ProductDAO{
	public List<Product> getProduct() {
		return null;
	}
	
	public List<Product> getProduct(int pageNo, int pageSize) {
		return null;
	}
	
	public int findProduct(List<Product> products,int[] categoryId, 
									 String keyWord, 
									 double lowNormalPrice, 
									 double highNormalPrice, 
									 double lowMemberPrice,
									 double highMemberPrice,
									 Date startDate,
									 Date endDate,
									 int pageNo,
									 int pageSize) {
		return 0;
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
		return false;
	}

	@Override
	public boolean addProduct(Product p) {
		return false;
	}

	@Override
	public int getPageCount(int pageSize) {
		return 0;
	}

	@Override
	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getLatestProduct(int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
