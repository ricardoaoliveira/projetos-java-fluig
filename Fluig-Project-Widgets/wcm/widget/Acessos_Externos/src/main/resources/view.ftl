<div id="AcessosExternos_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="AcessosExternos.instance()">

	<h2 class="page-header"><span class="fluigicon fluigicon-cloud"></span> Sistemas Corporativos</h2>
	
	<div id="container_${instanceId}">
		
	</div>

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script id="template_${instanceId}" type="text/template">
	{{#content}}	
	<div class="row">
		<div class="col-md-12">
			<h3>
				<a href="{{url}}" target="_blank">{{titulo}}</a>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<p>
				<a href="{{url}}" target="_blank">
					<img src="{{imageUrl}}" style="width: 100px; height: 100px;">		
					{{descricao}}								
				</a>							
			</p>
		</div>
	</div>
	{{/content}}
</script>