package youth.hong.Dao;

import java.sql.Timestamp;
import java.util.List;

import youth.hong.gilr.Girl;

public interface GirlDao {
	
	public void addGirl(Girl girl);
	
	public void update(Girl girl);
	
	public List<Girl> getGirls();
	
	public Girl getGirl(int id);
	
	public String Girls(int[] id, 
						String[] name,
						int lowAge,
						int highAge,
						Timestamp lowBirthday,
						Timestamp highBirthday,
						String email,
						String mobile, boolean delOrQuery);
	
	public List<Girl> findGirls(String sql);
	
	public void deleteGirls(String sql);
	
}
