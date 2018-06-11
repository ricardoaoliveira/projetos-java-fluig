package com.fluig.sample.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.totvs.technology.ecm.dm.ws.ApproverDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentDto;
import com.totvs.technology.ecm.dm.ws.DocumentDtoArray;
import com.totvs.technology.ecm.dm.ws.DocumentSecurityConfigDtoArray;
import com.totvs.technology.ecm.dm.ws.ECMFolderServiceService;
import com.totvs.technology.ecm.dm.ws.Exception_Exception;
import com.totvs.technology.ecm.dm.ws.FolderService;
import com.totvs.technology.ecm.dm.ws.WebServiceMessageArray;

public class SampleFolderServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String url = "http://mgwativosgestaoeadmi3732.fluig.cloudtotvs.com.br:80";
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("application/json; charset=UTF-8");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();

        int parent = 1461;
        if (request.getParameter("parent") != null) {
            parent = Integer.valueOf(request.getParameter("parent"));
        }

        String folderName = "new-folder-Soap";
        if (request.getParameter("foldername") != null) {
        	folderName = request.getParameter("foldername");
        }
        
        String user = "admin";
        if (request.getParameter("user") != null) {
        	user = request.getParameter("user");
        }

        String password = "Mf4UEuac";
        if (request.getParameter("password") != null) {
        	password = request.getParameter("password");
        }
        
        int tenantId = 1;
        if (request.getParameter("tenantid") != null) {
        	tenantId = Integer.valueOf(request.getParameter("tenantid"));
        }
        
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
            out.println("{\"content\":" + jsonResultCreate + ", \"message\":"+jsonMessage+"}");
        } catch (Exception_Exception e) {
        	 response.setStatus(500);
        	 out.println("{\"content\":" + e.getMessage() + ", \"message\":"+jsonMessage+"}");
		}
  
    }
    
	public URL getURLws(String service) throws MalformedURLException{
		String wsURL = this.url+"/webdesk/"+service+"?wsdl";
		return new URL(wsURL);
	}
    
}
