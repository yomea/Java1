package chatTest;
///////bug:���������ر�ʱ�����Socket close���󣡣�����
import java.io.*;

import java.net.*;

import java.util.*;

public class ChatServer {
	
	ArrayList<DataOutputStream> al = null;
	
	public static void main(String[] agrs) {
		new ChatServer().start();
		
	}

	public void start() {
		boolean bl = false;
		al = new ArrayList<DataOutputStream>();
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
				
			
				Socket sgh = ss.accept();
			Client ct = new Client(sgh);
			new Thread(ct).start();
			}
	}catch(IOException e) {
		e.printStackTrace();
	}finally {
		try {
			ss.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
		}
	class Client implements Runnable {
		private Socket s = null;
		private DataInputStream dis = null;//ע����Щ����Ӧ����ÿ���߳�˽�еģ�
		private boolean bbl = false;//���ܶ����ChatServer�ĳ�Ա��������dis��
		Iterator<DataOutputStream> it = null;
		DataOutputStream itdos = null;
		DataOutputStream ddos = null;
		public Client(Socket s) {
			this.s = s;
			bbl = true;
			try {
				dis = new DataInputStream(s.getInputStream());
				ddos = new DataOutputStream(s.getOutputStream());
				al.add(ddos);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		public void run() {
		
			
			
			try {
			while(bbl) {
				
//System.out.println("Connect!");
				String ds = dis.readUTF();
				System.out.println(ds);
				it = al.iterator();
				while(it.hasNext()) {
					itdos = it.next();
					if(itdos!=ddos) {
					itdos.writeUTF("�Է�:"+ds+"\n");
					itdos.flush();
					}
				}
				
				}
		
				
			
			
		}catch(EOFException e) {
			System.out.println("�ѶϿ����ӣ�����");
			al.remove(ddos);
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(dis!=null) dis.close();
			if(s!=null) s.close();
			if(itdos!=null) itdos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		}
	}
}
