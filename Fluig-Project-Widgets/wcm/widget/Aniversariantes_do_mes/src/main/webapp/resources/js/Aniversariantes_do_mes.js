var AniversariantesDoMes = SuperWidget.extend({
    
    init: function() {
    	console.log(this.instanceId);
    	this.initView(this.instanceId);
    },  
    
    initView: function(vInstanceId) {
    	var data = this.searchData();
    	
    	if (data.content.length > 0) {
    		var template = $("#template_" + vInstanceId).html();

    		console.log("Aniversariantes - template: " + template);
    		
    		var partials = {
    			urlImagem: WCMAPI.getServerURL() + "/webdesk/streamcontrol/padrao.png?WDCompanyId={{companyId}}&WDNrDocto={{documentid}}&WDNrVersao={{version}}",
    		};
    		
    		try {
    			var html = Mustache.render(template, data, partials);
        		
        		$("#container_" + vInstanceId).append(html);
    		} catch(e) {
    			console.log(e);
    		}
    	}
    },
    
    searchData: function() {
		var data = {
			content: []
		};
		
		var constraints   = new Array();
 
		var fields = new Array("id", "version", "companyId", "titulo");
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("ds_aniversariantes_do_mes", fields, constraints, sortingFields);
		
		console.log("dataset: " + dataset.values.length);
		
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			data.content = new Array( dataset.values[0] );
		}
		
		console.log(data);
		
		return data;
	}
    
});

