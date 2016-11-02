import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {
	private List<Curse> curses = new ArrayList<Curse>();
	private Scanner sc = new Scanner(System.in);
	private List<Student> st = new ArrayList<Student>();
	private static String option[] = {"addCurses","createStudent","exit","listStudentMessage","listCursesMessage","removeStudent","selectCurse","help"};
	private BufferedReader br = null;
	public static void main(String[] agrs) {
		Manager mr = new Manager();
		mr.addDefaultCurses();
		mr.chooceFunction();
	}
	
	public void chooceFunction() {
		System.out.println("Plase input command !");
		String s = sc.next();
		if(s.equalsIgnoreCase(option[0])) {
			this.addCurses();
		}else if(s.equalsIgnoreCase(option[1])) {
			this.createAStudent();
		}else if(s.equalsIgnoreCase(option[2])) {
			System.exit(0);
		}else if(s.equalsIgnoreCase(option[3])) {
			this.outputStudentMessage();
		}else if(s.equalsIgnoreCase(option[4])) {
			this.outputCursesMessage();
		}else if(s.equalsIgnoreCase(option[5])) {
			this.removeStudent();
		}else if(s.equalsIgnoreCase(option[6])) {
				this.selectCurse();
		}else if(s.equalsIgnoreCase(option[7])) {
			this.optionHelp();
		}else {
			System.out.println("error command !");
			this.chooceFunction();
		}
	}
	
	private void selectCurse() {
		boolean exist = false;
		System.out.println("���������ѧ��");
		String s = sc.next();
		if(s.equalsIgnoreCase("exit")) {
			this.chooceFunction();
		}
		for(Student student:st) {
			if(student.getId().equals(s)) {
				this.studentAddCurse(student);
				exist = true;
			}
		}
		
		this.creatYesOrNot(exist);
		
		
	}
	
	private void creatYesOrNot(boolean exist) {
		if(!exist) {
			System.out.println("����˺Ų����ڣ��Ƿ񴴽���yes or not or exit");
			String ss = sc.next();
			if(ss.equalsIgnoreCase("exit")) {
				this.chooceFunction();
			}
			if(ss.equalsIgnoreCase("yes")) {
				this.studentOption();
			}else if(ss.equalsIgnoreCase("not")) {
				System.out.print("��");
				this.selectCurse();
			}else {
				System.out.println("ָ����󣡣���");
				this.creatYesOrNot(exist);
			}
		}
	}

	private void studentOption() {
		boolean  swt = true;
		
		System.out.println("Plase input your id and name to create password");
		System.out.println("Plase input your id");
		String id = sc.next();
		if(id.equalsIgnoreCase("exit")) {
			this.chooceFunction();;
		}
		System.out.println("Plase input your name");
		String name = sc.next();
		if(id.equalsIgnoreCase("exit")) {
			this.chooceFunction();;
		}
		st.add(Student.create(id, name));
		System.out.println(st.size());
		
		
		this.selectCurse();
	}
	
	private void studentAddCurse(Student student) {
		for(Curse s : curses) {
			System.out.println(s.getMessage());
		}
		boolean success = false;
		System.out.println("��������Ҫ��ӿγ̵����");
		String s = sc.next();
		for(Curse curse : curses) {
			if(curse.getId().equals(s)) {
				boolean ride = student.sreach(curse);
				if(ride) {
					System.out.println("�벻Ҫ�ظ�ѡ��γ�");
					this.studentAddCurse(student);
				}
				student.addCurseSelect(curse);
				success = true;
			}
		}
		if(success) {
			System.out.println("��ӳɹ�������");
			this.listStudentCurseMessage(student);
		}else {
			System.out.println("ʧ�ܣ�����");
			this.studentAddCurse(student);
		}
		
	}
	
	private void listStudentCurseMessage(Student student) {
		System.out.println("���Ѿ��ɹ���ӵĿγ����£�");
		for(Curse curse : student.getCurseSelect()) {
			System.out.println(curse.getMessage());
		}
		System.out.println("������yes or no or exit");
		String s = sc.next();
		if(s.equalsIgnoreCase("exit")) {
			this.chooceFunction();
		}//////////////////////////////////////////////////////////////////////
		this.chooceFunction();
	}

	private void optionHelp() {
		try {
			br = new BufferedReader(new FileReader("D:\\java-workspace\\TestCollection\\src\\help\\help.txt"));
			String s = "";
			while((s = br.readLine()) != null) {
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Sorry,can not found help message!");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		this.chooceFunction();
		
	}
	
	private void addDefaultCurses() {
		curses.add(Curse.create("A", "���ݽṹ"));
		curses.add(Curse.create("B", "C����"));
		curses.add(Curse.create("C", "JAVA"));
		curses.add(Curse.create("D", "C++"));
		curses.add(Curse.create("E", "�������"));
		curses.add(Curse.create("F", "����ϵͳԭ��"));
		curses.add(Curse.create("G", "javaScript 300��"));
	}
	
	private void addCurses() {
		boolean swt = true;
		while(swt) {
			System.out.print("Plase input curses' id and name to create curseList");
			System.out.println("Plase input curse' id");
			String id = sc.next();
			if(id.equalsIgnoreCase("exit")) {
				break;
			}
			System.out.println("Plase input curse' name");
			String name = sc.next();
			if(name.equalsIgnoreCase("exit")) {
				break;
			}
			curses.add(Curse.create(id, name));
			
		}
		
		this.chooceFunction();
		
	}
	
	private void outputCursesMessage() {
		for(Curse s : curses) {
			System.out.println(s.getMessage());
		}
		this.chooceFunction();
	}
	
	private void outputStudentMessage() {
		for(Student s : st) {
			System.out.println(s.getMessage());
		}
		this.chooceFunction();
	}
	
	private void createAStudent() {
		boolean  swt = true;
		while(swt) {
		System.out.println("Plase input your id and name to create password");
		System.out.println("Plase input your id");
		String id = sc.next();
		if(id.equalsIgnoreCase("exit")) {
			break;
		}
		System.out.println("Plase input your name");
		String name = sc.next();
		if(id.equalsIgnoreCase("exit")) {
			break;
		}
		st.add(Student.create(id, name));
		
		}
		this.chooceFunction();
	}
	
	private void removeStudent() {
		boolean success = false;
		System.out.println("Plase input id to remove the student or input exit to back");
		String s = sc.next();
		if(s.equalsIgnoreCase("exit")) {
			this.chooceFunction();
		}
		for(Student student : st) {
			if(student.getId().equalsIgnoreCase(s)) {
				st.remove(s);
				success = true;
			}
		}
		if(success) {
			System.out.println("option success");
		}else {
			System.out.println("���󲻴��ڣ�����ѡ��ɾ���Ķ��󣡣���");
			removeStudent();
		}
		
		this.chooceFunction();
	}
}
