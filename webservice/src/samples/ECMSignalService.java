package samples;

import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.workflow.ws.Signal;
import com.totvs.technology.ecm.workflow.ws.SignalArray;
import com.totvs.technology.ecm.workflow.ws.SignalService;

/**
 * Classe que utiliza todos os métodos da ECMSignalService.
 * Com essa classe pode-se criar, exibir, emitir e deletar sinais.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class ECMSignalService {
    String fluigURL = "http://10.80.81.238:8080";
    String userLogin = "admin.fluig";
    String userPassword = "adm";
    String signalDescription = "fluig";
    int tenantId = 123;
    int sinalId = 5;

    SignalService signalService = instanceSignalService();

    public static void main(String args[]) {
        System.out.println("\nIniciando ECMSignalService");

        ECMSignalService ECMSignalService = new ECMSignalService();

        try {
            ECMSignalService.accessConfig();
            ECMSignalService.changeMethod();
            System.out.println("\nTerminou ECMSignalService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMethod() throws Exception {
        this.createSignal();
        //this.deleteSignal();
        //this.fireSignal();
        //this.getSignals();
    }
    
    /**
     * Cria um sinal.
     * 
     * Método: createSignal.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Descrição do Sinal
     */
    public void createSignal() throws Exception {
        System.out.println("\nIniciando createSignal()\n");

        String result = signalService.createSignal(this.userLogin,
                this.userPassword, this.tenantId, this.signalDescription);

        if (result.equals("ok")) {
            System.out.println("Sinal criado!");
        } else {
            System.out.println("Sinal não criado!");
        }

        System.out.println("\nTerminou createSignal()");
    }
    
    /**
     * Deleta um sinal.
     * 
     * Método: deleteSignal.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do Sinal
     */
    public void deleteSignal() throws Exception {
        System.out.println("\nIniciando deleteSignal()\n");

        String result = signalService.deleteSignal(this.userLogin,
                this.userPassword, this.tenantId, this.sinalId);

        if (result.equals("ok")) {
            System.out.println("Sinal deletado!");
        } else {
            System.out.println("Sinal não deletado!");
        }

        System.out.println("\nTerminou deleteSignal()");
    }
    
    /**
     * Dispara um sinal.
     * 
     * Método: fireSignal.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do Sinal
     */
    public void fireSignal() throws Exception {
        System.out.println("\nIniciando fireSignal()\n");

        String result = signalService.fireSignal(this.userLogin,
                this.userPassword, this.tenantId, this.sinalId);

        if (result.equals("ok")) {
            System.out.println("Sinal disparado!");
        } else {
            System.out.println("Sinal não disparado!");
        }

        System.out.println("\nTerminou fireSignal()");
    }
    
    /**
     * Retorna todos os sinais cadastrados.
     * 
     * Método: getSignals.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     */
    public void getSignals() throws Exception {
        System.out.println("\nIniciando getSignals()\n");

        SignalArray signalArray = signalService.getSignals(this.userLogin, this.userPassword, this.tenantId);

        for (Signal signal : signalArray.getItem()) {
            System.out.println("Sinal " + signal.getSignalPK().getSignalId() + " - " + signal.getDescription());
        }

        if (signalArray.getItem().isEmpty()) {
            System.out.println("Nenhum Sinal encontrado!");
        }

        System.out.println("\nTerminou getSignals()");
    }

    private void accessConfig() {
        BindingProvider bp = (BindingProvider) this.signalService;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.fluigURL + "/webdesk/ECMSignalService");
    }

    private SignalService instanceSignalService() {
        return new com.totvs.technology.ecm.workflow.ws.ECMSignalService().getSignalServicePort();
    }
}
