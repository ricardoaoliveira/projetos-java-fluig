<div id="CardapioDoDia_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="CardapioDoDia.instance()">

	<div class="fluig-style-guide">		
	
		<div id="container_${instanceId}">
		
		</div>		
		
    </div>

</div>

<script id="template_${instanceId}" type="text/template">
	{{#content}}
		<div class="fluig-style-guide">
			<h2 class="page-header"> 
				<span class="fluigicon fluigicon-food" id="spanTitulo_${instanceId}"></span>
				Cardápio do Dia
			</h2>
			<h3>
				{{nm}}
			</h3>
			<div id="divImage_${instanceId}">
				<p><img alt="" src="{{>urlImagem}}" style= "max-height: 400px; max-width: 100%; width: 100%;" ></p>	
			</div>
		</div>
	{{/content}}
</script>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>