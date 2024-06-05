package fjs.cs.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import fjs.cs.common.Constants;
import fjs.cs.dto.MSTCUSTOMER;
import fjs.cs.model.SearchBean;
import fjs.cs.logic.SearchLogic;
import fjs.cs.properties.Export;

public class SearchAction extends ActionSupport {

	/**
	 * Function default.
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		SearchBean bean = (SearchBean) form;
		HttpSession session = req.getSession();
		SearchLogic searchLogic = (SearchLogic) getWebApplicationContext().getBean("searchLogic");
		String customerId = (String) session.getAttribute("customerId");
		List<String> listHeader = (List) session.getAttribute("listHeader");
		List<String> screenId =(List<String>) session.getAttribute("screenId");
		String errorEx = Constants.SUCCESS;
		List<MSTCUSTOMER> list = new ArrayList<MSTCUSTOMER>();
		List<MSTCUSTOMER> listEx = new ArrayList<MSTCUSTOMER>();
		List<String> listHeaderExport = new ArrayList<String>();
		listEx = searchLogic.exportFile(bean);
		
		if (bean.getMode().isEmpty()) {
			list = searchLogic.getCustomer(bean, req);
		}
		
		if (Constants.SEARCH.equals(bean.getMode())) {
			bean.setCustomerNameOld(bean.getCustomerName());
			bean.setSexOld(bean.getSex());
			bean.setDayToOld(bean.getDayTo());
			bean.setDayFromOld(bean.getDayFrom());
			list = searchLogic.getCustomer(bean, req);
			listEx = searchLogic.exportFile(bean);
		} else if (Constants.DELETE.equals(bean.getMode())) {
			if (!bean.getListIdDelete().isEmpty()) {
				searchLogic.deleteId(bean.getListIdDelete());
			}
			list = searchLogic.getCustomer(bean, req);
		} else if (Constants.EDIT.equals(bean.getMode())) {
			return (mapping.findForward(Constants.EDIT));
		}
		else if (Constants.SEARCHUSER.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCHUSER));
		}
		else if (Constants.SEARCHORDER.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCHORDER));
		}
		else if (Constants.LOGOUT.equals(bean.getMode())) {
			session.invalidate();
			return (mapping.findForward(Constants.LOGIN));
		} else {
			list = searchLogic.pagination(bean, req);
		}
		
		if (listHeader != null) {
			req.setAttribute("listHeader", listHeader);
			listHeaderExport = listHeader;
		} else {
			List<String> listStart = new ArrayList<String>();
			listStart.add("Checkbox");
			listStart.add("CustomerID");
			listStart.add("CustomerName");
			listStart.add("Sex");
			listStart.add("Birthday");
			listStart.add("Address");
			req.setAttribute("listHeader", listStart);
			listHeaderExport = listStart;
		}
		
		if (Constants.EXPORT.equals(bean.getMode())) {
			try {
				Export ex = new Export();
				ex.writeFile(listEx, listHeaderExport);
			} catch (Exception e) {
				errorEx = Constants.ERROR;
			}
			req.setAttribute("errorEx", errorEx);
		}
		
		if (Constants.IMPORT.equals(bean.getMode())) {
			return (mapping.findForward(Constants.IMPORT));
		}
		
		if (customerId != null) {
			list = searchLogic.initSearch(customerId, req);
			session.removeAttribute("customerId");
		}
		req.setAttribute("screenId", screenId);
		req.setAttribute("customers", list);
		return (mapping.findForward(Constants.SEARCH));

	}
}