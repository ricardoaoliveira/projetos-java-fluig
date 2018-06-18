package samples;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.dm.ws.ApproverDtoArray;
import com.totvs.technology.ecm.dm.ws.Attachment;
import com.totvs.technology.ecm.dm.ws.AttachmentArray;
import com.totvs.technology.ecm.dm.ws.DocumentDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentService;
import com.totvs.technology.ecm.dm.ws.ECMDocumentServiceService;
import com.totvs.technology.ecm.dm.ws.RelatedDocumentDtoArray;
import com.totvs.technology.ecm.dm.ws.WebServiceMessage;
import com.totvs.technology.ecm.dm.ws.WebServiceMessageArray;

public class ECMDocumentService {

	String fluigURL = "http://mgwativosgestaoeadmi3732.fluig.cloudtotvs.com.br";
    String userId = "admin";
    String userLogin = "admin";
    String userPassword = "Mf4UEuac";
    String processId = "BPM-TESTE-Simples";
    int tenantId = 1;
    int nrDocument = 1459;
    int nrVersao = 1000;
    int limit = 10;
    int lastRowId = 0;

    DocumentService documentService = instanceDocumentService();
	    
    public static void main(String args[]) {
        System.out.println("\nIniciando ECMFavoritesService");

        ECMDocumentService ECMDocumentService = new ECMDocumentService();

        try {
        	ECMDocumentService.accessConfig();
        	ECMDocumentService.changeMethod();
            System.out.println("\nTerminou ECMFavoritesService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeMethod() throws Exception {
    	//this.getActiveDocument();
    	this.updateDocument();
    	//this.createSimpleDocument();
    }
    
    public void getActiveDocument() throws Exception {
        System.out.println("\nIniciando getActiveDocument()\n");

        try {
            DocumentDtoArray result = documentService.getActiveDocument(this.userId, this.userPassword, 1, this.nrDocument, this.userId); 

            System.out.println("result: " + result);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nTerminou getActiveDocument()");
    }
    
    public void updateDocument() throws Exception {
    	
    	try {
    		DocumentDtoArray result = documentService.getActiveDocument(this.userId, this.userPassword, 1, 1459, this.userId); 

    		int version = result.getItem().get(0).getVersion();
    		String fileName = result.getItem().get(0).getDocumentDescription();
    		
    		result.getItem().get(0).setExpires(false);
    		
    		GregorianCalendar c = new GregorianCalendar();
    		c.setTime(new Date());
    		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    		date2.setMonth(10);
    		
    		result.getItem().get(0).setExpirationDate(date2);
    		
    		//DocumentDtoArray documentDtoArray = new DocumentDtoArray();
    		
    		Attachment attachment = new Attachment();
        	
    		byte[] content = documentService.getDocumentContent(this.userId, this.userPassword, 1, this.nrDocument, this.userId, version, fileName);
    		attachment.setFilecontent( content );
    		
//        	File file = new File("C:\\temp\\Documento.docx");
//        	if(file.exists()){
//    			try{
//    				byte[] buffer = new byte[8192];
//    				FileInputStream fis = new FileInputStream(file);
//    				
//    				@SuppressWarnings("resource")
//					BufferedInputStream bis = new BufferedInputStream(fis, 8192);
//
//    				ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.length());
//    				int len = 0;
//    				while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
//    					baos.write(buffer, 0, len);
//    				}
//
//    				attachment.setFilecontent( baos.toByteArray() );
//    			}catch(Exception e){
//    				e.printStackTrace();
//    			}
//    		}else{
//    			System.out.println("Arquivo " + file.getName() + " não encontrado.");
//    		}
        	
        	attachment.setFileName(result.getItem().get(0).getDocumentDescription());
        	attachment.setPrincipal(true);
        	
        	AttachmentArray attachmentArray = new AttachmentArray();
        	attachmentArray.getItem().add(attachment);
    		
    		DocumentSecurityConfigDtoArray documentSecurityConfigDtoArray = new DocumentSecurityConfigDtoArray();
    		ApproverDtoArray approverDtoArray = new ApproverDtoArray();
    		RelatedDocumentDtoArray relatedDocumentDtoArray = new RelatedDocumentDtoArray();
    		
    		WebServiceMessageArray webServiceMessageArray = documentService.updateDocument(this.userId, this.userPassword, 1, result, attachmentArray, documentSecurityConfigDtoArray, approverDtoArray, relatedDocumentDtoArray);
    		
    		// Mostra resultado.
			if (webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
				System.out.println("Documento " + webServiceMessageArray.getItem().get(0).getDocumentId() + " foi atualizado!");
			} else {
				System.out.println(webServiceMessageArray.getItem().get(0).getWebServiceMessage());
			}
    		
            System.out.println("result: " + result);
            
    	} catch(Exception e) {
    		System.out.println("Error: " + e.getMessage());
    	}
    }
    
    public boolean isInteger(String value) {
    	
    	try {
    		Integer.valueOf(value);
    	} catch(NumberFormatException nfe) {
    		return false;
    	}
    	
    	return true;
    }
    
    public void updateDocumentGED(Integer cod_doc, String expira, String validade) throws Exception {
    	
    	try {
    		DocumentDtoArray result = documentService.getActiveDocument(this.userId, this.userPassword, 1, cod_doc, this.userId); 

    		int version = result.getItem().get(0).getVersion();
    		String fileName = result.getItem().get(0).getDocumentDescription();
    		
    		if (expira != null && "on".equalsIgnoreCase(expira) && isInteger(validade)) {
    			result.getItem().get(0).setExpires(true);
    			
    			Calendar now = Calendar.getInstance();
        		now.setTime(result.getItem().get(0).getCreateDate().toGregorianCalendar().getTime());
        		now.add(Calendar.DAY_OF_YEAR, Integer.valueOf(validade));
        		now.set(Calendar.MINUTE, 0);
        		now.set(Calendar.SECOND, 0);
        		now.set(Calendar.MILLISECOND, 0);
        		now.set(Calendar.HOUR_OF_DAY, 0);
        		
        		GregorianCalendar gc = new GregorianCalendar();
        		gc.setTime(now.getTime());
        		XMLGregorianCalendar xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        		
        		result.getItem().get(0).setExpirationDate(xmlGc);
    			
    		} else {
    			result.getItem().get(0).setExpires(false);	
    		}
    		
    		Attachment attachment = new Attachment();
        	
    		byte[] content = documentService.getDocumentContent(this.userId, this.userPassword, 1, cod_doc, this.userId, version, result.getItem().get(0).getPhisicalFile());
    		attachment.setFilecontent( content );
        	
        	attachment.setFileName(result.getItem().get(0).getDocumentDescription());
        	attachment.setPrincipal(true);
        	
        	AttachmentArray attachmentArray = new AttachmentArray();
        	attachmentArray.getItem().add(attachment);
    		
    		DocumentSecurityConfigDtoArray documentSecurityConfigDtoArray = new DocumentSecurityConfigDtoArray();
    		ApproverDtoArray approverDtoArray = new ApproverDtoArray();
    		RelatedDocumentDtoArray relatedDocumentDtoArray = new RelatedDocumentDtoArray();
    		
    		WebServiceMessageArray webServiceMessageArray = documentService.updateDocument(this.userId, this.userPassword, 1, result, attachmentArray, documentSecurityConfigDtoArray, approverDtoArray, relatedDocumentDtoArray);
    		
    		// Mostra resultado.
			if (webServiceMessageArray.getItem().get(0).getDocumentId() > 0) {
				System.out.println("Documento " + webServiceMessageArray.getItem().get(0).getDocumentId() + " foi atualizado!");
			} else {
				System.out.println(webServiceMessageArray.getItem().get(0).getWebServiceMessage());
			}
    		
            System.out.println("result: " + result);
            
    	} catch(Exception e) {
    		System.out.println("Error: " + e.getMessage());
    	}
    }
    
    public void createSimpleDocument() throws Exception {
        System.out.println("\nIniciando createSimpleDocument()\n");

        try {

        	String username = "admin";
        	String password = "Mf4UEuac";
        	int companyId = 1;
        	int parentDocumentId = 1394;
        	String publisherIr = "admin";
        	String documentDescription = "Teste";
        	
        	Attachment attachment = new Attachment();
        	
        	File file = new File("C:\\temp\\Documento.docx");
        	if(file.exists()){
    			try{
    				byte[] buffer = new byte[8192];
    				FileInputStream fis = new FileInputStream(file);
    				
    				@SuppressWarnings("resource")
					BufferedInputStream bis = new BufferedInputStream(fis, 8192);

    				ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.length());
    				int len = 0;
    				while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
    					baos.write(buffer, 0, len);
    				}

    				attachment.setFilecontent( baos.toByteArray() );
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    		}else{
    			System.out.println("Arquivo " + file.getName() + " não encontrado.");
    		}
        	
        	attachment.setFileName(file.getName());
        	attachment.setPrincipal(true);
        	
        	AttachmentArray attachmentArray = new AttachmentArray();
        	attachmentArray.getItem().add(attachment);
        	
        	WebServiceMessageArray response = documentService.createSimpleDocument(
        			username, password, companyId, parentDocumentId, publisherIr, documentDescription, attachmentArray);
            
        	if (response != null) {
        		
        		if (response.getItem() != null && !response.getItem().isEmpty()) {
        			for (WebServiceMessage webServiceMessage : response.getItem()) {
        				System.out.println(webServiceMessage);
        				if (webServiceMessage.getWebServiceMessage().equalsIgnoreCase("ok")) {
        					int documentId = webServiceMessage.getDocumentId();
        					int version = webServiceMessage.getVersion();
        				}
        			}
        		}
        		
        		System.out.println(response);
        	}
        	
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nTerminou createSimpleDocument()");
    }
    
    private void accessConfig() {
        BindingProvider bp = (BindingProvider) this.documentService;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.fluigURL + "/webdesk/ECMDocumentService");
    }
    
    private DocumentService instanceDocumentService() {
        return new ECMDocumentServiceService().getDocumentServicePort();
    }
    
}
