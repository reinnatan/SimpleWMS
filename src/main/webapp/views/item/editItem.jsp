<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../header.jsp"></jsp:include>
</head>
<body>

	<form method='post' action='/SimpleWarehousManagementSystem/items/edit-item'>
		<input type="hidden" name="itemId" value="${item.id}" />
		<table>
			<tr><td>Name</td><td><input type='text' name='nameItem' value="${item.name}"></td></tr>
			<tr><td>Date Production</td><td><input type='date' name='dateProduction' value="${dateProduction}"></td></tr>
			<tr><td>Count Item</td><td><input type='text' name='countItem' value="${countItem}"></td></tr>
			<tr><td><input type='submit' id='addItemSubmit'></td></tr>
		</table>
	</form>
</body>
</html>