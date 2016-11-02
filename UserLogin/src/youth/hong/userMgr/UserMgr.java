package youth.hong.userMgr;

import youth.hong.dao.PasswordNotCorrectException;
import youth.hong.dao.UserDao;
import youth.hong.dao.UsernameNotFoundException;
import youth.hong.user.User;

public class UserMgr {
	private static UserMgr um = null;
	private static UserDao dao = null;
	static {
		um = new UserMgr();
		dao = new UserDao();
	}
	private UserMgr() {
		
	}
	public static UserMgr getUm() {
		if(um == null) {
			um = new UserMgr();
		}
		return um;
	}
	public void addUser(User user) {
		dao.addUser(user);
	}
	
	public void checkUser(User user) throws UsernameNotFoundException, PasswordNotCorrectException{
		dao.checkUser(user);
	}
}
