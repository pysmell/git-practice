package com.meiya.quartz.example5;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class MisfireExample {

    private static Logger logger = LoggerFactory.getLogger(MisfireExample.class);

    public void run() throws SchedulerException {

        logger.info("------Initializing--------------------");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        logger.info("Initialization Complete");

        logger.info("------------Scheduling Jobs-----------------");
        Date startTime = nextGivenSecondDate(null, 15);

        JobDetail job = newJob(StatefulDumbJob.class).withIdentity("job1", "group1").
                usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L).build();

        SimpleTrigger trigger = newTrigger().withIdentity("trigger1","group1").startAt(startTime).
                withSchedule(simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();

        Date ft = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + "will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times,every "
        + trigger.getRepeatInterval() / 1000 + "seconds");

        job = newJob(StatefulDumbJob.class).withIdentity("job2", "group1").
                usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L).build();

        trigger = newTrigger().withIdentity("trigger2", "group1").startAt(startTime).
        withSchedule(simpleSchedule().withIntervalInSeconds(3).repeatForever().withMisfireHandlingInstructionNowWithExistingCount()).build();
        ft  = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + "will run at: " + ft + " and repeat: " + trigger.getRepeatCount()
        + " times, every " + trigger.getRepeatInterval() / 1000 + "seconds" );

        logger.info("-----------Starting Scheduler---------");
        scheduler.start();
        logger.info("-----------Started Scheduler----------");

        try {
            Thread.sleep(600L * 1000L);
        } catch (Exception e) {

        }
        logger.info("-------------Shutting down----------------");

        scheduler.shutdown(true);

        logger.info("-------------Shutdown Complete-------------");

        SchedulerMetaData schedulerMetaData = scheduler.getMetaData();
        logger.info("Executed " + schedulerMetaData.getNumberOfJobsExecuted() + " jobs");
    }

    public static void main(String[] args) throws SchedulerException {
        MisfireExample example = new MisfireExample();
        example.run();
    }
}








































