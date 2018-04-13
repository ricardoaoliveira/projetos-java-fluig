package samples;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import net.java.dev.jaxb.array.StringArray;

	
import com.totvs.technology.ecm.dm.ws.ApproverDto;
import com.totvs.technology.ecm.dm.ws.ApproverDtoArray;
import com.totvs.technology.ecm.dm.ws.Attachment;
import com.totvs.technology.ecm.dm.ws.AttachmentArray;
import com.totvs.technology.ecm.dm.ws.CardDto;
import com.totvs.technology.ecm.dm.ws.CardDtoArray;
import com.totvs.technology.ecm.dm.ws.CardFieldDto;
import com.totvs.technology.ecm.dm.ws.CardFieldDtoArray;
import com.totvs.technology.ecm.dm.ws.CardService;
import com.totvs.technology.ecm.dm.ws.ECMCardServiceService;
import com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto;
import com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDtoArray;
import com.totvs.technology.ecm.dm.ws.RelatedDocumentDto;
import com.totvs.technology.ecm.dm.ws.RelatedDocumentDtoArray;
import com.totvs.technology.ecm.dm.ws.WebServiceMessageArray;

/**
 * Classe que utiliza todos os métodos da CardService.
 * Com essa classe, pode-se criar, alterar, excluir e pesquisar fichas, além de realizar outras atividades relacionadas a fichas.
 * No método setParameters, pode-se setar algumas das variáveis que são mais utilizadas como parâmetros nos métodos desta classe.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class CardServiceClient {

	// Variáveis.
	String loginColaborador, senhaColaborador, matriculaColaborador, matriculaAprovador, matriculaColaboradorSeguranca, publicadorFicha, descricaoFicha, arquivo, nomeArquivo;
	int codigoEmpresa, numeroFicha, versaoFicha, numeroDocumentoPai, numeroDocumentoRelacionado;
	byte[] arrayBytes = null;
	HashMap<String, String> values = new HashMap<String, String>();
	
	// Variáveis de data.
	XMLGregorianCalendar expirationDate, validationStartDate;
	
	// Dto's.
	Attachment attachment = new Attachment();
	DocumentSecurityConfigDto documentSecurityConfigDto = new DocumentSecurityConfigDto();
	ApproverDto approverDto = new ApproverDto();
	RelatedDocumentDto relatedDocumentDto = new RelatedDocumentDto();
	CardDto cardDto = new CardDto();
	
	// Array's
	AttachmentArray attachmentArray = new AttachmentArray();
	DocumentSecurityConfigDtoArray documentSecurityConfigDtoArray = new DocumentSecurityConfigDtoArray();
	ApproverDtoArray approverDtoArray = new ApproverDtoArray();
	RelatedDocumentDtoArray relatedDocumentDtoArray = new RelatedDocumentDtoArray();
	WebServiceMessageArray webServiceMessageArray = new WebServiceMessageArray();
	CardDtoArray cardDtoArray = new CardDtoArray();
	CardFieldDtoArray cardFieldDtoArray = new CardFieldDtoArray();
	
	// Instancia CardServiceService.
	ECMCardServiceService cardService = new ECMCardServiceService();
	CardService service = cardService.getCardServicePort();
	
	// Inicia execução da classe.
	public static void main (String args[]) {
		System.out.println("\nClasse CardService");
		
		// Instancia classe CardServiceClient.
		CardServiceClient csc = new CardServiceClient();
		
		// Configura acesso ao WebServices.
		BindingProvider bp = (BindingProvider) csc.service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://mgwativosgestaoeadmi3732.fluig.cloudtotvs.com.br:80/webdesk/CardService");
		
		try {
			// Chama método que configura os valores das variáveis.
			csc.setParameters();

			// Chama método que é responsável por executar os métodos da classe.
			csc.changeMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configura parâmetros.
	 * Nesse método, pode-se setar algumas das variáveis que são mais utilizadas como parâmetros nos métodos desta classe.
	 */
	public void setParameters() throws Exception {
		this.loginColaborador = "admin";
		this.senhaColaborador = "Mf4UEuac";
		this.matriculaColaborador = "admin";
		this.matriculaAprovador = "admin";
		this.matriculaColaboradorSeguranca = "admin";
		this.codigoEmpresa = 1;
		this.numeroFicha = 1061;
		this.versaoFicha = 1000;
		this.descricaoFicha = "Ficha do ECM";
		this.publicadorFicha = "admin";
		this.numeroDocumentoRelacionado = 44056;
		this.numeroDocumentoPai = 44052;
		this.arquivo = "C:\\DOC.doc";
		this.nomeArquivo = "DOC.doc";
		
		// Cria data de expiração da ficha.
		this.expirationDate = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		this.expirationDate.setYear(2010);
		this.expirationDate.setMonth(04);
		this.expirationDate.setDay(30);
		this.expirationDate.setHour(0);
		this.expirationDate.setMinute(0);
		this.expirationDate.setSecond(0);

		// Cria data de validação inicial da ficha.
		this.validationStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		this.validationStartDate.setYear(2010);
		this.validationStartDate.setMonth(04);
		this.validationStartDate.setDay(27);
		this.validationStartDate.setHour(0);
		this.validationStartDate.setMinute(0);
		this.validationStartDate.setSecond(0);
	}
	
	/**
	 * Escolhe método.
	 * Nesse método, pode-se escolher qual método da classe será executado.
	 */
	public void changeMethod() throws Exception {
		// Chama método updateCardData.
		//this.updateCardData();

		// Chama método create.
		//this.create();
		
		// Chama método deleteCard.
		//this.deleteCard();
		
		// Chama método updateCard.
		//this.updateCard();
		
		// Chama método getCardVersion.
		this.getCardVersion();
		
		//this.getCardIndexesWithoutApprover();
	}
	
	/**
	 * Retorna o arquivo que será anexado no documento.
	 */
	public File getArchive() {
		// Cria arquivo.
		File file = new File(this.arquivo);
		
		if(file.exists()){
			try{
				byte[] buffer = new byte[8192];
		        FileInputStream fis = new FileInputStream(file);
		        BufferedInputStream bis = new BufferedInputStream(fis, 8192);
		
		        ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.length());
		        int len = 0;
		        while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
		            baos.write(buffer, 0, len);
		        }
				
		        this.arrayBytes = baos.toByteArray();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("Arquivo " + this.arquivo + " não encontrado.");
			return null;
		}
		
		return file;
	}
	
	/**
	 * Altera os campos de uma ficha.
	 * 
	 * Método: updateCardData.
	 * 
	 * Parâmetros:
	 * - Código da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Número da ficha;
	 * - Array de campos da ficha.
	 */
	public void updateCardData() throws Exception {
		System.out.println("\nMétodo updateCardData\n");
		
		// Verifica se ficha existe.
		this.cardDtoArray = service.getCardVersion(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador, this.numeroFicha, this.versaoFicha, this.matriculaColaborador);

		// Se existe...
		if (this.cardDtoArray != null) {
			
			// Limpa campo da ficha.
			this.cardDtoArray.getItem().get(0).getCardData().clear();
			
			// Seta campos da ficha.
			this.values.put("Titulo","Titulo");
			this.values.put("Data","27/05/2011");
			this.values.put("Escopo","Escopo");
			this.values.put("Objetivo","Objetivo");
			this.values.put("Responsavel","Responsavel");
			
			// Array para armazenar campos da ficha.
			StringArray campos = new StringArray();
			campos.getItem().addAll(this.values.keySet());
			
			// Array para armazenar valores da ficha.
			StringArray valores = new StringArray();
			valores.getItem().addAll(this.values.values());

			for (int i = 0; i < this.values.size(); i++) {
				
				CardFieldDto cardField = new CardFieldDto();
				cardField.setField(campos.getItem().get(i));
				cardField.setValue(valores.getItem().get(i));
				
				// Armazena campos na ficha.
				this.cardFieldDtoArray.getItem().add(i, cardField);
			}

			// Altera os campos de uma ficha.
			this.webServiceMessageArray = service.updateCardData(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador, this.numeroFicha, this.cardFieldDtoArray);
			
			// Mostra resultado.
			if (this.webServiceMessageArray.getItem().get(0).getWebServiceMessage().equals("ok")) {
				System.out.println("Ficha " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi alterada!");
			} else {
				System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
			}
		} else {
			System.out.println("Ficha " + this.numeroFicha + " com versão " + this.versaoFicha + " não foi encontrada!");
		}
	}
	
	/**
	 * Cria uma ficha.
	 * 
	 * Método: create.
	 * 
	 * Parâmetros:
	 * - Código da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Array de fichas.
	 */
	public void create() throws Exception {
		System.out.println("\nMétodo create\n");

		// Cria ficha.
		this.cardDto.setDocumentDescription(this.descricaoFicha);
		this.cardDto.setAdditionalComments("Ficha criada por Webservices");
		this.cardDto.setParentDocumentId(this.numeroDocumentoPai);
		this.cardDto.setColleagueId(this.publicadorFicha);
		this.cardDto.setValidationStartDate(this.validationStartDate);
		this.cardDto.setExpires(false);
		this.cardDto.setExpirationDate(this.expirationDate);
		this.cardDto.setUserNotify(false);
		this.cardDto.setInheritSecurity(true);
		this.cardDto.setTopicId(1);
		this.cardDto.setVersionDescription("");
		this.cardDto.setDocumentKeyWord("");

		// Cria anexo do documento.
		this.attachment.setFileName(this.getArchive().getName());
		this.attachment.setPrincipal(true);
		this.attachment.setFilecontent(this.arrayBytes);
		
		// Adiciona anexo na ficha.
		this.cardDto.getAttachs().add(this.attachment);
		
		// Recupera a seguranca da ficha. 
		List<DocumentSecurityConfigDto> ll = this.cardDto.getDocsecurity();
		for (DocumentSecurityConfigDto dsc : ll) {
			this.documentSecurityConfigDtoArray.getItem().add(dsc);
		}

		// Cria segurança da ficha.
		this.documentSecurityConfigDto.setAttributionValue(this.matriculaColaboradorSeguranca);
		this.documentSecurityConfigDto.setAttributionType(1); // 1 - Colaborador; 2 - Grupos; 3 - Todos.
		this.documentSecurityConfigDto.setSecurityLevel(3); // 0 - Leitura; 1 - Gravação; 2 - Modificação; 3 - Total.
		this.documentSecurityConfigDto.setPermission(true);
		this.documentSecurityConfigDto.setSecurityVersion(false);
		this.documentSecurityConfigDto.setShowContent(true);

		// Adiciona segurança na ficha.
		this.cardDto.getDocsecurity().add(this.documentSecurityConfigDto);
		
		// Cria aprovador da ficha.
		this.approverDto.setColleagueId(this.matriculaAprovador);
		this.approverDto.setApproverType(0); // 0 - Colaborador; 1 - Grupo de usuários.
		this.approverDto.setCompanyId(1);
		
		// Adiciona aprovador na ficha.
		this.cardDto.getDocapprovers().add(this.approverDto);
		
		// Cria documento relacionado.
		this.relatedDocumentDto.setRelatedDocumentId(this.numeroDocumentoRelacionado);
		this.relatedDocumentDto.setCompanyId(1);
		
		// Adiciona documento relacionado na ficha.
		this.cardDto.getReldocs().add(this.relatedDocumentDto);
		
		// Seta campos da ficha.
		this.values.put("Titulo","Titulo 1");
		this.values.put("Data","26/05/2011");
		this.values.put("Escopo","Escopo 1");
		this.values.put("Objetivo","Objetivo 1");
		this.values.put("Responsavel","Responsavel 1");
		
		// Array para armazenar campos da ficha.
		StringArray campos = new StringArray();
		campos.getItem().addAll(this.values.keySet());
		
		// Array para armazenar valores da ficha.
		StringArray valores = new StringArray();
		valores.getItem().addAll(this.values.values());

		for (int i = 0; i < this.values.size(); i++) {
			
			CardFieldDto cardField = new CardFieldDto();
			cardField.setField(campos.getItem().get(i));
			cardField.setValue(valores.getItem().get(i));
			
			// Armazena campos na ficha.
			this.cardDto.getCardData().add(i, cardField);
		}
		
		// Adiciona ficha no array de fichas.
		this.cardDtoArray.getItem().add(this.cardDto);

		// Cria ficha.
		this.webServiceMessageArray = service.create(this.codigoEmpresa, this.matriculaColaborador, this.senhaColaborador, this.cardDtoArray);

		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getWebServiceMessage().equals("ok")) {
			System.out.println("Ficha " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi publicada!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}
	
	/**
	 * Exclui uma ficha e envia para a lixeira.
	 * 
	 * Método: deleteCard.
	 * 
	 * Parâmetros:
	 * - Código da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Número da ficha.
	 */
	public void deleteCard() throws Exception {
		System.out.println("\nMétodo deleteCard\n");
		
		// Exclui uma ficha e envia para a lixeira.
		this.webServiceMessageArray = service.deleteCard(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador, this.numeroFicha);
		
		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getWebServiceMessage().equals("ok")) {
			System.out.println("Ficha " + this.numeroFicha + " foi excluida!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}
	
	/**
	 * Altera os metadados de uma ficha.
	 * 
	 * Método: updateCard.
	 * 
	 * Parâmetros:
	 * - Código da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Array de fichas;
	 * - Array de anexos da ficha;
	 * - Array de segurança da ficha;
	 * - Array de aprovadores da ficha;
	 * - Array de documentos relacionados da ficha.
	 */
	public void updateCard() throws Exception {
		System.out.println("\nMétodo updateCard\n");
		
		// Verifica se ficha existe.
		this.cardDtoArray = service.getCardVersion(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador, this.numeroFicha, this.versaoFicha, this.matriculaColaborador);

		// Se existe...
		if (this.cardDtoArray != null) {
			
			// Altera ficha. 
			this.cardDtoArray.getItem().get(0).setDocumentDescription(this.descricaoFicha);
			
			// Altera anexo da ficha.
			this.attachment.setFileName(this.getArchive().getName());
			this.attachment.setPrincipal(true);
			this.attachment.setFilecontent(this.arrayBytes);
			
			// Adiciona anexo no array de anexos.
			this.attachmentArray.getItem().add(this.attachment);
			
			// Recupera a seguranca da ficha. 
			List<DocumentSecurityConfigDto> ll = this.cardDto.getDocsecurity();
			for (DocumentSecurityConfigDto dsc : ll) {
				this.documentSecurityConfigDtoArray.getItem().add(dsc);
			}
			
			// Altera segurança da ficha.
			this.documentSecurityConfigDto.setAttributionValue(this.matriculaColaboradorSeguranca);
			this.documentSecurityConfigDto.setAttributionType(1); // 1 - Colaborador; 2 - Grupos; 3 - Todos.
			this.documentSecurityConfigDto.setSecurityLevel(3); // 0 - Leitura; 1 - Gravação; 2 - Modificação; 3 - Total.
			this.documentSecurityConfigDto.setPermission(true);
			this.documentSecurityConfigDto.setSecurityVersion(false);
			this.documentSecurityConfigDto.setShowContent(true);
			
			// Adiciona segurança no array de segurança.
			this.documentSecurityConfigDtoArray.getItem().add(this.documentSecurityConfigDto);
			
			// Altera aprovador da ficha.
			this.approverDto.setColleagueId(this.matriculaAprovador);
			this.approverDto.setApproverType(0); // 0 - Colaborador; 1 - Grupo de usuários.
			this.approverDto.setCompanyId(1);
			
			// Adiciona aprovador no array de aprovadores.
			this.approverDtoArray.getItem().add(this.approverDto);
			
			// Altera documento relacionado.
			this.relatedDocumentDto.setRelatedDocumentId(this.numeroDocumentoRelacionado);
			this.relatedDocumentDto.setCompanyId(1);
			
			// Adiciona documento no array de documentos relacionados.
			this.relatedDocumentDtoArray.getItem().add(this.relatedDocumentDto);
			
			// Altera os metadados de uma ficha.
			this.webServiceMessageArray = service.updateCard(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador, this.cardDtoArray, this.attachmentArray, this.documentSecurityConfigDtoArray, this.approverDtoArray, this.relatedDocumentDtoArray);
			
			// Mostra resultado.
			if (this.webServiceMessageArray.getItem().get(0).getWebServiceMessage().equals("ok")) {
				System.out.println("Ficha " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi alterada!");
			} else {
				System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
			}
		} else {
			System.out.println("Ficha " + this.numeroFicha + " com versão " + this.versaoFicha + " não foi encontrada!");
		}
	}
	
	/**
	 * Retorna a versão de uma ficha.
	 * 
	 * Método: getCardVersion.
	 * 
	 * Parâmetros:
	 * - Código da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Número da ficha;
	 * - Versão da ficha;
	 * - Matrícula do colaborador.
	 */
	public void getCardVersion() throws Exception {
		System.out.println("\nMétodo getCardVersion\n");

		// Retorna a versão de uma ficha.
		this.cardDtoArray = service.getCardVersion(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador, this.numeroFicha, this.versaoFicha, this.matriculaColaborador);

		// Mostra resultado.
		if (this.cardDtoArray != null) {
			System.out.println("Nome da ficha: " + cardDtoArray.getItem().get(0).getDocumentDescription());
			
			for (CardFieldDto campo : this.cardDtoArray.getItem().get(0).getCardData()) {
				System.out.println("	Valor do campo " + campo.getField() + ": " + campo.getValue());
			}
		} else {
			System.out.println("Ficha " + this.numeroFicha + " com versão " + this.versaoFicha + " não foi encontrada!");
		}
	}
}