import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Yard extends Frame {
	public static final int ROWS = 50;
	public static final int COLS = 50;
	public static int BLOCK_SIZE = 10;
	Image offImage = null;
	Snake s = new Snake();
	Egg e = new Egg();
	
	public void launch() {
		this.setLocation(200,100);
		this.setSize(ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMoniter());
		new Thread(new PaintThread()).start();
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		this.paintGrid(g);
		s.eat(e);
		s.draw(g);
		e.draw(g);
	}
	
	public void paintGrid(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, ROWS*BLOCK_SIZE, COLS*BLOCK_SIZE);
		g.setColor(Color.DARK_GRAY);
		
		for(int i = 1; i < ROWS; i++) {
			g.drawLine(0, i*BLOCK_SIZE, BLOCK_SIZE*COLS, BLOCK_SIZE*i);
			
		}
		
		for(int i = 1; i < COLS; i++) {
			g.drawLine(i*BLOCK_SIZE, 0, BLOCK_SIZE*i, BLOCK_SIZE*ROWS);
			
		}
		g.setColor(c);
	}
	
	public void update(Graphics g) {
		if(offImage == null) {
			offImage = this.createImage(BLOCK_SIZE*ROWS,BLOCK_SIZE*COLS);
		}
		
		Graphics offg = offImage.getGraphics();
		this.paintGrid(offg);
		this.paint(offg);
		g.drawImage(offImage, 0, 0, null);
		
		
	}
	
	public static void main(String[] agrs) {
		new Yard().launch();
	}
	
	private class PaintThread implements Runnable {
		public void run() {
			try{
				while(true) {
					Thread.sleep(50);
					repaint();
			}
				
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class KeyMoniter extends KeyAdapter {

		
		public void keyPressed(KeyEvent e) {
			s.keyPressed(e);
		}

		
		
	}
	
	
}
