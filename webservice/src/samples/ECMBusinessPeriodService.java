package samples;

import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.foundation.ws.BusinessPeriodDto;
import com.totvs.technology.ecm.foundation.ws.BusinessPeriodDtoArray;
import com.totvs.technology.ecm.foundation.ws.BusinessPeriodInfoDto;
import com.totvs.technology.ecm.foundation.ws.BusinessPeriodService;
import com.totvs.technology.ecm.foundation.ws.ECMBusinessPeriodServiceService;

/**
 * Classe que utiliza todos os métodos de ECMBusinessPeriodService.
 * Com essa classe, pode-se pesquisar os expedientes cadastrados no fluig.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class ECMBusinessPeriodService {
    String fluigURL = "http://10.80.81.238:8080";
    String userLogin = "admin.fluig";
    String userPassword = "adm";
    int tenantId = 123;

    BusinessPeriodService businessPeriodService = instanceBusinessPeriodService();

    public static void main(String args[]) {
        System.out.println("\nIniciando ECMBusinessPeriodService");

        ECMBusinessPeriodService ECMBusinessPeriodService = new ECMBusinessPeriodService();

        try {
            ECMBusinessPeriodService.accessConfig();
            ECMBusinessPeriodService.changeMethod();
            System.out.println("\nTerminou ECMBusinessPeriodService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMethod() throws Exception {
        this.getBusinessPeriods();
    }
    
    /**
     * Retorna os expedientes cadastrados na empresa.
     * 
     * Método: getBusinessPeriods.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     */
    public void getBusinessPeriods() throws Exception {
        System.out.println("\nIniciando getBusinessPeriods()");

        BusinessPeriodDtoArray periodArray = businessPeriodService.getBusinessPeriods(this.userLogin,
                this.userPassword, this.tenantId);

        for (BusinessPeriodDto period : periodArray.getItem()) {
            System.out.println("\n########## Expediente: '" + period.getPeriodId() + "' ##########");
            for (BusinessPeriodInfoDto periodInfo : period.getPeriodInfos()) {
                System.out.println("\nSequence: " + periodInfo.getSequence());
                System.out.println("WeekDay: " + periodInfo.getWeekDay());
                System.out.println("InitialHour: " + periodInfo.getInitialHour());
                System.out.println("FinalHour: " + periodInfo.getFinalHour());
            }
        }

        if (periodArray.getItem().isEmpty()) {
            System.out.println("Nenhum expediente encontrado!");
        }

        System.out.println("\nTerminou getBusinessPeriods()");
    }

    private void accessConfig() {
        BindingProvider bp = (BindingProvider) this.businessPeriodService;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.fluigURL + "/webdesk/ECMBusinessPeriodService");
    }

    private BusinessPeriodService instanceBusinessPeriodService() {
        return new ECMBusinessPeriodServiceService().getBusinessPeriodServicePort();
    }
}
