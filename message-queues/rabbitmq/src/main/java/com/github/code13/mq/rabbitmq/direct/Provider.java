package com.github.code13.mq.rabbitmq.direct;

import com.github.code13.mq.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import org.junit.Test;

import java.io.IOException;

/**
 * @author code13
 * @date 2020/10/13 22:22
 */
public class Provider {

  public static final String EXCHANGE = "logs_direct";

  @Test
  public void testSendMessage() throws IOException {
    var connection = RabbitMQUtils.getConnection();

    var channel = connection.createChannel();

    // 通道声明交换机 ，交换机不存在则创建
    channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);

    var routingKey = "error";

    // 发布
    channel.basicPublish(EXCHANGE, routingKey, null, ("这是direct发布的基于RoutingKey:{" + routingKey + "}的消息").getBytes());

    RabbitMQUtils.close(channel, connection);
  }

}
