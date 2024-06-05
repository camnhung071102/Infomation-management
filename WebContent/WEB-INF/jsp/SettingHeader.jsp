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
<meta charset="ISO-8859-1">
<title>Setting Header - InfomationManagement</title>
<link rel="stylesheet" href="css/setting.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/header.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script src="js/setting.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<html:form method="post" action="settingAction.do">
		<input type="hidden" name="mode" />
		<div class="dh">&nbsp;Login &gt; Search Customer &gt; Setting
			Header Customer List</div>
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
		<div class="message">
			<p style="color: red;" class="error" id="error"></p>
		</div>
		<table>
			<tr>
				<td rowspan="2"><select style="width: 130px; height: 200px"
					size="5" id="selectA" multiple>
						<c:if test="${ not empty listSelectA}">
							<c:forEach var="option" items="${listSelectA}">
								<option value="${option}">${option}</option>
							</c:forEach></c:if>
				</select></td>
				<td style="width: 60px"><button id="buttonRight"
						class="btnRight" onclick="btnRight()" type="button">
						<i class="material-icons" style="font-size: 15px">arrow_forward</i>
					</button></td>
				<td rowspan="2"><select style="width: 130px; height: 200px"
					size="5" id="selectB" multiple>
						<c:forEach var="optionInit" items="${listInit}">
							<option value="${optionInit}">${optionInit}</option>
						</c:forEach>
				</select></td>
				<td style="width: 60px"><button class="btnUp" onclick="btnUp()"
						type="button">
						<i class="material-icons" style="font-size: 15px">arrow_upward</i>
					</button></td>
			</tr>
			<tr>
				<td><button class="btnLeft" onclick="btnLeft()" type="button">
						<i class="material-icons" style="font-size: 15px">arrow_back</i>
					</button></td>
				<td><button class="btnDown" onclick="btnDown()" type="button">
						<i class="material-icons" style="font-size: 15px">arrow_downward</i>
					</button></td>
			</tr>
			<tr style="height: 100px">
				<td colspan="2"><button class="btnSave" onclick="btnSave()"
						type="button">Save</button></td>
				<td colspan="2"><button class="btnCancel" onclick="btnCancel()">Cancel</button></td>
			</tr>
		</table>
	</html:form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>