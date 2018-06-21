package com.tutorial.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Executou!");
	}
	
	public static void main(String args[]) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("job", "grupo")
				.build();
		
		SimpleScheduleBuilder simpleScheduler = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(1)
				.repeatForever();

		Trigger trigger = TriggerBuilder.newTrigger()
			.withIdentity("trigger", "grupo")
			.withSchedule(simpleScheduler)
			.build();
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
		
		scheduler.unscheduleJob(new TriggerKey("trigger", "grupo"));
		
		scheduler.getContext();
	}

}
