var GooglePieChart = SuperWidget.extend({

    //método iniciado quando a widget é carregada
    init: function() {
    	if (!this.isEditMode) {
    		this.DOM.parents('.wcm_corpo_widget_single').siblings('.wcm_title_widget').remove();
    	}
    	this.initPieChart();
    },
  
    initPieChart: function() {
    	var self = this;
    	
    	var dataset = this.searchData();
    	
    	var valores = new Array();
    	
    	valores.push(['Task', 'Hours per Day']);
    	
    	var titulo = '';
    	
    	if (dataset.values != null && dataset.values.length > 0) {
    		titulo = dataset.values[0].titulo;
    		for (var i=0; i<dataset.values.length; i++) {
    			valores.push([dataset.values[i].nm_escob, parseInt(dataset.values[i].vl_escob) ])
    		}
    	}
    	
        google.charts.setOnLoadCallback(function() {
        	  var data = google.visualization.arrayToDataTable(valores);

              var options = {
                title: titulo,
                is3D: true,
              };
              
              var chart = new google.visualization.PieChart(document.getElementById('piechart_3d_' + self.instanceId));
              chart.draw(data, options);
        });
    },
    
    searchData: function() {
		var constraints   = new Array();
 
		var fields = new Array("id", "ds_descriptor", "nm", "titulo", "nm_escob", "vl_escob");
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("dsAcompanhamentoEscobs", fields, constraints, sortingFields);
		
		return dataset;
	},

});
