package com.code13.designpatterns.strategy.demo01.chain;

import com.code13.designpatterns.strategy.demo01.Receipt;

/**
 * 责任链接口
 *
 * @author Code13
 * @date 2020-06-21 20:46
 */
public interface IReceiptHandleChain {

  void handleReceipt(Receipt receipt);

}
