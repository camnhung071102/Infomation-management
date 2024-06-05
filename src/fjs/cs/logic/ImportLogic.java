package fjs.cs.logic;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.springframework.web.struts.ActionSupport;

import fjs.cs.dao.ImportDao;

public class ImportLogic extends ActionSupport {
	private ImportDao importDao;

	public void setImportDao(ImportDao importDao) {
		this.importDao = importDao;
	}

	/**
	 * HAM KIEM TRA EXTENSION.
	 * @param fileName
	 * @return
	 */
	public boolean checkExtension(String fileName) {
		return importDao.checkExtension(fileName);
	}

	/**
	 * HAM IMPORT FILE.
	 * @param req
	 * @param file
	 * @param PSN_CD
	 * @return
	 * @throws IOException
	 */
	public ActionMessages importData(HttpServletRequest req, FormFile file, int PSN_CD) throws IOException {
		// Khoi tao ActionMessages.
		ActionMessages errors = new ActionMessages();
		// Import file.
		errors = importDao.importFile(file, PSN_CD);
		// Luu error.
		saveErrors(req, errors);
		// Tra ve.
		return errors;
	}
}