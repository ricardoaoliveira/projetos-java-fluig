function validateForm(form){
	if (form.getValue("cod_doc") == null || form.getValue("cod_doc") == "" ) {
		throw "O código do documento deve ser informado";
	} 
}