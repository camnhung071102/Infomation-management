<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - InfomationManagement</title>
<link rel="stylesheet" href="css/Login.css">
<link rel="stylesheet" href="css/header.css">
<script src="js/login.js"></script>
<style type="text/css">
@charset "UTF-8";

body {
	background-color: #ccffff;
	font-family: MS pgothic;
	margin: 20px 20px 20px 20px;
}

header {
	font-family: 'DM Serif Display', serif;
	color: #ff0000;
}

h3 {
	magrin-top: 0;
}

button {
	font-family: MS pgothic;
	width: 70px;
}

.container {
	margin: 150px 600px;
}

.tform {
	text-align: center;
	color: #3366ff;
	font-weight: 1000;
	font-size: 30px;
}

label {
	color: #ff0000;
	margin-top: 0;
	font-weight: bold;
	font-size: 14px;
}

.input {
	margin-left: 10px;
	border-bottom: 1px solid black;
	border-right: 1px solid black;
	border-top: 2px solid black;
	border-left: 2px solid black;
}

#btnLogin {
	font-size: 15px;
	 width : 70px;
	height: 21px;
	margin-right: 30px;
	margin-top: 15px;

}

#btnClear {
font-family: MS pgothic;
	font-size: 15px;
	width: 70px;
	margin-right: 35px;
}

table {
	margin-right: auto;
	margin-left: auto;
	margin-top: auto;
	margin-bottom: auto;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 98%;
}

p {
	margin: 0 0;
}
</style>
<script type="text/javascript">
	function clearData() {
		document.getElementById("txtUserID").value = null;
		document.getElementById("txtPassword").value = null;
		document.getElementById("lblErrorMessage").innerHTML = "";
		return;
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="screenName" style="font-size: 20px">&nbsp;Login</div>
	<br />
	<br />
	<div class="container">
		<html:form method="post" target="myform">
			<table>
				<tr>
					<td colspan="4" align="center" class="tform">LOGIN</td>
				</tr>
				<tr height="50px">
					<td colspan="4" align="center"><label id="lblErrorMessage"><html:errors/></label></td>
				</tr>
				<tr>
					<td width=20%>UserId:</td>
					<td colspan="3" align="left"><input class="input" style="width: 70%" maxlength="8" type="text" name="userId" id="txtUserID"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td colspan="3" align="left"><input class="input" style="width: 70%" maxlength="8" type="password" name="password" id="txtPassword"></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" align="right">
						<button ID="btnLogin" name="mode" value="login">Login</button>
						<html:button styleId="btnClear" property="clear" onclick="clearData()">Clear</html:button>
					</td>
				</tr>
			</table>
		</html:form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>