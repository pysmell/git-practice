package com.meiya.quartz.example5;

import org.quartz.*;

import java.util.Date;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class StatefulDumbJob implements Job {

    public static final String NUM_EXECUTIONS = "NumExecutions";

    public static final String EXECUTION_DELAY = "ExecutionDelay";

    public StatefulDumbJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.err.println("---" + jobExecutionContext.getJobDetail().getJobDataMap());

        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();

        int executeCount = 0;
        if (map.containsKey(NUM_EXECUTIONS)) {
            executeCount = map.getInt(NUM_EXECUTIONS);
        }
        executeCount++;

        map.put(NUM_EXECUTIONS, executeCount);
        long delay = 5000l;
        if (map.containsKey(EXECUTION_DELAY)) {
            delay = map.getLong(EXECUTION_DELAY);
        }

        try {
            Thread.sleep(delay);
        } catch (Exception ignore) {

        }
        System.err.println("---" + jobExecutionContext.getJobDetail().getKey() + "run at: " + (new Date()) + "times complete (" + executeCount + ").");
    }

}


































