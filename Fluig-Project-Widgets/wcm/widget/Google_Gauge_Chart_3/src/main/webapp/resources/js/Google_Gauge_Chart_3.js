var GoogleGaugeChart3 = SuperWidget.extend({

    init: function() {
    	if (!this.isEditMode) {
    		this.DOM.parents('.wcm_corpo_widget_single').siblings('.wcm_title_widget').remove();
    	}
    	this.initChart();
    },
    
    initChart: function() {
    	var self = this;
    	
    	var data = {
			content: []
		};
    	
    	var dataset = this.searchData();
    	
    	if (dataset != null && dataset.values.length > 0) {
    		for (var i=0; i<dataset.values.length; i++) {
        		data.content.push({indice: i});
        	}	
    	}
    	
    	var template = null;
    	
    	var isMobile = WCMAPI.isMobileAppMode();
    	
    	/*if ( isMobile ) {
    		template = $("#templateMobile_" + self.instanceId).html();
    	} else {
    		template = $("#template_" + self.instanceId).html();	
    	}*/

    	//var html = Mustache.render(template, data);
    	
    	var html = this.generateHtml(data);
    	
    	$("#containerCharts_" + self.instanceId).append(html);
    	
    	var options = {
            width: 200, height: 130,
            redFrom: 0, redTo: 94,
            greenFrom: 95, greenTo: 100,
            minorTicks: 5
        };
    	
    	var valores = new Array();
    	
    	if (dataset.values != null && dataset.values.length > 0) {    		
    		for (var i=0; i<dataset.values.length; i++) {
    			valores.push([dataset.values[i].mes_ano, parseFloat(dataset.values[i].vl) ])
    		}
    	}
    	
    	var arrayToChart = null;
    	
    	for (var i=0; i<valores.length; i++) {
    		arrayToChart = new Array();
    		arrayToChart.push(['Label', 'Value']);
    		arrayToChart.push(valores[i]);
    		
    		//if ( isMobile ) {
    			//this.addValores(arrayToChart, options, "divMobile_" + self.instanceId + "_" + i);
    		//} else {
    			this.addValores(arrayToChart, options, "div_" + self.instanceId + "_" + i);
    		//}
    	}
    },
    
    generateHtml: function(data) {    	
    	var htmlResult = '';
    	
    	if (!WCMAPI.isMobileAppMode()) {
    		htmlResult += '<div class="col-md-3 col-lg-3 col-sm-2 col-xs-6" id="div_esquerda_' + this.instanceId + '"></div>';
    	}
    	
    	for (var i=0; i<data.content.length; i++) {
    		htmlResult += "<div class='col-md-2 col-lg-2 col-sm-3 col-xs-6' style='text-align: center;'>";
    		htmlResult += "<div id='div_" + this.instanceId + "_" + data.content[i].indice + "' style='text-align: center;'>";
    		htmlResult += "</div>";
    		htmlResult += "</div>";
    	}
    	
    	return htmlResult;
    },
    
    addValores: function(vValores, options, divId) {
		google.charts.setOnLoadCallback(function() {
			  var data = google.visualization.arrayToDataTable(vValores);
			
			  var chart = new google.visualization.Gauge(document.getElementById(divId));

			  chart.draw(data, options);
		});
	},
    
    searchData: function() {
		var constraints   = new Array();
 
		var fields = new Array("id", "ds_descriptor", "nm", "mes_ano", "vl");
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("dsIndicadoresOperacao", fields, constraints, sortingFields);
		
		return dataset;
	},

});

