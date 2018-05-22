<div id="DocumentosAdministracaoConteudo_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="DocumentosAdministracaoConteudo.instance()">

	<div class="fluig-style-guide">

		<div id="container_${instanceId}">
		
			<div class="row">
				<div class="col-md-8">
					<h2 class="page-header"> 
					<span class="fluigicon fluigicon-videos"></span>
						<span>
							Como atualizar os conteúdos?
						</span>    		
					</h2>
					<div class="col-md-6">
						<h3>Intranet MGW Ativos</h3>
						<div id="container_intranet_${instanceId}">
							
						</div>
					</div>
					<div class="col-md-6">
						<h3>Portal RH</h3>
						<div id="container_portal_rh_${instanceId}">
									
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<h2 class="page-header"> 
					<span class="fluigicon fluigicon-folder-open"></span>
						<span>
							Documentos
						</span>    		
					</h2>
					<div id="container_documentos_${instanceId}">
									
					</div>
				</div>
			</div>
			
		</div>
		
	</div>

</div>

<script id="template_conteudos_${instanceId}" type="text/template">
	<div class="row">			
		<div class="col-md-12">
			<ul class="list-group">
				{{#content}}	
				<li class="list-group-item">
					<span class="{{icon}}"></span> <span> 
						<a href="#" onclick="abrirVideo('{{url}}', '{{label}}', '{{icon}}' )"> {{label}} </a> 
					</span>
				</li>
				{{/content}}					
			</ul>
		</div>	
	</div>
</script>

<script id="template_documentos_${instanceId}" type="text/template">
	<div class="row">			
		<div class="col-md-12">
			<ul class="list-group">
				{{#content}}	
				<li class="list-group-item">
					<span class="{{icon}}"></span> <span> 
						<a href="{{url}}" target="_blank"> {{label}} </a> 
					</span>
				</li>
				{{/content}}					
			</ul>
		</div>	
	</div>
</script>

<script id="template_video_${instanceId}" type="text/template">
	{{#content}}	
	<div class="col-md-12">
		<p style="text-align: center;">
			<span class="flowplayer" data-swf="http://mgwativosgestaoeadmi.fluig.cloudtotvs.com.br/portal/resources/style-guide/utils/fluigVideo.swf" style="width: 100%; height: 100%;">
				<video controls="controls" height="60%" width="60%">
					<source src="{{url}}" />
				</video>
			</span>
		</p>
	</div>
	{{/content}}					
</script>

<script type="text/javascript">

	var abrirVideo = function(urlVideo, label, icon) {
	
		var data_video = {
			content: [
				{
					url: urlVideo,
					label: label
				}
			],
		};
		
		var template_video = $("#template_video_${instanceId}").html();
		var html_video = Mustache.render(template_video, data_video);	
		
		var modal_video = FLUIGC.modal({
			title: label,
			content: html_video,
			id: 'fluig-modal',
			size: 'full',
			actions: [{
				'label': 'Fechar',
				'autoClose': true
			}]
		}, function(err, data) {
			if(err) {
				// do error handling
			} else {
				// do something with data
			}
		});
		
		$("#fluig-modal").hide();
		
		var titulo = '<h2 class="page-header">';
		titulo += '<span class="' + icon + '"></span>&nbsp;';
		titulo += label;
		titulo += '</h2>';
		
		setTimeout(function(){ 
			$(".modal-title").html( titulo );
			$("#fluig-modal").show();	
		}, 300);
	};

	var data_intranet = {
		content: [
			{
				icon: "fluigicon fluigicon-paperclip",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=334&WDNrVersao=1000",
				label: "Banner Corporativo",
			},
			{
				icon: "fluigicon fluigicon-paperclip",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=332&WDNrVersao=1000",
				label: "Mural de Avisos",
			},
			{
				icon: "fluigicon fluigicon-cloud",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=335&WDNrVersao=1000",
				label: "Sistemas Corporativos (Acessos Externos)",
			},
			{
				icon: "fluigicon fluigicon-calendar",	
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=333&WDNrVersao=1000",
				label: "Reserva da Sala de Reunião",
			},
			{
				icon: "fluigicon fluigicon-phone",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=339&WDNrVersao=1000",
				label: "Telefones Assessorias",
			},
			{
				icon: "fluigicon fluigicon-phone",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=341&WDNrVersao=1000",
				label: "Telefones Colaboradores",
			},
			{
				icon: "fluigicon fluigicon-phone",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=342&WDNrVersao=1000",
				label: "Telefones Emergência",
			},
			{
				icon: "fluigicon fluigicon-rss",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=344&WDNrVersao=1000",
				label: "Notícias",
			},
		],
	};
	
	var data_portal_rh = {
		content: [
			{
				icon: "fluigicon fluigicon-organogram",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=345&WDNrVersao=1000",
				label: "Organograma MGW Ativos",
			},
			{
				icon: "fluigicon fluigicon-food",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=346&WDNrVersao=1000",
				label: "Restaurantes Próximos (Parâmetros)",
			},
			{
				icon: "fluigicon fluigicon-user-cost",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=336&WDNrVersao=1000",
				label: "Benefícios",
			},
			{
				icon: "fluigicon fluigicon-cake fluigicon-md",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=337&WDNrVersao=1000",
				label: "Aniversariantes do Mês",
			},
			{
				icon: "fluigicon fluigicon-list",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=338&WDNrVersao=1000",
				label: "Convênios",
			},
			{
				icon: "fluigicon fluigicon-list",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=340&WDNrVersao=1000",
				label: "Dicas para Leituras, Passeios e etc",
			},
			{
				icon: "fluigicon fluigicon-user-role",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=343&WDNrVersao=1000",
				label: "Treinamento e Desenvolvimento",
			},
		],
	};
	
	var data_documentos = {
		content: [
			{
				icon: "fluigicon fluigicon-file-doc",
				url: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=347",
				label: "Administração-Intranet-MGW-Ativos.docx",
			},
			{
				icon: "fluigicon fluigicon-file-doc",
				url: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=348",
				label: "Administração-Portal-RH.docx",
			},
		],
	};

	var template_conteudos = $("#template_conteudos_${instanceId}").html();
	var html_intranet = Mustache.render(template_conteudos, data_intranet);	
	$("#container_intranet_${instanceId}").html(html_intranet);
	
	var html_portal_rh = Mustache.render(template_conteudos, data_portal_rh);	
	$("#container_portal_rh_${instanceId}").html(html_portal_rh);
	
	var template_documentos = $("#template_documentos_${instanceId}").html();
	var html_documentos = Mustache.render(template_documentos, data_documentos);	
	$("#container_documentos_${instanceId}").html(html_documentos);

</script>