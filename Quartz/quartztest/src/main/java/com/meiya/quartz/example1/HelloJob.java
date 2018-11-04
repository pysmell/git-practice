package com.meiya.quartz.example1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class HelloJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(HelloJob.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("hello world!" + new Date());
    }
}
