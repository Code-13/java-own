package com.github.code13.springboot.event.event;

import com.github.code13.springboot.event.enetity.Order;

/**
 * 订单事件
 *
 * @author code13
 * @date 2020/6/24 00:26
 */
public class OrderEvent {

  private Order order;

  public OrderEvent(Order order) {
    this.order = order;
  }

  public Order getOrder() {
    return this.order;
  }
}
