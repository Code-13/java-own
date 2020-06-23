package com.github.code13.springboot.event.service;

import com.github.code13.springboot.event.enetity.Order;
import com.github.code13.springboot.event.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 订单处理类
 *
 * @author code13
 * @date 2020/6/24 00:19
 */
@Slf4j
@Service
public class OrderService {

  //private ApplicationContext applicationContext;

  private ApplicationEventPublisher applicationEventPublisher;

  public OrderService(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public void saveOrder() {
    log.info("保存订单");
    final Order order = new Order();
    order.setId(1L);
    order.setMember("测试");
    //this.applicationContext.publishEvent(new OrderEvent(order));
    this.applicationEventPublisher.publishEvent(new OrderEvent(order));
    log.info("保存订单成功");
  }

}
