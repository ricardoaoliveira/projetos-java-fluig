function validateForm(form) {

	if (form.getValue("nm") == null || form.getValue("nm").trim().length() == 0) {
		throw "O nome do gráfico deve ser informado";
	}

	validateTablePeriodos(form);
	
	//validateTableCores(form);
}

function validateTablePeriodos(form) {
	var indexes = form.getChildrenIndexes("tbperiodos");
	var tbData = form.getChildrenFromTable("tbperiodos");
	
	for ( var index = 0; index < indexes.length; index++) {
		if (tbData.get('mes_ano___' + indexes[index]) == null
			|| tbData.get('mes_ano___' + indexes[index]).trim().length() == 0) {
			throw "O mês/ano deve ser informado";
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