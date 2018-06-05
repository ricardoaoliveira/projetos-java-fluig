function validateForm(form){
	
	var indexes = form.getChildrenIndexes("tbeventos");
	var tbeventos = form.getChildrenFromTable("tbeventos");

	for ( var index = 0; index < indexes.length; index++) {
		if (tbeventos.get('nm_evento___' + indexes[index]) == null
			|| tbeventos.get('nm_evento___' + indexes[index]).trim().length() == 0) {
			throw "O nome do evento deve ser informado";
		}		
	}
	
}

function isInsertMode(form,customHTML) {
	return form.getFormMode() == "ADD";
}

function isEditMode(form,customHTML) {
	return form.getFormMode() == "MOD";
}

function isViewMode(form,customHTML) {
	return form.getFormMode() == "VIEW";
}