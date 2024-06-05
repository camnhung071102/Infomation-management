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
<title>Setting Menu - InfomationManagement</title>
<link rel="stylesheet" href="css/settingMenu.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/header.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script src="js/settingMenu.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<html:form method="post" action="settingMenuAction.do">
		<input type="hidden" name="mode" />
		<input type="hidden" name="userID" value="${userID}"/>
		<div class="dh">&nbsp;Login &gt; Search User &gt; Setting
			Menu</div>
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
							<c:forEach var="screen" items="${listSelectA}">
								<option value="${screen.screenId}">${screen.screenName}</option>
							</c:forEach></c:if>
				</select></td>
				<td style="width: 60px"><button id="buttonRight"
						class="btnRight" onclick="btnRight()" type="button">
						<i class="material-icons" style="font-size: 15px">arrow_forward</i>
					</button></td>
				<td rowspan="2"><select style="width: 130px; height: 200px"
					size="5" id="selectB" multiple>
						<c:forEach var="screen1" items="${listSelectB}">
							<option value="${screen1.screenId}">${screen1.screenName}</option>
						</c:forEach>
				</select></td>
				<td style="width: 60px"></td>
			</tr>
			<tr>
				<td><button class="btnLeft" onclick="btnLeft()" type="button">
						<i class="material-icons" style="font-size: 15px">arrow_back</i>
					</button></td>
				<td></td>
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