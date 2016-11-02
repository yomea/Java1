import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	int x,y;
	Yard yd;
	private boolean live = true;
	
	Random rn = new Random();
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public Egg(int x, int y, Yard yd) {
		this.x = x;
		this.y = y;
		this.yd = yd;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			
			int x1 = rn.nextInt(Yard.ROWS*Yard.BLOCK_SIZE);
			int y1 = rn.nextInt(Yard.COLS*Yard.BLOCK_SIZE);
			
			if(x1%10 == 0 && y1%10 == 0) {
				this.x = x1;
				this.y = y1;
				live = true;
			}
			
		}
		Color c = g.getColor();
		
		g.setColor(Color.GREEN);
		
		g.fillRect(x, y, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
		
		g.setColor(c);
		
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
	}
	
	
}
