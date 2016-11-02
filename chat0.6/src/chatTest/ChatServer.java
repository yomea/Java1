package chatTest;

import java.io.*;

import java.net.*;

import java.util.*;

public class ChatServer {
	

	List<Client> Clients = new ArrayList<Client>();
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
			System.out.println("程序已执行！");
			System.exit(0);
		}catch(Exception e) {
			System.out.println("程序启动失败！！！");
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
		private DataInputStream dis = null;//注意有些变量应当是每个线程私有的，
		private boolean bbl = false;//不能定义成ChatServer的成员变量，如dis。
		private DataOutputStream dos = null;
		public Client(Socket s) {
			this.s = s;
			bbl = true;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				Clients.add(this);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		public void send(String str) {
			try {
				dos.writeUTF("Other person:"+str+"\n");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
		public void run() {
		
			
			
			try {
			while(bbl) {
				
//System.out.println("Connect!");
				String ds =dis.readUTF();
				System.out.println(ds);
				for(int i=0; i<Clients.size(); i++) {
					Client previous = Clients.get(i);
					if(previous!=this) {
					previous.send(ds);
					}
				}
				}
		
				
			
			
		}catch(EOFException e) {
			System.out.println("One persion disconnect!!! ");
			
	
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(dis!=null) dis.close();
			if(s!=null) s.close();
			if(dos!=null) dos.close();
			Clients.remove(this);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		}
	}
}
