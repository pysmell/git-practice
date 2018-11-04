package com.meiya.quartz.example10;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlugInExample {

    private static Logger logger = LoggerFactory.getLogger(PlugInExample.class);

    public void run() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        logger.info("-----------Initialization Complete--------");
        logger.info("--------Not Scheduling any Jobs - relying on xml definitions---------");

        logger.info("---------Starting Scheduler---------------");

        scheduler.start();

        logger.info("------Started Scheduler-------");

        logger.info("------------Waiting five minutes---------");

        try {
            Thread.sleep(300L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("---------Shutting Down----------");
        scheduler.shutdown(true);

        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs");
    }

    public static void main(String[] args) throws SchedulerException {
        PlugInExample plugInExample = new PlugInExample();
        plugInExample.run();
    }
}


























