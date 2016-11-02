import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Bullet {
	public static final int XSPEED = 2, YSPEED = 2;
	public static final int WIDTH = 10, HEIGHT = 10;
	int x,y;
	Direction dir;
	
	
	private boolean live = true;
	TankClient tc;
	private boolean good;
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image bulletimgs[] = null;
	
	private static Map<String,Image> imgs = new HashMap<String,Image>(); 
	
	static {
			bulletimgs = new Image[] {
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/missileL.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/missileLU.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/missileU.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/missileRU.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/missileR.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/missileRD.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/missileD.gif")),
			tk.getImage(Exploade.class.getClassLoader().getResource("Images/missileLD.gif"))
			
			
			};
			
			imgs.put("L", bulletimgs[0]);
			imgs.put("LU", bulletimgs[1]);
			imgs.put("U", bulletimgs[2]);
			imgs.put("RU", bulletimgs[3]);
			imgs.put("R", bulletimgs[4]);
			imgs.put("RD", bulletimgs[5]);
			imgs.put("D", bulletimgs[6]);
			imgs.put("LD", bulletimgs[7]);
	};

	
	public void setGood(boolean good) {
		this.good = good;
	}

	public Bullet(int x, int y, Direction dir) {
		
		this.x = x;
		this.y = y;
		this.dir = dir;
		
		
	}
	
	public Bullet(int x, int y, Direction dir,TankClient tc) {

		this(x,y,dir);
		this.tc = tc;
		
	}
	
	public Bullet(int x, int y, Direction dir, boolean good, TankClient tc) {

		this(x,y,dir,tc);
		this.good = good;
		
	}
	
	public void draw(Graphics g) {
		
		if(!live) {
			tc.bullets.remove(this);
			return;
		}
		
		switch(dir) {
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"), x, y, null);
			break;
		
		}
		
		
		isLive();
		move();
		
		
	}
	
	public void move() {
		
		switch(dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		
		}
	}
	
	public boolean isLive() {
		if(x<0||y<0||x>TankClient.WAR_WIDTH||y>TankClient.WAR_HEIGHT ) {
			 live = false;
			
		}
		
		
		return live;
	}
	
	
	public Rectangle hit() {
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
	
	public void hitWall(Wall wl) {
		
		if(this.live&&this.hit().intersects(wl.hit())) {
			this.live = false;
		}
	}
	
	public boolean hitTank(Tank tk) {
		
		
		if(this.live && hit().intersects(tk.hit()) && (tk.live == true) && this.good!=tk.good) {
			
			if(!good) {
				tk.setLiveValue(tk.getLiveValue() - 20 );
				if(tk.getLiveValue()<=0) tk.setLive(false);
			}else {
				tk.setLive(false);
			}
			
			this.live = false;
			tc.Exploades.add(new Exploade(x,y,tc));
			
			
			return true;
			
		}
		return false;
	}

	public boolean hitTanks() {
		for(int i=0; i<tc.tanks.size();i++) {
			Tank t = tc.tanks.get(i);
			hitTank(t);
		}
		return false;
	}
}
