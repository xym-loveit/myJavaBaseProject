package com.xym.myJava.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 基于quartz实现调度
 *
 * @author xym
 * @create 2018-09-04 13:39
 */
public class QuartzScheduleMain implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("要去数据库扫描啦。。。");
    }

    public static void main(String[] args) throws SchedulerException {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(QuartzScheduleMain.class).withIdentity("job1", "group1").build();
        //创建触发器，每三秒执行一次
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        // 将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        // 调度器开始调度任务
        scheduler.start();
    }
}
