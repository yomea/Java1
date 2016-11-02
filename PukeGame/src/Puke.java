
public class Puke implements Comparable<Puke>{

	private String id;
	private String name;
	private int index;
	private int flowerIndex;
	private String[] s = {"3","4","5","6","7","8","9","10","J","Q","K","A","2",};
	private String[] flower = {"·½Æ¬","Ã·»¨","ºÚÌÒ"}; 
	private int personId;
	
	private Puke(String id, String name) {
		this.id = id;
		this.name = name;
		this.personId = personId;
		for(int i = 0; i < s.length; i++) {
			if(id == s[i]) {
				index = i;
				break;
			}
		}
		
		for(int i = 0; i < flower.length; i++) {
			if(flower[i].equals(name)) {
				this.flowerIndex = i;
			}
		}

		
	}
	
	
	
	public static Puke create(String id, String name) {
		return new Puke(id,name);
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
	
	public int getIndex() {
		return index;
	}

	public int getFlowerIndex() {
		return flowerIndex;
	}
	
	public String getMessage() {
		return this.name+this.id;
	}

	@Override
	public int compareTo(Puke o) {
		if(this.index > o.getIndex()) {
			return 1;
		}else if(this.index < o.getIndex()) {
			return -1;
		}else {
			if(this.flowerIndex > o.getFlowerIndex()) {
				return 1;
			}else if(this.flowerIndex < o.getFlowerIndex()) {
				return -1;
			}else {
				return 0;
			}
			
		}
				
	}

	
}
