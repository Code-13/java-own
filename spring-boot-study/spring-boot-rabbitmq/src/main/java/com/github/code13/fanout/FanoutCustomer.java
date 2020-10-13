package com.github.code13.fanout;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author code13
 * @date 2020/10/13 23:28
 */
@Component
public class FanoutCustomer {

  @RabbitListener(bindings = {
    @QueueBinding(
      value = @Queue, //创建临时队列
      exchange = @Exchange(value = "logs", type = ExchangeTypes.FANOUT))//交换机
  })
  public void receive1(String message) {
    System.out.println("message1 -----" + message);
  }

  @RabbitListener(bindings = {
    @QueueBinding(
      value = @Queue, //创建临时队列
      exchange = @Exchange(value = "logs", type = ExchangeTypes.FANOUT))//交换机
  })
  public void receive2(String message) {
    System.out.println("message2 -----" + message);
  }

}
