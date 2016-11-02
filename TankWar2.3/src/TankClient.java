import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author May
 *
 */

@SuppressWarnings("serial")
public class TankClient extends Frame {
	//the window's location
	int x = 50,y = 50;
	// the window size
	public static final int WAR_WIDTH = 800, WAR_HEIGHT = 600;
	Image offScreenImage = null;
	
	Tank tankOne = new Tank(750,550,true,this);
	//this Random class isn't Math.Random,it come from java.util.Random
	Random rd = new Random();
	
	Blood  b = new Blood();
	//get key for option system
	private Toolkit tt = Toolkit.getDefaultToolkit();
	//Get the images' URL
	private Image[] img = {tt.getImage(Tank.class.getClassLoader().getResource("Images/warArea.jpg"))};
	
	List<Tank> tanks = new ArrayList<Tank>();
	
	boolean reset = false;
	
	Wall wl = new Wall(400,300,this);
	
	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Exploade> Exploades = new ArrayList<Exploade>();
	
	
	int initTankCount = Integer.parseInt(PropertiesM.getProperty("initTankCount"));
	
	public void launchFrame() {
		/*
		 * 窗口代码
		 */
		
		
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
	//new 出坦克
	public void createTanks() {
		for(int i=0; i<initTankCount; i++) {
			tanks.add(new Tank(rd.nextInt(700),rd.nextInt(500),false,this));
			}
	}
	
	//为了避免重画带来的闪烁，重写update方法，使用双缓冲来减少坦克移动带来的闪烁。
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(WAR_WIDTH,WAR_HEIGHT);
			
		}
		Graphics offg = offScreenImage.getGraphics();
		Color c = offg.getColor();
		offg.drawImage(img[0], 0, 0, null);
		paint(offg);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}



	public void paint(Graphics g) {
		if(reset) {
			tankOne = new Tank(750,550,true,this);
			tanks.clear();
			bullets.clear();
			b.setLive(true);
			
			reset = false;
			
		}
		if(tanks.size() == 0) {
			createTanks();
		}
		
		/*
		 *在窗口上打印坦克、子弹、爆炸的数量
		 *打印坦克的当前的生命值 
		 */
		
		
		g.drawString("Bullets count:"+bullets.size(), 10, 50);
		g.drawString("Exploades count:"+Exploades.size(), 10, 70);
		g.drawString("tanks count:"+tanks.size(), 10, 90);
		g.drawString("tanks liveValue:"+tankOne.getLiveValue(), 10, 110);
		tankOne.draw(g);
		b.draw(g);
		
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
	//定义线程对窗口进行重画
	private class PaintThread implements Runnable {
		public void run() {
			while(true) {
				TankClient.this.repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			}
		}
	}
	//添加键盘监听事件
	private class KeyMonitor extends KeyAdapter {

	
		public void keyPressed(KeyEvent e) {
			tankOne.keyPressed(e);
		}

		
		public void keyReleased(KeyEvent e) {
			tankOne.keyReleased(e);
			
			
		}
		
		
		
	}
}

