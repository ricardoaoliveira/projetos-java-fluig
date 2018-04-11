var PainelTaxasRSS = SuperWidget.extend({
    
    init: function() {
    	console.log("init: " + this.instanceId);
    	
    	var data = this.searchData();
    	
    	for (var i=0; i<data.content.length; i++) {
    		
    		this.appendTableByRssUrl(data.content[i].url, data.content[i].titulo, "corpo_impar_painel_taxas_rss", this.instanceId);
    	}
    },
    
    appendTableByRssUrl: function(url, titulo, css_corpo, vInstanceId) {
    	var data = {
    			content: []
		};
		
		$.ajax({
		  type: 'GET',
		  url: "https://api.rss2json.com/v1/api.json?rss_url=" + url,
		  dataType: 'jsonp',
		  success: function(result) {
			
			for (var i=0; i<result.items.length; i++) {
				
				console.log(result.items[i].content);
				
				$("#painel_" + vInstanceId).append("<div class='cabecalho_painel_taxas_rss'>" + titulo + "<div>");
				$("#painel_" + vInstanceId).append("<div class='" + css_corpo + "'>" + result.items[i].content + "</div>");
			}
			
		  }
		});
    },
    
    searchData: function() {
		var data = {
			content: []
		};
		
		var constraints   = new Array();
 
		var fields = new Array("id", "titulo", "url");
		var sortingFields = new Array("titulo");
				
		var dataset = DatasetFactory.getDataset("ds_painel_taxas_rss", fields, constraints, sortingFields);
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			data.content = dataset.values;
		}
		
		return data;
	},
   
});
