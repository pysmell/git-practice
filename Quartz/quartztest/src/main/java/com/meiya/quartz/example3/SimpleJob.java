package com.meiya.quartz.example3;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleJob implements Job {

    Logger logger = LoggerFactory.getLogger(SimpleJob.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        logger.info("SimpleJob say: " + jobKey + " run at the " + new Date());
    }
}























































