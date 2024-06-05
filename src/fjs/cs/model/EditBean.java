package fjs.cs.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import fjs.cs.common.Util;

public class EditBean extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String mode;
	private String customerId;
	private String customerName;
	private String sex;
	private String birthday;
	private String email;
	private String address;
	private String screen;

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req) {
		// Khoi tao ActionErrors.
		ActionErrors errors = new ActionErrors();
		// Khoi tao Util;
		Util util = new Util();
		// Kiem tra dinh dang ngay cua birthday
		boolean isValidDate = util.checkDate(getBirthday());
		// Kiem tra dinh dang email cua email
		boolean isValidEmail = util.checkEmail(getEmail());
		// Neu chua nhan button nao => mode rong.
		if (getMode().isEmpty()) {
			return null;
		}
		// Neu birthday sai dinh dang.
		if (!isValidDate) {
			errors.add("", new ActionMessage("date.isValid"));
		// Neu email sai dinh dang.
		} else if (!isValidEmail) {
			errors.add("", new ActionMessage("email.isValid"));
		}
		// Set value cho screen = addnew.
		setScreen("Add New");
		// Neu customer co data 
		if (!getCustomerId().isEmpty()) {
			// Set value cho screen = edit.
			setScreen("Edit");
		}
		// setAttribute screen
		req.setAttribute("screen", getScreen());
		// setAttribute customerId
		req.setAttribute("customerId", getCustomerId());
		// Tra ve errors.
		return (errors);
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		customerId = "";
		birthday = "";
		email = "";
		mode = "";
		screen = "";
	}

}