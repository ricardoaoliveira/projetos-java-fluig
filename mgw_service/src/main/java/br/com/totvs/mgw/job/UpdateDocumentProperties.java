package br.com.totvs.mgw.job;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.totvs.mgw.wsclients.ECMDatasetService;

@Singleton
public class UpdateDocumentProperties {
	
	Logger logger = LoggerFactory.getLogger(UpdateDocumentProperties.class);
	
	//private final String MSG_ERRO_TAREFAS_PENDENTES = "Existem tarefas pendentes para esse usu�rio.";
	
	@Schedule(minute = "*/10", dayOfWeek="*", hour="*", persistent = false)
	private void execute(final Timer t) {
		
		logger.info("Iniciando rotina de atualizacao de documentos");
		
		try {
			logger.info("Executando rotina de atualizacao de documentos.");
			
			ECMDatasetService service = new ECMDatasetService();
			service.updateConfiguracaoValidadeDocumento();
			
		} catch (Exception e) {
			logger.error("Erro na rotina de atualizacao de documentos.", e);
		}
		
		logger.info("Rotina de atualizacao de documentos finalizada.");
		
	}
	
}
