package com.code13.springbootscheduler.springasync.controller;

import com.code13.springbootscheduler.springasync.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步任务测试
 *
 * @author code13
 * @date 2020-02-12 16:06
 */
@RestController
public class AsyncController {

  @Autowired
  private AsyncService asyncService;

  @GetMapping("/hello")
  public String hello() {
    asyncService.hello();
    return "hello";
  }

}
