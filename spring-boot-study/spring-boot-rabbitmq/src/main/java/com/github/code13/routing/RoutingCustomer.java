package com.github.code13.routing;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author code13
 * @date 2020/10/13 23:33
 */
@Component
public class RoutingCustomer {

  @RabbitListener(bindings = {
    @QueueBinding(
      value = @Queue,
      exchange = @Exchange(value = "logs_direct", type = ExchangeTypes.DIRECT),
      key = {"info", "error", "debug"}
    )
  })
  public void receive1(String message) {
    System.out.println("message1----" + message);
  }

  @RabbitListener(bindings = {
    @QueueBinding(
      value = @Queue,
      exchange = @Exchange(value = "logs_direct", type = ExchangeTypes.DIRECT),
      key = {"error"}
    )
  })
  public void receive2(String message) {
    System.out.println("message2----" + message);
  }

}
