function validateForm(form){
	
	if (form.getValue("titulo") == null || form.getValue("titulo") == "" ) {
		throw "O Título deve ser informado";
	} 
	if (form.getValue("url") == null || form.getValue("url") == "" ) {
		throw "A URL deve ser informada";
	}
	
}