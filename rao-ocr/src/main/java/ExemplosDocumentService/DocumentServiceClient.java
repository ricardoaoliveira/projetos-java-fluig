package ExemplosDocumentService;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.dm.ws.ApprovalLevelDto;
import com.totvs.technology.ecm.dm.ws.ApprovalLevelDtoArray;
import com.totvs.technology.ecm.dm.ws.ApproverDto;
import com.totvs.technology.ecm.dm.ws.ApproverDtoArray;
import com.totvs.technology.ecm.dm.ws.ApproverWithLevelDto;
import com.totvs.technology.ecm.dm.ws.ApproverWithLevelDtoArray;
import com.totvs.technology.ecm.dm.ws.Attachment;
import com.totvs.technology.ecm.dm.ws.AttachmentArray;
import com.totvs.technology.ecm.dm.ws.DocumentApprovalStatusDto;
import com.totvs.technology.ecm.dm.ws.DocumentApprovalStatusDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentApprovementHistoryDto;
import com.totvs.technology.ecm.dm.ws.DocumentApprovementHistoryDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentDto;
import com.totvs.technology.ecm.dm.ws.DocumentDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDto;
import com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentService;
import com.totvs.technology.ecm.dm.ws.ECMDocumentServiceService;
import com.totvs.technology.ecm.dm.ws.RelatedDocumentDto;
import com.totvs.technology.ecm.dm.ws.RelatedDocumentDtoArray;
import com.totvs.technology.ecm.dm.ws.WebServiceMessageArray;
//import com.totvs.technology.ecm.dm.ws.CopyDocumentDto; //Não consegue encontrar
//import com.totvs.technology.ecm.dm.ws.CopyDocumentDtoArray; //Não consegue encontrar

import net.java.dev.jaxb.array.IntArray;

