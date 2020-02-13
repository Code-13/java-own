package com.code13.designpatterns.proxy.demo01;

/**
 * 代理角色
 *
 * @author code13
 * @date 2020-02-13 21:10
 */
public class Proxy implements Rent {

  private Host host;

  public Proxy() {
  }

  public Proxy(Host host) {
    this.host = host;
  }

  @Override
  public void rent() {
    seeHouse();
    host.rent();
    hetong();
    fare();
  }

  public void seeHouse() {
    System.out.println("中介带你看房");
  }

  public void fare() {
    System.out.println("收中介费");
  }

  public void hetong() {
    System.out.println("中介签合同");
  }

}
