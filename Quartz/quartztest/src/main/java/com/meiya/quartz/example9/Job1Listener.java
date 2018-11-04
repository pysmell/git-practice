package com.meiya.quartz.example9;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class Job1Listener implements JobListener {

    private static Logger logger = LoggerFactory.getLogger(Job1Listener.class);

    //返回监听器的名称，注册在JobDetail上的监听器名称必须匹配从监听器上getName()方法的返回值
    public String getName() {
        return "job1_to_job2";
    }
    //Scheduler在JobDetail将要被执行时调用这个方法
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        logger.info("Job1Listener says: Job is about to be executed");
    }
    //Scheduler在JobDetail即将被执行，但又被TriggerListener否决了时调用该方法
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        logger.info("Job1Listener says: Job is about to be vetoed");
    }
    //Scheduler在JobDetail被执行之后调用这个方法
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        logger.info("Job1Listener says:Job was executed");
        JobDetail job2 = newJob(SimpleJob2.class).withIdentity("job2").build();
        Trigger trigger = newTrigger().withIdentity("job2Trigger").startNow().build();
        try {
            jobExecutionContext.getScheduler().scheduleJob(job2, trigger);
        } catch (Exception e1) {
            logger.warn("Unable to schedule job2!");
            e.printStackTrace();
        }
    }
}



























































