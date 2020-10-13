package com.github.code13.mq.rabbitmq.topics;

import com.github.code13.mq.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import org.junit.Test;

import java.io.IOException;

/**
 * @author code13
 * @date 2020/10/13 22:47
 */
public class Provider {

  public static final String EXCHANGE = "topics";

  @Test
  public void testSendMessage() throws IOException {
    var connection = RabbitMQUtils.getConnection();
    var channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.TOPIC);

    String routingKey = "user.save";

    channel.basicPublish(EXCHANGE, routingKey, null, routingKey.getBytes());

    RabbitMQUtils.close(channel, connection);
  }

}
