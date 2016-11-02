package youth.hong.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import youth.hong.gilr.Girl;
import youth.hong.mgr.GirlMgr;

public class Control {
	Scanner in = null;

	public static void main(String[] args) {
		
		new Control().chanceGirl();
	}
	
	

	private void chanceGirl() {
		FileInputStream f = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		System.out.println("���������ѡ����");
		File file = new File("D:/help.txt");
		try {
			f = new FileInputStream(file);
			isr = new InputStreamReader(f);
			br = new BufferedReader(isr);
			String str = "";
			while ((str = br.readLine()) != null) {

				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		in = new Scanner(System.in);
		int sc = 0;
		try {
			sc = Integer.parseInt(in.next());

		} catch (NumberFormatException e) {
			System.out.println("!!!!!!!!!!!!!!����������!!!!!!!!!!!!!!!");
			this.chanceGirl();
			return;
		}
		if (1 == sc) {
			this.delOrQuery(false);
			this.chanceGirl();
			return;
		}
		else if(2 == sc) {
			this.addGirls();
		}
		else if(3 == sc) {
			this.delOrQuery(true);
			this.chanceGirl();
			return;
		}
		else if(4 == sc) {
			this.updateGirl();
			this.chanceGirl();
			return;
		}
		else if(5 == sc) {
			System.exit(0);
		}
		else {
			System.out.println("�޴���");
			this.chanceGirl();
			return;
		}
	}
	
	private String[] parseUpdateStr(String str) {
		String[] sstr = str.split(",");
		return sstr;
	}
	
	private void updateGirl() {
		System.out.println("ѡ��Ҫ���ĵ�Ů��,������id,all��ʾ�г�����Ů��");
		List<Girl> gs = GirlMgr.getGm().getGirls();
		List<Girl> gss = new ArrayList<Girl>();
		String parseStr = null;
		int id = 0;
		String str = null;
		while(true) {
			boolean check = false;
			try {
				str = in.next();
				id = Integer.parseInt(str);
			} catch(NumberFormatException e) {
				if(str.equals("all")) {
					
						
							
					for (Girl g : gs) {
						System.out.println(g.getId() + "," + g.getUsername() + "," + g.getAge() + "," + g.getBirthday() + ","
								+ g.getEmail() +"," + g.getMobile());
					}
						System.out.println("ѡ��id");
						while(true) {
							
							try {
								id = Integer.parseInt(in.next());
							} catch(NumberFormatException e1) {
								System.out.println("---------��������---------");
								continue;
							}
							break;
						}
						break;
					}
					
				
				System.out.println("-----����id��all-----");
				continue;
			}
			boolean tad = true;
			for (Girl girl : gs) {
				
				if(girl.getId() == id) {
					gss.add(girl);
					parseStr = girl.getId() + "," + girl.getUsername() + "," + girl.getAge() + "," + girl.getBirthday() + ","
							+ girl.getEmail() +"," + girl.getMobile();
						System.out.println(parseStr);
					tad = false;
				} 
			}
			if(tad) {
				System.out.println("not exist");
				check = true;
			}
			if(check) {
				continue;
			}
			break;
		}
		System.out.println("����ǰѡ��");
		for (Girl girl : gs) {
			
			if(girl.getId() == id) {
				gss.add(girl);
				parseStr = girl.getUsername() + "," + girl.getAge() + "," + new SimpleDateFormat("yyyy-MM-dd").format(girl.getBirthday()) + ","
						+ girl.getEmail() +"," + girl.getMobile();
					System.out.println(parseStr);
				
			} 
		}
		System.out.println("�����޸�");
		String daf = in.next();
		String[] hehe = this.parseUpdateStr(daf);
		
		String name = hehe[0];
		int age = Integer.parseInt(hehe[1]);
		Timestamp birthday = null;
		try {
			birthday = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(hehe[2]).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		String email = hehe[3];
		String mobile = hehe[4];
		
		Girl g = new Girl();
		g.setUser_name(name);
		g.setAge(age);
		g.setBirthday(birthday);
		g.setEmail(email);
		g.setMobile(mobile);
		g.setId(id);
		
		System.out.println("success");
		GirlMgr.getGm().update(g);
	
	}
	
	private void addGirls() {
		System.out.println("��������Ҫ���Ů���ĸ���");
		int count = 0;
		while(true) {
			try {
				count = Integer.parseInt(in.next());
			} catch(NumberFormatException e) {
				System.out.println("-----��������-----");
				continue;
			}
			break;
		}
		for(int i = 0; i < count; i++) {
	
		System.out.println("name:");
		String name = in.next();
		System.out.println("age:");
		int age = 15;
		while(true) {
			
			try {
				age = Integer.parseInt(in.next());
				if(age > 25 || age < 15) {
					System.out.println("Ů������15~25");
					continue;
				}
			} catch(NumberFormatException e) {
				System.out.println("����������");
				continue;
			}
			break;
		}
		System.out.println("birthday:");
		Timestamp birthday = null;
		while(true) {
			try {
				String str = in.next();
				birthday = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(str).getTime());
			} catch(Exception e) {
				System.out.println("��ʽ����");
				continue;
			}
			break;
		} 
		
		System.out.println("email:");
		String email = in.next();
		System.out.println("mobile");
		String mobile = in.next();
		
		Girl g = new Girl();
		g.setUser_name(name);
		g.setAge(age);
		g.setBirthday(birthday);
		g.setEmail(email);
		g.setMobile(mobile);
		GirlMgr.getGm().addGirl(g);
		
		}
		System.out.println("SUCCESS");
		this.chanceGirl();
	}
	
	private void delOrQuery(boolean delOrQuery) {
		int[] id = null;
		String[] name = null;
		int[] age = null;
		Timestamp[] birthday = null;
		String email = null;
		String mobile = null;
		if(delOrQuery) {
			System.out.println("ѡ��ɾ�����ܸ���");
		} else {
			System.out.println("ѡ���ѯ���ܸ���");
			
		}
		System.out.println("ѡ�������");
		System.out.println("1,id\n2,name\n3,age\n4,birthday\n5,eamil\n6,mobile");
		int[] fun = null;
		try {
			int count = Integer.parseInt(in.next());
			if(count > 6 || count < 0) {
				System.out.println("!!!!!!!!!!!!!!0~~~6!!!!!!!!!!!!!!!");
				this.delOrQuery(delOrQuery);
				return;
			}
			fun = new int[count];
			this.setChance(0, count, fun);
		} catch (NumberFormatException e) {
			System.out.println("!!!!!!!!!!!!!!����������!!!!!!!!!!!!!!!");
			this.delOrQuery(delOrQuery);
			return;
		}
		
		for(int i = 0; i < fun.length; i++) {
			
			if(1 == fun[i]) {
				id = this.queryid();
			}
			else if(2 == fun[i]) {
				name = this.queryName();	
			}
			else if(3 == fun[i]) {
				age = this.queryAge();
			}
			else if(4 == fun[i]) {
				birthday = this.queryBirthday();
			}
			else if(5 == fun[i]) {
				email = this.queryEamil();
			}
			else if(6 == fun[i]) {
				mobile = this.queryMobile();
			} 
			
		}
		if(age == null) {
			age = new int[2];
			age[0] = -1;
			age[1] = -1;
		}
		if(birthday == null) {
			birthday = new Timestamp[2];
			birthday[0] = null;
			birthday[1] = null;
		}
		//��������Ҳ���Զ���ΪList<Map<String,Object>>����������
		List<Girl> girls = GirlMgr.getGm().delOrQueryGirls(id, name, age[0], age[1], birthday[0], birthday[1], email, mobile, delOrQuery);
		this.writeGirlInfo(girls);
	}
	
	private String[] queryName() {
		
		System.out.println("name:(�ɲ���,ģ����ѯ��������Ҫ����ĸ���)");
		String[] name = this.setNameArray();
		return name;
		
	}
	
	private int[] queryAge() {
		int[] age = this.parseAge();
		return age;
	}
	
	private Timestamp[] queryBirthday() {
		Timestamp[] birthday = this.parseBirthday();
		return birthday;
	}
	
	private String queryEamil() {
		System.out.println("email:(�ɲ���ģ����ѯ)");
		String email = in.next();
		return email;
	}
	
	private String queryMobile() {
		System.out.println("mobile:(�ɲ���ģ����ѯ)");
		String mobile = in.next();
		return mobile;
	}
	
	private int[] queryid() {
		int[] id = null;
		while(true) {
			System.out.println("id:(���������Ҫ����id�ĸ���)");
			int count = 0;
			
			try {
				count = Integer.parseInt(in.next());
				id = new int[count];
				this.setIdArray(0, count, id);
				break;
			} catch (NumberFormatException e) {
				System.out.println("!!!!!!!!!!!!!!����������!!!!!!!!!!!!!!!");
				continue;
				
			}
		}
		

		return id;
	}
	
	private void writeGirlInfo(List<Girl> girls) {
		for (Girl g : girls) {
			System.out.println("[" + g.getId() + "," + g.getUsername() + "," + g.getAge() + "," + g.getBirthday() + ","
					+ g.getEmail() +"," + g.getMobile() + "]");
		}
		
		
	}

	private Timestamp[] parseBirthday() {
		Timestamp[] birthday = null;
		;
		while (true) {

			System.out.println("birthday:(�ɲ���eg:1990-01-01~1998-12-31)");
			String str = in.next();
			String[] birthdayStr = str.split("~");
			// System.out.println(ageStr.length);
			birthday = new Timestamp[2];
			// System.out.println(ageStr[0]);
			try {
				if (birthdayStr.length != 2) {
					System.out.println("�����Ϲ淶");
					continue;

				}
				birthday[0] = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr[0]).getTime());
				birthday[1] = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr[1]).getTime());
				break;
			} catch (ParseException e) {
				System.out.println("�����Ϲ淶");
				continue;
			}
		}
		return birthday;
	}

	private int[] parseAge() {
		int[] ageint = null;
		while (true) {

			System.out.println("age:(�ɲ��� eg:20~25)");
			String age = in.next();
			String[] ageStr = age.split("~");
			// System.out.println(ageStr.length);
			ageint = new int[2];
			// System.out.println(ageStr[0]);
			try {
				if (ageStr.length != 2) {
					System.out.println("�����Ϲ淶");
					continue;

				}
				ageint[0] = Integer.parseInt(ageStr[0]);
				ageint[1] = Integer.parseInt(ageStr[1]);
				break;
			} catch (NumberFormatException e) {
				System.out.println("�����Ϲ淶");
				continue;
			}
		}
		return ageint;
	}

	private String[] setNameArray() {
		String[] name = null;
		while(true) {
			int count = 0;
			
			try {
				count = Integer.parseInt(in.next());
				name = new String[count];
				for (int i = 0; i < count; i++) {
					System.out.println("�������" + (i + 1) + "��name");
					name[i] = in.next();
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("!!!!!!!!!!!!!!����������!!!!!!!!!!!!!!!");
				continue;
			}
		}
		
		return name;
	}

	private int[] setIdArray(int i, int count, int id[]) {

		try {
			for (; i < count; i++) {
				System.out.println("�������" + (i + 1) + "��id");
				id[i] = Integer.parseInt(in.next());
			}
		} catch (NumberFormatException e) {
			System.out.println("!!!!!!!!!!!!!!����������!!!!!!!!!!!!!!!");
			this.setIdArray(i, count, id);
		}

		return id;
	}
	
	private int[] setChance(int i, int count, int id[]) {
		
		try {
			for (; i < count; i++) {
				System.out.println("�������" + (i + 1) + "��");
				int checkData = 0;
				
				while(true) {
					boolean check = false;
					checkData = Integer.parseInt(in.next());
					for(int j = 0; j < i; j++) {
						if(checkData == id[j]) {
							
							check = true;
						}
					}
					if(check) {
						System.out.println("��Ҫ�ظ�Ŷ");
						continue;
					}
					if(checkData < 0 || checkData > 6) {
						System.out.println("?????????0~~~~~6????????????");
						continue;
					} else {
						break;
					}
				}
				
				
				id[i] = checkData;
				
			}
		} catch (NumberFormatException e) {
			System.out.println("!!!!!!!!!!!!!!����������!!!!!!!!!!!!!!!");
			this.setChance(i, count, id);
		}

		return id;
	}
}
