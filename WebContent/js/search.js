// Khoi tao mang.
var selectedRecords = [];

/**
 * selectAllRows
 * 
 * @returns
 */
function selectAllRows() {
	// Get checkbox id headerCheckbox
	var headerCheckbox = document.getElementById('headerCheckbox');
	// Get checkbox id rowCheckbox
	var rowCheckboxes = document.getElementsByClassName('rowCheckbox');
	// Lap tung rowCheckboxes
	for (var i = 0; i < rowCheckboxes.length; i++) {
		// RowCheckboxes is checked neu headerCheckbox is checked
		rowCheckboxes[i].checked = headerCheckbox.checked;
	}
}

/**
 * updateHeaderCheckbox
 * 
 * @returns
 */
function updateHeaderCheckbox() {
	// Get checkbox id headerCheckbox
	var headerCheckbox = document.getElementById('headerCheckbox');
	// Get checkbox id rowCheckbox
	var rowCheckboxes = document.getElementsByClassName('rowCheckbox');
	// Khoi tao bien allChecked.
	var allChecked = true;
	// Lap tung rowCheckboxes.
	for (var i = 0; i < rowCheckboxes.length; i++) {
		// neu rowCheckboxes !checked
		if (!rowCheckboxes[i].checked) {
			// gan gia tri allChecked
			allChecked = false;
			// Dung lap.
			break;
		}
	}
	// gan gia tri headerCheckbox.checked =allChecked.
	headerCheckbox.checked = allChecked;
}

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
 * Get cac id duoc checked.
 * 
 * @returns
 */
function deleteCustomers() {
	// Get value checkbox id rowCheckbox
	var checkboxes = document.getElementsByClassName("rowCheckbox");
	// Lap tat ca checkboxes
	for (var i = 0; i < checkboxes.length; i++) {
		// neu checkboxes[i] is checked.
		if (checkboxes[i].checked) {
			// Lay cot thu 2 (customerId)
			var customerID = checkboxes[i].closest("tr").querySelector("td[data-column='CustomerID']").textContent;
			// Them customerID vao mang.
			selectedRecords.push(customerID);
		}
	}
	// Thong bao loi khi khong chon.
	if (selectedRecords.length == 0) {
		alert("Please select the row.");
		return;
	}
	// Tạo một input hidden mới và gán giá trị mảng vào
	var hiddenInput = document.createElement("input");
	// Input Type
	hiddenInput.type = "hidden";
	// Input name
	hiddenInput.name = "listIdDelete";
	// Input value
	hiddenInput.value = selectedRecords.join(",");
	// Thêm input hidden vào biểu mẫu
	document.querySelector("form").appendChild(hiddenInput);
	// Get form
	var form = document.forms[0];
	// set value mode = delete.
	form.elements['mode'].value = "delete";
	// Submit form.
	form.submit();
}

/**
 * Set mode and validate
 * 
 * @returns
 */

function searchCustomers() {
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
 * set mode = export
 * 
 * @returns
 */
function exportFile() {
	// Get form
	var form = document.forms[0];
	// set value mode = export.
	form.elements['mode'].value = "export";
	// Submit form.
	form.submit();
}

/**
 * set mode = import
 * 
 * @returns
 */
function importFile() {
	// Get form
	var form = document.forms[0];
	// set value mode = export.
	form.elements['mode'].value = "import";
	// Submit form.
	form.submit();
}

/**
 * set mode = setting
 * 
 * @returns
 */
function setting() {
	// Get form
	var form2 = document.forms[2];
	// Submit form.
	form2.submit();
}

/**
 * set mode = previous
 * 
 * @returns
 */
function previousPage() {
	// Get form
	var form = document.forms[0];
	// set value mode = previous.
	form.elements['mode'].value = "previous";
	// Submit form.
	form.submit();

}
/**
 * set mode = first
 * 
 * @returns
 */
function firstPage() {
	// Get form
	var form = document.forms[0];
	// set value mode = first.
	form.elements['mode'].value = "first";
	form.submit();
}

/**
 * set mode = last
 * 
 * @returns
 */
function lastPage() {
	// Get form
	var form = document.forms[0];
	// set value mode = last.
	form.elements['mode'].value = "last";
	// Submit form.
	form.submit();
}

/**
 * set mode = next
 * 
 * @returns
 */
function nextPage() {
	// Get form
	var form = document.forms[0];
	// set value mode = next.
	form.elements['mode'].value = "next";
	// Submit form.
	form.submit();
}

/**
 * set mode = add
 * 
 * @returns
 */
function addNew() {
	// Get form
	var form = document.forms[0];
	// set value mode = edit.
	form.elements['mode'].value = "edit";
	// Submit form.
	form.submit();
}

function searchUser() {
	// Get form
	var form = document.forms[0];
	// set value mode = edit.
	form.elements['mode'].value = "searchUser";
	// Submit form.
	form.submit();
}
function searchOrder() {
	// Get form
	var form = document.forms[0];
	// set value mode = edit.
	form.elements['mode'].value = "searchOrder";
	// Submit form.
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