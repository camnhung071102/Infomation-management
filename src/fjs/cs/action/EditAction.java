package fjs.cs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import fjs.cs.common.Constants;
import fjs.cs.model.EditBean;
import fjs.cs.dto.MSTUSER;
import fjs.cs.logic.EditLogic;

public class EditAction extends ActionSupport {

	/**
	 * Function default.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		EditBean bean = (EditBean) form;
		EditLogic editLogic = (EditLogic) getWebApplicationContext().getBean("editLogic");
		HttpSession session = req.getSession();
		MSTUSER user = (MSTUSER) session.getAttribute("login");
		bean.setScreen("Add New");
		String forward = Constants.EDIT;
		
		if (!bean.getCustomerId().isEmpty() || Constants.CLEAR.equals(bean.getMode())) {
			bean.setScreen("Edit");
			editLogic.getAllById(bean.getCustomerId(), req);
		}
		
		if (Constants.SAVE.equals(bean.getMode())) {
			if (!bean.getCustomerId().isEmpty()) {
				editLogic.updateById(bean, user.getPsnCd());
				session.setAttribute("customerId", bean.getCustomerId());
			} else {
				session.setAttribute("customerId", editLogic.maxId());
				editLogic.addNew(bean, user.getPsnCd());
			}
			forward = Constants.SEARCH;
			return (mapping.findForward(forward));
		}
		
		if (Constants.LOGOUT.equals(bean.getMode())) {
			session.invalidate();
			return (mapping.findForward(Constants.LOGIN));
		}
		if (Constants.CANCEL.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCH));
		}
		
		req.setAttribute("customerId", bean.getCustomerId());
		req.setAttribute("screen", bean.getScreen());
		return (mapping.findForward(forward));
	}
}