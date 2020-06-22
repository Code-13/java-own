package com.github.code13.springbootscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * spring定时任务
 *
 * @author code13
 * @date 2020-02-11 19:41
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringBootSchedulerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootSchedulerApplication.class, args);
  }

}
