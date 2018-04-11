var RSSNoticias = SuperWidget.extend({
    
    init: function() {
        console.log("init: " + this.instanceId);
    	this.searchRSS(this.instanceId);
    },
  
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
		
		$.ajax({
		  type: 'GET',
		  url: "https://api.rss2json.com/v1/api.json?rss_url=" + url,
		  dataType: 'jsonp',
		  success: function(result) {
			
			console.log(result);
			
			var template = '';
		
			for (var i=0; i<result.items.length; i++) {
				data.content[i] = new Object();
				data.content[i].title = result.items[i].title;			
				data.content[i].content = result.items[i].content;			
				data.content[i].description = result.items[i].description;		
				data.content[i].link = result.items[i].link;			
				data.content[i].thumbnail = result.items[i].thumbnail;					
				data.content[i].collapseIndice = i;
				
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
	    		$("#accordion_" + vInstanceId).append(template);
	    	} catch(e) {
	    		console.log("erro accordion: " + e.message);
	    	}	    	
			
			$('#collapse').collapse({
				toggle: false
			});
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
				
		var dataset = DatasetFactory.getDataset("ds_noticias_rss", fields, constraints, sortingFields);
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			data.content = dataset.values;
		}
		
		return data;
	},
  
});

