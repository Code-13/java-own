package com.github.code13.mq.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author code13
 * @date 2020/10/13 19:50
 */
public class RabbitMQUtils {

  private static final ConnectionFactory connectionFactory;

  static {
    connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("39.105.51.99");
    connectionFactory.setPort(5672);
    connectionFactory.setVirtualHost("/");
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
  }

  public static Connection getConnection() {
    try {
      return connectionFactory.newConnection();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void close(Channel channel, Connection connection) {
    try {
      channel.close();
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
