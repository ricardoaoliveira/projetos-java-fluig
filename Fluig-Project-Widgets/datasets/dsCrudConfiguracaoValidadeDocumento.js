function createDataset(fields, constraints, sortFields)
{
	var dataset = DatasetBuilder.newDataset();
	dataset.addColumn("RETORNO");
	dataset.addColumn("cod_doc");
	dataset.addColumn("expira");
	dataset.addColumn("validade");
	dataset.addColumn("ds_descriptor");
	dataset.addColumn("METODO");
	dataset.addColumn("id");
	
	var cod_doc = null;
    var expira = null;
    var validade = null;
    var ds_descriptor = null;
    var metodo = null;
    var id = null;
    
    if (constraints != null) {
        for (var i = 0; i < constraints.length; i++) {
            if (constraints[i].fieldName == "cod_doc") { 
            	cod_doc = constraints[i].initialValue; 
            } else if (constraints[i].fieldName == "expira") {
            	expira = constraints[i].initialValue; 
            } else if (constraints[i].fieldName == "validade") {
            	validade = constraints[i].initialValue; 
            } else if (constraints[i].fieldName == "ds_descriptor") {
            	ds_descriptor = constraints[i].initialValue; 
            } else if (constraints[i].fieldName == "METODO") {
            	metodo = constraints[i].initialValue; 
            } else if (constraints[i].fieldName == "id") {
            	id = parseInt( constraints[i].initialValue ); 
            }
        }
    }
	
    //DEFINICAO DAS VARIAVEIS PARA A CHAMADA DA FUNCAO createData()
	var company = parseInt(getValue("WKCompany"));
	var user = "admin";
	var password = "Mf4UEuac";
	
	//CHAMADA DA FUNCAO PARA A CRIACAO DOS REGISTROS DE FORMULARIO
	var retorno = null;
	if (metodo == "INSERT") {
		retorno = createData(company, user, password, cod_doc, expira, validade, ds_descriptor);	
	} else if (metodo == "DELETE") {
		retorno = deleteData(company, user, password, id);
	} else if (metodo == "UPDATE") {
		retorno = updateData(company, user, password, cod_doc, expira, validade, ds_descriptor, id);
	}
	
	dataset.addRow([retorno]);
	
	return dataset;
}

function createData(company, user, password, cod_doc, expira, validade, ds_descriptor)
{
	try
	{
	
		var properties = {};
		properties["disable.chunking"] = "true";
		properties["log.soap.messages"] = "true";
		
		//CHAMADA DO SERVICO E INSTANCIAÇAO DAS CLASSES PARA A CHAMADA DO METODO	
		var serviceManager = ServiceManager.getService("WSCardService");
		var serviceInstance = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.ECMCardServiceService");
		var service = serviceInstance.getCardServicePort();	    		    
		var customClient = serviceManager.getCustomClient(service, "com.totvs.technology.ecm.dm.ws.CardService", properties);
		
		var attachment = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.Attachment");
		var relatedDocument = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.RelatedDocumentDto");
		var documentSecurity = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto");
		var approver = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.ApproverDto");
		
		var cardDtoArray = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardDtoArray");
		var cardDto = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardDto");		
		
		var cardFieldDto1 = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDto");
		var cardFieldDto2 = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDto");
		var cardFieldDto3 = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDto");
		var cardFieldDto4 = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDto");
		
		cardDto.getAttachs().add(attachment);
		cardDto.getReldocs().add(relatedDocument);
		cardDto.getDocsecurity().add(documentSecurity);
		cardDto.getDocapprovers().add(approver);
		
		//ADICIONA NO ARRAY OS METADADOS DO REGISTRO DE FORMULARIO 
		cardDto.setDocumentDescription("");
		cardDto.setAdditionalComments("");
		cardDto.setParentDocumentId(1849);
		cardDto.setColleagueId("admin");
		cardDto.setExpires(false);
		cardDto.setUserNotify(false);
		cardDto.setInheritSecurity(true);
		cardDto.setTopicId(1);
		cardDto.setVersionDescription("");
		cardDto.setDocumentKeyWord("");
		
		//ADICIONA NO ARRAY OS DADOS DOS CAMPOS DO FORMULARIO: NOME E O VALOR	
		cardFieldDto1.setField("cod_doc");
		cardFieldDto1.setValue(cod_doc);
		cardDto.getCardData().add(cardFieldDto1);
		
		cardFieldDto2.setField("expira");
		cardFieldDto2.setValue(expira);
		cardDto.getCardData().add(cardFieldDto2);
		
		cardFieldDto3.setField("validade");
		cardFieldDto3.setValue(validade);
		cardDto.getCardData().add(cardFieldDto3);
		
		cardFieldDto4.setField("ds_descriptor");
		cardFieldDto4.setValue(ds_descriptor);
		cardDto.getCardData().add(cardFieldDto4);
		
		// ADICIONA O REGISTRO NO ARRAY DO REGISTRO DE FORMULARIO
		cardDtoArray.getItem().add(cardDto);
		
		//CHAMADA METODO PARA CRIACAO DOS REGISTROS DE FORMULARIO
		result = customClient.create(company, user, password, cardDtoArray);
		log.info("###### CONFIGURACAO VALIDADE DOCUMENTO INSERIDO!");
		
		if (result.getItem().get(0).getWebServiceMessage().equals("ok")) {
			return "Inserção completada com sucesso!" ;
		} else {
			return result.getItem().get(0).getWebServiceMessage();
		}
	}
	catch(e)
	{
		log.error('###### Erro ao Inserir Configuracao Validade Documento. '+e.message);
		return;
	}
}

