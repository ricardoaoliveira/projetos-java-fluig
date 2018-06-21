package com.fluig.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fluig.webservice.ECMDatasetService;

public class DocumentUpdateJob implements Job{

	protected Logger log = Logger.getLogger(DocumentUpdateJob.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("DocumentUpdateJob execute");
		
		ECMDatasetService ecmDatasetService = new ECMDatasetService();
		try {
			ecmDatasetService.updateConfiguracaoValidadeDocumento();
			log.info("updateConfiguracaoValidadeDocumento");
		} catch (Exception e) {
			log.warn(e);
		}
	}

}
