<%@page import="fjs.cs.dto.MSTUSER"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Import - Training</title>
<link rel="stylesheet" href="css/import.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/header.css">
<script src="js/import.js"></script>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<html:form method="post" action="importAction.do"
		enctype="multipart/form-data">
		<input type="hidden" name="extension" />
		<input type="hidden" name="mode" />
		<div class="dh">&nbsp;Login &gt; Search Customer &gt; Import Customer</div>
		<br>
		<div class="container">
			<div class="welcome">
				<%
					MSTUSER user = (MSTUSER) session.getAttribute("login");
				%>
				<label>Welcome <%=user.getUserName()%></label>
			</div>
			<div class="myLink">
				<a href="#" onclick="logOut()">Log out</a>
			</div>
		</div>
		<hr class="kk">
		<div class="browser">
			<input id="getName" /> 
			<label for="import" class="upload" onclick="getName()">Browser</label> 
			<input id="import" class="importFile" name="uploadFile" type="file" maxlength="50" />
		</div>
		<div class="import">
			<button class="btnImport" onclick="importFileCsv()">Import</button>
			<button class="btnCancel" onclick="cancelImport()">Cancel</button>
		</div>
		<div class="error">
			<html:errors />
			<label></label>
		</div>
	</html:form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>