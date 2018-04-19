function defineStructure() {

}

function onSync(lastSyncDate) {

}

function createDataset(fields, constraints, sortFields) {
	//Cria as colunas
    var dataset = DatasetBuilder.newDataset();
    dataset.addColumn("id");
    dataset.addColumn("ds_descriptor");
    dataset.addColumn("dt_event");
    dataset.addColumn("nm_dia_semana");
    dataset.addColumn("nm_evento");
    dataset.addColumn("hr_inicio");
    dataset.addColumn("hr_termino");
     
    //Cria a constraint para buscar os formulários ativos
    var cst = DatasetFactory.createConstraint("metadata#active", true, true, ConstraintType.MUST);
    var constraints = new Array(cst);
     
    var datasetPrincipal = DatasetFactory.getDataset("ds_calendario", null, constraints, null);
     
    for (var i = 0; i < datasetPrincipal.rowsCount; i++) {
        var documentId = datasetPrincipal.getValue(i, "metadata#id");
        var documentVersion = datasetPrincipal.getValue(i, "metadata#version");
        var ds_descriptor = datasetPrincipal.getValue(i, "ds_descriptor");
        var dt_event = datasetPrincipal.getValue(i, "dt_event");
        var nm_dia_semana = datasetPrincipal.getValue(i, "nm_dia_semana");
                 
        //Cria as constraints para buscar os campos filhos, passando o tablename, número da formulário e versão
        var c1 = DatasetFactory.createConstraint("tablename", "tbeventos" ,"tbeventos", ConstraintType.MUST);
        var c2 = DatasetFactory.createConstraint("metadata#id", documentId, documentId, ConstraintType.MUST);
        var c3 = DatasetFactory.createConstraint("metadata#version", documentVersion, documentVersion, ConstraintType.MUST);
        var constraintsFilhos = new Array(c1, c2, c3);
 
        //Busca o dataset
        var datasetFilhos = DatasetFactory.getDataset("ds_calendario", null, constraintsFilhos, null);
 
        for (var j = 0; j < datasetFilhos.rowsCount; j++) {
            //Adiciona os valores nas colunas respectivamente.
            dataset.addRow(new Array(
                    documentId,
                    ds_descriptor,
                    dt_event,
                    nm_dia_semana,
                    datasetFilhos.getValue(j, "nm_evento"),
                    datasetFilhos.getValue(j, "hr_inicio"), 
                    datasetFilhos.getValue(j, "hr_termino")));
        }
    }
     
    return dataset;
}

function onMobileSync(user) {

}