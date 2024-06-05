package fjs.cs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.web.struts.ActionSupport;

import fjs.cs.common.Constants;
import fjs.cs.logic.LoginLogic;
import fjs.cs.model.LoginBean;

public class LoginAction extends ActionSupport {

	/**
	 * Function default.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		LoginBean bean = (LoginBean) form;
		String forward = Constants.LOGIN;
		HttpSession session = req.getSession();
		if (Constants.LOGIN.equals(bean.getMode())) {
			ActionMessages errors = validate(mapping, req, bean);
			if (errors.isEmpty()) {
				forward = Constants.SEARCH;
				List<String> screenIds = (List<String>) session.getAttribute("screenId");
				for (String screen : screenIds) {
					if (Constants.SEARCHCUSTOMERID.equals(screen)) {
						return (mapping.findForward(Constants.SEARCH));
					}
					if (Constants.SEARCHUSERID.equals(screen)) {
						return mapping.findForward(Constants.SEARCHUSER);
					}
					if (Constants.SEARCHORDERID.equals(screen)) {
						return (mapping.findForward(Constants.SEARCHORDER));
					}
				}
			}
		}
		return (mapping.findForward(forward));
	}

	public ActionMessages validate(ActionMapping mapping, HttpServletRequest req, LoginBean bean) {
		ActionMessages errors = new ActionMessages();
		LoginLogic loginLogic = (LoginLogic) getWebApplicationContext().getBean("loginLogic");

		if (bean.getUserId().isEmpty()) {
			errors.add("", new ActionMessage("user.isEmpty"));
		} else if (bean.getPassword().isEmpty()) {
			errors.add("", new ActionMessage("pass.isEmpty"));
		} else {
			boolean check = loginLogic.authenticate(bean.getUserId(), bean.getPassword(), req);
			if (!check) {
				errors.add("", new ActionMessage("login.authenticate"));
			}
		}

		saveErrors(req, errors);
		return errors;
	}
}