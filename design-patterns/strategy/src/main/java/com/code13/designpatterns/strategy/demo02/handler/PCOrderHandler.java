package com.code13.designpatterns.strategy.demo02.handler;

import com.code13.designpatterns.strategy.demo02.annotation.OrderHandlerType;
import com.code13.designpatterns.strategy.demo02.bean.Order;

/**
 * PC订单接口
 *
 * @author Code13
 * @date 2020-06-21 21:20
 */
@OrderHandlerType(source = "pc", payMethod = "weixin")
public class PCOrderHandler implements OrderHandler {

  @Override
  public void handle(Order order) {
    System.out.println("处理PC端微信订单");
  }

}
