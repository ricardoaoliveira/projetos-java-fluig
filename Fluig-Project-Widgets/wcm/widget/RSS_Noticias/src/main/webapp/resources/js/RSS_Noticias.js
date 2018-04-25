var RSSNoticias = SuperWidget.extend({
    
	bindings: {
		local: {
			'recolher': ['click_recolher'],
			'mostrar-mais': ['click_mostrarMais']
		},
		global: {}
	},
	
    init: function() {
        console.log("init: " + this.instanceId);
    	this.searchRSS(this.instanceId);
    },
    
	minShow: 3,
    
    result: new Array(),
    
  	searchRSS: function(vInstanceId) {
  		var data = {
			content: []
		};
	
		var dsData = this.searchData();
		
		var url = null;
		var header = null;
		
		for (var i=0; i<dsData.content.length; i++) {
    		url = dsData.content[i].url;
    		header = dsData.content[i].titulo;
    	}
    		
		if (url == null) {
			return;
		}
		
		var dataHora = " (" + FLUIGC.calendar.formatDateTimeZone(new Date()) + ")";
		
		$("#divHeader_" + vInstanceId).html(header + dataHora);
		
		$("#divHeaderDataHora_" + vInstanceId).html(dataHora);
		
		var api_key = 'mxz0eeqjpkndgqlmka9beq2mi3d3z4ozosnghqov';
		
		$.ajax({
		  type: 'GET',
		  url: "https://api.rss2json.com/v1/api.json?rss_url=" + url + '&api_key=' + api_key,
		  dataType: 'jsonp',
		  success: function(resp) {
			result = resp;
			
			var template = '';
			
			for (var i=0; i<result.items.length; i++) {

				if (i < 3) {
					template += "<div class='panel panel-default'>";
					template += 		"<div class='panel-heading'>";
					template += 			"<h4 class='panel-title'>";
					template += 				"<a class='collapse-icon up' data-toggle='collapse' data-parent='#accordion_" + vInstanceId + "' href='#collapse" + i + "'>";
					template += 				result.items[i].title;
					template += 			"</h4>";
					template += 		"</div>";
					template += 	"<div id='collapse" + i + "' class='panel-collapse collapse'>";
					template += 		"<div class='panel-body'>";
					template +=					"<div class='row'>"			
					template += 					"<div class='col-md-12'>"
					template += 						"<a href='" + result.items[i].link + "' target='_blank'>" + result.items[i].content + "</a>";
					template += 					"</div>"
					template +=					"</div>"			
					template += 		"</div>";
					template += 	"</div>";
					template += "</div>";	
				}
			}
			
			try {
	    		$("#accordion_" + vInstanceId).html(template);
	    	} catch(e) {
	    		console.log("erro accordion: " + e.message);
	    	}	    	
			
			$('#collapse').collapse({
				toggle: false
			});
			
			$('#btnCarregarMais_' + vInstanceId).show();
			$('#btnRecolher_' + vInstanceId).hide();
		  }
		});
  	},
  	
  	recolher: function() {
  		console.log(result);
		
  		var vInstanceId = this.instanceId;
  		
		var template = '';
	
		for (var i=0; i<result.items.length; i++) {

			if (i < this.minShow) {
				template += "<div class='panel panel-default'>";
				template += 		"<div class='panel-heading'>";
				template += 			"<h4 class='panel-title'>";
				template += 				"<a class='collapse-icon up' data-toggle='collapse' data-parent='#accordion_" + vInstanceId + "' href='#collapse" + i + "'>";
				template += 				result.items[i].title;
				template += 			"</h4>";
				template += 		"</div>";
				template += 	"<div id='collapse" + i + "' class='panel-collapse collapse'>";
				template += 		"<div class='panel-body'>";
				template +=					"<div class='row'>"			
				template += 					"<div class='col-md-12'>"
				template += 						"<a href='" + result.items[i].link + "' target='_blank'>" + result.items[i].content + "</a>";
				template += 					"</div>"
				template +=					"</div>"			
				template += 		"</div>";
				template += 	"</div>";
				template += "</div>";	
			}
		}
		
		try {
    		$("#accordion_" + vInstanceId).html(template);
    	} catch(e) {
    		console.log("erro accordion: " + e.message);
    	}	    	
		
		$('#collapse').collapse({
			toggle: false
		});
		
		$('#btnCarregarMais_' + this.instanceId).show();
		$('#btnRecolher_' + vInstanceId).hide();		
  	},
  	
  	mostrarMais: function() {
  		console.log(result);
		
  		var vInstanceId = this.instanceId;
  		
		var template = '';
	
		for (var i=0; i<result.items.length; i++) {

			template += "<div class='panel panel-default'>";
			template += 		"<div class='panel-heading'>";
			template += 			"<h4 class='panel-title'>";
			template += 				"<a class='collapse-icon up' data-toggle='collapse' data-parent='#accordion_" + vInstanceId + "' href='#collapse" + i + "'>";
			template += 				result.items[i].title;
			template += 			"</h4>";
			template += 		"</div>";
			template += 	"<div id='collapse" + i + "' class='panel-collapse collapse'>";
			template += 		"<div class='panel-body'>";
			template +=					"<div class='row'>"			
			template += 					"<div class='col-md-12'>"
			template += 						"<a href='" + result.items[i].link + "' target='_blank'>" + result.items[i].content + "</a>";
			template += 					"</div>"
			template +=					"</div>"			
			template += 		"</div>";
			template += 	"</div>";
			template += "</div>";	
		}
		
		try {
    		$("#accordion_" + vInstanceId).html(template);
    	} catch(e) {
    		console.log("erro accordion: " + e.message);
    	}	    	
		
		$('#collapse').collapse({
			toggle: false
		});
		
		$('#btnCarregarMais_' + vInstanceId).hide();
		$('#btnRecolher_' + vInstanceId).show();
  	},
  	
  	searchData: function() {
		var data = {
			content: []
		};
		
		var constraints   = new Array();
 
		var fields = new Array("id", "titulo", "url");
		var sortingFields = new Array("titulo");
				
		var dataset = DatasetFactory.getDataset("ds_noticias_rss", fields, constraints, sortingFields);
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			data.content = dataset.values;
		}
		
		return data;
	},
  
});

