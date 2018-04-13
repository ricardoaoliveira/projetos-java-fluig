package samples;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.foundation.ws.ColleagueReplacementDto;
import com.totvs.technology.ecm.foundation.ws.ColleagueReplacementDtoArray;
import com.totvs.technology.ecm.foundation.ws.ColleagueReplacementService;
import com.totvs.technology.ecm.foundation.ws.ECMColleagueReplacementServiceService;

/**
 * Classe que utiliza todos os métodos de ECMColleagueReplacementService.
 * Com essa classe, pode-se criar, alterar, excluir e pesquisar colaboradores substitutos, além de realizar outras atividades relacionadas a colaboradores substitutos.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class ECMColleagueReplacementService {
    String fluigURL = "http://mgwativosgestaoeadmi3732.fluig.cloudtotvs.com.br:80";
    String userId = "admin";
    String userLogin = "admin";
    String userPassword = "Mf4UEuac";
    String userReplacementId = "userCode_user.fluig";
    int tenantId = 3;
    
    ColleagueReplacementService colleagueReplacementService = instanceColleagueReplacementService();

    public static void main(String args[]) {
        System.out.println("\nIniciando ECMColleagueReplacementService");

        ECMColleagueReplacementService ECMColleagueReplacementService = new ECMColleagueReplacementService();

        try {
            ECMColleagueReplacementService.accessConfig();
            ECMColleagueReplacementService.changeMethod();
            System.out.println("\nTerminou ECMColleagueReplacementService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMethod() throws Exception {
        //this.createColleagueReplacement();
        //this.deleteColleagueReplacement();
        this.getColleagueReplacement();
        //this.getReplacementsOfUser();
        //this.getValidReplacedUsers();
        //this.getValidReplacement();
        //this.getValidReplacementsOfUser();
        //this.updateColleagueReplacement();
    }

    /**
     * Cria um substituto.
     * 
     * Método: createColleagueReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Cadastro do substituto;
     */
    public void createColleagueReplacement() throws Exception {
        System.out.println("\nIniciando createColleagueReplacement()\n");

        ColleagueReplacementDto colleagueReplacement = getColleagueReplacementDto();
        
        String result = colleagueReplacementService.createColleagueReplacement(this.userLogin, this.userPassword,
                this.tenantId, colleagueReplacement);
        
        if (result.equals("OK")) {
            System.out.println("Substituto criado!");
        } else {
            System.out.println(result);
        }
        
        System.out.println("\nTerminou createColleagueReplacement()");
    }
    
    /**
     * Deleta um substituto.
     * 
     * Método: deleteColleagueReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Código do substituto
     */
    public void deleteColleagueReplacement() throws Exception {
        System.out.println("\nIniciando deleteColleagueReplacement()\n");

        String result = colleagueReplacementService.deleteColleagueReplacement(this.userLogin, this.userPassword,
                this.tenantId, this.userId, this.userReplacementId);

        if (result.equals("OK")) {
            System.out.println("Substituto deletado!");
        } else {
            System.out.println(result);
        }

        System.out.println("\nTerminou deleteColleagueReplacement()");
    }
    
    /**
     * Retorna um substituto de um colaborador.
     * 
     * Método: getColleagueReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Código do substituto
     */
    public void getColleagueReplacement() throws Exception {
        System.out.println("\nIniciando getColleagueReplacement()\n");

        ColleagueReplacementDto replacementDto = colleagueReplacementService.getColleagueReplacement(this.userLogin,
                this.userPassword,this.tenantId, this.userId, this.userReplacementId);

        System.out.println("Usuário " + replacementDto.getReplacementId() + " é substituto de " + replacementDto.getColleagueId());

        System.out.println("\nTerminou getColleagueReplacement()");
    }
    
    /**
     * Retorna todos os substitutos de um colaborador.
     * 
     * Método: getReplacementsOfUser.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     */
    public void getReplacementsOfUser() throws Exception {
        System.out.println("\nIniciando getReplacementsOfUser()\n");

        ColleagueReplacementDtoArray replacementArray = colleagueReplacementService.getReplacementsOfUser(
                this.userLogin, this.userPassword, this.tenantId, this.userId);

        for (ColleagueReplacementDto replacement : replacementArray.getItem()) {
            System.out.println("Usuário substituto: " + replacement.getReplacementId());
        }

        if (replacementArray.getItem().isEmpty()) {
            System.out.println("Nenhum substituto encontrado para o usuário " + this.userId);
        }

        System.out.println("\nTerminou getReplacementsOfUser()");
    }
    
    /**
     * Retorna todos os colaboradores substituidos por um substituto válido.
     * 
     * Método: getValidReplacedUsers.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do substituto;
     */
    public void getValidReplacedUsers() throws Exception {
        System.out.println("\nIniciando getValidReplacedUsers()\n");

        ColleagueReplacementDtoArray replacementArray = colleagueReplacementService.getValidReplacedUsers(
                this.userLogin, this.userPassword, this.tenantId, this.userReplacementId);

        for (ColleagueReplacementDto replacement : replacementArray.getItem()) {
            System.out.println("Usuário substituído: " + replacement.getColleagueId());
        }

        if (replacementArray.getItem().isEmpty()) {
            System.out.println("Nenhum substituído encontrado para o usuário " + this.userReplacementId);
        }

        System.out.println("\nTerminou getValidReplacedUsers()");
    }
    
    /**
     * Retorna um substituto válido de um colaborador.
     * 
     * Método: getValidReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Código do substituto;
     */
    public void getValidReplacement() throws Exception {
        System.out.println("\nIniciando getValidReplacement()\n");

        ColleagueReplacementDto replacementDto = colleagueReplacementService.getValidReplacement(
                this.userLogin, this.userPassword, this.tenantId, this.userId, this.userReplacementId);

        System.out.println("Usuário " + replacementDto.getReplacementId() + " é substituto de " + replacementDto.getColleagueId());

        System.out.println("\nTerminou getValidReplacement()");
    }
    
    /**
     * Retorna todos os substitutos válidos de um colaborador.
     * 
     * Método: getValidReplacementsOfUser.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     */
    public void getValidReplacementsOfUser() throws Exception {
        System.out.println("\nIniciando getValidReplacementsOfUser()\n");

        ColleagueReplacementDtoArray replacementArray = colleagueReplacementService.getValidReplacementsOfUser(
                this.userLogin, this.userPassword, this.tenantId, this.userId);

        for (ColleagueReplacementDto replacement : replacementArray.getItem()) {
            System.out.println("Usuário substituto: " + replacement.getReplacementId());
        }

        if (replacementArray.getItem().isEmpty()) {
            System.out.println("Nenhum substituto encontrado para o usuário " + this.userId);
        }

        System.out.println("\nTerminou getValidReplacementsOfUser()");
    }
    
    /**
     * Altera o cadastro de um substituto.
     * 
     * Método: updateColleagueReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Cadastro do substituto;
     */
    public void updateColleagueReplacement() throws Exception {
        System.out.println("\nIniciando updateColleagueReplacement()\n");

        ColleagueReplacementDto colleagueReplacement = getColleagueReplacementDto();
        
        String result = colleagueReplacementService.updateColleagueReplacement(this.userLogin, this.userPassword,
                this.tenantId, colleagueReplacement);
        
        if (result.equals("OK")) {
            System.out.println("Substituto atualizado!");
        } else {
            System.out.println(result);
        }
        
        System.out.println("\nTerminou updateColleagueReplacement()");
    }
    
    public ColleagueReplacementDto getColleagueReplacementDto() throws DatatypeConfigurationException {
        ColleagueReplacementDto colleagueReplacementDto = new ColleagueReplacementDto();
        
        XMLGregorianCalendar startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        startDate.setYear(2017);
        startDate.setMonth(03);
        startDate.setDay(01);
        startDate.setHour(0);
        startDate.setMinute(0);
        startDate.setSecond(0);
        
        XMLGregorianCalendar endDate = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        endDate.setYear(2017);
        endDate.setMonth(12);
        endDate.setDay(01);
        endDate.setHour(0);
        endDate.setMinute(0);
        endDate.setSecond(0);

        colleagueReplacementDto.setColleagueId(this.userId);
        colleagueReplacementDto.setCompanyId(this.tenantId);
        colleagueReplacementDto.setReplacementId(this.userReplacementId);
        colleagueReplacementDto.setValidationFinalDate(endDate);
        colleagueReplacementDto.setValidationStartDate(startDate);
        colleagueReplacementDto.setViewGEDTasks(true);
        colleagueReplacementDto.setViewWorkflowTasks(true);

        return colleagueReplacementDto;
    }

    private void accessConfig() {
        BindingProvider bp = (BindingProvider) this.colleagueReplacementService;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.fluigURL + "/webdesk/ECMColleagueReplacementService");
    }

    private ColleagueReplacementService instanceColleagueReplacementService() {
        return new ECMColleagueReplacementServiceService().getColleagueReplacementServicePort();
    }
}
