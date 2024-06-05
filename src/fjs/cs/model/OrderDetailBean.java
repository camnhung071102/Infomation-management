package fjs.cs.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OrderDetailBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String mode;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		mode = "";
		orderId="";
	}
}
