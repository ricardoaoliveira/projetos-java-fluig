<div id="AcessosExternos_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="AcessosExternos.instance()">

	<h2 class="page-header"><span class="fluigicon fluigicon-cloud"></span> Acessos Externos</h2>
	
	<div class="row">
		<div id="container_${instanceId}" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
		</div>
	</div>

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script id="template_${instanceId}" type="text/template">
	<div class="row">
	{{#content}}	
		<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6"> 
			<h3>
				<a href="{{url}}" target="_blank">{{titulo}}</a>
			</h3>
			<p>
				<a href="{{url}}" target="_blank">
					<img src="{{imageUrl}}" style="max-width: 120px;">
				</a>
			</p>
		</div>
	{{/content}}					
	</div>		
</script>