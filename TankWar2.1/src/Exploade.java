import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Exploade {
	int x, y;
	private boolean live = true;
	TankClient tc;
	private boolean init = false;
	private static Toolkit tk = Toolkit.getDefaultToolkit(); 
	private static Image imgs[] = {
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/0.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/1.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/2.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/3.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/4.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/5.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/6.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/7.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/8.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/9.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/10.gif")),
			
			
	};
	int step = 0;
	
	public Exploade(int x, int y, TankClient tc) {
		
		this.x = x;
		this.y = y;
		this.tc = tc;
		
	}
	
	public void draw(Graphics g) {
		if(!init) {
			for(int i=0; i < imgs.length; i++) {
				g.drawImage(imgs[i], -100, -100, null);
			}
			init = true;
		}
		if(!live) return;
		
		if(step == imgs.length) {
			live = false;
			step = 0;
			tc.Exploades.remove(this);
			
			return;
		}
		
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.drawImage(imgs[step],x,y,null);
		g.setColor(c);
		step++;
	}



	public void setLive(boolean live) {
		this.live = live;
	}
}
