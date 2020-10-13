package com.github.code13;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author code13
 * @date 2020/10/13 23:09
 */
@SpringBootTest(classes = RabbitMqApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMq {

  @Autowired
  RabbitTemplate rabbitTemplate;

  @Test
  public void helloWord() {
    this.rabbitTemplate.convertAndSend("helloWorld", "hello World");
  }

  @Test
  public void workQueue() {
    for (int i = 0; i < 20; i++) {
      this.rabbitTemplate.convertAndSend("workqueue", "workqueue" + i);
    }
  }

  @Test
  public void fanout() {
    this.rabbitTemplate.convertAndSend("logs", "", "fanout");
  }

  @Test
  public void routing() {
    this.rabbitTemplate.convertAndSend("logs_direct", "info", "info等级的信息");
  }

  @Test
  public void topics() {
    this.rabbitTemplate.convertAndSend("topics", "user.save", "topics");
  }

}
