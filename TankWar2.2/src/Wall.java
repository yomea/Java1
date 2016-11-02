import java.awt.*;

public class Wall {
	int x,y;
	
	TankClient tc;
	
	public static final int WIDTH = 20, HEIGHT = 300;
	
	public Wall(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, WIDTH, HEIGHT);
		
		g.setColor(c);
		
	}
	
	public Rectangle hit() {
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
}
