package com.fluig.sample.services.test;

import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.totvs.technology.ecm.dm.ws.ApproverDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentDto;
import com.totvs.technology.ecm.dm.ws.DocumentDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDtoArray;
import com.totvs.technology.ecm.dm.ws.ECMFolderServiceService;
import com.totvs.technology.ecm.dm.ws.Exception_Exception;
import com.totvs.technology.ecm.dm.ws.FolderService;
import com.totvs.technology.ecm.dm.ws.WebServiceMessageArray;

public class SampleFolderTest {

	private String url = "http://mgwativosgestaoeadmi3732.fluig.cloudtotvs.com.br:80";
	
	public static void main(String args[]) throws MalformedURLException {
		SampleFolderTest sampleFolderTest = new SampleFolderTest();
		sampleFolderTest.test();
	}
	
	public void test() throws MalformedURLException {
		
		int parent = 1461;

        String folderName = "new-folder-Soap";
        
        String user = "admin";

        String password = "Mf4UEuac";
        
        int tenantId = 1;
        
        ECMFolderServiceService service = new ECMFolderServiceService(getURLws("ECMFolderService"));
        
        FolderService folderService = service.getFolderServicePort();
        
        DocumentDto folder = new DocumentDto();
        folder.setDocumentDescription(folderName);
        folder.setParentDocumentId(parent);
        folder.setColleagueId(user);
        folder.setPublisherId(user);
        folder.setCompanyId(tenantId);
        folder.setDocumentType("1");
        folder.setPrivateDocument(false);
        folder.setIconId(1);
        
        DocumentDtoArray documentDtoArray = new DocumentDtoArray();	
		documentDtoArray.getItem().add(folder);
		
		Gson gson = new Gson();
		String jsonMessage = gson.toJson(folder);
        
		try {
        	WebServiceMessageArray resultCreate = folderService.createFolder(user, password, tenantId, documentDtoArray, new DocumentSecurityConfigDtoArray(), new ApproverDtoArray());
        	String jsonResultCreate = gson.toJson(resultCreate);
            System.out.println("{\"content\":" + jsonResultCreate + ", \"message\":"+jsonMessage+"}");
        } catch (Exception_Exception e) {
        	 System.out.println("{\"content\":" + e.getMessage() + ", \"message\":"+jsonMessage+"}");
		}
		
	}
	
	public URL getURLws(String service) throws MalformedURLException{
		String wsURL = this.url+"/webdesk/"+service+"?wsdl";
		return new URL(wsURL);
	}

}
