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
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tank(int x, int y, TankClient tc) {
		this(x,y);
		this.tc = tc;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		move();
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
			case KeyEvent.VK_CONTROL:
				tc.bt = fire();
				break;
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
	
	public Bullet fire() {
		int x = this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
		int y = this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
		return new Bullet(x,y,dir);
		
	}
}
