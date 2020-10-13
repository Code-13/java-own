package com.github.code13.mq.rabbitmq.publishsubscribe;

import com.github.code13.mq.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import org.junit.Test;

import java.io.IOException;

/**
 * @author code13
 * @date 2020/10/13 20:03
 */
public class Provider {

  @Test
  public void testSendMessage() throws IOException {
    var connection = RabbitMQUtils.getConnection();

    var channel = connection.createChannel();

    // 通道声明交换机 ，交换机不存在则创建
    // 参数一： 交换机名称
    // 参数二： 交换机类型 广播为 fanout
    channel.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);

    // 发布
    channel.basicPublish("logs", "", null, "fanout".getBytes());

    RabbitMQUtils.close(channel, connection);
  }

}
