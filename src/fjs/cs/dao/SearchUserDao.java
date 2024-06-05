package fjs.cs.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fjs.cs.common.Constants;
import fjs.cs.dto.MSTCUSTOMER;
import fjs.cs.dto.MSTUSER;
import fjs.cs.model.SearchUserBean;

public class SearchUserDao extends HibernateDaoSupport {
	public SearchUserDao() {

	}
	/**
	 * HAM SEARCH.
	 * 
	 * @param search
	 * @param currentPage
	 * @param rowsOfPage
	 * @return
	 * @throws SQLException
	 */
	public List<MSTUSER> getUser(SearchUserBean bean) throws SQLException {
		Criteria cr = getSession().createCriteria(MSTUSER.class);
		
		if (!bean.getUserId().isEmpty()) {
			cr.add(Restrictions.like("userId", "%" + bean.getUserId() + "%"));
		}
		
		if (!bean.getUserName().isEmpty()) {
			cr.add(Restrictions.like("userName", "%" + bean.getUserName() + "%"));
		}
		
		if (!bean.getType().isEmpty()) {
			cr.add(Restrictions.eq("type", bean.getType()));
		}
		
		List<MSTUSER> list = cr.list();
		
		for (int i = 0; i < list.size(); i++) {
			if (Constants.TYPE0.equals(list.get(i).getType())) {
				list.get(i).setType(Constants.ADMIN);
			} else if (Constants.TYPE1.equals(list.get(i).getType())) {
				list.get(i).setType(Constants.STAFF);
			} 
		}
		
		return list;
	}


	/**
	 * GET ALL DATA BY ID.
	 * 
	 * @param id
	 * @return
	 */
	public MSTCUSTOMER getAllById(String id) {
		// Khoi tao doi tuong Criteria.
		Criteria cr = getSession().createCriteria(MSTCUSTOMER.class);
		// Lay cac record co customerID = id.
		cr.add(Restrictions.eq("customerID", Integer.parseInt(id)));
		// Khoi tao MSTCUSTOMER chua ket qua.
		MSTCUSTOMER customer = (MSTCUSTOMER) cr.uniqueResult();
		// Tra ve.
		return customer;
	}
}
