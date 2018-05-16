var ReservaSalaReuniao = SuperWidget.extend({

    init: function() {
    	this.initView();    	
    },
    
    initView: function() {
    	//PROD
    	//var documentId = 276;

    	//TESTES
    	var documentId = 1376;
    	
    	var c1 = DatasetFactory.createConstraint("documentPK.documentId", documentId, documentId, ConstraintType.MUST);
    	var c2 = DatasetFactory.createConstraint("activeVersion", "true", "true", ConstraintType.MUST);
    	var constraints   = new Array(c1,c2);
    	var sortingFields = new Array(); 
    	var dataset = DatasetFactory.getDataset("document", null, constraints, sortingFields);

    	if (dataset != null && dataset.values.length > 0) {
    		
    		var imageUrlGenerate = "/webdesk/streamcontrol/?WDCompanyId=1&WDNrDocto=" + documentId + "&WDNrVersao=" + dataset.values[0]["documentPK.version"];
    		
    		console.log("Open_Reserva_Sala_Reuniao, imageUrl: " + imageUrlGenerate);
    		
    		var data = {
    			content: [{
    				imageUrl: imageUrlGenerate
    			}]    			
    		};
    		
    		var template = "{{#content}}<img src='{{imageUrl}}' style='width: 100%; height: 100%;' />{{/content}}";
    		
    		try {
    			var html = Mustache.render(template, data);
        		
        		$("#container_" + this.instanceId).append(html);
    		} catch(e) {
    			console.log(e);
    		}
    		
    	}
    },
  
});
