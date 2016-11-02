import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestObjectStream {
	public static void main(String[] agrs) throws FileNotFoundException, IOException, ClassNotFoundException {
		String file = "D:/javaio/testObject.dat";
		/*ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(file));
		Studendt st = new Studendt("ºê¸ç","11311010106",22);
		oos.writeObject(st);*/
		
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(file));
		Studendt ss = (Studendt)ois.readObject();
		System.out.println(ss);
	}
}
