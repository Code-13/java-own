package com.github.code13.ap.annotation;

import java.lang.annotation.*;

/**
 * @author Code13
 * @date 2020-09-30 14:50
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Factory {

  /**
   * 工厂的名字
   */
  Class<?> type();

  /**
   * 用来表示生成哪个对象的唯一id
   */
  String id();

}
