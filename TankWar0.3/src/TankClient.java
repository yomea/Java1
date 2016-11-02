import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	int x = 50,y = 50;
	public static final int WAR_WIDTH = 800, WAR_HEIGHT = 600;
	Image offScreenImage = null;
	
	public void launchFrame() {
		setLocation(200,50);
		setSize(WAR_WIDTH,WAR_HEIGHT);
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
	
	
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = createImage(WAR_WIDTH,WAR_HEIGHT);
			
		}
		Graphics offg = offScreenImage.getGraphics();
		Color c = offg.getColor();
		offg.setColor(Color.GREEN);
		offg.fillRect(0, 0, WAR_WIDTH,WAR_HEIGHT);
		offg.setColor(c);
		paint(offg);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}



	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		y = y+1;
	}
	
	public static void main(String[] agrs ) {
		new TankClient().launchFrame();
	}
	
	private class PaintThread implements Runnable {
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			}
		}
	}
}

