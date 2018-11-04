package com.meiya.quartz.example10;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Set;

public class SimpleJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(SimpleJob.class);

    public SimpleJob() {
    }

    @SuppressWarnings("unchecked")
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        logger.info("Executing job: " + jobKey + " executing at " + new Date() + ", fired by: " +jobExecutionContext.getTrigger().getKey());
        //Trigger中也可以设置JobDataMap属性，这是为了在多个Trigger中使用相同的Job，JobExecutionContext将会合并
        //JobDetail与Trigger的JobDataMap，如果其中属性名相同，后者覆盖前者，
        //可以使用JobExecutionContext.getMergedJobDataMap()方法来获取合并后的JobDataMap
        if (jobExecutionContext.getMergedJobDataMap().size() > 0) {
            Set<String> keys = jobExecutionContext.getMergedJobDataMap().keySet();
            for (String key :keys) {
                String val = jobExecutionContext.getMergedJobDataMap().getString(key);
                logger.info(" jobDataMap entry: " + key + " = " + val);
            }
        }

        jobExecutionContext.setResult("hello");
    }
}

/*
* @DisallowConcurrentExecution，如果使用该注解，那么同一时间将只有一个Job实例被执行。如，ReportJob有个实例为ReportForJoe，那么同一时间只有一个ReportForJoe被执行。而ReportForMike等都可以执行。
* @PersistJobDataAfterExecution，如果使用该注解，在Job被执行结束后，将会更新JobDataMap，这样下次Job执行后就会使用新的值而不是初始值。
* 如果使用@PersistJobDataAfterExecution注解，推荐也使用@DisallowConcurrentExecution注解，这是为了避免并发问题导致数据紊乱。
*
*
*Durability，持久性；如果Job是非持久性的，一旦没有Trigger与其相关联，它就会从Scheduler被删除。也就是说Job的生命周期和其Trigger是关联的。
*RequestsRecovery，如果为true，那么在Scheduler异常中止或者系统异常关闭后，当Scheduler重启后，Job会被重新执行。
JobExecutionException
execute()方法只允许抛出JobExecutionException异常
*
*
* */































