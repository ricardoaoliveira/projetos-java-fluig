function validateForm(form){
	
	if (form.getValue("api_key") == null || form.getValue("api_key") == "" ) {
		throw "A chave da API do Google deve ser informada";
		
	} else if (form.getValue("distancia") == null || form.getValue("distancia") == "" ) {
		throw "A distância deve ser informada";
		
	} else if (form.getValue("latitude") == null || form.getValue("latitude") == "" ) {
		throw "A latitude deve ser informada";
		
	} else if (form.getValue("longitude") == null || form.getValue("longitude") == "" ) {
		throw "A longitude deve ser informada";
		
	} else if (form.getValue("min_show") == null || form.getValue("min_show") == "" ) {
		throw "A quantidade mínima de exibição de restaurantes deve ser informada";
	} 
	
}