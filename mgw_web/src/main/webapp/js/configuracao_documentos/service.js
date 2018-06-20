var insertConfiguracaoValidadeDocumento = function(item) {
	try {		
		var constraints   = new Array();
		var c_cod_doc = DatasetFactory.createConstraint("cod_doc", item.cod_doc, item.cod_doc, ConstraintType.MUST);
		var c_expira = DatasetFactory.createConstraint("expira", item.expira, item.expira, ConstraintType.MUST);
		var c_validade = DatasetFactory.createConstraint("validade", item.validade, item.validade, ConstraintType.MUST);
		var c_ds_descriptor = DatasetFactory.createConstraint("ds_descriptor", item.ds_descriptor, item.ds_descriptor, ConstraintType.MUST);
		var c_METODO = DatasetFactory.createConstraint("METODO", 'INSERT', 'INSERT', ConstraintType.MUST);
	
		constraints.push(c_cod_doc);
		constraints.push(c_expira);
		constraints.push(c_validade);
		constraints.push(c_ds_descriptor);
		constraints.push(c_METODO);
	
		var fields = new Array();
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("dsCrudConfiguracaoValidadeDocumento", fields, constraints, sortingFields);
	} catch(e) {
		console.log(e);	
	}
};

var updateConfiguracaoValidadeDocumento = function(item) {	
	try {		
		var constraints   = new Array();
		var c_cod_doc = DatasetFactory.createConstraint("cod_doc", item.cod_doc, item.cod_doc, ConstraintType.MUST);
		var c_expira = DatasetFactory.createConstraint("expira", item.expira, item.expira, ConstraintType.MUST);
		var c_validade = DatasetFactory.createConstraint("validade", item.validade, item.validade, ConstraintType.MUST);
		var c_ds_descriptor = DatasetFactory.createConstraint("ds_descriptor", item.ds_descriptor, item.ds_descriptor, ConstraintType.MUST);
		var c_METODO = DatasetFactory.createConstraint("METODO", 'UPDATE', 'UPDATE', ConstraintType.MUST);
		var c_id = DatasetFactory.createConstraint("id", item.id, item.id, ConstraintType.MUST);

		constraints.push(c_cod_doc);
		constraints.push(c_expira);
		constraints.push(c_validade);
		constraints.push(c_ds_descriptor);
		constraints.push(c_METODO);
		constraints.push(c_id);

		var fields = new Array();
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("dsCrudConfiguracaoValidadeDocumento", fields, constraints, sortingFields);
		
	} catch(e) {		
		console.log(e);
	}
};

var findConfiguracaoValidadeDocumento = function(item) {
	var dataset = null;
	
	try {
		var constraints   = new Array();
		var c_cod_doc = DatasetFactory.createConstraint("cod_doc", item.cod_doc, item.cod_doc, ConstraintType.MUST);

		constraints.push(c_cod_doc);
		var fields = new Array();
		var sortingFields = new Array();
				
		dataset = DatasetFactory.getDataset("dsConfiguracao_Validade_Documento", fields, constraints, sortingFields);
		
	} catch(e) {
		console.log(e);
	}
		
	return dataset;
};

var findAllConfiguracaoValidadeDocumento = function() {
	var dataset = null;
	
	try {
		var constraints   = new Array();

		var fields = new Array();
		var sortingFields = new Array();
				
		dataset = DatasetFactory.getDataset("dsConfiguracao_Validade_Documento", fields, constraints, sortingFields);
		
	} catch(e) {
		console.log(e);
	}
		
	return dataset;
};

var getConfiguracaoValidadeDocumento = function(cod_doc, listConfiguracaoValidadeDocumento) {

	var configuracaoValidadeDocumento = null;
	
	try {
		for (var i=0; i<listConfiguracaoValidadeDocumento.values.length; i++) {
			if (listConfiguracaoValidadeDocumento.values[i].cod_doc == cod_doc) {
				configuracaoValidadeDocumento = listConfiguracaoValidadeDocumento.values[i];
			}
		}
	} catch(e) {
		console.log(e);
	}
		
	return configuracaoValidadeDocumento;
};

var insertOrUpdateConfiguracaoValidadeDocumento = function(item) {
	try {
		if (item != null && item.cod_doc != null) {
			buildDsDescriptor(item);
			var dataset = findConfiguracaoValidadeDocumento(item);
			if (dataset != null && dataset.values.length > 0) {
				item.id = dataset.values[0].documentid;
				updateConfiguracaoValidadeDocumento(item);
			} else {
				insertConfiguracaoValidadeDocumento(item);
			}
		}
	} catch(e) {
		console.log(e);
	}	
};

var buildDsDescriptor = function(item) {
	var dsDescriptor = item.cod_doc;
	if (item.expira == null || item.expira == "") {
		dsDescriptor += " - Não Expira";
	} else {
		dsDescriptor += " - Expira";
		if (item.validade != null && item.validade != "") {
			dsDescriptor += " - " + item.validade + " dia(s)";	
		}
	}
	
	item.ds_descriptor = dsDescriptor;
};