package youth.hong.TestSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTCPSocket {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8888);
			System.out.println("µÈ´ýÁ¬½Ó£¡£¡£¡");
			
			while(true) {
				Socket socket = serverSocket.accept();
				new Thread(new ClientThread(socket)).start();
			}
			
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
