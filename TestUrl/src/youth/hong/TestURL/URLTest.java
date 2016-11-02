package youth.hong.TestURL;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.imooc.com");
			URL url2 = new URL(url,"/index?username=Tom#test");
			System.out.println(url2.getProtocol());
			System.out.println(url2.getHost());
			System.out.println(url2.getPort());
			System.out.println(url2.getFile());
			System.out.println(url2.getPath());
			System.out.println(url2.getQuery());
			System.out.println(url2.getRef());
			System.out.println(url2.getDefaultPort());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}

	}

}
