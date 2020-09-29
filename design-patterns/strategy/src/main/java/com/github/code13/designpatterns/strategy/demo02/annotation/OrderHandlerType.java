package com.github.code13.designpatterns.strategy.demo02.annotation;

import com.github.code13.designpatterns.strategy.demo02.service.OrderType;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 2.定义一个OrderHandlerType注解，来表示某个类是用来处理何种来源的订单。
 *
 * @author Code13
 * @date 2020-06-21 21:17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface OrderHandlerType {

  //String source();

  //String payMethod();

  OrderType value();

}
