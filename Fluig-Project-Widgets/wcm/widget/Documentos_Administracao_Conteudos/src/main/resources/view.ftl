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
					
					<h2 class="page-header"> 
					<span class="fluigicon fluigicon-picture"></span>
						<span>
							Imagens
						</span>    		
					</h2>
					
					<div id="container_redimensionar_imagens_${instanceId}">
									
					</div>
					
					<div class="row col-md-12">
						<p>OBS: É recomendável redimensionar imagens sempre de tamanho maior para menor, pois gera menos distorções visuais.</p>
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
					<span class="{{iconLink}}" onclick="window.open('{{link}}');" style="float:right;" title="{{tooltip}}">
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
				iconLink: "fluigicon fluigicon-folder-open",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=72",
				tooltip: "Pasta",
			},
			{
				icon: "fluigicon fluigicon-paperclip",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=332&WDNrVersao=1000",
				label: "Mural de Avisos",
				iconLink: "fluigicon fluigicon-folder-open",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=71",
				tooltip: "Pasta",
			},
			{
				icon: "fluigicon fluigicon-cloud",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=335&WDNrVersao=1000",
				label: "Sistemas Corporativos (Acessos Externos)",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=8",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-calendar",	
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=333&WDNrVersao=1000",
				label: "Reserva da Sala de Reunião",
				iconLink: "fluigicon fluigicon-picture",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=275",
				tooltip: "Imagem",
			},
			{
				icon: "fluigicon fluigicon-phone",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=339&WDNrVersao=1000",
				label: "Telefones Assessorias",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=106",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-phone",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=341&WDNrVersao=1000",
				label: "Telefones Colaboradores",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=7",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-phone",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=342&WDNrVersao=1000",
				label: "Telefones Emergência",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=107",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-rss",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=344&WDNrVersao=1000",
				label: "Notícias",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=100",
				tooltip: "Formulário",
			},
		],
	};
	
	var data_portal_rh = {
		content: [
			{
				icon: "fluigicon fluigicon-organogram",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=345&WDNrVersao=1000",
				label: "Organograma MGW Ativos",
				iconLink: "fluigicon fluigicon-picture",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=313",
				tooltip: "Imagem",
			},
			{
				icon: "fluigicon fluigicon-food",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=346&WDNrVersao=1000",
				label: "Restaurantes Próximos (Parâmetros)",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=103",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-user-cost",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=336&WDNrVersao=1000",
				label: "Benefícios",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=95",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-cake fluigicon-md",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=337&WDNrVersao=1000",
				label: "Aniversariantes do Mês",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=38",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-list",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=338&WDNrVersao=1000",
				label: "Convênios",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=51",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-list",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=340&WDNrVersao=1000",
				label: "Dicas para Leituras, Passeios e etc",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=14",
				tooltip: "Formulário",
			},
			{
				icon: "fluigicon fluigicon-user-role",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=343&WDNrVersao=1000",
				label: "Treinamento e Desenvolvimento",
				iconLink: "fluigicon fluigicon-form",
				link: "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=108",
				tooltip: "Formulário",
			},
		],
	};
	
	var data_redimensionar_imagens = {
		content: [
			{
				icon: "fluigicon fluigicon-minimize",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=352&WDNrVersao=1000",
				label: "Redimensionando imagem para 120 x 90 px",
			},
			{
				icon: "fluigicon fluigicon-cut",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=353&WDNrVersao=1000",
				label: "Removendo parte de conteúdo em branco de imagem",
			},
			{
				icon: "fluigicon fluigicon-newspaper",
				url: "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=354&WDNrVersao=1000",
				label: "Criando imagem 120 x 90 px, a partir de imagem menor",
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
	
	var html_redimensionar_imagens = Mustache.render(template_conteudos, data_redimensionar_imagens);	
	$("#container_redimensionar_imagens_${instanceId}").html(html_redimensionar_imagens);

</script>