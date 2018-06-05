function validateForm(form){

	if (form.getValue("nm") == null || form.getValue("nm") == "" ) {
		throw "O nome deve ser informado";
	} else if (form.getValue("fone") == null || form.getValue("fone") == "" ) {
		throw "O telefone deve ser informado";
	} 
			
}