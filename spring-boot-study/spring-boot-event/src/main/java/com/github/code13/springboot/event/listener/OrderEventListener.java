package com.github.code13.springboot.event.listener;

import com.github.code13.springboot.event.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 订单事件监听处理器
 *
 * @author code13
 * @date 2020/6/24 00:31
 */
@Slf4j
@Component
public class OrderEventListener {

  @Async
  @EventListener(OrderEvent.class)
  @Order(10)
  public void deductStock(OrderEvent orderEvent) {

    log.info("第一个事件");

    //return new OrderEvent(new com.github.code13.springboot.event.enetity.Order());
  }

  @Async
  @TransactionalEventListener(value = OrderEvent.class, phase = TransactionPhase.AFTER_ROLLBACK)
  @Order(3)
  public void sendEmail(OrderEvent orderEvent) {
    log.info("第二个事件");
  }

  @EventListener({ContextStartedEvent.class, ContextRefreshedEvent.class})
  public void handleContextStart() {
    // ...
    log.info("测试多参数");
  }

  @EventListener
  public void gen(OrderEvent orderEvent) {
    // ...
    log.info("测试非参数");
  }

  //@EventListener
  //public void gen1() {
  //  // ...
  //  log.info("异常");
  //}

}
