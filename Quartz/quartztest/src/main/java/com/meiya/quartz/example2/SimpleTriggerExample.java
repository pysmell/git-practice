package com.meiya.quartz.example2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleTriggerExample {
    public  void run() throws SchedulerException {

        Logger logger = LoggerFactory.getLogger(SimpleTriggerExample.class);
        //程序首先获取调度程序的实例，这是通过创建一个StdSchedulerFactory然后使用他来创建一个调度器来完成的。
        //这将创建一个简单的基于Ram的调度程序
        logger.info("----initializing----");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        logger.info("----initialization Complete----");
        logger.info("----scheduling jobs----");

        //第一个参数开始的日期时间，第二个参数输入的基准时的间隔时间，如果secondBase为0，间隔为1分钟
        //第一个参数为null，即当前时间
        Date startTime = DateBuilder.nextGivenSecondDate(null, 15);

        //定义一个任务
        JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();

        //定义一个触发器
        SimpleTrigger simpleTrigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1").startAt(startTime).build();

        //schedule it to run!
        Date ft = scheduler.scheduleJob(job, simpleTrigger);

        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times, every " + simpleTrigger.getRepeatInterval() / 1000 + "seconds");

        job = newJob(SimpleJob.class).withIdentity("job2", "group1").build();

        simpleTrigger = (SimpleTrigger) newTrigger().withIdentity("trigger2", "group1").startAt(startTime).build();

        ft = scheduler.scheduleJob(job, simpleTrigger);

        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times, every " + simpleTrigger.getRepeatInterval() / 1000  + " seconds");

        //工作会运行11次（运行一次，重复10次以上）
        //重复的工作会每10秒
        job = newJob(SimpleJob.class).withIdentity("job3", "group1").build();

        simpleTrigger = newTrigger().withIdentity("trigger3", "group1").startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();

        ft = scheduler.scheduleJob(job, simpleTrigger);

        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times, every " + simpleTrigger.getRepeatInterval() / 1000 + " seconds");

        simpleTrigger = newTrigger().withIdentity("trigger3", "group2").startAt(startTime).
                withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(2)).forJob(job).build();

        ft = scheduler.scheduleJob(simpleTrigger);

        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times, every " + simpleTrigger.getRepeatInterval() / 1000 + " seconds");

        job = newJob(SimpleJob.class).withIdentity("job4", "group1").build();

        simpleTrigger = newTrigger().withIdentity("trigger4", "group1").startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(5)).build();

        ft = scheduler.scheduleJob(job, simpleTrigger);

        logger.info(job.getKey() + " will run at" + ft + " and repeat: " + simpleTrigger.getRepeatCount()
        + " times, every" + simpleTrigger.getRepeatInterval() / 1000 + " seconds" );

        job = newJob(SimpleJob.class).withIdentity("job5","group1").build();

        //futureDate(5, DateBuilder.IntervalUnit.MINUTE) 5分钟后触发
        simpleTrigger = (SimpleTrigger) newTrigger().withIdentity("trigger5", "group1").startAt(futureDate(5, DateBuilder.IntervalUnit.MINUTE)).build();
        ft = scheduler.scheduleJob(job, simpleTrigger);
        logger.info(job.getKey() + " will run at" + ft + " and repeat: " + simpleTrigger.getRepeatCount()
        + "times, every" + simpleTrigger.getRepeatInterval() / 1000 + "seconds" );

        job = newJob().withIdentity("job6", "group1").build();

        simpleTrigger = newTrigger().withIdentity("trigger6", "group6").startAt(startTime).withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();

        ft = scheduler.scheduleJob(job, simpleTrigger);

        logger.info(job.getKey() + " will run at" + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times,every " + simpleTrigger.getRepeatInterval() / 1000 + "seconds");

        logger.info("starting scheduler");

        scheduler.start();

        logger.info("started scheduler");

        //在任务调度器开始之后，job7、触发器也会也被调到
        job = newJob(SimpleJob.class).withIdentity("job7", "group1").build();

        simpleTrigger = newTrigger().withIdentity("trigger7", "group1").startAt(startTime).
                withSchedule(simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();
        ft = scheduler.scheduleJob(job, simpleTrigger);

        logger.info(job.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + "times, every " + simpleTrigger.getRepeatInterval() / 1000 + "seconds");

        job = newJob(SimpleJob.class).withIdentity("job8", "group1").storeDurably().build();

        //添加一个job给调度器
        scheduler.addJob(job, true);

        //手动触发该任务
        scheduler.triggerJob(jobKey("job8", "group1"));

        //jobs can be re-scheduled...
        //job 7 will run immediately and repeat 20 times
        //如果服务器当前时间与表达式配置的执行时间差在两小时以内时，动态修改就会出现立即执行的情况
        //解决办法trigger.setStartTime(new Date)是表达式生效时间而非执行时间，使表达式从修改以后开始生效，就不会立即执行
        logger.info("---------------Rescheduling...------------------");
        simpleTrigger = newTrigger().withIdentity("trigger7", "group1").startAt(startTime).
               withSchedule(simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();
        //返回Date，如果返回null，说明替换失败，原因就是旧触发器没有找到，所以新的触发器也不会设置进去
        //替换失败的原因一般有两种：一种情况是传入的triggerKey没有与之匹配的，另外一种就是旧触发器的
        //触发时间已经全部完成，在触发完成后调度引擎会自动清除无用的触发器
        ft = scheduler.rescheduleJob(simpleTrigger.getKey(), simpleTrigger);

        logger.info("job7 rescheduled to run at: " + ft);

        logger.info("-----------Waiting 90 seconds--------------");

        try {
            //wait 90 seconds to show jobs
            Thread.sleep(90L * 1000L);
        } catch (Exception e) {

        }

        logger.info("-----shutting Down-----");
        scheduler.shutdown(true);
        logger.info("-----shutdown Complete------");

        //对scheduler实例提供了一些判断的方法
        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs");
    }

    public static void main(String[] args) throws SchedulerException {

        SimpleTriggerExample simpleTriggerExample = new SimpleTriggerExample();
        simpleTriggerExample.run();
    }

}

/*
* 1、job：job是任务的执行流程
* 2、jobDetail：JobDetail是job实例，是一个对象，包含了该实例的执行计划和所需要的数据
* 3、Trigger：Trigger是定时器，决定任务何时执行
* 4、Scheduler：调度器，调度器接受一组JobDetail+Trigger即可安排一个任务，其中一个JobDetail可以关联多个Trigger
* 一个Trigger只能对应一个JobDetail
*
* JobDetail job = JobBuilder.newJob(MyJob.class) // MyJob是我实现的Job类
*    .withIdentity("myjob") // 可以给该JobDetail起一个id，便于之后的检索。也可以 .withIdentity("myjob", "group1")
*    .requestRecovery() // 执行中应用发生故障，需要重新执行
*    .storeDurably() // 即使没有Trigger关联时，也不需要删除该JobDetail
*    .usingJobData("key1", "value1")
*    .usingJobData("key2", "value2") // 以Key-Value形式关联数据
*    .build();
*    Quartz因为考虑到有些任务不是幂等的，不可以多次重复执行，所以默认没有开启“requestRecovery”。当确认业务中允许一次任务执行两次的情况下，可以开启该选项，则任务肯定不会因为应用停止而漏调用，但缺点就是，有可能会重复调用。
*    每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中，可以通过context取出该数据，进行业务流程处理
*
*    Trigger trigger = TriggerBuilder.newTrigger()
*    .forJob("myjob") // 关联上述的JobDetail
*    .withIdentity("myjob-trigger1") // 给该Trigger起一个id
*    .startAt(DateBuilder.futureDate(20, IntervalUnit.SECOND)) // 延迟20秒开始
*    .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever()) // 每分钟触发一次，无限循环
*    .usingJobData("key3", "value3")
*    .usingJobData("key4", "value4") // 以Key-Value形式关联数据
*    .build();
*
*
*   org.quartz.threadPool.threadCount=10
*   org.quartz.scheduler.batchTriggerAcquisitionMaxCount=1
*
*   quartz 配置文件中默认的线程池的线程数，默认值是10，当执行任务会并发执行多个耗时任务，根据业务特点选择线程池的大小
*   当检查某个Trigger应该触发时，默认每次只Acquire一个
*
*   持久化
*   如果定时器在业务中属于关键数据，需要在故障重启后恢复状态，则需要把Quartz配置为持久化模式。默认情况下，所有定时任务和数据都保存在内存中，在应用重启后状态会消失。
*   JobStore决定了Quartz如何存储任务和触发器，默认值是org.quartz.simpl.RAMJobStore，我们需要把它配置为org.quartz.impl.jdbcjobstore.JobStoreTX，即可以使用JDBC数据源，把状态持久化到关系型数据库中。
*
*   之后，打开quartz-x.x.x.tar.gz包内的docs/dbTables文件夹内有各种数据库下的建表语句，直接在数据库中执行，把表先建好。
*   配置文件中增加上述对JobStore的设定后，代码不用修改一行，所有的任务和触发器已经是持久化的了，当应用停机重启后，错过的(misfire)任务和上次正在执行的(recovery)任务都会回复状态，如果JobDetail指定了requestRecovery，上次执行中，但没有执行完毕的任务会重新执行一遍。
* */






























