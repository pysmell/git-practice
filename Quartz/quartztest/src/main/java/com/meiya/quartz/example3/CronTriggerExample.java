package com.meiya.quartz.example3;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class CronTriggerExample {

    private final Logger logger = LoggerFactory.getLogger(CronTriggerExample.class);

    public void run() throws SchedulerException {
        logger.info("---------------Initializing---------------");

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        logger.info("Initialization Complete");
        logger.info("Scheduling jobs");

        JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();
        //表示每隔20秒执行一次，0表示从0秒开始
        CronTrigger cronTrigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/20 * * * * ?")).build();
        Date ft = scheduler.scheduleJob(job, cronTrigger);

        logger.info(job.getKey() + "has been scheduled to run at: " + ft + " and repeat based on expression: " + cronTrigger.getCronExpression());

        job = newJob(SimpleJob.class).withIdentity("job2", "group1").build();
        //表示当前时间隔2分钟那时间的第15秒执行，每次隔2分钟执行
        cronTrigger = newTrigger().withIdentity("trigger2", "group1").
                withSchedule(cronSchedule("15 0/2 * * * ?")).build();

        ft = scheduler.scheduleJob(job, cronTrigger);

        logger.info(
                job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + cronTrigger.getCronExpression());

        //job 3 将在8am and 5pm时间之内 每隔2分钟执行一次
        job = newJob(SimpleJob.class).withIdentity("job3", "group1").build();
        cronTrigger = newTrigger().withIdentity("trigger3", "group1").withSchedule(cronSchedule("0 0/2 8-17 * * ?")).build();

        ft = scheduler.scheduleJob(job, cronTrigger);
        logger.info(
                job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + cronTrigger.getCronExpression());

        // job 4 will run every three minutes but only between 5pm and 11pm
        job = newJob(SimpleJob.class).withIdentity("job4", "group1").build();

        cronTrigger = newTrigger().withIdentity("trigger4", "group1").withSchedule(cronSchedule("0 0/3 17-23 * * ?")).build();

        ft = scheduler.scheduleJob(job, cronTrigger);
        logger.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                + cronTrigger.getCronExpression());

        // job 5 will run at 10am on the 1st and 15th days of the month
        //工作5将运行在第一和第十五天的月10am
        job = newJob(SimpleJob.class).withIdentity("job5", "group1").build();

        cronTrigger = newTrigger().withIdentity("trigger5", "group1").withSchedule(cronSchedule("0 0 10am 1,15 * ?")).build();

        ft = scheduler.scheduleJob(job, cronTrigger);
        logger.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                + cronTrigger.getCronExpression());

        //job6
        job = newJob(SimpleJob.class).withIdentity("job6", "group1").build();
        cronTrigger = newTrigger().withIdentity("trigger6", "group1").withSchedule(cronSchedule("0,30 * * ? * MON-FRI"))
                .build();

        ft = scheduler.scheduleJob(job, cronTrigger);
        logger.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                + cronTrigger.getCronExpression());

        // job 7 will run every 30 seconds but only on Weekends (Saturday and Sunday)
        job = newJob(SimpleJob.class).withIdentity("job7", "group1").build();

        cronTrigger = newTrigger().withIdentity("trigger7", "group1").withSchedule(cronSchedule("0,30 * * ? * SAT,SUN"))
                .build();

        ft = scheduler.scheduleJob(job, cronTrigger);
        logger.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                + cronTrigger.getCronExpression());

        logger.info("----------Starting Scheduler-----------");
        scheduler.start();
        logger.info("------------Started Scheduler-------------");

        try {
            Thread.sleep(300L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("------shutting Down");
        scheduler.shutdown(true);
        logger.info("------shutdown Complete");

        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed" + metaData.getNumberOfJobsExecuted() + "jobs");
    }

    public static void main(String[] args) throws SchedulerException {
        CronTriggerExample cronTriggerExample = new CronTriggerExample();
        cronTriggerExample.run();
    }
}
/*
* 1、seconds
* 2、Minutes
* 3、Hours
* 4、Day-of-Month 可以用数字1-31中的任一一个值，但要注意一些特别的月份
* 5、Month 可以用0-11或用字符串"JAN,FEB,MAR,APR,MAY,JUN,JUL,AUG,SEP,OCT,NOV and DEC"表示
* 6、Day-of-week 可以用数字1-7表示（1=星期日）
* 7、Year
*
* “/”：为特别单位，表示为“每”如“0/15”表示每隔15分钟执行一次,“0”表示为从“0”分开始, “3/20”表示表示每隔20分钟执行一次，“3”表示从第3分钟开始执行
* “?”：“？”字符仅被用于天（月）和天（星期）两个子表达式，表示不指定值
*  当2个子表达式其中之一被指定了值以后，为了避免冲突，需要将另一个子表达式的值设为“？”
* “L”：用于每月，或每周，表示为每月的最后一天，或每个月的最后星期几如“6L”表示“每月的最后一个星期五”
* “W”：表示为最近工作日，如“15W”放在每月（day-of-month）字段上表示为“到本月15日最近的工作日”
* “#”：是用来指定“的”每月第n个工作日,例 在每周（day-of-week）这个字段中内容为"6#3" or "FRI#3" 则表示“每月第三个星期五”
* “*” 代表整个时间段
* “,”字符：指定数个值
* “-”字符：指定一个值的范围
* 格式: [秒] [分] [小时] [日] [月] [周] [年]
* 序号 说明 是否必填 允许填写的值 允许的通配符
* 1 秒 是 0-59 , - * /
* 2 分 是 0-59 , - * /
* 3 小时 是 0-23 , - * /
* 4 日 是 1-31 , - * ? / L W
* 5 月 是 1-12 or JAN-DEC , - * /
* 6 周 是 1-7 or SUN-SAT , - * ? / L #
* 7 年 否 empty 或 1970-2099 , - * /
*
*
* */























































