import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.ArrayList;
public class TankClient extends Frame {
	
	int x = 50,y = 50;
	
	public static final int WAR_WIDTH = 800, WAR_HEIGHT = 600;
	Image offScreenImage = null;
	
	Tank tankOne = new Tank(750,550,true,this);
	
	List<Tank> tanks = new ArrayList<Tank>();
	
	Wall wl = new Wall(400,300,this);
	
	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Exploade> Exploades = new ArrayList<Exploade>();
	
	public void launchFrame() {
		
		for(int i=0; i<10; i++) {
			tanks.add(new Tank(50+40*(i+1),50,false,this));
		}
		setLocation(200,50);
		setSize(WAR_WIDTH,WAR_HEIGHT);
		setBackground(Color.GREEN);
		this.setResizable(false);
		setTitle("TankWar");
		new Thread(new PaintThread()).start();
		this.addKeyListener(new KeyMonitor());
		setVisible(true);
		
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
		g.drawString("Bullets count:"+bullets.size(), 10, 50);
		g.drawString("Exploades count:"+Exploades.size(), 10, 70);
		g.drawString("tanks count:"+tanks.size(), 10, 90);
		g.drawString("tanks liveValue:"+tankOne.getLiveValue(), 10, 110);
		tankOne.draw(g);
		
		wl.draw(g);
		
		
		for(int i=0; i < bullets.size(); i++) {
			Bullet bbt = bullets.get(i);
				bbt.hitTanks();
				bbt.hitTank(tankOne);
				bbt.hitWall(wl);
				bbt.draw(g);
			
			
		}
		
		for(int i=0; i < Exploades.size(); i++) {
			Exploade ep = Exploades.get(i);
			
				ep.draw(g);
		}
		
		for(int i=0; i<tanks.size();i++) {
			Tank tk = tanks.get(i);
			tk.collidesWithWall(wl);
			tk.collidesWithTanks(tanks);
			tk.collidesWithTank(tankOne);
			tk.draw(g);
		}
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

	private class KeyMonitor extends KeyAdapter {

	
		public void keyPressed(KeyEvent e) {
			tankOne.keyPressed(e);
		}

		
		public void keyReleased(KeyEvent e) {
			tankOne.keyReleased(e);
			
			
		}
		
		
		
	}
}

