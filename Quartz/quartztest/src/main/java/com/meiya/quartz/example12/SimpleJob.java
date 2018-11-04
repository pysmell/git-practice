package com.meiya.quartz.example12;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleJob implements Job {

    public static final String MESSAGE = "msg";

    private static Logger logger = LoggerFactory.getLogger(SimpleJob.class);

    public SimpleJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        String message = (String) jobExecutionContext.getJobDetail().getJobDataMap().get(MESSAGE);

        logger.info("SimpleJob: " + jobKey + " executing at " + new Date());
        logger.info("SimpleJob: msg " + message);
    }
}

















































