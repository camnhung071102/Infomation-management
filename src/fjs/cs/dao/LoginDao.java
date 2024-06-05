package fjs.cs.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fjs.cs.dto.MSTMENU;
import fjs.cs.dto.MSTSTAFFMENU;
import fjs.cs.dto.MSTUSER;

public class LoginDao extends HibernateDaoSupport {

	public LoginDao() {
	}

	/**
	 * HÀM ĐĂNG NHẬP.
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	public MSTUSER login(String userID, String password) {
		Criteria criteria = getSession().createCriteria(MSTUSER.class);
		try {
			// Lay cac record co userId la userID
			criteria.add(Restrictions.eq("userId", userID));
			// Lay cac record co password la password
			criteria.add(Restrictions.eq("password", password));
			// Lay gia tri max.
			criteria.setMaxResults(1);
			// Khoi tao MSTUSER chua ket qua.
			MSTUSER user = (MSTUSER) criteria.uniqueResult();
			// Tra ve user.
			return user;
		} catch (Exception e) {
			// In ngoai le.
			e.printStackTrace();
		}
		// Tra ve null.
		return null;
	}
	public String getScreenById(String id) {
		Criteria cr = getSession().createCriteria(MSTSTAFFMENU.class);
		cr.add(Restrictions.eq("userId", id));
		MSTSTAFFMENU staffMenu = (MSTSTAFFMENU) cr.uniqueResult();
		return staffMenu.getScreenId();
		}
}