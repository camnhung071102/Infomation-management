package fjs.cs.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fjs.cs.dto.MSTORDER;
import fjs.cs.model.SearchOrderBean;

public class SearchOrderDao extends HibernateDaoSupport {
	public SearchOrderDao() {

	}

	public List<MSTORDER> getAllOrder() {
		Criteria cr = getSession().createCriteria(MSTORDER.class);
		List<MSTORDER> orders = cr.list();
		return orders;
	}

	public List<MSTORDER> getOrders(SearchOrderBean bean) throws SQLException {
		Criteria cr = getSession().createCriteria(MSTORDER.class);
		if (!bean.getCustomerIdOld().isEmpty()) {
			cr.add(Restrictions.like("customerId", "%" + bean.getCustomerIdOld() + "%"));
		}
		if (!bean.getDayFromOld().isEmpty() && !bean.getDayToOld().isEmpty()) {
			cr.add(Restrictions.between("date", bean.getDayFromOld(), bean.getDayToOld()));
		}
		if (!bean.getDayFromOld().isEmpty() && bean.getDayToOld().isEmpty()) {
			cr.add(Restrictions.gt("date", bean.getDayFromOld()));
		}
		if (bean.getDayFromOld().isEmpty() && !bean.getDayToOld().isEmpty()) {
			cr.add(Restrictions.lt("date", bean.getDayToOld()));
		}
		List<MSTORDER> list = cr.list();
		return list;
	}

}
