import java.awt.Color;
import java.awt.Graphics;

/**
 * ����ը����
 * ʹ�ô�С��һ��Բ����ģ��
 * @author mashibing
 *
 */
public class Explode {
	int x, y;

	private int[] diameters = { 4, 7, 12, 18, 26, 32, 49, 30, 14, 6 };

	private boolean live = true;

	private TankClient tc;

	int step = 0;
	
	/**
	 * ����λ�ò����µı�ը
	 * @param x ��ը���x����
	 * @param y ��ը���y����
	 * @param tc ��ը�������ĳ���
	 * @see TankClient
	 */
	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	/**
	 * ������ը��ǰ��Բ
	 * @param g ����
	 * @see java.awt.Graphics
	 */
	public void draw(Graphics g) {
		if (!live) {
			tc.explodes.remove(this);
			return;
		}

		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameters[step], diameters[step]);
		g.setColor(c);

		step++;
		if (step == diameters.length) {
			live = false;
		}
	}
}
