import java.awt.*;

public class Exploade {
	int x, y;
	private boolean live = true;
	TankClient tc;
	int[] d = {4,10,20,30,40,60,40,30,20,10,4};
	int step = 0;
	
	public Exploade(int x, int y, TankClient tc) {
		
		this.x = x;
		this.y = y;
		this.tc = tc;
		
	}
	
	public void draw(Graphics g) {
		if(!live) return;
		
		if(step == d.length) {
			live = false;
			step = 0;
			tc.Exploades.remove(this);
			
			return;
		}
		
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillOval(x, y, d[step], d[step]);
		g.setColor(c);
		step++;
	}



	public void setLive(boolean live) {
		this.live = live;
	}
}
