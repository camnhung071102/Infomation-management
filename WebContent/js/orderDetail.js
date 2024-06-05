
function back() {
	var form = document.forms[0];
	form.elements['mode'].value = "cancel";
	form.submit();
}

/**
 * set mode = searchUser.
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


