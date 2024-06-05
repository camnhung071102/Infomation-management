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
<meta charset="ISO-8859-1">
<title>Search - InfomationManagement</title>
<link rel="stylesheet" href="css/edit.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/header.css">
<script src="js/edit.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<form method="post" action="editAction.do">
		<input type="hidden" name="mode" value=""> 
		<input type="hidden" name="screen" value="${screen}">
		<div class="dh">
			Login &gt; Search Customer &gt; ${screen} Customer Info
		</div>
		<br> <input type="hidden" id="deleteRow">
		<div class="container">
			<div class="welcome">
				<%
					MSTUSER user = (MSTUSER) session.getAttribute("login");
				%>
				<label>Welcome <%=user.getUserName()%></label>
			</div>
			<div class="myLink">
				<a href="#" onclick="logOut()">Log out </a>
			</div>
		</div>
		<div class="kk">
			<p style="color: white" class="edit">${screen}</p>
		</div>
		<div class="error" align="center">
			<label id="lblErrorMessage"><html:errors /></label>
		</div>
		<table>
			<tr height="20px">
			</tr>
			<tr>
				<td style="width: 42%"></td>
				<td style="width: 8%">Customer Id</td>
				<td style="width: 40%"><label>${customerId}<input type="hidden" name="customerId" id ="customerId" value="${customerId}"></label></td>
				<td style="width: 10%"></td>
			</tr>
			<tr>
				<td></td>
				<td>Customer Name</td>
				<td><input name="customerName" class="input" id="customerName" value="${customerName}" maxlength="50"></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>Sex</td>
				<td><select id="option" class="input" name="sex">
						<option value="" ${ sex.equals("") ? 'selected' : ''}></option>
						<option value="0" ${ sex.equals("0") ? 'selected' : ''}>Male</option>
						<option value="1" ${ sex.equals("1") ? 'selected' : ''}>Female</option>
				</select></td>
				<td></td>
			<tr>
				<td></td>
				<td>Birthday</td>
				<td><input id="birthday" name="birthday" style="width: 120px" value="${birthday}" maxlength="10" class="input"></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>Email</td>
				<td><input id="email" class="input" name="email" value="${email}" maxlength="40"></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td style="padding: 0px 40px 40px 0px;">Address</td>
				<td><textarea style="width: 300px; height: 50px; resize: none" id="address" rows="3" name="address" maxlength="256">${address}</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2">
					<button class="save" id="button" value="save" onclick="save()">Save</button>
					<html:button property="clear" styleId="button" onclick="clearData()">Clear</html:button>
					<button class="cancel" id="button" value="save" onclick="cancel()">Cancel</button>
				</td>
				<td></td>
			</tr>
		</table>
	</form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>