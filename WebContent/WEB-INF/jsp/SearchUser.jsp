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
<title>SearchUser - InfomationManagement</title>
<link rel="stylesheet" href="css/searchUser.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/header.css">
<script src="js/searchUser.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<html:form method="post" action="searchUserAction.do">
		<input type="hidden" name="mode" value="">
		<div class="dh">&nbsp;Login &gt; Search User</div>
		<br>
		<div class="container">
			<div class="welcome">
				<%
					MSTUSER user = (MSTUSER) session.getAttribute("login");
				%>
				<label>Welcome <%=user.getUserName()%></label>
			</div>
			<div class="myLink">
				<logic:iterate name="screenId" id="u">
					<c:if test="${u == 'T008'}">
						<a href="#" onclick="searchOrder()" style="margin-right: 15px">Search
							Order</a>
					</c:if>
					<c:if test="${u == 'T002'}">
						<a href="#" onclick="searchCustomer()" style="margin-right: 15px">Search
							Customer</a>
					</c:if>
				</logic:iterate>
				<a href="#" onclick="logOut()">Log out</a>
			</div>
		</div>
		<hr class="kk">
		<div class="box" style="background-color: #ffff99">
			<div style="width: 100px">
				<label class="lb">User ID</label>
			</div>
			<div class="kc" style="width: 320px">
				<input class="input " value="${userId}" maxlength="50" name="userId">
			</div>
			<div style="width: 100px">
				<label>User Name</label>
			</div>
			<div style="width: 320px">
				<input class="input " value="${userName}" maxlength="50"
					name="userName">
			</div>
			<div style="width: 100px">
				<label>Type</label>
			</div>
			<div style="width: 320px">
				<select class="kc" name="type">
					<option value="" ${ type.equals("") ? 'selected' : ''}></option>
					<option value="0" ${ type.equals("0") ? 'selected' : ''}>Admin</option>
					<option value="1" ${ type.equals("1") ? 'selected' : ''}>Staff</option>
				</select>
			</div>
			<div style="float: right">
				<html:button property="search" styleClass="btnSearch"
					onclick="searchUser()">Search</html:button>
			</div>
		</div>
		<div class="setHeight" style="overflow-y: scroll;">
			<table>
				<thead style="position: sticky; top: 0; background-color: #339966;">
					<tr>
						<th style="width: 5%"></th>
						<th style="width: 30%">UserID</th>
						<th style="width: 30%">UserName</th>
						<th style="width: 30%">Type</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate name="users" id="u">
						<c:if test="${not empty users}">
							<tr onclick="selectUser('${u.userId}')">
								<td></td>
								<td>${u.userId}</td>
								<td>${u.userName}</td>
								<td>${u.type}</td>
							</tr>
						</c:if>
					</logic:iterate>
				</tbody>
			</table>
		</div>
	</html:form>
	<html:form method="post" action="settingMenuAction.do">
		<input type="hidden" name="userID" value="">
	</html:form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>