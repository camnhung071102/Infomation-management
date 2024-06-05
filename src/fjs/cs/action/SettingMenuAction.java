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
import fjs.cs.dto.MSTMENU;
import fjs.cs.logic.SettingMenuLogic;
import fjs.cs.model.SettingMenuBean;

public class SettingMenuAction extends ActionSupport {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
	        HttpServletResponse resp) throws Exception {
	    SettingMenuBean bean = (SettingMenuBean) form;
	    HttpSession session = req.getSession();
	    SettingMenuLogic settingMenuLogic = (SettingMenuLogic) getWebApplicationContext().getBean("settingMenuLogic");
	    List<MSTMENU> listAllMenus = settingMenuLogic.getAllMenus();
	    List<MSTMENU> listSelectB = settingMenuLogic.getAllByIds(bean.getUserID());
	    List<MSTMENU> listSelectA = new ArrayList<>(listAllMenus);
	    
	    if (!listSelectB.isEmpty()) {
	        listSelectA = settingMenuLogic.getAllExceptA(listSelectB);
	    }
	    
	    if (Constants.SAVE.equals(bean.getMode())) {
	    	settingMenuLogic.updateById(bean);
			return (mapping.findForward(Constants.SEARCHUSER));
		}
	    
		if (Constants.CANCEL.equals(bean.getMode())) {
			return (mapping.findForward(Constants.SEARCHUSER));
		}
		
		if (Constants.LOGOUT.equals(bean.getMode())) {
			session.invalidate();
			return (mapping.findForward(Constants.LOGIN));
		}
		
	    req.setAttribute("listSelectA", listSelectA);
	    req.setAttribute("listSelectB", listSelectB);
	    req.setAttribute("userID", bean.getUserID());
	    return (mapping.findForward(Constants.SETTINGMENU));
	}
}
