import java.awt.*;

import java.util.*;

public class Blood {
	int x,y,w,h;
	
	int step;
	
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	private int[][] pos = {
			{250,300},{270,300},{310,430},{320,320},{360,300},{450,500},{440,360},{123,356}
			
	};
	
	public Blood() {
		
		w=h=15;
		
	}
	
	public void poss() {
		step++;
		
		if(step == pos.length) {
			step = 0;
		}
		
		x = pos[step][0];
		y = pos[step][1];
	}
	
	public void draw(Graphics g) {
		if(!live) return;
		poss();
		Color c = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, w, h);
		g.setColor(c);
	}
	
	public Rectangle hit() {
		return new Rectangle(x,y,w,h);
	}
}
