import java.awt.*;
import java.awt.event.KeyEvent;


public class Tank {
	public static final int XSPEED = 1, YSPEED = 1;
	public static final int WIDTH = 30, HEIGHT = 30;
	private int x,y;
	private boolean bl = false,bu = false,br = false,bd = false;
	enum direction {L,LU,U,RU,R,RD,D,LD,STOP};
	direction dir = direction.STOP;
	TankClient tc = null;
	direction ptdir = direction.D;
	private boolean good = true;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tank(int x, int y, boolean good, TankClient tc) {
		this(x,y);
		this.good = good;
		this.tc = tc;
	}
	
	void move() {
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
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		if(good) g.setColor(Color.RED);
		else g.setColor(Color.BLUE);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
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
			
			
		}
		
		directer();
	}
	
	public void fire() {
		int x = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
		int y = this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
		Bullet blt = new Bullet(x,y,ptdir,tc);
		tc.bullets.add(blt);
		
	}
}
