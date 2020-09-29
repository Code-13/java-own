package com.github.code13.designpatterns.strategy.demo02.service;

import com.github.code13.designpatterns.strategy.demo02.annotation.OrderHandlerType;
import com.github.code13.designpatterns.strategy.demo02.bean.Order;
import com.github.code13.designpatterns.strategy.demo02.handler.MobileOrderHandler;
import com.github.code13.designpatterns.strategy.demo02.handler.OrderHandler;
import com.github.code13.designpatterns.strategy.demo02.handler.PCOrderHandler;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author Code13
 * @date 2020-09-29 09:54
 */
public class TestStrategy extends AbstractStrategyHolder<OrderHandler, OrderHandlerType, OrderType> {

  protected TestStrategy(List<OrderHandler> strategyList) {
    super(strategyList);
  }

  //@Override
  //protected Class<OrderHandlerType> annotationType() {
  //  return OrderHandlerType.class;
  //}

  //@Override
  //protected String annotationMethodName() {
  //  return "value";
  //}

  public static void main(String[] args) {
    final TestStrategy testStrategy = new TestStrategy(Arrays.asList(new MobileOrderHandler(), new PCOrderHandler()));

    final Type[] types = testStrategy.getTypes();
    System.out.println(Arrays.toString(types));

    final OrderHandler handler = testStrategy.getHandler(OrderType.PC);
    handler.handle(new Order());
  }

}
