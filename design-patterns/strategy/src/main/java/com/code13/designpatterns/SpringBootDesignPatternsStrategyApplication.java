package com.code13.designpatterns;

import com.code13.designpatterns.strategy.demo02.bean.Order;
import com.code13.designpatterns.strategy.demo02.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 设计模式之代理模式
 *
 * @author code13
 * @date 2020-02-13 21:02
 */
@SpringBootApplication
public class SpringBootDesignPatternsStrategyApplication {

  private static OrderService ORDER_SERVICE;

  @Autowired
  public void setOrderService(OrderService orderService) {
    ORDER_SERVICE = orderService;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootDesignPatternsStrategyApplication.class, args);
    ORDER_SERVICE.orderService(new Order().setSource("pc").setPayMethod("weixin"));
  }

}
