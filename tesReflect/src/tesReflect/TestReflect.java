package tesReflect;

public class TestReflect {
	public static void main(String[] agrs) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String str = "TT";
		
		Class c = Class.forName(str);
		
		// c.newInstance();
		
	}
}

class TT {
	static {
		System.out.println("gg");
	}
	int i = 100;
	public void m() {
		System.out.println("mm");
		
	}
	
}
