var documentos = null;

var all_data = [];

var changeDocumentoExpira = function(id) {
	var documentoExpiraSim = $("#documentoExpiraSim_" + id ).is(":checked");
	var documentoExpiraNao = $("#documentoExpiraNao_" + id).is(":checked");
	
	console.log("documentoExpiraSim: " + documentoExpiraSim);
	console.log("documentoExpiraNao: " + documentoExpiraNao);
	
	var item = new Object();
	item.cod_doc = id;
	
	if (documentoExpiraSim) {
		$("#validade_" + id).attr("disabled", false);
		item.expira = 'on';
		item.validade = 30;
		$("#validade_" + id).val(30);
	} else if (documentoExpiraNao) {
		item.expira = '';
		item.validade = null;
		$("#validade_" + id).val("");
		$("#validade_" + id).attr("disabled", true);
	}
	
	setDocumento(item);
};	

var listaDocumentos = new Array();

var setDocumento = function(item) {
	if (listaDocumentos.length == 0) {
		listaDocumentos.push(item);
	} else {
		var find = false;
		for (var i=0; i<listaDocumentos.length; i++) {
			if (listaDocumentos[i].cod_doc == item.cod_doc) {
				find = true;
				listaDocumentos[i].expira = item.expira;
				listaDocumentos[i].validade = item.validade;
			}
		}
		if (!find) {
			listaDocumentos.push(item);
		}
	}
};

var mapValidateInDays = new Object();
var mapNotificationInDays = new Object();

var setValidateInDays = function(fld, id) {
	
	var item = new Object();
	
	item.cod_doc = id;
	item.expira = 'on';
	item.ds_descriptor = parseInt(fld.value);
	item.validade = parseInt(fld.value);
	
	setDocumento(item);
};

var date_diff_indays = function(dt1, dt2) {
	//dt1 = date1new Date(date1);
	//dt2 = new Date(date2);
	return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) /(1000 * 60 * 60 * 24));
}

var date_is_past = function(dt1) {
	var now = new Date();
	now.setHours(0,0,0,0);

	dt1.setHours(0,0,0,0);
	
	if (dt1 < now) {
		return true;
	}
	
	return false;
}

var documentExpirationIsAlertSuccess = function(doc) {
	var dt = new Date(doc.expirationDate);
	if ( !date_is_past(dt) ) {
		var diff_days = date_diff_indays( new Date(), dt );
		if (diff_days > 30) {
			return true;
		}
	}
	return false;
};

var documentExpirationIsAlertWarning = function(doc) {
	var dt = new Date(doc.expirationDate);
	if ( !date_is_past(dt) ) {
		var diff_days = date_diff_indays( new Date(), dt );
		if (diff_days <= 30 && diff_days > 10) {
			return true;
		}
	}
	return false;
};

var formatarData = function(dt) {
	var month = dt.getMonth() + 1;
	var day = dt.getDay() + 1;
	var year = dt.getFullYear();
	month = !isNaN(month) && parseInt(month, 10) < 10 ? '0' + parseInt(month, 10) : month;
	day = !isNaN(day) && parseInt(day, 10) < 10 ? '0' + parseInt(day, 10) : day;
	return day + "/" + month + "/" + year;
};	

var documentExpirationIsAlertDanger = function(doc) {
	var dt = new Date(doc.expirationDate);
	if ( !date_is_past(dt) ) {
		var diff_days = date_diff_indays( new Date(), dt );
		if (diff_days <= 10) {
			return true;
		}
	} else {
		return true;
	}
	return false;
};	

var findDocumentsById = function(documentId) {
	var constraints = new Array();

	var c1 = DatasetFactory.createConstraint("documentPK.documentId", documentId, documentId, ConstraintType.MUST);
	var c2 = DatasetFactory.createConstraint("activeVersion", "true", "true", ConstraintType.MUST);
	var constraints   = new Array(c1,c2);
			
	var fields = new Array();
	var sortingFields = new Array();
			
	var dataset = DatasetFactory.getDataset("document", fields, constraints, sortingFields);

	return dataset.values;
};

