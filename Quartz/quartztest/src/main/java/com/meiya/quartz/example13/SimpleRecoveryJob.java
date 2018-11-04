package com.meiya.quartz.example13;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleRecoveryJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(SimpleRecoveryJob.class);

    private static final String COUNT = "count";

    public SimpleRecoveryJob() {

    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();

        if (jobExecutionContext.isRecovering()) {
            logger.info("SimpleRecoveryJob: " + jobKey + "RECOVERING at " + new Date());
        } else {
            logger.info("SimpleRecoveryJob: " + jobKey + "starting at " + new Date());
        }

        long delay = 10L * 1000L;
        try {
           Thread.sleep(delay);
        } catch (Exception e) {

        }

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        int count;
        if (dataMap.containsKey(COUNT)) {
            count = dataMap.getInt(COUNT);
        } else {
            count = 0;
        }

        count ++ ;

        dataMap.put(COUNT, count);

        logger.info("SimpleRecoveryJob: " + jobKey + " done at " + new  Date() + "\n Execution #" + count);

    }
}









