package youth.hong.UDPTest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client implements Runnable {
	DatagramPacket packet = null;
	int port;
	InetAddress address = null;
	DatagramSocket socket;
	public Client(DatagramPacket packet, DatagramSocket socket) {
		this.packet = packet;
		this.socket = socket;
		this.port = packet.getPort();
		this.address = packet.getAddress();
	}
	
	public void run() {
		
	}
	
}
