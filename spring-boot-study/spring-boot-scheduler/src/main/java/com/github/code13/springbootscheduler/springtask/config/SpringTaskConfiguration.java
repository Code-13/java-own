package com.github.code13.springbootscheduler.springtask.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * spring定时任务
 * <p>
 * TaskExecutor、TaskScheduler
 * <p>
 * 再配置类上添加 @EnableScheduling 注解
 * 在方法上加上 @Scheduled 注解
 *
 * @author code13
 * @date 2020-02-12 15:42
 */
@Configuration
public class SpringTaskConfiguration {

  /**
   * second, minute, hour, day of month, month, and day of week.
   * <p>
   * "0 * * * * MON-FRI"
   * <p>
   * , 枚举； - 区间；* 任意；/ 步长；？ 日/星期冲突匹配；L 最后；W 工作日；C 和Calendar联系后计算过的值；# 星期，4#2 第二个星期4
   */
  //@Scheduled(cron = "0 * * * * MON-WED")
  //@Scheduled(cron = "0,1,2,3,4 * * * * MON-WED")
  //@Scheduled(cron = "0-4 * * * * MON-WED")
  @Scheduled(cron = "0/4 * * * * MON-WED")             // 每 4 秒执行一次
  public void hello() {
    System.out.println("hello");
  }

}