function updateData(company, user, password, cod_doc, expira, validade, ds_descriptor, id)
{
	try
	{
	
		var properties = {};
		properties["disable.chunking"] = "true";
		properties["log.soap.messages"] = "true";
		
		//CHAMADA DO SERVICO E INSTANCIAÇAO DAS CLASSES PARA A CHAMADA DO METODO	
		var serviceManager = ServiceManager.getService("WSCardService");
		var serviceInstance = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.ECMCardServiceService");
		var service = serviceInstance.getCardServicePort();	    		    
		var customClient = serviceManager.getCustomClient(service, "com.totvs.technology.ecm.dm.ws.CardService", properties);
		
		var attachment = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.Attachment");
		var relatedDocument = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.RelatedDocumentDto");
		var documentSecurity = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto");
		var approver = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.ApproverDto");
		
		var cardDtoArray = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDtoArray");
		
		var cardFieldDto1 = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDto");
		var cardFieldDto2 = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDto");
		var cardFieldDto3 = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDto");
		var cardFieldDto4 = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.CardFieldDto");
		
		//ADICIONA NO ARRAY OS DADOS DOS CAMPOS DO FORMULARIO: NOME E O VALOR	
		cardFieldDto1.setField("cod_doc");
		cardFieldDto1.setValue(cod_doc);
		cardDtoArray.getItem().add(cardFieldDto1);
		
		cardFieldDto2.setField("expira");
		cardFieldDto2.setValue(expira);
		cardDtoArray.getItem().add(cardFieldDto2);
		
		cardFieldDto3.setField("validade");
		cardFieldDto3.setValue(validade);
		cardDtoArray.getItem().add(cardFieldDto3);
		
		cardFieldDto4.setField("ds_descriptor");
		cardFieldDto4.setValue(ds_descriptor);
		cardDtoArray.getItem().add(cardFieldDto4);
		
		//CHAMADA METODO PARA CRIACAO DOS REGISTROS DE FORMULARIO
		result = customClient.updateCardData(company, user, password, id, cardDtoArray);
		log.info("###### CONFIGURACAO VALIDADE DOCUMENTO ATUALIZADO!");
		
		if (result.getItem().get(0).getWebServiceMessage().equals("ok")) {
			return "Alteração completada com sucesso!" ;
		} else {
			return result.getItem().get(0).getWebServiceMessage();
		}
	}
	catch(e)
	{
		log.error('###### Erro ao Alterar Configuracao Validade Documento. '+e.message);
		return;
	}
}

function deleteData(company, user, password, id)
{
	try
	{
	
		var properties = {};
		properties["disable.chunking"] = "true";
		properties["log.soap.messages"] = "true";
		
		//CHAMADA DO SERVICO E INSTANCIAÇAO DAS CLASSES PARA A CHAMADA DO METODO	
		var serviceManager = ServiceManager.getService("WSCardService");
		var serviceInstance = serviceManager.instantiate("com.totvs.technology.ecm.dm.ws.ECMCardServiceService");
		var service = serviceInstance.getCardServicePort();	    		    
		var customClient = serviceManager.getCustomClient(service, "com.totvs.technology.ecm.dm.ws.CardService", properties);
		
		//CHAMADA METODO PARA CRIACAO DOS REGISTROS DE FORMULARIO
		result = customClient.deleteCard(company, user, password, id);
		log.info("###### CONFIGURACAO VALIDADE DOCUMENTO EXCLUIDO!");
		
		if (result.getItem().get(0).getWebServiceMessage().equals("ok")) {
			return "Exclusão completada com sucesso!" ;
		} else {
			return result.getItem().get(0).getWebServiceMessage();
		}
	}
	catch(e)
	{
		log.error('###### Erro ao excluir Configuracao Validade Documento. '+e.message);
		return;
	}
}