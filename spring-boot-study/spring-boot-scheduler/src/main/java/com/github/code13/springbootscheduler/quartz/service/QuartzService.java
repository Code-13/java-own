package com.github.code13.springbootscheduler.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.code13.springbootscheduler.quartz.entity.Task;

/**
 * Quartz
 *
 * @author code13
 * @date 2020-02-11 22:04
 */
public interface QuartzService extends IService<Task> {

  /**
   * 初始化任务
   */
  void initScheduler();


}
