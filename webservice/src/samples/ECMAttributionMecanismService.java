package samples;

import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.foundation.ws.AttributionMecanismDto;
import com.totvs.technology.ecm.foundation.ws.AttributionMecanismDtoArray;
import com.totvs.technology.ecm.foundation.ws.AttributionMecanismService;
import com.totvs.technology.ecm.foundation.ws.ECMAttributionMecanismServiceService;

/**
 * Classe que utiliza todos os métodos de ECMAttributionMecanismService.
 * Com essa classe, pode-se pesquisar os mecanismos de atribuição cadastros no fluig.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class ECMAttributionMecanismService {
    String fluigURL = "http://10.80.81.238:8080";
    String userLogin = "admin.fluig";
    String userPassword = "adm";
    int tenantId = 123;

    AttributionMecanismService attributionMecanismService = instanceAttributionMecanismService();

    public static void main(String args[]) {
        System.out.println("\nIniciando ECMAttributionMecanismService");

        ECMAttributionMecanismService ECMAttributionMecanismService = new ECMAttributionMecanismService();

        try {
            ECMAttributionMecanismService.accessConfig();
            ECMAttributionMecanismService.changeMethod();
            System.out.println("\nTerminou ECMAttributionMecanismService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMethod() throws Exception {
        this.getAttributionMecanism();
    }

    /**
     * Retorna informações dos mecanismos de atribuição.
     * 
     * Método: getAttributionMecanism.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     */
    public void getAttributionMecanism() throws Exception {
        System.out.println("\nIniciando getAttributionMecanism()");

        AttributionMecanismDtoArray mecanismDtoArray = attributionMecanismService.getAttributionMecanism(this.userLogin,
                this.userPassword, this.tenantId);

        if (!mecanismDtoArray.getItem().isEmpty()) {
            for (AttributionMecanismDto mecanismDto : mecanismDtoArray.getItem()) {
                System.out.println("\nCódigo: " + mecanismDto.getAttributionMecanismId());
                System.out.println("Descrição: " + mecanismDto.getAttributionMecanismDescription());
            }
        } else {
            System.out.println("Não foi encontrado nenhum mecanismo de atribuição!");
        }
        System.out.println("\nTerminou getAttributionMecanism()");
    }

    private void accessConfig() {
        BindingProvider bp = (BindingProvider) this.attributionMecanismService;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.fluigURL + "/webdesk/ECMAttributionMecanismService");
    }

    private AttributionMecanismService instanceAttributionMecanismService() {
        return new ECMAttributionMecanismServiceService().getAttributionMecanismServicePort();
    }
}
