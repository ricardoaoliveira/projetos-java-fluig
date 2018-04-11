<div id="GoogleGaugeChart3_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide"
 	data-params="GoogleGaugeChart3.instance()"> 
				 
	<div class="fluig-style-guide" style="text-align: center;">	
		<h2 class="page-header"> 
    		<span class="fluigicon fluigicon-column-chart"></span>
        	<center>Indicadores da Operação (d-1)</center>
			<center>PCA (%) (Per Call Answered)</center>
    	</h2>
    	
		<br/>
		<center>
		<div id="containerCharts_${instanceId}">
			
		</div>
		</center>
    </div>	
	
</div>
<script id="template_${instanceId}" type="text/template">
	<div class="col-md-3 col-lg-3 col-sm-2 col-xs-6" id="div_esquerda_${instanceId}">
	</div>
	{{#content}}
		<div class="col-md-2 col-lg-2 col-sm-3 col-xs-6" style="text-align: center;">
			<center>
			<div id="div_${instanceId}_{{indice}}" style="text-align: center;">
				
			</div>
			</center>
		</div>
	{{/content}}
</script>

<script id="templateMobile_${instanceId}" type="text/template">
	{{#content}}
		<div class="col-md-2 col-lg-2 col-sm-3 col-xs-6" style="text-align: center;">
			<center>
			<div id="divMobile_${instanceId}_{{indice}}" style="text-align: center;">
				
			</div>
			</center>
		</div>
	{{/content}}
</script>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>