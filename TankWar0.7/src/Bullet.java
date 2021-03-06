import java.awt.*;

public class Bullet {
	public static final int XSPEED = 2, YSPEED = 2;
	public static final int WIDTH = 10, HEIGHT = 10;
	int x,y;
	Tank.direction dir;
	
	public Bullet(int x, int y, Tank.direction dir) {

		this.x = x;
		this.y = y;
		this.dir = dir;
		
		
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
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
	
	
}
