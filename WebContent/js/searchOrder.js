// Khoi tao mang.
var selectedRecords = [];

/**
 * Get Customer Id
 * 
 * @param customerId
 * @returns
 */
function getData(customerId) {
	// Get form
	var form = document.forms[1];
	// set value customerId = customerId.
	form.elements['customerId'].value = customerId;
	// Submit form.
	form.submit();
}

/**
 * set mode = logOut.
 * 
 * @returns
 */
function logOut() {
	// Get form
	var form = document.forms[0];
	// set value mode = logOut.
	form.elements['mode'].value = "logOut";
	// Submit form.
	form.submit();
}

/**
 * set mode = searchUser.
 * 
 * @returns
 */
function searchOrder() {
	// Get form
	var form = document.forms[0];
	// set value mode = search.
	form.elements['mode'].value = "search";
	// Get value input id dayFrom
	var dayFrom = document.getElementById('dayFrom').value;
	// Get value input id dayTo
	var dayTo = document.getElementById('dayTo').value;
	// Neu ca hai dayFrom va dayTo deu sai dinh dang.
	if (!isValidDate(dayFrom) && !isValidDate(dayTo)) {
		// Hien thi alert.
		alert("Invalid BirthDay (From) \nInvalid BirthDay (To)");
		// Dung.
		return;
	}
	// Neu dayFrom sai dinh dang.
	if (!isValidDate(dayFrom)) {
		// Hien thi alert.
		alert("Invalid BirthDay (From)");
		// Dung.
		return;
		// Neu dayTo sai dinh dang.
	} else if (!isValidDate(dayTo)) {
		// Hien thi alert.
		alert("Invalid BirthDay (To)");
		// Dung.
		return;
		// Neu dayFrom > dayTo.
	} else if (!compareDates(dayFrom, dayTo)) {
		// Hien thi alert.
		alert("There is an error the range input of Birthday");
		// Dung.
		return;
	}
	// Submit form.
	form.submit();
}

/**
 * set mode = searchOrder
 * 
 * @returns
 */
function searchUser() {
	// Get form
	var form = document.forms[0];
	// set value mode = export.
	form.elements['mode'].value = "searchUser";
	// Submit form.
	form.submit();
}
function exportFile() {
	// Get form
	var form = document.forms[0];
	// set value mode = export.
	form.elements['mode'].value = "export";
	// Submit form.
	form.submit();
}
/**
 * set mode = searchCustomer
 * 
 * @returns
 */
function searchCustomer() {
	// Get form
	var form = document.forms[0];
	// set value mode = edit.
	form.elements['mode'].value = "searchCustomer";
	// Submit form.
	form.submit();
}
function selectOrder(OrderID) {
	  var form = document.forms[1];
	  form.elements['orderId'].value = OrderID;
	  form.submit();
	}
/**
 * check validate dataString
 * 
 * @returns
 */
function isValidDate(txtDate) {
	// Neu txtDate trong.
	if (txtDate == '')
		return true;
	// Tao chuoi Regex.
	var rxDatePattern = /^(\d{4})(\/|-)(\d{1,2})(\/|-)(\d{1,2})$/;
	// So sanh voi regex.
	var dtArray = txtDate.match(rxDatePattern);
	// Neu dtArray = null.
	if (dtArray == null)
		return false;
	dtMonth = dtArray[3];
	dtDay = dtArray[5];
	dtYear = dtArray[1];
	// Neu thang <1 || thang > 12 .
	if (dtMonth < 1 || dtMonth > 12)
		return false;
	// Neu ngay < 1 || ngay > 31.
	else if (dtDay < 1 || dtDay > 31)
		return false;
	// neu thang la 4 || 6 || 9 || 11 va ngay la 31.
	else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11)
			&& dtDay == 31)
		return false;
	// Neu thang = 2
	else if (dtMonth == 2) {
		// Tinh nam nhuan.
		var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
		// Neu day > 29 || day = 29 va khong phai nhuan.
		if (dtDay > 29 || (dtDay == 29 && !isleap))
			return false;
	}
	return true;
}

/**
 * so sanh ngay.
 * 
 * @param date1
 * @param date2
 * @returns
 */
function compareDates(date1, date2) {
	// Neu input dayFrom va DayTo co data.
	if (!date1 == '' && !date2 == '') {
		return !(date1 > date2)
	}
	return true;
}