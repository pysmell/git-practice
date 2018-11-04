package com.meiya.quartz.example9;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class ListenerExample {

    Logger logger = LoggerFactory.getLogger(ListenerExample.class);
    public void run() throws SchedulerException {
        logger.info("------------------Initializing-----------------");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        logger.info("------------------Initialization Complete-----------------");

        logger.info("--------------Scheduling Jobs---------------");

        JobDetail job = newJob(SimpleJob1.class).withIdentity("job1").build();
        Trigger trigger = newTrigger().withIdentity("trigger1").startNow().build();

        JobListener jobListener = new Job1Listener();
        Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getKey());
        scheduler.getListenerManager().addJobListener(jobListener, matcher);

        scheduler.scheduleJob(job, trigger);

        logger.info("-----------------Starting Scheduler-----------");
        scheduler.start();

        logger.info("------- Waiting 30 seconds... --------------");
        try {
            // wait 30 seconds to show jobs
            Thread.sleep(30L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        // shut down the scheduler
        logger.info("------- Shutting Down ---------------------");
        scheduler.shutdown(true);
        logger.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws SchedulerException {
        ListenerExample listenerExample = new ListenerExample();
        listenerExample.run();
    }
}

/*
* ListenerManager
* 通过ListenerManager向scheduler中添加我们的监听器，常用方法有
* 1、public void addJobListener(JobListener jobListener)
* 添加全局监听器，即所有JobDetail都会被此监听器监听
* 2、public void addJobListener(JobListener jobListener, Matcher matcher)
* 添加带条件匹配的监听器，在matcher中声明我们的匹配条件
* 3、public void addJobListener(JobListener jobListener, Matcher...matchers)
* 添加附带不定参条件匹配的监听器
* 4、public boolean removeJobListener(String name)
* 根据名字移除JobListener
* 5、public List getJobListeners()
* 获取所有的监听器
* 6、public JobListener getJobListener(String name)
* 根据名字获取监听器
*
*
* matcher让不同的监听器监听不同的任务，有很多实现类
* 1、KeyMatcher<JobKey>
* 根据JobKey进行匹配，每个JobDetail都有一个对应的JobKey，里面储存了JobName和JobGroup来定位
* 唯一的JobDetail，常用方法有
* 构造Matcher方法
* KeyMatcher<JobKey> keyMatcher = KeyMatcher.keyEquals(pickNewsJob.getKey());//构造匹配pickNewsJob中的JobKey的keyMatcher。
*
* 使用方法
* scheduler.getListenerManager().addJobListener(myJobListener, keyMatcher);//通过这句完成我们监听器对pickNewsJob的唯一监听
*
* 2、GroupMatcher
* 根据组名信息匹配，他的常用方法有
* GroupMatcher<JobKey> groupMatcher = Group.jobGroupContains("group1");  //包含特定的字符串
* GroupMatcher.groupEndsWith("oup1") //以特定字符串结尾
* GroupMatcher.groupEquals("jgroup") //以特定字符串完全匹配
* GroupMathcher.groupStartsWith("jgou") //以特定字符串开头
*
*
* AndMatcher
* 对两个匹配器取交集
* KeyMatcher<JobKey> keyMatcher = KeyMatcher.keyEquals(job.getKey());
* GroupMatcher<JobKey> groupMatcher = GroupMatcher.jobGroupContains("group1");
* AndMatcher<JobKey> andMatcher = AndMatcher.and(keyMatcher.groupMatcher); 通过满足两个入参匹配
*
*
* OrMatcher 对两个匹配器取并集
* OrMatcher<JobKey> orMatcher = orMatcher.or(KeyMatcher.groupMatcher);  //满足任意一个即可
*
* */














































































