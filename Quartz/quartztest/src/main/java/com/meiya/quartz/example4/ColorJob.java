package com.meiya.quartz.example4;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 作业的多个实例将不被允许同时运行
 * 注释导致行为就像他们的名字描述一样 - 作业的多个实例将不被允许同时运行（考虑一个作业的*execute（）方法中有代码需要运行34秒的情况，但是它的计划是使用触发器每30秒重复一次），并在每*次执行后将其JobDataMap内容重新保留在调度程序的JobStore*中。就本示例而言，只有@PersistJobDataAfterExecution *注释是真正相关的，但使用@DisallowConcurrentExecution注释总是明智的，以防止保存数据的竞争条*件。
 *
 *Quartz每次执行job，job永远是全新的对象，但是，如果job实现StatefulJob接口，而不是job接口
 *此时JobDetail的JobDataMap将会共享一个对象，也可以通过注解@PersistJobDataAfterExecution，
 * 其他的比如，job本身，Trigger等，还是每次执行的时候是全新的对象，所以只有JobDetail的JobDataMap是共*用的，由于Job和StatefulJob在框架中使用中存在两个关键差异，首先，JobDataMap在每次执行之后重新持久*daoJobStore中，这样就能确保对job数据的改变直到下次执行依旧保持着
 *
 */

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ColorJob implements Job {

    private static  Logger logger = LoggerFactory.getLogger(ColorJob.class);

    public static final String FAVORITE_COLOR = "favorite color";
    public static final String EXECUTION_COUNT = "count";

    private int counter = 1;

    public ColorJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String favoriteColor = dataMap.getString(FAVORITE_COLOR);
        int count = (Integer) dataMap.getInt(EXECUTION_COUNT);
        logger.info("ColorJob: " + jobKey + " executing at " + new Date() + "\n" + " favorite color is " + favoriteColor + " execution count (from job map) is " + count + "\n" + " execution count (from job member variable) is " + counter);
        count++;
        dataMap.put(EXECUTION_COUNT, count);

        counter++;
    }
}


















































