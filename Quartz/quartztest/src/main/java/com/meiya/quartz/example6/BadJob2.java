package com.meiya.quartz.example6;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class BadJob2 implements Job {

    //Logging
    private static Logger logger = LoggerFactory.getLogger(BadJob2.class);
    private  int calculation;

    public BadJob2() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        logger.info("---" + jobKey + " executing at " + new Date());
        try {
            int zero = 0;
            calculation = 4815 / zero;
        } catch (Exception e) {
            logger.info("----error in job!");
            JobExecutionException jobExecutionException = new JobExecutionException(e);
            jobExecutionException.setUnscheduleAllTriggers(true);
            throw jobExecutionException;
        }

        logger.info("---" + jobKey + " completed at " + new Date());
    }
}

















