import java.util.Scanner;

public class RentBooks {

	public static void main(String[] args) {
		String[] s = {"chinese","math","computer","notebook","sopc"};
		Scanner sc = new Scanner(System.in);
		System.out.println("���������������0~5���������鼮");
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
				System.out.println("�����±���磬���������롣����");
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
							System.out.println("�˳�");
							System.exit(0);
						}
					}
					if(!flag) {
						try {
							throw new BookNotFoundException("BookNotFoundException");
						} catch (BookNotFoundException e) {
							e.printStackTrace();
							System.out.println("δ�ҵ�Ŀ�꣬����������������");
						}
						
						continue;
					}else flag = false;
				}
					
				
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("�����±�Խ�磬���������롣������");
				continue;
			}
			
		}
		
	}

}
