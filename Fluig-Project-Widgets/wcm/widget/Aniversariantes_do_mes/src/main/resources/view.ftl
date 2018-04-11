<div id="AniversariantesDoMes_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="AniversariantesDoMes.instance()">

	<div class="fluig-style-guide">		
	
		<div id="container_${instanceId}">
		
		</div>		
		
    </div>

</div>

<script id="template_${instanceId}" type="text/template">
	{{#content}}
		<div class="fluig-style-guide">
			<h2 class="page-header"> 
				<span class="fluigicon fluigicon-cake" id="spanTitulo_${instanceId}"></span>
				{{titulo}}
			</h2>
			<div id="divImage_${instanceId}">
				<p><img alt="" src="{{imageUrl}}" style= "max-height: 400px; max-width: 100%; width: 100%;" ></p>
			</div>
		</div>
	{{/content}}
</script>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>