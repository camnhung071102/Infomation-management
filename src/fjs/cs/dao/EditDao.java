package fjs.cs.dao;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fjs.cs.model.EditBean;
import fjs.cs.dto.MSTCUSTOMER;

public class EditDao extends HibernateDaoSupport {
	public EditDao() {

	}

	/**
	 * Get data by id.
	 * 
	 * @param id
	 * @return
	 */
	public MSTCUSTOMER getAllById(String id) {
		Criteria cr = getSession().createCriteria(MSTCUSTOMER.class);
		cr.add(Restrictions.eq("customerID", Integer.parseInt(id)));
		MSTCUSTOMER customer = (MSTCUSTOMER) cr.uniqueResult();
		return customer;
	}

	/**
	 * Ham insert a new customer.
	 * 
	 * @param bean
	 * @param PSN_CD
	 */
	public void addNew(EditBean bean, int PSN_CD) {
		Session session = getSession();
		session.beginTransaction();
		MSTCUSTOMER customer = new MSTCUSTOMER();
		customer.setCustomerName(bean.getCustomerName());
		customer.setBirthday(bean.getBirthday());
		customer.setAddress(bean.getAddress());
		customer.setEmail(bean.getEmail());
		customer.setSex(bean.getSex());
		customer.setInsert_psn_cd(PSN_CD);
		customer.setUpdate_psn_cd(PSN_CD);
		customer.setInsert_ymd(Calendar.getInstance().getTime());
		customer.setUpdate_ymd(Calendar.getInstance().getTime());
		session.save(customer);
		session.getTransaction().commit();
	}

	/**
	 * ham lay gia tri max id.
	 * 
	 * @return
	 */
	public int maxId() {
		Criteria cr = getSession().createCriteria(MSTCUSTOMER.class);
		int maxId = (int) cr.setProjection(Projections.max("customerID")).list().get(0);
		return maxId + 1;
	}

	/**
	 * Ham update customer by id.
	 * 
	 * @param bean
	 * @param PSN_CD
	 */
	public void updateById(EditBean bean, int PSN_CD) {
		Session session = getSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(MSTCUSTOMER.class);
		MSTCUSTOMER customer = (MSTCUSTOMER) cr
				.add(Restrictions.eq("customerID", Integer.parseInt(bean.getCustomerId()))).uniqueResult();
		customer.setCustomerName(bean.getCustomerName());
		customer.setSex(bean.getSex());
		customer.setBirthday(bean.getBirthday());
		customer.setEmail(bean.getEmail());
		customer.setAddress(bean.getAddress());
		customer.setUpdate_psn_cd(PSN_CD);
		customer.setUpdate_ymd(Calendar.getInstance().getTime());
		session.update(customer);
		session.getTransaction().commit();
	}
}