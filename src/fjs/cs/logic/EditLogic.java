package fjs.cs.logic;

import javax.servlet.http.HttpServletRequest;

import fjs.cs.dao.EditDao;
import fjs.cs.model.EditBean;
import fjs.cs.dto.MSTCUSTOMER;

public class EditLogic {
	private EditDao editDao;

	public void setEditDao(EditDao editDao) {
		this.editDao = editDao;
	}

	/**
	 * Get data by id.
	 * 
	 * @param id
	 * @return
	 */
	public void getAllById(String id, HttpServletRequest req) {
		// Get customer co customerId = id.
		MSTCUSTOMER customer = editDao.getAllById(id);
		// setAttribute customerName
		req.setAttribute("customerName", customer.getCustomerName());
		// setAttribute sex
		req.setAttribute("sex", customer.getSex());
		// setAttribute birthday
		req.setAttribute("birthday", customer.getBirthday());
		// setAttribute email
		req.setAttribute("email", customer.getEmail());
		// setAttribute address
		req.setAttribute("address", customer.getAddress());
	}

	/**
	 * Get id addnew
	 * 
	 * @return
	 */
	public String maxId() {
		return String.valueOf(editDao.maxId());
	}

	/**
	 * Ham insert a new customer.
	 * 
	 * @param bean
	 * @param PSN_CD
	 */
	public void addNew(EditBean bean, int PSN_CD) {
		editDao.addNew(bean, PSN_CD);
	}

	/**
	 * Ham update customer by id.
	 * 
	 * @param bean
	 * @param PSN_CD
	 */
	public void updateById(EditBean bean, int PSN_CD) {
		editDao.updateById(bean, PSN_CD);
	}
}