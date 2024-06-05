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

function btnSave() {
	listHeader = [];
	setListHeader();
	var hiddenInputSelectB = document.createElement("input");
	hiddenInputSelectB.type = "hidden";
	hiddenInputSelectB.name = "listSelectB";
	hiddenInputSelectB.value = listHeader.join(",");
	document.querySelector("form").appendChild(hiddenInputSelectB);
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