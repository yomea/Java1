package youth.hong.shopping;

import java.util.ArrayList;
import static java.lang.Math.*;
import java.util.List;

public class Cart {
	List<CartItem> items = new ArrayList<CartItem>();
	
	public List<CartItem> getItem() {
		return items;
	}
	
	public boolean addItem(CartItem ci) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getProductId() == ci.getProductId()) {
				int count = items.get(i).getCount();
				int nCount = ci.getCount();
				items.get(i).setCount(count+nCount);
				return true;
			}
		}
		
		items.add(ci);
		
		return true;
	}
	
	public double getTotalPrice() {
		double totalPrice = 0;
		for(CartItem ci : items) {
			totalPrice += ci.getTotalPrice();
			
		}
		totalPrice = round(totalPrice*100.0)/100.0;
		return totalPrice;
	}
}
