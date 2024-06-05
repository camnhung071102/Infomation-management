<%@page import="fjs.cs.dto.MSTUSER"%>
<%@page import="fjs.cs.model.SearchBean"%>
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
<title>Search - InfomationManagement</title>
<link rel="stylesheet" href="css/search.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/header.css">
<script src="js/search.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<html:form method="post" action="searchAction.do">
		<input type="hidden" name="mode" value="">
		<input type="hidden" name="currentPage" value="${currentPage}">
		<input type="hidden" name="customerNameOld"
			value="${customerNameOld }">
		<input type="hidden" name="sexOld" value="${sexOld }">
		<input type="hidden" name="dayFromOld" value="${ dayFromOld}">
		<input type="hidden" name="dayToOld" value="${dayToOld }">
		<div class="dh">&nbsp;Login &gt; Search Customer</div>
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
					<c:if test="${u == 'T006'}">
						<a href="#" onclick="searchUser()" style="margin-right: 15px">Search
							User</a>
					</c:if>
				</logic:iterate>
				<a href="#" onclick="logOut()">Log out</a>
			</div>
		</div>
		<hr class="kk">
		<div class="box" style="background-color: #ffff99">
			<div style="width: 180px">
				<label class="lb">Customer Name</label>
			</div>
			<div class="kc" style="width: 320px">
				<input class="input " value="${customerName}" maxlength="50"
					name="customerName">
			</div>
			<div style="width: 50px">
				<label>Sex</label>
			</div>
			<div style="width: 320px">
				<select class="kc" name="sex">
					<option value="" ${ sex.equals("") ? 'selected' : ''}></option>
					<option value="0" ${ sex.equals("0") ? 'selected' : ''}>Male</option>
					<option value="1" ${ sex.equals("1") ? 'selected' : ''}>Female</option>
				</select>
			</div>
			<div style="width: 100px">
				<label>Birthday</label>
			</div>
			<div style="width: 320px">
				<input class="input " name="dayFrom" type="text" id="dayFrom"
					maxlength="10" value="${dayFrom}"> ～ <input class="input"
					name="dayTo" type="text" id="dayTo" class="kc" maxlength="10"
					value="${dayTo }">
			</div>
			<div style="float: right">
				<html:button property="search" styleClass="btnSearch"
					onclick="searchCustomers()">Search</html:button>
			</div>
		</div>
		<div class="bapage">
			<div class="before">
				<button class="btnpage" id="btnPrevious" onclick="previousPage()"
					${disablePrevious ? 'disabled' :'' }>&lt;</button>
				<button class="btnpage" id="btnFirst" onclick="firstPage()"
					style="float: left" ${disableFirst ? 'disabled' :'' }>&lt;&lt;</button>
				<label class="bf">Previous</label>
			</div>
			<div class="after">
				<label class="af">Next</label>
				<button class="btnpage" id="btnLast" onclick="lastPage()"
					${disableLast ? 'disabled' :'' } style="float: right">&gt;&gt;</button>
				<button class="btnpage" id="btnNext" onclick="nextPage()"
					${disableNext ? 'disabled' :'' }>&gt;</button>
			</div>
		</div>
		<div class="setHeight">
			<table>
				<tr style="background: #339966">
					<c:if test="${not empty listHeader}">
						<logic:iterate name="listHeader" id="u">
							<c:if test="${u == 'Checkbox'}">
								<td width="2%"><input type="checkbox" style="width: 20px"
									id="headerCheckbox" onchange="selectAllRows()"></td>
							</c:if>
							<c:if test="${u != 'Checkbox'}">
								<td>${u}</td>
							</c:if>
						</logic:iterate>
					</c:if>
				</tr>
				<logic:iterate name="customers" id="u">
					<c:if test="${not empty customers}">
						<tr>
							<logic:iterate name="listHeader" id="a">
								<c:if test="${a == 'Checkbox'}">
									<td><input type="checkbox" style="width: 20px"
										class="rowCheckbox" onchange="updateHeaderCheckbox()"></td>
								</c:if>
								<c:if test="${a == 'CustomerID'}">
									<td data-column="CustomerID"><a href="#"
										onclick="getData(${u.customerID})">${u.customerID}</a></td>
								</c:if>
								<c:if test="${a == 'CustomerName'}">
									<td>${u.customerName}</td>
								</c:if>
								<c:if test="${a == 'Sex'}">
									<td>${u.sex}</td>
								</c:if>
								<c:if test="${a == 'Birthday'}">
									<td>${u.birthday}</td>
								</c:if>
								<c:if test="${a == 'Address'}">
									<td>${u.address}</td>
								</c:if>
							</logic:iterate>
						</tr>
					</c:if>
				</logic:iterate>
			</table>
		</div>
		<div class="errorEx">
			<label id="errorEx">${errorEx}</label>
		</div>
		<div class="btn">
			<html:button styleClass="btnAdd" property="add" onclick="addNew()">Add New</html:button>
			<button class="btnDelete" onclick="deleteCustomers()"
				${disableDelete ? 'disabled' :'' }>Delete</button>
			<button class="btnExport" onclick="exportFile()">Export</button>
			<button class="btnImport" onclick="importFile()">Import</button>
			<button type="button" class="btnSetting" onclick="setting()">Setting
				Header</button>
		</div>
	</html:form>
	<html:form method="post" action="editAction.do">
		<input type="hidden" name="customerId" value="">
	</html:form>
	<html:form method="post" action="settingAction.do">
		<input type="hidden" name="headerList" value="${listHeader}">
	</html:form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>