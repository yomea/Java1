import java.io.Serializable;

/**
 * @author May
 *
 */
public class Studendt implements Serializable{
	private String name;
	private String xuehao;
	private int age;
	public Studendt(String name, String xuehao, int age) {
		super();
		this.name = name;
		this.xuehao = xuehao;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Studendt [name=" + name + ", xuehao=" + xuehao + ", age=" + age + "]";
	}
	
	
	
	
 }
