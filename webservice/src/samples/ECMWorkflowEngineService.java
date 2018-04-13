package samples;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import com.totvs.technology.ecm.workflow.ws.Attachment;
import com.totvs.technology.ecm.workflow.ws.AttachmentArray;
import com.totvs.technology.ecm.workflow.ws.AvailableUsersDto;
import com.totvs.technology.ecm.workflow.ws.ColleagueDto;
import com.totvs.technology.ecm.workflow.ws.DeadLineDto;
import com.totvs.technology.ecm.workflow.ws.ECMWorkflowEngineServiceService;
import com.totvs.technology.ecm.workflow.ws.KeyValueDto;
import com.totvs.technology.ecm.workflow.ws.KeyValueDtoArray;
import com.totvs.technology.ecm.workflow.ws.ProcessAttachmentDto;
import com.totvs.technology.ecm.workflow.ws.ProcessAttachmentDtoArray;
import com.totvs.technology.ecm.workflow.ws.ProcessDefinitionDto;
import com.totvs.technology.ecm.workflow.ws.ProcessDefinitionDtoArray;
import com.totvs.technology.ecm.workflow.ws.ProcessDefinitionVersionDto;
import com.totvs.technology.ecm.workflow.ws.ProcessDefinitionVersionDtoArray;
import com.totvs.technology.ecm.workflow.ws.ProcessHistoryDto;
import com.totvs.technology.ecm.workflow.ws.ProcessHistoryDtoArray;
import com.totvs.technology.ecm.workflow.ws.ProcessStateDto;
import com.totvs.technology.ecm.workflow.ws.ProcessStateDtoArray;
import com.totvs.technology.ecm.workflow.ws.ProcessTaskAppointmentDto;
import com.totvs.technology.ecm.workflow.ws.ProcessTaskAppointmentDtoArray;
import com.totvs.technology.ecm.workflow.ws.WorkflowEngineService;

import net.java.dev.jaxb.array.IntArray;
import net.java.dev.jaxb.array.StringArray;
import net.java.dev.jaxb.array.StringArrayArray;

/**
 * Classe que utiliza todos os métodos da ECMWorkflowEngineService.
 * Com essa classe pode-se movimentar solicitações Workflow e realizar outras atividades relacionadas a essa funcionalidade.
 * No método setParameters, pode-se setar algumas das variáveis que são utilizadas como parâmetros nos método(s) desta classe.
 * No método changeMethod, pode-se escolher qual método será executado.
 */
public class ECMWorkflowEngineService {
    String fluigURL = "http://10.80.81.238:8080";
    String userId = "admin.fluig";
    String userLogin = "admin.fluig";
    String userPassword = "adm";
    String processId = "BPM-TESTE-Simples";
    String cancellationText = "Cancelando a solicitação do fluig via Webservice";
    String userReplacementId = "user.fluig";
    String periodId = "Default";
    String date = "2017-02-28";
    String userSearch = "fluig";
    String processSearch = "ADHOC";
    String fieldName = "nome";
    String filePath = "/tmp/BPM-TESTE-Simples.xml";
    String documentDescription = "Documento fluig";
    String requestComment = "Comentário via Webservice";
    
    int tenantId = 123;
    int processInstanceId = 48;
    int activity = 2;
    int time = 7200;
    int deadline = 1;
    int stateSequence = 0;
    int limit = 10;
    int lastRowId = 0;
    int documentId = 1092;
    int documentVersion = 1000;
    
    boolean newProcess = false;
    boolean overWrite = false;
    boolean publishViaFTP = false;
    boolean isManager = false;
    boolean needAppointment = false;
    boolean favoriteProcess = false;
    boolean completeTask = true;
    
    byte[] fileArrayBytes = null;

    WorkflowEngineService workflowEngineService = instanceWorkflowEngineService();

