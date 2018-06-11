package samples;

import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.dm.ws.DocumentDto;
import com.totvs.technology.ecm.dm.ws.DocumentDtoArray;
import com.totvs.technology.ecm.dm.ws.ECMFavoritesServiceService;
import com.totvs.technology.ecm.dm.ws.FavoritesService;
import com.totvs.technology.ecm.dm.ws.ProcessDefinitionVersionDto;
import com.totvs.technology.ecm.dm.ws.ProcessDefinitionVersionDtoArray;

/**
 * Classe que utiliza todos os métodos de ECMFavoritesService.
 * Com essa classe, pode-se adicionar, remover e pesquisar Documentos e Processos favoritos.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class ECMFavoritesService {
    String fluigURL = "http://192.168.15.11:8080";
    String userId = "admin";
    String userLogin = "admin";
    String userPassword = "adm";
    String processId = "BPM-TESTE-Simples";
    int tenantId = 1;
    int nrDocument = 1092;
    int nrVersao = 1000;
    int limit = 10;
    int lastRowId = 0;

    FavoritesService favoritesService = instanceFavoritesService();

    public static void main(String args[]) {
        System.out.println("\nIniciando ECMFavoritesService");

        ECMFavoritesService ECMFavoritesService = new ECMFavoritesService();

        try {
            ECMFavoritesService.accessConfig();
            ECMFavoritesService.changeMethod();
            System.out.println("\nTerminou ECMFavoritesService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMethod() throws Exception {
        //this.addDocumentToFavorites();
        //this.addProcessToFavorites();
        this.findFavorites();
        //this.findFavoritesOnDemand();
        //this.findFavoritesProcess();
        //this.findFavoritesProcessOnDemand();
        //this.isFavoriteDocument();
        //this.removeFavoriteDocument();
        //this.removeFavoriteProcess();
    }

    /**
     * Adiciona um documento como favorito
     * 
     * Método: addDocumentToFavorites.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do documento;
     * - Número da versão do documento;
     * - Código do usuário; 
     */
    public void addDocumentToFavorites() throws Exception {
        System.out.println("\nIniciando addDocumentToFavorites()\n");

        try {
            favoritesService.addDocumentToFavorites(this.userLogin, this.userPassword,
                    this.tenantId, this.nrDocument, this.nrVersao, this.userId);
            System.out.println("Documento " + this.nrDocument + " adicionado como favorito!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nTerminou addDocumentToFavorites()");
    }
    
    /**
     * Adiciona um processo como favorito
     * 
     * Método: addProcessToFavorites.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     * - Código do usuário;
     */
    public void addProcessToFavorites() throws Exception {
        System.out.println("\nIniciando addProcessToFavorites()\n");

        try {
            favoritesService.addProcessToFavorites(this.userLogin, this.userPassword,
                    this.tenantId, this.processId, this.userId);
            System.out.println("Processo " + this.processId + " adicionado como favorito!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nTerminou addProcessToFavorites()");
    }
    
    /**
     * Retorna os documentos favoritos
     * 
     * Método: findFavorites.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     */
    public void findFavorites() throws Exception {
        System.out.println("\nIniciando findFavorites()\n");

        DocumentDtoArray documentArray = favoritesService.findFavorites(this.userLogin, this.userPassword,
                this.tenantId, this.userId);

        for (DocumentDto document : documentArray.getItem()) {
            if (document != null) {
                System.out.println("Documento " + document.getDocumentId() + " - " + document.getDocumentDescription());
            }
        }

        if (documentArray.getItem().isEmpty()) {
            System.out.println("Nenhum documento favorito encontrado!");
        }

        System.out.println("\nTerminou findFavorites()");
    }
    
    /**
     * Retorna os documentos favoritos, paginados.
     * 
     * Método: findFavoritesOnDemand.
     * 
     * Parâmetros:
     * - Código da empresa;
     * - Login do usuário;
     * - Senha do usuário;
     * - Código do usuário;
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada;
     */
    public void findFavoritesOnDemand() throws Exception {
        System.out.println("\nIniciando findFavoritesOnDemand()\n");

        DocumentDtoArray documentArray = favoritesService.findFavoritesOnDemand(this.tenantId, this.userLogin,
                this.userPassword, this.userId, this.limit, this.lastRowId);

        for (DocumentDto document : documentArray.getItem()) {
            if (document != null) {
                System.out.println("Documento " + document.getDocumentId() + " - " + document.getDocumentDescription());
            }
        }

        if (documentArray.getItem().isEmpty()) {
            System.out.println("Nenhum documento favorito encontrado!");
        }

        System.out.println("\nTerminou findFavoritesOnDemand()");
    }
    
    /**
     * Retorna os processos favoritos.
     * 
     * Método: findFavoritesProcess.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     */
    public void findFavoritesProcess() throws Exception {
        System.out.println("\nIniciando findFavoritesProcess()\n");

        ProcessDefinitionVersionDtoArray processArray = favoritesService.findFavoritesProcess(this.userLogin,
                this.userPassword, this.tenantId, this.userId);

        for (ProcessDefinitionVersionDto process : processArray.getItem()) {
            System.out.println("Processo: " + process.getProcessDescription());
        }

        if (processArray.getItem().isEmpty()) {
            System.out.println("Nenhum processo favorito encontrado!");
        }

        System.out.println("\nTerminou findFavoritesProcess()");
    }
    
    /**
     * Retorna os processos favoritos, paginados.
     * 
     * Método: findFavoritesProcessOnDemand.
     * 
     * Parâmetros:
     * - Código da empresa;
     * - Login do usuário;
     * - Senha do usuário;
     * - Código do usuário;
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada;
     */
    public void findFavoritesProcessOnDemand() throws Exception {
        System.out.println("\nIniciando findFavoritesProcessOnDemand()\n");

        ProcessDefinitionVersionDtoArray processArray = favoritesService.findFavoritesProcessOnDemand(this.tenantId,
                this.userLogin, this.userPassword, this.userId, this.limit, this.lastRowId);

        for (ProcessDefinitionVersionDto process : processArray.getItem()) {
            System.out.println("Processo: " + process.getProcessDescription());
        }

        if (processArray.getItem().isEmpty()) {
            System.out.println("Nenhum processo favorito encontrado!");
        }

        System.out.println("\nTerminou findFavoritesProcessOnDemand()");
    }
    
    /**
     * Retorna se o documento é ou não favorito.
     * 
     * Método: isFavoriteDocument.
     * 
     * Parâmetros:
     * - Código da empresa;
     * - Login do usuário;
     * - Senha do usuário;
     * - Código do documento
     * - Código do usuário;
     */
    public void isFavoriteDocument() throws Exception {
        System.out.println("\nIniciando isFavoriteDocument()\n");

        boolean isFavoriteDocument = favoritesService.isFavoriteDocument(this.userLogin,
                this.userPassword, this.tenantId, this.nrDocument, this.userId);

        if (isFavoriteDocument) {
            System.out.println("Documento " + this.nrDocument + " está favoritado!");
        } else {
            System.out.println("Documento " + this.nrDocument + " não está favoritado!");
        }

        System.out.println("\nTerminou isFavoriteDocument()");
    }
    
    /**
     * Remove o documento dos favoritos.
     * 
     * Método: removeFavoriteDocument.
     * 
     * Parâmetros:
     * - Código da empresa;
     * - Login do usuário;
     * - Senha do usuário;
     * - Código do documento
     * - Código do usuário;
     */
    public void removeFavoriteDocument() throws Exception {
        System.out.println("\nIniciando removeFavoriteDocument()\n");

        try {
            favoritesService.removeFavoriteDocument(this.userLogin,
                    this.userPassword, this.tenantId, this.nrDocument, this.userId);
            System.out.println("Documento " + this.nrDocument + " não é mais favorito!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nTerminou removeFavoriteDocument()");
    }
    
    /**
     * Remove o documento dos favoritos.
     * 
     * Método: removeFavoriteProcess.
     * 
     * Parâmetros:
     * - Código da empresa;
     * - Login do usuário;
     * - Senha do usuário;
     * - Código do processo
     * - Código do usuário;
     */
    public void removeFavoriteProcess() throws Exception {
        System.out.println("\nIniciando removeFavoriteProcess()\n");

        try {
            favoritesService.removeFavoriteProcess(this.userLogin,
                    this.userPassword, this.tenantId, this.processId, this.userId);
            System.out.println("Processo " + this.processId + " não é mais favorito!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nTerminou removeFavoriteProcess()");
    }

    private void accessConfig() {
        BindingProvider bp = (BindingProvider) this.favoritesService;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.fluigURL + "/webdesk/ECMFavoritesService");
    }

    private FavoritesService instanceFavoritesService() {
        return new ECMFavoritesServiceService().getFavoritesServicePort();
    }
}
