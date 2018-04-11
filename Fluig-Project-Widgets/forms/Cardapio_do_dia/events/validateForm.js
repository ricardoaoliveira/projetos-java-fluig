function validateForm(form){
	
	if (form.getValue("nm") == null || form.getValue("nm") == "" ) {
		throw "O nome do estabelecimento deve ser informado";
	}
	
}