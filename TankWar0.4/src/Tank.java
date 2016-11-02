import java.awt.*;
import java.awt.event.KeyEvent;


public class Tank {
	int x,y;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
			case KeyEvent.VK_RIGHT: x = x+5; break;
			case KeyEvent.VK_DOWN: y = y+5; break;
			case KeyEvent.VK_UP: y = y-5; break;
			case KeyEvent.VK_LEFT: x = x-5; break;
			
			
		}
	}
}