/**
 * Classe que utiliza todos os métodos da DocumentService.
 * Com essa classe, pode-se criar, alterar, excluir e pesquisar documentos, além de realizar outras atividades relacionadas a documentos.
 * No método setParameters, pode-se setar algumas das variáveis que são mais utilizadas como parâmetros nos métodos desta classe.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class DocumentServiceClient {

	// Variáveis.
	String loginColaborador, senhaColaborador, matriculaColaborador, matriculaAprovador, matriculaColaboradorSeguranca, codigoDocumentoExterno, publicadorDocumento, descricaoDocumento, arquivo, nomeArquivo;
	int codigoEmpresa, numeroDocumento, versaoDocumento, qtdeDocumentosBusca, numeroDocumentPai, numeroPastaParticular, numeroDocumentoRelacionado, destFolder, permissionType, restrictionType, limite, ultimoRegistro;
	byte[] arrayBytes = null;
	boolean publicaViaFTP;
	IntArray arrayDocuments = new IntArray();
	File file;
	
	// Variáveis de data.
	XMLGregorianCalendar approvedDate, createDate, expirationDate, lastModifiedDate, validationStartDate;
	Date data = new Date();

	// Dto's.
	DocumentDto documentDto = new DocumentDto();
	Attachment attachment = new Attachment();
	DocumentSecurityConfigDto documentSecurityConfigDto = new DocumentSecurityConfigDto();
	ApproverDto approverDto = new ApproverDto();
	RelatedDocumentDto relatedDocumentDto = new RelatedDocumentDto();
	ApproverWithLevelDto approverWithLevelDto = new ApproverWithLevelDto();
	ApprovalLevelDto approvalLevelDto = new ApprovalLevelDto();

	// Array's
	DocumentDtoArray documentDtoArray = new DocumentDtoArray();
	AttachmentArray attachmentArray = new AttachmentArray();
	DocumentSecurityConfigDtoArray documentSecurityConfigDtoArray = new DocumentSecurityConfigDtoArray();
	ApproverDtoArray approverDtoArray = new ApproverDtoArray();
	RelatedDocumentDtoArray relatedDocumentDtoArray = new RelatedDocumentDtoArray();
	ApproverWithLevelDtoArray approverWithLevelDtoArray = new ApproverWithLevelDtoArray();
	ApprovalLevelDtoArray approvalLevelDtoArray = new ApprovalLevelDtoArray();
	WebServiceMessageArray webServiceMessageArray = new WebServiceMessageArray();

	// Instancia DocumentServiceService.
	ECMDocumentServiceService documentService = new ECMDocumentServiceService();
	DocumentService service = documentService.getDocumentServicePort();

	public void createDocumentByParentId(int numeroDocumentPai, File file) {
		// Instancia classe DocumentServiceClient.
		DocumentServiceClient dsc = new DocumentServiceClient();		

		// Configura acesso ao WebServices.
		BindingProvider bp = (BindingProvider) dsc.service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://169.254.186.142:8080/webdesk/DocumentService");

		try {
			// Chama método que configura os valores das variáveis.
			dsc.setParameters();
			
			dsc.numeroDocumentPai = numeroDocumentPai;
			dsc.file = file;
			dsc.nomeArquivo = file.getAbsolutePath();
			dsc.descricaoDocumento = file.getName();

			// Chama método que é responsável por executar os métodos da classe.
			dsc.createSimpleDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Inicia execução da classe.
	public static void main (String args[]) {
		System.out.println("\nClasse DocumentService");

		// Instancia classe DocumentServiceClient.
		DocumentServiceClient dsc = new DocumentServiceClient();

		// Configura acesso ao WebServices.
		BindingProvider bp = (BindingProvider) dsc.service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://169.254.186.142:8080/webdesk/DocumentService");

		try {
			// Chama método que configura os valores das variáveis.
			dsc.setParameters();

			// Chama método que é responsável por executar os métodos da classe.
			dsc.changeMethod();
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
		this.senhaColaborador = "adm";
		this.matriculaColaborador = "admin";
		this.matriculaAprovador = "admin";
		this.matriculaColaboradorSeguranca = "admin";
		this.codigoEmpresa = 1;
		this.numeroDocumento = 46130;
		this.codigoDocumentoExterno = "ECM";
		this.versaoDocumento = 1000;
		this.descricaoDocumento = "Documento do ECM";
		this.publicadorDocumento = "admin";
		this.numeroDocumentoRelacionado = 43538;
		this.numeroDocumentPai = 34;
		this.numeroPastaParticular = 110;
		this.qtdeDocumentosBusca = 3;
		//this.arquivo = "C:\\development\\upload\\Doc.docx";
		this.nomeArquivo = "Doc.docx";
		this.publicaViaFTP = false; // Setando para true, é possíl utilizar um arquivo que esta no diretório de upload do colaborador publicador para ser anexado ao documento.
		this.permissionType= 1;
		this.restrictionType= 1;
                this.limite = 5;
                this.ultimoRegistro = 0;
		
		//lista de ids de documentos para mover
		this.arrayDocuments.getItem().add(749);
		this.arrayDocuments.getItem().add(5007);
		this.arrayDocuments.getItem().add(5002);
		this.arrayDocuments.getItem().add(802);
		
		//id pasta destino
		this.destFolder = 4956;
		

		// Cria data de expiração do documento.
		this.expirationDate = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		this.expirationDate.setYear(2011);
		this.expirationDate.setMonth(07);
		this.expirationDate.setDay(30);
		this.expirationDate.setHour(0);
		this.expirationDate.setMinute(0);
		this.expirationDate.setSecond(0);


		// Cria data de validação inicial do documento.
		this.validationStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		this.validationStartDate.setYear(2010);
		this.validationStartDate.setMonth(05);
		this.validationStartDate.setDay(15);
		this.validationStartDate.setHour(0);
		this.validationStartDate.setMinute(0);
		this.validationStartDate.setSecond(0);
	}

	/**
	 * Escolhe método.
	 * Nesse método, pode-se escolher qual método da classe seré executado.
	 */
	public void changeMethod() throws Exception {
		// Chama método getActiveDocuments.
		//this.getActiveDocuments();

		// Chama método getDocumentContent.
		//this.getDocumentContent();

		// Chama método getDocumentVersion.
		//this.getDocumentVersion();

		// Chama método getDocumentByExternalId.
		//this.getDocumentByExternalId();

		// Chama método getSecurity.
		//this.getSecurity();

		// Chama método getApprovers.
		//this.getApprovers();

		// Chama método getRelatedDocuments.
		//this.getRelatedDocuments();

		// Chama método createDocument.
		//this.createDocument();

		// Chama método createDocumentWithApprovementLevels.
		//this.createDocumentWithApprovementLevels();

		// Chama método updateDocument.
		//this.updateDocument();

		// Chama método updateSimpleDocument.
		//this.updateSimpleDocument();

		// Chama método updateDocumentWithApprovementLevels. 
		//this.updateDocumentWithApprovementLevels();

		// Chama método validateIntegrationRequirements.
		//this.validateIntegrationRequirements();

		// Chama método getReportSubjectId.
		//this.getReportSubjectId();

		// Chama método removeSecurity.
		//this.removeSecurity();

		// Chama método getUserPermissions.
		//this.getUserPermissions();

		// Chama método createSimpleDocument.
		this.createSimpleDocument();

		// Chama método createSimpleDocumentPrivate.
		//this.createSimpleDocumentPrivate();

		// Chama método findMostPopularDocuments.
		//this.findMostPopularDocuments();
            
        // Chama método findMostPopularDocumentsOnDemand.
		//this.findMostPopularDocumentsOnDemand();

		// Chama método deleteDocument.
		//this.deleteDocument();

		// Chama método destroyDocument.
		//this.destroyDocument();

		// Chama método findRecycledDocuments.
		//this.findRecycledDocuments();

		// Chama método restoreDocument.
		//this.restoreDocument();
		
		// Chama método moveDocument.
		//this.moveDocument();
		
		//Chama método updateGroupSecurityType.
		//this.updateGroupSecurityType();
		
		//Chama método getDocumentApprovalStatus
		//this.getDocumentApprovalStatus();
		
		//Chama método getDocumentApprovalHistory;
		//getDocumentApprovalHistory();
		
		//Chama método copyDocuments;
		//copyDocuments();
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
	 * Retorna o arquivo que será anexado no documento.
	 */
	public File getArchiveCreateSimpleDocument() {
		// Cria arquivo.
		if(this.file.exists()){
			try{
				byte[] buffer = new byte[8192];
				FileInputStream fis = new FileInputStream(this.file);
				BufferedInputStream bis = new BufferedInputStream(fis, 8192);

				ByteArrayOutputStream baos = new ByteArrayOutputStream((int) this.file.length());
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
	 * Retorna um documento ativo.
	 * 
	 * Método: getActiveDocument.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Matrícula do colaborador.
	 */
	public void getActiveDocuments() throws Exception {
		System.out.println("\nMétodo getActiveDocuments\n");

		// Retorna documento ativo.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Mostra resultado.
		if (this.documentDtoArray.getItem().size() > 0) {
			System.out.println("Nome do documento: " + documentDtoArray.getItem().get(0).getDocumentDescription());
		} else {
			System.out.println("Documento " + this.numeroDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Retorna o byte do arquivo físico de um documento.
	 * 
	 * Método: getDocumentContent.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Matrícula do colaborador;
	 * - Versão do documento;
	 * - Nome do arquivo.
	 */
	public void getDocumentContent() throws Exception {
		System.out.println("\nMétodo getDocumentContent\n");

		// Retorna o byte do arquivo físico do documento.
		byte[] byt = service.getDocumentContent(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador, this.versaoDocumento, this.nomeArquivo);

		// Mostra resultado.
		System.out.println("byte: " + byt.toString());
	}

	/**
	 * Retorna a versão de um documento.
	 * 
	 * Método: getDocumentVersion.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Versão do documento;
	 * - Matrícula do colaborador.
	 */
	public void getDocumentVersion() throws Exception {
		System.out.println("\nMétodo getDocumentVersion\n");

		// Retorna a versão do documento.
		this.documentDtoArray = (DocumentDtoArray) service.getDocumentVersion(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento, this.matriculaColaborador);

		// Mostra resultado.
		if (this.documentDtoArray.getItem().size() > 0) {
			System.out.println("Nome do documento: " + documentDtoArray.getItem().get(0).getDocumentDescription());
		} else {
			System.out.println("Documento " + this.numeroDocumento + " na versão: " + this.versaoDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Retorna um documento pelo código externo.
	 * 
	 * Método: getDocumentByExternalId.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Código externo do documento;
	 * - Matrícula do colaborador.
	 */
	public void getDocumentByExternalId() throws Exception {
		System.out.println("\nMétodo getDocumentByExternalId\n");

		// Retorna documento pelo código externo.
		this.documentDtoArray = (DocumentDtoArray) service.getDocumentByExternalId(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.codigoDocumentoExterno, this.matriculaColaborador);

		// Mostra resultado.
		if (this.documentDtoArray != null) {
			System.out.println("Nome do documento externo: " + documentDtoArray.getItem().get(0).getDocumentDescription());
		} else {
			System.out.println("Documento com código externo " + this.codigoDocumentoExterno + " não foi encontrado!");
		}
	}

	/**
	 * Retorna a segurança de um documento.
	 * 
	 * Método: getSecurity.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Versão do documento.
	 */
	public void getSecurity() throws Exception {
		System.out.println("\nMétodo getSecurity\n");

		// Verifica se documento existe.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Se existe...
		if (this.documentDtoArray.getItem().get(0) != null) {

			// Retorna segurança do documento.
			this.documentSecurityConfigDtoArray = (DocumentSecurityConfigDtoArray) service.getSecurity(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento);

			// Mostra resultado.
			if (this.documentSecurityConfigDtoArray.getItem().size() > 0) {
				for (int i = 0; i < this.documentSecurityConfigDtoArray.getItem().size(); i++) {
					System.out.println("Colaborador: " + this.documentSecurityConfigDtoArray.getItem().get(i).getAttributionValue());
					System.out.println("Sequência: " + this.documentSecurityConfigDtoArray.getItem().get(i).getSequence());
					System.out.println("Level de segurança: " + this.documentSecurityConfigDtoArray.getItem().get(i).getSecurityLevel());
					System.out.println("");
				}
			} else {
				System.out.println("Documento " + this.numeroDocumento + " não possui segurança!");
			}
		} else {
			System.out.println("Documento " + this.numeroDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Retorna os aprovadores de um documento.
	 * 
	 * Método: getApprovers.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Versão do documento.
	 */
	public void getApprovers() throws Exception {
		System.out.println("\nMétodo getApprovers\n");

		// Verifica se documento existe.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Se existe...
		if (this.documentDtoArray.getItem().get(0) != null) {

			String approverType = "";

			// Retorna os aprovadores do documento.
			this.approverWithLevelDtoArray = (ApproverWithLevelDtoArray) service.getApprovers(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento);

			// Mostra resultado.
			if (this.approverWithLevelDtoArray.getItem().size() > 0) {
				for (int i = 0; i < this.approverWithLevelDtoArray.getItem().size(); i++) {

					// Verifica tipo de aprovação (0 - Colaboradores; 1 - Grupos).
					if (this.approverWithLevelDtoArray.getItem().get(i).getApproverType() == 0) {
						approverType = "Colaborador: ";
					} else {
						approverType = "Grupo: ";
					}

					System.out.println(approverType + this.approverWithLevelDtoArray.getItem().get(i).getColleagueId());
					System.out.println("Nível de aprovação: " + this.approverWithLevelDtoArray.getItem().get(i).getLevelId());
					System.out.println("");
				}
			} else {
				System.out.println("Documento " + this.numeroDocumento + " não possui aprovadores!");
			}
		} else {
			System.out.println("Documento " + this.numeroDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Retorna os documentos relacionados de um documento.
	 * 
	 * Método: getRelatedDocuments.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Versão do documento.
	 */
	public void getRelatedDocuments() throws Exception {
		System.out.println("\nMétodo getRelatedDocuments\n");

		// Verifica se documento existe.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Se existe...
		if (this.documentDtoArray.getItem().get(0) != null) {

			// Retorna os documentos relacionados do documento.
			this.relatedDocumentDtoArray = (RelatedDocumentDtoArray) service.getRelatedDocuments(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento);

			// Mostra resultado.
			if (this.relatedDocumentDtoArray.getItem().size() > 0) {
				for (int i = 0; i < this.relatedDocumentDtoArray.getItem().size(); i++) {
					System.out.println("Documento Relacionado: " + this.relatedDocumentDtoArray.getItem().get(i).getRelatedDocumentId());
					System.out.println("");
				}
			} else {
				System.out.println("Documento " + this.numeroDocumento + " não possui documentos relacionados!");
			}
		} else {
			System.out.println("Documento " + this.numeroDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Cria um documento.
	 * 
	 * Método: createDocument.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Array de documentos;
	 * - Array de anexos do documento;
	 * - Array de segurança do documento;
	 * - Array de aprovadores do documento;
	 * - Array de documentos relacionados do documento.
	 */
	public void createDocument() throws Exception {
		System.out.println("\nMétodo createDocument\n");

		// Cria documento.
		this.documentDto.setApprovalAndOr(false); //Informe 'false' para utilizar tipo de aprovação OU e 'true' para utilizar tipo de aprovação E.
		this.documentDto.setAtualizationId(1); //Campo descontinuado, está mantido no DTO para suportar o legado
		this.documentDto.setColleagueId(this.matriculaColaborador); //Matrícula do publicador do documento
		this.documentDto.setCompanyId(1); //Identificador da empresa cadastrada no Fluig
		this.documentDto.setDeleted(false); //Lixeira: true - enviar documento para a lixeira ; false - cria o documento na navegação de documentos.
		this.documentDto.setDocumentDescription(this.descricaoDocumento); //Descrição do documento
		this.documentDto.setDocumentType("2"); // Tipo de documento:  1 - Pasta; 2 - Documento; 3 - Documento Externo; 4 - Fichário; 5 - Fichas; 9 - Aplicativo; 10 - Relatório.
		this.documentDto.setDownloadEnabled(true); //Habilita o download do documento
		this.documentDto.setExpirationDate(this.expirationDate); //- //Data de expiração do documento
		this.documentDto.setExpires(false); //Documento Expira?
		this.documentDto.setInheritSecurity(true); //Herda Segurança?
		this.documentDto.setParentDocumentId(this.numeroDocumentPai); //Código da pasta em que o documento será publicado
		this.documentDto.setPrivateDocument(false); //Pertence a pasta Meus Documentos?
		this.documentDto.setPublisherId(this.publicadorDocumento); //Matrícula do publicador do documento
		this.documentDto.setValidationStartDate(this.validationStartDate); //Início da data de validade
		this.documentDto.setUpdateIsoProperties(true); //Atualizar propriedades de cópia controlada?
		this.documentDto.setUserNotify(false); // Notifica atualização?
		this.documentDto.setVersionOption("0"); // 0 - Mantém versão atual; 1 - Cria nova revisão; 2 - Cria nova versão.
		this.documentDto.setDocumentPropertyNumber(0); //Formulário ou Documento - 0; Subformulário - informar número do formulário PAI.
		this.documentDto.setDocumentPropertyVersion(0); //Informar a versão do Formulário pai, se houver.
		this.documentDto.setVolumeId("Default");  //Volume utilizado
		this.documentDto.setIconId(2); //ID do ícone cadastrado no Painel de Controle do Fluig
		this.documentDto.setLanguageId("pt"); //ID de idioma: pt - Português; en - Inglês; es - Espanhol.
		this.documentDto.setIndexed(false); //Informar se o documento foi indexado. Para criação sempre 'false'.
		this.documentDto.setActiveVersion(true); //Informar se é a versão ativa do documento. Para criação sempre 'true'.
		this.documentDto.setTranslated(false); //Campo descontinuado, está mantido no DTO para suportar o legado
		this.documentDto.setTopicId(1); //ID do assunto cadastrado no Painel de Controle do Fluig 
		this.documentDto.setDocumentTypeId(""); //ID do tipo de documento
		this.documentDto.setExternalDocumentId(""); //Criar ID personalizado para o documento
		this.documentDto.setDatasetName(""); //Informar o nome do dataset, se o documentado publicado for um formulário e desejar armazenar os dados deste em um dataset.
		this.documentDto.setColleagueName(""); //Nome do publicador
		this.documentDto.setVersionDescription(""); //Descrição da versão/revisão do documento
		this.documentDto.setKeyWord(""); //Palavras chaves
		this.documentDto.setAdditionalComments(""); //Comentários do documento
		this.documentDto.setImutable(false); //Versão revisão inalterável
		//this.documentDto.setProtectedCopy(); //Campo descontinuado, está mantido no DTO para suportar o legado
		this.documentDto.setAccessCount(0); //Hits do documento. Na criação inicia 0.
		this.documentDto.setVersion(this.versaoDocumento); //Versão do documento


		// Adiciona documento no array de documentos.
		this.documentDtoArray.getItem().add(this.documentDto);

		// Cria anexo do documento.
		if (!this.publicaViaFTP) {
			if (this.getArchive() != null && this.getArchive().exists()){
				this.attachment.setFileName(this.getArchive().getName()); //Nome do arquivo
				this.attachment.setPrincipal(true); //Arquivo principal?
				this.attachment.setFilecontent(this.arrayBytes); //Bytes do arquivo físico
			} else {
				System.out.println("Arquivo utilizado para anexo não existe!");
			}


		} else {
			// Anexa um arquivo do diretório de upload. Para isso, o arquivo deve estar no diretório
			// de upload do colaborador que vai publicar o documento.
			if (this.getArchive() != null && this.getArchive().exists()){
				this.attachment.setFileName(this.nomeArquivo); //Nome do arquivo
				this.attachment.setPrincipal(true); //Arquivo principal?
				this.attachment.setFilecontent(null); 
			} else {
				System.out.println("Arquivo utilizado para anexo não existe!");
			}

		}

		// Adiciona anexo no array de anexos.
		this.attachmentArray.getItem().add(this.attachment);

		// Cria segurança do documento.
		this.documentSecurityConfigDto.setAttributionValue(this.matriculaColaboradorSeguranca); //Matrícula do usuário que possuirá a segurança
		this.documentSecurityConfigDto.setAttributionType(1); //Tipo de atribuição: 1 - Colaborador; 2 - Grupos; 3 - Todos.
		this.documentSecurityConfigDto.setSecurityLevel(3); //Nível de segurança: 0 - Leitura; 1 - Gravação; 2 - Modificação; 3 - Total.
		this.documentSecurityConfigDto.setPermission(true); //true - permissão ; false - restrição.
		this.documentSecurityConfigDto.setSecurityVersion(false); //informa se os usuário/grupo/todos possuirão acesso a versão anteriores do documento
		this.documentSecurityConfigDto.setShowContent(true); //Informa se os usuário/grupo/todos possuirão permissão para os conteúdos listados

		// Adiciona segurança no array de segurança.
		this.documentSecurityConfigDtoArray.getItem().add(this.documentSecurityConfigDto);

		// Cria aprovador do documento.
		this.approverDto.setColleagueId(this.matriculaAprovador); //Matrícula do aprovador
		this.approverDto.setApproverType(0); //Tipo de aprovador: 0 - Colaborador; 1 - Grupo de usuários.
		this.approverDto.setCompanyId(1); //Identificador da empresa cadastrada no Fluig
		this.approverDto.setLevelId(1); // Define a criação do aprovador par ao primeiro nível de aprovação. Para criar documento com aprovadores em mais níveis utilizar método createDocumentWithApprovementLevels
	

		// Adiciona aprovador no array de aprovadores.
		this.approverDtoArray.getItem().add(this.approverDto);

		// Cria documento relacionado.
		this.relatedDocumentDto.setRelatedDocumentId(this.numeroDocumentoRelacionado);

		// Adiciona documento no array de documentos relacionados.
		this.relatedDocumentDtoArray.getItem().add(this.relatedDocumentDto);

		// Cria documento.
		this.webServiceMessageArray = service.createDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.documentDtoArray, this.attachmentArray, this.documentSecurityConfigDtoArray, this.approverDtoArray, this.relatedDocumentDtoArray);

		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
			System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi publicado!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}

	/**
	 * Cria um documento com nível de aprovação.
	 * 
	 * Método: createDocumentWithApprovementLevels.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Array de documentos;
	 * - Array de anexos do documento;
	 * - Array de segurança do documento;
	 * - Array de aprovadores em níveis;
	 * - Array de níveis de aprovação do documento;
	 * - Array de documentos relacionados do documento.
	 */
	public void createDocumentWithApprovementLevels() throws Exception {
		System.out.println("\nMétodo createDocumentWithApprovementLevels\n");

		// Cria documento.
		this.documentDto.setApprovalAndOr(true);
		this.documentDto.setAtualizationId(1);
		this.documentDto.setColleagueId(this.matriculaColaborador);
		this.documentDto.setCompanyId(1);
		this.documentDto.setDeleted(false);
		this.documentDto.setDocumentDescription(this.descricaoDocumento);
		this.documentDto.setDocumentType("2"); // 1 - Pasta; 2 - Documento; 3 - Documento Externo; 4 - Fichário; 5 - Fichas; 9 - Aplicativo; 10 - Relatório.
		this.documentDto.setDownloadEnabled(true);
		this.documentDto.setExpirationDate(this.expirationDate);
		this.documentDto.setExpires(true);
		this.documentDto.setInheritSecurity(true);
		this.documentDto.setParentDocumentId(this.numeroDocumentPai);
		this.documentDto.setPrivateDocument(false);
		this.documentDto.setPublisherId(this.publicadorDocumento);
		this.documentDto.setValidationStartDate(this.validationStartDate);
		this.documentDto.setUpdateIsoProperties(true);
		this.documentDto.setUserNotify(false);
		this.documentDto.setVersionOption("0"); // 0 - Mantém versão atual; 1 - Cria nova revisão; 2 - Cria nova versão.
		this.documentDto.setDocumentPropertyNumber(0);
		this.documentDto.setDocumentPropertyVersion(0);
		this.documentDto.setVolumeId("Default");  
		this.documentDto.setIconId(2);
		this.documentDto.setLanguageId("pt");
		this.documentDto.setIndexed(false);
		this.documentDto.setActiveVersion(true);
		this.documentDto.setTranslated(false);
		this.documentDto.setTopicId(1);
		this.documentDto.setDocumentTypeId("");
		this.documentDto.setExternalDocumentId("");
		this.documentDto.setDatasetName("");
		this.documentDto.setColleagueName("");
		this.documentDto.setVersionDescription("");
		this.documentDto.setKeyWord("");
		this.documentDto.setAdditionalComments("");
		this.documentDto.setImutable(false);
		this.documentDto.setProtectedCopy(false);
		this.documentDto.setAccessCount(0);
		this.documentDto.setVersion(this.versaoDocumento);

		// Adiciona documento no array de documentos.
		this.documentDtoArray.getItem().add(this.documentDto);

		//Este 'if' é utilizado para validar se o arquivo que foi utilizado para ser anexo do documento existe.
		if (this.getArchive() != null && this.getArchive().exists()){
			// Cria anexo do documento.
			if (!this.publicaViaFTP) {
				this.attachment.setFileName(this.getArchive().getName());
				this.attachment.setPrincipal(true);
				this.attachment.setFilecontent(this.arrayBytes);
			} else {
				/* Anexa um arquivo do diretório de upload. Para isso, o arquivo deve estar no diretório
			   de upload do colaborador que vai publicar o documento. */
				this.attachment.setFileName(this.nomeArquivo);
				this.attachment.setPrincipal(true);
				this.attachment.setFilecontent(null);
			}
		} else {
			System.out.println("Arquivo utilizado para anexo não existe!");
		}


		// Adiciona anexo no array de anexos.
		this.attachmentArray.getItem().add(this.attachment);

		// Cria segurança do documento.
		this.documentSecurityConfigDto.setAttributionValue(this.matriculaColaboradorSeguranca);
		this.documentSecurityConfigDto.setAttributionType(1); // 1 - Colaborador; 2 - Grupos; 3 - Todos.
		this.documentSecurityConfigDto.setSecurityLevel(3); // 0 - Leitura; 1 - Gravação; 2 - Modificação; 3 - Total.
		this.documentSecurityConfigDto.setPermission(true);
		this.documentSecurityConfigDto.setSecurityVersion(false);
		this.documentSecurityConfigDto.setShowContent(true);

		// Adiciona segurança no array de segurança.
		this.documentSecurityConfigDtoArray.getItem().add(this.documentSecurityConfigDto);

		// Cria nível de aprovação em que os aprovadores do documento estarão.
		this.approverWithLevelDto.setApproverType(0); // 0 - Colaborador; 1 - Grupo de usuários.
		this.approverWithLevelDto.setColleagueId(this.matriculaAprovador);
		this.approverWithLevelDto.setLevelId(1);
		this.approverWithLevelDto.setCompanyId(this.codigoEmpresa);

		// Adiciona níveis de aprovação no array de aprovadores em níveis.
		this.approverWithLevelDtoArray.getItem().add(this.approverWithLevelDto);

		// Cria níveis de aprovação do documento.
		this.approvalLevelDto.setApprovalMode(1); // 1 - Ou; 2 - E; 3 - única.
		this.approvalLevelDto.setLevelDescription("1");
		this.approvalLevelDto.setLevelId(1);

		// Adiciona níveis de aprovação no array de níveis de aprovação do documento.
		this.approvalLevelDtoArray.getItem().add(this.approvalLevelDto);

		// Cria documento relacionado.
		this.relatedDocumentDto.setRelatedDocumentId(this.numeroDocumentoRelacionado);

		// Adiciona documento no array de documentos relacionados.
		this.relatedDocumentDtoArray.getItem().add(this.relatedDocumentDto);

		// Cria documento.
		this.webServiceMessageArray = service.createDocumentWithApprovementLevels(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.documentDtoArray, this.attachmentArray, this.documentSecurityConfigDtoArray, this.approverWithLevelDtoArray, this.approvalLevelDtoArray, this.relatedDocumentDtoArray);

		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
			System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi publicado!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}

	/**
	 * Altera um documento.
	 * 
	 * Método: updateDocument.
	 * 
	 * Parâtros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Array de documentos;
	 * - Array de anexos do documento;
	 * - Array de segurança do documento;
	 * - Array de aprovadores do documento;
	 * - Array de documentos relacionados do documento.
	 */
	public void updateDocument() throws Exception {
		System.out.println("\nMétodo updateDocument\n");

		// Verifica se documento existe.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Se existe...
		if (this.documentDtoArray.getItem().get(0) != null) {
			// Altera documento. 
			this.documentDtoArray.getItem().get(0).setDocumentDescription(this.descricaoDocumento); //Descrição do documento

			//Altera a versão
			this.documentDto.setVersion(this.versaoDocumento); //Versão do documento

			// Este 'if' é utilizado para validar se o arquivo que foi utilizado para ser anexo do documento existe.
			if (this.getArchive() != null && this.getArchive().exists()){
				// Cria anexo do documento.
				if (!this.publicaViaFTP) {
					this.attachment.setFileName(this.getArchive().getName()); //Nome do arquivo
					this.attachment.setPrincipal(true); //Arquivo principal
					this.attachment.setFilecontent(this.arrayBytes); //Conteúdo do arquivo
				} else {
					// Anexa um arquivo do  de upload. Para isso, o arquivo deve estar no diretório
					// de upload do colaborador que vai publicar o documento.
					this.attachment.setFileName(this.nomeArquivo); //Nome do arquivo
					this.attachment.setPrincipal(true); //Arquivo principal?
					this.attachment.setFilecontent(null); //Bytes do arquivo físico
				}
			} else {
				System.out.println("Arquivo utilizado para anexo n�o existe!");
			}

			// Adiciona anexo no array de anexos.
			this.attachmentArray.getItem().add(this.attachment);

			// Altera segurança do documento.
			this.documentSecurityConfigDto.setAttributionValue(this.matriculaColaboradorSeguranca); //Matrícula do usuário que possuirá a segurança
			this.documentSecurityConfigDto.setAttributionType(1); // Tipo de atribuição: 1 - Colaborador; 2 - Grupos; 3 - Todos.
			this.documentSecurityConfigDto.setSecurityLevel(3); //Nível de aprovação: 0 - Leitura; 1 - Gravação; 2 - Modificação; 3 - Total.
			this.documentSecurityConfigDto.setPermission(true); //true - permissão ; false - restrição.
			this.documentSecurityConfigDto.setSecurityVersion(false); //informa se os usuário/grupo/todos possuirão acesso a versão anteriores do documento
			this.documentSecurityConfigDto.setShowContent(true); //Informa se os usuário/grupo/todos possuirão permissão para os conteúdos listados


			// Adiciona segurança no array de segurança.
			this.documentSecurityConfigDtoArray.getItem().add(this.documentSecurityConfigDto);

			// Altera aprovador do documento.
			this.approverDto.setColleagueId(this.matriculaAprovador);
			this.approverDto.setApproverType(0); //Tipo de aprovador: 0 - Colaborador; 1 - Grupo de usuários.
			this.approverDto.setCompanyId(1); //Identificador da empresa cadastrada no Fluig

			// Adiciona aprovador no array de aprovadores.
			this.approverDtoArray.getItem().add(this.approverDto);

			// Altera documento relacionado.
			this.relatedDocumentDto.setRelatedDocumentId(this.numeroDocumentoRelacionado);

			// Adiciona documento no array de documentos relacionados.
			this.relatedDocumentDtoArray.getItem().add(this.relatedDocumentDto);

			// Atualiza documento.
			this.webServiceMessageArray = service.updateDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.documentDtoArray, this.attachmentArray, this.documentSecurityConfigDtoArray, this.approverDtoArray, this.relatedDocumentDtoArray);

			// Mostra resultado.
			if (this.webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
				System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi atualizado!");
			} else {
				System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
			}
		} else {
			System.out.println("Documento " + this.numeroDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Altera um documento simples.
	 * 
	 * Método: updateSimpleDocument.
	 * 
	 * Parâmetros:
	 * - Login do usuário de integração;
	 * - Senha do usuário de integração;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Matrícula do colaborador publicador;
	 * - Descrição do documento;
	 * - Array de anexos do documento;
	 */
	public void updateSimpleDocument() throws Exception {
		System.out.println("\nMétodo updateSimpleDocument\n");

		// Este 'if' é utilizado para validar se o arquivo que foi utilizado para ser anexo do documento existe.
		if (this.getArchive() != null && this.getArchive().exists()){
			// Cria anexo do documento.
			if (!this.publicaViaFTP) {
				this.attachment.setFileName(this.getArchive().getName());
				this.attachment.setPrincipal(true);
				this.attachment.setFilecontent(this.arrayBytes);
			} else {
				// Anexa um arquivo do diretório de upload. Para isso, o arquivo deve estar no diretório
				// de upload do colaborador que vai publicar o documento.
				this.attachment.setFileName(this.nomeArquivo);
				this.attachment.setPrincipal(true);
				this.attachment.setFilecontent(null);
			}
		} else {
			System.out.println("Arquivo utilizado para anexo não existe!");
		}

		// Adiciona anexo no array de anexos.
		this.attachmentArray.getItem().add(this.attachment);

		// Altera documento simples.
		this.webServiceMessageArray = service.updateSimpleDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.publicadorDocumento, this.descricaoDocumento, this.attachmentArray);

		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
			System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi alterado!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}

	/**
	 * Altera um documento com nível de aprovação.
	 * 
	 * Método: updateDocumentWithApprovementLevels.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Array de documentos;
	 * - Array de anexos do documento;
	 * - Array de segurança do documento;
	 * - Array de aprovadores em níveis;
	 * - Array de níveis de aprovação do documento;
	 * - Array de documentos relacionados do documento.
	 */
	public void updateDocumentWithApprovementLevels() throws Exception {
		System.out.println("\nMétodo updateDocumentWithApprovementLevels\n");

		// Verifica se documento existe.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Se existe...
		if (this.documentDtoArray.getItem().get(0) != null) {
			// Altera documento. 
			this.documentDtoArray.getItem().get(0).setDocumentDescription(this.descricaoDocumento);
			this.documentDtoArray.getItem().get(0).setVersionOption("0"); // 0 - Mantém versão atual; 1 - Cria nova revisão; 2 - Cria nova versão.
			this.documentDtoArray.getItem().get(0).setApprovalAndOr(true);

			// Este 'if' é utilizado para validar se o arquivo que foi utilizado para ser anexo do documento existe.
			if (this.getArchive() != null && this.getArchive().exists()){
			// Cria anexo do documento.
			if (!this.publicaViaFTP) {
					this.attachment.setFileName(this.getArchive().getName());
					this.attachment.setPrincipal(true);
					this.attachment.setFilecontent(this.arrayBytes);
			} else {
				// Anexa um arquivo do diretório de upload. Para isso, o arquivo deve estar no diretório
				// de upload do colaborador que vai publicar o documento.
					this.attachment.setFileName(this.nomeArquivo);
					this.attachment.setPrincipal(true);
					this.attachment.setFilecontent(null);
			}
			} else {
				System.out.println("Arquivo utilizado para anexo não existe!");
			}

			// Adiciona anexo no array de anexos.
			this.attachmentArray.getItem().add(this.attachment);

			// Altera segurança do documento.
			this.documentSecurityConfigDto.setAttributionValue(this.matriculaColaboradorSeguranca);
			this.documentSecurityConfigDto.setAttributionType(1); // 1 - Colaborador; 2 - Grupos; 3 - Todos.
			this.documentSecurityConfigDto.setSecurityLevel(3); // 0 - Leitura; 1 - Gravação; 2 - Modificação; 3 - Total.
			this.documentSecurityConfigDto.setPermission(true);
			this.documentSecurityConfigDto.setSecurityVersion(false);
			this.documentSecurityConfigDto.setShowContent(true);

			// Adiciona segurança no array de segurança.
			this.documentSecurityConfigDtoArray.getItem().add(this.documentSecurityConfigDto);

			// Altera níbvel de aprovação em que os aprovadores do documento estarão.
			this.approverWithLevelDto.setApproverType(0); // 0 - Colaborador; 1 - Grupo de usuários.
			this.approverWithLevelDto.setColleagueId(this.matriculaAprovador);
			this.approverWithLevelDto.setLevelId(1);
			this.approverWithLevelDto.setCompanyId(this.codigoEmpresa);

			// Adiciona níveis de aprovação no array de aprovadores em níveis.
			this.approverWithLevelDtoArray.getItem().add(this.approverWithLevelDto);

			// Altera níveis de aprovação do documento.
			this.approvalLevelDto.setApprovalMode(1); // 1 - Ou; 2 - E; 3 - única.
			this.approvalLevelDto.setLevelDescription("1");
			this.approvalLevelDto.setLevelId(1);

			// Adiciona níveis de aprovação no array de níveis de aprovação do documento.
			this.approvalLevelDtoArray.getItem().add(this.approvalLevelDto);

			// Altera documento relacionado.
			this.relatedDocumentDto.setRelatedDocumentId(this.numeroDocumentoRelacionado);

			// Adiciona documento no array de documentos relacionados.
			this.relatedDocumentDtoArray.getItem().add(this.relatedDocumentDto);

			// Altera documento.
			this.webServiceMessageArray = service.updateDocumentWithApprovementLevels(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.documentDtoArray, this.attachmentArray, this.documentSecurityConfigDtoArray, this.approverWithLevelDtoArray, this.approvalLevelDtoArray, this.relatedDocumentDtoArray);

			// Mostra resultado.
			if (this.webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
				System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi alterado!");
			} else {
				System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
			}
		} else {
			System.out.println("Documento " + this.numeroDocumento + " n�o foi encontrado!");
		}
	}

	/**
	 * Verifica a integração do colaborador.
	 * 
	 * Método: validateIntegrationRequirements.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa.
	 */
	public void validateIntegrationRequirements() throws Exception {
		System.out.println("\nMétodo getReportSubjectId\n");

		// Verifica a integração do colaborador.
		String result = service.validateIntegrationRequirements(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa);

		// Mostra resultado.
		if (result.equals("OK")){
			System.out.println("integração do colaborador " + this.loginColaborador + ": " + result);
		} else {
			System.out.println(result);
		}
	}

	/**
	 * Retorna o código do assunto relatórios.
	 * 
	 * Método: getReportSubjectId.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - código da empresa.
	 */
	public void getReportSubjectId() throws Exception {
		System.out.println("\nMétodo getReportSubjectId\n");

		// Retorna o código do assunto relatórios.
		int codigo = service.getReportSubjectId(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa);

		// Mostra resultado.
		if (codigo != 0){
			System.out.println("código do assunto relatórios: " + codigo);
		} else {
			System.out.println("Assunto relatórios não encontrado!");
		}
	}

	/**
	 * Remove a segurança de um documento.
	 * 
	 * Método: removeSecurity.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Versão do documento.
	 */
	public void removeSecurity() throws Exception {
		System.out.println("\nMétodo removeSecurity\n");

		// Verifica se documento existe.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Se existe...
		if (this.documentDtoArray.getItem().get(0) != null) {

			// Verifica se documento possui segurança.
			this.documentSecurityConfigDtoArray = (DocumentSecurityConfigDtoArray) service.getSecurity(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento);

			// Se possui...
			if (this.documentSecurityConfigDtoArray.getItem().size() > 0) {

				// Remove segurança do documento.
				for (int i = 0; i < this.documentSecurityConfigDtoArray.getItem().size(); i++) {
					service.removeSecurity(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.documentSecurityConfigDtoArray.getItem().get(i).getDocumentId(), this.documentSecurityConfigDtoArray.getItem().get(i).getVersion());

					// Mostra resultado.
					System.out.println("Removendo colaborador " + this.documentSecurityConfigDtoArray.getItem().get(i).getAttributionValue() + " da segurança do documento " + this.numeroDocumento);
					System.out.println("");
				}
			} else {
				System.out.println("Documento " + this.numeroDocumento + " não possui segurança!");
			}
		} else {
			System.out.println("Documento " + this.numeroDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Retorna as permissões do colaborador sobre um documento.
	 * 
	 * Método: getUserPermissions. 
	 * 
	 * Parâmetros:
	 * - Código da empresa;
	 * - Matricula do colaborador;
	 * - Número do documento;
	 * - Versão do documento.
	 */
	public void getUserPermissions() throws Exception {
		System.out.println("\nMétodo getUserPermissions\n");

		// Verifica se documento existe.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Se existe...
		if (this.documentDtoArray.getItem().get(0) != null) {

			// Verifica se documento possui segurança.
			this.documentSecurityConfigDtoArray = (DocumentSecurityConfigDtoArray) service.getSecurity(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento);

			// Se possui...
			if (this.documentSecurityConfigDtoArray.getItem().size() > 0) {

				System.out.println("Número do documento: " + this.documentDtoArray.getItem().get(0).getDocumentId());
				System.out.println("Nome do documento: " + this.documentDtoArray.getItem().get(0).getDocumentDescription());
				System.out.println("Versão do documento: " + this.documentDtoArray.getItem().get(0).getVersion());

				// Retorna permissão do colaborador sobre o documento.
				int permissao = service.getUserPermissions(this.codigoEmpresa, this.matriculaColaborador, this.documentDtoArray.getItem().get(0).getDocumentId(), this.documentDtoArray.getItem().get(0).getVersion());

				String userPermission = "";

				// Verifica tipo de permissão.
				if (permissao == 0) {
					userPermission = "de leitura";
				} else if (permissao == 1) {
					userPermission = "de gravação";
				} else if (permissao == 2) {
					userPermission = "de modificação";
				} else if (permissao == 3) {
					userPermission = "total";
				} else {
					System.out.println("\nColaborador " + this.matriculaColaborador + " não possui permissão sobre o documento " + this.numeroDocumento + " - versão " + this.versaoDocumento);
				}

				// Mostra resultado.
				if (permissao > -1) {
					System.out.println("\nColaborador " + this.matriculaColaborador + " possui permissão " + userPermission + " sobre o documento " + this.numeroDocumento + " - versão " + this.versaoDocumento);
				}
			} else {
				System.out.println("Documento " + this.numeroDocumento + " não possui segurança!");
			}
		} else {
			System.out.println("Documento " + this.numeroDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Cria um documento simples.
	 * 
	 * Método: createSimpleDocument.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento pai;
	 * - Matrícula do colaborador publicador;
	 * - Descrição do documento;
	 * - Array de anexos do documento;
	 */
	public void createSimpleDocument() throws Exception {
		System.out.println("\nMétodo createSimpleDocument\n");

		// Este 'if' é utilizado para validar se o arquivo que foi utilizado para ser anexo do documento existe.
		if (this.getArchiveCreateSimpleDocument() != null && this.getArchiveCreateSimpleDocument().exists()){
			// Cria anexo do documento.
			if (!this.publicaViaFTP) {
				this.attachment.setFileName(this.getArchiveCreateSimpleDocument().getName());
				this.attachment.setPrincipal(true);
				this.attachment.setFilecontent(this.arrayBytes);
			} else {
				// Anexa um arquivo do diretório de upload. Para isso, o arquivo deve estar no diretório
				// de upload do colaborador que vai publicar o documento.
				this.attachment.setFileName(this.nomeArquivo);
				this.attachment.setPrincipal(true);
				this.attachment.setFilecontent(null);
			}
		} else {
			System.out.println("Arquivo utilizado para anexo não existe!");
		}

		// Adiciona anexo no array de anexos.
		this.attachmentArray.getItem().add(this.attachment);
		
		try {
			// Cria documento simples.
			this.webServiceMessageArray = service.createSimpleDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumentPai, this.publicadorDocumento, this.descricaoDocumento, this.attachmentArray);

			// Mostra resultado.
			if (this.webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
				System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi publicado!");
			} else {
				System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cria um documento simples na pasta particular.
	 * 
	 * Método: createSimpleDocumentPrivate.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento pai;
	 * - Descrição do documento;
	 * - Array de anexos do documento;
	 */
	public void createSimpleDocumentPrivate() throws Exception {
		System.out.println("\nMétodo createSimpleDocumentPrivate\n");

		// Este 'if' é utilizado para validar se o arquivo que foi utilizado para ser anexo do documento existe.
		if (this.getArchive() != null && this.getArchive().exists()){
			// Cria anexo do documento.
			if (!this.publicaViaFTP) {
				this.attachment.setFileName(this.getArchive().getName());
				this.attachment.setPrincipal(true);
				this.attachment.setFilecontent(this.arrayBytes);
			} else {
				// Anexa um arquivo do diretório de upload. Para isso, o arquivo deve estar no diretório
				// de upload do colaborador que vai publicar o documento.
				this.attachment.setFileName(this.nomeArquivo);
				this.attachment.setPrincipal(true);
				this.attachment.setFilecontent(null);
			}
		} else {
			System.out.println("Arquivo utilizado para anexo não existe!");
		}

		// Adiciona anexo no array de anexos.
		this.attachmentArray.getItem().add(this.attachment);

		// Cria documento simples na pasta particular.
		this.webServiceMessageArray = service.createSimpleDocumentPrivate(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroPastaParticular, this.descricaoDocumento, this.attachmentArray);

		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
			System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi publicado!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}

	/**
	 * Retorna os documentos mais acessados no ECM.
	 * 
	 * Método: findMostPopularDocuments.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Matrícula do colaborador;
	 * - Quantidade de documentos retornados na busca.
	 */
	public void findMostPopularDocuments() throws Exception {
		System.out.println("\nMétodo findMostPopularDocuments\n");

		// Retorna os documentos mais acessados no ECM.
		this.documentDtoArray = service.findMostPopularDocuments(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador, this.qtdeDocumentosBusca);

		// Mostra resultado.
		if (this.documentDtoArray.getItem().size() > 0) {
			for (int i = 0; i < this.documentDtoArray.getItem().size(); i++) {
				System.out.println("Número do documento: " + this.documentDtoArray.getItem().get(i).getDocumentId());
				System.out.println("Descrição do documento: " + this.documentDtoArray.getItem().get(i).getDocumentDescription());
				System.out.println("");
			}
		} else {
			System.out.println("Não foi encontrado nenhum documento!");
		}
	}
        
        /**
	 * Retorna os documentos mais acessados no ECM, paginados.
	 * 
	 * Método: findMostPopularDocumentsOnDemand.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Matrícula do colaborador;
	 * - Quantidade de documentos retornados na busca;
         * - Quantidade de registros a serem retornados;
         * - Última linha retornada.
	 */
	public void findMostPopularDocumentsOnDemand() throws Exception {
		System.out.println("\nMétodo findMostPopularDocumentsOnDemand\n");

		// Retorna os documentos mais acessados no ECM.
		this.documentDtoArray = service.findMostPopularDocumentsOnDemand(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador, this.matriculaColaborador, this.limite, this.ultimoRegistro);

		// Mostra resultado.
		if (this.documentDtoArray.getItem().size() > 0) {
			for (int i = 0; i < this.documentDtoArray.getItem().size(); i++) {
				System.out.println("Número do documento: " + this.documentDtoArray.getItem().get(i).getDocumentId());
				System.out.println("Descrição do documento: " + this.documentDtoArray.getItem().get(i).getDocumentDescription());
				System.out.println("");
			}
		} else {
			System.out.println("Não foi encontrado nenhum documento!");
		}
	}

	/**
	 * Exclui um documento e envia para a lixeira.
	 * 
	 * Método: deleteDocument.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Matríla do colaborador.
	 */
	public void deleteDocument() throws Exception {
		System.out.println("\nMétodo deleteDocument\n");

		// Verifica se documento existe.
		this.documentDtoArray = (DocumentDtoArray) service.getActiveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Se existe...
		if (this.documentDtoArray.getItem().get(0) != null) {

			// Exclui um documento e envia para a lixeira.
			//this.webServiceMessageArray = service.deleteDocument(this.loginColaborador, this.senhaColaborador, this.documentDtoArray.getItem().get(0).getCompanyId(), this.documentDtoArray.getItem().get(0).getDocumentId(), this.matriculaColaborador);

			// Mostra resultado.
			if (this.webServiceMessageArray != null && this.webServiceMessageArray.getItem().get(0).getWebServiceMessage().equalsIgnoreCase("ok")) {

				System.out.println("Documento " + this.documentDtoArray.getItem().get(0).getDocumentId() + " foi excluido!");
			} else {
				System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
			}
		} else {
			System.out.println("Documento " + this.numeroDocumento + " não foi encontrado!");
		}
	}

	/**
	 * Exclui um documento da lixeira.
	 * 
	 * Método: destroyDocument.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Matrícula do colaborador.
	 */
	public void destroyDocument() throws Exception {
		System.out.println("\nMétodo destroyDocument\n");

		// Exclui um documento da lixeira.
		this.webServiceMessageArray = service.destroyDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getDocumentId() != 0) {
			System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi excluido da lixeira!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}

	/**
	 * Retorna todos os documentos da lixeira de um colaborador.
	 * 
	 * Método: findRecycledDocuments.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Matrícula do colaborador.
	 */
	public void findRecycledDocuments() throws Exception {
		System.out.println("\nMétodo findRecycledDocuments\n");

		// Retorna todos os documentos da lixeira de um colaborador.
		this.documentDtoArray = service.findRecycledDocuments(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.matriculaColaborador);

		// Mostra resultado.
		if (!this.documentDtoArray.getItem().isEmpty()) {
			for (int i = 0; i < this.documentDtoArray.getItem().size(); i++) {
				System.out.println("Número do documento: " + this.documentDtoArray.getItem().get(i).getDocumentId());
				System.out.println("Descrição do documento: " + this.documentDtoArray.getItem().get(i).getDocumentDescription());
				System.out.println("");
			}
		} else {
			System.out.println("Não foi encontrado nenhum documento na lixeira!");
		}
	}

	/**
	 * Restaura um documento da lixeira.
	 * 
	 * Método: restoreDocument.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Matrícula do colaborador.
	 */
	public void restoreDocument() throws Exception {
		System.out.println("\nMétodo restoreDocument\n");

		// Restaura um documento da lixeira.
		this.webServiceMessageArray = service.restoreDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.matriculaColaborador);

		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getDocumentId() != 0) {
			System.out.println("Documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi restaurado!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}
	
	/**
	 * Move um documento para pasta destino.
	 * 
	 * Método: moveDocument.
	 * 
	 * Parâmetros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Código da empresa;
	 * - Número do documento;
	 * - Matrícula do colaborador;
	 * - Pasta destino.
	 */
	public void moveDocument() throws Exception {
		System.out.println("\nM�todo moveDocument\n");
		
		// Restaura um documento da lixeira.
		String message = service.moveDocument(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.arrayDocuments, this.matriculaColaborador, this.destFolder);

		System.out.println(message);
	}
	
	
	/**
	 * Modifica a tipo da segurança dos grupos, onde 0 - TODOS os colaboradores dos Grupos / 1 -SOMENTE colaboradores comuns dos grupos
	 * 
	 * Método: updateGroupSecurityType.
	 * 
	 * Parâtros:
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Cóo da empresa;
	 * - Núo do documento;
	 * - Versão do documento;
	 * - Tipo da permissão (0 - TODOS os colaboradores dos Grupos / 1 -SOMENTE colaboradores comuns dos grupos)
	 * - Ttipo da restrição (0 - TODOS os colaboradores dos Grupos / 1 -SOMENTE colaboradores comuns dos grupos)
	 * - Matrícula do colaborador.
	 */
	public void updateGroupSecurityType() throws Exception {
		System.out.println("\nMétodo updateGroupSecurityType\n");

		// Altera o tipo de segurança para grupo, onde 0 - TODOS os colaboradores dos Grupos / 1 -SOMENTE colaboradores comuns dos grupos
		this.webServiceMessageArray = service.updateGroupSecurityType(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento, this.permissionType, this.restrictionType, this.matriculaColaborador);

		// Mostra resultado.
		if (this.webServiceMessageArray.getItem().get(0).getDocumentId() != 0) {
			System.out.println("O tipo de segurança para grupos do documento " + this.webServiceMessageArray.getItem().get(0).getDocumentId() + " foi alterado!");
		} else {
			System.out.println(this.webServiceMessageArray.getItem().get(0).getWebServiceMessage());
		}
	}
	
	/**
	 * Retorna o status de aprovacao do documento
	 * 
	 * Parametros utilizados no web services:
	 * 
	 * @param user matricula do usuario de integracao
	 * @param password senha do usuario de integracao
	 * @param companyId codigo da empresa
	 * @param documentId codigo do documento que se deseja visualizar o status da aprovacao
	 * @param version versao do documento
	 */
	public void getDocumentApprovalStatus() throws Exception {
		System.out.println("\nMétodo getDocumentApprovalStatus\n");
		
		DocumentApprovalStatusDtoArray docAppStatusAr = service.getDocumentApprovalStatus(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento);
		
		if (docAppStatusAr != null) {
			DocumentApprovalStatusDto docAppStatus = docAppStatusAr.getItem().get(0);
			
			/**
			 * Status:
			 * 
			 * 0 - Aprovado
			 * 1 - Reprovado
			 * 2 - Em analise
			 */
			
			System.out.printf("Empresa=%s, Documento=%s, Versao=%s, Status=%s", docAppStatus.getCompanyId(), docAppStatus.getDocumentId(), docAppStatus.getVersion(), docAppStatus.getStatus());
		}
	}
	
	/**
	 * Retorna o historico de aprovacoes do documento
	 * 
	 * Parametros utilizados no web services:
	 * 
	 * @param user matricula do usuario de integracao
	 * @param password senha do usuario de integracao
	 * @param companyId codigo da empresa
	 * @param documentId codigo do documento que se deseja visualizar o status da aprovacao
	 * @param version versao do documento
	 */
	public void getDocumentApprovalHistory() throws Exception {
		System.out.println("\nMétodo getDocumentApprovalHistory\n");
		
		DocumentApprovementHistoryDtoArray docAppHisAr = service.getDocumentApprovalHistory(this.loginColaborador, this.senhaColaborador, this.codigoEmpresa, this.numeroDocumento, this.versaoDocumento);
		
		if (docAppHisAr != null) {
			
			
			List<DocumentApprovementHistoryDto> docAppHisList = docAppHisAr.getItem();
			
			for (DocumentApprovementHistoryDto docAppHis : docAppHisList) {
				
				/**
				 * Status:
				 * 
				 * 0 - Aprovado
				 * 1 - Reprovado
				 * 2 - Em analise
				 */
				
				System.out.printf("DocumentVersion=%s, IterationSequence=%s, LevelId=%s, MovementSequence=%s, ColleagueId=%s, Observation=%s, Signed=%s", docAppHis.getDocumentVersion(), docAppHis.getIterationSequence(), docAppHis.getLevelId(),
						docAppHis.getMovementSequence(), docAppHis.getColleagueId(), docAppHis.getObservation(), docAppHis.isSigned());
				
			}
				
		}
	}
	
	public void copyDocuments()  throws Exception {
			/* Código da Empresa */
			 int companyId = 1;
			 /* Login do usuário*/
	         String login = "user";
	         /* Senha */
	         String password = "password";
			 
			/* Id do usuário */
			String userId = "user";
	         
	         /* Documentos a serem copiados */
	         IntArray origins = new IntArray();
	         origins.getItem().add(40641);
	         origins.getItem().add(40643);
	         
	         /* Pasta de destino */
	         int destination = 40640;
			 
//			CopyDocumentDtoArray result = service.copyDocuments(companyId, login, password, userId, origins, destination);
//			 List<CopyDocumentDto> itens = result.getItem();
//	         
//	         for (CopyDocumentDto copyDocumentDto : itens) {
//	             System.out.println("Código Novo Documento: "+copyDocumentDto.getDocDestination());
//	         }
	}
	
}