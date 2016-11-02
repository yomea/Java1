package youth.hong.InetTest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {

	public static void main(String[] args) {
		try {
			/*InetAddress address = InetAddress.getLocalHost();
			System.out.println(address.getHostAddress());
			System.out.println(address.getHostName());*/
			//InetAddress address = InetAddress.getByName("may");
			InetAddress address = InetAddress.getByName("10.106.78.157");
			System.out.println(address.getHostName());
			System.out.println(address.getHostAddress());
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}

	}

}
