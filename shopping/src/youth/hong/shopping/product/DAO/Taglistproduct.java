package youth.hong.shopping.product.DAO;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import youth.hong.shopping.product.Product;
import youth.hong.shopping.product.ProductMgr;

public class Taglistproduct extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		List<Product> products = ProductMgr.getInstance().getProduct();
		String  str = "<html><body><table border='1'><tr><td>name</td></tr>";
		for(Product p : products) {
			str += "<tr><td>" + p.getName() + "</td></tr>";
		}
		str += "</table></body></html>";
		this.getJspContext().getOut().write(str);
	}
	
}
