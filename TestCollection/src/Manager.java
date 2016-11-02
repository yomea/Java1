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
		System.out.println("请输入你的学号");
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
			System.out.println("你的账号不存在，是否创建，yes or not or exit");
			String ss = sc.next();
			if(ss.equalsIgnoreCase("exit")) {
				this.chooceFunction();
			}
			if(ss.equalsIgnoreCase("yes")) {
				this.studentOption();
			}else if(ss.equalsIgnoreCase("not")) {
				System.out.print("再");
				this.selectCurse();
			}else {
				System.out.println("指令错误！！！");
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
		System.out.println("请输入你要添加课程的序号");
		String s = sc.next();
		for(Curse curse : curses) {
			if(curse.getId().equals(s)) {
				boolean ride = student.sreach(curse);
				if(ride) {
					System.out.println("请不要重复选择课程");
					this.studentAddCurse(student);
				}
				student.addCurseSelect(curse);
				success = true;
			}
		}
		if(success) {
			System.out.println("添加成功！！！");
			this.listStudentCurseMessage(student);
		}else {
			System.out.println("失败，重来");
			this.studentAddCurse(student);
		}
		
	}
	
	private void listStudentCurseMessage(Student student) {
		System.out.println("你已经成功添加的课程如下：");
		for(Curse curse : student.getCurseSelect()) {
			System.out.println(curse.getMessage());
		}
		System.out.println("继续吗？yes or no or exit");
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
		curses.add(Curse.create("A", "数据结构"));
		curses.add(Curse.create("B", "C语言"));
		curses.add(Curse.create("C", "JAVA"));
		curses.add(Curse.create("D", "C++"));
		curses.add(Curse.create("E", "汇编语言"));
		curses.add(Curse.create("F", "操作系统原理"));
		curses.add(Curse.create("G", "javaScript 300例"));
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
			System.out.println("对象不存在！重新选择删除的对象！！！");
			removeStudent();
		}
		
		this.chooceFunction();
	}
}
