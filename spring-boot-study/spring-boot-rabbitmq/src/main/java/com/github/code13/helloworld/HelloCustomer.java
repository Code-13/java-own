package com.github.code13.helloworld;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author code13
 * @date 2020/10/13 23:12
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("helloWorld"))
public class HelloCustomer {

  @RabbitHandler
  public void reveive(String message) {
    System.out.println("message----" + message);
  }

}
