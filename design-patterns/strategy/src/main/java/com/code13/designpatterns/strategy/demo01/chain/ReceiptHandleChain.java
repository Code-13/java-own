package com.code13.designpatterns.strategy.demo01.chain;

import com.code13.designpatterns.strategy.demo01.Receipt;
import com.code13.designpatterns.strategy.demo01.handler.IReceiptHandleStrategy;
import com.code13.designpatterns.strategy.demo01.handler.impl.Mt2101ReceiptHandleStrategy;
import com.code13.designpatterns.strategy.demo01.handler.impl.Mt8104ReceiptHandleStrategy;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理链
 *
 * @author Code13
 * @date 2020-06-21 20:47
 */
public class ReceiptHandleChain implements IReceiptHandleChain {

  private int index = 0;

  //处理者集合
  private static List<IReceiptHandleStrategy> receiptHandlerList;

  static {
    //从容器中获取处理器对象
    //责任链处理者容器(如果采用spring,则可以通过依赖注入的方式获取到IReceiptHandler的子类对象)
    receiptHandlerList = new ArrayList<>();
    receiptHandlerList.add(new Mt2101ReceiptHandleStrategy());
    receiptHandlerList.add(new Mt8104ReceiptHandleStrategy());
  }

  @Override
  public void handleReceipt(Receipt receipt) {
    if (CollectionUtils.isNotEmpty(receiptHandlerList)) {
      if (this.index != receiptHandlerList.size()) {
        final IReceiptHandleStrategy iReceiptHandleStrategy = receiptHandlerList.get(this.index++);
        iReceiptHandleStrategy.handleReceipt(receipt, this);
      }
    }
  }
}
