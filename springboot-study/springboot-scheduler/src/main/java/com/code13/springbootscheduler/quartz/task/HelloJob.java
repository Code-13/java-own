package com.code13.springbootscheduler.quartz.task;

import com.code13.springbootscheduler.quartz.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * 测试任务类
 *
 * @author code13
 * @date 2020-02-11 22:52
 */
@Slf4j
// 阻止任务并发执行
@DisallowConcurrentExecution
@Component
public class HelloJob extends QuartzJobBean {

  @Autowired
  private QuartzService quartzService;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    // 获取jobDetail
    JobDetail jobDetail = context.getJobDetail();

    // 取出 JobDataMao中的数据
    JobDataMap jobDataMap = jobDetail.getJobDataMap();

    String value = jobDataMap.getString("key");

    System.out.println(value);

    System.out.println("测试成功");

    System.out.println(quartzService.count());

    //JobKey key = context.getJobDetail().getKey();
  }
}
