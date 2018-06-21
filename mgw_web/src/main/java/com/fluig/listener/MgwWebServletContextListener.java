package com.fluig.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.fluig.job.DocumentUpdateJob;

public class MgwWebServletContextListener implements ServletContextListener {

	protected Logger log = Logger.getLogger(MgwWebServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("ServletContextListener destroyed");
		
		Scheduler scheduler = null;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			log.warn(e);
		}
		try {
			scheduler.unscheduleJob(new TriggerKey("trigger", "doc_ged_update"));
			log.info("Remove trigger doc_ged_update");
		} catch (SchedulerException e) {
			log.warn(e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("ServletContextListener started");
		
		JobDetail jobDetail = JobBuilder.newJob(DocumentUpdateJob.class)
				.withIdentity("job", "doc_ged_update")
				.build();
		
		SimpleScheduleBuilder simpleScheduler = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInMinutes(5)
				.repeatForever();

		Trigger trigger = TriggerBuilder.newTrigger()
			.withIdentity("trigger", "doc_ged_update")
			.withSchedule(simpleScheduler)
			.build();
		
		Scheduler scheduler = null;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			log.warn(e);
		}
		
		try {
			scheduler.scheduleJob(jobDetail, trigger);
			log.info("Add trigger doc_ged_update");
		} catch (SchedulerException e) {
			log.warn(e);
		}
		
		try {			
			scheduler.start();
		} catch (SchedulerException e) {
			log.warn(e);
		}
	}
	
}
