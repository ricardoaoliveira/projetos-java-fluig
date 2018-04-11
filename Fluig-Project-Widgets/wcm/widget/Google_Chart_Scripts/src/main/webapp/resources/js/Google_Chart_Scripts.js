var GoogleChartScripts = SuperWidget.extend({

    init: function() {
//    	console.log(this);
    	if (!this.isEditMode) {
    		$("#_instance_" + this.instanceId + "_").hide();
    	}
//    	google.charts.load('current', {'packages':['gauge', 'corechart']});
    },
  
});
