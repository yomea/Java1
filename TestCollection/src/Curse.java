
public class Curse {
	private String id;
	private String name;
	
	private Curse(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Curse create(String id, String name) {
		return new Curse(id,name);
	}
	
	public String getMessage() {
		return "curseID:"+this.id+"\n"+"curseName:"+this.name;
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
		if(!(obj instanceof Curse) ) {
			return false;
		}
		
		Curse curse = (Curse) obj;
		if(curse.id == this.id && curse.name == this.name) {
			return true;
		}
		
		return false;
	}
	
	
	
}
