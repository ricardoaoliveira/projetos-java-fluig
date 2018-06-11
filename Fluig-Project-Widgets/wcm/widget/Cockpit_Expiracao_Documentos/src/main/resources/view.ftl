<div id="CockpitExpiracaoDocumentos_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="CockpitExpiracaoDocumentos.instance()">

	<h2 class="page-header"> 
		<span class="fluigicon fluigicon-replace-file"></span>
		Validade de Documentos
	</h2>

	<div class="row col-md-12 form-inline" align="right">
		<label>Exibir: </label>
		<div class="checkbox">
			<label style="color: green; font-weight: bold;">
				<input type="checkbox" id="doc_no_prazo_${instanceId}" name="doc_no_prazo_${instanceId}" checked onchange="filtrarDocumentos_${instanceId}();"> 
				No Prazo
			</label>
		</div>
		<div class="checkbox">
			<label style="color: yellow; font-weight: bold;">
				<input type="checkbox" id="doc_a_expirar_${instanceId}" name="doc_a_expirar_${instanceId}" checked onchange="filtrarDocumentos_${instanceId}();"> 
				À Expirar
			</label>
		</div>
		<div class="checkbox">
			<label style="color: red; font-weight: bold;">
				<input type="checkbox" id="doc_expirado_${instanceId}" name="doc_expirado_${instanceId}" checked onchange="filtrarDocumentos_${instanceId}();"> 
				Expirado
			</label>
		</div>
		<div class="checkbox">
			<label style="color: blue; font-weight: bold;">
				<input type="checkbox" id="doc_nao_expira_${instanceId}" name="doc_nao_expira_${instanceId}" onchange="filtrarDocumentos_${instanceId}();"> 
				Não Expira
			</label>
		</div>
	</div>

	<div class="row col-md-12">
		<br/>
	</div>

	<div id="container_${instanceId}" class="row col-md-12">
		
	</div>

</div>

<script src="/webdesk/vcXMLRPC.js" type="text/javascript"></script>

