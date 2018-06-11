package samples;

import javax.xml.ws.BindingProvider;

import net.java.dev.jaxb.array.AnyTypeArray;
import net.java.dev.jaxb.array.StringArray;
  
import com.datasul.technology.webdesk.dataservice.ws.DatasetDto;
import com.datasul.technology.webdesk.dataservice.ws.DatasetService;
import com.datasul.technology.webdesk.dataservice.ws.DatasetServiceService;
import com.datasul.technology.webdesk.dataservice.ws.SearchConstraintDto;
import com.datasul.technology.webdesk.dataservice.ws.SearchConstraintDtoArray;

/**
 * Classe que utiliza todos os m�todos de DatasetService.
 * Com essa classe, pode-se pesquisar os datasets existentes no ECM.
 * No m�todo setParameters, pode-se setar algumas das vari�veis que s�o mais utilizadas como par�metros nos m�todos desta classe.
 * No m�todo changeMethod, pode-se escolher qual m�todo ser� executado.
 */
public class DatasetServiceClient {

	// Vari�veis.
	String loginColaborador, senhaColaborador, nomeDataset;
	int codigoEmpresa;
	
	// Dto
	DatasetDto datasetDto = new DatasetDto();
	SearchConstraintDto searchConstraintDto = new SearchConstraintDto();
	
	// Array
	AnyTypeArray anyTypeArray = new AnyTypeArray();
	StringArray camposDataset = new StringArray();
	StringArray ordemDataset = new StringArray();
	SearchConstraintDtoArray searchConstraintDtoArray = new SearchConstraintDtoArray();
	
	// Instancia DatasetServiceService.
	DatasetServiceService datasetServiceService = new DatasetServiceService();
	DatasetService service = datasetServiceService.getDatasetServicePort();
	
	// Inicia execu��o da classe.
	public static void main (String args[]) {
		System.out.println("\nClasse DatasetService");
		
		// Instancia classe DatasetServiceClient.
		DatasetServiceClient dsc = new DatasetServiceClient();
		
		// Configura acesso ao WebServices.
		BindingProvider bp = (BindingProvider) dsc.service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://192.168.15.11:8080/webdesk/DatasetService");
		
		try {
			// Chama m�todo que configura os valores das vari�veis.
			dsc.setParameters();

			// Chama m�todo que � respons�vel por executar os m�todos da classe.
			dsc.changeMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configura par�metros.
	 * Nesse m�todo pode-se setar algumas das vari�veis que s�o mais utilizadas como par�metros nos m�todos desta classe.
	 */
	public void setParameters() throws Exception {
		this.loginColaborador = "admin";
		this.senhaColaborador = "adm";
		this.codigoEmpresa = 1;
		this.nomeDataset = "ecm";
	}
	
	/**
	 * Escolhe m�todo.
	 * Nesse m�todo, pode-se escolher qual m�todo da classe ser� executado.
	 */
	public void changeMethod() throws Exception {
		// Chama m�todo getAvailableDatasets.
		//this.getAvailableDatasets();
		
		// Chama m�todo getDataset.
		//this.getDataset();
	}
		
	/**
	 * Retorna todos os datasets dispon�veis.
	 * 
	 * M�todo: getAvailableDatasets.
	 * 
	 * Par�metros:
	 * - C�digo da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador.
	 */
	public void getAvailableDatasets() throws Exception {
		System.out.println("\nM�todo getAvailableDatasets\n");

		//Retorna todos os datasets dispon�veis.
		this.anyTypeArray = service.getAvailableDatasets(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador);
		
		// Mostra resultado.
		for (int i = 0; i < this.anyTypeArray.getItem().size(); i++) {
			System.out.println("Dataset: " + this.anyTypeArray.getItem().get(i));
		}
	}
	
	/**
	 * Retorna as informa��es de um dataset.
	 * 
	 * M�todo: getDataset.
	 * 
	 * Par�metros:
	 * - C�digo da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Nome do dataset;
	 * - Campos que ser�o retornados do dataset. (null = todos os campos);
	 * - Filtro dos registros que ir�o compor o dataset. (null = todos);
	 * - Campos utilizados para ordenar o dataset. (null = ordena��o padr�o).
	 */
	public void getDataset() throws Exception {
		System.out.println("\nM�todo getDataset\n");
		this.nomeDataset = "group";
		
		// Campos que ser�o retornados.
		this.camposDataset.getItem().add("groupDescription");
		this.camposDataset.getItem().add("groupPK.groupId");
		this.camposDataset.getItem().add("groupPK.companyId");

		// Cria filtros.
		this.searchConstraintDto.setContraintType("MUST");
		this.searchConstraintDto.setFieldName("groupDescription");
		this.searchConstraintDto.setFinalValue("z");
		this.searchConstraintDto.setInitialValue("a");		
		
		// Ordena��o do dataset.
		this.ordemDataset.getItem().add("groupDescription");

		// Adiciona filtros no array de filtros.
		this.searchConstraintDtoArray.getItem().add(this.searchConstraintDto);
		
		// Retorna as informa��es de um dataset.
		this.datasetDto = service.getDataset(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador, this.nomeDataset, 
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
			System.out.println("Dataset n�o encontrado!");
		}
	}
}