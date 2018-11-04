package com.meiya.quartz.example4;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class JobStateExample {

    private static Logger logger = LoggerFactory.getLogger(JobStateExample.class);

    public void run() throws SchedulerException {
        logger.info("---------Initializing----------");

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        logger.info("----------Initialization Complete----");
        logger.info("----------Scheduling Jobs------------");

        Date startTime = nextGivenSecondDate(null, 10);

        JobDetail jobOne = newJob(ColorJob.class).withIdentity("job1", "group1").build();

        SimpleTrigger simpleTriggerOne = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1").startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build();

        jobOne.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Green");

        jobOne.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        Date scheduleTimeOne = scheduler.scheduleJob(jobOne, simpleTriggerOne);

        logger.info(simpleTriggerOne.getKey() + " will run at: " + scheduleTimeOne + " and repeat: " + simpleTriggerOne.getRepeatCount()
                + " times, every " + simpleTriggerOne.getRepeatInterval() / 1000 + " seconds");

        JobDetail jobTwo = newJob(ColorJob.class).withIdentity("job2", "group1").build();

        SimpleTrigger triggerTwo = (SimpleTrigger)newTrigger().withIdentity("trigger2", "group1").startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build();

        jobTwo.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Red");
        jobTwo.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        // schedule the job to run
        Date scheduleTime2 = scheduler.scheduleJob(jobTwo, triggerTwo);
        logger.info(jobTwo.getKey().toString() + " will run at: " + scheduleTime2 + " and repeat: " + triggerTwo.getRepeatCount()
                + " times, every " + triggerTwo.getRepeatInterval() / 1000 + " seconds");

        logger.info("------- Starting Scheduler ----------------");
        scheduler.start();

        logger.info("------- Started Scheduler -----------------");

        logger.info("------- Waiting 60 seconds... -------------");
        try {
            // wait five minutes to show jobs
            Thread.sleep(60L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        logger.info("------- Shutting Down ---------------------");

        scheduler.shutdown(true);

        logger.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws SchedulerException {

        JobStateExample jobStateExample = new JobStateExample();
        jobStateExample.run();

    }
}



























