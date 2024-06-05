/**
 * Clear data and set value mode clear.
 * 
 * @returns
 */
function clearData() {
	// Get data from input.
	var customerId = document.getElementById("customerId").value;
	// Neu customerId rong.
	if (customerId == "") {
		// Xoa gia tri o input customerName.
		document.getElementById("customerName").value = "";
		// Xoa gia tri o input birthday.
		document.getElementById("birthday").value = "";
		// Xoa gia tri o input email.
		document.getElementById("email").value = "";
		// Xoa gia tri o input address.
		document.getElementById("address").value = "";
		// Set value option = 0.
		document.getElementById("option").selectedIndex = 0;
		// Xoa error.
		document.getElementById("lblErrorMessage").innerHTML = "";
		return;
	} else {
		// Get form
		var form = document.forms[0];
		// set value mode = clear.
		form.elements['mode'].value = "clear";
		// Submit form.
		form.submit();
	}
}

/**
 * Set value mode logout.
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
 * Set value mode save.
 * 
 * @returns
 */
function save() {
	// Get form
	var form = document.forms[0];
	// set value mode = save.
	form.elements['mode'].value = "save";
	// Submit form.
	form.submit();
}

function cancel() {
	// Get form
	var form = document.forms[0];
	// set value mode = save.
	form.elements['mode'].value = "cancel";
	// Submit form.
	form.submit();
}