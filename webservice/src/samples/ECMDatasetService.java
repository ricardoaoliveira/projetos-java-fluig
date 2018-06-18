package samples;

import java.util.ArrayList;
import java.util.List;

import com.totvs.technology.ecm.dataservice.ws.DatasetDto;
import com.totvs.technology.ecm.dataservice.ws.ECMDatasetServiceService;
import com.totvs.technology.ecm.dataservice.ws.SearchConstraintDto;
import com.totvs.technology.ecm.dataservice.ws.SearchConstraintDtoArray;

import net.java.dev.jaxb.array.AnyTypeArray;
import net.java.dev.jaxb.array.StringArray;

public class ECMDatasetService {

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
    
    String nomeDataset = "group";
    
    DatasetDto datasetDto = new DatasetDto();
	SearchConstraintDto searchConstraintDto = new SearchConstraintDto();
	
	// Array
	AnyTypeArray anyTypeArray = new AnyTypeArray();
	StringArray camposDataset = new StringArray();
	StringArray ordemDataset = new StringArray();
	SearchConstraintDtoArray searchConstraintDtoArray = new SearchConstraintDtoArray();

    ECMDatasetServiceService datasetService = instanceDatasetService();
	    
    public static void main(String args[]) {
        System.out.println("\nIniciando ECMDatasetService");

        ECMDatasetService ECMDatasetService = new ECMDatasetService();

        try {
        	ECMDatasetService.changeMethod();
            System.out.println("\nTerminou ECMDatasetService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void changeMethod() throws Exception {
    	//this.getAvailableDatasets();
    	//this.getDatasetGroup();
    	this.getDatasetConfiguracaoValidadeDocumento();
    }
    
    public void getAvailableDatasets() throws Exception {
    	try {
    		AnyTypeArray anyTypeArray = datasetService.getDatasetServicePort().getAvailableDatasets(1, this.userLogin, this.userPassword);
    		System.out.println(anyTypeArray);
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }
    
    public void getDatasetGroup() throws Exception {
    	System.out.println("\nMétodo getDataset\n");
		this.nomeDataset = "group";
		
		// Campos que serão retornados.
		this.camposDataset.getItem().add("groupDescription");
		this.camposDataset.getItem().add("groupPK.groupId");
		this.camposDataset.getItem().add("groupPK.companyId");

		// Cria filtros.
		this.searchConstraintDto.setContraintType("MUST");
		this.searchConstraintDto.setFieldName("groupDescription");
		this.searchConstraintDto.setFinalValue("z");
		this.searchConstraintDto.setInitialValue("a");		
		
		// Ordenação do dataset.
		this.ordemDataset.getItem().add("groupDescription");

		// Adiciona filtros no array de filtros.
		this.searchConstraintDtoArray.getItem().add(this.searchConstraintDto);
		
		// Retorna as informações de um dataset.
		this.datasetDto = datasetService.getDatasetServicePort().getDataset(1, this.userLogin, this.userPassword, this.nomeDataset, 
				this.camposDataset, this.searchConstraintDtoArray, this.ordemDataset);

		// Mostra resultado.
		if (this.datasetDto != null) {
			
			String coluna = "";
			
			// Retorna nome das colunas.
			for (int i = 0; i < this.datasetDto.getColumns().size(); i++) {
				coluna += this.datasetDto.getColumns().get(i) + "     ";
			}
			
			System.out.println("Dataset: " + this.nomeDataset);
			System.out.println("Colunas: " + coluna);
			System.out.println("Valores: ");
			
			// Retorna valores.
			for (int j = 0; j < this.datasetDto.getValues().size(); j++) {
				System.out.println("	" + this.datasetDto.getValues().get(j).getValue());
			}
			
			System.out.println("");
		} else {
			System.out.println("Dataset não encontrado!");
		}
    }
    
    public List<ConfiguracaoValidadeDocumentoVO> getDatasetConfiguracaoValidadeDocumento() throws Exception {
    	List<ConfiguracaoValidadeDocumentoVO> result = new ArrayList<ConfiguracaoValidadeDocumentoVO>();
    	
    	ConfiguracaoValidadeDocumentoVO item = null;
    	
    	System.out.println("\nMétodo getDataset\n");
		this.nomeDataset = "dsConfiguracao_Validade_Documento";
		
		// Campos que serão retornados.
		this.camposDataset.getItem().add("cod_doc");
		this.camposDataset.getItem().add("expira");
		this.camposDataset.getItem().add("validade");

		// Cria filtros.
		this.searchConstraintDto.setContraintType("MUST");
		this.searchConstraintDto.setFieldName("expira");
		this.searchConstraintDto.setFinalValue("on");
		this.searchConstraintDto.setInitialValue("on");		
		
		// Ordenação do dataset.
		this.ordemDataset.getItem().add("cod_doc");

		// Adiciona filtros no array de filtros.
		//this.searchConstraintDtoArray.getItem().add(this.searchConstraintDto);
		
		// Retorna as informações de um dataset.
		this.datasetDto = datasetService.getDatasetServicePort().getDataset(1, this.userLogin, this.userPassword, this.nomeDataset, 
				this.camposDataset, this.searchConstraintDtoArray, this.ordemDataset);

		// Mostra resultado.
		if (this.datasetDto != null) {
			
			String coluna = "";
			
			// Retorna nome das colunas.
			for (int i = 0; i < this.datasetDto.getColumns().size(); i++) {
				coluna += this.datasetDto.getColumns().get(i) + "     ";
			}
			
			System.out.println("Dataset: " + this.nomeDataset);
			System.out.println("Colunas: " + coluna);
			System.out.println("Valores: ");
			
			ECMDocumentService ecmDocumentService = new ECMDocumentService();
			
			// Retorna valores.
			for (int j = 0; j < this.datasetDto.getValues().size(); j++) {
				System.out.println("	" + this.datasetDto.getValues().get(j).getValue());
				List<Object> arrayObject = this.datasetDto.getValues().get(j).getValue();
				String cod_doc = (String) arrayObject.get(6);
				String expira = (String) arrayObject.get(8);
				String validade = (String) arrayObject.get(10);
				
				item = new ConfiguracaoValidadeDocumentoVO();
				item.setCod_doc(cod_doc);
				item.setExpira(expira);
				item.setValidade(validade);
				
				result.add(item);
				
				ecmDocumentService.updateDocumentGED(Integer.valueOf(cod_doc), expira, validade);	
				
				System.out.println("cod_doc: " + cod_doc + ", expira: " + expira + ", validade: " + validade);
			}
			
			System.out.println("");
		} else {
			System.out.println("Dataset não encontrado!");
		}
		
		return result;
    }
    
    private ECMDatasetServiceService instanceDatasetService() {
        return new ECMDatasetServiceService();
    }
    
}
