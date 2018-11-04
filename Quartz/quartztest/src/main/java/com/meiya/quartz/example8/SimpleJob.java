package com.meiya.quartz.example8;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(SimpleJob.class);

    public SimpleJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey =  jobExecutionContext.getJobDetail().getKey();
        logger.info("SimpleJob says: " + jobKey + " executing at " + new Date());
    }
}



























































