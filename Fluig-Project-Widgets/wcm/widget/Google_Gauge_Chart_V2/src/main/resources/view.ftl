<div id="GoogleGaugeChartV2_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="GoogleGaugeChartV2.instance()">

	<h2>Google Gauge Chart 3 - V2</h2>

	<div class="fluig-style-guide">
		<h2 class="page-header"> 
    		<span class="fluigicon fluigicon-column-chart"></span>
        	Resultado das Assessorias (D-1) 
        	<br/>
			Losango - Campanha Agências Externas
    	</h2>
		<div id="containerCharts">
			
		</div>
    </div>	

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script id="template" type="text/template">
	{{#content}}
		<div class="col-md-2 col-lg-2 col-sm-3 col-xs-6">
			<div id="div{{indice}}">
				
			</div>
		</div>
	{{/content}}
</script>

<script type="text/javascript">

	google.charts.load('current', {'packages':['gauge', 'corechart']});
	
	var data = {
		content: [
			{indice: 0},
			{indice: 1},
			{indice: 2},
			{indice: 3},
			{indice: 4},
			{indice: 5},
		]
	};
	
	var template = $("#template").html();

	var html = Mustache.render(template, data);
	
	$("#containerCharts").append(html);
	
	var options = {
		width: 240, height: 130,
		redFrom: 0, redTo: 30,
		yellowFrom: 31, yellowTo: 70,
		greenFrom: 71, greenTo: 100,
		minorTicks: 5
	};
	
	var valores = new Array();
    	
	valores.push(['JL', 81.9 ]);
	valores.push(['BNS', 68.9 ]);
	valores.push(['CardBank', 65.2 ]);
	valores.push(['DNR', 80 ]);
	valores.push(['Pereira', 75 ]);
	valores.push(['QUALIZ', 20 ]);
	
	var addValores = function(vValores, options, divId) {
		google.charts.setOnLoadCallback(function() {
			  var data = google.visualization.arrayToDataTable(vValores);
			
			  var chart = new google.visualization.Gauge(document.getElementById(divId));

			  chart.draw(data, options);
		});
	};
		
	var arrayToChart = null;
	
	for (var i=0; i<valores.length; i++) {
		arrayToChart = new Array();
		arrayToChart.push(['Label', 'Value']);
		arrayToChart.push(valores[i]);
		addValores(arrayToChart, options, "div" + i);	
	}
	
	var dataset = DatasetFactory.getDataset("dsIndicadoresOperacao");
	
	console.log(dataset);
	
</script>
