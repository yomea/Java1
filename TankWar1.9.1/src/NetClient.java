import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetClient {
	public int udpPort;
	
	public NetClient(int udpPort) {
		this.udpPort = udpPort;
	}
	public void Connect(String ip, int port) {
		
		System.out.println(UDPPort.portList.size());
		Socket s = null;
		try {
			s = new Socket(ip,port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpPort);
			
			DatagramSocket ds = new DatagramSocket(udpPort);
			
		
//System.out.println("connect!!");
		} catch (UnknownHostException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				
				
				s.close();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
	}
}
