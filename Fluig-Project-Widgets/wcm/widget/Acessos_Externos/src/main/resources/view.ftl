<div id="AcessosExternos_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="AcessosExternos.instance()">

	<h2 class="page-header"><span class="fluigicon fluigicon-cloud"></span> Acessos Externos</h2>
	
	<div id="container_${instanceId}">
		
	</div>

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script id="template_${instanceId}" type="text/template">
	{{#content}}	
		{{#insertRow}}
			{{#isFirst}}
				<div class="row">
			{{/isFirst}}
			{{^isFirst}}
				</div>
				<div class="row">
			{{/isFirst}}
		{{/insertRow}}
		
		<div class="col-md-6"> 
			<h3>
				<a href="{{url}}" target="_blank">{{titulo}}</a>
			</h3>
			<p>
				<a href="{{url}}" target="_blank">
					<img src="{{imageUrl}}">
				</a>
			</p>
		</div>
		
		{{#insertRow}}
			{{#isLast}}
				</div>
			{{/isLast}}
		{{/insertRow}}
	{{/content}}
</script>