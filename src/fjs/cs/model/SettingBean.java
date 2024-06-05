package fjs.cs.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SettingBean extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String listHeader;
	private String mode;
	private String headerList;
	private String listSelectA;

	public String getListHeader() {
		return listHeader;
	}

	public void setListHeader(String listHeader) {
		this.listHeader = listHeader;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getHeaderList() {
		return headerList;
	}

	public void setHeaderList(String headerList) {
		this.headerList = headerList;
	}

	public String getListSelectA() {
		return listSelectA;
	}

	public void setListSelectA(String listSelectA) {
		this.listSelectA = listSelectA;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		listSelectA =null;
		headerList = "";
		listHeader = "";
	}
}