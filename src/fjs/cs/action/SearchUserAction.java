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
import fjs.cs.dto.MSTUSER;
import fjs.cs.logic.SearchUserLogic;
import fjs.cs.model.SearchUserBean;

public class SearchUserAction extends ActionSupport {

	/**
	 * Function default.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		SearchUserBean bean = (SearchUserBean) form;
		HttpSession session = req.getSession();
		SearchUserLogic searchUserLogic = (SearchUserLogic) getWebApplicationContext().getBean("searchUserLogic");
		List<MSTUSER> list = new ArrayList<MSTUSER>();

		if (bean.getMode().isEmpty()) {
			list = searchUserLogic.getUser(bean, req);
		}

		if (Constants.SEARCH.equals(bean.getMode())) {
			list = searchUserLogic.getUser(bean, req);
		} else if (Constants.SEARCHORDER.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCHORDER));
		} else if (Constants.SEARCHCUSTOMER.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCH));
		} else if (Constants.LOGOUT.equals(bean.getMode())) {
			session.invalidate();
			return (mapping.findForward(Constants.LOGIN));

		}
		req.setAttribute("users", list);
		return (mapping.findForward(Constants.SEARCHUSER));
	}
}
