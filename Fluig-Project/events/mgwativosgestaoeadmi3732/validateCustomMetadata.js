function validateCustomMetadata (fields){ 
	
	var numeroPastaPai = getValue("WKNumParentDocument");
	
	if (numeroPastaPai == 340) {
		fields.setValue("Origem da Informação", "Outros");
	}
	
	//Exemplo implementação
	/*if( fields.getValue("campo1") == “Area 1�?){ 
		throw "TRATAMENTO DA EXCEÇÃO"; 
	}*/
} 