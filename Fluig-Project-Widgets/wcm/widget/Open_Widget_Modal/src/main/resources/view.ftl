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
	
	<button id="btnResultadoAssessorias">
		Resultado das Assessorias
	</button>
	
	<button id="btnIndicadoresOperacao">
		Indicadores da Operação
	</button>
	
	<button id="btnAcompanhamentoEscobs">
		Acompanhamento Escobs
	</button>
	
	<button id="btnAcompanhamentoEscobsDocs" style="display:none;">
		Acompanhamento Escobs Docs
	</button>
	
	<button id="btnReservaSalaReuniao">
		Reserva da Sala de Reunião
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
		    size: 'full',
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
		grupoWidgetsTelefoneModalTitulo += '<span class="fluigicon fluigicon-column-chart"></span>&nbsp;';
		grupoWidgetsTelefoneModalTitulo += pTitle;
		grupoWidgetsTelefoneModalTitulo += '</h2>';
		
		setTimeout(function(){ 
			$("#" + pWidgetCode).hide();
			
			var modalTitle = "<h2 class='page-header'>";
			modalTitle +=  $(".page-header").html();
			modalTitle += "</h2>";
			$(".page-header").html("");
			$(".modal-title").html( modalTitle );
						
			$(".page-header " + pWidgetCode).hide();		
			
			$("#fluig-modal").show();	
		}, 300);
	}
	
	$("#btnTelefonesAssessorias").click(function() {
		openWidget("Lista de Telefones (Assessorias)", "Lista_Telefones_Assessorias");
	});
	
	$("#btnTelefonesColaboradores").click(function() {
		openWidget("Lista de Telefones (Colaboradores)", "Lista_Telefones");
	});
	
	$("#btnResultadoAssessorias").click(function() {
		openWidget("Resultado das Assessorias", "Google_Gauge_Chart");
	});
	
	$("#btnIndicadoresOperacao").click(function() {
		openWidget("Indicadores da Operação", "Google_Gauge_Chart_3");
	});
	
	$("#btnAcompanhamentoEscobs").click(function() {
		openWidget("Acompanhamento Escobs", "Google_Pie_Chart");
	});
	
	$("#btnAcompanhamentoEscobsDocs").click(function() {
		openWidget("Acompanhamento Escobs Docs", "Acompanhamento_Escobs_Docs");
	});
	
	$("#btnReservaSalaReuniao").click(function() {
		openWidget("Reserva da Sala de Reunião", "Reserva_Sala_Reuniao");
	});
	
</script>