var BoasVindas = SuperWidget.extend({

    init: function() {
    	console.log(this.instanceId);
    	//this.initView();
    },
    
    initView: function() {
    	var exibeBoasVindas = false;

    	var storageBoasVindas = localStorage.getItem("boas-vindas-fluig-mgw" + WCMAPI.getUserCode());

    	console.log("storageBoasVindas: " + storageBoasVindas);
    	
    	if ( storageBoasVindas == null ) {
    		exibeBoasVindas = true;
    	} else {
    		if ( storageBoasVindas != null && storageBoasVindas != WCMAPI.getUserCode() ) {
    			exibeBoasVindas = true;
    		}
    	}
    	if ( exibeBoasVindas ) {

			localStorage.setItem("boas-vindas-fluig-mgw"  + WCMAPI.getUserCode(), WCMAPI.getUserCode());
    		
    		var imagem = '<div class="col-md-6"><image src="/Boas_Vindas/resources/images/Boas-Vindas-Left.png" style="width: 100%; height: 100%;" /> </div>';

    		var texto = '<div class="col-md-6"><p class="">A Intranet é uma poderosa ferramenta de comunicação que influencia positivamente a relação empresa / funcionários, facilita a divulgação de informações, agiliza a implantação de processos, promove a integração dos funcionários, favorece o compartilhamento de recursos e habilidades, além de ter forte influencia na redução de custos operacionais.</p>';
    	    texto += '<p class="">A MGW investiu em um sistema intranet almejando bons resultados e conseguiremos isso com o engajamento dos colaboradores, pois de nada adianta que a Intranet esteja dentro das tendências, com ferramentas úteis e com informações relevantes sobre a MGW se os colaboradores não aderirem ao uso.</p>';
    	    texto += '<p class="">É fundamental que tenhamos em mente, que a Geração de Conteúdo não poderá ficar restrita apenas a um grupo de pessoas.</p>';
    	    texto += '<p class="">A Intranet tem que ter uma “alimentação colaborativa” e para isso teremos que incentivar que cada colaborador apresente sugestões !!</p>';
    		texto += '<p class="">Seria legal que trouxessem ideias para criarmos um Banco de Ideias para a Intranet.</p></div>';
    		
			var myModal = FLUIGC.modal({
				title: 'Sejam Bem-Vindos à nova intranet MGW Ativos !!',
				content: '<div class="row">' + imagem + texto + "</div>", 
				id: 'fluig-modal',
				size: 'full',
				actions: []
			}, function(err, data) {
				if(err) {
					// do error handling
				} else {
					// do something with data
				}
				
				//$(".model-body").css("max-height", "100%");
			});
			
			$(".modal-title").html("<h2  class='page-header'><strong>Seja bem vindo a nova intranet MGW Ativos</strong></h2>");
    	}

    },
    
    searchData: function() {
    	var data = {
			content: []
		};

		var constraints = new Array();

		var c1 = DatasetFactory.createConstraint("documentPK.documentId", 257, 257, ConstraintType.MUST);
		constraints.push(c1);

		var fields = new Array();
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("document", fields, constraints, sortingFields);

		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			data.content = dataset.values;
		}

		return data;
	},
  
});

