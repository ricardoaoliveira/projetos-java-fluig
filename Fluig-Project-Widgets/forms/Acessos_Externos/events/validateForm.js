function validateForm(form){
	
	if (form.getValue("titulo") == null || form.getValue("titulo") == "" ) {
		throw "A descrição deve ser informada";
	} else if (form.getValue("url") == null || form.getValue("url") == "" ) {
		throw "A URL deve ser informada";
	} 
	
}