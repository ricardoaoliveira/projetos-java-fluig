var initializeArvoreGED = function() {

	$('#data').jstree({
		
		 "plugins": ["contextmenu"],
	        "contextmenu": {
	            "items": function ($node) {
	                return {
	                    "create": {
	                        "separator_before": false,
	                        "separator_after": false,
	                        "label": "Abrir",
	                        "action": function (obj) {
                        		try {
                        			var folderRaiz = 608;
                        			if (window.location.href.indexOf("3732") == -1) {
                        				folderRaiz = 175;
                        			}                        			
    	                        	window.location.href = "/portal/p/1/ecmnavigation?app_ecm_navigation_folder=" + folderRaiz;	 
                        		} catch(err) {
                        			console.log(err);
                        		}		
	                        }
	                    }
	                };
	            }
	        },
		
		'core' : {
			'data' : [
			
				{ "text" : "GRUPO", "state" : { "opened" : true },
					"children" : [
					
							{ "text" : "01 - MAIS CREDIT", "state" : { "opened" : true },
								"children" : [
								
									{ "text" : "01 - BID", "state" : { "opened" : true },
										"children" : [
											{ "text" : "CASAS BAHIA", "state" : { "opened" : false },
												"children" : [
													{ "text" : "01 - CARTA CONVITE", "state" : { "opened" : true },
														"children" : [
														
														]
													},
													{ "text" : "02 - NDA", "state" : { "opened" : true },
														"children" : [
														
														]												
													},
													{ "text" : "03 - PROPOSTA COMERCIAL", "state" : { "opened" : true },
														"children" : [
														
														]
													},
													{ "text" : "04 - DOCUMENTOS", "state" : { "opened" : true },
														"children" : [
														
														]
													},
												]
											},
											{ "text" : "SPANI", "state" : { "opened" : false },
												"children" : [
													{ "text" : "01 - CARTA CONVITE", "state" : { "opened" : true },
														"children" : [
														
														]
													},
													{ "text" : "02 - NDA", "state" : { "opened" : true },
														"children" : [
														
														]
													},
													{ "text" : "03 - PROPOSTA COMERCIAL", "state" : { "opened" : true },
														"children" : [
														
														]
													},
													{ "text" : "04 - DOCUMENTOS", "state" : { "opened" : true },
														"children" : [
														
														]
													},
												]
											}
										]
									}, 									
									
									{ "text" : "02 - DOCUMENTOS GERAIS", "state" : { "opened" : false },
										"children" : [
											{ "text" : "01 - INSCRIÇÕES", "state" : { "opened" : true },
												"children" : [
														
												]
											},
											{ "text" : "02 - CONTRATO SOCIAL", "state" : { "opened" : true }, 
												"children" : [
														
												]
											},
											{ "text" : "03 - DADOS CADASTRAIS", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											},
											{ "text" : "04 - DOCUMENTOS DOS REPRESENTANTES LEGAIS E OU SÓCIOS", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											},
											{ "text" : "05 - PROCURAÇÕES", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											}, 
											{ "text" : "06 - CERTIDÕES", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											}, 
											{ "text" : "07 - COMPROVANTE", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											}, 
											{ "text" : "08 - DECLARAÇÃO", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											}, 
											{ "text" : "09 - IR - IMPOSTO DE RENDA", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											}, 
											{ "text" : "10 - BALANÇO E DRE - DEMONSTRAÇÃO DE RESULTADO DO EXERCÍCIO ", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											},
											{ "text" : "11 - INVESTIDORES EXTERNOS ", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											},
											{ "text" : "12 - CERTIFICADO ", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											},
											{ "text" : "13 - ATESTADO ", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											}
										]
									},
									{ "text" : "03 - CONTRATO DE CESSÃO DE CRÉDITOS", "state" : { "opened" : true },
										"children" : [
											{ "text" : "SPANI", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											}
										]
									}
								]								
							},
							
							{ "text" : "02 - MGW ATIVOS", "state" : { "opened" : true }, 
								"children" : [
									{ "text" : "CARTEIRAS", "state" : { "opened": true },
										"children" : [
											{ "text" : "SCP BANIF COBALTO", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											},
											{ "text" : "SCP LOSANGO", "state" : { "opened" : true }, 
												"children" : [
														
												]											
											}
										]										
									},
									{ "text" : "INFORMAÇÕES GERAIS", "state" : { "opened": true },
										"children" : [
														
										]											
									}
								]
							},							
							
							{ "text" : "03 - MAS", "state" : { "opened" : true }, 
								"children" : [
														
								]											
							}
					]
				}
			]
		}
	});	
	
};
