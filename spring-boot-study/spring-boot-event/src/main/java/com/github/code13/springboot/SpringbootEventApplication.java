package com.github.code13.springboot;

import com.github.code13.springboot.event.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 应用
 *
 * @author code13
 * @date 2020/6/24 00:17
 */
@EnableAsync
@SpringBootApplication
public class SpringbootEventApplication {

  private static OrderService ORDER_SERVICE;

  @Autowired
  public void setOrderService(OrderService orderService) {
    ORDER_SERVICE = orderService;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringbootEventApplication.class, args);
    ORDER_SERVICE.saveOrder();
  }

}
