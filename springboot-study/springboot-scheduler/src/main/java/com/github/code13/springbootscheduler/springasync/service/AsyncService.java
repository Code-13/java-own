package com.github.code13.springbootscheduler.springasync.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步任务
 *
 * @author code13
 * @date 2020-02-12 16:05
 */
@Service
public class AsyncService {

  @Async
  public void hello() {
    try {
      Thread.sleep(3000);
      System.out.println("处理任务中");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
