package com.github.code13.designpatterns.strategy.demo02.handler;

import com.github.code13.designpatterns.strategy.demo02.annotation.OrderHandlerType;
import com.github.code13.designpatterns.strategy.demo02.bean.Order;
import com.github.code13.designpatterns.strategy.demo02.service.OrderType;

/**
 * PC订单接口
 *
 * @author Code13
 * @date 2020-06-21 21:20
 */
@OrderHandlerType(OrderType.PC)
public class PCOrderHandler implements OrderHandler {

  @Override
  public void handle(Order order) {
    System.out.println("处理PC端微信订单");
  }

}
