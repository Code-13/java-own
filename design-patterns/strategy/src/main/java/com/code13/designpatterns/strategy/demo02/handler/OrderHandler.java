package com.code13.designpatterns.strategy.demo02.handler;

import com.code13.designpatterns.strategy.demo02.bean.Order;

/**
 * 订单处理
 *
 * @author Code13
 * @date 2020-06-21 21:16
 */
public interface OrderHandler {

  void handle(Order order);

}
