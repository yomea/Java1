package youth.hong.TestDom;

public class Book {
	private String id = "";
	private String className = "";
	private String name = "";
	private String author = "";
	private String year = "";
	public Book(String id, String name, String author, String year) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.year = year;
	}
	
	public Book() {
		
	}
	

	@Override
	public String toString() {
		return "Book [id=" + id + ", className=" + className + ", name=" + name + ", author=" + author + ", year="
				+ year + "]";
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
	
}
