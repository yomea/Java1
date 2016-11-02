package youth.hong.TestSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable{
	
	Socket socket = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	
	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = null;
			while((info = br.readLine()) != null) {
				System.out.println(info);
			}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				br.close();
				is.close();
				isr.close();
				socket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	
}
