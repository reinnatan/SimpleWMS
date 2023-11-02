<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../../header.jsp"></jsp:include>
<script>
function format ( d ) {
	console.log(d);
	console.log(d[5]['id']);
	console.log(d[5]['name']);
	console.log(d[5]['dateProduction']);
	
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>ID Item:</td>'+
            '<td>'+d[5]['id']+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Name Item:</td>'+
            '<td>'+d[5]['name']+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extra info:</td>'+
            '<td>And any further details here (images etc)...</td>'+
        '</tr>'+
    '</table>';
	}

 	$(document).ready(function() {
 		// Add event listener for opening and closing details
 	   $('#example').on('click', 'tbody td.dt-control', function () {
 	        var tr = $(this).closest('tr');
 	        var row = table.row( tr );
 	 
 	        if ( row.child.isShown() ) {
 	            // This row is already open - close it
 	            row.child.hide();
 	        }
 	        else {
 	            // Open this row
 	            row.child( format(row.data()) ).show();
 	        }
 	    });
 	 
 	    $('#example').on('requestChild.dt', function(e, row) {
 	        row.child(format(row.data())).show();
 	    });
 		
 		
	 	var table = new DataTable('#example', {
		    ajax: '/SimpleWarehousManagementSystem/inbound-outbound/list-inbound-outbound-data/',
		    columns:[
		    	{data:0},
		    	{data:1},
		    	{data:2},
		    	{data:3},
		    	{data:4},
		    	
		    	{
		            className: 'dt-control',
		            orderable: false,
		            data: null,
		            defaultContent: ''
		        }
		    ]
		});
	 	
 		
	 	table.on('stateLoaded', (e, settings, data) => {
	        for(var i = 0; i < data.childRows.length; i++) {
	            var row = table.row(data.childRows[i]);
	            row.child(format(row.data())).show();
	        }
	    })
	    
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