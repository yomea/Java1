import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class Tank {
	
	public static final int XSPEED = 1, YSPEED = 1;
	public static final int WIDTH = 30, HEIGHT = 30;
	
	private int x,y;
	
	
	
	private BloodBar bb = new BloodBar();
	
	private boolean bl = false,bu = false,br = false,bd = false;
	
	enum direction {L,LU,U,RU,R,RD,D,LD,STOP};
	
	direction dir = direction.STOP;
	
	TankClient tc = null;
	
	direction ptdir = direction.D;
	
	public boolean good = true;
	
	int oldX,oldY;
	
	boolean live = true;
	
	private int liveValue = 100;
	
	public int getLiveValue() {
		return liveValue;
	}

	public void setLiveValue(int liveValue) {
		this.liveValue = liveValue;
	}

	private Random rn = new Random();
	
	int step = rn.nextInt(60)+15;
	
	public Tank(int x, int y) {
		this.x = x;
		oldX = x;
		oldY = y;
		this.y = y;
	}
	
	public Tank(int x, int y, boolean good, TankClient tc) {
		this(x,y);
		this.good = good;
		this.tc = tc;
	}
	
	void move() {
		
		oldX = this.x;
		oldY = this.y;
		
		switch(dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		
		if(dir!=direction.STOP) {
			ptdir = dir;
		}
		
		if(x < 0) x = 0;
		if(y < 30) y = 30;
		if((x + Tank.WIDTH) > TankClient.WAR_WIDTH) x = TankClient.WAR_WIDTH - Tank.WIDTH;
		if((y + Tank.HEIGHT) > TankClient.WAR_HEIGHT) y = TankClient.WAR_HEIGHT - Tank.HEIGHT;
		
		
		
		if(!good) {
			
			direction dr[] = direction.values();
			
			if(step == 0) {
				
				step = rn.nextInt(60)+15;
				int index = rn.nextInt(dr.length);
				dir = dr[index];
				
			}
			if(rn.nextInt(80)>78) {
				fire();
			}
			
			step--;
		}
		
		
	}
	
	public void draw(Graphics g) {
		if(!live) {
			if(!good) {
				tc.tanks.remove(this);
			}else {
			return;
			}
		}
		this.eat(tc.b);
		Color c = g.getColor();
		if(good) g.setColor(Color.RED);
		else g.setColor(Color.BLUE);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		if(good) bb.draw(g);
		
		move();
		
		
		switch(ptdir) {
		case L:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y+Tank.HEIGHT/2);
			break;
		case LU:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y);
			break;
		case U:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH/2, y);
			break;
		case RU:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+WIDTH, y);
			break;
		case R:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+WIDTH, y+Tank.HEIGHT/2);
			break;
		case RD:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+WIDTH, y+Tank.HEIGHT);
			break;
		case D:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+WIDTH/2, y+Tank.HEIGHT);
			break;
		case LD:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y+Tank.HEIGHT);
			break;
		
		}
		
	}
	

	
	
	
	

	public void setLive(boolean live) {
		this.live = live;
	}

	void directer() {
		if( bl && !bu && !br && !bd ) {
			dir = direction.L;
		}
		
		else if( bl && bu && !br && !bd ) {
			dir = direction.LU;	
		}
		
		else if( bu && !bl && !br && !bd ) {
			dir = direction.U;
		}
		
		else if( bu && br && !bl && !bd ) {
			dir = direction.RU;
		}
		
		else if( br && !bu && !bl && !bd ) {
			dir = direction.R;
		}
		
		else if( br && bd && !bu && !bl ) {
			dir = direction.RD;
		}
		
		else if( bd && !bu && !br && !bl ) {
			dir = direction.D;
		}
		
		else if( bl && bd && !br && !bu ) {
			dir = direction.LD;
		}
		
		else if( !bl && !bu && !br && !bd ) {
			dir = direction.STOP;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
			
			case KeyEvent.VK_RIGHT:
				br = true; 
				break;
			case KeyEvent.VK_DOWN: 
				bd = true; 
				break;
			case KeyEvent.VK_UP:
				bu = true; 
				break;
			case KeyEvent.VK_LEFT: 
				bl = true;
				break;
			case KeyEvent.VK_F2:
				
				tc.reset = true;
				
				break;
			case KeyEvent.VK_F1:
				if(!this.live) {
					this.live = true;
					this.liveValue = 100;
				}
					
			
			
		}
		
		directer();
		
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		case KeyEvent.VK_CONTROL:
			fire();
			break;
			case KeyEvent.VK_RIGHT:
				br = false; 
				break;
			case KeyEvent.VK_DOWN: 
				bd = false; 
				break;
			case KeyEvent.VK_UP:
				bu = false; 
				break;
			case KeyEvent.VK_LEFT: 
				bl = false;
				break;
			case KeyEvent.VK_A:
				superfire();
				break;
			
			
		}
		
		directer();
	}
	
	public void fire() {
		
		if(!live) return;
		int x = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
		int y = this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
		Bullet blt = null;
		
			blt = new Bullet(x,y,ptdir,good,tc);
	
			
	
		
		tc.bullets.add(blt);
		
	}
	
	public void fire(direction dir) {
		if(!live) return;
		int x = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
		int y = this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
		Bullet blt = null;
		
			blt = new Bullet(x,y,dir,good,tc);
	
			
	
		
		tc.bullets.add(blt);
		
	}
	
	public void superfire() {
		direction[] dir = direction.values();
		
		
		for(int i=0; i < 8; i++) {
		
			this.fire(dir[i]);
		}
		
	}
	
	public Rectangle hit() {
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
	
	public void stay() {
		this.x = oldX;
		this.y = oldY;
	}
	
	public void collidesWithWall(Wall wl) {
		if(this.live && this.hit().intersects(wl.hit())) {
			this.stay();
		}
		
	}
	
	public void collidesWithTank(Tank tk) {
		if(this != tk && tk.live && this.live && this.hit().intersects(tk.hit())) {
			this.stay();
			tk.stay();
		}
		
	}
	
	public void collidesWithTanks(java.util.List<Tank> tanks) {
		for(int i=0; i<tanks.size();i++) {
			Tank t = tanks.get(i);
			this.collidesWithTank(t);
		}
		
	}
	
	private class BloodBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y-10, WIDTH, 10);
			g.fillRect(x, y-10, WIDTH*liveValue/100, 10);
			g.setColor(c);
		}
	}
	
	
	public boolean eat(Blood b) {
		if(this.live && this.hit().intersects(b.hit()) && b.isLive() && this.good != false) {
			this.liveValue = 100;
			b.setLive(false);
			return true;
		}
		return false;
	}
	
}
