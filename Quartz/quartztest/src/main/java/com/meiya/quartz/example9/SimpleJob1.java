package com.meiya.quartz.example9;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleJob1 implements Job {
    private static Logger logger = LoggerFactory.getLogger(SimpleJob1.class);

    public SimpleJob1() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        logger.info("SimpleJob1 says: " + jobKey + " run at: " + new Date());
    }
}

























