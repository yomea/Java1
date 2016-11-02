package youth.hong.getHTML;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHTML {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.wlmt.date");
//			URL url = new URL("http://www.imooc.com");
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Õı¡÷¬˛Ã∏.html"),"utf-8"));
			String s = br.readLine();
			while (s != null) {
				System.out.println(s);
				bw.write(s);
				bw.newLine();
				s = br.readLine();
			}
			bw.close();
			br.close();
			is.close();
			isr.close();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}finally {
			
		}

	}

}
