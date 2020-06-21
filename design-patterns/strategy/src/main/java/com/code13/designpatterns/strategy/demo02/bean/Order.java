package com.code13.designpatterns.strategy.demo02.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Code13
 * @date 2020-06-21 21:15
 */
@Data
@Accessors(chain = true)
public class Order {

  /**
   * 订单来源
   */
  private String source;
  /**
   * 支付方式
   */
  private String payMethod;
  /**
   * 订单编号
   */
  private String code;
  /**
   * 订单金额
   */
  private BigDecimal amount;
  // ...其他的一些字段

}
