<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>

	<form method="post" action="/SimpleWarehousManagementSystem/warehouse">
		<input type="hidden" name="id"  value='${listWarehouse[0]}'/>
		<input type="hidden" name="method" value="update" />
		<table>
			<tr><td>Name</td><td><input type = "text" name="name" value='${listWarehouse[1]}'/></td></tr>
			<tr><td>Address</td><td><input type = "text" name="address" value='${listWarehouse[2]}'/></td></tr>
			<tr><td>Phone</td><td><input type = "text" name="phone"  value='${listWarehouse[3]}'/></td></tr>
			<tr><td><input type="submit" value="update"></td><td></td></tr>
		</table>
	</form>
</body>
</html>