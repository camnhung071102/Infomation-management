package fjs.cs.logic;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fjs.cs.common.Util;
import fjs.cs.dao.SearchOrderDao;
import fjs.cs.dto.MSTORDER;
import fjs.cs.model.SearchOrderBean;

public class SearchOrderLogic {
	Util utils = new Util();
	private SearchOrderDao searchOrderDao;

	public void setSearchOrderDao(SearchOrderDao searchOrderDao) {
		this.searchOrderDao = searchOrderDao;
	}

	public List<MSTORDER> getAllOrder() {
		return searchOrderDao.getAllOrder();
	}

	public List<MSTORDER> getOrders(SearchOrderBean bean, HttpServletRequest req) throws SQLException {
		req.setAttribute("customerId", bean.getCustomerId());
		req.setAttribute("dayTo", bean.getDayTo());
		req.setAttribute("dayFrom", bean.getDayFrom());
		setValue(req, bean);
		return searchOrderDao.getOrders(bean);
	}

	public void setValue(HttpServletRequest req, SearchOrderBean bean) {
		req.setAttribute("sexOld", bean.getCustomerIdOld());
		// setAttribute dayFromOld
		req.setAttribute("dayFromOld", bean.getDayFromOld());
		// setAttribute dayToOld
		req.setAttribute("dayToOld", bean.getDayToOld());
	}
}
