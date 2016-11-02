import java.io.IOException;
import java.util.Properties;

public class PropertiesM {
private static Properties pps = new Properties();
	
	static {
		try {
			pps.load(TankClient.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		
		return pps.getProperty(key);
	}
}
