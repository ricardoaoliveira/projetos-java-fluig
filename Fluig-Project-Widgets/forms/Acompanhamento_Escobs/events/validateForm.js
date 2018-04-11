function validateForm(form) {
	
	var indexes = form.getChildrenIndexes("tbescobs");
	var tbreservaData = form.getChildrenFromTable("tbescobs");

	if (form.getValue("nm") == null || form.getValue("nm").trim().length() == 0) {
		throw "O nome do gráfico deve ser informado";
	}
	
	for ( var index = 0; index < indexes.length; index++) {
		if (tbreservaData.get('nm_escob___' + indexes[index]) == null
			|| tbreservaData.get('nm_escob___' + indexes[index]).trim().length() == 0) {
			throw "O nome do Escob deve ser informado";
		}
		if (tbreservaData.get("vl_escob___" + indexes[index]) == null
			|| tbreservaData.get('vl_escob___' + indexes[index]).trim().length() == 0) {
			throw "O valor deve ser informado";
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