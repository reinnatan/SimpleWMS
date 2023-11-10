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
	//processing items in rack
	var data = "";
	for(i=0; i<d['item'].length; i++){
		var dateProd = new Date(d['item'][i]['dateProduction']);
		
		const formatted = dateProd.getFullYear()+"-"+dateProd.getMonth()+"-"+dateProd.getDate();
		
		data = data.concat('<tr><td>'+d['item'][i]['id']+'</td><td>'+d['item'][i]['name']+'</td><td>'+formatted+'</td></tr>');
	}
	
	var finalize = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	   	'<th>ID Item</th><th>Name Item</th><th>Date Production</th>'+
	   	data
	   	+'</table>';
	   	
	console.log("data"+data);   	
	console.log("finalize"+finalize);
	
    // `d` is the original data object for the row
    return finalize;
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
		    	{data:'id'},
		    	{data:'name'},
		    	{data:'count'},
		    	{data:'dateInbound'},
		    	{data:'dateOutbound'},
		    	
		    	{
		    		data:'id',
		    		render: function ( data, type, row, meta ) {
			                return "<a href=/SimpleWarehousManagementSystem/racks/input-inbound?id="+(data)+"><span class='edit'><i class='fa fa-plus'></i></span></a>";
			        }    
		    	},
		    	
		    	{
		            className: 'dt-control',
		            orderable: true,
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
                 <th>Action</th>
                <th></th>
            </tr>
        </thead>
    </table>
</body>
</html>