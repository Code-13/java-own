package com.github.code13.designpatterns.strategy.demo02.handler;

import com.github.code13.designpatterns.strategy.demo02.annotation.OrderHandlerType;
import com.github.code13.designpatterns.strategy.demo02.bean.Order;

/**
 * 手机订单处理实现
 *
 * @author Code13
 * @date 2020-06-21 21:18
 */
@OrderHandlerType(source = "mobile", payMethod = "weixin")
public class MobileOrderHandler implements OrderHandler {

  @Override
  public void handle(Order order) {
    System.out.println("处理移动端订单");
  }

}
