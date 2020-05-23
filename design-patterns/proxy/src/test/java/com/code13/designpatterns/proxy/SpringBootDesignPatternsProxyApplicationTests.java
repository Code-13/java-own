package com.code13.designpatterns.proxy;

import com.code13.designpatterns.proxy.demo01.Host;
import com.code13.designpatterns.proxy.demo01.Proxy;
import com.code13.designpatterns.proxy.demo01.Rent;
import com.code13.designpatterns.proxy.dynamicproxy.ProxyCommon;
import com.code13.designpatterns.proxy.noimpl.IUserDao;
import com.code13.designpatterns.proxy.noimpl.ProxyFactory;
import org.junit.jupiter.api.Test;

/**
 * 测试类
 *
 * @author code13
 * @date 2020-02-13 21:04
 */
public class SpringBootDesignPatternsProxyApplicationTests {

  /**
   * 代理的优点
   *  1.可以使真实角色的操作更加纯粹，不用去关注一些公众业务
   *  2.公共的业务就可以交给代理角色，实现了业务的分工
   *  3.公共业务发生拓展的时候，方便集中管理
   * 代理的缺点
   *  一个真实角色就会产生一个代理角色；代码量会翻倍，开发效率会变低
   */

  /**
   * 接口 真实角色 代理角色 使用代理角色
   */

  /**
   * 静态代理
   */
  @Test
  void demo01() {
    // 直接
    Host host = new Host();
    //host.rent();


    // 代理 代理一般会有附属操作
    Proxy proxy = new Proxy(host);
    proxy.rent();
  }

  /**
   * 动态代理
   *
   * 动态代理的代理类是动态生成的
   *
   * 动态代理分类：
   *  1.基于接口 JDK动态代理
   *  2.基于类 cglib
   *  3.java字节码 javassist
   */

  /**
   * {@link java.lang.reflect.Proxy}
   * {@link java.lang.reflect.InvocationHandler}
   */
  @Test
  public void dynamicProxy() {
    Host host = new Host();
    ProxyCommon common = new ProxyCommon(host);
    Rent proxy = (Rent) common.getProxy();
    proxy.rent();
  }

  @Test
  public void noImplTest() {
    IUserDao dao = new ProxyFactory<>(IUserDao.class).getProxy();
    dao.saveUser();
  }

}
