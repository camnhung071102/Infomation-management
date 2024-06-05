package fjs.cs.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import fjs.cs.common.Constants;
import fjs.cs.dto.MSTCUSTOMER;
import fjs.cs.model.SearchBean;
import fjs.cs.properties.ReadProperties;

public class SearchDao extends HibernateDaoSupport {
	public SearchDao() {

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
	public List<MSTCUSTOMER> getCustomer(SearchBean search, int currentPage, int rowsOfPage) throws SQLException {
		// Khoi tao doi tuong Criteria.
		Criteria cr = getSession().createCriteria(MSTCUSTOMER.class);
		// lay cac record co delete_ymd is null
		cr.add(Restrictions.isNull("delete_ymd"));
		// Lay cac record co sex is not null.
		cr.add(Restrictions.isNotNull("sex"));
		// Neu input customerName co data.
		if (!search.getCustomerNameOld().isEmpty()) {
			// Lay cac record la customerName.
			cr.add(Restrictions.like("customerName", "%" + search.getCustomerNameOld() + "%"));
		}
		// Neu input sex co data.
		if (!search.getSexOld().isEmpty()) {
			// Lay cac record co sex la getSexOld().
			cr.add(Restrictions.eq("sex", search.getSexOld()));
		}
		// Neu input dayFrom va dayTo co data.
		if (!search.getDayFromOld().isEmpty() && !search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday trong khoang getDayFromOld va getDayToOld.
			cr.add(Restrictions.between("birthday", search.getDayFromOld(), search.getDayToOld()));
		}
		// Neu chi dayFrom co data
		if (!search.getDayFromOld().isEmpty() && search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday lon hon hoac bang getDayFromOld.
			cr.add(Restrictions.gt("birthday", search.getDayFromOld()));
		}
		// Neu chi dayTo co data
		if (search.getDayFromOld().isEmpty() && !search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday nho hon hoac bang getDayToOld.
			cr.add(Restrictions.lt("birthday", search.getDayToOld()));
		}
		// Lay cac record bat dau tu record thu : (currentPage - 1) * rowsOfPage.
		cr.setFirstResult((currentPage - 1) * rowsOfPage);
		// Lay rowsOfPage record tu vi tri bat dau.
		cr.setMaxResults(rowsOfPage);
		// Gan ket qua vao List<MSTCUSTOMER>.
		List<MSTCUSTOMER> list = cr.list();
		for (int i = 0; i < list.size(); i++) {
			// Neu getSex = 0.
			if (Constants.SEX0.equals(list.get(i).getSex())) {
				// Set value cho sex = male.
				list.get(i).setSex(Constants.MALE);
				// Neu getSex = 1.
			} else if (Constants.SEX1.equals(list.get(i).getSex())) {
				// Set value cho sex = female.
				list.get(i).setSex(Constants.FEMALE);
				// Nguoc lai.
			} else {
				// Set value cho sex = "".
				list.get(i).setSex("");
			}
		}
		// Tra ve.
		return list;
	}

	/**
	 * Read rowOfPage in file .properties
	 * 
	 * @return
	 */
	public int rowOfPage() {
		// Khoi tao ReadProperties.
		ReadProperties rd = new ReadProperties();
		// Khoi tao bien rowOfPage.
		int rowOfPage = 0;
		try {
			//
			rowOfPage = rd.readRowOfPage();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return rowOfPage;
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

	/**
	 * HAM DELETE
	 * 
	 * @param listSplit
	 */
	public void delete(String id) {
		Session session = getSession();
		// Bat dau transaction.
		session.beginTransaction();
		// Khoi tao doi tuong Criteria.
		Criteria cr = session.createCriteria(MSTCUSTOMER.class);
		// Lay cac customer co customerID = id.
		MSTCUSTOMER customer = (MSTCUSTOMER) cr.add(Restrictions.eq("customerID", Integer.parseInt(id))).uniqueResult();
		customer.setDelete_ymd(Calendar.getInstance().getTime());
		// Thuc hien update.
		session.update(customer);
		// Luu thay doi
		session.getTransaction().commit();
	}
	/**
	 * HAM DEM SO KET QUA SEARCH.
	 * 
	 * @param search
	 * @return
	 * @throws SQLException
	 */
	public int countRow(SearchBean search) throws SQLException {
		// Khoi tao doi tuong Criteria.
		Criteria cr = getSession().createCriteria(MSTCUSTOMER.class);
		// lay cac record co delete_ymd is null
		cr.add(Restrictions.isNull("delete_ymd"));
		// Lay cac record co sex is not null.
		cr.add(Restrictions.isNotNull("sex"));
		// Neu input customerName co data.
		if (!search.getCustomerNameOld().isEmpty()) {
			// Lay cac record la customerName.
			cr.add(Restrictions.like("customerName", "%" + search.getCustomerNameOld() + "%"));
		}
		// Neu input sex co data.
		if (!search.getSexOld().isEmpty()) {
			// Lay cac record co sex la getSexOld().
			cr.add(Restrictions.eq("sex", search.getSexOld()));
		}
		// Neu input dayFrom va dayTo co data.
		if (!search.getDayFromOld().isEmpty() && !search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday trong khoang getDayFromOld va getDayToOld.
			cr.add(Restrictions.between("birthday", search.getDayFromOld(), search.getDayToOld()));
		}
		// Neu chi dayFrom co data
		if (!search.getDayFromOld().isEmpty() && search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday lon hon hoac bang getDayFromOld.
			cr.add(Restrictions.gt("birthday", search.getDayFromOld()));
		}
		// Neu chi dayTo co data
		if (search.getDayFromOld().isEmpty() && !search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday nho hon hoac bang getDayToOld.
			cr.add(Restrictions.lt("birthday", search.getDayToOld()));
		}
		// Khoi tao bien size.
		int size = cr.list().size();
		// Tra ve.
		return size;
	}
	
	
	
	public List<MSTCUSTOMER> exportFile(SearchBean search) throws SQLException {
		// Khoi tao doi tuong Criteria.
		Criteria cr = getSession().createCriteria(MSTCUSTOMER.class);
		// lay cac record co delete_ymd is null
		cr.add(Restrictions.isNull("delete_ymd"));
		// Lay cac record co sex is not null.
		cr.add(Restrictions.isNotNull("sex"));
		// Neu input customerName co data.
		if (!search.getCustomerNameOld().isEmpty()) {
			// Lay cac record la customerName.
			cr.add(Restrictions.like("customerName", "%" + search.getCustomerNameOld() + "%"));
		}
		// Neu input sex co data.
		if (!search.getSexOld().isEmpty()) {
			// Lay cac record co sex la getSexOld().
			cr.add(Restrictions.eq("sex", search.getSexOld()));
		}
		// Neu input dayFrom va dayTo co data.
		if (!search.getDayFromOld().isEmpty() && !search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday trong khoang getDayFromOld va getDayToOld.
			cr.add(Restrictions.between("birthday", search.getDayFromOld(), search.getDayToOld()));
		}
		// Neu chi dayFrom co data
		if (!search.getDayFromOld().isEmpty() && search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday lon hon hoac bang getDayFromOld.
			cr.add(Restrictions.gt("birthday", search.getDayFromOld()));
		}
		// Neu chi dayTo co data
		if (search.getDayFromOld().isEmpty() && !search.getDayToOld().isEmpty()) {
			// Lay cac record co birthday nho hon hoac bang getDayToOld.
			cr.add(Restrictions.lt("birthday", search.getDayToOld()));
		}
		// Gan ket qua vao List<MSTCUSTOMER>.
		List<MSTCUSTOMER> list = cr.list();
		for (int i = 0; i < list.size(); i++) {
			// Neu getSex = 0.
			if (Constants.SEX0.equals(list.get(i).getSex())) {
				// Set value cho sex = male.
				list.get(i).setSex(Constants.MALE);
				// Neu getSex = 1.
			} else if (Constants.SEX1.equals(list.get(i).getSex())) {
				// Set value cho sex = female.
				list.get(i).setSex(Constants.FEMALE);
				// Nguoc lai.
			} else {
				// Set value cho sex = "".
				list.get(i).setSex("");
			}
		}
		// Tra ve.
		return list;
	}

}