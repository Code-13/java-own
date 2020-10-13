package com.github.code13.mq.rabbitmq.topics;

import com.github.code13.mq.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author code13
 * @date 2020/10/13 22:50
 */
public class CustomerOne {

  public static void main(String[] args) throws IOException {
    var connection = RabbitMQUtils.getConnection();
    var channel = connection.createChannel();

    channel.exchangeDeclare(Provider.EXCHANGE, BuiltinExchangeType.TOPIC);

    var queue = channel.queueDeclare().getQueue();

    channel.queueBind(queue, Provider.EXCHANGE, "user.*");

    channel.basicConsume(queue, true, new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("Customer 1 " + new String(body));
      }
    });

  }

}
