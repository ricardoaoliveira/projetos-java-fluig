<div id="GoogleGaugeChart_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
 data-params="GoogleGaugeChart.instance()"> 

	<div class="fluig-style-guide" style="text-align: center;">
		<h2 class="page-header"> 
    		<span class="fluigicon fluigicon-column-chart"></span>
        	<center>Resultado das Assessorias</center>
			<center>Losango - Campanha Agências Externas</center>
			<center>Atendimento da Meta Diária (d-1)</center>
    	</h2>
		
		<br/>
		<div id="containerCharts_${instanceId}">
			
		</div>
		
    </div>	
	
</div>
<script id="template_${instanceId}" type="text/template">
	{{#content}}
		<div class="col-md-2 col-lg-2 col-sm-3 col-xs-6">
			<div id="div_${instanceId}_{{indice}}">
				
			</div>
		</div>
	{{/content}}
</script>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>