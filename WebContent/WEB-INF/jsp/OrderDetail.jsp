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
<title>Order Detail - InfomationManagement</title>
<link rel="stylesheet" href="css/orderDetail.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/header.css">
<script src="js/orderDetail.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<html:form method="post" action="orderDetailAction.do">
		<input type="hidden" name="mode" value="">
		<div class="dh">&nbsp;Login &gt; Search Order &gt; Order Detail</div>
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
		<div class="orderId"><p>ORDERID  ${orderId}<p></div>	
		<div class="setHeight" >
			<table>
				<thead style="position: sticky; top: 0; background-color: #339966;">
					<tr>
						<th style="width:20%">OrderDetailID</th>
						<th style="width:20%">ProductID</th>
						<th style="width:40%">Quatity</th>
						<th style="width:20%">Price</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate name="order" id="u">
						<c:if test="${not empty order}">
							<tr >
								<td>${u.orderDetailId}</td>
								<td>${u.productId}</td>
								<td>${u.quantity}</td>
								<td>${u.price}</td>
							</tr>
						</c:if>
					</logic:iterate>
				</tbody>
			</table>
		</div>
		<div class="btn">
			<button class="btnBack" onclick="back()">Back</button>
		</div>
	</html:form>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>