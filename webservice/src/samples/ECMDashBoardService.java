package samples;

import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.dm.ws.DashBoardService;
import com.totvs.technology.ecm.dm.ws.ECMDashBoardServiceService;
import com.totvs.technology.ecm.dm.ws.HomeRequestSummaryDto;
import com.totvs.technology.ecm.dm.ws.TaskVODto;
import com.totvs.technology.ecm.dm.ws.TaskVODtoArray;
import com.totvs.technology.ecm.dm.ws.WorkflowProcessDto;
import com.totvs.technology.ecm.dm.ws.WorkflowProcessDtoArray;
import com.totvs.technology.ecm.dm.ws.WorkflowTaskDto;
import com.totvs.technology.ecm.dm.ws.WorkflowTaskDtoArray;

import net.java.dev.jaxb.array.IntArray;

/**
 * Classe que utiliza todos os métodos da ECMDashBoardService.
 * Com essa classe pode-se interagir com a central de tarefas do fluig.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class ECMDashBoardService {
    String fluigURL = "http://10.80.81.238:8080";
    String userId = "admin.fluig";
    String userLogin = "admin.fluig";
    String userPassword = "adm";
    String replacementId = "user.fluig"; 
    int tenantId = 123;
    int lastRowId = 0;
    int limit = 10;
    int yearIni = 2016;
    int yearFin = 2017;
    int mounthIni = 1;
    int mounthFin = 12;
    int dayIni = 1;
    int dayFin = 31;
    int kindTask = 1; // Tarefas a concluir

    DashBoardService dashBoardService = instanceDashBoardService();

    public static void main(String args[]) {
        System.out.println("\nIniciando ECMDashBoardService");

        ECMDashBoardService ECMSignalService = new ECMDashBoardService();

        try {
            ECMSignalService.accessConfig();
            ECMSignalService.changeMethod();
            System.out.println("\nTerminou ECMDashBoardService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMethod() throws Exception {
        this.findCancelledTasks();
        //this.findCompletedTasks();
        //this.findConsensusTasks();
        //this.findTransferredTasks();
        //this.findWorkflowTasks();
        //this.findWorkflowTasksOnDemand();
        //this.findMyRequests();
        //this.findMyRequestsOnDemand();
        //this.findMyManagerTasks();
        //this.findExpiredWorkflowTasks();
        //this.fillChronoTasks();
        //this.fillStatusTask();
        //this.fillTypeTasks();
        //this.fillTypeTasksOfReplacement();
        //this.getOpenTasksByColleagueGroups();
        //this.getOpenTasksByColleagueGroupsOnDemand();
        //this.getOpenTasksByColleagueRoles();
        //this.getOpenTasksByColleagueRolesOnDemand();
        //this.getSummaryRequests();
    }
    
    /**
     * Retorna as tarefas atribuidas ao usuário que foram canceladas.
     * 
     * Método: findCancelledTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void findCancelledTasks() throws Exception {
        System.out.println("\nIniciando findCancelledTasks()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findCancelledTasks(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas canceladas!");
        }
        
        System.out.println("\nTerminou findCancelledTasks()");
    }
    
    /**
     * Retorna as tarefas atribuidas ao usuário que foram finalizadas.
     * 
     * Método: findCompletedTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void findCompletedTasks() throws Exception {
        System.out.println("\nIniciando findCompletedTasks()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findCompletedTasks(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas finalizadas!");
        }
        
        System.out.println("\nTerminou findCompletedTasks()");
    }
    
    /**
     * Retorna as tarefas atribuidas ao usuário que estão em consenso.
     * 
     * Método: findConsensusTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void findConsensusTasks() throws Exception {
        System.out.println("\nIniciando findConsensusTasks()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findConsensusTasks(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas em consenso!");
        }
        
        System.out.println("\nTerminou findConsensusTasks()");
    }
    
    /**
     * Retorna as tarefas atribuidas ao usuário que foram transferidas.
     * 
     * Método: findTransferredTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void findTransferredTasks() throws Exception {
        System.out.println("\nIniciando findTransferredTasks()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findTransferredTasks(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas que foram transferidas!");
        }
        
        System.out.println("\nTerminou findTransferredTasks()");
    }
    
    /**
     * Retorna as tarefas do usuário que estão abertas.
     * 
     * Método: findWorkflowTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void findWorkflowTasks() throws Exception {
        System.out.println("\nIniciando findWorkflowTasks()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findWorkflowTasks(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas pendênte!");
        }
        
        System.out.println("\nTerminou findWorkflowTasks()");
    }
    
    /**
     * Retorna as tarefas do usuário que estão abertas, paginadas.
     * 
     * Método: findWorkflowTasksOnDemand.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada.
     * @throws Exception
     */
    public void findWorkflowTasksOnDemand() throws Exception {
        System.out.println("\nIniciando findWorkflowTasksOnDemand()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findWorkflowTasksOnDemand(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.limit, this.lastRowId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas pendênte!");
        }
        
        System.out.println("\nTerminou findWorkflowTasksOnDemand()");
    }
    
    /**
     * Retorna as solicitações abertas que foram inicializadas pelo usuário.
     * 
     * Método: findMyRequests.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void findMyRequests() throws Exception {
        System.out.println("\nIniciando findMyRequests()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findMyRequests(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas iniciadas pelo usuário!");
        }
        
        System.out.println("\nTerminou findMyRequests()");
    }
    
    /**
     * Retorna as solicitações abertas que foram inicializadas pelo usuário, paginadas.
     * 
     * Método: findMyRequestsOnDemand.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada.
     * @throws Exception
     */
    public void findMyRequestsOnDemand() throws Exception {
        System.out.println("\nIniciando findMyRequestsOnDemand()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findMyRequestsOnDemand(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.limit, this.lastRowId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas iniciadas pelo usuário!");
        }
        
        System.out.println("\nTerminou findMyRequestsOnDemand()");
    }
    
    /**
     * Retorna as tarefas gerenciadas pelo usuário.
     * 
     * Método: findMyManagerTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void findMyManagerTasks() throws Exception {
        System.out.println("\nIniciando findMyManagerTasks()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findMyManagerTasks(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas sob gerência!");
        }
        
        System.out.println("\nTerminou findMyManagerTasks()");
    }
    
    /**
     * Retorna as tarefas do usuário que estão em atraso.
     * 
     * Método: findExpiredWorkflowTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void findExpiredWorkflowTasks() throws Exception {
        System.out.println("\nIniciando findExpiredWorkflowTasks()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.findExpiredWorkflowTasks(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas atrasadas!");
        }
        
        System.out.println("\nTerminou findExpiredWorkflowTasks()");
    }
    
    /**
     * Retorna a quantidade de tarefas com prazo de conclusão que foram atribuidas ao usuário e não foram finalizadas.
     * 
     * Método: fillChronoTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void fillChronoTasks() throws Exception {
        System.out.println("\nIniciando fillChronoTasks()\n");

        IntArray resultArray = dashBoardService.fillChronoTasks(this.userLogin, this.userPassword, this.tenantId,
                this.userId, this.yearIni, this.yearFin, this.mounthIni, this.mounthFin, this.dayIni, this.dayFin,
                this.kindTask);
        
        System.out.println("Atrasada: " + resultArray.getItem().get(0));
        System.out.println("No prazo: " + resultArray.getItem().get(1));
        System.out.println("Sem prazo definido: " + resultArray.getItem().get(2));
        
        System.out.println("\nTerminou fillChronoTasks()");
    }
    
    /**
     * Retorna a quantidade de tarefas atrasadas, no prazo e sem prazo de um usuário.
     * 
     * Método: fillStatusTask.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void fillStatusTask() throws Exception {
        System.out.println("\nIniciando fillStatusTask()\n");

        IntArray resultArray = dashBoardService.fillStatusTask(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        System.out.println("Atrasada: " + resultArray.getItem().get(0));
        System.out.println("No prazo: " + resultArray.getItem().get(1));
        System.out.println("Sem prazo definido: " + resultArray.getItem().get(2));
        
        System.out.println("\nTerminou fillStatusTask()");
    }
    
    /**
     * Retorna os tipos de tarefas que o usuário possui.
     * 
     * Método: fillTypeTasks.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void fillTypeTasks() throws Exception {
        System.out.println("\nIniciando fillTypeTasks()\n");

        TaskVODtoArray tasks = dashBoardService.fillTypeTasks(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (TaskVODto task : tasks.getItem()) {
            System.out.println("Tarefa: " + task.getType() + " - " + task.getDescription());
        }
        
        if (tasks.getItem().size() == 0) {
            System.out.println("Não foi encontrado nenhum tipo de tarefa!");
        }
        
        System.out.println("\nTerminou fillTypeTasks()");
    }
    
    /**
     * Retorna os tipos de tarefas que o usuário possui.
     * 
     * Método: fillTypeTasksOfReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Código do substituto
     * @throws Exception
     */
    public void fillTypeTasksOfReplacement() throws Exception {
        System.out.println("\nIniciando fillTypeTasksOfReplacement()\n");

        TaskVODtoArray tasks = dashBoardService.fillTypeTasksOfReplacement(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.replacementId);
        
        for (TaskVODto task : tasks.getItem()) {
            System.out.println("Tarefa: " + task.getType() + " - " + task.getDescription());
        }
        
        if (tasks.getItem().size() == 0) {
            System.out.println("Não foi encontrado nenhum tipo de tarefa!");
        }
        
        System.out.println("\nTerminou fillTypeTasksOfReplacement()");
    }
    
    /**
     * Retorna as tarefas atribuídas a um grupo que o usuário participa.
     * 
     * Método: getOpenTasksByColleagueGroups.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void getOpenTasksByColleagueGroups() throws Exception {
        System.out.println("\nIniciando getOpenTasksByColleagueGroups()\n");

        WorkflowTaskDtoArray workflowTaskDtoArray = dashBoardService.getOpenTasksByColleagueGroups(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowTaskDto workflowTaskDto : workflowTaskDtoArray.getItem()) {
            System.out.println("Processo: " + workflowTaskDto.getProcessInstanceId() + " - " + workflowTaskDto.getDescription());
        }
        
        if (workflowTaskDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas atribuidas para o grupo do usuário!");
        }
        
        System.out.println("\nTerminou getOpenTasksByColleagueGroups()");
    }
    
    /**
     * Retorna as tarefas atribuídas a um grupo que o usuário participa, paginadas
     * 
     * Método: getOpenTasksByColleagueGroupsOnDemand.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada.
     * @throws Exception
     */
    public void getOpenTasksByColleagueGroupsOnDemand() throws Exception {
        System.out.println("\nIniciando getOpenTasksByColleagueGroupsOnDemand()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.getOpenTasksByColleagueGroupsOnDemand(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.limit, this.lastRowId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas atribuidas para o grupo do usuário!");
        }
        
        System.out.println("\nTerminou getOpenTasksByColleagueGroupsOnDemand()");
    }
    
    /**
     * Retorna as tarefas atribuídas a um papel que o usuário participa.
     * 
     * Método: getOpenTasksByColleagueRoles.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void getOpenTasksByColleagueRoles() throws Exception {
        System.out.println("\nIniciando getOpenTasksByColleagueRoles()\n");

        WorkflowTaskDtoArray workflowTaskDtoArray = dashBoardService.getOpenTasksByColleagueRoles(this.userLogin,
                this.userPassword, this.tenantId, this.userId);
        
        for (WorkflowTaskDto workflowTaskDto : workflowTaskDtoArray.getItem()) {
            System.out.println("Processo: " + workflowTaskDto.getProcessInstanceId() + " - " + workflowTaskDto.getDescription());
        }
        
        if (workflowTaskDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas atribuidas para o papel do usuário!");
        }
        
        System.out.println("\nTerminou getOpenTasksByColleagueRoles()");
    }
    
    /**
     * Retorna as tarefas atribuidas a um papel workflow que o usuário participa, paginadas.
     * 
     * Método: getOpenTasksByColleagueRolesOnDemand.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada.
     * @throws Exception
     */
    public void getOpenTasksByColleagueRolesOnDemand() throws Exception {
        System.out.println("\nIniciando getOpenTasksByColleagueRolesOnDemand()\n");

        WorkflowProcessDtoArray workflowProcessDtoArray = dashBoardService.getOpenTasksByColleagueRolesOnDemand(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.limit, this.lastRowId);
        
        for (WorkflowProcessDto workflowProcessDto : workflowProcessDtoArray.getItem()) {
            System.out.println("Processo: " + workflowProcessDto.getProcessInstanceId() + " - " + workflowProcessDto.getProcessDescription());
        }
        
        if (workflowProcessDtoArray.getItem().size() == 0) {
            System.out.println("Não foi encontrado tarefas atribuidas para o papel do usuário!");
        }
        
        System.out.println("\nTerminou getOpenTasksByColleagueRolesOnDemand()");
    }
    
    /**
     * Retorna os contadores de pendências da Home.
     * 
     * Método: getSummaryRequests.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * @throws Exception
     */
    public void getSummaryRequests() throws Exception {
        System.out.println("\nIniciando getSummaryRequests()\n");

        HomeRequestSummaryDto homeRequestSummaryDto = dashBoardService.getSummaryRequests(this.tenantId, this.userLogin,
                this.userPassword, this.userId);
        
        System.out.println(homeRequestSummaryDto.getNumberOfDocumentApprovalPending());
        System.out.println(homeRequestSummaryDto.getNumberOfDocumentAwaitingApproval());
        System.out.println(homeRequestSummaryDto.getNumberOfLearningTasks());
        System.out.println(homeRequestSummaryDto.getNumberOfOpenRequests());
        System.out.println(homeRequestSummaryDto.getNumberOfPoolGroupRequests());
        System.out.println(homeRequestSummaryDto.getNumberOfPoolRoleRequests());
        System.out.println(homeRequestSummaryDto.getNumberOfWorkflowDelayedRequests());
        System.out.println(homeRequestSummaryDto.getNumberOfWorkflowPendingRequests());
        System.out.println(homeRequestSummaryDto.getNumberOfWorkflowUnderDeadlineRequests());
        
        System.out.println("\nTerminou getSummaryRequests()");
    }

    private void accessConfig() {
        BindingProvider bp = (BindingProvider) this.dashBoardService;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.fluigURL + "/webdesk/ECMDashBoardService");
    }

    private DashBoardService instanceDashBoardService() {
        return new ECMDashBoardServiceService().getDashBoardServicePort();
    }
}
