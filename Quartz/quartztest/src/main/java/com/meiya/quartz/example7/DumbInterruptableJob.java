package com.meiya.quartz.example7;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 中断job
 */
public class DumbInterruptableJob implements InterruptableJob {

    private static Logger logger = LoggerFactory.getLogger(DumbInterruptableJob.class);

    private boolean interrupted = false;

    private JobKey jobKey = null;

    public DumbInterruptableJob() {
    }

    public void interrupt() throws UnableToInterruptJobException {
        logger.info("----" + jobKey + " --- INTERRUPTING");
        interrupted = true;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobKey = jobExecutionContext.getJobDetail().getKey();

        logger.info("---" + jobKey + "executing at " + new Date());
        try {

        for (int i = 0;i < 4; i++) {
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {

            }
            if (interrupted) {
                    logger.info("----" + jobKey + "----Interrupted... bailing out!");
                    return;
                }
            }
        } finally {
            logger.info("---" + jobKey + " completed at " + new Date());
        }
    }
}







































