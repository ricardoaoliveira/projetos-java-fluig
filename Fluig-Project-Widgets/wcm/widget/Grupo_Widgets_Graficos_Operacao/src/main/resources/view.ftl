<div id="Grupo_Widgets_Graficos_Operacao_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="Grupo_Widgets_Graficos_Operacao.instance()">

	<h2 class="page-header"><span class="fluigicon fluigicon-column-chart"></span> Gráficos</h2>
	
	<div id="container_${instanceId}">
						
	</div>

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script id="template_${instanceId}" type="text/template">	
	<ul class="list-group">
		{{#content}}		
			<a href="#" onclick="openWidget('{{{nome}}}', '{{{app_code}}}');" class="list-group-item">
				<h3 class="list-group-item-heading"><center>{{{nome}}}</center></h3>
				<p class="list-group-item-text"><center>{{{descricao}}}</center></p>
			</a>
		{{/content}}							
	</ul>
</script>

<script type="text/javascript">

	var beginElem = "";
	var endElem = "";

	var data = {
		content: [
			{
				nome: beginElem + 'Resultado das Assessorias' + endElem, 
				descricao: 'Losango - Campanha Agências Externas <br/> Atendimento da Meta Diária (d-1)',
				app_code: 'Google_Gauge_Chart'				
			},
			{
				nome: beginElem + 'Indicadores da Operação (d-1)' + endElem, 
				descricao: 'PCA (%) (Per Call Answered)',
				app_code: 'Google_Gauge_Chart_3'				
			},
			{
				nome: beginElem + 'Gráfico de Acompanhamento de Produtividade de Escobs (Pizza)' + endElem, 
				descricao: 'Promessa/CPC (d-1)',
				app_code: 'Google_Pie_Chart'				
			},
			{
				nome: beginElem + 'Gráfico de Acompanhamento de Produtividade de Escobs' + endElem, 
				descricao: 'Promessa/CPC (d-1)',
				app_code: 'Acompanhamento_Escobs_Docs'				
			},
		],
	};

	var template = $("#template_${instanceId}").html();

	var html = Mustache.render(template, data);
	
	$("#container_${instanceId}").append(html);
	
	function openWidget(pTitle, pWidgetCode) {
		var myModal = FLUIGC.modal({
		    title: '',
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
			
			//$(".modal-title").html( modalTitle );
						
			$(".page-header " + pWidgetCode).hide();		
			
			$("#fluig-modal").show();	
		}, 300);
	}
	
</script>