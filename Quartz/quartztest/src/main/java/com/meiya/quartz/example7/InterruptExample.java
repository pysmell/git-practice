package com.meiya.quartz.example7;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class InterruptExample {

    private static Logger logger = LoggerFactory.getLogger(InterruptExample.class);

    public void run() throws SchedulerException {
        logger.info("-----Initializing----");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        logger.info("-----initializing Complete");

        logger.info("-----------Scheduling Jobs");

        Date startTime = nextGivenSecondDate(null, 15);

        JobDetail job = newJob(DumbInterruptableJob.class).withIdentity("job1", "group1").build();

        SimpleTrigger simpleTrigger = newTrigger().withIdentity("trigger1", "group1").
        startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();

        Date ft = scheduler.scheduleJob(job, simpleTrigger);
        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times,every " + simpleTrigger.getRepeatInterval() / 1000 + "seconds");

        scheduler.start();
        logger.info("--------Started Scheduler----------");
        for (int i = 0;i < 50; i++) {
            try {
                Thread.sleep(7000L);
                scheduler.interrupt(job.getKey());
            } catch (Exception e) {

            }
        }
        logger.info("-------------Shutting Down----------------");
        scheduler.shutdown(true);
        logger.info("-------------Shutdown Complete------------");

        SchedulerMetaData schedulerMetaData = scheduler.getMetaData();
        logger.info("Executed " + schedulerMetaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws SchedulerException {
        InterruptExample interruptExample = new InterruptExample();
        interruptExample.run();
    }
}


























































