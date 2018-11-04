package com.meiya.quartz.example6;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class JobExceptionExample {
    Logger logger = LoggerFactory.getLogger(JobExceptionExample.class);

    public void run() throws SchedulerException {
        logger.info("----------Initializing------------------");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        logger.info("--------------Initialization Complete-------------");

        logger.info("-------------Scheduling Jobs-----------------");

        Date startTime = nextGivenSecondDate(null, 15);

        JobDetail job = newJob(BadJob1.class).withIdentity("badJob1", "group1").
        usingJobData("denominator", "0").build();

        SimpleTrigger simpleTrigger = newTrigger().withIdentity("trigger1", "group1").
        startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();

        Date ft = scheduler.scheduleJob(job, simpleTrigger);

        logger.info(job.getKey() + " will  run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times,every "
        + simpleTrigger.getRepeatInterval() / 1000 + "seconds");

        job = newJob(BadJob2.class).withIdentity("badJob2", "group1").build();

        simpleTrigger = newTrigger().withIdentity("trigger2", "group1").
        startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

        ft  = scheduler.scheduleJob(job, simpleTrigger);
        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times,every " + simpleTrigger.getRepeatInterval() / 1000 + " seconds");

        logger.info("--------Starting Scheduler----------");
        scheduler.start();
        logger.info("--------Started Scheduler----------");
        try {
            Thread.sleep(30L * 1000L);
        } catch (Exception e) {

        }
        logger.info("--------Stopping Scheduler----------");
        scheduler.shutdown(true);
        logger.info("--------Stopped Scheduler-----------");
        SchedulerMetaData schedulerMetaData = scheduler.getMetaData();
        logger.info("Executed" + schedulerMetaData.getNumberOfJobsExecuted() + "jobs");
    }

    public static void main(String[] args) throws SchedulerException {

        JobExceptionExample jobExceptionExample = new JobExceptionExample();
        jobExceptionExample.run();

    }
}









































