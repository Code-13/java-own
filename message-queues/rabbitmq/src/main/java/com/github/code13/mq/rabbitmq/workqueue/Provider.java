package com.github.code13.mq.rabbitmq.workqueue;

import com.github.code13.mq.rabbitmq.utils.RabbitMQUtils;
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

    //通道绑定对应消息队列
    // 参数1： 队列名称 如果队列不存在自动创建
    // 参数2： 用来定义队列特性是否要持久化
    // 参数3： 是否独占队列
    // 参数4： 是否在消费完成后自动删除队列
    // 参数5： 额外附加参数
    channel.queueDeclare("workqueue", true, false, false, null);

    //发布消息
    // 参数1： 交换机名称
    // 参数2： 队列名称
    // 参数3： 传递消息额外设置
    // 参数4： 消息的具体内容

    for (int i = 1; i <= 20; i++) {
      channel.basicPublish("", "workqueue", null, ("workqueue RabbitMq ==> " + i).getBytes());
    }

    //关闭连接
    RabbitMQUtils.close(channel, connection);
  }

}
