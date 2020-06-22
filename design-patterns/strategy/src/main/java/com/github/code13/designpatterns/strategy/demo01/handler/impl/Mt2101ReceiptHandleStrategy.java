package com.github.code13.designpatterns.strategy.demo01.handler.impl;

import com.github.code13.designpatterns.strategy.demo01.Receipt;
import com.github.code13.designpatterns.strategy.demo01.chain.IReceiptHandleChain;
import com.github.code13.designpatterns.strategy.demo01.handler.IReceiptHandleStrategy;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Code13
 * @date 2020-06-21 20:16
 */
public class Mt2101ReceiptHandleStrategy implements IReceiptHandleStrategy {

  @Override
  public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
    if (StringUtils.equals("MT2101", receipt.getType())) {
      System.out.println("解析报文MT2101:" + receipt.getMessage());
    }
    //处理不了该回执就往下传递
    else {
      handleChain.handleReceipt(receipt);
    }
  }

}
