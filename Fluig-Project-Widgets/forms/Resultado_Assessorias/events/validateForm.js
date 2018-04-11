function validateForm(form) {

	if (form.getValue("nm") == null || form.getValue("nm").trim().length() == 0) {
		throw "O nome do gráfico deve ser informado";
	}

	validateTableEscobs(form);
	
	//validateTableCores(form);
}

function validateTableEscobs(form) {
	var indexes = form.getChildrenIndexes("tbescobs");
	var tbData = form.getChildrenFromTable("tbescobs");
	
	for ( var index = 0; index < indexes.length; index++) {
		if (tbData.get('nm_escob___' + indexes[index]) == null
			|| tbData.get('nm_escob___' + indexes[index]).trim().length() == 0) {
			throw "O nome do estabelecimento deve ser informado";
		}
		if (tbData.get("vl___" + indexes[index]) == null
			|| tbData.get('vl___' + indexes[index]).trim().length() == 0) {
			throw "O valor deve ser informado";
		}
	}
}

function validateTableCores(form) {
	var indexes = form.getChildrenIndexes("tbcores");
	var tbData = form.getChildrenFromTable("tbcores");
	
	for ( var index = 0; index < indexes.length; index++) {
		if (tbData.get('cor___' + indexes[index]) == null
			|| tbData.get('cor___' + indexes[index]).trim().length() == 0) {
			throw "A cor deve ser informada";
		}
		if (tbData.get("vl_inicial___" + indexes[index]) == null
			|| tbData.get('vl_inicial___' + indexes[index]).trim().length() == 0) {
			throw "O valor inicial deve ser informado";
		}
		if (tbData.get("vl_final___" + indexes[index]) == null
			|| tbData.get('vl_final___' + indexes[index]).trim().length() == 0) {
			throw "O valor final deve ser informado";
		}
	}
}