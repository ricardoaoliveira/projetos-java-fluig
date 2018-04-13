var AcessosExternos = SuperWidget.extend({
    
    init: function() {
    	console.log(this.instanceId);
    	this.initView(this.instanceId);
    },
    
    initView: function(vInstanceId) {
    	var data = this.searchData();
    	
    	if (data.content.length > 0) {
    		var template = $("#template_" + vInstanceId).html();

    		for (var i=0; i<data.content.length; i++) {
    			data.content[i].imageUrl = 
    				this.getImageUrl(data.content[i].companyid, 
    							data.content[i].documentid, 
    							data.content[i].version);
    		}
    		
    		try {
    			var html = Mustache.render(template, data);
        		
        		$("#container_" + vInstanceId).append(html);
    		} catch(e) {
    			console.log(e);
    		}
    	}
    },
    
    getImageUrl: function(companyid, documentid, version) {
    	var imageUrl = WCMAPI.getServerURL() + 
    		"/webdesk/streamcontrol/padrao.png?WDCompanyId=" + companyid +
    		"&WDNrDocto=" + documentid + "&WDNrVersao=" + version;
    		
    	console.log(imageUrl);
    	
    	return imageUrl;
    },
    
    searchData: function() {
		var data = {
			content: []
		};
		
		var constraints = new Array();
		
		var fields = new Array("id", "companyid", "titulo", "url", "version");
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("ds_acessos_externos", fields, constraints, sortingFields);
		
		console.log("dataset: " + dataset.values.length);
		
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			data.content = dataset.values;
		}
		
		console.log(data);
		
		return data;
	},    
        
});