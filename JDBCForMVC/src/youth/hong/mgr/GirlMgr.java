package youth.hong.mgr;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import youth.hong.Dao.GirlDao;
import youth.hong.gilr.Girl;

public class GirlMgr {
	private static GirlMgr gm = null;
	private static GirlDao dao = null;
	
	static {
		gm = new GirlMgr();
		Properties ps = new Properties();
		try {
			ps.load(GirlMgr.class.getClassLoader().getResourceAsStream("config/dao.properties"));
			String className = ps.getProperty("dao");
			dao = (GirlDao)Class.forName(className).newInstance();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private GirlMgr() {}
	
	public static GirlMgr getGm() {
		if(gm == null) {
			gm = new GirlMgr();
		}
		return gm;
	}
	
	public void addGirl(Girl girl) {
		dao.addGirl(girl);
	}
	
	public void update(Girl girl) {
		dao.update(girl);
	}
	
	public List<Girl> getGirls() {
		return dao.getGirls();
	}
	
	public Girl getGirl(int id) {
		return dao.getGirl(id);
	}
	
	public List<Girl> delOrQueryGirls(int[] id, 
								String[] name,
								int lowAge,
								int highAge,
								Timestamp lowBirthday,
								Timestamp highBirthday,
								String email,
								String mobile, boolean delOrQuery) {
		List<Girl> girls = new ArrayList<Girl>();
		if(delOrQuery) {
			dao.deleteGirls(dao.Girls(id, name, lowAge, highAge, lowBirthday, highBirthday, email, mobile, delOrQuery));
		} else {
			girls = dao.findGirls(dao.Girls(id, name, lowAge, highAge, lowBirthday, highBirthday, email, mobile, delOrQuery));
		}
		return girls;
	}
	
	
}
