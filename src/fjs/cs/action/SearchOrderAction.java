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
import fjs.cs.dto.MSTORDER;
import fjs.cs.logic.SearchOrderLogic;
import fjs.cs.model.SearchOrderBean;
import fjs.cs.properties.ExportOrder;

public class SearchOrderAction extends ActionSupport {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		SearchOrderBean bean = (SearchOrderBean) form;
		String errorEx = "";
		HttpSession session = req.getSession();
		SearchOrderLogic searchOrderLogic = (SearchOrderLogic) getWebApplicationContext().getBean("searchOrderLogic");
		List<MSTORDER> listOrder = new ArrayList<MSTORDER>();
		List<MSTORDER> listEx = new ArrayList<MSTORDER>();
		listOrder = searchOrderLogic.getAllOrder();
		listEx = searchOrderLogic.getOrders(bean, req);

		if (Constants.SEARCH.equals(bean.getMode())) {
			bean.setCustomerIdOld(bean.getCustomerId());
			bean.setDayToOld(bean.getDayTo());
			bean.setDayFromOld(bean.getDayFrom());
			listOrder = searchOrderLogic.getOrders(bean, req);
			listEx = searchOrderLogic.getOrders(bean, req);
		}

		if (Constants.SEARCHUSER.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCHUSER));
		} else if (Constants.SEARCHCUSTOMER.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCH));
		} else if (Constants.ORDERDETAIL.equals(bean.getMode())) {
			return (mapping.findForward(Constants.ORDERDETAIL));
		} else if (Constants.LOGOUT.equals(bean.getMode())) {
			session.invalidate();
			return (mapping.findForward(Constants.LOGIN));
		}

		if (Constants.EXPORT.equals(bean.getMode())) {
			try {
				ExportOrder ex = new ExportOrder();
				ex.writeFile(listEx);
				errorEx = Constants.SUCCESS;
			} catch (Exception e) {
				errorEx = Constants.ERROR;
			}
			req.setAttribute("errorEx", errorEx);
		}
		
		req.setAttribute("orders", listOrder);
		return (mapping.findForward(Constants.SEARCHORDER));
	}

}
