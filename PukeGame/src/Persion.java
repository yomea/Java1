import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Persion {
	private int id;
	private String name;
	private List<Puke> set = null;
	
	private Persion(int id, String name ) {
		this.id = id;
		this.name = name;
		this.set = new ArrayList<Puke>();
	}
	
	public void addPuke(Puke pk) {
		set.add(pk);
	}
	
	public void sortList() {
		Collections.sort(set);
	}
	
	public List<Puke> getSet() {
		return set;
	}

	public static Persion create(int id, String name) {
		return new Persion(id, name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persion other = (Persion) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public void forSet() {
		for(Puke puke : set) {
			System.out.println(puke.getMessage());
		}
	}
	
	public String getMessage() {
		return this.id + "---" + this.name;
	}
	
}
