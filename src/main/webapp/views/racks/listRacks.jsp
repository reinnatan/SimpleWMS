<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../header.jsp"></jsp:include>
<script>
 	$(document).ready(function() {
	 	new DataTable('#example', {
		    ajax: '/SimpleWarehousManagementSystem/racks/list-racks-data',
		    columns:[
		    	{data:0},
		    	{data:1},
		    	{data:2},
		    	{data:3},
		    	{data:4},
		    	{
		            data: 0,
		            render: function ( data, type, row, meta ) {
		                return "<a href=/SimpleWarehousManagementSystem/racks/edit?id="+(data)+"><span class='edit'><i class='fa fa-pencil'></i></span></a>&nbsp;&nbsp;<a href=/SimpleWarehousManagementSystem/racks/delete?id="+(data)+"><span class='edit'><i class='fa fa-trash'></i></span></a>";
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
                <th>Count</th>
                <th>Date Inbound</th>
                <th>Date Outbound</th>
                <th></th>
            </tr>
        </thead>
    </table>
</body>
</html>