<div id="OpenWidgetModal_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="OpenWidgetModal.instance()">

	<button id="btnTelefonesAssessorias">
		Telefones Assessorias
	</button>
	
	<button id="btnTelefonesColaboradores">
		Telefones Colaboradores
	</button>
	
	<button id="btnAcessosExternos">
		Acessos Externos
	</button>
	
</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

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
		
		setTimeout(function(){ 
			$(".modal-title").html( $(".page-header").html() );
			$(".page-header").hide();		
			
			$("#fluig-modal").show();	
		}, 300);
	}
	
	$("#btnTelefonesAssessorias").click(function() {
		openWidget("Lista de Telefones (Assessorias)", "Lista_Telefones_Assessorias");
	});
	
	$("#btnTelefonesColaboradores").click(function() {
		openWidget("Lista de Telefones (Colaboradores)", "Lista_Telefones");
	});
	
	$("#btnAcessosExternos").click(function() {
		openWidget("Acessos Externos", "Acessos_Externos");
	});
	
</script>