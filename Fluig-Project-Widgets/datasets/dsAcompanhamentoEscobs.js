function defineStructure() {

}

function onSync(lastSyncDate) {

}

function createDataset(fields, constraints, sortFields) {
	//Cria as colunas
    var dataset = DatasetBuilder.newDataset();
    dataset.addColumn("id");
    dataset.addColumn("ds_descriptor");
    dataset.addColumn("nm");
    dataset.addColumn("titulo");
    dataset.addColumn("nm_escob");
    dataset.addColumn("vl_escob");
     
    //Cria a constraint para buscar os formulários ativos
    var cst = DatasetFactory.createConstraint("metadata#active", true, true, ConstraintType.MUST);
    var constraints = new Array(cst);
     
    var datasetPrincipal = DatasetFactory.getDataset("ds_acompanhamento_escobs", null, constraints, null);
     
    for (var i = 0; i < datasetPrincipal.rowsCount; i++) {
        var documentId = datasetPrincipal.getValue(i, "metadata#id");
        var documentVersion = datasetPrincipal.getValue(i, "metadata#version");
        var ds_descriptor = datasetPrincipal.getValue(i, "ds_descriptor");
        var nm = datasetPrincipal.getValue(i, "nm");
        var titulo = datasetPrincipal.getValue(i, "titulo");        
         
        //Cria as constraints para buscar os campos filhos, passando o tablename, número da formulário e versão
        var c1 = DatasetFactory.createConstraint("tablename", "tbescobs" ,"tbescobs", ConstraintType.MUST);
        var c2 = DatasetFactory.createConstraint("metadata#id", documentId, documentId, ConstraintType.MUST);
        var c3 = DatasetFactory.createConstraint("metadata#version", documentVersion, documentVersion, ConstraintType.MUST);
        var constraintsFilhos = new Array(c1, c2, c3);
 
        //Busca o dataset
        var datasetFilhos = DatasetFactory.getDataset("ds_acompanhamento_escobs", null, constraintsFilhos, null);
 
        for (var j = 0; j < datasetFilhos.rowsCount; j++) {
            //Adiciona os valores nas colunas respectivamente.
            dataset.addRow(new Array(
                    documentId,
                    ds_descriptor,
                    nm,
                    titulo,
                    datasetFilhos.getValue(j, "nm_escob"), 
                    datasetFilhos.getValue(j, "vl_escob")));
        }
    }
     
    return dataset;
}

function onMobileSync(user) {

}