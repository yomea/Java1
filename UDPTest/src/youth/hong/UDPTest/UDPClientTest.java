package youth.hong.UDPTest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClientTest {
	
	public static void main(String[] args) {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null;
		DatagramSocket socket = null;
		int port = new UDPPort().getPort();
		try {
			InetAddress address = InetAddress.getLocalHost();
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			dos.writeUTF("Œ“ «ƒ„¥Û“Ø");
			DatagramPacket packet = new DatagramPacket(baos.toByteArray(), baos.size(), address, 8888);
			
			socket = new DatagramSocket(port);
			socket.send(packet);
		} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				dos.close();
				baos.close();
				socket.close();
				System.out.println(UDPPort.portList.size());
				UDPPort.portList.remove(new Integer(port));
				
				System.out.println(UDPPort.portList.size());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}

	}

}
