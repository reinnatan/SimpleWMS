<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../header.jsp"></jsp:include>
  </head>
<body>
	
	<form method='post' id='formAddItem' action='/SimpleWarehousManagementSystem/items/add-item'>
			<table>
				<tr><td>Name</td><td><input type='text' name='nameItem'></td></tr>
				<tr><td>Date Production</td><td><input type='date' name='dateProduction'></td></tr>
				<tr><td>Count Item</td><td><input type='text' name='countItem'></td></tr>
				<tr><td><input type='submit' id='addItemSubmit'></td></tr>
			</table>
	</form>

</body>
</html>