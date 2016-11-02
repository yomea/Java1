import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Yard extends Frame {
	
	public  static final int ROWS = 50;
	
	public  static final int COLS = 50;
	
	public  static final int BLOCK_SIZE = 10;
	
	List<Snake> snakes = new ArrayList<Snake>();
	
	
	
	Snake sk = new Snake(50,50,Direction.R, this);
	
	Egg eg = new Egg(70,70,this);
	
	Image img = null;
	
	public void launch() {
		
		this.setLocation(200,200);
		this.setSize(ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		this.addKeyListener(new KeyFrame());
		
		new Thread(new ThreadPaint()).start(); 
		
		this.setResizable(false);
		
		this.setVisible(true);
		
	}
	
	public void paint(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
		g.setColor(c);
		g.drawString("Length:"+snakes.size(), 10, 50);
		g.setColor(Color.DARK_GRAY);
		
		for(int i = 1; i < ROWS; i++) {
			g.drawLine(0, BLOCK_SIZE*i, BLOCK_SIZE*COLS, BLOCK_SIZE*i);
		}
		
		for(int i = 1; i < COLS; i++) {
			g.drawLine(BLOCK_SIZE*i, 0, BLOCK_SIZE*i, BLOCK_SIZE*ROWS);
		}
		
		g.setColor(c);
		sk.draw(g);
		sk.eat(eg);
		
		for(int i = 0; i < snakes.size(); i++ ) {
			
			Snake snake = snakes.get(i);
			
			snake.draw(g);
			
			
				snake.setDir(sk.dir);
			
				
				snake.setOldLocation(sk.oldX+i*BLOCK_SIZE,sk.oldY+i*BLOCK_SIZE);
			
			
			
			
			
			
		}
		eg.draw(g);
		
	}
	
	
	
	@Override
	public void update(Graphics g) {
		if(img == null) {
			img = this.createImage(ROWS*BLOCK_SIZE,COLS*BLOCK_SIZE);
		}
		
		Graphics fg = img.getGraphics();
		Color c = fg.getColor();
		fg.setColor(Color.GRAY);
		fg.fillRect(0, 0, ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
		fg.setColor(Color.DARK_GRAY);
		
		for(int i = 1; i < ROWS; i++) {
			fg.drawLine(0, BLOCK_SIZE*i, BLOCK_SIZE*COLS, BLOCK_SIZE*i);
		}
		
		for(int i = 1; i < COLS; i++) {
			fg.drawLine(BLOCK_SIZE*i, 0, BLOCK_SIZE*i, BLOCK_SIZE*ROWS);
		}
		
		fg.setColor(c);
		paint(fg);
		
		
		g.drawImage(img, 0, 0, null);
	
	}



	private class KeyFrame extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			
			sk.keyPressed(e);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			sk.keyReleased(e);
		}
		
		
		
		
		
	}
	
	private class ThreadPaint implements Runnable {

		@Override
		public void run() {
			
			while(true) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			
				repaint();
			}
			
			
		}
		
		
		
	}
	
	public static void main(String[] args) {
		new Yard().launch();

	}

}
