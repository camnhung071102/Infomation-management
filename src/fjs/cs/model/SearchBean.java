package fjs.cs.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SearchBean extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String customerName;
	private String sex;
	private String dayFrom;
	private String dayTo;
	private String mode;
	private String customerNameOld;
	private String sexOld;
	private String dayFromOld;
	private String dayToOld;
	private String listIdDelete;
	private int currentPage;
	boolean searchBtn = false;
	private String customerId;

	public String getCustomerNameOld() {
		return customerNameOld;
	}

	public void setCustomerNameOld(String customerNameOld) {
		this.customerNameOld = customerNameOld;
	}

	public String getSexOld() {
		return sexOld;
	}

	public void setSexOld(String sexOld) {
		this.sexOld = sexOld;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public boolean isSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(boolean searchBtn) {
		this.searchBtn = searchBtn;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getListIdDelete() {
		return listIdDelete;
	}

	public void setListIdDelete(String listIdDelete) {
		this.listIdDelete = listIdDelete;
	}

	public SearchBean() {
	}

	public String getDayFrom() {
		return dayFrom;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		customerName = "";
		sex = "";
		dayFrom = "";
		dayTo = "";
		mode = "";
		customerNameOld = "";
		sexOld = "";
		dayFromOld = "";
		dayToOld = "";
		listIdDelete = "";
		currentPage = 1;
	}

}