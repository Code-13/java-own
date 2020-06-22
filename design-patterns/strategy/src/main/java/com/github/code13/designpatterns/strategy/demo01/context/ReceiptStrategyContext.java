package com.github.code13.designpatterns.strategy.demo01.context;

import com.github.code13.designpatterns.strategy.demo01.handler.IReceiptHandleStrategy;

/**
 * 策略上下文
 *
 * @author Code13
 * @date 2020-06-21 20:17
 */
public class ReceiptStrategyContext {

  private IReceiptHandleStrategy receiptHandleStrategy;

  /**
   * 设置策略接口
   *
   * @param receiptHandleStrategy receiptHandleStrategy
   */
  public void setReceiptHandleStrategy(IReceiptHandleStrategy receiptHandleStrategy) {
    this.receiptHandleStrategy = receiptHandleStrategy;
  }

  //public void handleReceipt(Receipt receipt) {
  //  if (this.receiptHandleStrategy != null) {
  //    this.receiptHandleStrategy.handleReceipt(receipt);
  //  }
  //}

}
