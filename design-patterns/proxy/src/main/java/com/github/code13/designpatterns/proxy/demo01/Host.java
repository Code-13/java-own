package com.github.code13.designpatterns.proxy.demo01;

/**
 * 房东
 *
 * @author code13
 * @date 2020-02-13 21:09
 */
public class Host implements Rent {

  @Override
  public void rent() {
    System.out.println("房东要出租房子");
  }
}
