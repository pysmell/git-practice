package com.meiya.quartz.example6;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class BadJob1 implements Job {

    private static Logger logger = LoggerFactory.getLogger(BadJob1.class);

    private  int calculation;

    public BadJob1() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        int denominator = dataMap.getInt("denominator");
        logger.info("---" + jobKey + " executing at " + new Date() + " with denominator " + denominator);

        try {
            calculation = 4815 / denominator;
        } catch (Exception e) {
            logger.info("---Error in job!");
            JobExecutionException executionException = new JobExecutionException(e);
            dataMap.put("denominator", 1);
            executionException.setRefireImmediately(true);
            throw executionException;
        }

        logger.info("---" + jobKey + " complete at " + new Date() + "result:" + calculation);
    }
}



/*
* 此示例旨在如何处理作业执行异常。Quartz中的作业被允许抛出一个JobExecutionExceptions。
* 当抛出这个异常时，可以指示quartz采取什么行动。
* */








































