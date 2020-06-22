package com.github.code13.designpatterns.strategy.demo01.factory;

import com.github.code13.designpatterns.strategy.demo01.handler.IReceiptHandleStrategy;
import com.github.code13.designpatterns.strategy.demo01.handler.impl.Mt2101ReceiptHandleStrategy;
import com.github.code13.designpatterns.strategy.demo01.handler.impl.Mt8104ReceiptHandleStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂
 *
 * @author Code13
 * @date 2020-06-21 20:18
 */
public class ReceiptHandleStrategyFactory {

  private static final Map<String, IReceiptHandleStrategy> RECEIPT_HANDLE_STRATEGY_MAP;

  static {
    RECEIPT_HANDLE_STRATEGY_MAP = new HashMap<>();
  }

  private ReceiptHandleStrategyFactory() {
    RECEIPT_HANDLE_STRATEGY_MAP.put("MT2101", new Mt2101ReceiptHandleStrategy());
    RECEIPT_HANDLE_STRATEGY_MAP.put("MT8104", new Mt8104ReceiptHandleStrategy());
  }

  public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType) {
    return RECEIPT_HANDLE_STRATEGY_MAP.get(receiptType);
  }

}
