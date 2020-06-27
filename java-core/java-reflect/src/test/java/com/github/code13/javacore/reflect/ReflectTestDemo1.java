package com.github.code13.javacore.reflect;

import org.junit.jupiter.api.Test;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author code13
 * @date 2020/6/27 09:44
 */
public class ReflectTestDemo1 {

  /**
   * spring方法参数
   */
  private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

  /**
   * 方法参数名示例
   *
   * @throws ClassNotFoundException ClassNotFoundException
   */
  @Test
  public void test2() throws ClassNotFoundException {
    final Class<?> aClass = Class.forName("com.github.code13.javacore.reflect.ParameterTest");
    final Method[] methods = aClass.getMethods();
    for (Method method : methods) {
      System.out.println(method.getName());
      final String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
      if (parameterNames == null) {
        continue;
      }
      for (String parameterName : parameterNames) {
        System.out.println("\t\t" + parameterName);
      }
    }
  }

  /**
   * 方法参数名示例
   *
   * @throws ClassNotFoundException ClassNotFoundException
   */
  @Test
  public void test1() throws ClassNotFoundException {
    final Class<?> aClass = Class.forName("com.github.code13.javacore.reflect.ParameterTest");
    final Method[] methods = aClass.getMethods();
    final Constructor<?>[] constructors = aClass.getConstructors();
    for (Constructor<?> constructor : constructors) {
      final Parameter[] parameters = constructor.getParameters();
      for (Parameter parameter : parameters) {
        this.printParameter(parameter);
      }
    }

    for (Method method : methods) {
      final Parameter[] parameters = method.getParameters();
      for (Parameter parameter : parameters) {
        this.printParameter(parameter);
      }

    }

  }

  private void printParameter(Parameter parameter) {
    final Class<?> type = parameter.getType();
    System.out.println(type.equals(String.class));
    System.out.println(type);
    System.out.println(parameter.getName());
    System.out.println(parameter.isImplicit());
    System.out.println(parameter.isNamePresent());
    System.out.println(parameter.isSynthetic());
    System.out.println(parameter.isVarArgs());

  }

}
