package com.code13.designpatterns.strategy.demo02.annotation;

import java.lang.annotation.Annotation;

/**
 * 订单处理器类型实现
 *
 * @author Code13
 * @date 2020-06-21 21:39
 */
public class OrderHandlerTypeImpl implements OrderHandlerType {

  private String source;
  private String payMethod;

  public OrderHandlerTypeImpl(String source, String payMethod) {
    this.source = source;
    this.payMethod = payMethod;
  }

  @Override
  public String source() {
    return this.source;
  }

  @Override
  public String payMethod() {
    return this.payMethod;
  }

  @Override
  public Class<? extends Annotation> annotationType() {
    return OrderHandlerType.class;
  }

  /**
   * hashCode
   */
  @Override
  public int hashCode() {
    int hashCode = 0;
    hashCode += (127 * "source".hashCode()) ^ this.source.hashCode();
    hashCode += (127 * "payMethod".hashCode()) ^ this.payMethod.hashCode();
    return hashCode;
  }


  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof OrderHandlerType)) {
      return false;
    }
    OrderHandlerType other = (OrderHandlerType) obj;
    return this.source.equals(other.source()) && this.payMethod.equals(other.payMethod());
  }
}
