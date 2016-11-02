package chatTest;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatClient extends Frame {
	TextField tf = null;
	TextArea ta = null;
	Socket sk = null;
	
	DataInputStream dis = null;
	DataOutputStream dos = null;
	
	ChatClient() {
		super("Client");
	}
	public static void main(String[] agrs) {
		ChatClient cc = new ChatClient();
		cc.launchFrame();
		cc.start();
	}
	
	public void start() {
		try{
			sk = new Socket("127.0.0.1",599);
			Server rs = new Server();
			new Thread(rs).start();
			
			dos = new DataOutputStream(sk.getOutputStream());
			
			}catch(UnknownHostException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
	}
	
	public void launchFrame() {
		setLocation(100,100);
		setSize(300,300);
		tf = new TextField();
		ta = new TextArea();
		tf.addActionListener(new Alistener());
		add(ta,BorderLayout.NORTH);
		add(tf,BorderLayout.SOUTH);
		pack(); 
		setVisible(true);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				disConnect();
				System.exit(0);
			}
		});
		
	
	}
	
	public void disConnect() {
		try {
			sk.close();
			dis.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	class Alistener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String s = tf.getText();
			
			ta.append("自己:"+s+"\n");
			tf.setText("");
			
			
			
			try{
				dos.writeUTF(s);
				dos.flush();
			}catch(Exception e1) {
				System.out.println("Output error!");
			}
		}
	}
	
	class Server implements Runnable {
		public void run() {
			try {
				dis = new DataInputStream(sk.getInputStream());
				while(true) {
				String sak = dis.readUTF();
				ta.append(sak);
				}
			} catch (IOException e) {
				
				System.out.println("已断开连接！！！");
			}
	
		}
	}
	
		
}

	
	


