package com.github.code13.springbootscheduler.quartz.listener;

import com.github.code13.springbootscheduler.quartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 启动quartz
 *
 * @author code13
 * @date 2020-02-11 20:03
 */
@Component
@Order(-1)
public class ScheduleJobInitListener implements ApplicationRunner {

  @Autowired
  private QuartzService scheduleJobService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    this.scheduleJobService.initScheduler();
  }
}
