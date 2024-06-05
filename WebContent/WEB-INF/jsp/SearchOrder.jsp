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
<title>Search Order - InfomationManagement</title>
<link rel="stylesheet" href="css/searchOrder.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/header.css">
<script src="js/searchOrder.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<html:form method="post" action="searchOrderAction.do">
		<input type="hidden" name="mode" value="">
		<input type="hidden" name="customerIdOld" value="${sexOld }">
		<input type="hidden" name="dayFromOld" value="${ dayFromOld}">
		<input type="hidden" name="dayToOld" value="${dayToOld }">
		<div class="dh">&nbsp;Login &gt; Search Order</div>
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
					<c:if test="${u == 'T002'}">
						<a href="#" onclick="searchCustomer()" style="margin-right: 15px">Search Customer</a>
					</c:if>
					<c:if test="${u == 'T006'}">
						<a href="#" onclick="searchUser()" style="margin-right: 15px">Search User</a>
					</c:if>
				</logic:iterate>
				<a href="#" onclick="logOut()">Log out</a>
			</div>
		</div>
		<hr class="kk">
		<div class="box" style="background-color: #ffff99">
			<div style="width: 100px">
				<label>Customer ID</label>
			</div>
			<div style="width: 320px">
				<input class="input " value="${customerId}" maxlength="50"
					name="customerId">
			</div>
			<div style="width: 100px">
				<label>Order date</label>
			</div>
			<div style="width: 320px">
				<input class="input " name="dayFrom" type="text" id="dayFrom"
					maxlength="10" value="${dayFrom}"> ～ <input class="input"
					name="dayTo" type="text" id="dayTo" class="kc" maxlength="10"
					value="${dayTo }">
			</div>
			<div style="float: right">
				<html:button property="search" styleClass="btnSearch"
					onclick="searchOrder()">Search</html:button>
			</div>
		</div>
		<div class="setHeight" style="overflow-y: scroll">
			<table>
				<thead style="position: sticky; top: 0; background-color: #339966;">
					<tr>
						<th style="width: 20%">OrderID</th>
						<th style="width: 20%">CustomerID</th>
						<th style="width: 40%">OrderDate</th>
						<th style="width: 20%">TotalAmount</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate name="orders" id="u">
						<c:if test="${not empty orders}">
							<tr onclick="selectOrder('${u.orderId}')">
								<td>${u.orderId}</td>
								<td>${u.customerId}</td>
								<td>${u.date}</td>
								<td>${u.totalAmount}</td>
							</tr>
						</c:if>
					</logic:iterate>
				</tbody>
			</table>
		</div>
		<div class="errorEx">
			<label id="errorEx">${errorEx}</label>
		</div>
		<div class="btn">
			<button class="btnExport" onclick="exportFile()">Export</button>
		</div>
	</html:form>
	<html:form method="post" action="orderDetailAction.do">
		<input type="hidden" name="orderId" value="">
	</html:form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>