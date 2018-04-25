var ListaTelefonesEmergencia = SuperWidget.extend({

    init: function() {
    	this.initTable();
    },
    
    isEven: function(n) {
		return n % 2 == 0;
	},

	isOdd: function(n) {
		return Math.abs(n % 2) == 1;
	},
	
	searchData: function() {
		var data = {
			content: []
		};
		
		var constraints   = new Array();
 
		var fields = new Array("id", "nm", "fone");
		var sortingFields = new Array("nm");
				
		var dataset = DatasetFactory.getDataset("ds_telefones_emergencia", fields, constraints, sortingFields);
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			data.content = dataset.values;
		}
		
		return data;
	},
    
	strToLinkTel: function(str) {
		var isNumeric = /^[-+]?(\d+|\d+\.\d*|\d*\.\d+)$/;

		var newStr = 'tel://+55';
		
		for (var i=0; i<str.length; i++) {
			if (isNumeric.test(str[i])) {
				newStr += str[i];
			}
		}
		
		return newStr;
	},    
	
    initTable: function() {
    	var data = this.searchData();
    	
    	for (var i=0; i<data.content.length; i++) {
    		
    		if (data.content[i].fone != null && data.content[i].fone.length > 0) {
				data.content[i].foneLink = this.strToLinkTel( data.content[i].fone );
			}
    		
    		if ( this.isEven(i) ) {
    			data.content[i].td_class = 'linha_impar';
    		} else {
    			data.content[i].td_class = 'linha_par';
    		}
    	}
    	
    	var template = "{{#content}}<tr class='{{td_class}}'>" +
    		"<td class='{{td_class}}'>{{nm}}</td>" +    		
    		"<td class='{{td_class}}'><a href='{{foneLink}}'>{{fone}}</a></td>{{/content}}" +
    	"</tr>";
    	
        var html = Mustache.to_html(template, data);
    	
    	$("#tblListaTelefone_" + this.instanceId).append(html);
    }
    
});