    public static void main(String args[]) {
        System.out.println("\nIniciando ECMWorkflowEngineService");

        ECMWorkflowEngineService ECMWorkflowEngineService = new ECMWorkflowEngineService();

        try {
            ECMWorkflowEngineService.setParameters();
            ECMWorkflowEngineService.accessConfig();
            ECMWorkflowEngineService.changeMethod();
            System.out.println("\nTerminou ECMWorkflowEngineService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setParameters() throws Exception {

        
    }

    public void changeMethod() throws Exception {
        this.calculateDeadLineHours();
        //this.calculateDeadLineTime();
        //this.cancelInstance();
        //this.cancelInstanceByReplacement();
        //this.createWorkFlowProcessVersion();
        //this.exportProcess();
        //this.exportProcessInZipFormat();
        //this.getActualThread();
        //this.getAllActiveStates();
        //this.getAllProcessAvailableToExport();
        //this.getAllProcessAvailableToImport();
        //this.getAttachments();
        //this.getAvailableProcess();
        //this.getAvailableProcessOnDemand();
        //this.getAvailableStates();
        //this.getAvailableStatesDetail();
        //this.getAvailableUsers();
        //this.getAvailableUsersOnDemand();
        //this.getAvailableUsersStart();
        //this.getAvailableUsersStartOnDemand();
        //this.getCardValue();
        //this.getHistories();
        //this.getInstanceCardData();
        //this.getProcessFormId();
        //this.getProcessImage();
        //this.getWorkFlowProcessVersion();
        //this.importProcess();
        //this.importProcessWithCard();
        //this.releaseProcess();
        //this.saveAndSendTask();
        //this.saveAndSendTaskByReplacement();
        //this.saveAndSendTaskClassic();
        //this.searchProcess();
        //this.setAutomaticDecisionClassic();
        //this.setDueDate();
        //this.setTasksComments();
        //this.simpleStartProcess();
        //this.startProcess();
        //this.startProcessClassic();
        //this.takeProcessTask();
        //this.takeProcessTaskByReplacement();
    }

    /**
     * Retorna um prazo a partir de uma data com base nos expedientes e feriados cadastrados no fluig.
     * 
     * Método: calculateDeadLineHours.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Data prazo;
     * - Hora prazo;
     * - Prazo da atividade para cálculo;
     * - Código do expediente;
     */
    private void calculateDeadLineHours() throws Exception {
        System.out.println("\nIniciando calculateDeadLineHours()");

        DeadLineDto deadLine = workflowEngineService.calculateDeadLineHours(this.userLogin, this.userPassword,
                this.tenantId, this.userId, this.date, this.time, this.deadline, this.periodId);

        System.out.println("\nData: " + deadLine.getDate());
        System.out.println("Hora: " + deadLine.getHora());

        System.out.println("\nTerminou calculateDeadLineHours()");
    }

    /**
     * Retorna um prazo a partir de uma data com base nos expedientes e feriados cadastrados no fluig.
     * 
     * Método: calculateDeadLineTime.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Data prazo;
     * - Hora prazo;
     * - Prazo da atividade para cálculo;
     * - Código do expediente;
     */
    private void calculateDeadLineTime() throws Exception {
        System.out.println("\nIniciando calculateDeadLineTime()");

        DeadLineDto deadLine = workflowEngineService.calculateDeadLineTime(this.userLogin, this.userPassword,
                this.tenantId, this.userId, this.date, this.time, this.deadline, this.periodId);

        System.out.println("\nData: " + deadLine.getDate());
        System.out.println("Hora: " + deadLine.getHora());

        System.out.println("\nTerminou calculateDeadLineTime()");
    }
    
    /**
     * Cancela uma solicitação.
     * 
     * Método: cancelInstance.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código da solicitação que será cancelada;
     * - Código do usuário;
     * - Texto para cancelamento
     */
    private void cancelInstance() throws Exception {
        System.out.println("\nIniciando cancelInstance()");

        String result = workflowEngineService.cancelInstance(this.userLogin, this.userPassword,
                this.tenantId, this.processInstanceId, this.userId, this.cancellationText);

        if (result.equals("OK")) {
            System.out.println("Solicitação " + this.processInstanceId + " foi cancelada!");
        } else {
            System.out.println("Solicitação " + this.processInstanceId + " não foi cancelada!");
        }

        System.out.println("\nTerminou cancelInstance()");
    }
    
    /**
     * Cancela uma solicitação utilizando colaborador substituto.
     * 
     * Método: cancelInstanceByReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código da solicitação que será cancelada;
     * - Código do usuário;
     * - Texto para cancelamento
     * - Código do usuário substituto;
     */
    private void cancelInstanceByReplacement() throws Exception {
        System.out.println("\nIniciando cancelInstanceByReplacement()");

        String result = workflowEngineService.cancelInstanceByReplacement(this.userLogin, this.userPassword,
                this.tenantId, this.processInstanceId, this.userId, this.cancellationText, this.userReplacementId);
        
        if (result.equals("OK")) {
            System.out.println("Solicitação para o substituto " + this.userReplacementId + " foi cancelada!");
        } else {
            System.out.println("Solicitação para o substituto " + this.userReplacementId + " não foi cancelada!");
        }

        System.out.println("\nTerminou cancelInstanceByReplacement()");
    }
    
    /**
     * Cria uma nova versão do processo.
     * 
     * Método: createWorkFlowProcessVersion.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     */
    private void createWorkFlowProcessVersion() throws Exception {
        System.out.println("\nIniciando createWorkFlowProcessVersion()");

        String result = workflowEngineService.createWorkFlowProcessVersion(this.userLogin, this.userPassword,
                this.tenantId, this.processId);

        if (result.contains("error")) {
            System.out.println("\nNão foi possível criar uma versão do processo " + this.processId);
        } else {
            System.out.println("\nFoi criado uma nova versão do processo " + this.processId);
        }

        System.out.println("\nTerminou createWorkFlowProcessVersion()");
    }
    
    /**
     * Exportação de processo retornando o XML.
     * 
     * Método: exportProcess.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     */
    private void exportProcess() throws Exception {
        System.out.println("\nIniciando exportProcess()");

        try {
            String result = workflowEngineService.exportProcess(this.userLogin, this.userPassword,
                    this.tenantId, this.processId);
            System.out.println("\nProcesso " + this.processId + " exportado! Tamanho: " + result.length());
        } catch (Exception e) {
            System.out.println("\nNão foi possível exportar o processo " + this.processId);
        }

        System.out.println("\nTerminou exportProcess()");
    }
    
    /**
     * Exportação de processo retornando o ZIP.
     * 
     * Método: exportProcessInZipFormat.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     */
    private void exportProcessInZipFormat() throws Exception {
        System.out.println("\nIniciando exportProcessInZipFormat()");

        try {
            byte[] result = workflowEngineService.exportProcessInZipFormat(this.userLogin, this.userPassword,
                    this.tenantId, this.processId);
            System.out.println("\nProcesso " + this.processId + " exportado! Tamanho: " + result.length);
        } catch (Exception e) {
            System.out.println("\nNão foi possível exportar o processo " + this.processId);
        }

        System.out.println("\nTerminou exportProcessInZipFormat()");
    }
    
    /**
     * Retorna a sequência da thread de uma solicitação.
     * 
     * Método: getActualThread.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código da solicitação;
     * - Sequência do estado;
     */
    private void getActualThread() throws Exception {
        System.out.println("\nIniciando getActualThread()");

        int result = workflowEngineService.getActualThread(this.userLogin, this.userPassword,
                this.tenantId, this.processInstanceId, this.stateSequence);
        
        System.out.println("\nSequência da thread: " + result);
        System.out.println("\nTerminou getActualThread()");
    }
    
    /**
     * Retorna o número da atividade em que uma solicitação esta.
     * 
     * Método: getAllActiveStates.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Código da solicitação;
     */
    private void getAllActiveStates() throws Exception {
        System.out.println("\nIniciando getAllActiveStates()");
        IntArray intArray = workflowEngineService.getAllActiveStates(this.userLogin, this.userPassword,
                this.tenantId, this.userId, this.processInstanceId);

        if (!intArray.getItem().isEmpty()) {
            System.out.println("\nSolicitação esta na atividade " + intArray.getItem().get(0));
        } else {
            System.out.println("\nNenhuma atividade encontrada para a solicitação " + this.processInstanceId);
        }

        System.out.println("\nTerminou getAllActiveStates()");
    }
    
    /**
     * Retorna uma lista de processos disponíveis para ser exportado.
     * 
     * Método: getAllProcessAvailableToExport.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     */
    private void getAllProcessAvailableToExport() throws Exception {
        System.out.println("\nIniciando getAllProcessAvailableToExport()\n");

        ProcessDefinitionDtoArray processArray = workflowEngineService.getAllProcessAvailableToExport(this.userLogin, this.userPassword, this.tenantId);

        for (ProcessDefinitionDto process : processArray.getItem()) {
            System.out.println("Processo: " + process.getProcessId());
        }

        if (processArray.getItem().isEmpty()) {
            System.out.println("Nenhum processo encontrado para ser exportado!");
        }

        System.out.println("\nTerminou getAllProcessAvailableToExport()");
    }
    
    /**
     * Retorna uma lista de processos disponíveis para ser importado.
     * 
     * Método: getAllProcessAvailableToImport.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     */
    private void getAllProcessAvailableToImport() throws Exception {
        System.out.println("\nIniciando getAllProcessAvailableToImport()\n");

        ProcessDefinitionDtoArray processArray = workflowEngineService.getAllProcessAvailableToImport(this.userLogin, this.userPassword, this.tenantId);

        for (ProcessDefinitionDto process : processArray.getItem()) {
            System.out.println("Processo: " + process.getProcessId());
        }

        if (processArray.getItem().isEmpty()) {
            System.out.println("Nenhum processo encontrado para ser importado!");
        }

        System.out.println("\nTerminou getAllProcessAvailableToImport()");
    }
    
    /**
     * Retorna os anexos de uma solicitação.
     * 
     * Método: getAttachments.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Código da solicitação;
     */
    private void getAttachments() throws Exception {
        System.out.println("\nIniciando getAttachments()\n");

        ProcessAttachmentDtoArray attachmentArray = workflowEngineService.getAttachments(this.userLogin, this.userPassword,
                this.tenantId, this.userId, this.processInstanceId);

        for (ProcessAttachmentDto attachment : attachmentArray.getItem()) {
            System.out.println(attachment.getDocumentId() + " - " + attachment.getDescription());
        }

        if (attachmentArray.getItem().isEmpty()) {
            System.out.println("Nenhum anexo encontrado na solicitação " + this.processInstanceId);
        }

        System.out.println("\nTerminou getAttachments()");
    }
    
    /**
     * Retorna os processos que o usuário tem permissão para iniciar uma solicitação.
     * 
     * Método: getAvailableProcess.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     */
    private void getAvailableProcess() throws Exception {
        System.out.println("\nIniciando getAvailableProcess()\n");

        ProcessDefinitionVersionDtoArray processArray = workflowEngineService.getAvailableProcess(this.userLogin, this.userPassword,
                this.tenantId, this.userId);

        for (ProcessDefinitionVersionDto process : processArray.getItem()) {
            System.out.println("Processo: " + process.getProcessId());
        }

        if (processArray.getItem().isEmpty()) {
            System.out.println("Nenhum processo encontrado para o usuário " + this.userId + " iniciar!");
        }

        System.out.println("\nTerminou getAvailableProcess()");
    }
    
    /**
     * Retorna os processos que o usuário tem permissão para iniciar uma solicitação 
     * com utilização de parâmetros para paginação.
     * 
     * Método: getAvailableProcessOnDemand.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada.
     */
    private void getAvailableProcessOnDemand() throws Exception {
        System.out.println("\nIniciando getAvailableProcessOnDemand()\n");

        ProcessDefinitionVersionDtoArray processArray = workflowEngineService.getAvailableProcessOnDemand(this.userLogin, this.userPassword,
                this.tenantId, this.userId, this.limit, this.lastRowId);

        for (ProcessDefinitionVersionDto process : processArray.getItem()) {
            System.out.println("Processo: " + process.getProcessId());
        }

        if (processArray.getItem().isEmpty()) {
            System.out.println("Nenhum processo encontrado para o usuário " + this.userId + " iniciar!");
        }

        System.out.println("\nTerminou getAvailableProcessOnDemand()");
    }
    
    /**
     * Retorna o número da próxima atividade de uma solicitação.
     * 
     * Método: getAvailableStates.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo
     * - Código da solicitação;
     * - Número da sequência da thread
     */
    private void getAvailableStates() throws Exception {
        System.out.println("\nIniciando getAvailableStates()");

        IntArray intArray = workflowEngineService.getAvailableStates(this.userLogin, this.userPassword,
                this.tenantId, this.processId, this.processInstanceId, this.stateSequence);

        if (!intArray.getItem().isEmpty()) {
            System.out.println("\nPróxima atividade: " + intArray.getItem().get(0));
        }

        System.out.println("\nTerminou getAvailableStates()");
    }
    
    /**
     * Retorna detalhes das atividades disponíveis para seleção.
     * 
     * Método: getAvailableStatesDetail.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo
     * - Código da solicitação;
     * - Número da sequência da thread
     */
    private void getAvailableStatesDetail() throws Exception {
        System.out.println("\nIniciando getAvailableStatesDetail()\n");

        ProcessStateDtoArray processArray = workflowEngineService.getAvailableStatesDetail(this.userLogin, this.userPassword,
                this.tenantId, this.processId, this.processInstanceId, this.stateSequence);

        for (ProcessStateDto process : processArray.getItem()) {
            System.out.println("Atividade " + process.getSequence() + " - " + process.getStateDescription());
        }

        if (processArray.getItem().isEmpty()) {
            System.out.println("Nenhuma atividade encontrado na solicitação " + this.processInstanceId);
        }

        System.out.println("\nTerminou getAvailableStatesDetail()");
    }
    
    /**
     * Retorna detalhes das atividades disponíveis para seleção.
     * 
     * Método: getAvailableUsers.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código da solicitação;
     * - Número da atividade
     * - Número da sequência da thread
     */
    private void getAvailableUsers() throws Exception {
        System.out.println("\nIniciando getAvailableUsers()\n");

        StringArray userArray = workflowEngineService.getAvailableUsers(this.userLogin, this.userPassword,
                this.tenantId, this.processInstanceId, this.activity, this.stateSequence);
        
        for (String user : userArray.getItem()) {
            System.out.println("Usuário: " + user);
        }

        if (userArray.getItem().isEmpty()) {
            System.out.println("Nenhum usuário encontrado na solicitação " + this.processInstanceId);
        }

        System.out.println("\nTerminou getAvailableUsers()");
    }
    
    /**
     * Retorna os usuários que podem executar a tarefa corrente de uma solicitação, paginados e com busca.
     * 
     * Método: getAvailableUsersOnDemand.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código da solicitação;
     * - Número da atividade
     * - Número da sequência da thread
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada;
     * - Texto a ser buscado
     */
    private void getAvailableUsersOnDemand() throws Exception {
        System.out.println("\nIniciando getAvailableUsersOnDemand()\n");

        AvailableUsersDto userArray = workflowEngineService.getAvailableUsersOnDemand(this.userLogin, this.userPassword,
                this.tenantId, this.processInstanceId, this.activity, this.stateSequence, this.limit, this.lastRowId, this.userSearch);

        for (ColleagueDto user : userArray.getUsers()) {
            System.out.println("Usuário: " + user.getColleagueName());
        }

        if (userArray.getUsers().isEmpty()) {
            System.out.println("Nenhum usuário encontrado para executar a solicitação " + this.processInstanceId);
        }

        System.out.println("\nTerminou getAvailableUsersOnDemand()");
    }
    
    /**
     * Retorna os usuários disponíveis na abertura de uma solicitação.
     * 
     * Método: getAvailableUsersStart.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     * - Número da atividade
     * - Número da sequência da thread
     */
    private void getAvailableUsersStart() throws Exception {
        System.out.println("\nIniciando getAvailableUsersStart()\n");

        StringArray userArray = workflowEngineService.getAvailableUsersStart(this.userLogin, this.userPassword,
                this.tenantId, this.processId, this.activity, this.stateSequence);

        for (String user : userArray.getItem()) {
            System.out.println("Usuário: " + user);
        }

        if (userArray.getItem().isEmpty()) {
            System.out.println("Nenhum usuário encontrado para o processo " + this.processId);
        }

        System.out.println("\nTerminou getAvailableUsersStart()");
    }
    
    /**
     * Retorna os usuários disponíveis na abertura de uma solicitação, paginados e com busca.
     * 
     * Método: getAvailableUsersStartOnDemand.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     * - Número da atividade
     * - Número da sequência da thread
     * - Quantidade de registros a serem retornados;
     * - Última linha retornada;
     * - Texto a ser buscado
     */
    private void getAvailableUsersStartOnDemand() throws Exception {
        System.out.println("\nIniciando getAvailableUsersStartOnDemand()\n");

        AvailableUsersDto userArray = workflowEngineService.getAvailableUsersStartOnDemand(this.userLogin,
                this.userPassword, this.tenantId, this.processId, this.activity, 
                this.stateSequence, this.limit, this.lastRowId, this.userSearch);

        for (ColleagueDto user : userArray.getUsers()) {
            System.out.println("Usuário: " + user.getColleagueName());
        }

        if (userArray.getUsers().isEmpty()) {
            System.out.println("Nenhum usuário encontrado para o processo " + this.processId);
        }

        System.out.println("\nTerminou getAvailableUsersStartOnDemand()");
    }
    
    /**
     * Retorna o valor de um campo do registro de formulário.
     * 
     * Método: getCardValue.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do solicitação;
     * - Código do usuário;
     * - Nome do campo do registro de formulário;
     */
    private void getCardValue() throws Exception {
        System.out.println("\nIniciando getCardValue()\n");

        String cardValue = workflowEngineService.getCardValue(this.userLogin,
                this.userPassword, this.tenantId, this.processInstanceId, this.userId, this.fieldName);

        if (cardValue != null) {
            System.out.println("Valor do campo '" + this.fieldName + "': " + cardValue);
        } else {
            System.out.println("Campo '" + this.fieldName + "' não encontrado!");
        }

        System.out.println("\nTerminou getCardValue()");
    }
    
    /**
     * Retorna lista de históricos de um processo.
     * 
     * Método: getHistories.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Código do solicitação;
     */
    private void getHistories() throws Exception {
        System.out.println("\nIniciando getHistories()\n");

        ProcessHistoryDtoArray processArray = workflowEngineService.getHistories(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.processInstanceId);

        for (ProcessHistoryDto process : processArray.getItem()) {
            System.out.println("Solicitação: " + process.getProcessInstanceId());
        }

        if (processArray.getItem().isEmpty()) {
            System.out.println("Nenhum histórico encontrado para a solicitação " + this.processInstanceId);
        }

        System.out.println("\nTerminou getHistories()");
    }
    
    /**
     * Retorna o valor dos campos do registro de formulário de uma solicitação.
     * 
     * Método: getInstanceCardData.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Código do solicitação;
     */
    private void getInstanceCardData() throws Exception {
        System.out.println("\nIniciando getInstanceCardData()\n");

        StringArrayArray stringArray = workflowEngineService.getInstanceCardData(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.processInstanceId);

        for (StringArray process : stringArray.getItem()) {
            System.out.println("Valor do campo '" + process.getItem().get(0) + "': " + process.getItem().get(1));
        }

        if (stringArray.getItem().isEmpty()) {
            System.out.println("Nenhum campo encontrado para a solicitação " + this.processInstanceId);
        }

        System.out.println("\nTerminou getInstanceCardData()");
    }
    
    /**
     * Retorna o código do formulário vinculado ao processo.
     * 
     * Método: getProcessFormId.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     */
    private void getProcessFormId() throws Exception {
        System.out.println("\nIniciando getProcessFormId()");

        int formId = workflowEngineService.getProcessFormId(this.userLogin,
                this.userPassword, this.tenantId, this.processId);

        if (formId != 0) {
            System.out.println("\nCódigo do formulário: " + formId);
        } else {
            System.out.println("\nProcesso " + this.processId + " não possui formulário associado!");
        }

        System.out.println("\nTerminou getProcessFormId()");
    }
    
    /**
     * Retorna o código do formulário vinculado ao processo.
     * 
     * Método: getProcessImage.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário
     * - Código do processo;
     */
    private void getProcessImage() throws Exception {
        System.out.println("\nIniciando getProcessImage()\n");

        String result = workflowEngineService.getProcessImage(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.processId);

        System.out.println(this.processId + " - " + result);

        System.out.println("\nTerminou getProcessImage()");
    }
    
    /**
     * Retorna a versão de um processo.
     * 
     * Método: getWorkFlowProcessVersion.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     */
    private void getWorkFlowProcessVersion() throws Exception {
        System.out.println("\nIniciando getWorkFlowProcessVersion()\n");

        int processVersion = workflowEngineService.getWorkFlowProcessVersion(this.userLogin,
                this.userPassword, this.tenantId, this.processId);

        System.out.println("Versão do processo '" + this.processId + "': " + processVersion);

        System.out.println("\nTerminou getWorkFlowProcessVersion()");
    }
    
    /**
     * Importa processos workflow.
     * 
     * Método: importProcess.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     * - Arquivo a ser importado.
     * - Indica se deve ser criado um processo ou atualizado o existente.
     * - Código do usuário
     */
    private void importProcess() throws Exception {
        System.out.println("\nIniciando importProcess()\n");

        Attachment attachment = new Attachment();
        if (this.publishViaFTP) {
            attachment.setFileName(filePath);
        } else {
            attachment.setFileName(this.getArchive().getName());
        }
        attachment.setPrincipal(true);
        attachment.setFilecontent(this.fileArrayBytes);

        AttachmentArray attachmentArray = new AttachmentArray();
        attachmentArray.getItem().add(attachment);

        String result = workflowEngineService.importProcess(this.userLogin,
                this.userPassword, this.tenantId, this.processId, attachmentArray, this.newProcess, this.overWrite,
                this.userId);

        System.out.println(result);

        System.out.println("\nTerminou importProcess()");
    }
    
    /**
     * Libera processos workflow.
     * 
     * Método: releaseProcess.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     */
    private void releaseProcess() throws Exception {
        System.out.println("\nIniciando releaseProcess()\n");

        String result = workflowEngineService.releaseProcess(this.userLogin,
                this.userPassword, this.tenantId, this.processId);

        System.out.println(result);

        System.out.println("\nTerminou releaseProcess()");
    }
    
    /**
     * Movimenta solicitação para próxima atividade.
     * 
     * Método: saveAndSendTask.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Número da solicitação;
     * - Número da atividade;
     * - Usuário destino;
     * - Comentários;
     * - Código do usuário;
     * - Indica se deve completar a tarefa (true) ou apenas salvar (false);
     * - Anexos do processo;
     * - Dados do formulário;
     * - Apontamento da atividade;
     * - Indica se o usuário está acessando a tarefa como Gestor do processo;
     * - Número da sequência da thread;
     */
    private void saveAndSendTask() throws Exception {
        System.out.println("\nIniciando saveAndSendTask()\n");

        StringArrayArray resultArray = workflowEngineService.saveAndSendTask(this.userLogin,
                this.userPassword, this.tenantId, this.processInstanceId, this.activity, this.createDestinationUser(),
                this.requestComment, this.userId, this.completeTask, this.createProcessAttachment(),
                this.createFormFieldStringValue(), this.createAppointment(), this.isManager, this.stateSequence);

        for (StringArray result : resultArray.getItem()) {
            System.out.println(result.getItem().get(0) + ": " + result.getItem().get(1));
        }

        System.out.println("\nTerminou saveAndSendTask()");
    }
    
    /**
     * Movimenta solicitação para próxima atividade.
     * 
     * Método: saveAndSendTaskByReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Número da solicitação;
     * - Número da atividade;
     * - Usuário destino;
     * - Comentários;
     * - Código do usuário;
     * - Indica se deve completar a tarefa (true) ou apenas salvar (false);
     * - Anexos do processo;
     * - Dados do formulário;
     * - Apontamento da atividade;
     * - Indica se o usuário está acessando a tarefa como Gestor do processo;
     * - Número da sequência da thread;
     * - Código do usuário substituto;
     */
    private void saveAndSendTaskByReplacement() throws Exception {
        System.out.println("\nIniciando saveAndSendTaskByReplacement()\n");

        StringArrayArray resultArray = workflowEngineService.saveAndSendTaskByReplacement(this.userLogin,
                this.userPassword, this.tenantId, this.processInstanceId, this.activity, this.createDestinationUser(),
                this.requestComment, this.userId, this.completeTask, this.createProcessAttachment(),
                this.createFormFieldStringValue(), this.createAppointment(), this.isManager, this.stateSequence,
                this.userReplacementId);

        for (StringArray result : resultArray.getItem()) {
            System.out.println(result.getItem().get(0) + ": " + result.getItem().get(1));
        }

        System.out.println("\nTerminou saveAndSendTaskByReplacement()");
    }
    
    /**
     * Movimenta solicitação para próxima atividade.
     * 
     * Método: saveAndSendTaskClassic.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Número da solicitação;
     * - Número da atividade;
     * - Usuário destino;
     * - Comentários;
     * - Código do usuário;
     * - Indica se deve completar a tarefa (true) ou apenas salvar (false);
     * - Anexos do processo;
     * - Dados do formulário;
     * - Apontamento da atividade;
     * - Indica se o usuário está acessando a tarefa como Gestor do processo;
     * - Número da sequência da thread;
     */
    private void saveAndSendTaskClassic() throws Exception {
        System.out.println("\nIniciando saveAndSendTaskClassic()\n");

        KeyValueDtoArray result = workflowEngineService.saveAndSendTaskClassic(this.userLogin,
                this.userPassword, this.tenantId, this.processInstanceId, this.activity, this.createDestinationUser(),
                this.requestComment, this.userId, this.completeTask, this.createProcessAttachment(),
                this.createFormFieldKeyValue(), this.createAppointment(), this.isManager, this.stateSequence);

        for (KeyValueDto keyValueDtoa : result.getItem()) {
            System.out.println(keyValueDtoa.getKey() + ": " + keyValueDtoa.getValue());
        }

        System.out.println("\nTerminou saveAndSendTaskClassic()");
    }
    
    /**
     * Busca processo disponíveis para inicialização.
     * 
     * Método: searchProcess.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuários;
     * - Nome do processo a ser pesquisado;
     * - Busca da lista de processos favoritos (true) ou da listagem padrão (false);
     */
    private void searchProcess() throws Exception {
        System.out.println("\nIniciando searchProcess()\n");

        ProcessDefinitionVersionDtoArray processArray = workflowEngineService.searchProcess(this.userLogin,
                this.userPassword, this.tenantId, this.userId, this.processSearch, this.favoriteProcess);

        for (ProcessDefinitionVersionDto process : processArray.getItem()) {
            System.out.println("Processo: " + process.getProcessDescription());
        }

        System.out.println("\nTerminou searchProcess()");
    }
    
    /**
     * Seleciona usuário(s) e avança atividade automática.
     * 
     * Método: setAutomaticDecisionClassic.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Número da solicitação;
     * - Número da atividade que é automática;
     * - Número da atividade destino;
     * - Número da condição, para registro do que gerou a decisão;
     * - Usuário destino;
     * - Comentários da tarefa;
     * - Usuário que está com a atividade atual;
     * - Indica se o colaborador esta acessando a tarefa como gestor do processo;
     * - Sequência da Thread;
     */
    private void setAutomaticDecisionClassic() throws Exception {
        System.out.println("\nIniciando setAutomaticDecisionClassic()\n");

        //Código da atividade automática
        int iTaskAutom = 4;

        //Código que identificará a condição que será seguida na atividade automática
        int condition = 0;

        KeyValueDtoArray result = workflowEngineService.setAutomaticDecisionClassic(this.userLogin,
                this.userPassword, this.tenantId, this.processInstanceId, iTaskAutom, this.activity, condition,
                this.createDestinationUser(), this.requestComment, this.userId, this.isManager, this.stateSequence);

        for (KeyValueDto keyValueDtoa : result.getItem()) {
            System.out.println(keyValueDtoa.getKey() + ": " + keyValueDtoa.getValue());
        }

        System.out.println("\nTerminou setAutomaticDecisionClassic()");
    }
    
    /**
     * Altera a data de prazo da tarefa.
     * 
     * Método: setDueDate.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Número da solicitação;
     * - Código do usuário;
         * Quando é Para um Grupo ou Para um Papel deve ser passado da seguinte forma:
            Papel: "Pool:Role:CodigoDoPapel";
            Grupo: "Pool:Group:CodigoDoGrupo";
     * - Sequência da Thread;
     * - Nova data de prazo da tarefa. Exemplo: "2017-03-01";
     * - Quantidade de segundos após a meia noite. Exemplo: "32000";
     */
    private void setDueDate() throws Exception {
        System.out.println("\nIniciando setDueDate()\n");

        String result = workflowEngineService.setDueDate(this.userLogin, this.userPassword, this.tenantId,
                this.processInstanceId, this.userId, this.stateSequence, this.date, this.time);

        System.out.println(result);

        System.out.println("\nTerminou setDueDate()");
    }
    
    /**
     * Adiciona um comentários na tarefa.
     * 
     * Método: setTasksComments.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Número da solicitação;
     * - Código do usuário;
     * - Sequência da Thread;
     * - Comentário da tarefa
     */
    private void setTasksComments() throws Exception {
        System.out.println("\nIniciando setTasksComments()\n");

        String result = workflowEngineService.setTasksComments(this.userLogin, this.userPassword, this.tenantId,
                this.processInstanceId, this.userId, this.stateSequence, this.requestComment);

        System.out.println(result);

        System.out.println("\nTerminou setTasksComments()");
    }
    
    /**
     * Inicia uma solicitação simples.
     * 
     * Método: simpleStartProcess.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     * - Comentário da atividade;
     * - Anexos da solicitação;
     * - Dados do formulário;
     */
    private void simpleStartProcess() throws Exception {
        System.out.println("\nIniciando simpleStartProcess()\n");

        StringArray resultArray = workflowEngineService.simpleStartProcess(this.userLogin, this.userPassword,
                this.tenantId, this.processId, this.requestComment,
                this.createProcessAttachment(), this.createFormFieldStringValue());

        for (String result : resultArray.getItem()) {
            System.out.println(result);
        }

        System.out.println("\nTerminou simpleStartProcess()");
    }
    
    /**
     * Inicia uma solicitação.
     * 
     * Método: startProcess.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     * - Número da atividade;
     * - Usuário destino;
     * - Comentários;
     * - Código do usuário;
     * - Indica se deve completar a tarefa (true) ou apenas salvar (false);
     * - Anexos do processo;
     * - Dados do formulário;
     * - Apontamento da atividade;
     * - Indica se o usuário está acessando a tarefa como Gestor do processo;
     */
    private void startProcess() throws Exception {
        System.out.println("\nIniciando startProcess()\n");

        StringArrayArray resultArray = workflowEngineService.startProcess(this.userLogin,
                this.userPassword, this.tenantId, this.processId, this.activity, this.createDestinationUser(),
                this.requestComment, this.userId, this.completeTask, this.createProcessAttachment(),
                this.createFormFieldStringValue(), this.createAppointment(), this.isManager);

        for (StringArray result : resultArray.getItem()) {
            System.out.println(result.getItem().get(0) + ": " + result.getItem().get(1));
        }

        System.out.println("\nTerminou startProcess()");
    }
    
    /**
     * Inicia uma solicitação.
     * 
     * Método: startProcessClassic.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do processo;
     * - Número da atividade;
     * - Usuário destino;
     * - Comentários;
     * - Código do usuário;
     * - Indica se deve completar a tarefa (true) ou apenas salvar (false);
     * - Anexos do processo;
     * - Dados do formulário;
     * - Apontamento da atividade;
     * - Indica se o usuário está acessando a tarefa como Gestor do processo;
     */
    private void startProcessClassic() throws Exception {
        System.out.println("\nIniciando startProcessClassic()\n");

        KeyValueDtoArray result = workflowEngineService.startProcessClassic(this.userLogin,
                this.userPassword, this.tenantId, this.processId, this.activity, this.createDestinationUser(),
                this.requestComment, this.userId, this.completeTask, this.createProcessAttachment(),
                this.createFormFieldKeyValue(), this.createAppointment(), this.isManager);

        for (KeyValueDto keyValueDtoa : result.getItem()) {
            System.out.println(keyValueDtoa.getKey() + ": " + keyValueDtoa.getValue());
        }

        System.out.println("\nTerminou startProcessClassic()");
    }
    
    /**
     * Assume uma tarefa.
     * 
     * Método: takeProcessTask.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Número da solicitação;
     * - Sequência da Thread;
     */
    private void takeProcessTask() throws Exception {
        System.out.println("\nIniciando takeProcessTask()\n");

        String result = workflowEngineService.takeProcessTask(this.userLogin, this.userPassword,
                this.tenantId, this.userId, this.processInstanceId, this.stateSequence);

        System.out.println(result);

        System.out.println("\nTerminou takeProcessTask()");
    }
    
    /**
     * Assume uma tarefa.
     * 
     * Método: takeProcessTaskByReplacement.
     * 
     * Parâmetros:
     * - Login do usuário;
     * - Senha do usuário;
     * - Código da empresa;
     * - Código do usuário;
     * - Número da solicitação;
     * - Sequência da Thread;
     * - Código do usuário substituto
     */
    private void takeProcessTaskByReplacement() throws Exception {
        System.out.println("\nIniciando takeProcessTaskByReplacement()\n");

        String result = workflowEngineService.takeProcessTaskByReplacement(this.userLogin, this.userPassword,
                this.tenantId, this.userId, this.processInstanceId, this.stateSequence, this.userReplacementId);

        System.out.println(result);

        System.out.println("\nTerminou takeProcessTask()");
    }

    private StringArray createDestinationUser() {
        StringArray stringArray = new StringArray();

        stringArray.getItem().add(this.userId);

        return stringArray;
    }
    
    private StringArrayArray createFormFieldStringValue() {
        StringArrayArray stringArrayArray = new StringArrayArray();
        
        StringArray field = new StringArray();
        field.getItem().add("nome"); // nome do campo.
        field.getItem().add("Leandro Carlos Pereira"); // valor do campo.
        
        stringArrayArray.getItem().add(field);
        
        return stringArrayArray;
    }
    
    private KeyValueDtoArray createFormFieldKeyValue() {
        KeyValueDtoArray keyValueDtoArray = new KeyValueDtoArray();

        KeyValueDto field = new KeyValueDto();
        field.setKey("nome"); // nome do campo.
        field.setValue("Leandro Carlos Pereira"); // valor do campo.

        keyValueDtoArray.getItem().add(field);

        return keyValueDtoArray;
    }
    
    private ProcessTaskAppointmentDtoArray createAppointment() throws DatatypeConfigurationException {
        ProcessTaskAppointmentDtoArray processTaskAppointmentDtoArray = new ProcessTaskAppointmentDtoArray();
        ProcessTaskAppointmentDto processTaskAppointmentDto = new ProcessTaskAppointmentDto();

        if (this.needAppointment) {
            processTaskAppointmentDto.setAppointmentDate(getDate());
            processTaskAppointmentDto.setAppointmentSeconds(this.time);

            processTaskAppointmentDtoArray.getItem().add(processTaskAppointmentDto);
        }

        return processTaskAppointmentDtoArray;
    }
    
    private ProcessAttachmentDtoArray createProcessAttachment() {
        ProcessAttachmentDto processAttachmentDto = new ProcessAttachmentDto();
        processAttachmentDto.setDocumentId(this.documentId); //Número do documento que será publicado como anexo. Para documento novo informar 0.
        processAttachmentDto.setVersion(this.documentVersion); //Versão do documento publicado como anexo
        processAttachmentDto.setOriginalMovementSequence(this.stateSequence); //Para startProcess informar sempre 1. Sequência da movimentação da solicitação em que o documento foi adicionado.
        processAttachmentDto.setColleagueId(this.userId); // Matrícula do usuário que adicionará o anexo
        processAttachmentDto.setCompanyId(this.tenantId); //Identificador da empresa cadastrada no Fluig
        processAttachmentDto.setDescription(documentDescription); // Descrição do anexo
        processAttachmentDto.setFileName(getArchive().getName()); //Nome do arquivo principal do anexo
        processAttachmentDto.setDeleted(false); //O anexo será deletado?
        processAttachmentDto.setPermission("1"); //Informar sempre 1.
        processAttachmentDto.setNewAttach(true); //True: Novo documento publicado como anexo. False: Documento já existe no fluig.

        Attachment attachment = new Attachment();
        attachment.setAttach(true); //Informar true para criar anexos no processo.
        attachment.setFileName(this.getArchive().getName()); //Nome do arquivo do anexo
        attachment.setPrincipal(false); //Anexo principal?
        attachment.setFilecontent(this.fileArrayBytes); //Informar os bytes do anexo

        // Adiciona anexos no array de anexos do processo.
        processAttachmentDto.getAttachments().add(attachment);

        ProcessAttachmentDtoArray processAttachmentDtoArray = new ProcessAttachmentDtoArray();
        processAttachmentDtoArray.getItem().add(processAttachmentDto);

        return processAttachmentDtoArray;
    }
    
    public XMLGregorianCalendar getDate() throws DatatypeConfigurationException {
        XMLGregorianCalendar appointment = DatatypeFactory.newInstance().newXMLGregorianCalendar();

        appointment.setYear(2017);
        appointment.setMonth(03);
        appointment.setDay(01);
        appointment.setHour(0);
        appointment.setMinute(0);
        appointment.setSecond(0);

        return appointment;
    }
    
    private File getArchive() {
        File file = new File(this.filePath);

        if (file.exists()) {
            try {
                byte[] buffer = new byte[8192];
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis, 8192);

                ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.length());
                int len = 0;
                while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, len);
                }

                this.fileArrayBytes = baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Arquivo " + this.filePath + " não encontrado.");
            return null;
        }

        return file;
    }
    
    private void accessConfig() {
        BindingProvider bp = (BindingProvider) this.workflowEngineService;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.fluigURL + "/webdesk/ECMWorkflowEngineService");
    }

    private WorkflowEngineService instanceWorkflowEngineService() {
        return new ECMWorkflowEngineServiceService().getWorkflowEngineServicePort();
    }
}