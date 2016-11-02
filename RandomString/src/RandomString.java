import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomString {
	
	private List<String> ls = new ArrayList<String>();
	private Random rd = new Random();
	StringBuilder c1 = null;
	
	
	
	private RandomString() {
		c1 = new StringBuilder("abcdefghijklmnopqrstuvwxyz0123456789");
		String s = new String(c1);
		c1.append(s.toUpperCase());
		
	}
	
	public static void main(String[] agrs ) {
		RandomString rs = new RandomString();
		rs.addStringList();
		rs.listString();
		System.out.println("≈≈–Ú∫Û//////////////////////////////////////////////////");
		rs.sortString();
			
	}
	
	private String addString() {
		StringBuilder sb = new StringBuilder();
		int count = rd.nextInt(10)+1;
		for(int i = 0; i < count; i++) {
			int j = rd.nextInt(c1.length());
			sb.append(c1.charAt(j));
		}
		return sb.toString();
	}
	
	private void addStringList() {
		for(int i = 0; i < 10;) {
			String ss = this.addString();
			if(ls.contains(ss)) {
				continue;
			}
			ls.add(ss);
			i++;
			
		}
	}
	
	private void listString() {
		for (String string : ls) {
			System.out.println(string);
		}
	}
	
	private void sortString() {
		Collections.sort(ls);
		this.listString();
	}
}
