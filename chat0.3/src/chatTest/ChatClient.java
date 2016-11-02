package chatTest;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatClient extends Frame {
	TextField tf = null;
	TextArea ta = null;
	static Socket sk = null;
	static InputStream is = null;
	static DataInputStream dis = null;
	static DataOutputStream dos = null;
	
	ChatClient() {
		super("Client");
	}
	public static void main(String[] agrs) {
		ChatClient cc = new ChatClient();
		cc.launchFrame();
		try{
		sk = new Socket("127.0.0.1",599);
		is = sk.getInputStream();
		dis = new DataInputStream(is);
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
		
			ta.append("Client:"+s+"\n");
			tf.setText("");
			
			
			
			try{
				dos.writeUTF(s);
				dos.flush();
			}catch(Exception e1) {
				System.out.println("Output error!");
			}
		}

	}
		
}

	
	


