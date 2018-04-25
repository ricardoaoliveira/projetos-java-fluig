<div id="GrupoWidgetsTelefone_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="GrupoWidgetsTelefone.instance()">

<h2 class="page-header"><span class="fluigicon fluigicon-phone"></span> Telefones</h2>
	
	<div id="container_${instanceId}">
		
	</div>

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script id="template_${instanceId}" type="text/template">
	{{#content}}	
		<div class="col-md-4"> 
			<h3>
				<center>
					<a onclick="openWidget('{{titulo}}', '{{codigo}}')">{{titulo}}</a>
				</center>
			</h3>
			<p>
				<a onclick="openWidget('{{titulo}}', '{{codigo}}')">
					<center>
						<img src="{{imageUrl}}" style="width: 100px; height: 100px;">
					</center>					
				</a>
			</p>
		</div>
	{{/content}}
</script>

<script type="text/javascript">
	function openWidget(pTitle, pWidgetCode) {
		var myModal = FLUIGC.modal({
		    title: pTitle,
		    content: {
		        widgetCode: pWidgetCode, 
		        ftl: 'view.ftl',		        
		    },
		    id: 'fluig-modal',
		    actions: []		    
		}, function(err, data) {
		    if(err) {
		        // do error handling
		    } else {
		        // do something with data
		    }
		});
		
		$("#fluig-modal").hide();
		
		var grupoWidgetsTelefoneModalTitulo = '<h2 class="page-header">';
		grupoWidgetsTelefoneModalTitulo += '<span class="fluigicon fluigicon-phone"></span>&nbsp;';
		grupoWidgetsTelefoneModalTitulo += pTitle;
		grupoWidgetsTelefoneModalTitulo += '</h2>';
		
		setTimeout(function(){ 
			$("#" + pWidgetCode).hide();
			$(".modal-title").html( grupoWidgetsTelefoneModalTitulo );
			$(".page-header " + pWidgetCode).hide();		
			
			$("#fluig-modal").show();	
		}, 300);
	}
</script>