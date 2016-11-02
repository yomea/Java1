import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	int x = 50,y = 50;
	public void launchFrame() {
		setLocation(200,50);
		setSize(800,600);
		setBackground(Color.GREEN);
		this.setResizable(false);
		setTitle("TankWar");
		setVisible(true);
		new Thread(new PaintThread()).start();
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		y = y+5;
	}
	
	public static void main(String[] agrs ) {
		new TankClient().launchFrame();
	}
	
	private class PaintThread implements Runnable {
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			}
		}
	}
}

