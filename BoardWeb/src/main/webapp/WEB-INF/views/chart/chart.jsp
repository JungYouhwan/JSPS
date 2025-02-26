<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
    	// [[], [], []....];
    	  let aryData = [];
    	  
    	  fetch('chartData.do')
    	  .then(result => result.json()) // json을 파싱
    	  .then(result => {
    		  aryData.push(['부서명', '인원']);
    		  result.forEach(item =>{
    			  aryData.push([item.dept_name, item.dept_count]);
    		  });
    		  console.log(aryData);
    		  google.charts.load('current', {'packages':['corechart']});
    	      google.charts.setOnLoadCallback(drawChart);
    	  })
    	  .catch(err => console.log(err));
      function drawChart() {
        var data = google.visualization.arrayToDataTable(aryData);
        var options = {
          title: '하루 일과'
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
	<h3>chart</h3>
    <div id="piechart" style="width: 1200px; height: 800px;"></div>
  </body>
</html>
