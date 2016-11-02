package chatTest;
////�ɶ������ӷ����������ͻ��˲��ܶԻ�������
import java.io.*;

import java.net.*;

public class ChatServer {
	
	static DataOutputStream dos = null;
	
	public static void main(String[] agrs) {
		new ChatServer().start();
		
	}

	public void start() {
		boolean bl = false;
		
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
				
			
				Socket s = ss.accept();
			Client ct = new Client(s);
			new Thread(ct).start();
			}
	}catch(IOException e) {
		e.printStackTrace();
	}
		}
	class Client implements Runnable {
		private Socket s = null;
		private DataInputStream dis = null;//ע����Щ����Ӧ����ÿ���߳�˽�еģ�
		private boolean bbl = false;//���ܶ����ChatServer�ĳ�Ա��������dis��
		public Client(Socket s) {
			this.s = s;
			bbl = true;
			try {
				dis = new DataInputStream(s.getInputStream());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		public void run() {
		
			
			
			try {
			while(bbl) {
				
//System.out.println("Connect!");
				
				System.out.println(dis.readUTF());
				
				}
		
				
			
			
		}catch(EOFException e) {
			System.out.println("�ѶϿ����ӣ�����");
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
}
