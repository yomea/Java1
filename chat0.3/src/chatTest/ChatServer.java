package chatTest;

import java.io.*;

import java.net.*;

public class ChatServer {
	static DataInputStream dis = null;
	static DataOutputStream dos = null;
	
	public static void main(String[] agrs) {
		boolean bl = false;
		Socket s = null;
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(599);
			bl = true;
		}catch(BindException e) {
			System.out.println("������ִ�У�");
			System.exit(0);
		}catch(Exception e) {
			System.out.println("��������ʧ�ܣ�����");
		}
			
		try{
			while(bl) {
				
			boolean bbl = false;
			s = ss.accept();
			bbl = true;
			dis = new DataInputStream(s.getInputStream());
			
			
			while(bbl) {
				
//System.out.println("Connect!");
				try {
				System.out.println(dis.readUTF());
				}catch (EOFException e) {
					System.out.println("�Է��ѶϿ�������");
					break;
				}
		
				}
			s.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(dis!=null) dis.close();
			if(s!=null) s.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
