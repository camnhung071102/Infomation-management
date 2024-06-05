package fjs.cs.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fjs.cs.common.Util;
import fjs.cs.dao.SearchUserDao;
import fjs.cs.dto.MSTUSER;
import fjs.cs.model.SearchUserBean;

public class SearchUserLogic {
	Util utils = new Util();
	private SearchUserDao searchUserDao;

	public void setSearchUserDao(SearchUserDao searchUserDao) {
		this.searchUserDao = searchUserDao;
	}

	/**
	 * HAM SEARCH.
	 * 
	 * @param bean
	 * @param req
	 * @return
	 * @throws SQLException
	 */
	public List<MSTUSER> getUser(SearchUserBean bean, HttpServletRequest req) throws SQLException {

		List<MSTUSER> list = new ArrayList<MSTUSER>();
		// Tien hanh search.
		list = searchUserDao.getUser(bean);
		// Set value
		setValue(req, bean);
		return list;
	}

	/**
	 * HAM SET GIA TRI.
	 * 
	 * @param req
	 * @param bean
	 */
	public void setValue(HttpServletRequest req, SearchUserBean bean) {
		// setAttribute customerName
		req.setAttribute("userId", bean.getUserId());
		// setAttribute sex
		req.setAttribute("userName", bean.getUserName());
		req.setAttribute("type", bean.getType());

	}
}
