package com.github.code13.mq.rabbitmq.helloworld;

import com.github.code13.mq.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author code13
 * @date 2020/10/13 20:05
 */
public class Customer {

  public static void main(String[] args) throws IOException {

    // 工具类获取连接
    var connection = RabbitMQUtils.getConnection();

    //创建通道
    var channel = connection.createChannel();

    //通道绑定对队列
    channel.queueDeclare("helloWorld", false, false, false, null);

    // 消费消息
    // 参数一： 消费哪个队列的消息 队列名称
    // 参数二： 开始消息的自动确认机制
    // 参数三： 消费时的回调接口
    channel.basicConsume("helloWorld", true, new DefaultConsumer(channel) {
      // body就是消息队列取出的消息
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("====" + new String(body));
      }
    });

  }

}
