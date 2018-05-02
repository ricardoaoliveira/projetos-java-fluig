<div id="OpenReservaSalaReuniao_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
data-params="OpenReservaSalaReuniao.instance()">

<h2 class="page-header"><span class="fluigicon fluigicon-calendar"></span> Reserva da Sala de Reunião</h2>
	
	<div id="container_${instanceId}">
		
	</div>

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script id="template_${instanceId}" type="text/template">	
	{{#content}}	
	<div class="row" style="display:none;">
		<div class="col-md-12">
			<h3>
				<a onclick="openWidgetReserva('{{titulo}}', '{{codigo}}')">{{titulo}}</a>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<p>
				<a onclick="openWidgetReserva('{{titulo}}', '{{codigo}}')">
					<img src="{{imageUrl}}">
					{{descricao}}
				</a>
			</p>
		</div>
	</div>
	{{/content}}
</script>

<script type="text/javascript">
	function openWidgetReserva(pTitle, pWidgetCode) {
		var myModal = FLUIGC.modal({
		    title: pTitle,
		    content: {
		        widgetCode: pWidgetCode, 
		        ftl: 'view.ftl',		        
		    },
		    id: 'fluig-modal',
		    size: 'full | large | small',
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
		grupoWidgetsTelefoneModalTitulo += '<span class="fluigicon fluigicon-calendar"></span>&nbsp;';
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