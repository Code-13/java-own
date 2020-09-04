package com.github.code13.designpatterns.strategy.demo02.annotation;

import com.github.code13.designpatterns.strategy.demo02.handler.MobileOrderHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

  public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

    List<String> strings = Arrays.asList("equals", "toString", "hashCode", "annotationType");

    final OrderHandlerType annotation = MobileOrderHandler.class.getAnnotation(OrderHandlerType.class);

    final Method[] methods = OrderHandlerType.class.getMethods();

    for (Method method : methods) {
      System.out.println(method.getName());
    }

    final Set<Method> methodSet = Arrays.stream(methods).filter(method -> !strings.contains(method.getName())).collect(Collectors.toSet());

    final Map<String, Object> collect1 = methodSet.stream().collect(Collectors.toMap(Method::getName, method -> {
      try {
        return method.invoke(annotation);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }, (o, o2) -> o));

    System.out.println(collect1);

  }

}
