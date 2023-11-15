<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../header.jsp"></jsp:include>
<script>
	$(document).ready(function(){
		$("#showGudangId").click(function(){
			
			fetch('/SimpleWarehousManagementSystem/list-warehouse-data')
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
		      		let data = "<tr><td><input type='radio' id="+rack[0]+" name='idGdg'/></td><td>"+rack[1]+"</td><td>"+rack[2]+"</td><td>"+rack[3]+"</td></tr>";
		      		rows = rows+data;
		      	})
		      	
		      	let finalHtml = "<table><th></th><th>Name</th><th>Address</th><th>Phone</th>"
		      	+rows+"</table>";
		      	Swal.fire({
					  title: '<strong>List Gudang</strong>',
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
							let value = $('input[name="idGdg"]:checked').attr('id');
							$("#warehouseId").val(value);
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
	});
</script>

</head>
<body>	
	<form method="post" action="/SimpleWarehousManagementSystem/racks">
		<input type="hidden" name="action" value="add" />
		<table>
			<tr><td>Gudang</td><td><input type = "text" id="warehouseId" name="warehouseId" readonly="readonly"/></td><td><input type="button" id="showGudangId" value="..."/></td></tr>
			<tr><td>Name</td><td><input type = "text" name="rackName"/></td></tr>
			<tr><td><input type="submit" value="save"></td><td></td></tr>
		</table>
		
	</form>

</body>
</html>