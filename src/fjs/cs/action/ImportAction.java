package fjs.cs.action;

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
import fjs.cs.model.ImportBean;
import fjs.cs.dto.MSTUSER;
import fjs.cs.logic.ImportLogic;

public class ImportAction extends ActionSupport {
	
	/**
	 * Function default.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String forward=Constants.IMPORT;
		ImportBean bean = (ImportBean) form;
		ImportLogic importLogic = (ImportLogic) getWebApplicationContext().getBean("importLogic");
		HttpSession session = req.getSession();
		MSTUSER user = (MSTUSER) session.getAttribute("login");
		
		if (Constants.IMPORT.equals(bean.getMode())) {
			ActionMessages errors = validate(mapping, req, bean);
			if (errors.isEmpty()) {
				importLogic.importData(req, bean.getUploadFile(),user.getPsnCd());
			}
		}
		
		if(Constants.CANCEL.equals(bean.getMode())) {
			forward = Constants.SEARCH;
		}
		
		if(Constants.LOGOUT.equals(bean.getMode())) {
			forward = Constants.LOGOUT;
		}
		
		return (mapping.findForward(forward));
	}
	
	/**
	 * Ham VALIDATE
	 * @param mapping
	 * @param req
	 * @param bean
	 * @return
	 */
	public ActionMessages validate(ActionMapping mapping, HttpServletRequest req, ImportBean bean) {
		ActionMessages errors = new ActionMessages();
		ImportLogic importLogic = (ImportLogic) getWebApplicationContext().getBean("importLogic");
		
		if (bean.getExtension().isEmpty()) {
			errors.add("", new ActionMessage("file.exist"));
		} else if (!importLogic.checkExtension(bean.getExtension())) {
			errors.add("", new ActionMessage("file.extension"));
		} else {
			if (bean.getUploadFile().getFileSize() == 0) {
				errors.add("", new ActionMessage("file.isEmpty"));
			}
		}
		
		saveErrors(req, errors);
		return errors;
	}
}