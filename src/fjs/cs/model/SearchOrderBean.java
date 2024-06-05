package fjs.cs.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchOrderBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String mode;
	private String dayFrom;
	private String dayTo;
	private String customerIdOld;
	private String dayFromOld;
	private String dayToOld;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDayFrom() {
		return dayFrom;
	}

	public void setDayFrom(String dayFrom) {
		this.dayFrom = dayFrom;
	}

	public String getDayTo() {
		return dayTo;
	}

	public void setDayTo(String dayTo) {
		this.dayTo = dayTo;
	}
	
	public String getCustomerIdOld() {
		return customerIdOld;
	}

	public void setCustomerIdOld(String customerIdOld) {
		this.customerIdOld = customerIdOld;
	}

	public String getDayFromOld() {
		return dayFromOld;
	}

	public void setDayFromOld(String dayFromOld) {
		this.dayFromOld = dayFromOld;
	}

	public String getDayToOld() {
		return dayToOld;
	}

	public void setDayToOld(String dayToOld) {
		this.dayToOld = dayToOld;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		customerId = "";
		dayFrom="";
		mode="";
		dayTo="";
		customerIdOld = "";
		dayFromOld="";
		dayToOld="";
	}

}
