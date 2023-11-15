<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script>
$(document).ready(function() {
	var countInbound = 0;
	var countOutbound = 0;
	var countItem = 0;
	
	fetch('/SimpleWarehousManagementSystem/inbound-outbound/list-inbound-outbound-data')
    .then(response => {
      if (!response.ok) {
      	
      }
      return response.json();
    }).then((data) => {
    	for(a=0; a<data.data.length; a++){
    		for(b=0; b<data.data[a]['item'].length; b++){
    			countInbound += data.data[a]['item'][b]['countInboundItem'];
    			countOutbound +=  data.data[a]['item'][b]['countOutboundItem'];
    			countItem += data.data[a]['item'][b]['countItem'];
    		}
    	}
    	
    	console.log(countInbound );
    	
    	//mapping data into donut
    	var xValues = ["Inbound Count", "Outbound Count", "Items Count"];
    	var yValues = [countInbound, countOutbound, countItem];
    	var barColors = [
    	  "#b91d47",
    	  "#00aba9",
    	  "#2b5797",
    	];

    	new Chart("myChart", {
    	  type: "doughnut",
    	  data: {
    	    labels: xValues,
    	    datasets: [{
    	      backgroundColor: barColors,
    	      data: yValues
    	    }]
    	  },
    	  options: {
    	    title: {
    	      display: true,
    	      text: "Inbound-Outbound-Count Item"
    	    }
    	  }
    	});
    	
    }).catch(error => {
      console.log(error);
    })
});


</script>
	
</head>
<body>
<p>
	<h5>Chart inbound outbound item</h5>
	<canvas id="myChart" style="width:100%;max-width:700px"></canvas>
</p>

</body>
</html>