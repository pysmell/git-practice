package com.meiya.quartz.example12;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteServerExample {

    Logger logger = LoggerFactory.getLogger(RemoteServerExample.class);

    public void run() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        logger.info("-------------Initialization Complete----------");

        logger.info("------- (Not Scheduling any Jobs - relying on a remote client to schedule jobs --");

        logger.info("------- Starting Scheduler ----------------");

        // start the schedule
        scheduler.start();

        logger.info("------- Started Scheduler -----------------");

        logger.info("------- Waiting ten minutes... ------------");

        // wait five minutes to give our jobs a chance to run
        try {
            Thread.sleep(600L * 1000L);
        } catch (Exception e) {
            //
        }

        // shut down the scheduler
        logger.info("------- Shutting Down ---------------------");
        scheduler.shutdown(true);
        logger.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws SchedulerException {
        System.getProperties().put("org.quartz.properties","server.properties");
        RemoteServerExample example = new RemoteServerExample();
        example.run();
    }
}




















































