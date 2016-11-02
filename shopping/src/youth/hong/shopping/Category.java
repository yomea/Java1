package youth.hong.shopping;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Category {
	private int id;
	private int pid;
	private String name;
	private String descr;
	private int cno;
	private int grade;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public static void add(int id, int grade, String name, String descr) {
		Category c = new Category();
		c.setId(-1);
		c.setPid(id);
		c.setCno(0);
		c.setDescr(descr);
		c.setName(name);
		c.setGrade(grade + 1);
		CategoryDAO.save(c);
	}
	public static void addRootCategory(String name, String descr) {
		Category c = new Category();
		c.setName(name);
		c.setId(-1);
		c.setCno(0);
		c.setGrade(1);
		c.setPid(0);
		c.setDescr(descr);
		CategoryDAO.save(c);
	}
	
	public static void delete(int id) {
		Connection conn = CategoryDAO.getConn();
		CategoryDAO.deleteCategories(conn, id);
		DB.close(conn);
	}
	
	public static void setLeaf(int pid) {
		CategoryDAO.setCno(pid);
	}
	
	public static List<Category> getCategories() {
		List<Category> cgs = new ArrayList<Category>();
		Connection conn = CategoryDAO.getConn();
		CategoryDAO.getCategory(conn, cgs, 0);
		DB.close(conn);
		return cgs;
	}
	
	public static void moify(int id, String name, String descr) {
		CategoryDAO.modifyCategory(id, name, descr);
	}
	
	public static Category getCategory(int id) {
		Category c = CategoryDAO.getCategory(id);
		return c;
	}
}
