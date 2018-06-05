<div id="CockpitExpiracaoDocumentos_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="CockpitExpiracaoDocumentos.instance()">

	<h2 class="page-header"> 
		<span class="fluigicon fluigicon-replace-file"></span>
		Validade de Documentos
	</h2>

	<div id="container_${instanceId}" class="row col-md-12">
		
	</div>

</div>

<script src="/webdesk/vcXMLRPC.js" type="text/javascript"></script>

<script type="text/javascript">
	
	var documentos = null;

	var all_data = [];

	var findDocumentsById = function(documentId) {
		var constraints = new Array();
	
		var c1 = DatasetFactory.createConstraint("documentPK.documentId", documentId, documentId, ConstraintType.MUST);
		var c2 = DatasetFactory.createConstraint("activeVersion", "true", "true", ConstraintType.MUST);
		var constraints   = new Array(c1,c2);
				
		var fields = new Array();
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("document", fields, constraints, sortingFields);
	
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			console.log("dataset.values: ");
			console.log(dataset.values);
		}
		
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
	
		if (dataset != null && dataset.values != null && dataset.values.length > 0) {
			console.log("dataset.values: ");
			console.log(dataset.values);
		}
		
		return dataset.values;
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
	
	var ordenarDocumentosPorVencimentoDescendente = function() {
		all_data.sort(function(a,b){
		  return new Date(b.expirationDate) - new Date(a.expirationDate);
		});
	};
	
	var writeTable = function() {
	
		ordenarDocumentosPorVencimentoDescendente();
	
		var str_html_table = '<div class="table">';
		str_html_table += 		'<table class="table table-responsive table-bordered table-condensed table-striped">';
		str_html_table +=			'<div class="table-responsive">';
		
		str_html_table +=				'<tr>';
		str_html_table +=					'<th>Nome do Documento</th>';
		str_html_table +=					'<th>Data de Vencimento</th>';
		str_html_table +=				'</tr>';
		
		for (var i=0; i<all_data.length; i++) {
		
			var item = all_data[i];
			
			if (!item.folder) {
			
				var isDanger = documentExpirationIsAlertDanger(item);
				var isWarning = documentExpirationIsAlertWarning(item);
				var isSuccess = documentExpirationIsAlertSuccess(item);
				
				var tdStyle = '';
				if (isDanger) {
					tdStyle = 'documento_vermelho';
				} else if (isWarning) {
					tdStyle = 'documento_amarelo';
				} else if (isSuccess) {
					tdStyle = 'documento_verde';
				}
				
				var strExpirationDate = 'Não expira';
				
				if (item.expirationDate != item.createDate) {
					strExpirationDate = formatarData( new Date(item.expirationDate) );
				} else {
					tdStyle = 'documento_verde';
				}
				
				str_html_table +=				'<tr>';
				str_html_table +=					'<td class="' + tdStyle + '"> <a title="Ir para a pasta do documento" target="_blank" href="/portal/p/1/ecmnavigation?app_ecm_navigation_folder=' + item["documentPK.documentId"] + '"> <span class="fancytree-icon fluigicon fluigicon-file"> </span> ' + item.documentTitle +  '</a> </td>';
				str_html_table +=					'<td class="' + tdStyle + '"> <a title="Ir para a pasta do documento" target="_blank" href="/portal/p/1/ecmnavigation?app_ecm_navigation_folder=' + item["documentPK.documentId"] + '">' + strExpirationDate +' </a> </td>';
				str_html_table +=				'</tr>';
			}
		}
		
		// /portal/p/1/ecmnavigation?app_ecm_navigation_folder=1456
		
		str_html_table +=			'</div>';
		str_html_table +=		'</table>';
		str_html_table +=	'</div>';
			
		$("#container_${instanceId}").append(str_html_table);
	};
	
	documentos = findDocumentsById(633);
	
	buildData(documentos);
	
	writeTable();	
	
	console.log(documentos);
	
</script>