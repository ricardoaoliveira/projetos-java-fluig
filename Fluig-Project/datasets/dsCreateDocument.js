function createDataset(fields, constraints, sortFields) {

	log.info("#### [Dataset: createDocument] - Iniciando");
	
	var dtResult = DatasetBuilder.newDataset();
	dtResult.addColumn("Resultado");
	
	/*
	 * ============================================== 
	 * == PARÂMETROS QUE PRECISAM SER MODIFICADOS: ==
	 * ==============================================
	 */
	
	
	var ServiceDocumentName = "ECMDocumentService"; // O ServiceDocumentName corresponde ao código do serviço criado no studio. Para este exemplo, crie o serviço como CXF

	var codEmpresa = 1; // Informe o codigo da empresa
	var loginAdm = "admin"; // Usuario integrador - login do usuário administrador 
	var senhaAdm = "Mf4UEuac"; // Usuario integrador - Senha do usuário administrador
	
	var DocumentDescription = "Documento criado via webservice"; // Descricao do documento
	var ParentDocumentId = 40; // Codigo da pasta pai onde o novo documento sera publicado
	var PublisherId = "admin"; // Matricula do usuario publicador
	var ColleagueId = "admin"; // Matricula do usuario criador
	
	
	var fileName = "Nome do documento.txt"; // Nome do arquivo fisico:
	/* 
	 * No fileName eh necessario informar o nome do arquivo fisico disponivel que sera publicado. 
	 * Este precisa estar na pasta de upload do usuário integrador (loginAdm)
	 * Para enviar o documento para a pasta de upload do usuario, veja: http://tdn.totvs.com/x/zABlDw
	 */
	
	try {
		// neste momento, sera instanciado o servidor ECMDocumentService
		var webServiceProvider = ServiceManager.getServiceInstance(ServiceDocumentName);
		var webServiceLocator  = webServiceProvider.instantiate("com.totvs.technology.ecm.dm.ws.ECMDocumentServiceService");
		var webService = webServiceLocator.getDocumentServicePort();
		var documentoArray =  webServiceProvider.instantiate("com.totvs.technology.ecm.dm.ws.DocumentDtoArray"); 
		var documento =  webServiceProvider.instantiate("com.totvs.technology.ecm.dm.ws.DocumentDto");
		
		// agora sera definida as propriedades do documento, a lista completa de propriedade pode ser vista aqui: http://tdn.totvs.com/x/l4eADQ
		documento.setApprovalAndOr(false);
		documento.setAtualizationId(1);
		documento.setColleagueId(ColleagueId);
		documento.setCompanyId(codEmpresa);
		documento.setDeleted(false);
		documento.setDocumentDescription(DocumentDescription);
		documento.setDocumentType("2"); // 1 - Pasta; 2 - Documento; 3 - Documento Externo; 4 - Fichario; 5 - Fichas; 9 - Aplicativo; 10 - Relatorio.
		documento.setDownloadEnabled(true);
		documento.setExpires(false);
		documento.setInheritSecurity(true);
		documento.setParentDocumentId(ParentDocumentId);
		documento.setPrivateDocument(false);
		documento.setPublisherId(PublisherId);
		documento.setUpdateIsoProperties(true);
		documento.setUserNotify(false);
		documento.setVersionOption("0"); 
		documento.setDocumentPropertyNumber(0);
		documento.setDocumentPropertyVersion(0);
		documento.setVolumeId("Default");  
		documento.setIconId(2);
		documento.setLanguageId("pt");
		documento.setIndexed(true);//o default era false
		documento.setActiveVersion(true);
		documento.setTranslated(false);
		documento.setTopicId(1);
		documento.setDocumentTypeId("");
		documento.setExternalDocumentId("");
		documento.setDatasetName("");
		documento.setVersionDescription(""); 
		documento.setKeyWord("");
		documento.setImutable(false);
		documento.setProtectedCopy(false);
		documento.setAccessCount(0);
		documento.setVersion(1000);

	    documentoArray.getItem().add(documento);
		
		// agora sera definida as propriedades do anexo, a lista completa de propriedade pode ser vista aqui: http://tdn.totvs.com/x/l4eADQ
		var attachmentArray = webServiceProvider.instantiate("com.totvs.technology.ecm.dm.ws.AttachmentArray"); 
		var attachment = webServiceProvider.instantiate("com.totvs.technology.ecm.dm.ws.Attachment"); 

		log.info("## [Dataset: createDocument] - Nome e extensão do arquivo físico a ser publicado: " + fileName);
		
		attachment.setFileName(fileName);
		attachment.setPrincipal(true);
		attachment.setFilecontent(null);

		attachmentArray.getItem().add(attachment);
		
		var documentSecurityConfigDtoArray = webServiceProvider.instantiate("com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDtoArray");
		var approverDtoArray = webServiceProvider.instantiate("com.totvs.technology.ecm.dm.ws.ApproverDtoArray"); 
		var relatedDocumentDtoArray = webServiceProvider.instantiate("com.totvs.technology.ecm.dm.ws.RelatedDocumentDtoArray"); 
		
		log.info("## [Dataset: createDocument] - chamando createDocument");
		// agora sera feita a publicacao do documento
		var retornoDocumento = webService.createDocument(loginAdm, senhaAdm, codEmpresa, documentoArray, attachmentArray, documentSecurityConfigDtoArray, approverDtoArray, relatedDocumentDtoArray);
		
		// codigo do documento publicado
		var idDocumento = retornoDocumento.getItem().get(0).getDocumentId();
		
		log.info("## [Dataset: createDocument] - Documento criado com SUCESSO! Código: " + idDocumento);
		dtResult.addRow(["Documento criado com SUCESSO! Código: " + idDocumento]);
		
		
		log.info("#### [Dataset: createDocument] - Finalizado");
		return dtResult;
		
	} catch (e) {
		dtResult.addRow(["Erro " + e.message]);
		log.info("## [Dataset: createDocument] - Erro ao tentar criar documento: " + e.message);
		return dtResult;
	} 
};