var findDocumentsByParentId = function(parentDocumentId) {
	var constraints = new Array();

	var c1 = DatasetFactory.createConstraint("parentDocumentId", parentDocumentId, parentDocumentId, ConstraintType.MUST);
	var c2 = DatasetFactory.createConstraint("activeVersion", "true", "true", ConstraintType.MUST);
	var constraints   = new Array(c1,c2);
			
	var fields = new Array();
	var sortingFields = new Array();
			
	var dataset = DatasetFactory.getDataset("document", fields, constraints, sortingFields);

	return dataset.values;
};

var fillFolderDescription = function(item) {

	var folderDescription = "";
	
	folderDescription = item.documentDescription + " / " + folderDescription;

	if (item.parent != null) {
		folderDescription = fillFolderDescription(item.parent) + folderDescription;
	} 
	
	return folderDescription;
};

var folder_space = "";

var buildData = function(data_in) {
	for (var i=0; i<data_in.length > 0; i++) {
	
		data_in[i].key = data_in[i]["documentPK.documentId"];
		data_in[i].title = data_in[i].documentDescription;
		if ( data_in[i].documentType == 1 ) {
			data_in[i].folder = true;
			data_in[i].folderTitle = folder_space + data_in[i].title;
		} else {
			data_in[i].documentTitle = folder_space + data_in[i].title;
		}
	
		if ( !containsDocumentInAllData(data_in[i]) ) {
		
			var isDanger = documentExpirationIsAlertDanger(data_in[i]);
			var isWarning = documentExpirationIsAlertWarning(data_in[i]);
			var isSuccess = documentExpirationIsAlertSuccess(data_in[i]);
		
			data_in[i].isDanger = isDanger;
			data_in[i].isWarning = isWarning;
			data_in[i].isSuccess = isSuccess;
			var isInformation = false;
		
			var strExpirationDate = null;
		
			strExpirationDate = formatarData( new Date(data_in[i].expirationDate) );
		
			var strDocumentoExpira = "Sim";
		
			if (data_in[i].expirationDate == data_in[i].createDate) {
				isInformation = true;
				
				data_in[i].isDanger = false;
				data_in[i].isWarning = false;
				data_in[i].isSuccess = false;
				
				strDocumentoExpira = "Não";
				strExpirationDate = "";
			} 
		
			data_in[i].strDocumentoExpira = strDocumentoExpira;
		
			data_in[i].strExpirationDate = strExpirationDate;
			
			data_in[i].isInformation = isInformation;
		
			all_data.push(data_in[i]);
		}
	
		var children = findChildren(data_in[i]);
		
		if (children != null && children.length > 0) {
			data_in[i].children = children;
			fillParentInChidren(children, data_in[i]);
			buildData(data_in[i].children);
		}
	}
};

var findChildren = function(item) {
	var result = findDocumentsByParentId(item['documentPK.documentId']);
	return result;
};

var containsDocumentInAllData = function(doc) {
	for (var i=0; i<all_data.length; i++) {
		if (all_data[i]["documentPK.documentId"] == doc["documentPK.documentId"]) {
			return true;
		}
	}
	return false;
};

var fillParentInChidren = function(children, parent) {
	if (children != null && children.length > 0) {
		for (var i=0; i<children.length; i++) {
			children[i].parent = parent;
		}
	}
};

var ordenarDocumentosPorVencimentoDescendente = function() {
	all_data.sort(function(a,b){
	  return new Date(b.expirationDate) - new Date(a.expirationDate);
	});
};

var buildHtmlDocumentoExpira = function(item) {
	var strHtmlDocExpira = '<div class="row col-md-12 form-inline">';
	strHtmlDocExpira 	+= '	<div class="radio"> ';
	strHtmlDocExpira 	+= '		<label>';
	strHtmlDocExpira 	+= '			<input type="radio" name="documentoExpira_' + item['documentPK.documentId'] +'" id="documentoExpiraSim_' + item['documentPK.documentId'] +'" value="Sim" checked onchange="changeDocumentoExpira(' + item['documentPK.documentId'] + ');">';
	strHtmlDocExpira 	+= '			Sim';
	strHtmlDocExpira 	+= '		</label>';
	strHtmlDocExpira 	+= '	</div>';
	strHtmlDocExpira 	+= '	<div class="radio">';
	strHtmlDocExpira 	+= '		<label>';
	strHtmlDocExpira 	+= '			<input type="radio" name="documentoExpira_' + item['documentPK.documentId'] +'"" id="documentoExpiraNao_' + item['documentPK.documentId'] +'"" value="Não" onchange="changeDocumentoExpira(' + item['documentPK.documentId'] + ');">';
	strHtmlDocExpira 	+= '			Não';
	strHtmlDocExpira 	+= '		</label>';
	strHtmlDocExpira 	+= '	</div>';
	strHtmlDocExpira 	+= '</div>';
	return strHtmlDocExpira;
};

