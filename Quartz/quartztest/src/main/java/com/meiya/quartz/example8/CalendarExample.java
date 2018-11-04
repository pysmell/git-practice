package com.meiya.quartz.example8;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class CalendarExample {

    private static Logger logger = LoggerFactory.getLogger(CalendarExample.class);

    public void run() throws SchedulerException {
        logger.info("------------initializing------------");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        logger.info("------------initialization Complete---------------");
        logger.info("-----------Scheduling Jobs------------------------");
        //创建Quartz的Calendar，法定节假日是以每年为周期的，所以使用AnnualCalendar
        AnnualCalendar annualCalendar = new AnnualCalendar();
        Calendar fourthOfJuly = new GregorianCalendar(2018, 6, 4);
        annualCalendar.setDayExcluded(fourthOfJuly, true); ////排除的日期，如果设置为false则为包含
        Calendar halloween = new GregorianCalendar(2018, 9 ,31);
        annualCalendar.setDayExcluded(halloween, true);
        //第一个参数日历的名字，第二个参数日历，第三个参数如果为true会替换掉同名的日历
        //第四个参数)如果为true，Scheduler中已引用Calendar的Trigger将得到更新
        scheduler.addCalendar("holidays", annualCalendar, false, false);

        Date runDate = dateOf(0, 0, 10, 31, 10);

        JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();
        SimpleTrigger trigger = newTrigger().withIdentity("trigger1", "group1").
                withSchedule(simpleSchedule().withIntervalInHours(1).repeatForever()).modifiedByCalendar("holidays").build();
        Date firstRunTime = scheduler.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + firstRunTime + "and repeat: " + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
        logger.info("----------Starting Scheduler------------");
        scheduler.start();
        logger.info("--------------Waiting 30 seconds-------------");
        try {
            Thread.sleep(30L * 1000L);
        } catch (Exception e) {}

        logger.info("-----------Shutting Down-----------");
        scheduler.shutdown(true);
        logger.info("-----------Shutdown Complete-------");
        SchedulerMetaData metaData = scheduler.getMetaData();
        logger.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs");
    }

    public static void main(String[] args) throws SchedulerException {
        CalendarExample calendarExample = new CalendarExample();
        calendarExample.run();
    }
}
/*
* AnnualCalendar 排除每一年中指定的一天或多天
* CronCalendar  使用表达式排除某些时间段不执行
* DailyCalendar  指定的时间范围内的每一天不执行
* HolidayCalendar 排除节假日
* MonthlyCalendar：排除月份中的数天
* WeeklyCalenday：排除星期中的一天或多天
* */

























