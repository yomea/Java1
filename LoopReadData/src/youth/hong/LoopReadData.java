package youth.hong;

import java.util.Scanner;

public class LoopReadData {
	public static final String ADD = "a";
	public static final String query = "q";
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("��ѡ�������");
		String in = null;
		String previous = "";
		int step = 1;
		while(scn.hasNext()) {
			in = scn.next().toString();
			if(in.equals(ADD) || previous.equals(ADD)) {
				previous = ADD;
				if(step == 1) {
					System.out.println("name");
					
				} else if(step == 2) {
					///////setName��ϸ��java֮·�е�jdbc��ĳ���
					System.out.println("age");
					
					
					
					
				}else if (step == 3) {
					//setAge
					System.out.println("ok");
				}
				if(previous.equals(ADD)) {
					step++;
				}
			} else if(in.equals(query)) {
				System.out.println("�������ѯ");
				previous = query;
			}else {
				System.out.println("��������");
			}
		}
	}
}
