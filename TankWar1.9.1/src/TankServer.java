import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TankServer {
	
	public static final int TCP_PORT = 8888;
	
	private List<Client> clients = new ArrayList<Client>();
	
	
	public void start() {
		Socket s = null;
		try {
			ServerSocket ss = new ServerSocket(TCP_PORT);
			
			while(true) {
				s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				int port = dis.readInt();
				Client c = new Client(s.getInetAddress().getHostAddress(), port);
				clients.add(c);
System.out.println("a client connect:"+s.getInetAddress()+":"+port);
			}
		} catch (IOException e) {
	
			e.printStackTrace();
		}finally {
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				s = null;
			}
		}
	}
	
	public static void main(String[] agrs) {
		
		new TankServer().start();
	}

	private class Client{
		String ip;
		int udp_port;
		
		public Client(String ip, int udp_port) {
			this.ip = ip;
			this.udp_port = udp_port;
		}
	}
	
}
