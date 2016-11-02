import java.awt.*;

public class Bullet {
	public static final int XSPEED = 2, YSPEED = 2;
	public static final int WIDTH = 10, HEIGHT = 10;
	int x,y;
	Tank.direction dir;
	private boolean live = true;
	TankClient tc;
	
	public Bullet(int x, int y, Tank.direction dir) {

		this.x = x;
		this.y = y;
		this.dir = dir;
		
		
	}
	
	public Bullet(int x, int y, Tank.direction dir,TankClient tc) {

		this(x,y,dir);
		this.tc = tc;
		
	}
	
	public void draw(Graphics g) {
		if(!live) {
			tc.bullets.remove(this);
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		isLive();
		move();
		
		
	}
	
	public void move() {
		
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
		
		}
	}
	
	public boolean isLive() {
		if(x<0||y<0||x>TankClient.WAR_WIDTH||y>TankClient.WAR_HEIGHT ) {
			 live = false;
			
		}
		
		
		return live;
	}
	
	public Rectangle hit() {
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
	
	public boolean hitTank(Tank tk) {
		if(hit().intersects(tk.hit())&&(tk.live == true)) {
			tk.setLive(false);
			this.live = false;
			return true;
		}
		return false;
	}
	
}
