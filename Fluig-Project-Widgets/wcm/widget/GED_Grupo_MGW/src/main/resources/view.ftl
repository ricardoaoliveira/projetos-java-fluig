<div id="GEDGrupoMGW_${instanceId}" class="super-widget wcm-widget-class fluig-style-guide" 
	data-params="GEDGrupoMGW.instance()">

	<div id="container_${instanceId}">
		<div id="myTreeview3_${instanceId}"></div>
	</div>

</div>

<script src="/webdesk/vcXMLRPC.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/portal/resources/style-guide/css/fluig-style-guide-treeview.min.css">
<script src="/portal/resources/style-guide/js/fluig-style-guide-treeview.min.js"></script>

<script type="text/javascript">
	
	var documentos = null;

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
	
	var buildData = function(data_in) {
		for (var i=0; i<data_in.length > 0; i++) {
		
			data_in[i].key = data_in[i]['documentPK.documentId'];
			data_in[i].title = data_in[i].documentDescription;
			
			console.log("documentId: " + data_in[i]['documentPK.documentId']);
			
			var children = findChildren(data_in[i]);
			
			data_in[i].children = children;
			
			buildData(data_in[i].children);
		}
	};
	
	var findChildren = function(item) {
		var result = findDocumentsByParentId(item['documentPK.documentId']);
		return result;
	};
	
	documentos = findDocumentsById(608);
	
	buildData(documentos);
	
	console.log(documentos);
	
	var settings3 = {
		source: documentos,
		multiSelect : true,
		showCheckbox : true,
	};	
	
	var myTreeview = FLUIGC.treeview('#myTreeview3_${instanceId}', settings3);
	
</script>