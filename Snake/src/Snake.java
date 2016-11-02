import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	int x,y;
	
	private static final int SNAKE_SIZE = 10;
	
	private boolean bl = false,  bu = false,  br = false, bd = false;
	
	Direction dir;
	
	Yard yd;
	
	int oldX,oldY;
	
	private boolean live = true;
	
	public Snake(int x, int y, Direction dir,Yard yd) {
		this.dir = dir;
		this.yd = yd;
		this.x = x;
		this.y = y;
		new Thread(new ThreadSnake()).start();
		
	}
	
	public void draw(Graphics g) {
		if(!live) return;
		Color c = g.getColor();
		
		g.setColor(Color.BLACK);
		
		g.fillRect(x, y, SNAKE_SIZE, SNAKE_SIZE);
		
		g.setColor(c);
		
		
		
	}
	
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	public void setOldLocation(int oldX, int oldY) {
		x = oldX;
		y = oldY;
	}
	
	public void move(Direction dir) {
		this.oldX = x;
		this.oldY = y;
		switch(dir) {
		
		case L:
			x -= SNAKE_SIZE;
			
			break;
			
		
		case U:
			
			y -= SNAKE_SIZE;
			break;
			
		
			
		case R:
			x += SNAKE_SIZE;
			break;
			
		
			
		case D:
			y += SNAKE_SIZE;
			break;
			
	
		default:
			break;
		
		}
		
		if(x < 0) {
			x =0;
		}
		
		if(y < 30) {
			y = 30;
		}
		
		if(x+SNAKE_SIZE > Yard.COLS*Yard.BLOCK_SIZE) {
			x =  Yard.COLS*Yard.BLOCK_SIZE-SNAKE_SIZE;
		}
		
		if(y+SNAKE_SIZE > Yard.ROWS*Yard.BLOCK_SIZE) {
			y = Yard.ROWS*Yard.BLOCK_SIZE-SNAKE_SIZE;
		}
		
	}
	
	public void setDirection() {
		
			if( bl && !bu && !br && !bd ) {
				dir = Direction.L;
			
				
			}
			
			
			
			else if( bu && !bl && !br && !bd ) {
				dir = Direction.U;
			}
			
			
			else if( br && !bu && !bl && !bd ) {
				dir = Direction.R;
			}
			
			
			
			else if( bd && !bu && !br && !bl ) {
				dir = Direction.D;
			}
			
		
			
			
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		switch(key) {
		
			case KeyEvent.VK_F2:
				if(!live) {
					live = true;
				}
		
			case KeyEvent.VK_LEFT:
				bl = true;
				break;
				
			case KeyEvent.VK_UP:
				bu = true;
				break;
				
			case KeyEvent.VK_RIGHT:
				br = true;
				break;
				
			case KeyEvent.VK_DOWN:
				bd = true;
				break;
		}
		
		this.setDirection();
		
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
		
		setDirection();
	}
	
	
	public Rectangle getRect() {
		return new Rectangle(x, y, Yard.BLOCK_SIZE, Yard.BLOCK_SIZE);
	}
	
	public boolean eat(Egg e) {
		if(e.isLive() && this.live && this.getRect().intersects(e.getRect())) {
			e.setLive(false);
			yd.snakes.add(new Snake(oldX,oldY,dir,yd));
		}
		
		return false;
		
	}
	
	private class ThreadSnake implements Runnable {

		@Override
		public void run() {
			
			while(true) {
				
				try {
					Thread.sleep(500);
					move(dir);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
			
		}
		
		
	}
	
}
