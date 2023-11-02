<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../header.jsp"></jsp:include>
<script>
	$(document).ready(function(){
		//function for called list racks
		$("#showRackId").click(function(){
			fetch('/SimpleWarehousManagementSystem/racks/list-racks-data')
		      .then(response => {
		        if (!response.ok) {
		        	Swal.showValidationMessage(
		        		new Error(response.statusText)
		  			)	
		        }
		        return response.json();
		      }).then((data) => {
		    	let rows = "";
		      	data.data.map(rack => {
		      		let data = "<tr><td><input type='radio' id="+rack[0]+" name='idRack'/></td><td>"+rack[1]+"</td><td>"+rack[2]+"</td><td>"+rack[3]+"</td><td>"+rack[4]+"</td></tr>";
		      		rows = rows+data;
		      	})
		      	
		      	let finalHtml = "<table><th></th><th>Name</th><th>Count</th><th>Date Inbound</th><th>Date Outbound</th>"
		      	+rows+"</table>";
		      	Swal.fire({
					  title: '<strong>List Racks</strong>',
					  icon: 'info',
					  html: finalHtml,
					  showCloseButton: true,
					  showCancelButton: true,
					  focusConfirm: false,
					  width: '650px',
					  confirmButtonText:
					    '<i class="fa fa-check"></i> OK',
					  cancelButtonText:
					    '<i class="fa fa-times">Cancel</i>',
					}).then(function(result){
						if(result.value){
							let value = $('input[name="idRack"]:checked').attr('id');
							$("#rackId").val(value);
				        }else if(result.dismiss == 'cancel'){
				         	console.log("dismiss called");  
				        }
					});
		      }).catch(error => {
		        Swal.showValidationMessage(
		          `Request failed: ${error}`
		        )
		      })
		});
		
		
		//function for called list item
		$("#showItemId").click(function(){
			fetch('/SimpleWarehousManagementSystem/items')
		      .then(response => {
		        if (!response.ok) {
		        	Swal.showValidationMessage(
		        		new Error(response.statusText)
		  			)	
		        }
		        return response.json();
		      }).then((data) => {
		    	let rows = "";
		    	console.log(data);
		      	data.data.map(rack => {
		      	
		      		let data = "<tr><td><input type='radio' id="+rack[0].id+" name='idRack'/></td><td>"+rack[0].name+"</td><td>"+rack[0].dateProduction+"</td></tr>";
		      		rows = rows+data;
		      	})
		      	
		      	let finalHtml = "<table><th></th><th>Name</th><th>Date Production</th>"
		      	+rows+"</table>";
		      	Swal.fire({
					  title: '<strong>List Racks</strong>',
					  icon: 'info',
					  html: finalHtml,
					  showCloseButton: true,
					  showCancelButton: true,
					  focusConfirm: false,
					  width: '650px',
					  confirmButtonText:
					    '<i class="fa fa-check"></i> OK',
					  cancelButtonText:
					    '<i class="fa fa-times">Cancel</i>',
					}).then(function(result){
						if(result.value){
							let value = $('input[name="idRack"]:checked').attr('id');
							$("#itemId").val(value);
				        }else if(result.dismiss == 'cancel'){
				         	console.log("dismiss called");  
				        }
					});
		      }).catch(error => {
		        Swal.showValidationMessage(
		          `Request failed: ${error}`
		        )
		      })
		});
		
		//show add item modal
		$("#addItem").click(function(){
			
			let finalHtml = 
				"<form method='post' id='formAddItem' action='/SimpleWarehousManagementSystem/items'>"+
					"<table>"+
						"<tr><td>Name</td><td><input type='text' name='nameItem'></td></tr>"+
						"<tr><td>Date Production</td><td><input type='date' name='dateProduction'></td></tr>"+
						"<tr><td><input type='submit' id='addItemSubmit' style='visibility: hidden;'></td></tr>"+
					"</table>"+
				"</form>";
				Swal.fire({
					  title: '<strong>Add Item</strong>',
					  icon: 'info',
					  html: finalHtml,
					  showCloseButton: true,
					  showCancelButton: true,
					  focusConfirm: false,
					  width: '750px',
					  confirmButtonText:
					    '<i class="fa fa-check"></i> OK',
					  cancelButtonText:
					    '<i class="fa fa-times">Cancel</i>',
				}).then(function(result){
					if(result.value){
						let nameItem = $('input[name="nameItem"]').val();
						let dateProd = $('input[name="dateProduction"]').val();
						var formData = 'rackName='+nameItem+'&dateProduction='+dateProd;
				        $.ajax({
				          url: '/SimpleWarehousManagementSystem/items',
				          data: formData,
				          type: 'POST',
				          dataType: 'json',
				          processData: false,
				          contentType: 'application/x-www-form-urlencoded',
				          success: function (data) {
				        	  Swal.showValidationMessage('FormData Object Send Successfully!');
				          },
				          error: function (err) {
				        	  Swal.showValidationMessage('FormData Object Send Failed!');
				          },
				        })
						
			        }else if(result.dismiss == 'cancel'){
			         	
			        }
				})
		});
		
		function submitAddItem(){
			
		}
		
	});
</script>
</head>
<body>
	<h5> Input Outbound</h5>
	<form method="post" action="/SimpleWarehousManagementSystem/racks">
		<input type="hidden" name="action" value="outbound" />
		<table>
			<tr><td><input type='hidden' name='action' value='inbound'></td></tr>
			<tr><td>Rack ID</td><td><input type = "text" id="rackId" name="rackId" readonly="readonly"/></td><td><input type="button" id="showRackId" value="..."/></td></tr>
			<tr><td>Item ID</td><td><input type = "text" id="itemId" name="itemId" readonly="readonly"/></td><td><input type="button" id="showItemId" value="..."/><input type="button" id="addItem" value="+"/></td></tr>
			<tr><td>Count</td><td><input type = "text" id="count" name="count"/></td></tr>
			<tr><td><input type="submit" value="save"></td><td></td></tr>
		</table>
		
	</form>

</body>
</body>
</html>