<script type="text/javascript">
	
	var documentos_${instanceId} = null;

	var all_data_${instanceId} = [];

	var date_diff_indays_${instanceId} = function(dt1, dt2) {
		//dt1 = date1new Date(date1);
		//dt2 = new Date(date2);
		return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) /(1000 * 60 * 60 * 24));
	}
	
	var date_is_past_${instanceId} = function(dt1) {
		var now = new Date();
		now.setHours(0,0,0,0);

		dt1.setHours(0,0,0,0);
		
		if (dt1 < now) {
			return true;
		}
		
		return false;
	}

	var documentExpirationIsAlertSuccess_${instanceId} = function(doc) {
		var dt = new Date(doc.expirationDate);
		if ( !date_is_past_${instanceId}(dt) ) {
			var diff_days = date_diff_indays_${instanceId}( new Date(), dt );
			if (diff_days > 30) {
				return true;
			}
		}
		return false;
	};
	
	var documentExpirationIsAlertWarning_${instanceId} = function(doc) {
		var dt = new Date(doc.expirationDate);
		if ( !date_is_past_${instanceId}(dt) ) {
			var diff_days = date_diff_indays_${instanceId}( new Date(), dt );
			if (diff_days <= 30 && diff_days > 10) {
				return true;
			}
		}
		return false;
	};
	
	var formatarData_${instanceId} = function(dt) {
		var month = dt.getMonth() + 1;
		var day = dt.getDay() + 1;
		var year = dt.getFullYear();
		month = !isNaN(month) && parseInt(month, 10) < 10 ? '0' + parseInt(month, 10) : month;
		day = !isNaN(day) && parseInt(day, 10) < 10 ? '0' + parseInt(day, 10) : day;
		return day + "/" + month + "/" + year;
	};	
	
	var documentExpirationIsAlertDanger_${instanceId} = function(doc) {
		var dt = new Date(doc.expirationDate);
		if ( !date_is_past_${instanceId}(dt) ) {
			var diff_days = date_diff_indays_${instanceId}( new Date(), dt );
			if (diff_days <= 10) {
				return true;
			}
		} else {
			return true;
		}
		return false;
	};	

	var findDocumentsById_${instanceId} = function(documentId) {
		var constraints = new Array();
	
		var c1 = DatasetFactory.createConstraint("documentPK.documentId", documentId, documentId, ConstraintType.MUST);
		var c2 = DatasetFactory.createConstraint("activeVersion", "true", "true", ConstraintType.MUST);
		var constraints   = new Array(c1,c2);
				
		var fields = new Array();
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("document", fields, constraints, sortingFields);
	
		return dataset.values;
	};
	
	var findDocumentsByParentId_${instanceId} = function(parentDocumentId) {
		var constraints = new Array();
	
		var c1 = DatasetFactory.createConstraint("parentDocumentId", parentDocumentId, parentDocumentId, ConstraintType.MUST);
		var c2 = DatasetFactory.createConstraint("activeVersion", "true", "true", ConstraintType.MUST);
		var constraints   = new Array(c1,c2);
				
		var fields = new Array();
		var sortingFields = new Array();
				
		var dataset = DatasetFactory.getDataset("document", fields, constraints, sortingFields);
	
		return dataset.values;
	};
	
	var folder_space_${instanceId} = "";
	
	var buildData_${instanceId} = function(data_in) {
		for (var i=0; i<data_in.length > 0; i++) {
		
			data_in[i].key = data_in[i]["documentPK.documentId"];
			data_in[i].title = data_in[i].documentDescription;
			if ( data_in[i].documentType == 1 ) {
				data_in[i].folder = true;
				data_in[i].folderTitle = folder_space_${instanceId} + data_in[i].title;
			} else {
				data_in[i].documentTitle = folder_space_${instanceId} + data_in[i].title;
			}
		
			if ( !containsDocumentInAllData_${instanceId}(data_in[i]) ) {
			
				var isDanger = documentExpirationIsAlertDanger_${instanceId}(data_in[i]);
				var isWarning = documentExpirationIsAlertWarning_${instanceId}(data_in[i]);
				var isSuccess = documentExpirationIsAlertSuccess_${instanceId}(data_in[i]);
			
				data_in[i].isDanger = isDanger;
				data_in[i].isWarning = isWarning;
				data_in[i].isSuccess = isSuccess;
				var isInformation = false;
			
				var strExpirationDate = null;
			
				strExpirationDate = formatarData_${instanceId}( new Date(data_in[i].expirationDate) );
			
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
			
				all_data_${instanceId}.push(data_in[i]);
			}
		
			var children = findChildren_${instanceId}(data_in[i]);
			
			if (children != null && children.length > 0) {
				data_in[i].children = children;
				fillParentInChidren_${instanceId}(children, data_in[i]);
				buildData_${instanceId}(data_in[i].children);
			}
		}
	};
	
	var findChildren_${instanceId} = function(item) {
		var result = findDocumentsByParentId_${instanceId}(item['documentPK.documentId']);
		return result;
	};
	
	var containsDocumentInAllData_${instanceId} = function(doc) {
		for (var i=0; i<all_data_${instanceId}.length; i++) {
			if (all_data_${instanceId}[i]["documentPK.documentId"] == doc["documentPK.documentId"]) {
				return true;
			}
		}
		return false;
	};
	
	var fillParentInChidren_${instanceId} = function(children, parent) {
		if (children != null && children.length > 0) {
			for (var i=0; i<children.length; i++) {
				children[i].parent = parent;
			}
		}
	};
	
	var ordenarDocumentosPorVencimentoDescendente_${instanceId} = function() {
		all_data_${instanceId}.sort(function(a,b){
		  return new Date(b.expirationDate) - new Date(a.expirationDate);
		});
	};
	
	var writeTable_${instanceId} = function(isSuccess, isWarning, isDanger, isInformation) {
	
		//ordenarDocumentosPorVencimentoDescendente();
	
		var str_html_table = '<div class="table">';
		str_html_table += 		'<table id="table_1_${instanceId}" class="table table-responsive table-bordered table-condensed table-striped">';
		
		str_html_table +=				'<thead>';
		str_html_table +=					'<th> <span class="fancytree-icon fluigicon fluigicon-file"> </span> </th>';
		str_html_table +=					'<th>Nome do Documento</th>';
		str_html_table +=					'<th>Data de Vencimento</th>';
		str_html_table +=					'<th>Documento Expira?</th>';
		str_html_table +=				'</thead>';
		
		str_html_table +=		'<tbody>';
		
		for (var i=0; i<all_data_${instanceId}.length; i++) {
		
			var item = all_data_${instanceId}[i];
			
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
					
					str_html_table +=				'<tr>';
					str_html_table +=					'<td class="' + '' + '"> <a title="Ir para a pasta do documento" target="_blank" href="/portal/p/1/ecmnavigation?app_ecm_navigation_folder=' + item["documentPK.documentId"] + '"> <span class="fancytree-icon fluigicon fluigicon-file"> </span> </a> </td>';
					str_html_table +=					'<td class="' + tdStyle + '">'  + item.documentTitle +  ' </td>';
					str_html_table +=					'<td class="' + tdStyle + '">' + item.strExpirationDate +'</td>';
					str_html_table +=					'<td class="' + tdStyle + '">' + item.strDocumentoExpira +'</td>';
					str_html_table +=				'</tr>';				
				}		
				
			}
		}
		
		// /portal/p/1/ecmnavigation?app_ecm_navigation_folder=1456
		
		str_html_table +=		'</tbody>';
		str_html_table +=		'</table>';
		str_html_table +=	'</div>';
			
		$("#container_${instanceId}").html(str_html_table);
		
		$('#table_1_${instanceId}').DataTable({
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
	
	documentos_${instanceId} = findDocumentsById_${instanceId}(633);
	
	buildData_${instanceId}(documentos_${instanceId});
	
	var filtrarDocumentos_${instanceId} = function() {
	
		var doc_no_prazo = $("#doc_no_prazo_${instanceId}").is(':checked');
		var doc_a_expirar = $("#doc_a_expirar_${instanceId}").is(':checked');
		var doc_expirado = $("#doc_expirado_${instanceId}").is(':checked');
		var doc_nao_expira = $("#doc_nao_expira_${instanceId}").is(':checked');
	
		writeTable_${instanceId}(doc_no_prazo, doc_a_expirar, doc_expirado, doc_nao_expira);
	};
	
	writeTable_${instanceId}(true, true, true, false);	
	
</script>