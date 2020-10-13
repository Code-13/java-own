package com.github.code13.topics;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author code13
 * @date 2020/10/13 23:39
 */
@Component
public class TopicsCustomer {

  @RabbitListener(bindings = {
    @QueueBinding(
      value = @Queue,
      exchange = @Exchange(value = "topics", type = ExchangeTypes.TOPIC),
      key = {"user.save", "user.*"}
    )
  })
  public void receive1(String message) {
    System.out.println("message1----" + message);
  }

  @RabbitListener(bindings = {
    @QueueBinding(
      value = @Queue,
      exchange = @Exchange(value = "topics", type = ExchangeTypes.TOPIC),
      key = {"order.#", "product.#", "user.*"}
    )
  })
  public void receive2(String message) {
    System.out.println("message2----" + message);
  }

}
