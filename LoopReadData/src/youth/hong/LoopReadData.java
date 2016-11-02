package youth.hong;

import java.util.Scanner;

public class LoopReadData {
	public static final String ADD = "a";
	public static final String query = "q";
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("请选择个功能");
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
					///////setName详细看java之路中的jdbc里的程序
					System.out.println("age");
					
					
					
					
				}else if (step == 3) {
					//setAge
					System.out.println("ok");
				}
				if(previous.equals(ADD)) {
					step++;
				}
			} else if(in.equals(query)) {
				System.out.println("请输入查询");
				previous = query;
			}else {
				System.out.println("就这样吧");
			}
		}
	}
}
