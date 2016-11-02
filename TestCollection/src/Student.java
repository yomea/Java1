import java.util.ArrayList;
import java.util.List;

public class Student {
	private String id;
	private String name;
	private List<Curse> curseSelect;
	
	private Student(String id, String name) {
		this.id = id;
		this.name = name;
		curseSelect = new ArrayList<Curse>();
	}
	
	public static Student create(String id, String name) {
		return new Student(id,name);
	}
	
	public String getMessage() {
		return "studentID:"+this.id+"\n"+"studentName:"+this.name;
	}
	
	public void addCurseSelect(Curse c) {
		curseSelect.add(c);
	}
	
	public List<Curse> getCurseSelect() {
		return curseSelect;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Student) ) {
			return false;
		}
		
		Student student = (Student) obj;
		if(student.id == this.id && student.name == this.name) {
			return true;
		}
		
		return false;
	}
	
	public boolean sreach(Curse curse) {
	
			for(Curse c : curseSelect) {
				if(c.getId().equals(this.id)) {
					return true;
				
				}
			}
			
			return false;
		}
	public void listCurseMessage() {
		for(Curse curse : curseSelect) {
			System.out.println(curse.getMessage());
		}
	}
	
}
