<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <jsp:include page="../../header.jsp"></jsp:include>
 <script>
 	$(document).ready(function() {
	 	new DataTable('#example', {
		    ajax: '/SimpleWarehousManagementSystem/list-warehouse-data',
		    columns:[
		    	{data:0},
		    	{data:1},
		    	{data:2},
		    	{data:3},
		    	{
		            data: 0,
		            render: function ( data, type, row, meta ) {
		                return "<a href=/SimpleWarehousManagementSystem/edit?id="+(data)+"><span class='edit'><i class='fa fa-pencil'></i></span></a>&nbsp;&nbsp;<a href=/SimpleWarehousManagementSystem/delete?id="+(data)+"><span class='edit'><i class='fa fa-trash'></i></span></a>";
		            }    
		           
		        }
		    ]
		});
 	});
 </script>
</head>
<body>
	<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Phone</th>
                <th></th>
            </tr>
        </thead>
    </table>
</body>
</html>