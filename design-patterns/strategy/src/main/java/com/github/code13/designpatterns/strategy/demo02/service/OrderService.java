package com.github.code13.designpatterns.strategy.demo02.service;

import com.github.code13.designpatterns.strategy.demo02.annotation.OrderHandlerType;
import com.github.code13.designpatterns.strategy.demo02.annotation.OrderHandlerTypeImpl;
import com.github.code13.designpatterns.strategy.demo02.bean.Order;
import com.github.code13.designpatterns.strategy.demo02.handler.OrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单处理服务
 *
 * @author Code13
 * @date 2020-06-21 21:22
 */
@Service
public class OrderService {

  // 第一种方式
  /*private Map<String, OrderHandler> orderHandleMap;

  @Autowired
  public void setOrderHandleMap(List<OrderHandler> orderHandlers) {
    this.orderHandleMap =
      orderHandlers.stream().collect(
        Collectors.toMap(orderHandler ->
          Objects.requireNonNull(AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderHandlerType.class)).source(), v -> v, (v1, v2) -> v1));


  }

  public void orderService(Order order) {
    // ...一些前置处理

    // 通过订单来源确定对应的handler
    OrderHandler orderHandler = this.orderHandleMap.get(order.getSource());
    orderHandler.handle(order);

    // ...一些后置处理
  }*/

  // 第二种方式

  private Map<OrderHandlerType, OrderHandler> orderHandleMap;


  @Autowired
  public void setOrderHandleMap(List<OrderHandler> orderHandlers) {
    // 注入各种类型的订单处理类
    this.orderHandleMap = orderHandlers.stream().collect(
      Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderHandlerType.class),
        v -> v, (v1, v2) -> v1));
  }

  public void orderService(Order order) {
    // ...一些前置处理

    // 通过订单来源确以及支付方式获取对应的handler
    OrderHandlerType orderHandlerType = new OrderHandlerTypeImpl(order.getSource(), order.getPayMethod());
    OrderHandler orderHandler = this.orderHandleMap.get(orderHandlerType);
    orderHandler.handle(order);

    // ...一些后置处理
  }

}
