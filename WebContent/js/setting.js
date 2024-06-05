var listHeader = [];
var listSelect = [];

window.addEventListener('load', function() {
	setListHeader();
	setListSelectA();
	disableButton();
})
function disableButton() {
	var selectA = document.getElementById('selectA');
	var selectB = document.getElementById('selectB');
	var btnRight = document.getElementById('buttonRight');
	if (selectA.options.length == 0) {
		btnRight.disabled = true;
	} else {
		btnRight.disabled = false;
	}

}
function setListHeader() {
	var selectB = document.getElementById('selectB');
	for (var i = 0; i < selectB.options.length; i++) {
		var option = selectB.options[i];
		listHeader.push(option.value);
	}
}
function setListSelectA() {
	var selectA = document.getElementById('selectA');
	for (var i = 0; i < selectA.options.length; i++) {
		var option = selectA.options[i];
		if (!option.value == "") {
			listSelect.push(option.value);
		}
	}
}

function btnRight() {
	var error = document.getElementById('error');
	error.innerText = '';
	var selectA = document.getElementById('selectA');
	var selectB = document.getElementById('selectB');
	var check = false;
	var i = 0;
	while (i < selectA.options.length) {
		if (selectA.options[i].selected) {
			var option = selectA.options[i];
			check = true;
			selectB.appendChild(option);
			listHeader.push(option.value);
		} else {
			i++;
		}
	}
	if (check == false) {
		error.innerText = 'Please select to move!';
	}

	disableButton();
	return;
}

function btnLeft() {
	var error = document.getElementById('error');
	error.innerText = '';
	var selectA = document.getElementById('selectA');
	var selectB = document.getElementById('selectB');
	var check = false;
	var i = 0;
	while (i < selectB.options.length) {
		if (selectB.options[i].selected) {
			var option = selectB.options[i];
			if (option.value == 'Checkbox' || option.value == 'CustomerID') {
				error.innerText = option.text + ' cannot be removed!';
				return;
			} else {
				check = true;
				selectA.appendChild(option);
			}
		} else {
			i++;
		}
	}
	if (check == false) {
		error.innerText = 'Please select to move!';
	}
	disableButton();
	return;
}

function btnDown() {
	var error = document.getElementById('error');
	error.innerText = '';
	var check = false;
	var selectB = document.getElementById('selectB');
	for (var i = 0; i < selectB.options.length; i++) {
		if (selectB.options[i].selected) {
			if (i != selectB.options.length - 1) {
				check = true;
				var temp = selectB.options[i];
				selectB.options[i] = new Option(selectB.options[i + 1].text,
						selectB.options[i + 1].value);
				selectB.options[i + 1] = new Option(temp.text, temp.value);
				for (var j = listHeader.length - 2; j >= 0; j--) {
					var tempList = listHeader[j];
					if (temp.value == listHeader[j]) {
						listHeader[j] = listHeader[j + 1];
						listHeader[j + 1] = tempList;
					}
				}
			}
		}
	}
	if (check == false) {
		error.innerText = 'Please select to move!';
	}
	return;
}

function btnUp() {
	var error = document.getElementById('error');
	error.innerText = '';
	var check = false;
	var selectB = document.getElementById('selectB');
	for (var i = 0; i < selectB.options.length; i++) {
		if (selectB.options[i].selected) {
			if (i != 0) {
				check = true;
				var temp = selectB.options[i];
				selectB.options[i] = new Option(selectB.options[i - 1].text,
						selectB.options[i - 1].value);
				selectB.options[i - 1] = new Option(temp.text, temp.value);
				for (var j = 0; j < listHeader.length; j++) {
					var tempList = listHeader[j];
					if (temp.value == listHeader[j]) {
						listHeader[j] = listHeader[j - 1];
						listHeader[j - 1] = tempList;
					}
				}
			}
		}
	}
	if (check == false) {
		error.innerText = 'Please select to move!';
	}
	return;
}
function btnSave() {
	listHeader = [];
	setListHeader();
	var hiddenInput = document.createElement("input");
	hiddenInput.type = "hidden";
	hiddenInput.name = "listHeader";
	hiddenInput.value = listHeader.join(",");
	document.querySelector("form").appendChild(hiddenInput);
	listSelect = [];
	setListSelectA();
	var hiddenInputSelectA = document.createElement("input");
	hiddenInputSelectA.type = "hidden";
	hiddenInputSelectA.name = "listSelectA";
	hiddenInputSelectA.value = listSelect.join(",");
	document.querySelector("form").appendChild(hiddenInputSelectA);
	// Get form
	var form = document.forms[0];
	// set value mode = delete.
	form.elements['mode'].value = "save";
	// Submit form.
	form.submit();
}

function btnCancel() {
	var form = document.forms[0];
	// set value mode = delete.
	form.elements['mode'].value = "cancel";
	// Submit form.
	form.submit();
}

function logOut() {
	// Get form
	var form = document.forms[0];
	// set value mode = logOut.
	form.elements['mode'].value = "logOut";
	// Submit form.
	form.submit();
}