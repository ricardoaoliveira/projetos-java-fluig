<div id="PainelTaxas_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="PainelTaxas.instance()">

	<div class="fluig-style-guide">
		
		<h2 class="page-header"> 
    	<span class="fluigicon fluigicon-rss"></span>
	        Painel de Taxas RSS<div id="divDataHora_${instanceId}"> </div>
	    </h2>
		
		<div id="painel_${instanceId}">
			
		</div>
		
		<div>
			<p>Fonte: <a href="https://www.bcb.gov.br/pt-br/#!/n/feed_painel_taxas" target="_blank">Banco Central do Brasil</a>
		</div>
		
		<div id="template_${instanceId}" style="display:none;">
		
			<div class="row" id="apresentacao">
				{{#content}}
				<div class="{{divClass}}"> 
				
					<div class="row">
					
						<div class="{{divClassChildren}}">
							<table class="table table-bordered table-condensed table-responsive">
								<tr class="cabecalho">
									<th>
										<div class="row">
											<div class="col-md-12 divTitulo">
												Poupança
											</div>
										</div>									
									</th>
								</tr>
								<tr class="linha_impar">
									<td class="linha_impar">
										<div class="row">
											<div class="col-md-12 divConteudo">
												<strong> {{poupanca.valor}}% a.m </strong>
											</div>											
										</div>
										<div class="row">
											<div class="col-md-12 divConteudo">
												&nbsp;
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					
						<div class="{{divClassChildren}}">
							<table class="table table-bordered table-condensed table-responsive">
								<tr class="cabecalho">
									<th>
										<div class="row">
											<div class="col-md-12 divTitulo">
												Meta de Inflação
											</div>
										</div>		
									</th>
								</tr>
								<tr class="linha_impar">
									<td class="linha_impar">
										<div class="row">
											<div class="col-md-12 divConteudo">
												{{inflacao.meta.valor}} {{inflacao.meta.texto}}
											</div>
										</div>
										<div class="row">
											<div class="col-md-12 divConteudo">
												&nbsp;
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
						
						<div class="{{divClassChildren}}">
							<table class="table table-bordered table-condensed table-responsive">
								<tr class="cabecalho">
									<th>
										<div class="row">
											<div class="col-md-12 divTitulo">
												Inflação Acumulada
											</div>
										</div>	
									</th>
								</tr>
								<tr class="linha_impar">
									<td class="linha_impar">
										<div class="row">
											<div class="col-md-12 divConteudo">
												{{inflacao.acumulada.texto}} - <strong>{{inflacao.acumulada.valor}}%</strong>
											</div>											
										</div>
									</td>
								</tr>
							</table>
						</div>
						
						<div class="{{divClassChildren}}">
							<table class="table table-bordered table-condensed table-responsive">
								<tr class="cabecalho">
									<th>
										<div class="row">
											<div class="col-md-12 divTitulo">
												Dólar PTAX
											</div>
										</div>	
									</th>
								</tr>
								<tr class="linha_impar">
									<td class="linha_impar">
										<div class="row">
											<div class="col-md-12 divConteudo">
												Compra: <strong> R$ {{cambio.dolarPtax.valorCompra}} </strong>
											</div>											
										</div>
										<div class="row">
											<div class="col-md-12 divConteudo">
												Venda: <strong> R$ {{cambio.dolarPtax.valorVenda}} </strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
						
						<div class="{{divClassChildren}}">
							<table class="table table-bordered table-condensed table-responsive">
								<tr class="cabecalho">
									<th>
										<div class="row">
											<div class="col-md-12 divTitulo">
												Taxa Selic - Meta
											</div>
										</div>	
									</th>
								</tr>
								<tr class="linha_impar">
									<td class="linha_impar">
										<div class="row">
											<div class="col-md-12 divConteudo">
												<strong> {{selic.meta.valor}}% </strong>
											</div>											
										</div>
										<div class="row">
											<div class="col-md-12 divConteudo">
												&nbsp;
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
						
						<div class="{{divClassChildren}}">
							<table class="table table-bordered table-condensed table-responsive">
								<tr class="cabecalho">
									<th>
										<div class="row">
											<div class="col-md-12 divTitulo">
												Taxa Selic Diária
											</div>
										</div>	
									</th>
								</tr>
								<tr class="linha_impar">
									<td class="linha_impar">
										<div class="row">
											<div class="col-md-12 divConteudo">
												{{selic.diaria.data}} <strong> {{selic.diaria.valor}}% </strong>
											</div>											
										</div>
										<div class="row">
											<div class="col-md-12 divConteudo">
												&nbsp;
											</div>
										</div>
										
									</td>
								</tr>
							</table>
						</div>
					
					</div>
					
				</div> 
				{{/content}}
			</div>
		
		</div> <!-- End Template -->
		
    </div>	

</div>

<script type="text/javascript" src="/webdesk/vcXMLRPC.js"></script>

<script type="text/javascript">
	
	var selic = {
		meta: {
			valor: ''
		},
		diaria: {
			valor: '',
			data: ''
		},
		reuniaoCopom: {
			data: ''
		}
	};
	
	var poupanca = {
		valor: ''
	};
	
	var inflacao = {
		meta: {
			valor: '',
			texto: ''
		},
		acumulada: {
			texto: '',
			valor: ''
		}
	};
	
	var cambio = {
		dolarPtax: {
			valorCompra: '',
			valorVenda: ''
		}
	};
	
	var urlSelic = 'http://conteudo.bcb.gov.br/api/feed/pt-br/PAINEL_INDICADORES/juros'; // selic
	var urlPoupanca = "http://conteudo.bcb.gov.br/api/feed/pt-br/PAINEL_INDICADORES/poupanca"; // poupanca
	var urlInflacao = "http://conteudo.bcb.gov.br/api/feed/pt-br/PAINEL_INDICADORES/inflacao"; // inflacao
	var urlCambio = "http://conteudo.bcb.gov.br/api/feed/pt-br/PAINEL_INDICADORES/cambio"; // cambio
	
	var showObjectContentSplit = false;
	var showObjectTaxas = false;
	var showObjectData = false;
	
	var atualizarSelic = function() {
		$.ajax({
		  type: 'GET',
		  url: "https://api.rss2json.com/v1/api.json?rss_url=" + urlSelic,
		  dataType: 'jsonp',
		  success: function(result) {
			
			console.log("Atualizar Selic");
			
			var contentsSplit = null;
			
			for (var i=0; i<result.items.length; i++) {
				
				var content = result.items[i].content.replace("</div>", " ");
				
				contentsSplit = result.items[i].content.split("<div>");
			}
			
			if (showObjectContentSplit) {
				console.log(contentsSplit);
			}
			
			selic.meta.valor = contentsSplit[3].split("</div>")[0];
			
			selic.diaria.valor = contentsSplit[7].split("</div>")[0];
			selic.diaria.data = contentsSplit[8].split("</div>")[0];
			
			selic.reuniaoCopom.data = contentsSplit[4].split("</div>")[0];
			
			if (showObjectTaxas) {
				console.log(selic);
			}
			
			atualizarPoupanca();
		  }
		});
	};
	
	var atualizarPoupanca = function() {
		$.ajax({
		  type: 'GET',
		  url: "https://api.rss2json.com/v1/api.json?rss_url=" + urlPoupanca,
		  dataType: 'jsonp',
		  success: function(result) {
			
			console.log("Atualizar Poupanca");
			
			var contentsSplit = null;
			
			for (var i=0; i<result.items.length; i++) {
				
				var content = result.items[i].content.replace("</div>", " ");
				
				contentsSplit = result.items[i].content.split("<div>");
			}
			
			if (showObjectContentSplit) {
				console.log(contentsSplit);
			}
			
			poupanca.valor = contentsSplit[2].split("</div>")[0];
			
			if (showObjectTaxas) {
				console.log(poupanca);
			}
			
			atualizarInflacao();
		  }
		});
	};
	
	var atualizarInflacao = function() {
		$.ajax({
		  type: 'GET',
		  url: "https://api.rss2json.com/v1/api.json?rss_url=" + urlInflacao,
		  dataType: 'jsonp',
		  success: function(result) {
			
			console.log("Atualizar Inflacao");
			
			var contentsSplit = null;
			
			for (var i=0; i<result.items.length; i++) {
				
				var content = result.items[i].content.replace("</div>", " ");
				
				contentsSplit = result.items[i].content.split("<div>");
			}
			
			if (showObjectContentSplit) {
				console.log(contentsSplit);
			}
			
			inflacao.meta.valor = contentsSplit[3].split("</div>")[0];
			inflacao.meta.texto = contentsSplit[4].split("</div>")[0];
			
			inflacao.acumulada.texto = contentsSplit[8].split("</div>")[0];
			inflacao.acumulada.valor = contentsSplit[7].split("</div>")[0];
			
			if (showObjectTaxas) {
				console.log(inflacao);
			}
			
			atualizarCambio();
			
		  }
		});
	};
	
	var atualizarCambio = function() {
		$.ajax({
		  type: 'GET',
		  url: "https://api.rss2json.com/v1/api.json?rss_url=" + urlCambio,
		  dataType: 'jsonp',
		  success: function(result) {
			
			console.log("Atualizar Cambio");
			
			var contentsSplit = null;
			
			for (var i=0; i<result.items.length; i++) {
				
				if (result.items[i].title == 'Dólar (PTAX)') {
					
					var content = result.items[i].content.replace("</div>", " ");
					
					contentsSplit = result.items[i].content.split("<div>");
				}
			}
			
			if (showObjectContentSplit) {
				console.log(contentsSplit);
			}
			
			cambio.dolarPtax.valorCompra = contentsSplit[3].split("</div>")[0];
			cambio.dolarPtax.valorVenda = contentsSplit[6].split("</div>")[0];
			
			if (showObjectTaxas) {
				console.log(cambio);
			}
			
			atualizarApresentacao();
			
		  }
		});
	};
	
	var atualizarApresentacao = function() {
	
		console.log("Atualizar Apresentacao");
	
		var data = {
			content: [{
				selic: selic,
				poupanca: poupanca,
				inflacao: inflacao,
				cambio: cambio,
				divClass: 'col-xs-12 col-sm-12 col-md-12 col-lg-12',
				divClassChildren: 'col-xs-12 col-sm-6 col-md-6 col-lg-6',				
				}
			]
		};
	
		if (showObjectData) {
			console.log(data);
		}
	
		var template = $("#template_${instanceId}").html();
	
		var html = Mustache.to_html(template, data);
		
    	$("#painel_${instanceId}").append(html);
	};
	
	var dataHora = " (" + FLUIGC.calendar.formatDateTimeZone(new Date()) + ")";
	
	$("#divDataHora_${instanceId}").html(dataHora);
	
	atualizarSelic();
	
</script>