package com.meiya.quartz.example11;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class LoadExample {

    private static Logger logger = LoggerFactory.getLogger(LoadExample.class);

    private int numberOfJobs = 500;

    public LoadExample(int inNumberOfJobs) {
        numberOfJobs = inNumberOfJobs;
    }

    public void run() throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        logger.info("initialization Complete");

        for (int count = 1; count <= numberOfJobs; count++) {
            JobDetail job = newJob(SimpleJob.class).withIdentity("job" + count, "group1").requestRecovery().build();
            long timeDelay = (long) (Math.random() * 2500);
            job.getJobDataMap().put(SimpleJob.DELAY_TIME, timeDelay);

            Trigger trigger = newTrigger().withIdentity("trigger" + count, "group1").
            startAt(futureDate(10000 + (count * 100), DateBuilder.IntervalUnit.MILLISECOND)).build();
            scheduler.scheduleJob(job, trigger);

            if (count % 25 == 0) {
                logger.info("...scheduled " + count + "jobs");
            }
        }

        scheduler.start();

        logger.info("------- Started Scheduler -----------------");

        logger.info("------- Waiting five minutes... -----------");

        // wait five minutes to give our jobs a chance to run
        try {
            Thread.sleep(300L * 1000L);
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

        int numberOfJobs = 500;
        if (args.length == 1) {
            numberOfJobs = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            System.out.println("Usage: java " + LoadExample.class.getName() + "[# of jobs]");
            return;
        }
        LoadExample example = new LoadExample(numberOfJobs);
        example.run();
    }
}



























































