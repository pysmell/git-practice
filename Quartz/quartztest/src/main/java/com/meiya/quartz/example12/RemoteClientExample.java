package com.meiya.quartz.example12;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class RemoteClientExample {

    private static Logger logger = LoggerFactory.getLogger(RemoteClientExample.class);

    public void run() throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = newJob(SimpleJob.class).withIdentity("remotelyAddedJob2", "default").build();
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("msg", "Your remotely added job has executed!");

        Trigger trigger = newTrigger().withIdentity("remotelyAddedTrig2", "default").forJob(job.getKey()).withSchedule(cronSchedule("/5 * * ? * *")).build();
        scheduler.scheduleJob(job, trigger);
        logger.info("Remote job scheduled");
    }

    public static void main(String[] args) throws SchedulerException {
        System.getProperties().put("org.quartz.properties","client.properties");
        RemoteClientExample example = new RemoteClientExample();
        example.run();
    }
}


























