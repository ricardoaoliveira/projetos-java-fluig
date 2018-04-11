function validateForm(form){
	
	var indexes = form.getChildrenIndexes("tbreservas");
	var tbreservaData = form.getChildrenFromTable("tbreservas");

	for ( var index = 0; index < indexes.length; index++) {
		if (tbreservaData.get('hr_inicio___' + indexes[index]) == null
			|| tbreservaData.get('hr_inicio___' + indexes[index]).trim().length() == 0) {
			throw "A hora de início deve ser informada";
		}
		if (tbreservaData.get("nm_solicitante___" + indexes[index]) == null
			|| tbreservaData.get('nm_solicitante___' + indexes[index]).trim().length() == 0) {
			throw "O nome do solicitante deve ser informado";
		}
		if (tbreservaData.get("ds_participantes___" + indexes[index]) == null
			|| tbreservaData.get('ds_participantes___' + indexes[index]).trim().length() == 0) {
			throw "O(s) nome(s) do(s) participante(s) devem ser informado(s)";
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