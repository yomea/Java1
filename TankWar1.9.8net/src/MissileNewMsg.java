import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * �����ӵ���������Ϣ��
 * @author mashibing
 *
 */
public class MissileNewMsg implements Msg {
	int msgType = Msg.MISSILE_NEW_MSG;

	TankClient tc;

	Missile m;
	
	/**
	 * �����ӵ���Ϣ�����µ���Ϣ��
	 * @param m ������Ϣ���ӵ�
	 */
	public MissileNewMsg(Missile m) {
		this.m = m;
	}
	
	/**
	 * ������Ϣ�����ĳ��������µ���Ϣ
	 * @param tc
	 */
	public MissileNewMsg(TankClient tc) {
		this.tc = tc;
	}
	
	/**
	 * ������ص���Ϣ
	 * @param ds ͨ����socket��������
	 * @param IP ���ݵ�Ŀ��IP
	 * @param udpPort ���ݵ�Ŀ��˿�
	 */
	public void send(DatagramSocket ds, String IP, int udpPort) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(m.tankId);
			dos.writeInt(m.id);
			dos.writeInt(m.x);
			dos.writeInt(m.y);
			dos.writeInt(m.dir.ordinal());
			dos.writeBoolean(m.good);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length,
					new InetSocketAddress(IP, udpPort));
			ds.send(dp);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �������յ�����Ϣ����
	 * @param dis ���յ�����Ϣ���ݵ�������
	 */
	public void parse(DataInputStream dis) {
		try {
			int tankId = dis.readInt();
			if (tankId == tc.myTank.id) {
				return;
			}
			int id = dis.readInt();
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			boolean good = dis.readBoolean();

			// System.out.println("id:" + id + "-x:" + x + "-y:" + y + "-dir:" +
			// dir + "-good:" + good);
			Missile m = new Missile(tankId, x, y, good, dir, tc);
			m.id = id;
			tc.missiles.add(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
