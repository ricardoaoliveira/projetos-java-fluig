<div id="PainelAcessoPortais_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="PainelAcessoPortais.instance()">

	<h2 class="page-header"><span class="fluigicon fluigicon-th"></span> Portais</h2>
	
	<div id="container_${instanceId}">
		
	</div>

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script id="template_${instanceId}" type="text/template">
	{{#content}}	
		<a href="{{url}}">
			<img class="img_painel_acesso_portal" src="{{imageUrl}}" />
		</a>
	{{/content}}	
</script>
