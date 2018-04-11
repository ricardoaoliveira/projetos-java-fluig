var GraficoAcompanhamentoEscobs = SuperWidget.extend({
    
    init: function() {
    	this.initChart();
    },
    
    initChart: function() {
    	var self = this;
    	
    	google.charts.load("current", {packages:["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
          var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['BNS',     11],
            ['CENTER CREDIT',      2],
            ['CARD BANK',  2],
            ['DNR', 2],
            ['PREIRA',    7],
  		  	['QUALIZ',    1]
          ]);

          var options = {
            title: '',
            is3D: true,
          };
          
          var chart = new google.visualization.PieChart(document.getElementById('piechart_3d_' + self.instanceId));
          chart.draw(data, options);
        }
    },
    
    searchData: function() {
		var data = {
			content: []
		};
		
		var constraints   = new Array();
 
		var fields = new Array("id", "ds_descriptor", "nm", "titulo", "nm_escob", "vl_escob");
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("dsAcompanhamentoEscobs", fields, constraints, sortingFields);
		
		console.log("dataset: " + dataset.values.length);
		
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			data.content = new Array( dataset.values[0] );
		}
		
		console.log(data);
		
		return data;
	},
  
});
