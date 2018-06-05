function validateForm(form){
	
	if (form.getValue("titulo") == null || form.getValue("titulo") == "" ) {
		throw "A descrição deve ser informada";
	} else if (form.getValue("codigo") == null || form.getValue("codigo") == "" ) {
		throw "O código da Widget deve ser informada";
	} 
	
}