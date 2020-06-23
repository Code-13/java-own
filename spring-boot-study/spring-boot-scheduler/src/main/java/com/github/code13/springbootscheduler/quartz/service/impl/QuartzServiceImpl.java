package com.github.code13.springbootscheduler.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.code13.springbootscheduler.quartz.dao.QuartzMapper;
import com.github.code13.springbootscheduler.quartz.entity.Task;
import com.github.code13.springbootscheduler.quartz.entity.enums.JobStatusEnum;
import com.github.code13.springbootscheduler.quartz.service.QuartzService;
import com.github.code13.springbootscheduler.quartz.util.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Quartz实现
 *
 * @author code13
 * @date 2020-02-11 22:05
 */
@Service
public class QuartzServiceImpl extends ServiceImpl<QuartzMapper, Task> implements QuartzService {

  @Autowired
  private QuartzMapper quartzMapper;

  @Autowired
  private QuartzManager quartzManager;

  @Override
  public void initScheduler() {
    List<Task> list = this.list();
    list.forEach(task -> {
      if (Objects.equals(task.getJobStatus(), JobStatusEnum.RUNNING)) {
        quartzManager.addJob(task);
      }
    });
  }

}
