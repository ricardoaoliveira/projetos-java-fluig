var BoasVindas = SuperWidget.extend({

    init: function() {
    	console.log(this.instanceId);
    	this.initView();
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

    		//var data = this.searchData();
    		
    		console.log("Boas Vindas: data: " + data);
    		
    		//if (data.content != null) {
    			//var urlImagem = WCMAPI.getServerURL() + "/webdesk/streamcontrol/Boas-vindas-6.png?WDCompanyId=1&WDNrDocto=257&WDNrVersao=" 
    				//+ data.content[0]["documentPK.version"]; 
    			
    			localStorage.setItem("boas-vindas-fluig-mgw"  + WCMAPI.getUserCode(), WCMAPI.getUserCode());
    			
    			var myModal = FLUIGC.modal({
    				title: 'Sejam Bem-Vindos à nova intranet MGW Ativos !!',
    				content: '<div class="row col-md-6"><image src="/Boas_Vindas/resources/images/Boas-Vindas-Left.png" style="width: 100%; height: 100%;" /></div> <div class="row col-md-6"><image src="/Boas_Vindas/resources/images/Boas-Vindas-Right.png" style="width: 100%; height: 100%;" /></div>',
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
    		//}
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

