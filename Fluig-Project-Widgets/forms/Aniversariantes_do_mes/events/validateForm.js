function validateForm(form){
	
	if (form.getValue("titulo") == null || form.getValue("titulo") == "" ) {
		throw "O título deve ser informado";
	} 
	
}