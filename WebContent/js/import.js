function getName() {
 document.getElementById('import').onchange = function() {
  document.getElementById('getName').value = this.files[0].name;

  var form = document.forms[0];
  form.elements['extension'].value = this.files[0].name;

 };
}
function importFileCsv() {
 var form = document.forms[0];
 form.elements['mode'].value = "import";
 form.submit();
}
function cancelImport() {
 var form = document.forms[0];
 form.elements['mode'].value = "cancel";
 form.submit();
}
function logOut() {
 var form = document.forms[0];
 form.elements['mode'].value = "logOut";
 form.submit();
}