import java.util.Scanner;

public class RentBooks {

	public static void main(String[] args) {
		String[] s = {"chinese","math","computer","notebook","sopc"};
		Scanner sc = new Scanner(System.in);
		System.out.println("输入书名或者序号0~5进行搜索书籍");
		sreach(s,sc);

	}

	private static void sreach(String[] s, Scanner sc) {
		boolean flag = false;
		int count = 0;
		while(true) {
			String k = sc.nextLine();
			int n = -1;
			for(int i = 0; i < k.length(); i++) {
				char c = k.charAt(i);
				if(c>='0' && c<='9') {
					n = Character.getNumericValue(c);
					count++;
					
				}
				
			}
			
			if(2 == count) {
				System.out.println("数组下标出界，请重新输入。。。");
				count = 0;
				continue;
			}
			
			try{
				if(n!=-1) {
					System.out.println(s[n]);
				}else {
					for(int i = 0; i < s.length; i++) {
						if(k.equalsIgnoreCase(s[i])) {
							System.out.println(s[i]);
							flag = true;
						}
						if(k.equalsIgnoreCase("exit")) {
							System.out.println("退出");
							System.exit(0);
						}
					}
					if(!flag) {
						try {
							throw new BookNotFoundException("BookNotFoundException");
						} catch (BookNotFoundException e) {
							e.printStackTrace();
							System.out.println("未找到目标，请重新搜索。。。");
						}
						
						continue;
					}else flag = false;
				}
					
				
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("数组下标越界，请重新输入。。。。");
				continue;
			}
			
		}
		
	}

}
