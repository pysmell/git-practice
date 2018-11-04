package com.meiya.quartz.example1;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleExample {

    public void run() throws SchedulerException {
        Logger logger = LoggerFactory.getLogger(SimpleExample.class);
        logger.info("--------------------Initializing----------------------");

        //first we must get a reference to a scheduler
        //程序首先获取调度程序的实例，这是通过创建一个StdSchedulerFactory然后使用他来创建一个调度器来完成的。
        //这将创建一个简单的基于Ram的调度程序
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        logger.info("--------------------Initialization Complete------------");

        //创建一个SimpleTrigger，它会在下一个分钟发射
        Date runTime = evenMinuteDate(new Date());

        //define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

        //Trigger the job to run on the next round minute
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        //Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + runTime);
        //启动调度程序（没有任何东西可以实际运行，直到
        //调度程序已经启动）
        scheduler.start();
        logger.info("Started Scheduler");
        //wait long enough so that the scheduler as an opportunity to run the job

        logger.info("-----Waiting 90 seconds");

        try {
            Thread.sleep(6500L * 1000L);
        } catch (Exception e) {

        }

        logger.info("shutting down");
        scheduler.shutdown(true);
        logger.info("Shutdown Complete");
    }

    public static void main(String[] args) throws SchedulerException {

        SimpleExample simpleExample = new SimpleExample();
        simpleExample.run();
    }
}

















