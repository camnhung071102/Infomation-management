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
function searchUser() {
	// Get form
	var form = document.forms[0];
	// set value mode = logOut.
	form.elements['mode'].value = "search";
	// Submit form.
	form.submit();
}

/**
 * set mode = searchOrder
 * 
 * @returns
 */
function searchOrder() {
	// Get form
	var form = document.forms[0];
	// set value mode = export.
	form.elements['mode'].value = "searchOrder";
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
function selectUser(userID) {
	  var form = document.forms[1];
	  form.elements['userID'].value = userID;

	  form.submit();
	}
