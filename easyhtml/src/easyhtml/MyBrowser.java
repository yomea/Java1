package easyhtml;


	import java.awt.event.*;
	import javax.swing.*;
	import javax.swing.event.*;
	import java.awt.*;
	import java.net.*;
	import java.io.*;

	public class MyBrowser implements ActionListener,HyperlinkListener{
	//������Ҫʵ��HyperlinkListener�ӿڣ�����Ӧ�û���
	//���������¼�
	JLabel msgLbl;
	JTextField urlText;  //���û�����URL
	JEditorPane content;  //��ʾ��ҳ����
	JScrollPane JSPanel;
	JPanel panel;
	Container con;
	JFrame mainJframe;
	//���췽�������ڳ������Ĳ���
	public MyBrowser(){
	mainJframe=new JFrame("�ҵ������");
	mainJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	con=mainJframe.getContentPane();
	msgLbl=new JLabel("�����ַ��");
	urlText=new JTextField();
	urlText.setColumns(20);
	urlText.addActionListener(this);
	panel=new JPanel();
	panel.setLayout(new FlowLayout());
	panel.add(msgLbl);
	panel.add(urlText);

	content=new JEditorPane();
	content.setEditable(false);
	//Ϊcontent��ӳ������¼�������
	content.addHyperlinkListener(this);
	JSPanel=new JScrollPane(content);

	con.add(panel,BorderLayout.NORTH);
	con.add(JSPanel,BorderLayout.CENTER);
	mainJframe.setSize(800,600);
	mainJframe.setVisible(true);
	mainJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//���û����»س����󣬵��ô˷���
	public void actionPerformed(ActionEvent e){
	try{
	//�����û����빹��URL����
	URL url=new URL(urlText.getText());
	//��ȡ��ҳ���ݲ���ʾ
	content.setPage(url);
	}catch (MalformedURLException el){
	System.out.println(e.toString());
	}catch(IOException el){
	JOptionPane.showMessageDialog(mainJframe,"���Ӵ���");
	}
	}
	//ʵ��hyperlinkUpdate���������û������ҳ�ϵ�����ʱ��ϵͳ�����ô˷���
	public void hyperlinkUpdate(HyperlinkEvent e){
	if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
	try{
	URL url=e.getURL();         //��ȡ�û������URL
	content.setPage(url);      //��ת����ҳ��
	urlText.setText(e.getURL().toString()); //�����û�������е�URL
	}catch (MalformedURLException el){
	System.out.println(e.toString());
	}catch(IOException el){
	JOptionPane.showMessageDialog(mainJframe,"���Ӵ���");
	}
	}
	}

	public static void main(String[] args) {
	new MyBrowser();
	}
	} 
	 