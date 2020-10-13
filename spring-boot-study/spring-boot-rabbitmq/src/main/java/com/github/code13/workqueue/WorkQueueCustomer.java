package com.github.code13.workqueue;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author code13
 * @date 2020/10/13 23:20
 */
@Component
public class WorkQueueCustomer {

  @RabbitListener(queuesToDeclare = @Queue(value = "workqueue"))
  public void receive1(String message) {
    System.out.println("message1----" + message);
  }

  @RabbitListener(queuesToDeclare = @Queue(value = "workqueue"))
  public void receive2(String message) {
    System.out.println("message2----" + message);
  }

}
