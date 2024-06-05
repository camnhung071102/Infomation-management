package fjs.cs.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class ImportBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormFile uploadFile;
	private String extension;
	private String mode;

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		extension = "";
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}