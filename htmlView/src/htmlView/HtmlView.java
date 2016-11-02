package htmlView;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlView extends Frame {
	
	TextField tf = new TextField(100);
	TextArea ta = new TextArea();
	Panel ptf = new Panel(new GridLayout(1, 1));
	Panel pta = new Panel(new GridLayout(1, 1));
	
	
	private void launchFrame() {
		this.setLocation(300, 200);
		this.setLayout(new BorderLayout());
		this.setSize(600, 600);
		this.setTitle("HtmlView");
		ptf.setLocation(10, 10);
		ptf.setSize(100, 100);
		pta.setSize(300,300);
		this.add(ptf,BorderLayout.NORTH);
		this.add(pta,BorderLayout.CENTER);
		ptf.add(tf);
		pta.add(ta);
		this.pack();
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		tf.addActionListener(new FrameMonitor());
		
		
	}
	
	private class FrameMonitor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String tfip = tf.getText();
			
				URL url = null;
				ta.setText("");
				
				try {
					url = new URL(tfip);
					InputStream is = url.openStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8")); 
					String s = br.readLine();
					while(s != null) {
						ta.append(s + "\n");
						
						s = br.readLine();
					}
					
				} catch (MalformedURLException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			
			
		}
		
	}

	public static void main(String[] args) {
		new HtmlView().launchFrame();

	}

}
