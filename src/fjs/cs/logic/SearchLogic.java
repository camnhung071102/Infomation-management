package fjs.cs.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fjs.cs.common.Constants;
import fjs.cs.common.Util;
import fjs.cs.dao.SearchDao;
import fjs.cs.dto.MSTCUSTOMER;
import fjs.cs.model.SearchBean;

public class SearchLogic {
	Util utils = new Util();
	private SearchDao searchDao;

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

	/**
	 * HAM SEARCH.
	 * @param bean
	 * @param req
	 * @return
	 * @throws SQLException
	 */
	public List<MSTCUSTOMER> getCustomer(SearchBean bean, HttpServletRequest req) throws SQLException {
		// Khoi tao totalRows chua so record.
		int totalRows = searchDao.countRow(bean);
		// Khoi tao page hien tai = 1;
		int currentPage = 1;
		// Khoi tao List<MSTCUSTOMER>.
		List<MSTCUSTOMER> list = new ArrayList<MSTCUSTOMER>();
		// Tien hanh search.
		list = searchDao.getCustomer(bean, currentPage, searchDao.rowOfPage());
		// Set value
		setValue(req, bean);
		// disable
		disable(list, currentPage, totalRows, req);
		// Tra ve.
		return list;
	}

	/**
	 * INIT SEARCH.
	 * @param id
	 * @param req
	 * @return
	 */
	public List<MSTCUSTOMER> initSearch(String id, HttpServletRequest req) {
		// Khoi tao List<MSTCUSTOMER>.
		List<MSTCUSTOMER> list = new ArrayList<MSTCUSTOMER>();
		// Lay cac customer có customerId là id.
		MSTCUSTOMER customer = searchDao.getAllById(id);
		// Neu customer.getSex() = 0.
		if (Constants.SEX0.equals(customer.getSex())) {
			customer.setSex(Constants.MALE);
			// Neu customer.getSex() = 1.
		} else if (Constants.SEX1.equals(customer.getSex())) {
			customer.setSex(Constants.FEMALE);
			// Neu customer.getSex() = "".
		} else {
			customer.setSex("");
		}
		// Disable button Last
		req.setAttribute("disableLast", true);
		// Disable button next
		req.setAttribute("disableNext", true);
		// Them customer vao list.
		list.add(customer);
		// Tra ve.
		return list;

	}

	/**
	 * HAM DEM SO KET QUA SEARCH.
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public int countRow(SearchBean bean) throws SQLException {
		// Tra ve so record.
		return searchDao.countRow(bean);
	}

	/**
	 * HAM PHAN TACH ID TU CHUOI ID CAN XOA.
	 * 
	 * @param listId
	 */
	public void deleteId(String listId) {
		// khoi tao mang chua id sau khi split.
		String listSplit[] = listId.split(",");
		// Lap mang.
		for (int i = 0; i < listSplit.length; i++) {
			// Tien hanh xoa .
			searchDao.delete(listSplit[i]);
		}
	}
	/**
	 * HAM PHAN TRANG.
	 * 
	 * @param bean
	 * @param req
	 * @return
	 * @throws SQLException
	 */
	public List<MSTCUSTOMER> pagination(SearchBean bean, HttpServletRequest req) throws SQLException {
		// Khoi tao bien chua so record.
		int totalRows = searchDao.countRow(bean);
		// Khoi tao bien chua page hien tai.
		int currentPage = bean.getCurrentPage();
		// Neu page = 1.
		if (currentPage == 0) {
			// Gan page = 1.
			currentPage = 1;
		}
		// Khoi tao List<MSTCUSTOMER>.
		List<MSTCUSTOMER> list = new ArrayList<MSTCUSTOMER>();
		// Tinh so page.
		int totalLastPage = (int) Math.ceil((double) totalRows / searchDao.rowOfPage());
		// Neu mode = first.
		if (Constants.FIRST.equals(bean.getMode())) {
			// Gan page tai = 1.
			currentPage = 1;
			// Neu mode = previous.
		} else if (Constants.PREVIOUS.equals(bean.getMode())) {
			// giam page hien tai xuuong 1.
			currentPage--;
			// Neu mode = next.
		} else if (Constants.NEXT.equals(bean.getMode())) {
			// Tang page hien tai len 1.
			currentPage++;
			// Neu mode = last.
		} else if (Constants.LAST.equals(bean.getMode())) {
			// Gsn page hien tai = page cuoi.
			currentPage = totalLastPage;
		}
		// setAttribute 
		req.setAttribute("currentPage", currentPage);
		// Set value.
		setValue(req, bean);
		// Tien hanh Seach
		list = searchDao.getCustomer(bean, currentPage, searchDao.rowOfPage());
		// Disable.
		disable(list, currentPage, totalRows, req);
		// Tra ve.
		return list;
	}

	/**
	 * HAM DISABLE.
	 * 
	 * @param list
	 * @param currentPage
	 * @param totalRows
	 * @param req
	 */
	public void disable(List<MSTCUSTOMER> list, int currentPage, int totalRows, HttpServletRequest req) {
		//Tinh toan so page.
		int totalLastPage = (int) Math.ceil((double) totalRows / searchDao.rowOfPage());
		// Disable button First neu : dang la page dau tien || list trong || so record nho hon so record/page.
		boolean disableFirst = (currentPage == 1) || list.isEmpty() || totalRows < searchDao.rowOfPage();
		// Disable button Last neu : dang la page cuoi || list trong || so record nho hon so record/page.
		boolean disableLast = (currentPage == totalLastPage) || list.isEmpty() || totalRows < searchDao.rowOfPage();
		// Disable button Previous neu : dang la page dau tien || list trong || so record nho hon so record/page.
		boolean disablePrevious = (currentPage == 1) || list.isEmpty() || totalRows < searchDao.rowOfPage();
		// Disable button Next neu : dang la page cuoi || list trong || so record nho hon so record/page.
		boolean disableNext = (currentPage == totalLastPage) || list.isEmpty() || totalRows < searchDao.rowOfPage();
		// Disable delete neu list rong.
		boolean disableDelete = list.isEmpty();
		// setAttribute disableFirst
		req.setAttribute("disableFirst", disableFirst);
		// setAttribute disableLast
		req.setAttribute("disableLast", disableLast);
		// setAttribute disablePrevious
		req.setAttribute("disablePrevious", disablePrevious);
		// setAttribute disableNext
		req.setAttribute("disableNext", disableNext);
		// setAttribute disableDelete
		req.setAttribute("disableDelete", disableDelete);
	}

	/**
	 * HAM SET GIA TRI.
	 * 
	 * @param req
	 * @param bean
	 */
	public void setValue(HttpServletRequest req, SearchBean bean) {
		// setAttribute customerName
		req.setAttribute("customerName", bean.getCustomerName());
		// setAttribute sex
		req.setAttribute("sex", bean.getSex());
		// setAttribute dayFrom
		req.setAttribute("dayFrom", bean.getDayFrom());
		// setAttribute dayTo
		req.setAttribute("dayTo", bean.getDayTo());
		// setAttribute customerNameOld
		req.setAttribute("customerNameOld", bean.getCustomerNameOld());
		// setAttribute sexOld
		req.setAttribute("sexOld", bean.getSexOld());
		// setAttribute dayFromOld
		req.setAttribute("dayFromOld", bean.getDayFromOld());
		// setAttribute dayToOld
		req.setAttribute("dayToOld", bean.getDayToOld());
	}
	
	public List<MSTCUSTOMER> exportFile(SearchBean search) throws SQLException {
		return searchDao.exportFile(search);
	}

}