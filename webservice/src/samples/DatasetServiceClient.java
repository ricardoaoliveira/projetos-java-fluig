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
 * Classe que utiliza todos os métodos de DatasetService.
 * Com essa classe, pode-se pesquisar os datasets existentes no ECM.
 * No método setParameters, pode-se setar algumas das variáveis que são mais utilizadas como parâmetros nos métodos desta classe.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class DatasetServiceClient {

	// Variáveis.
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
	
	// Inicia execução da classe.
	public static void main (String args[]) {
		System.out.println("\nClasse DatasetService");
		
		// Instancia classe DatasetServiceClient.
		DatasetServiceClient dsc = new DatasetServiceClient();
		
		// Configura acesso ao WebServices.
		BindingProvider bp = (BindingProvider) dsc.service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://192.168.15.11:8080/webdesk/DatasetService");
		
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
	 * Nesse método pode-se setar algumas das variáveis que são mais utilizadas como parâmetros nos métodos desta classe.
	 */
	public void setParameters() throws Exception {
		this.loginColaborador = "admin";
		this.senhaColaborador = "adm";
		this.codigoEmpresa = 1;
		this.nomeDataset = "ecm";
	}
	
	/**
	 * Escolhe método.
	 * Nesse método, pode-se escolher qual método da classe será executado.
	 */
	public void changeMethod() throws Exception {
		// Chama método getAvailableDatasets.
		//this.getAvailableDatasets();
		
		// Chama método getDataset.
		//this.getDataset();
	}
		
	/**
	 * Retorna todos os datasets disponíveis.
	 * 
	 * Método: getAvailableDatasets.
	 * 
	 * Parâmetros:
	 * - Código da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador.
	 */
	public void getAvailableDatasets() throws Exception {
		System.out.println("\nMétodo getAvailableDatasets\n");

		//Retorna todos os datasets disponíveis.
		this.anyTypeArray = service.getAvailableDatasets(this.codigoEmpresa, this.loginColaborador, this.senhaColaborador);
		
		// Mostra resultado.
		for (int i = 0; i < this.anyTypeArray.getItem().size(); i++) {
			System.out.println("Dataset: " + this.anyTypeArray.getItem().get(i));
		}
	}
	
	/**
	 * Retorna as informações de um dataset.
	 * 
	 * Método: getDataset.
	 * 
	 * Parâmetros:
	 * - Código da empresa;
	 * - Login do colaborador;
	 * - Senha do colaborador;
	 * - Nome do dataset;
	 * - Campos que serão retornados do dataset. (null = todos os campos);
	 * - Filtro dos registros que irão compor o dataset. (null = todos);
	 * - Campos utilizados para ordenar o dataset. (null = ordenação padrão).
	 */
	public void getDataset() throws Exception {
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
			System.out.println("Dataset não encontrado!");
		}
	}
}