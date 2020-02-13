package com.code13.springbootscheduler.quartz.util;

import com.code13.springbootscheduler.quartz.entity.Task;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * quartz任务管理
 *
 * @author code13
 * @date 2020-02-11 22:34
 */
@Component
public class QuartzManager {

  @Autowired
  private Scheduler scheduler;

  @SuppressWarnings("unchecked")
  public void addJob(Task task) {

    try {
      // 创建jobDetail实例，绑定Job实现类
      // 指明job的名称，所在组的名称，以及绑定job类

      Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(task.getBeanClass());

      JobDetail jobDetail = JobBuilder.newJob(jobClass)
          .withIdentity(task.getJobName(), task.getJobGroup())
          // 设置JobDataMap 中的数据
          .usingJobData("key", "value")
          .build();

      // 定义调度触发规则
      // 使用cornTrigger规则
      CronTrigger trigger = TriggerBuilder.newTrigger()
          .withIdentity(task.getJobName(), task.getJobGroup())
          .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
          .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression()))
          .startNow()
          .build();

      // 把作业和触发器注册到任务调度中
      scheduler.scheduleJob(jobDetail, trigger);

      if (!scheduler.isShutdown()) {
        scheduler.start();
      }

    } catch (ClassNotFoundException | SchedulerException e) {
      e.printStackTrace();
    }


  }

}
