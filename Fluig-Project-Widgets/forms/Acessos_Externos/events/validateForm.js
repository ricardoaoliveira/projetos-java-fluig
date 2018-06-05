function validateForm(form){
	
	if (form.getValue("titulo") == null || form.getValue("titulo") == "" ) {
		throw "O nome deve ser informado";
	} else if (form.getValue("url") == null || form.getValue("url") == "" ) {
		throw "A URL deve ser informada";
	} 
	
}