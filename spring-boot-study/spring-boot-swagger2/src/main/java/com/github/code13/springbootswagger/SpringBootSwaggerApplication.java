package com.github.code13.springbootswagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot swagger主启动类
 *
 * @author code13
 * @date 2020-02-12 16:23
 */
@EnableSwagger2Doc
@EnableKnife4j
@SpringBootApplication
public class SpringBootSwaggerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootSwaggerApplication.class, args);
  }

}