var buildHtmlValidate = function(index, item) {
	var strHtmlValidate = '	<div class="form-group">';
	strHtmlValidate 	+= '	<input type="number" id="validade_' + item['documentPK.documentId'] +'" min="1" step="1" class="form-control" onchange="setValidateInDays(this,' + item['documentPK.documentId'] + ');" />';
	strHtmlValidate 	+= '</div>';		
	return strHtmlValidate;
};

var listConfiguracaoValidadeDocumento = null;

var writeTable = function(isSuccess, isWarning, isDanger, isInformation) {

	//ordenarDocumentosPorVencimentoDescendente();

	listConfiguracaoValidadeDocumento = findAllConfiguracaoValidadeDocumento();
	var configuracaoValidadeDocumento = null;
	
	var str_html_table = '<div class="table">';
	str_html_table += 		'<table id="table_1" class="table table-responsive table-bordered table-condensed table-striped">';
	
	str_html_table +=				'<thead>';
	str_html_table +=					'<th> <span class="fancytree-icon fluigicon fluigicon-file"> </span> </th>';
	str_html_table +=					'<th>Nome da Pasta';
	str_html_table +=					'<th>Nome do Documento</th>';
	str_html_table +=					'<th>Documento Expira?</th>';
	str_html_table +=					'<th>Validade</th>';
	str_html_table +=				'</thead>';
	
	str_html_table +=		'<tbody>';
	
	for (var i=0; i<all_data.length; i++) {
	
		var item = all_data[i];
		
		if (!item.folder) {
		
			var canAdd = false;
		
			if (item.isSuccess && isSuccess) {
				canAdd = true;
			}
			
			if (item.isWarning && isWarning) {
				canAdd = true;
			}
			
			if (item.isDanger && isDanger) {
				canAdd = true;
			}
			
			if (item.isInformation && isInformation) {
				canAdd = true;
			}
		
			if (canAdd) {
			
				var tdStyle = '';
				if (item.isDanger) {
					tdStyle = 'documento_vermelho';
				} else if (item.isWarning) {
					tdStyle = 'documento_amarelo';
				} else if (item.isSuccess) {
					tdStyle = 'documento_verde';
				} else if (item.isInformation) {
					tdStyle = 'documento_azul';
				}
				
				item.folderTitle = fillFolderDescription(item.parent);
				
				tdStyle = '';
				
				str_html_table +=				'<tr>';
				str_html_table +=					'<td class="' + '' + '"> <a title="Ir para a pasta do documento" target="_blank" href="/portal/p/1/ecmnavigation?app_ecm_navigation_folder=' + item["documentPK.documentId"] + '"> <span class="fancytree-icon fluigicon fluigicon-file"> </span> </a> </td>';
				str_html_table +=					'<td class="' + tdStyle + '">'  + item.folderTitle +  ' </td>';
				str_html_table +=					'<td class="' + tdStyle + '">'  + item.documentTitle +  ' </td>';
				str_html_table +=					'<td class="' + tdStyle + '">' + buildHtmlDocumentoExpira(item); + '</td>';
				str_html_table +=					'<td class="' + tdStyle + '">' + buildHtmlValidate(i, item) +'</td>';					
				str_html_table +=				'</tr>';		
			}		
			
		}
	}
	
	// /portal/p/1/ecmnavigation?app_ecm_navigation_folder=1456
	
	str_html_table +=		'</tbody>';
	str_html_table +=		'</table>';
	str_html_table +=	'</div>';
		
	$("#container").html(str_html_table);
	
	updateTable();
	
	$('#table_1').DataTable({
		"ordering": false,
		/*columnDefs: [ {
		  targets: 3,
		  render: $.fn.dataTable.render.moment( 'DD/MM/YYYY', 'DD/MM/YYYY', 'pt' )
		} ],*/
		language: {
			"sEmptyTable": "Nenhum registro encontrado",
			"sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered": "(Filtrados de _MAX_ registros)",
			"sInfoPostFix": "",
			"sInfoThousands": ".",
			"sLengthMenu": "_MENU_ Resultados por Página",
			"sLoadingRecords": "Carregando...",
			"sProcessing": "Processando...",
			"sZeroRecords": "Nenhum registro encontrado",
			"sSearch": "Pesquisar",
			"oPaginate": {
				"sNext": "Próximo",
				"sPrevious": "Anterior",
				"sFirst": "Primeiro",
				"sLast": "Último"
			},
			"oAria": {
				"sSortAscending": ": Ordenar colunas de forma ascendente",
				"sSortDescending": ": Ordenar colunas de forma descendente"
			}
		}
	});
	
};

