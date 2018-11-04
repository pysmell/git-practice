package com.meiya.quartz.example11;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(SimpleJob.class);

    public static final String DELAY_TIME = "delay time";

    public SimpleJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        logger.info("Executing job: " + jobKey + " executing at " + new Date());
        long delayTime = jobExecutionContext.getJobDetail().getJobDataMap().getLong(DELAY_TIME);
        try {
            Thread.sleep(delayTime);
        } catch (Exception e) {

        }

        logger.info("Finished Executing job: " + jobKey + " at " + new Date());
    }
}




























