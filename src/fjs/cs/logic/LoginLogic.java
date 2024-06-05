package fjs.cs.logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fjs.cs.common.Util;
import fjs.cs.dao.LoginDao;
import fjs.cs.dto.MSTUSER;

public class LoginLogic {

	private LoginDao loginDao;
	
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao=loginDao;
	}

	/**
	 * AUTHENTICATE.
	 * @param user
	 * @param pass
	 * @return
	 */
	public boolean authenticate(String user, String pass, HttpServletRequest req) {
		// Khoi tao MSTUSER chua users co userId va pass trung khop.
		MSTUSER users = loginDao.login(user,pass);
		// Neu users ton tai.
		if (users != null ) {
			// Khoi tao sesion
			HttpSession session = req.getSession();
			// set value cho session.
			session.setAttribute("login", users);
			List<String> screenId = getScreenById(users.getUserId());
			session.setAttribute("screenId", screenId);
			return true;
		} else {
			return false;
		}
	}
	public List<String> getScreenById(String id) {
		Util util= new Util();
		 List<String> screenId = util.listHeader(loginDao.getScreenById(id));
		return screenId;
	}
}