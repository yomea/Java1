package youth.hong.TestSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestTCPClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost",8888);
			OutputStream is = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(is);
			int i = 0;
			while( i < 20){
				pw.write("ÎÒÊÇClient");
				pw.flush();
				i++;
			}
				
			
		
			pw.close();
			is.close();
			socket.close();
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}

}
