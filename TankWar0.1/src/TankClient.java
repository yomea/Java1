import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	
	public void launchFrame() {
		setLocation(200,50);
		setSize(800,600);
		setBackground(Color.GREEN);
		this.setResizable(false);
		setTitle("TankWar");
		setVisible(true);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(50, 50, 30, 30);
		g.setColor(c);
	}
	
	public static void main(String[] agrs ) {
		new TankClient().launchFrame();
	}
}

