import java.util.Scanner;

public class Rent {
	public static void main(String[] agrs) {
		Scanner sc = new Scanner(System.in); 
		System.out.println("Are you want rent Car? Plase input 1 or 0");
		int[] carType = {
			1,2,3,4,5,6,7,8,9	
		};
		while(true) {
		grate(carType,sc);
		}
		
		
	}
	public static void grate(int[] carType, Scanner sc) {
		int input = sc.nextInt();
		
		if(input == 1) {
			int count = 0;
			int[] ad = null;
			int k = 0;
			boolean flag = false;
			System.out.println("���еĳ�����");
			for(int i = 0; i < carType.length; i++) {
				System.out.println(carType[i]);
			}
			System.out.println("��������Ҫѡ��ĳ��ӵ�������");
			count = sc.nextInt();
			while(count > 9) {
				System.out.println("��󲻳���9,���������롣��������");
				count = sc.nextInt();
			}
			ad = new int[count];
			for(int i=0;i<count;i++) {
				System.out.println("�������"+(i+1)+"�������");
				k = sc.nextInt();
				while(k>9) {
					System.out.println("��󲻳���9,���������롣��������");
					k = sc.nextInt();
				}
				ad[i] = k;
				
				
			}
			System.out.println("��������ѡ��ĳ����������Ϣ��");
			for(int i=0; i < ad.length; i++) {
				System.out.println(carType[ad[i]]);
			}
			
		}else if(input == 0) {
			System.out.println("Didn't rent car");
			
		}else if(input == 10) {
			System.exit(0);
		}
		else {
			System.out.println("Plase input 1 or 0");
			grate(carType,sc);
		}
	}
}
