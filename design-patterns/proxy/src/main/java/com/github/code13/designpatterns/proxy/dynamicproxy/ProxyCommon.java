package com.github.code13.designpatterns.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author code13
 * @date 2020-02-13 21:52
 */
public class ProxyCommon implements InvocationHandler {

  /**
   * 被代理的接口
   */
  private Object target;

  public ProxyCommon(Object rent) {
    this.target = rent;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    //此处就是执行前

    //动态代理的本质就是使用反射
    Object result = method.invoke(target, args);

    //此处就是执行后

    return result;
  }

  /**
   * 生成得到代理对象
   */
  public Object getProxy() {
    return Proxy.newProxyInstance(this.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
  }

}
