package youth.hong.UDPTest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServerTest {

	
	public void ServerStart() {
		try {
			DatagramSocket socket = new DatagramSocket(8888);
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			while(true) {
				socket.receive(packet);
				Client client = new Client(packet, socket);
				new Thread(client).start();
				String info = new String(data, 0, packet.getLength(), "utf-8");
				info = info.trim();
				System.out.println(info + "----" + packet.getPort());
			}
			
		} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	
	
	public static void main(String[] args) {
		UDPServerTest ust = new UDPServerTest();
		ust.ServerStart();
	}

}
