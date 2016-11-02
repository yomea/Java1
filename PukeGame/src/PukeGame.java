import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PukeGame {
	
	private static List<Puke> pukeBox = new ArrayList<Puke>(); 
	List<Persion> twoPerson = new ArrayList<Persion>();
	List<Puke> lp = new ArrayList<Puke>();
	private static String[] s = {"3","4","5","6","7","8","9","10","J","Q","K","A","2",};
	private static String[] flower = {"方片","梅花","黑桃"}; 
	
	static {
		for(int i = 0; i < s.length; i++) {
			for(int j = 0; j < flower.length; j++) {
				pukeBox.add(Puke.create(s[i],flower[j]));
			}
		}
	}
	
	public static void main(String[] args) {
		PukeGame pg = new PukeGame();
		pg.listPuke();
		pg.createPer();
		pg.listPerson();
		pg.send();
		pg.getMax();
		
	}
	
	private void listPuke() {
		System.out.println(pukeBox.size());
		for (Puke puke : pukeBox) {
			System.out.println(puke.getMessage());
		}
	}
	
	private void createPer() {
		
		Scanner sc = new Scanner(System.in);
		int id = 0;
		for(int i = 0; i < 2; i++) {
			while(true) {
				boolean over = false;
				System.out.println("请输入你的ID");
				try{
					id = sc.nextInt();
				}catch (Exception e) {
					System.out.println("请输入整数。。。。");
					continue;
				}
				
				for(Persion persion : twoPerson) {
					if(persion.getId() == id) {
						System.out.println("id重复，重输。。");
						over = true;
					}
				}
				if(over) {
					continue;
				}
				System.out.println("请输入你的name");
				String name = sc.next();
				twoPerson.add(Persion.create(id,name));
				break;
			}
			}
			
	}
	
	
	
	private void listPerson() {
		for (Persion puke : twoPerson) {
			System.out.println(puke.getMessage());
		}
	}
	
	private void send() {
		int k = 0;
		Iterator<Puke> it = pukeBox.iterator();
		Collections.shuffle(pukeBox);
		for(int i = 0; i < 4; i++) {
			Puke pk = it.next();
			if(k%2 == 0) {
				twoPerson.get(0).addPuke(pk);
				System.out.println(twoPerson.get(0).getName()+"拿牌"+"    ");
			}else {
				twoPerson.get(1).addPuke(pk);
				System.out.println(twoPerson.get(1).getName()+"拿牌"+"    ");
			}
			k++;
			
		}
		
	}
	
	private void getMax() {
		for(int i = 0; i < twoPerson.size(); i++) {
			Persion p = twoPerson.get(i);
			System.out.println(p.getName()+"的手牌是：");
			p.forSet();
			p.sortList();
			System.out.println(p.getName()+"最大的手牌"+p.getSet().get(1).getMessage());
			lp.add(p.getSet().get(1));
		}
		if(lp.get(0).getMessage().equals(lp.get(1).getMessage())) {
			System.out.println("平局");
		}else {
			Collections.sort(lp);
			System.out.println(lp.get(1).getMessage()+"获胜");
			Scanner ssc = new Scanner(System.in);
			System.out.println("继续吗？yes or no");
			String s = ssc.next();
			if(s.equals("yes")) {
				for(int i = 0; i < twoPerson.size(); i++) {
					Persion p = twoPerson.get(i);
					p.getSet().clear();
					}
				this.send();
				this.getMax();
			}
			//System.out.println(lp.get(1).getMessage().equals(lp.get(1).getMessage()));
			
		}
		
		
	}
	
	

}
