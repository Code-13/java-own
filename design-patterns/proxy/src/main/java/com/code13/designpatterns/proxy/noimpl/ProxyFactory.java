package com.code13.designpatterns.proxy.noimpl;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Code13
 * @date 2020-05-23 13:31
 */
public class ProxyFactory<T> {

  private Class<T> target;

  public ProxyFactory(Class<T> target) {
    this.target = target;
  }


  public T getProxy() {
    return (T) Proxy.newProxyInstance(this.target.getClassLoader(), new Class[]{this.target}, (proxy, method, args) -> {
      System.out.println(method.getName());
      System.out.println(Arrays.toString(args));
      System.out.println("我仅仅代理了一个接口的方法，而没有代理有具体实现类的方法");
      return null;
    });
  }

}
