package fjs.cs.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SettingMenuBean extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID; 
	private String mode;
	private String listSelectB;
	private String listSelectA;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getListSelectB() {
		return listSelectB;
	}
	public void setListSelectB(String listSelectB) {
		this.listSelectB = listSelectB;
	}
	public String getListSelectA() {
		return listSelectA;
	}
	public void setListSelectA(String listSelectA) {
		this.listSelectA = listSelectA;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		listSelectA = "";
		listSelectB="";
		mode="";
		userID="";
	}
	

}
