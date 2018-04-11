var filtro = new Object();
filtro["name"] = "";

var dataset = getDatasetValues("colleague", filtro);
if(dataset.getRowsCount() > 0) {
	for (var i=0; i<dataset.getRowsCount(); i++) {
		console.log(dataset.values[i]['name']);
	}
}