var salvar = function() {
	for (var i=0; i<listaDocumentos.length; i++) {
		insertOrUpdateConfiguracaoValidadeDocumento(listaDocumentos[i]);
	}
};

var filtrarDocumentos = function() {

	var doc_no_prazo = $("#doc_no_prazo").is(':checked');
	var doc_a_expirar = $("#doc_a_expirar").is(':checked');
	var doc_expirado = $("#doc_expirado").is(':checked');
	var doc_nao_expira = $("#doc_nao_expira").is(':checked');

	writeTable(doc_no_prazo, doc_a_expirar, doc_expirado, doc_nao_expira);
};

var updateTable = function() {
	
	listConfiguracaoValidadeDocumento = findAllConfiguracaoValidadeDocumento();
	
	var configuracaoValidadeDocumento = null;
	var canAdd = null;	
	
	for (var i=0; i<all_data.length; i++) {
		
		var item = all_data[i];
		
		if (!item.folder) {
		
			var cod_doc = item["documentPK.documentId"];
			
			if (cod_doc != null) {
				updateTableRow( cod_doc );	
			}
		}		
	}
};

var updateTableRow = function(cod_doc) {
	
	var configuracaoValidadeDocumento = getConfiguracaoValidadeDocumento(cod_doc, listConfiguracaoValidadeDocumento);
	
	console.log("configuracaoValidadeDocumento");
	console.log(configuracaoValidadeDocumento);
	
	if (configuracaoValidadeDocumento != null) {
		
		var item = {
			cod_doc: configuracaoValidadeDocumento.cod_doc,
			expira: configuracaoValidadeDocumento.expira,
			ds_descriptor: configuracaoValidadeDocumento.ds_descriptor,
			validade: configuracaoValidadeDocumento.validade
		};
		
		//setDocumento(item);
		
		if ( configuracaoValidadeDocumento.expira == "on" ) {
			console.log("expira on checked");
			$("#documentoExpiraSim_" + configuracaoValidadeDocumento.cod_doc ).attr("checked", true);
			$("#documentoExpiraNao_" + configuracaoValidadeDocumento.cod_doc ).attr("checked", false);
		} else {
			console.log("expira não checked");
			$("#documentoExpiraSim_" + configuracaoValidadeDocumento.cod_doc ).attr("checked", false);
			$("#documentoExpiraNao_" + configuracaoValidadeDocumento.cod_doc ).attr("checked", true);
		}
		
		if ( configuracaoValidadeDocumento.expira == "on" ) {
			console.log("expira on validade");
			$("#validade_" + configuracaoValidadeDocumento.cod_doc).attr("disabled", false);
			$("#validade_" + configuracaoValidadeDocumento.cod_doc).val( configuracaoValidadeDocumento.validade );						
		} else {
			console.log("expira não validade disabled");
			$("#validade_" + configuracaoValidadeDocumento.cod_doc).attr("disabled", false);
			$("#validade_" + configuracaoValidadeDocumento.cod_doc).val( "" );	
			$("#validade_" + configuracaoValidadeDocumento.cod_doc).attr("disabled", true);
		}
	} else {
		$("#validade_" + cod_doc).attr("disabled", false);
		$("#validade_" + cod_doc).val( 30 );
	}
};

$(document).ready(function() {
	
	// Component construction by setting the window.
	var myLoading2 = FLUIGC.loading(window);
	// We can show the message of loading
	myLoading2.show();
	
	documentos = findDocumentsById(633);

	buildData(documentos);

	writeTable(true, true, true, false);	
	
	myLoading2.hide();
});
