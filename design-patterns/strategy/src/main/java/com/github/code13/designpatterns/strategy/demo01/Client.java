package com.github.code13.designpatterns.strategy.demo01;

import com.github.code13.designpatterns.strategy.demo01.chain.ReceiptHandleChain;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 客户端
 *
 * @author Code13
 * @date 2020-06-21 20:19
 */
public class Client {

  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    ////模拟回执
    //List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
    ////策略上下文
    //ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();
    //for (Receipt receipt : receiptList) {
    //  //获取并设置策略
    //  IReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategy(receipt.getType());
    //  receiptStrategyContext.setReceiptHandleStrategy(receiptHandleStrategy);
    //  //执行策略
    //  receiptStrategyContext.handleReceipt(receipt);
    //}


    //模拟回执
    List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
    for (Receipt receipt : receiptList) {
      //回执处理链对象
      ReceiptHandleChain receiptHandleChain = new ReceiptHandleChain();
      receiptHandleChain.handleReceipt(receipt);
    }

  }


}
