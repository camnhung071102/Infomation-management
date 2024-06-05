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
import fjs.cs.model.SettingBean;
import fjs.cs.logic.SettingLogic;

public class SettingAction extends ActionSupport {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		SettingBean bean = (SettingBean) form;
		HttpSession session = req.getSession();
		SettingLogic settingLogic = (SettingLogic) getWebApplicationContext().getBean("settingLogic");
		List<String> list = new ArrayList<String>();
		List<String> listInit = new ArrayList<String>();

		if (!bean.getHeaderList().isEmpty()) {
			listInit = settingLogic.listHeader(bean.getHeaderList());
		}

		if (Constants.SAVE.equals(bean.getMode())) {
			list = settingLogic.listHeader(bean.getListHeader());
			session.setAttribute("listHeader", list);
			List<String> listSelectA = new ArrayList<String>();
			if (bean.getListSelectA() != null) {
				listSelectA = settingLogic.listHeader(bean.getListSelectA());
				session.setAttribute("listSelectA", listSelectA);
			}
			return (mapping.findForward(Constants.SEARCH));
		}

		if (Constants.CANCEL.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCH));
		}

		if (Constants.LOGOUT.equals(bean.getMode())) {
			session.invalidate();
			return (mapping.findForward(Constants.LOGIN));
		}
		req.setAttribute("listInit", listInit);
		return (mapping.findForward(Constants.SETTING));
	}
}