import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * �����ӵ���ʧ����Ϣ��
 * @author mashibing
 *
 */
public class MissileDeadMsg implements Msg {
	int msgType = Msg.MISSILE_DEAD_MSG;

	TankClient tc;

	int tankId;

	int id;
	
	/**
	 * ����̹�˵�id���ӵ������id������Ϣ
	 * @param tankId ̹��id
	 * @param id �ӵ������id
	 */
	public MissileDeadMsg(int tankId, int id) {
		this.tankId = tankId;
		this.id = id;
	}
	
	/**
	 * ������Ϣ�����ĳ��������µ���Ϣ
	 * @param tc
	 */
	public MissileDeadMsg(TankClient tc) {
		this.tc = tc;
	}
	
	/**
	 * �������յ�����Ϣ����
	 * @param dis ���յ�����Ϣ���ݵ�������
	 */
	public void parse(DataInputStream dis) {
		try {
			int tankId = dis.readInt();
			int id = dis.readInt();

			// System.out.println("id:" + id + "-x:" + x + "-y:" + y + "-dir:" +
			// dir + "-good:" + good);
			for (int i = 0; i < tc.missiles.size(); i++) {
				Missile m = tc.missiles.get(i);
				if (m.tankId == tankId && m.id == id) {
					m.live = false;
					tc.explodes.add(new Explode(m.x, m.y, tc));
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

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
			dos.writeInt(tankId);
			dos.writeInt(id);
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

}
