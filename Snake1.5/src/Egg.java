import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	int row,col;
	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	private static Random rn = new Random();

	public Egg(int row, int col) {
		
		this.row = row;
		this.col = col;
	}
	
	public Egg() {
		this(rn.nextInt(Yard.ROWS-3)+3,rn.nextInt(Yard.COLS));
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE,w, h);
		g.setColor(c);
	}
	
	
	public void reAppear() {
		this.row = rn.nextInt(Yard.ROWS-3)+3;
		this.col = rn.nextInt(Yard.COLS);
	}
	
	public Rectangle getRect() {
		return new Rectangle(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE, w, h);
	}
	
}
