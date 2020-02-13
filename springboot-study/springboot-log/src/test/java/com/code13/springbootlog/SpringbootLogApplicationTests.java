package com.code13.springbootlog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 可以在测试期间很方便的类似编码一样进行自动注入
 */
@SpringBootTest
public class SpringbootLogApplicationTests {

  @Test
  void contextLoads() {
    Logger logger = LoggerFactory.getLogger(getClass());

    // 日志的级别由低到高
    // 可以调整输出的日志级别;日子就会在这个级别和以后的高级别生效
    logger.trace("这是 trace 跟踪轨迹");
    logger.debug("这是 debug 日志");

    // springboot 默认 info 级别
    logger.info("这是 info 信息");
    logger.warn("这是 warn 日志");
    logger.error("这是 error 日志");


  }

}
