package fjs.cs.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fjs.cs.dto.MSTORDERDETAIL;
import fjs.cs.model.OrderDetailBean;

public class OrderDetailDao extends HibernateDaoSupport {
	public OrderDetailDao() {

	}

	public List<MSTORDERDETAIL> getOrders(OrderDetailBean bean) throws SQLException {
		Criteria cr = getSession().createCriteria(MSTORDERDETAIL.class);
		
		if (!bean.getOrderId().isEmpty()) {
			cr.add(Restrictions.like("orderId", "%" + bean.getOrderId() + "%"));
		}
		
		List<MSTORDERDETAIL> list = cr.list();
		return list;
	}
}
