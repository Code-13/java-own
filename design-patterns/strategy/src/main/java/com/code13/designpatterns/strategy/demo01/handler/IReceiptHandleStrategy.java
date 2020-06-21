package com.code13.designpatterns.strategy.demo01.handler;

import com.code13.designpatterns.strategy.demo01.Receipt;
import com.code13.designpatterns.strategy.demo01.chain.IReceiptHandleChain;

/**
 * 绘制i处理策略接口
 *
 * @author Code13
 * @date 2020-06-21 20:15
 */
public interface IReceiptHandleStrategy {

  /**
   * 处理回执
   *
   * @param receipt     回执
   * @param handleChain 处理链
   */
  void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain);

}
