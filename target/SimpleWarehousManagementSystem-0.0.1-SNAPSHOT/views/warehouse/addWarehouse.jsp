<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../header.jsp"></jsp:include>
  </head>
<body>
	
	<form method="post" action="/SimpleWarehousManagementSystem/warehouse">
		<input type="hidden" name="method" value="add" />
		<table>
			<tr><td>Name</td><td><input type = "text" name="name"/></td></tr>
			<tr><td>Address</td><td><input type = "text" name="address"/></td></tr>
			<tr><td>Phone</td><td><input type = "text" name="phone"/></td></tr>
			<tr><td><input type="submit" value="save"></td><td></td></tr>
		</table>
		
	</form>

</body>
</html>