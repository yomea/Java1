package youth.hong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TestHTTP {

	public static void main(String[] args) {
		Socket s = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			s = new Socket("localhost",8888);
			pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			pw.println("GET / HTTP/1.1");
			pw.println("Host:localhost");
			pw.println("Content-Type:text/html");
			pw.println();
			pw.flush();
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String code = "";
			while((code = br.readLine()) != null) {
				System.out.println(code);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if(s != null) {
					s.close();
					s = null;
				}
				if(br != null) {
					br.close();
					br = null;
				}
				if(pw != null) {
					pw.close();
					pw = null;
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}

}
